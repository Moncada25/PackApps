package com.bookverse.development.packapps.utils;

import javax.swing.JOptionPane;
import org.jetbrains.annotations.NotNull;

public class Alerts {

  public static void error(@NotNull Exception exception, String parent) {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style() + "<strong>" + exception.getMessage()
            + "</strong></html>",
        "Error from " + parent, JOptionPane.ERROR_MESSAGE);
  }

  public static void message(String tittle, String message) {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style() + "<strong>" + message + "</strong></html>",
        tittle, JOptionPane.PLAIN_MESSAGE);
  }

  public static void emptyTable() {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style()
            + "<u><strong><center>Empty Table</center></strong></u><br>"
            + "The search did not return any results.</html>",
        "No records found!", JOptionPane.PLAIN_MESSAGE);
  }

  public static void changeUI(String aspect) {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style()
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
    return JOptionPane.showInputDialog(null, "<html>" + Format.style()
            + "<strong><em>What are you looking for?</em></strong></html>",
        "Search records", JOptionPane.PLAIN_MESSAGE, null, new Object[]{"ID", "Nickname"}, "ID");
  }

  public static void inputSomethingText() {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style() + "<strong>Input something text...</strong></html>",
        "Text empty!",
        JOptionPane.PLAIN_MESSAGE);
  }

  public static void export(String file) {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style() + "<strong><center>Export successfully</center><br></strong>" +
            "<strong>Saved in: </strong>"
            + file + "</html>",
        "Done!", JOptionPane.PLAIN_MESSAGE);
  }

  public static boolean replaceFile() {
    return JOptionPane.YES_OPTION != JOptionPane.showConfirmDialog(null,
        "<html>" + Format.style() + "<strong>Do you want to replace it?</strong></html>",
        "The file already exist!",
        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
  }

  public static String inputText(String request) {
    return JOptionPane.showInputDialog(null,
        "<html>" + Format.style() + "<strong>" + request + "</strong></html>", "Message",
        JOptionPane.PLAIN_MESSAGE);
  }

  public static void inputLarge() {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style() + "<strong>Input too large</strong></html>", "Message",
        JOptionPane.PLAIN_MESSAGE);
  }

  public static void invalidInput() {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style() + "<strong>Invalid input... try again.</strong></html>",
        "Message",
        JOptionPane.PLAIN_MESSAGE);
  }

  public static void onlyNumbers(int length) {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style() + "<strong>Only numbers less than " + length
            + " digits</strong></html>", "Error",
        JOptionPane.PLAIN_MESSAGE);
  }

  public static boolean requestResponse(String request, String tittle){
    return JOptionPane.showConfirmDialog(null, request, tittle,
        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION;
  }

  public static void existProduct() {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style()
            + "<strong><center>The product already exists in inventory.</center></strong><br>"
            + "The reference you want to buy already exists in inventory,<br>"
            + "but with different information, change the reference or search<br>"
            + "the product in the inventory to make the purchase.</html>",
        "Reference found!", JOptionPane.PLAIN_MESSAGE);
  }

  public static void actionSuccessfully(String action, String quantityOfProducts, double totalPurchases) {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style() + "<strong><center>Done!</center></strong><br><strong>"
            + quantityOfProducts + "</strong> products "+action+" with  a total of <strong>$"
            + String.format("%.0f", totalPurchases) + "</strong></html>",
        "Success", JOptionPane.PLAIN_MESSAGE);
  }

  public static void fieldMailRequired(){
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style() + "<strong><center>Secure credentials</center></strong><br>"
            + "The use of this medium is authorized by Google through<br>"
            + "the use of its JavaMail API, your data is protected!</html>",
        "Required field!!", JOptionPane.PLAIN_MESSAGE);
  }
}