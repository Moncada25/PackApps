package com.bookverse.development.packapps.core;

import static com.bookverse.development.packapps.utils.AppConstants.PASSWORD_DBA;

import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.Format;
import com.bookverse.development.packapps.utils.WindowEffect;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import org.apache.commons.codec.binary.Base64;
import org.jetbrains.annotations.NotNull;

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

public class AppConfig {

  public static final Color TEXT_COLOR = new Color(21, 87, 163);
  public static final Color MAIN_COLOR = new Color(220, 12, 12);
  public static final Border BORDER_BLUE = BorderFactory.createLineBorder(TEXT_COLOR, 2, true);
  public static final Border BORDER_RED = BorderFactory.createLineBorder(MAIN_COLOR, 2, true);
  public static final Cursor POINT = new Cursor(Cursor.CROSSHAIR_CURSOR);
  public static final Cursor LOADER = new Cursor(Cursor.WAIT_CURSOR);
  public static final Cursor RESIZE = new Cursor(Cursor.NE_RESIZE_CURSOR);
  public static final Cursor TEXT = new Cursor(Cursor.TEXT_CURSOR);
  public static final Cursor HAND = new Cursor(Cursor.HAND_CURSOR);
  public static final Font SMALL = new Font("Times New Roman", Font.ITALIC, 15);
  public static final Font MEDIUM = new Font("Segoe Print", Font.HANGING_BASELINE, 16);
  public static final Font BIG = new Font("Segoe Print", Font.ITALIC, 24);

  public static String inputText(String request, int length) {

    boolean canContinue = false;
    String text;

    do {

      text = Alerts.inputText(request);

      if (text != null && !text.trim().equals("") && Pattern.matches("^[a-zA-Z]*$", text)) {

        if (text.length() <= length) {
          canContinue = true;
        } else {
          Alerts.inputLarge();
        }

      } else {
        Alerts.invalidInput();
      }

    } while (!canContinue);

    return text;
  }

  @NotNull
  public static String inputNumber(String request, int length) {

    boolean canContinue = false;
    String text;
    int num = 0;

    do {

      text = Alerts.inputText(request);

      try {
        num = Integer.parseInt(text);

        if (text.length() <= length) {
          canContinue = true;
        } else {
          Alerts.inputLarge();
        }

      } catch (NumberFormatException e) {
        Alerts.onlyNumbers(length);
      }
    } while (!canContinue);

    return String.valueOf(num);
  }

  public static boolean saveGame() {

    boolean save = false;

    if (Alerts.requestResponse("You want to save the data?", "Save")) {
      save = true;
    }

    return save;
  }

  @NotNull
  public static String getDate() {

    Calendar date = new GregorianCalendar();
    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

    return dateFormat.format(date.getTime()) + " - " + date.get(Calendar.HOUR_OF_DAY) + ":"
        + date.get(Calendar.MINUTE);
  }

  public static int intRandom(int min, int max) {
    return (int) Math.floor(Math.random() * (min - max + 1) + max);
  }

