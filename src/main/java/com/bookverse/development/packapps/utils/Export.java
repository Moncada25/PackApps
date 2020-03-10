package com.bookverse.development.packapps.utils;

import com.bookverse.development.packapps.models.Database;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jetbrains.annotations.NotNull;

public class Export {

  private static int n = 1;
  private static String filePath;

  @NotNull
  private static String setPath(@NotNull String file) {

    JFileChooser chooser = new JFileChooser();

    switch (file) {

      case ".pdf":
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF Document (.pdf)", "pdf"));
        break;

      case ".xls":
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("XLS Document (.xls)", "xls"));
        break;

      case ".txt":
        chooser
            .addChoosableFileFilter(new FileNameExtensionFilter("TXT Document (.txt)", "txt"));
        break;
    }

    chooser.setAcceptAllFileFilterUsed(false);

    String path = "";
    try {

      if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
        path = chooser.getSelectedFile().getAbsolutePath();
      }

    } catch (Exception ex) {
      Alerts.error(ex, "setPath()");
    }
    return path + file;
  }

  private static String verifyExist(String file) {

    filePath = setPath(file);

    if (!filePath.equals(file)) {

      if (new File(filePath).exists()) {

        if (Alerts.replaceFile()) {
          filePath = filePath.substring(0, filePath.length() - 4) + "(" + (n++) + ")" + file;
        }
      }
    }

    return filePath;
  }

  public static void pdf(JTable jTable, String title, String query, String file) {

    filePath = verifyExist(file);

    if (!filePath.equals(file)) {

      if (new Database().readTable(jTable, query, false)) {

        try {

          Document document = new Document();

          try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }

          document.open();

          document.addTitle(title);
          document.addSubject("Made in PackApps");
          document.addKeywords("Java, PDF, PackApps");
          document.addAuthor("SMONCADA");
          document.addCreator("PackApps");

          Anchor anchor = new Anchor(title + " - PackApps");
          anchor.setName(title);

          Chapter catPart = new Chapter(new Paragraph(anchor), 1);
          Paragraph subPara = new Paragraph();
          Section subCatPart = catPart.addSection(subPara);
          PdfPTable table = new PdfPTable(jTable.getColumnCount());
          PdfPCell columnHeader;

          for (int column = 0; column < jTable.getColumnCount(); column++) {
            columnHeader = new PdfPCell(new Phrase(jTable.getColumnName(column)));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(columnHeader);
          }
          table.setHeaderRows(1);

          for (int row = 0; row < jTable.getRowCount(); row++) {
            for (int column = 0; column < jTable.getColumnCount(); column++) {
              table.addCell(jTable.getValueAt(row, column).toString());
            }
          }
          subCatPart.add(table);
          document.add(catPart);
          document.close();

          Alerts.export(filePath);

        } catch (DocumentException documentException) {
          Alerts.error(documentException, "Export PDF");
        }
      }
    }
  }

  public static void excel(JTable jTable1, String query, String file) throws IOException {

    filePath = verifyExist(file);

    if (!filePath.equals(file)) {

      if (new Database().readTable(jTable1, query, false)) {

        int rowCount = jTable1.getRowCount();
        int columnCount = jTable1.getColumnCount();
        Workbook wb;
        wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet(" ");

        for (int i = 0; i < 2; i++) {
          Row row = sheet.createRow(i);
          for (int j = 0; j < columnCount; j++) {
            Cell cell = row.createCell(j);

            cell.setCellValue(String.valueOf(jTable1.getColumnName(j)));
            wb.write(new FileOutputStream(filePath));
          }
        }

        for (int i = 0; i < rowCount; i++) {
          Row row = sheet.createRow(i + 1);
          for (int j = 0; j < columnCount; j++) {
            Cell cell = row.createCell(j);

            cell.setCellValue(String.valueOf(jTable1.getValueAt(i, j)));
            wb.write(new FileOutputStream(filePath));
          }
        }
        Alerts.export(filePath);
      }
    }
  }

  public static void txt(JTable table, String query, String file) {

    filePath = verifyExist(file);

    if (!filePath.equals(file)) {

      if (new Database().readTable(table, query, false)) {

        try {

          BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));

          bufferedWriter.write("{");
          for (int k = 0; k < table.getColumnCount(); k++) {
            bufferedWriter.write(table.getColumnName(k));
            if (k < table.getColumnCount() - 1) {
              bufferedWriter.write(", ");
            }
          }
          bufferedWriter.write("} -> Columns");

          bufferedWriter.newLine();
          bufferedWriter.newLine();

          for (int i = 0; i < table.getRowCount(); i++) {
            bufferedWriter.write("[");
            for (int j = 0; j < table.getColumnCount(); j++) {
              bufferedWriter.write(String.valueOf((table.getValueAt(i, j))));
              if (j < table.getColumnCount() - 1) {
                bufferedWriter.write(", ");
              }
            }
            bufferedWriter.write("]");
            bufferedWriter.newLine();
          }

          bufferedWriter.close();
          Alerts.export(filePath);

        } catch (IOException e) {
          Alerts.error(e, "Export TXT");
        }
      }
    }
  }
}