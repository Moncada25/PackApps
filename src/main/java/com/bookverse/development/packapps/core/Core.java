package com.bookverse.development.packapps.core;

import com.bookverse.development.packapps.models.Database;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/*
Date 23/12/18
8748 view p = 336.46 : 26 class
2282 model p = 207.45 : 11 class
1229 controller p = 307.25 : 4 class
21 images : 1 class
12280 total  : 42 class
*/

/*
Date 23/01/19
11912 view p = 321.94 : 37 class
956 model p = 478 : 2 class
2773 controller p = 554.6 : 5 class
19 resources : 1 class ? 93 images
15660 total  : 45 class
*/

/*
Date 10/06/19
13430 view p = 344.35 : 39 class
954 model p = 477 : 2 class
1375 controller p = 1375 : 1 class
100 resources p = 33.33 : 3 class ? 108 images
15859 total  : 45 class
*/

public class Core {

  //EXPORTAR
  public static int n = 1;
  public final Color AZUL = new Color(21, 87, 163);
  public final Color ROJO = new Color(220, 12, 12);
  public final Border MEDIO = BorderFactory.createLineBorder(AZUL, 2, true);
  public final Border HARD = BorderFactory.createLineBorder(ROJO, 2, true);
  public final Cursor MIRA = new Cursor(Cursor.CROSSHAIR_CURSOR);
  public final Cursor CARGAR = new Cursor(Cursor.WAIT_CURSOR);
  public final Cursor REDI = new Cursor(Cursor.NE_RESIZE_CURSOR);
  public final Cursor TEXT = new Cursor(Cursor.TEXT_CURSOR);
  public final Cursor MANO = new Cursor(Cursor.HAND_CURSOR);
  public final Font SMALL = new Font("Times New Roman", 0, 15);
  public final Font MEDIUM = new Font("Times New Roman", 0, 20);
  public final Font BIG = new Font("Times New Roman", 0, 30);
  private double phi;
  private double totalCompra = 0, totalVenta = 0, totalPrestamo = 2;
  private int contProd = 0;

  public void soloUnPunto(char num, KeyEvent evt,
      String datos) { // Valida que solo se ingrese un punto

    if (num == '.' && datos.contains(".")) {
      evt.consume();
    }
  }

  public void soloTexto(char txt, KeyEvent evt, String datos, int cantidad) {
    if ((txt < 'a' || txt > 'z') && (txt < 'A' || txt > 'Z') && txt != KeyEvent.VK_ESCAPE
        && txt != KeyEvent.VK_ENTER && txt != KeyEvent.VK_DELETE
        && (txt == KeyEvent.VK_BACK_SPACE || txt != ' ') && (txt != ',' || txt != '.')
        || datos.length() > cantidad - 1) {

      if (datos.length() > cantidad - 1 && txt != KeyEvent.VK_ENTER) {
        JOptionPane
            .showMessageDialog(null, "<html><strong>No se permiten más letras</strong></html>");
      } else if ((txt < 'a' || txt > 'z') && txt != KeyEvent.VK_ESCAPE && txt != KeyEvent.VK_ENTER
          && txt == KeyEvent.VK_DELETE && (txt == KeyEvent.VK_BACK_SPACE || txt != ' ')
          && (txt != ',' || txt != '.')) {
        JOptionPane.showMessageDialog(null, "<html><strong>¡Only text!</strong></html>", "Mensaje",
            JOptionPane.PLAIN_MESSAGE);
      }
      evt.consume();
    }
  }

  public void solonumeros(char num, KeyEvent evt, String datos, int cantidad) {
    if ((num < '0' || num > '9') && num != KeyEvent.VK_ESCAPE && num != KeyEvent.VK_ENTER
        && num != KeyEvent.VK_DELETE && (num != KeyEvent.VK_BACK_SPACE || num == ' ')
        && (num != ',' || num != '.') || datos.length() > cantidad - 1) {

      if (datos.length() > cantidad - 1 && num != KeyEvent.VK_ENTER) {
        JOptionPane
            .showMessageDialog(null, "<html><strong>No se permiten más dígitos</strong></html>",
                "Mensaje", JOptionPane.PLAIN_MESSAGE);
      } else if ((num < '0' || num > '9') && num != KeyEvent.VK_ESCAPE && num != KeyEvent.VK_ENTER
          && num != KeyEvent.VK_DELETE && (num != KeyEvent.VK_BACK_SPACE || num == ' ')
          && (num != ',' || num != '.')) {
        JOptionPane
            .showMessageDialog(null, "<html><strong>¡Only numbers!</strong></html>", "Mensaje",
                JOptionPane.PLAIN_MESSAGE);
      }
      evt.consume();
    }
  }

  public void soloAlfa(char txt, KeyEvent evt, String datos, int cantidad) {
    if ((txt < 'a' || txt > 'z') && (txt < 'A' || txt > 'Z') && (txt < '0' || txt > '9')
        && txt != KeyEvent.VK_ESCAPE && txt != KeyEvent.VK_ENTER && txt != KeyEvent.VK_DELETE
        && (txt == KeyEvent.VK_BACK_SPACE || txt != ' ') && (txt != ',' || txt != '.')
        || datos.length() > cantidad - 1) {

      if (datos.length() > cantidad - 1 && txt != KeyEvent.VK_ENTER) {
        JOptionPane
            .showMessageDialog(null, "<html><strong>No se permiten más caracteres</strong></html>",
                "Mensaje", JOptionPane.PLAIN_MESSAGE);
      } else if ((txt < 'a' || txt > 'z') && (txt < '0' || txt > '9') && txt != KeyEvent.VK_ESCAPE
          && txt != KeyEvent.VK_ENTER && txt != KeyEvent.VK_DELETE
          && (txt != KeyEvent.VK_BACK_SPACE || txt == ' ') && (txt != ',' || txt != '.')) {
        JOptionPane
            .showMessageDialog(null, "<html><strong>¡Caracter inválido!</strong></html>", "Mensaje",
                JOptionPane.PLAIN_MESSAGE);
      }
      evt.consume();
    }
  }