  public static String encrypt(String text, boolean isEmail) {

    String base64EncryptedString = "";

    try {

      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] digestOfPassword = md.digest(getSecretKey(isEmail).getBytes(StandardCharsets.UTF_8));
      byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

      SecretKey key = new SecretKeySpec(keyBytes, "DESede");
      Cipher cipher = Cipher.getInstance("DESede");
      cipher.init(Cipher.ENCRYPT_MODE, key);

      byte[] plainTextBytes = text.getBytes(StandardCharsets.UTF_8);
      byte[] buf = cipher.doFinal(plainTextBytes);
      byte[] base64Bytes = Base64.encodeBase64(buf);
      base64EncryptedString = new String(base64Bytes);

    } catch (Exception ex) {

    }
    return base64EncryptedString;
  }

  public static String decrypt(String text, boolean isEmail) {

    String base64EncryptedString = "";

    try {
      byte[] message = Base64.decodeBase64(text.getBytes(StandardCharsets.UTF_8));
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] digestOfPassword = md.digest(getSecretKey(isEmail).getBytes(StandardCharsets.UTF_8));
      byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
      SecretKey key = new SecretKeySpec(keyBytes, "DESede");

      Cipher decipher = Cipher.getInstance("DESede");
      decipher.init(Cipher.DECRYPT_MODE, key);

      byte[] plainText = decipher.doFinal(message);

      base64EncryptedString = new String(plainText, StandardCharsets.UTF_8);

    } catch (Exception ex) {

    }

    if (base64EncryptedString.equals("")) {

      JOptionPane.showMessageDialog(null, "<html>" + Format.style()
              + "<strong>El texto introducido no tiene contenido encriptado o la llave es incorrecta.</strong></html>",
          "¡Verifique!", JOptionPane.PLAIN_MESSAGE);

      return text;
    } else {
      return base64EncryptedString;
    }
  }

  public static String secretKey() {

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
            "<html>" + Format.style()
                + "<strong>Invalid text or length too short.</strong></html>",
            "Warnings",
            JOptionPane.PLAIN_MESSAGE);
        canContinue = false;
      }

    } while (!canContinue);

    return value;
  }

  public static boolean loginDBA() {

    JPasswordField pass = new JPasswordField(10);

    boolean canContinue = true;

    while (canContinue) {

      int action = JOptionPane
          .showConfirmDialog(null, pass, "DBA's Password", JOptionPane.PLAIN_MESSAGE,
              JOptionPane.PLAIN_MESSAGE);

      if (action >= 0) {

        String aux = new String(pass.getPassword());

        if (aux.length() != 0) {

          if (PASSWORD_DBA.equals(aux)) {
            return true;
          } else {
            JOptionPane.showMessageDialog(null,
                "<html>" + Format.style() + "<strong>Contraseña incorrecta</strong></html>",
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

  public static boolean verifyConnection(String request, boolean show) {

    String dirWeb = "www.google.com";
    int puerto = 80;
    Socket s = null;

    try {
      s = new Socket(dirWeb, puerto);

      return s.isConnected();

    } catch (Exception e) {

      if (show) {
        JOptionPane.showMessageDialog(null,
            "<html>" + Format.style() + "<strong>" + request + "</strong></html>",
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

  @NotNull
  public static Border getBorder(String tittle) {

    TitledBorder border = BorderFactory.createTitledBorder(BORDER_BLUE, tittle);

    border.setTitleColor(MAIN_COLOR);
    border.setTitleFont(MEDIUM);
    border.setTitleJustification(TitledBorder.CENTER);

    return border;
  }

  public static String getSecretKey(boolean isEmail) {

    if (!isEmail) {
      return secretKey();
    } else {
      return "Akatsuki25";
    }
  }

  public static void fadeIn(JDialog window) {
    WindowEffect.JDialogFadeIn(0f, 1f, 0.2f, 50, window);
  }

  public static void fadeIn(JFrame window) {
    WindowEffect.JFrameFadeIn(0f, 1f, 0.2f, 50, window);
  }

  public static void fadeOut(JFrame window) {
    WindowEffect.JFrameFadeOut(1f, 0f, 0.2f, 50, window, WindowEffect.EXIT);
  }

  public static void fadeOut(JDialog ventana) {
    WindowEffect.JDialogFadeOut(1f, 0f, 0.2f, 50, ventana, WindowEffect.DISPOSE);
  }

  //INSTRUCCIONES

  public static void instruccionesRuleta() {

    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style() + "<strong><center>Ruleta</center></strong><br>"
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


  public static void instruccionesDados() {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style() + "<strong><center>Juego de Dados</center></strong><br>"
            + "<strong>Jugadores: </strong>3<br><br>"
            + "El juego consta de 5 rondas, cada jugador<br>"
            + "lanzará tres dados para sumar sus puntos<br>"
            + "pero si los saca iguales ganará inmediatamente.<br>"
            + "Si al finalizar el juego, ninguno saca los tres<br>"
            + "dados iguales, ganará quien sume más puntos." + "</html>",
        "Instrucciones", JOptionPane.PLAIN_MESSAGE);
  }

  public static void instruccionesRompe() {
    JOptionPane.showMessageDialog(null, "<html>" + Format.style()
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

  public static void instruccionesAdivinar() {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style() + "<strong><center>Adivinar Número</center></strong><br>"
            + "<strong>Jugadores: </strong>1<br><br>"
            + "Después de seleccionar la dificultad, se pedirá<br>"
            + "el máximo rango a adivinar, éste no puede ser<br>"
            + "mayor de 6 dígitos. Después, simplemente tendrás<br>"
            + "que ir ingresando valores hasta adivinar el número<br>"
            + "secreto en la menor cantidad de intentos." + "</html>",
        "Instrucciones", JOptionPane.PLAIN_MESSAGE);
  }

  public static void instruccionesTriqui() {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style() + "<strong><center>Triqui</center></strong><br>"
            + "<strong>Jugadores: </strong>1 o 2<br><br>"
            + "Triqui clásico usuario vs usuario o usuario vs CPU.<br>"
            + "En principio, cada uno tendrá tres movimientos.<br>"
            + "Después de hacerlos, tendrán que empezar a mover<br>"
            + "las piezas ya puestas hasta lograr poner las tres en linea.<br>"
            + "Cuando un jugador llegue a 5 puntos ganará la partida." + "</html>",
        "Instrucciones", JOptionPane.PLAIN_MESSAGE);
  }

  public static void instruccionesEstructuras() {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style()
            + "<strong><center>Estructuras de Datos</center></strong><br>"
            + "Simulación funcional de estructuras tipo <strong>pilas, colas </strong><br> y <strong>matrices </strong>"
            + "con funciones de conteo, suma y promedio,<br>"
            + "para las primeras, y multiplicación, transpuesta<br>"
            + "y determinantes para las opecaciones con matrices.<br>"
            + "Útil para entender el funcionamiento de estas estructuras." + "</html>",
        "Instrucciones", JOptionPane.PLAIN_MESSAGE);
  }

  public static void instruccionesAhorcado() {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style() + "<strong><center>Ahorcadito</center></strong><br>"
            + "<strong>Jugadores: </strong>1<br><br>"
            + "El juego empieza cuando se selecciona una<br>"
            + "categoría de palabras para jugar. Tendrás que ir<br>"
            + "digitando letra por letra la palabra secreta,<br>"
            + "¡pero no te tardes! tendrás 60 segundo para ganar." + "</html>",
        "Instrucciones", JOptionPane.PLAIN_MESSAGE);
  }

  public static void instruccionesNotas() {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style() + "<strong><center>Notas</center></strong><br>"
            + "<strong>¿No sabes cuánto necesitas para ganar la materia?</strong><br><br>"
            + "¡Con esta aplicación podrás saberlo! suma tus notas<br>"
            + "y entérate si tienes esperanza para pasarla o si ya<br>"
            + "mejor la cancelas... puedes agregar hasta 10 notas.<br>" + "</html>",
        "Instrucciones", JOptionPane.PLAIN_MESSAGE);
  }
}