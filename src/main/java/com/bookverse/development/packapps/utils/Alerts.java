package com.bookverse.development.packapps.utils;

import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import org.jetbrains.annotations.NotNull;

public class Alerts {

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

    if (Alerts.requestResponse("Do you want to save the data?", "Save")) {
      save = true;
    }

    return save;
  }

  public static void error(@NotNull Exception exception, String parent) {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style() + "<strong>" + exception.getMessage()
            + "</strong></html>",
        "Error from " + parent, JOptionPane.ERROR_MESSAGE);
  }

  public static void message(String tittle, String message) {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style() + "<strong>" + message,
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
            + "<strong><center>Changes saved</center></strong><br>"
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
        "<html>" + Format.style() + "<strong><center>Empty fields</center></strong><br>"
            + "Some of the fields are empty, please enter a value." + "</html>",
        "Verify!", JOptionPane.PLAIN_MESSAGE);
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

  public static String inputPassword(String request) {
    JPasswordField password = new JPasswordField(10);
    JOptionPane.showConfirmDialog(null, password, request, JOptionPane.PLAIN_MESSAGE,
        JOptionPane.PLAIN_MESSAGE);

    return new String(password.getPassword());
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

  public static boolean requestResponse(String request, String tittle) {
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

  public static void actionSuccessfully(String action, String quantityOfProducts,
      double totalPurchases) {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style() + "<strong><center>Done!</center></strong><br><strong>"
            + quantityOfProducts + "</strong> products " + action + " with  a total of <strong>$"
            + String.format("%.0f", totalPurchases) + "</strong></html>",
        "Success", JOptionPane.PLAIN_MESSAGE);
  }

  public static void lendSuccessfully(String time, double totalPurchases) {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style() + "<strong><center>Done!</center></strong><br>"
            + "Loan made for <strong>" + String.format("%.0f", totalPurchases)
            + "</strong>, there is a term to pay until <strong>" + time + "</strong></html>",
        "Success", JOptionPane.PLAIN_MESSAGE);
  }

  public static void fieldMailRequired() {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style() + "<strong><center>Secure credentials</center></strong><br>"
            + "The use of this medium is authorized by Google through<br>"
            + "the use of its JavaMail API, your data is protected!</html>",
        "Required field!", JOptionPane.PLAIN_MESSAGE);
  }

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

  public static void instruccionesPreguntas() {
    JOptionPane.showMessageDialog(null,
        "<html>" + Format.style() + "<strong><center>Quién quiere ser millonario</center></strong><br>"
            + "<strong>Jugadores: </strong>1<br><br>"
            + "El juego empieza cuando se selecciona una categoría<br>"
            + "de preguntas para jugar. Tendrás que adivinar<br>"
            + "la respuesta correcta para avanzar de nivel,<br>"
            + "</html>",
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