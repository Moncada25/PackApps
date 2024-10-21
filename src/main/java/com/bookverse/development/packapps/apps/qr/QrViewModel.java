package com.bookverse.development.packapps.apps.qr;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import lombok.Data;

@Data
public class QrViewModel {
  private JLabel readQR;
  private JLabel generateQR;
  private JLabel exit;
  private JTextArea text;
}
