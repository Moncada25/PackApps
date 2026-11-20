package com.bookverse.packapps;

import com.bookverse.packapps.apps.ocr.OcrService;
import com.bookverse.packapps.database.DatabaseConnection;
import com.bookverse.packapps.database.Queries;
import com.bookverse.packapps.database.notes.NotesQueries;
import com.bookverse.packapps.utils.ExportFile;
import java.awt.*;
import java.nio.file.*;
import javax.swing.JTable;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/** Runs against the libraries and Java runtime extracted from each release download. */
public final class DesktopChecks {
  private static void require(boolean ok, String message) {
    if (!ok) throw new AssertionError(message);
  }

  public static void main(String[] args) throws Exception {
    Path work = Path.of(args[0]);
    try (var connection = DatabaseConnection.getConnection()) {
      if (args[1].equals("write")) {
        try (var insert = connection.prepareStatement(Queries.insertDices())) {
          insert.setString(1, "tester");
          insert.setString(2, "winner");
          insert.setInt(3, 1);
          insert.setString(4, "2026-01-01");
          insert.executeUpdate();
        }
        try (var insert = connection.prepareStatement(NotesQueries.insertNewNote())) {
          for (int i = 1; i <= 6; i++) insert.setString(i, "1");
          insert.executeUpdate();
        }
        try (var insert = connection.prepareStatement(Queries.insertInventory())) {
          insert.setString(1, "sample");
          insert.setString(2, "available");
          insert.setDouble(3, 2.5);
          insert.setInt(4, 3);
          insert.executeUpdate();
        }
        try (var update = Queries.updateInventory("sample", 2).prepare(connection)) {
          require(update.executeUpdate() == 1, "inventory update");
        }
        return;
      }
      for (String table : new String[] {"dices", "notes", "inventory"}) {
        try (var stmt = connection.createStatement();
            var rows = stmt.executeQuery("SELECT COUNT(*) FROM " + table)) {
          require(rows.next() && rows.getInt(1) == 1, "restart persistence for " + table);
        }
      }
    }

    var table = new JTable(new Object[][] {{"Ana", "canción"}}, new Object[] {"Name", "Text"});
    for (String ext : new String[] {".pdf", ".xls", ".txt"})
      ExportFile.write(table, "Notes", work.resolve("notes" + ext), ext);
    try (var doc = Loader.loadPDF(work.resolve("notes.pdf").toFile())) {
      require(new PDFTextStripper().getText(doc).contains("canción"), "PDF text");
    }
    try (var input = Files.newInputStream(work.resolve("notes.xls"));
        var book = new HSSFWorkbook(input)) {
      require(
          book.getSheetAt(0).getRow(1).getCell(1).getStringCellValue().equals("canción"),
          "Excel data");
    }
    require(Files.readString(work.resolve("notes.txt")).contains("canción"), "TXT text");

    var image = work.resolve("ocr.png");
    try (var fixture = DesktopChecks.class.getResourceAsStream("/bilingual.png")) {
      Files.copy(fixture, image);
    }
    String recognized = new OcrService().readText(image.toString(), true);
    require(
        recognized.contains("garden") && recognized.contains("mañana"),
        "OCR output: " + recognized);
    for (int i = 1; i <= 14; i++)
      require(
          DesktopChecks.class.getResource("/images/img" + i + ".jpg") != null, "wallpaper " + i);
  }
}
