package com.bookverse.development.packapps.apps.qr;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
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
import com.bookverse.development.packapps.utils.other.Format;

public class QrService {

  public String readQR(String image) {

    BinaryBitmap binaryBitmap;

    try {

      binaryBitmap = new BinaryBitmap(new HybridBinarizer(
          new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(image))))
      );

      return new MultiFormatReader().decode(binaryBitmap).getText();

    } catch (IOException | NotFoundException e) {
      return "Error";
    }
  }

  @SneakyThrows
  public String createQR(String data, int height, int width) {

    String folder = "target/qrs/";
    String file = Format.getNow() + ".jpg";
    String charset = "UTF-8";
    File folderFile = new File(folder);
    File fileToCreate = new File(folder + file);

    if (!folderFile.exists()) {
      boolean foldercreated = folderFile.mkdirs();

      if (!foldercreated) {
        throw new IOException("Error creating folder");
      }
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

  public void showQRGenerated(String image, JDialog parent) {

    JLabel code = new JLabel();
    code.setIcon(new ImageIcon(image));
    code.setSize(400, 400);

    JDialog result = new JDialog(parent, true);

    result.setLayout(null);
    result.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    result.setSize(412, 427);
    result.setTitle("QR code generated");
    result.setResizable(false);
    result.setLocationRelativeTo(null);

    ((JPanel) result.getContentPane()).setOpaque(false);

    result.add(code);
    result.setVisible(true);
  }
}