  public void solonumerosYpunto(char num, KeyEvent evt, String datos, int cantidad) {
    if ((num < '0' || num > '9') && num != KeyEvent.VK_ESCAPE && num != KeyEvent.VK_ENTER
        && num != KeyEvent.VK_DELETE && (num != KeyEvent.VK_BACK_SPACE || num == ' ')
        && (num == ',' || num != '.') || datos.length() > cantidad - 1) {

      if (datos.length() > cantidad - 1 && num != KeyEvent.VK_ENTER && num != KeyEvent.VK_ESCAPE) {
        JOptionPane
            .showMessageDialog(null, "<html><strong>No se permiten más dígitos</strong></html>",
                "Mensaje", JOptionPane.PLAIN_MESSAGE);
      } else if ((num < '0' || num > '9') && num != KeyEvent.VK_ESCAPE && num != KeyEvent.VK_ENTER
          && num != KeyEvent.VK_DELETE && (num != KeyEvent.VK_BACK_SPACE || num == ' ')
          && (num == ',' || num != '.')) {
        JOptionPane
            .showMessageDialog(null, "<html><strong>¡Only numbers!</strong></html>", "Mensaje",
                JOptionPane.PLAIN_MESSAGE);
      }
      evt.consume();
    }
  }

  public void solonumerosCalc(char num, KeyEvent evt, String datos, int cantidad) {
    if ((num < '0' || num > '9') && (num != '+' && num != '-' && num != '*' && num != '/')
        && num != KeyEvent.VK_ESCAPE && num != KeyEvent.VK_ENTER && num != KeyEvent.VK_DELETE
        && (num != KeyEvent.VK_BACK_SPACE || num == ' ') && (num == ',' || num != '.')
        || datos.length() > cantidad - 1) {

      if (datos.length() > cantidad - 1 && num != KeyEvent.VK_ENTER && num != KeyEvent.VK_ESCAPE) {
        JOptionPane
            .showMessageDialog(null, "<html><strong>No se permiten más dígitos</strong></html>",
                "Mensaje", JOptionPane.PLAIN_MESSAGE);
      } else if ((num < '0' || num > '9') && num != KeyEvent.VK_ESCAPE && num != KeyEvent.VK_ENTER
          && num != KeyEvent.VK_DELETE && (num != KeyEvent.VK_BACK_SPACE || num == ' ')
          && (num == ',' || num != '.') && (num != '+' && num != '-' && num != '*' && num != '/')) {
        JOptionPane
            .showMessageDialog(null, "<html><strong>¡Only numbers!</strong></html>", "Mensaje",
                JOptionPane.PLAIN_MESSAGE);
      }
      evt.consume();
    }
  }

  public void PuntoMedio(char num, KeyEvent evt, String datos) {

    if (num == '.' && (datos.contains(".") || datos.equals("") || datos.length() > 1)) {
      evt.consume();
    }
  }

  // Valida que sólo se ingresen código binario (nueve dígitos)
  public void solobinario(char num, KeyEvent evt, String datos) {
    if ((num < '0' || num > '1') && num != KeyEvent.VK_ESCAPE && num != KeyEvent.VK_ENTER
        && num != KeyEvent.VK_DELETE && (num != KeyEvent.VK_BACK_SPACE || num == ' ')
        && (num != ',' || num != '.') || datos.length() > 8) {

      if (datos.length() > 8 && num != KeyEvent.VK_ENTER) {
        JOptionPane
            .showMessageDialog(null, "<html><strong>Sólo se permiten nueve dígitos</strong></html>",
                "Mensaje", JOptionPane.PLAIN_MESSAGE);
      } else if ((num != '0' || num != '1') && num != KeyEvent.VK_ESCAPE && num != KeyEvent.VK_ENTER
          && num != KeyEvent.VK_DELETE && (num != KeyEvent.VK_BACK_SPACE || num == ' ')
          && (num != ',' || num != '.')) {
        JOptionPane.showMessageDialog(null,
            "<html><strong>¡Sólo se permite código binario!</strong></html>",
            "Mensaje", JOptionPane.PLAIN_MESSAGE);
      }
      evt.consume();
    }
  }

  public void verificarPrimo(int num) {

    String divisores = "";
    int contGN = 1, contD = 0, cont = 0;

    while (contGN <= num) {

      if (num % contGN == 0) {
        contD++;
        cont++;
        divisores += "[" + contGN + "]";
      }

      if (cont == 10) {
        divisores += "\n";
        cont = 0;
      }

      contGN++;
    }

    if (contD == 2) {

      JOptionPane.showMessageDialog(
          null, "<html>" + styleJOption() + "<strong>" + num + " Sí es primo</strong><br><br>"
              + "<strong>Únicos divisores</strong> ? " + divisores + "</html>",
          "Cantidad divisores  " + contD, JOptionPane.PLAIN_MESSAGE);

    } else {

      JOptionPane.showMessageDialog(
          null, "<html>" + styleJOption() + "<strong>" + num + " NO es primo</strong><br><br>"
              + "<strong><em>Divisores</em></strong><br>" + divisores,
          "Cantidad divisores ? " + contD, JOptionPane.PLAIN_MESSAGE);
    }
  }

