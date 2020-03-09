package com.bookverse.development.packapps.automation.utils;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class TextUtility {

  public static void textPass(char txt, KeyEvent evt, String datos, int cantidad){

    if (datos.length() > cantidad - 1 && txt != KeyEvent.VK_ENTER) {
      JOptionPane
          .showMessageDialog(null, "<html><strong>No se permiten más caracteres</strong></html>",
              "Mensaje", JOptionPane.PLAIN_MESSAGE);
      evt.consume();
    }
  }
}
