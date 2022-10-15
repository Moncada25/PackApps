package com.bookverse.development.packapps.apps.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import lombok.SneakyThrows;

import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public final class QRService {

  public static String readQR(String image) {

    BinaryBitmap binaryBitmap;

    try {

      binaryBitmap = new BinaryBitmap(
          new HybridBinarizer(new BufferedImageLuminanceSource(
              ImageIO.read(new FileInputStream(image))))
      );

      return new MultiFormatReader().decode(binaryBitmap).getText();

    } catch (IOException | NotFoundException e) {
      return "Error";
    }
  }

  @SneakyThrows
  public static void createQR(String data, String path, int height, int width) {

    final String CHARSET = "UTF-8";

    BitMatrix matrix = new MultiFormatWriter().encode(
        new String(data.getBytes(CHARSET), CHARSET), BarcodeFormat.QR_CODE, width, height);

    MatrixToImageWriter.writeToPath(
        matrix,
        path.substring(path.lastIndexOf('.') + 1),
        new File(path).toPath()
    );
  }

  private QRService(){
  }
}