  public void buscarPrimos(int desde, int hasta) {

    if (desde <= hasta) {

      int suma = 0;
      String cadena = "";
      int cont = 0, cantidad = 0;

      for (int i = desde; i <= hasta; i++) {

        int contGN = 1, contD = 0;

        while (contGN <= i) {

          if (i % contGN == 0) {
            contD++;
          }

          contGN++;
        }

        if (contD == 2) {
          cadena += "[" + i + "]";
          suma += i;
          cont++;
          cantidad++;

          if (cont == 30) {
            cadena += "\n";
            cont = 0;
          }
        }
      }

      JOptionPane.showMessageDialog(null, cadena, "Cantidad ? " + cantidad + " - Suman ? " + suma,
          JOptionPane.PLAIN_MESSAGE);

    } else {
      JOptionPane.showMessageDialog(null,
          "<html>" + styleJOption() + "<strong>Rango ilógico</strong></html>", "¡Verifique!",
          JOptionPane.PLAIN_MESSAGE);
    }
  }

  public void calcularPi(double nsecue) {

    double fra = 0, x = 4, n = 1, pi = 0, cont = 0;

    while (cont < nsecue) {
      fra = x / n;
      pi = pi + fra;
      x = x * (-1);
      n = n + 2;
      cont = cont + 1;
    }
    JOptionPane.showMessageDialog(null, pi + "...", "Resultado", JOptionPane.PLAIN_MESSAGE);
  }

  public void calcularPi2(double nsecue) {

    double fra1 = 0, num = 1, den = 1, pi = 0, resul = 0, pot = 2, i;

    for (i = 1; i <= nsecue; i++) {

      fra1 = num / Math.pow(den, pot);
      resul = resul + fra1;
      den = den + 1;
    }

    pi = Math.sqrt(resul * 6);

    JOptionPane.showMessageDialog(null, pi + "...", "Resultado", JOptionPane.PLAIN_MESSAGE);
  }

  public void generarFibonacci(int lim) {

    String numeros = "";

    double num1 = 0, num2 = 1, f = 0;

    for (int i = 0; i < lim; i++) {

      if (i % 10 == 0 && i != 0 && i != 1 && i != 2) {
        numeros += "\n";
      }

      f = num1 + num2;
      num2 = num1;
      num1 = f;

      numeros += "[" + String.format("%.0f", f) + "]";
    }

    JOptionPane.showMessageDialog(null, numeros, "Resultado", JOptionPane.PLAIN_MESSAGE);

    CalcularPhi(num1, num2);
  }

  public void ObtenerFibonnaci(int n) {

    double fi, numero;

    fi = (1 + Math.sqrt(5)) / 2;

    numero = (1 / Math.sqrt(5)) * (Math.pow(fi, n) - (Math.pow(-1 / fi, n)));

    JOptionPane.showMessageDialog(null,
        "<html>" + styleJOption() + "<strong>N°" + n + " de la serie Fibonacci</strong> ?  "
            + String.format("%.0f", numero) + "</html>", "Resultado", JOptionPane.PLAIN_MESSAGE);
  }

  public void campoVacio() {
    JOptionPane.showMessageDialog(null,
        "<html>" + styleJOption() + "<strong>Digite algún número</strong></html>", "Campo vacío",
        JOptionPane.PLAIN_MESSAGE);
  }

  public void CalcularPhi(double num1, double num2) {
    setPhi(num1 / num2);
  }

  public void proporcionAurea(double longitud) {

    double a, b;

    a = longitud / ((1 + Math.sqrt(5)) / 2);
    b = longitud - a;

    JOptionPane.showMessageDialog(null,
        "<html>" + styleJOption() + "<strong>Porción A: </strong>" + String.format("%.2f", a)
            + "<br>"
            + "<strong>Porción B: </strong>" + String.format("%.2f", b) + "</html>",
        "Resultado", JOptionPane.PLAIN_MESSAGE);
  }

  public void binarioEntero(String num) {

    int numero = Integer.parseInt(num);

    int aux = numero;
    long digito, decimal = 0; // será el equivalente en base decimal
    int exponente = 0;

    while (numero != 0) {
      // se toma la última cifra
      digito = numero % 10;
      // se multiplica por la potencia de 2 correspondiente y se suma al número
      decimal = decimal + digito * (int) Math.pow(2, exponente);
      // se aumenta el exponente
      exponente++;
      // se quita la última cifra para repetir el proceso
      numero = numero / 10;
    }

    JOptionPane.showMessageDialog(null,
        "<html>" + styleJOption() + "<strong>" + aux + " en decimal</strong> ?  " + decimal
            + "</html>",
        "Resultado", JOptionPane.PLAIN_MESSAGE);
  }

  public void enteroBinario(int decimal) {

    int cociente = decimal;// El cociente inicia con el valor del número decimal.

    String binario = " ";// Inicio de la cifra binaria

    while (cociente > 1) {// Repetir mientras el cociente de dividir por 2 sea mayor a 1.

      int digito = cociente % 2;// Obtener el residuo de dividir por 2, éste será el dígito binario.

      cociente = (cociente - digito) / 2;// Obtener el cociente de la división entera por 2

      binario = digito + binario;// Agregar el dígito binario a la cifra
    }

    binario = cociente + binario;// Agregar el último cociente para completar la cifra.

    JOptionPane.showMessageDialog(null,
        "<html>" + styleJOption() + "<strong>" + decimal + " en binario</strong> ? " + binario
            + "</html>", "Resultado",
        JOptionPane.PLAIN_MESSAGE);
  }

