package com.bookverse.development.packapps.utils;

import com.bookverse.development.packapps.core.Core;
import javax.swing.JOptionPane;

public class Alerts {

  public static void error(Exception exception, String parent){
    JOptionPane.showMessageDialog(null, "<html>" + Core.styleJOption() + "<strong>" + exception.getMessage() +"</strong></html>", "Error from "+parent, JOptionPane.ERROR_MESSAGE);
  }

  public static void message(String tittle, String message){
    JOptionPane.showMessageDialog(null,
        "<html>" + Core.styleJOption() + "<strong>" + message +"</strong></html>",
        tittle, JOptionPane.PLAIN_MESSAGE);
  }

  public static void emptyTable() {
    JOptionPane.showMessageDialog(null,
        "<html>" + Core.styleJOption() + "<u><strong><center>Empty Table</center></strong></u><br>"
            + "The search did not return any results.</html>",
        "No records found!", JOptionPane.PLAIN_MESSAGE);
  }
}