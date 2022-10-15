package com.bookverse.development.packapps.apps.services;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public final class OCRService {

  public static String readText(String image) {

    Tesseract tesseract = new Tesseract();
    tesseract.setDatapath("src/main/resources/tessdata");
    tesseract.setPageSegMode(1);
    tesseract.setOcrEngineMode(1);

    try {
      return tesseract.doOCR(new File(image));
    } catch (TesseractException e) {
      return "Error";
    }
  }

  private OCRService(){
  }
}