  public void invertirCifras(String num) {

    String numeroInvertido = "";
    int longitudNumero = 0;

    longitudNumero = num.length();

    while (longitudNumero != 0) {
      numeroInvertido += num.substring(longitudNumero - 1, longitudNumero);
      longitudNumero--;
    }

    JOptionPane.showMessageDialog(null,
        "<html>" + styleJOption() + "<strong>Número invertido</strong> ? " + numeroInvertido
            + "</html>",
        "Resultado", JOptionPane.PLAIN_MESSAGE);
  }

  public boolean saveGame() {

    boolean save = false;

    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
        "<html>" + styleJOption() + "<strong>¿Desea guardar los datos?</strong></html>", "Save",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.PLAIN_MESSAGE)) {

      save = true;
    }

    return save;
  }

  public String ingreseNickname(String request, int longitud) {

    boolean canContinue = false;
    String aux;

    do {

      aux = JOptionPane.showInputDialog(null,
          "<html>" + styleJOption() + "<strong>" + request + "</strong></html>", "Mensaje",
          JOptionPane.PLAIN_MESSAGE);

      if (aux != null && !aux.trim().equals("") && Pattern.matches("^[a-zA-Z]*$", aux)) {

        if (aux.length() <= longitud) {
          canContinue = true;
        } else {
          JOptionPane.showMessageDialog(null,
              "<html>" + styleJOption() + "<strong>Too large text</strong></html>", "Message",
              JOptionPane.PLAIN_MESSAGE);
        }

      } else {
        JOptionPane.showMessageDialog(null,
            "<html>" + styleJOption() + "<strong>Texto inválido</strong></html>", "Mensaje",
            JOptionPane.PLAIN_MESSAGE);
        canContinue = false;
      }

    } while (!canContinue);

    return aux;
  }

  public String ingreseNumero(String request, int cantidad) {

    boolean canContinue = false;
    String txt;
    int num = 0;

    do {

      txt = JOptionPane.showInputDialog(null,
          "<html>" + styleJOption() + "<strong>" + request + "</strong></html>", "Mensaje",
          JOptionPane.PLAIN_MESSAGE);

      try {
        num = Integer.parseInt(txt);

        if (txt.length() <= cantidad) {
          canContinue = true;
        } else {
          JOptionPane.showMessageDialog(null,
              "<html>" + styleJOption() + "<strong>Too large number</strong></html>", "Message",
              JOptionPane.PLAIN_MESSAGE);
        }

      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null,
            "<html>" + styleJOption() + "<strong>Only numbers less than " + cantidad
                + " digits</strong></html>", "Error",
            JOptionPane.PLAIN_MESSAGE);
        canContinue = false;
      }
    } while (!canContinue);

    return String.valueOf(num);
  }

  public String obtenerDate() {

    Calendar fecha = new GregorianCalendar();
    DateFormat fechaFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
    String date =
        fechaFormat.format(fecha.getTime()) + " - " + fecha.get(Calendar.HOUR_OF_DAY) + ":"
            + fecha.get(Calendar.MINUTE);

    return date;
  }

  public int enteroAleatorio(int min, int max) {
    return (int) Math.floor(Math.random() * (min - max + 1) + max);
  }

  public String styleJOption() {
    return
        "<style type='text/css'>" +
            "	strong {" +
            " 		color:rgb(220, 12, 12);" +
            " 	} " +
            "</style>";
  }

  public void mostrarMensaje(String titulo, String mensaje) {
    JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.PLAIN_MESSAGE);
  }

  public void emptyTable() {
    JOptionPane.showMessageDialog(null,
        "<html>" + styleJOption() + "<u><strong><center>Empty Table</center></strong></u><br>"
            + "La búsqueda realizada no arrojó ningún resultado.</html>",
        "!No se han encontrado registros!", JOptionPane.PLAIN_MESSAGE);
  }

  public String Encriptar(String texto, boolean isEmail) {

    String base64EncryptedString = "";

    try {

      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] digestOfPassword = md.digest(getSecretKey(isEmail).getBytes("utf-8"));
      byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

      SecretKey key = new SecretKeySpec(keyBytes, "DESede");
      Cipher cipher = Cipher.getInstance("DESede");
      cipher.init(Cipher.ENCRYPT_MODE, key);

      byte[] plainTextBytes = texto.getBytes("utf-8");
      byte[] buf = cipher.doFinal(plainTextBytes);
      byte[] base64Bytes = Base64.encodeBase64(buf);
      base64EncryptedString = new String(base64Bytes);

    } catch (Exception ex) {

    }
    return base64EncryptedString;
  }

  public String Desencriptar(String textoEncriptado, boolean isEmail) {

    String base64EncryptedString = "";

    try {
      byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] digestOfPassword = md.digest(getSecretKey(isEmail).getBytes("utf-8"));
      byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
      SecretKey key = new SecretKeySpec(keyBytes, "DESede");

      Cipher decipher = Cipher.getInstance("DESede");
      decipher.init(Cipher.DECRYPT_MODE, key);

      byte[] plainText = decipher.doFinal(message);

      base64EncryptedString = new String(plainText, "UTF-8");

    } catch (Exception ex) {

    }

    if (base64EncryptedString.equals("")) {

      JOptionPane.showMessageDialog(null, "<html>" + styleJOption()
              + "<strong>El texto introducido no tiene contenido encriptado o la llave es incorrecta.</strong></html>",
          "¡Verifique!", JOptionPane.PLAIN_MESSAGE);

      return textoEncriptado;
    } else {
      return base64EncryptedString;
    }
  }

  public String secretKey() {

    boolean canContinue;
    JPasswordField pass = new JPasswordField(10);
    String value;

    do {

      int action = JOptionPane.showConfirmDialog(null, pass, "Enter secret key",
          JOptionPane.DEFAULT_OPTION,
          JOptionPane.PLAIN_MESSAGE);

      value = new String(pass.getPassword());

      if (action >= 0 && value.length() >= 2) {
        canContinue = true;
      } else {
        JOptionPane.showMessageDialog(null,
            "<html>" + styleJOption() + "<strong>Invalid text or length too short.</strong></html>", "Warnings",
            JOptionPane.PLAIN_MESSAGE);
        canContinue = false;
      }

    } while (!canContinue);

    return value;
  }

  public boolean loginDBA() {

    JPasswordField pass = new JPasswordField(10);

    boolean canContinue = true;

    while (canContinue) {

      int action = JOptionPane
          .showConfirmDialog(null, pass, "DBA's Password", JOptionPane.PLAIN_MESSAGE,
              JOptionPane.PLAIN_MESSAGE);

      if (action >= 0) {

        String aux = new String(pass.getPassword());

        if (aux.length() != 0) {

          if (getPasswordDBA().equals(aux)) {
            return true;
          } else {
            JOptionPane.showMessageDialog(null,
                "<html>" + styleJOption() + "<strong>Contraseña incorrecta</strong></html>",
                "Error", JOptionPane.PLAIN_MESSAGE);
            pass.setText("");
          }

        } else {
          canContinue = false;
        }

      } else {
        canContinue = false;
      }
    }

    return false;
  }

  public boolean comprobarConexion(String request, boolean show) {

    String dirWeb = "www.google.com";
    int puerto = 80;
    Socket s = null;

    try {
      s = new Socket(dirWeb, puerto);

      return s.isConnected();

    } catch (Exception e) {

      if (show) {
        JOptionPane.showMessageDialog(null,
            "<html>" + styleJOption() + "<strong>" + request + "</strong></html>",
            "Sin conexión a Internet", JOptionPane.PLAIN_MESSAGE);
      }

    } finally {

      try {

        if (s != null) {
          s.close();
        }

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  public Border bordeAzul(String title) {

    TitledBorder titulo = BorderFactory.createTitledBorder(MEDIO, title);

    titulo.setTitleColor(ROJO);
    titulo.setTitleFont(MEDIUM);
    titulo.setTitleJustification(TitledBorder.CENTER);

    return titulo;
  }

  public String getTitle() {
    return "PackApps";
  }

  public String getSecretKey(boolean isEmail) {

    if (!isEmail) {
      return secretKey();
    } else {
      return "Akatsuki25";
    }
  }

  public String getPasswordDBA() {
    return "admin";
  }

  public void fadeIn(JDialog ventana) {
    Fade.JDialogFadeIn(0f, 1f, 0.2f, 50, ventana);
  }

  public void fadeOut(JDialog ventana) {
    Fade.JDialogFadeOut(1f, 0f, 0.2f, 50, ventana, Fade.DISPOSE);
  }

  public void fadeIn(JFrame ventana) {
    Fade.JFrameFadeIn(0f, 1f, 0.2f, 50, ventana);
  }

  public void fadeOut(JFrame ventana) {
    Fade.JFrameFadeOut(1f, 0f, 0.2f, 50, ventana, Fade.EXIT);
  }

  public double getPhi() {
    return phi;
  }

  public void setPhi(double phi) {
    this.phi = phi;
  }

  public double getTotalPrestamo() {
    return totalPrestamo;
  }

  public void setTotalPrestamo(double totalPrestamo) {
    this.totalPrestamo = totalPrestamo;
  }

  public double getTotalVenta() {
    return totalVenta;
  }

  public void setTotalVenta(double totalVenta) {
    this.totalVenta = totalVenta;
  }

  public int getContProd() {
    return contProd;
  }

  public void setContProd(int contProd) {
    this.contProd = contProd;
  }

  public double getTotalCompra() {
    return totalCompra;
  }

  public void setTotalCompra(double totalCompra) {
    this.totalCompra = totalCompra;
  }

  public boolean cantidadDigitos(String ref) {

    if ((ref.length() < 5 || ref.length() > 15)) {
      JOptionPane.showMessageDialog(null, "Ingrese una referencia válida", "Mensaje",
          JOptionPane.PLAIN_MESSAGE);
      return true;
    }
    return false;
  }

  public boolean valDoc(String doc) {

    if (doc.length() < 8 || doc.length() > 10) {
      JOptionPane.showMessageDialog(null, "Ingrese un documento válido", "Mensaje",
          JOptionPane.PLAIN_MESSAGE);
      return true;
    }
    return false;
  }

  public boolean usuarioPass(String usu) {
    return usu.trim().length() >= 7 && usu.trim().length() <= 20;
  }

  public boolean telefono(String tel) {
    if (tel.length() != 10 && tel.length() != 7) {
      JOptionPane.showMessageDialog(null, "Ingrese un teléfono válido", "Mensaje",
          JOptionPane.PLAIN_MESSAGE);
      return false;
    }
    return true;
  }

  public String buscarProducto() {

    boolean canContinue = false;
    String aux = "";

    do {

      aux = JOptionPane.showInputDialog(null,
          "<html>" + styleJOption() + "<strong>¿Qué referencia busca?</strong></html>", "Mensaje",
          JOptionPane.PLAIN_MESSAGE);

      if (aux == null) {
        aux = "";
      }

      canContinue = true;

    } while (!canContinue);

    return aux;
  }

  public boolean validarValor(double valor) {

    if (valor < 1) {
      JOptionPane
          .showMessageDialog(null, "No se ha ingresado ningún valor ¡hágalo por favor!", "Mensaje",
              JOptionPane.PLAIN_MESSAGE);
      return false;
    }
    return true;
  }

  public String getCodProp() {
    return "admin";
  }

  public String loginUser() {

    JPasswordField pass = new JPasswordField(10);

    JOptionPane.showConfirmDialog(null, pass, "Enter your password", JOptionPane.PLAIN_MESSAGE,
        JOptionPane.PLAIN_MESSAGE);

    return new String(pass.getPassword());
  }

  public String newPassword() {

    JPasswordField pass = new JPasswordField(10);

    boolean canContinue = true;

    while (canContinue) {

      int action = JOptionPane
          .showConfirmDialog(null, pass, "Enter new password", JOptionPane.PLAIN_MESSAGE,
              JOptionPane.PLAIN_MESSAGE);

      if (action >= 0) {

        String aux = new String(pass.getPassword());

        if (!usuarioPass(aux)) {
          JOptionPane.showMessageDialog(null,
              "La contraseña es demasiado débil, inténtalo de nuevo.", "Mensaje",
              JOptionPane.PLAIN_MESSAGE);
        } else {
          canContinue = false;
        }
      }
    }

    return new String(pass.getPassword());
  }

  public void mostrarTotalCompra() {
    JOptionPane.showMessageDialog(null,
        "<html>" + styleJOption() + "<strong><center>Éxito</center></strong><br><strong>" + contProd
            + "</strong> producto(s) comprados con un total de <strong>$"
            + String.format("%.0f", getTotalCompra()) + "</strong></html>",
        "¡Compra realizada!", JOptionPane.PLAIN_MESSAGE);
  }

  public void mostrarTotalVenta() {
    JOptionPane.showMessageDialog(null,
        "<html>" + styleJOption() + "<strong><center>Éxito</center></strong><br><strong>" + contProd
            + "</strong> producto(s) vendidos con un total de <strong>$"
            + String.format("%.0f", getTotalVenta()) + "</strong></html>",
        "¡Venta realizada!", JOptionPane.PLAIN_MESSAGE);
  }

  public void mostrarPrestamo(String plazo,
      String cliente) {// Muestra el préstamo realizado y el plazo a pagar
    JOptionPane.showMessageDialog(null,
        "<html>" + styleJOption() + "<strong><center>Éxito</center></strong><br>Se le prestó $"
            + String.format("%.0f", getTotalPrestamo()) + " a <strong>" + cliente
            + "</strong> el cual tiene hasta <strong>" + plazo + "</strong> para pagar.</html>",
        "¡Préstamo realizado!", JOptionPane.PLAIN_MESSAGE);
  }

  public void productoExiste() {
    JOptionPane.showMessageDialog(null,
        "<html>" + styleJOption() + "<strong><center>Producto existente</center></strong><br>"
            + "La referencia que quieres comprar ya existe en el inventario<br>"
            + "con información diferente, cambia la referencia o busca<br>"
            + "el producto en el inventario para realizar la compra.</html>",
        "!Referencia encontrada!", JOptionPane.PLAIN_MESSAGE);
  }

  public boolean reemplazarArchivo() {

    return JOptionPane.YES_OPTION != JOptionPane.showConfirmDialog(null,
        "<html>" + styleJOption() + "<strong>¿Desea reemplazarlo?</strong></html>",
        "¡El archivo ya existe!",
        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
  }

  public void exception(Exception e) {
    JOptionPane.showMessageDialog(null,
        "<html>" + styleJOption() + "<strong>" + e.getMessage() + "</strong></html>",
        "Algo ha ocurrido",
        JOptionPane.PLAIN_MESSAGE);
  }

  public void instruccionesDados() {
    JOptionPane.showMessageDialog(null,
        "<html>" + styleJOption() + "<strong><center>Juego de Dados</center></strong><br>"
            + "<strong>Jugadores: </strong>3<br><br>"
            + "El juego consta de 5 rondas, cada jugador<br>"
            + "lanzará tres dados para sumar sus puntos<br>"
            + "pero si los saca iguales ganará inmediatamente.<br>"
            + "Si al finalizar el juego, ninguno saca los tres<br>"
            + "dados iguales, ganará quien sume más puntos." + "</html>",
        "Instrucciones", JOptionPane.PLAIN_MESSAGE);
  }

  public void instruccionesRompe() {
    JOptionPane.showMessageDialog(null, "<html>" + styleJOption()
            + "<strong><center>Rompecabezas</center></strong><br>"
            + "<strong>Jugadores: </strong>1<br><br>"
            + "Después de seleccionar la dificultad, el<br>"
            + "tablero iniciará en orden aleatorio, se tendrán<br>"
            + "que ordenar los números de menor a mayor<br>"
            + "en la menor cantidad de intentos posible.<br><br>"
            + "¡Apúrate! habrá un tiempo límite para ganar:<br><br>"
            + "<strong>Level Easy:</strong> 3 min<br>"
            + "<strong>Level Medium:</strong> 6 min<br>" + "<strong>Level Hard:</strong> 10 min<br>"
            + "</html>",
        "Instrucciones", JOptionPane.PLAIN_MESSAGE);
  }

  public void instruccionesRuleta() {

    JOptionPane.showMessageDialog(null,
        "<html>" + styleJOption() + "<strong><center>Ruleta</center></strong><br>"
            + "<strong>Jugadores: </strong>1-9<br><br>"
            + "Al iniciar, se pedirá el número de jugadores<br>"
            + "que participarán. Luego, tendrá que ingresar<br>"
            + "la información de cada jugador (nombre y saldo).<br><br>"
            + "Finalmente, empezarán las apuestas pertinentes<br>"
            + "teniendo en cuenta lo siguiente: de ganar se le<br>"
            + "sumará al saldo el doble del valor de la apuesta y<br>"
            + "<strong>¡apostar por el cero (0) implica perder x2 y ganar x4!</strong><br>"
            + "</html>",
        "Instrucciones", JOptionPane.PLAIN_MESSAGE);
  }

  public void instruccionesAdivinar() {
    JOptionPane.showMessageDialog(null,
        "<html>" + styleJOption() + "<strong><center>Adivinar Número</center></strong><br>"
            + "<strong>Jugadores: </strong>1<br><br>"
            + "Después de seleccionar la dificultad, se pedirá<br>"
            + "el máximo rango a adivinar, éste no puede ser<br>"
            + "mayor de 6 dígitos. Después, simplemente tendrás<br>"
            + "que ir ingresando valores hasta adivinar el número<br>"
            + "secreto en la menor cantidad de intentos." + "</html>",
        "Instrucciones", JOptionPane.PLAIN_MESSAGE);
  }

  public void instruccionesTriqui() {
    JOptionPane.showMessageDialog(null,
        "<html>" + styleJOption() + "<strong><center>Triqui</center></strong><br>"
            + "<strong>Jugadores: </strong>1 o 2<br><br>"
            + "Triqui clásico usuario vs usuario o usuario vs CPU.<br>"
            + "En principio, cada uno tendrá tres movimientos.<br>"
            + "Después de hacerlos, tendrán que empezar a mover<br>"
            + "las piezas ya puestas hasta lograr poner las tres en linea.<br>"
            + "Cuando un jugador llegue a 5 puntos ganará la partida." + "</html>",
        "Instrucciones", JOptionPane.PLAIN_MESSAGE);
  }

  public void instruccionesEstructuras() {
    JOptionPane.showMessageDialog(null,
        "<html>" + styleJOption() + "<strong><center>Estructuras de Datos</center></strong><br>"
            + "Simulación funcional de estructuras tipo <strong>pilas, colas </strong><br> y <strong>matrices </strong>"
            + "con funciones de conteo, suma y promedio,<br>"
            + "para las primeras, y multiplicación, transpuesta<br>"
            + "y determinantes para las opecaciones con matrices.<br>"
            + "Útil para entender el funcionamiento de estas estructuras." + "</html>",
        "Instrucciones", JOptionPane.PLAIN_MESSAGE);
  }

  public void instruccionesAhorcado() {
    JOptionPane.showMessageDialog(null,
        "<html>" + styleJOption() + "<strong><center>Ahorcadito</center></strong><br>"
            + "<strong>Jugadores: </strong>1<br><br>"
            + "El juego empieza cuando se selecciona una<br>"
            + "categoría de palabras para jugar. Tendrás que ir<br>"
            + "digitando letra por letra la palabra secreta,<br>"
            + "¡pero no te tardes! tendrás 60 segundo para ganar." + "</html>",
        "Instrucciones", JOptionPane.PLAIN_MESSAGE);
  }

  public void instruccionesNotas() {
    JOptionPane.showMessageDialog(null,
        "<html>" + styleJOption() + "<strong><center>Notas</center></strong><br>"
            + "<strong>¿No sabes cuánto necesitas para ganar la materia?</strong><br><br>"
            + "¡Con esta aplicación podrás saberlo! suma tus notas<br>"
            + "y entérate si tienes esperanza para pasarla o si ya<br>"
            + "mejor la cancelas... puedes agregar hasta 10 notas.<br>" + "</html>",
        "Instrucciones", JOptionPane.PLAIN_MESSAGE);
  }

  public void miraWe(boolean isUI) {

    if (isUI) {
      JOptionPane.showMessageDialog(null,
          "Ésta UI ya está aplicada, inténtalo con otra.",
          "UI aplicada", JOptionPane.PLAIN_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(null,
          "Ésta imagen ya está aplicada, inténtalo con otra.",
          "Imagen aplicada", JOptionPane.PLAIN_MESSAGE);
    }
  }

  public String elegirRuta(String file) {

    JFileChooser chooser = new JFileChooser();

    switch (file) {

      case ".pdf":
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Documento PDF (.pdf)", "pdf"));
        break;

      case ".xls":
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Libro de Excel (.xls)", "xls"));
        break;

      case ".txt":
        chooser
            .addChoosableFileFilter(new FileNameExtensionFilter("Archivo de Texto (.txt)", "txt"));
        break;
    }

    chooser.setAcceptAllFileFilterUsed(false);

    String ruta = "";
    try {

      if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
        ruta = chooser.getSelectedFile().getAbsolutePath();
      }

    } catch (Exception ex) {
      exception(ex);
    }
    return ruta + file;
  }

  public String validarExist(String file) {

    String rutaArchivo = elegirRuta(file);

    if (!rutaArchivo.equals(file)) {

      if (new File(rutaArchivo).exists()) {

        if (reemplazarArchivo()) {
          rutaArchivo =
              rutaArchivo.substring(0, rutaArchivo.length() - 4) + "(" + (n++) + ")" + file;
        }
      }
    }

    return rutaArchivo;
  }

  public void pdf(JTable jTable, String title, String query, String file) {

    String pdfNewFile = validarExist(file);

    if (!pdfNewFile.equals(file)) {

      if (new Database().readTable(jTable, query, false)) {

        try {

          // Creamos el documento e indicamos el nombre del fichero.
          Document document = new Document();

          try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfNewFile));
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }

          document.open();

          // Añadimos los metadatos del PDF
          document.addTitle(title);
          document.addSubject("Made in PackApps");
          document.addKeywords("Java, PDF, PackApps");
          document.addAuthor("SMONCADA");
          document.addCreator("PackApps");

          // Primera página
          Anchor anchor = new Anchor(title + " - PackApps");
          anchor.setName(title);

          // El segundo parámetro es el número del capítulo).
          Chapter catPart = new Chapter(new Paragraph(anchor), 1);

          Paragraph subPara = new Paragraph();
          Section subCatPart = catPart.addSection(subPara);

          // Creamos la tabla
          PdfPTable table = new PdfPTable(jTable.getColumnCount());

          // Ahora llenamos las filas de PdfPTable
          PdfPCell columnHeader;

          // Rellenamos las cabeceras de las columnas de la tabla.
          for (int column = 0; column < jTable.getColumnCount(); column++) {
            columnHeader = new PdfPCell(new Phrase(jTable.getColumnName(column)));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(columnHeader);
          }
          table.setHeaderRows(1);
          // rellenamos las filas de la tabla
          for (int row = 0; row < jTable.getRowCount(); row++) {
            for (int column = 0; column < jTable.getColumnCount(); column++) {
              table.addCell(jTable.getValueAt(row, column).toString());
            }
          }
          subCatPart.add(table);

          document.add(catPart);

          document.close();

          JOptionPane.showMessageDialog(null,
              "<html>" + styleJOption() + "<strong><center>Exportación</center></strong><br>"
                  + "Se ha exportado exitosamente<br>" + "<strong>Guardado en: </strong>"
                  + pdfNewFile + "</html>",
              "¡Éxito!", JOptionPane.PLAIN_MESSAGE);

        } catch (DocumentException documentException) {
          JOptionPane.showMessageDialog(null,
              "<html>" + styleJOption() + "<strong><center>Exportación</center></strong><br><br>"
                  + "Ha ocurrido un error al exportar</html>",
              "¡Error!", JOptionPane.PLAIN_MESSAGE);
        }
      }
    }
  }

  public void excel(JTable jTable1, String query, String file) throws IOException {

    String rutaArchivo = validarExist(file);

    if (!rutaArchivo.equals(file)) {

      if (new Database().readTable(jTable1, query, false)) {

        int cantFila = jTable1.getRowCount();
        int cantColumna = jTable1.getColumnCount();
        Workbook wb;
        wb = new HSSFWorkbook();
        Sheet hoja = wb.createSheet(" ");

        // títulos de columnas
        for (int i = 0; i < 2; i++) {
          Row fila = hoja.createRow(i);
          for (int j = 0; j < cantColumna; j++) {
            Cell celda = fila.createCell(j);

            celda.setCellValue(String.valueOf(jTable1.getColumnName(j)));
            wb.write(new FileOutputStream(rutaArchivo));
          }
        }

        // registros
        for (int i = 0; i < cantFila; i++) {
          Row fila = hoja.createRow(i + 1);
          for (int j = 0; j < cantColumna; j++) {
            Cell celda = fila.createCell(j);

            celda.setCellValue(String.valueOf(jTable1.getValueAt(i, j)));
            wb.write(new FileOutputStream(rutaArchivo));
          }
        }

        JOptionPane.showMessageDialog(null,
            "<html>" + styleJOption() + "<strong><center>Exportación</center></strong><br>"
                + "Se ha exportado exitosamente<br>" + "<strong>Guardado en: </strong>"
                + rutaArchivo
                + "</html>",
            "¡Éxito!", JOptionPane.PLAIN_MESSAGE);
      }
    }
  }

  public void txt(JTable table, String query, String file) {

    String rutaArchivo = validarExist(file);

    if (!rutaArchivo.equals(file)) {

      if (new Database().readTable(table, query, false)) {

        try {

          BufferedWriter bfw = new BufferedWriter(new FileWriter(rutaArchivo));

          bfw.write("{");
          for (int k = 0; k < table.getColumnCount(); k++) {
            bfw.write(table.getColumnName(k));

            if (k < table.getColumnCount()
                - 1) { // agrega separador "," si no es el ultimo elemento de la
              // fila.
              bfw.write(", ");
            }
          }
          bfw.write("} ? Columnas");

          bfw.newLine();
          bfw.newLine();

          for (int i = 0; i < table.getRowCount(); i++) { // realiza un barrido por filas.

            bfw.write("[");

            for (int j = 0; j < table.getColumnCount(); j++) { // realiza un barrido por columnas.

              bfw.write(String.valueOf((table.getValueAt(i, j))));

              if (j < table.getColumnCount()
                  - 1) { // agrega separador "," si no es el ultimo elemento de
                // la
                // fila.
                bfw.write(", ");
              }
            }
            bfw.write("]");
            bfw.newLine(); // inserta nueva linea.
          }

          bfw.close(); // cierra archivo!

          JOptionPane.showMessageDialog(null,
              "<html>" + styleJOption() + "<strong><center>Exportación</center></strong><br>"
                  + "Se ha exportado exitosamente<br>" + "<strong>Guardado en: </strong>"
                  + rutaArchivo + "</html>",
              "¡Éxito!", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {

          JOptionPane.showMessageDialog(null,
              "<html>" + styleJOption() + "<strong><center>Exportación</center></strong><br><br>"
                  + "Ha ocurrido un error al exportar</html>",
              "¡Error!", JOptionPane.PLAIN_MESSAGE);
        }
      }
    }
  }
}