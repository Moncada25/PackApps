package com.bookverse.development.packapps.utils;

import javax.swing.JOptionPane;

public class Alerts {

  public static void error(Exception exception, String parent){
    JOptionPane.showMessageDialog(null, exception.getMessage(), "Error from "+parent, JOptionPane.ERROR_MESSAGE);
  }
}
