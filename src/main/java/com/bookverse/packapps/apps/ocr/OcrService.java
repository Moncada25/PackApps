package com.bookverse.packapps.apps.ocr;

import java.io.File;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OcrService {

  public String readText(String image, boolean onlyText) {

    ITesseract tesseract = new Tesseract();
    tesseract.setDatapath("src/main/resources/tessdata");
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
