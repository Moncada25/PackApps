package com.bookverse.packapps.utils;

import com.bookverse.packapps.database.Query;
import com.bookverse.packapps.repositories.OlderRepository;
import com.bookverse.packapps.utils.ui.Alerts;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public final class ExportFile {
  private ExportFile() {}

  public static void pdf(JTable table,String title,Query query,String file) { export(table,title,query,file); }
  public static void excel(JTable table,Query query,String file) { export(table,"PackApps",query,file); }
  public static void txt(JTable table,Query query,String file) { export(table,"PackApps",query,file); }

  private static void export(JTable table,String title,Query query,String extension) {
    var chooser = new JFileChooser();
    chooser.setFileFilter(new FileNameExtensionFilter(extension.substring(1).toUpperCase(),extension.substring(1)));
    if (chooser.showSaveDialog(table) != JFileChooser.APPROVE_OPTION) return;
    String name = chooser.getSelectedFile().getAbsolutePath();
    Path path = Path.of(name.endsWith(extension) ? name : name + extension);
    if (Files.exists(path) && JOptionPane.showConfirmDialog(table,"Replace " + path.getFileName() + "?",
        "Export", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;
    ((javax.swing.table.DefaultTableModel)table.getModel()).setRowCount(0);
    if (!OlderRepository.readTable(table,query,false)) return;
    try {
      write(table,title,path,extension);
      Alerts.export(path.toString());
    } catch (IOException e) { Alerts.error(e,"Export"); }
  }

  public static void write(JTable table,String title,Path path,String extension) throws IOException {
    var rows = new ArrayList<List<String>>();
    rows.add(IntStream.range(0,table.getColumnCount()).mapToObj(table::getColumnName).toList());
    for (int r=0;r<table.getRowCount();r++) {
      var row = new ArrayList<String>();
      for (int c=0;c<table.getColumnCount();c++) row.add(java.util.Objects.toString(table.getValueAt(r,c),""));
      rows.add(row);
    }
    switch (extension) {
      case ".txt" -> {
        try (var writer=Files.newBufferedWriter(path)) {
          writer.write("{" + String.join(", ",rows.getFirst()) + "} -> Columns\n\n");
          for (var row:rows.subList(1,rows.size())) writer.write(row + "\n");
        }
      }
      case ".xls" -> {
        try (var book=new HSSFWorkbook(); var output=Files.newOutputStream(path)) {
          var sheet=book.createSheet("PackApps");
          for (int i=0;i<rows.size();i++) {
            var row=sheet.createRow(i);
            for (int j=0;j<rows.get(i).size();j++) row.createCell(j).setCellValue(rows.get(i).get(j));
          }
          book.write(output);
        }
      }
      case ".pdf" -> writePdf(title, rows, path);
      default -> throw new IllegalArgumentException(extension);
    }
  }

  private static void writePdf(String title,List<List<String>> rows,Path path) throws IOException {
    try (var document=new PDDocument(); var in=ExportFile.class.getResourceAsStream("/fonts/NotoSans-Regular.ttf")) {
      var font=PDType0Font.load(document,in);
      float width=760f/rows.getFirst().size();
      var lines=new ArrayList<List<String>>();
      for (var row:rows) {
        var columns=new ArrayList<List<String>>();
        for (var cell:row) {
          var wrapped=new ArrayList<String>();
          StringBuilder line=new StringBuilder();
          for (int cp:cell.replaceAll("\\p{Cntrl}"," ").codePoints().toArray()) {
            String ch=new String(Character.toChars(cp));
            try { font.encode(ch); } catch (IllegalArgumentException missing) { ch="?"; }
            if (!line.isEmpty() && font.getStringWidth(line.toString()+ch)*0.009f>width-5) {
              wrapped.add(line.toString()); line.setLength(0);
            }
            line.append(ch);
          }
          wrapped.add(line.toString()); columns.add(wrapped);
        }
        int height=columns.stream().mapToInt(List::size).max().orElse(1);
        for (int i=0;i<height;i++) {
          var line=new ArrayList<String>();
          for (var column:columns) line.add(i<column.size()?column.get(i):"");
          lines.add(line);
        }
      }
      for (int offset=0;offset<lines.size();offset+=38) {
        var page=new PDPage(new PDRectangle(842,595)); document.addPage(page);
        try (var content=new PDPageContentStream(document,page)) {
          content.beginText(); content.setFont(font,14); content.newLineAtOffset(40,555);
          content.showText(title+" - PackApps"); content.endText();
          int y=530;
          for (var line:lines.subList(offset,Math.min(offset+38,lines.size()))) {
            for (int col=0;col<line.size();col++) {
              content.beginText(); content.setFont(font,9); content.newLineAtOffset(40+col*width,y);
              content.showText(line.get(col)); content.endText();
            }
            y-=13;
          }
        }
      }
      document.save(path.toFile());
    }
  }
}
