package com.bookverse.development.packapps.apps.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import lombok.SneakyThrows;
import com.bookverse.development.packapps.apps.utils.other.Format;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public final class QrService {

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
  public static String createQR(String data, int height, int width) {

    String folder = "target/qrs/";
    String file = Format.getNow() + ".jpg";
    String charset = "UTF-8";
    File fileToCreate = new File(folder + file);

    if (!Files.exists(Paths.get(folder))) {
      new File(folder).mkdirs();
    }

    BitMatrix matrix = new MultiFormatWriter().encode(new String(
        data.getBytes(charset), charset), BarcodeFormat.QR_CODE, width, height
    );

    MatrixToImageWriter.writeToPath(
        matrix,
        file.substring(file.lastIndexOf('.') + 1),
        fileToCreate.toPath()
    );

    return fileToCreate.getPath();
  }

  private QrService() {
  }
}
