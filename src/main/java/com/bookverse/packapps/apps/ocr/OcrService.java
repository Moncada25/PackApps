package com.bookverse.packapps.apps.ocr;

import com.bookverse.packapps.utils.GeneralUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import lombok.SneakyThrows;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OcrService {

  private static void copyTessdataFromJar(File outputDir) throws IOException {

    if (!outputDir.exists() && !outputDir.mkdirs()) {
      throw new IOException("Folder not found " + outputDir.getAbsolutePath());
    }

    String tessdataPathInJar = "/tessdata";
    String[] resourceFiles = {"spa.traineddata", "eng.traineddata"};

    for (String resourceFile : resourceFiles) {
      String file = tessdataPathInJar + "/" + resourceFile;

      try (InputStream inputStream = OcrService.class.getResourceAsStream(file)) {
        if (inputStream == null) {
          System.err.println("File not found: " + resourceFile);
          continue;
        }

        File outputFile = new File(outputDir, resourceFile);

        try (OutputStream outputStream = new FileOutputStream(outputFile)) {
          byte[] buffer = new byte[1024];
          int bytesRead;
          while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
          }
        }

        System.out.println("File copied " + outputFile.getAbsolutePath());
      }
    }
  }

  @SneakyThrows
  public String readText(String image, boolean onlyText) {

    String outputDir = GeneralUtils.getOutputDirectory() + "tessdata";
    File tessdataDir = new File(outputDir);

    if (!tessdataDir.exists() || Objects.requireNonNull(tessdataDir.listFiles()).length == 0) {
      copyTessdataFromJar(tessdataDir);
    }

    ITesseract tesseract = new Tesseract();
    tesseract.setDatapath(tessdataDir.getAbsolutePath());
    tesseract.setPageSegMode(1);
    tesseract.setOcrEngineMode(1);

    if (!onlyText) {
      tesseract.setVariable("tessedit_create_hocr", "1");
    }

    try {
      return tesseract.doOCR(new File(image));
    } catch (TesseractException e) {
      return "Error";
    }
  }
}
