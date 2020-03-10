package com.bookverse.development.packapps.utils;

import static com.bookverse.development.packapps.core.Core.styleJOption;

import com.bookverse.development.packapps.core.Core;
import javax.swing.JOptionPane;

public class Alerts {

  public static void error(Exception exception, String parent) {
    JOptionPane.showMessageDialog(null,
        "<html>" + Core.styleJOption() + "<strong>" + exception.getMessage() + "</strong></html>",
        "Error from " + parent, JOptionPane.ERROR_MESSAGE);
  }

  public static void message(String tittle, String message) {
    JOptionPane.showMessageDialog(null,
        "<html>" + Core.styleJOption() + "<strong>" + message + "</strong></html>",
        tittle, JOptionPane.PLAIN_MESSAGE);
  }

  public static void emptyTable() {
    JOptionPane.showMessageDialog(null,
        "<html>" + Core.styleJOption() + "<u><strong><center>Empty Table</center></strong></u><br>"
            + "The search did not return any results.</html>",
        "No records found!", JOptionPane.PLAIN_MESSAGE);
  }

  public static void changeUI(String aspect) {
    JOptionPane.showMessageDialog(null,
        "<html>" + Core.styleJOption()
            + "<strong><center>Changes Saved</center></strong><br>"
            + "Modified UI, enjoy the " + aspect + " aspect!</html>",
        "New Look!", JOptionPane.PLAIN_MESSAGE);
  }

  public static void elementApplied(boolean isUI) {

    if (isUI) {
      JOptionPane.showMessageDialog(null,
          "This UI is already applied, try another.",
          "UI applied", JOptionPane.PLAIN_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(null,
          "This background is already applied, try another.",
          "Background applied", JOptionPane.PLAIN_MESSAGE);
    }
  }

  public static Object searchRecords() {
    return JOptionPane.showInputDialog(null, "<html>" + Core.styleJOption()
            + "<strong><em>What are you looking for?</em></strong></html>",
        "Search records", JOptionPane.PLAIN_MESSAGE, null, new Object[]{"ID", "Nickname" }, "ID");
  }

  public static void inputSomethingText() {
    JOptionPane.showMessageDialog(null,
        "<html>" + styleJOption() + "<strong>Input something text...</strong></html>",
        "Text empty!",
        JOptionPane.PLAIN_MESSAGE);
  }

  public static void export(String file){
    JOptionPane.showMessageDialog(null,
        "<html>" + styleJOption() + "<strong><center>Export successfully</center><br></strong>"+
            "<strong>Saved in: </strong>"
            + file + "</html>",
        "Done!", JOptionPane.PLAIN_MESSAGE);
  }

  public static boolean replaceFile() {
    return JOptionPane.YES_OPTION != JOptionPane.showConfirmDialog(null,
        "<html>" + styleJOption() + "<strong>Do you want to replace it?</strong></html>",
        "The file already exist!",
        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
  }
}