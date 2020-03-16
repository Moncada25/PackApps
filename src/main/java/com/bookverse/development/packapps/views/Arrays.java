package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.AppConfig.BIG;
import static com.bookverse.development.packapps.core.AppConfig.MAIN_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.MEDIUM;
import static com.bookverse.development.packapps.core.AppConfig.SMALL;
import static com.bookverse.development.packapps.core.AppConfig.TEXT_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.inputNumber;
import static com.bookverse.development.packapps.core.AppConfig.intRandom;

import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.Format;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Arrays extends JDialog implements ActionListener {

  private JButton btnDeterminant, btnAction, btnAuto, btnClean, btnTransposed, btnDiagonals, btnMultiply;
  private JTextField txtRows, txtColumns;
  private Resources resources = new Resources();
  private JButton[][] arrayBoard;
  private boolean isWork = false;

  public Arrays(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {

    setLayout(null);

    arrayBoard = new JButton[9][9];
    int x = 250;
    int y = 80;

    for (int f = 0; f < arrayBoard.length; f++) {
      for (int c = 0; c < arrayBoard.length; c++) {
        arrayBoard[f][c] = resources.getButton("", null, this, this);
        arrayBoard[f][c].setBounds(x, y, 45, 45);
        arrayBoard[f][c].setVisible(false);
        x = x + 45;
      }

      x = 250;
      y = y + 45;
    }

    JLabel welcome = resources
        .getLabel("<html><em><strong>Matrix operations</strong></em></html>", MAIN_COLOR, this,
            BIG);
    welcome.setBounds(280, 10, 500, 40);

    btnAction = resources.getButton("Show", TEXT_COLOR, this, this);
    btnAction.setBounds(20, 190, 70, 25);

    btnAuto = resources.getButton("Auto fill", TEXT_COLOR, this, this);
    btnAuto.setBounds(120, 100, 90, 25);
    btnAuto.setEnabled(false);

    btnClean = resources.getButton("Clean", MAIN_COLOR, this, this);
    btnClean.setBounds(120, 150, 90, 25);
    btnClean.setEnabled(false);

    btnTransposed = resources.getButton("Transpose", TEXT_COLOR, this, this);
    btnTransposed.setBounds(720, 100, 120, 25);
    btnTransposed.setEnabled(false);

    btnMultiply = resources.getButton("Multiply", TEXT_COLOR, this, this);
    btnMultiply.setBounds(720, 200, 120, 25);
    btnMultiply.setEnabled(false);

    btnDeterminant = resources.getButton("|A|", TEXT_COLOR, this, this);
    btnDeterminant.setBounds(720, 250, 120, 25);
    btnDeterminant.setEnabled(false);

    btnDiagonals = resources.getButton("Diagonals", TEXT_COLOR, this, this);
    btnDiagonals.setBounds(720, 150, 120, 25);
    btnDiagonals.setEnabled(false);

    JLabel rows = resources
        .getLabel("<html><em>Rows</em></html>", TEXT_COLOR, this,
            SMALL);
    rows.setBounds(40, 80, 100, 25);

    txtRows = new JTextField();
    txtRows.setBounds(20, 100, 70, 25);
    txtRows.setHorizontalAlignment(JTextField.CENTER);
    add(txtRows);

    txtRows.addKeyListener(new KeyAdapter() {

      public void keyTyped(KeyEvent e) {
        txtNumKeyTyped(e);
      }

      public void txtNumKeyTyped(KeyEvent e) {
        Format.onlyNumbers(e.getKeyChar(), e, txtRows.getText(), 1);
      }
    });

    JLabel columns = resources.getLabel("<html><em>Columns</em></html>", TEXT_COLOR, this,
        SMALL);
    columns.setBounds(25, 130, 100, 25);

    txtColumns = new JTextField();
    txtColumns.setBounds(20, 150, 70, 25);
    txtColumns.setHorizontalAlignment(JTextField.CENTER);
    add(txtColumns);

    txtColumns.addKeyListener(new KeyAdapter() {

      public void keyTyped(KeyEvent e) {
        txtNumKeyTyped(e);
      }

      public void keyPressed(KeyEvent e) {
        txtNumKeyPressed(e);
      }

      public void txtNumKeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          generarMatrizAP();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      public void txtNumKeyTyped(KeyEvent e) {
        Format.onlyNumbers(e.getKeyChar(), e, txtColumns.getText(), 1);
      }
    });
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnAction) {
      generarMatrizAP();
    } else if (e.getSource() == btnAuto) {
      autoFillAP(arrayBoard, Integer.parseInt(txtRows.getText()),
          Integer.parseInt(txtColumns.getText()));
    } else if (e.getSource() == btnClean) {
      emptyAP();
    } else if (e.getSource() == btnTransposed) {
      transpuestaAP();
    } else if (e.getSource() == btnDiagonals) {
      diagonalesAP();
    } else if (e.getSource() == btnMultiply) {
      multiplicarAP();
    } else if (e.getSource() == btnDeterminant) {
      determinantAP();
    }

    if (btnAction.getText().equals("New")) {
      manualFill(e, arrayBoard, Integer.parseInt(txtRows.getText()),
          Integer.parseInt(txtColumns.getText()));
    }
  }

  private void determinantAP() {

    if (entradasFull(arrayBoard, Integer.parseInt(txtRows.getText()),
        Integer.parseInt(txtColumns.getText()))) {

      int f = Integer.parseInt(txtRows.getText());
      int c = Integer.parseInt(txtColumns.getText());

      if (esCuadrada(f, c)) {
        Alerts.message("Determinant of the matrix",
            "|A| = " + Determinante.getDeterminante(arrayBoard, f, c));
      }
    }
  }

  private void multiplicarAP() {

    if (entradasFull(arrayBoard, Integer.parseInt(txtRows.getText()),
        Integer.parseInt(txtColumns.getText()))) {

      for (int i = 0; i < Integer.parseInt(txtRows.getText()); i++) {
        for (int j = 0; j < Integer.parseInt(txtColumns.getText()); j++) {
          arrayBoard[i][j].setBackground(getBackground());
        }
      }

      Object opcion = null;

      opcion = JOptionPane.showInputDialog(null,
          "<html>" + Format.style()
              + "<strong><em>¿Con qué desea multiplicar la matriz?</em></strong></html>",
          "Producto de Matrices", JOptionPane.PLAIN_MESSAGE, null,
          new Object[]{"Un escalar", "Un vector", "Una matriz"}, "Un escalar");

      if (opcion != null) {

        switch (opcion.toString()) {

          case "Un escalar":

            int escalar = Integer.parseInt(
                inputNumber("Ingrese el escalar por el cual desea multiplicar la matriz", 2));

            for (int i = 0; i < Integer.parseInt(txtRows.getText()); i++) {
              for (int j = 0; j < Integer.parseInt(txtColumns.getText()); j++) {

                if (arrayBoard[i][j].getText().length() == 1) {
                  arrayBoard[i][j]
                      .setText(
                          String.valueOf(Integer.parseInt(arrayBoard[i][j].getText()) * escalar));
                  arrayBoard[i][j].setFont(MEDIUM);
                } else if (arrayBoard[i][j].getText().length() == 2) {
                  arrayBoard[i][j]
                      .setText(
                          String.valueOf(Integer.parseInt(arrayBoard[i][j].getText()) * escalar));
                  arrayBoard[i][j].setFont(SMALL);
                }
              }
            }

            break;

          case "Un vector":

            int fv = Integer.parseInt(txtRows.getText());
            int cv = Integer.parseInt(txtColumns.getText());

            double[][] matrizO = new double[fv][cv];
            double[][] vectorB = getMatrizB(cv, 1);

            for (int i = 0; i < fv; i++) {
              for (int j = 0; j < cv; j++) {
                matrizO[i][j] = Double.parseDouble(arrayBoard[i][j].getText());
              }
            }

            showMatrizC(matrizxmatriz(matrizO, vectorB), fv, 1);

            break;

          case "Una matriz":

            int columnasB = Integer
                .parseInt(inputNumber("Ingrese el número de columnas que tendrá la matriz B", 1));

            int f = Integer.parseInt(txtRows.getText());
            int c = Integer.parseInt(txtColumns.getText());

            double[][] matrizA = new double[f][c];
            double[][] matrizB = getMatrizB(c, columnasB);

            for (int i = 0; i < f; i++) {
              for (int j = 0; j < c; j++) {
                matrizA[i][j] = Double.parseDouble(arrayBoard[i][j].getText());
              }
            }

            showMatrizC(matrizxmatriz(matrizA, matrizB), f, columnasB);

            break;

          default:
            JOptionPane
                .showMessageDialog(null, "Opción inválida.", "Error", JOptionPane.PLAIN_MESSAGE);
        }
      }
    }
  }

  private void showMatrizC(int[][] matrizC, int filas, int columnas) {

    JDialog result = new JDialog(this, true);
    result.setLayout(null);

    JButton[][] matrizResult = new JButton[filas][columnas];

    int x = 30;
    int y = 30;

    for (int f = 0; f < filas; f++) {
      for (int c = 0; c < columnas; c++) {
        matrizResult[f][c] = new JButton();
        matrizResult[f][c].setBounds(x, y, 45, 45);
        matrizResult[f][c].setText(String.valueOf(matrizC[f][c]));
        matrizResult[f][c].setFont(SMALL);
        result.add(matrizResult[f][c]);
        x = x + 45;
      }

      x = 30;
      y = y + 45;
    }

    result.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    result.setSize(480, 480);
    result.setTitle("Resultado de multiplicar A x B");
    result.setResizable(false);
    result.setLocationRelativeTo(null);
    result.setVisible(true);
  }

  private void diagonalesAP() {

    if (entradasFull(arrayBoard, Integer.parseInt(txtRows.getText()),
        Integer.parseInt(txtColumns.getText()))) {

      int f = Integer.parseInt(txtRows.getText());
      int c = Integer.parseInt(txtColumns.getText());

      if (esCuadrada(f, c)) {

        String principal = "";
        String secundaria = "";
        int sumaSuperior = 0;
        int sumaInferior = 0;

        for (int i = 0; i < f; i++) {
          for (int j = 0; j < c; j++) {

            if (i == j) {
              principal += "[" + arrayBoard[i][j].getText() + "]";
              arrayBoard[i][j].setBackground(MAIN_COLOR);
            }

            if (i + j == f - 1) {
              secundaria += "[" + arrayBoard[i][j].getText() + "]";
              arrayBoard[i][j].setBackground(TEXT_COLOR);
            }
          }
        }

        for (int i = 0; i < f; i++) {
          for (int j = i; j < c; j++) {

            if (i != j) {
              sumaSuperior += Integer.parseInt(arrayBoard[i][j].getText());
            }
          }
        }

        for (int i = 1; i < f; i++) {
          for (int j = 0; j < c; j++) {
            if (j < i) {
              sumaInferior += Integer.parseInt(arrayBoard[i][j].getText());
            }
          }
        }

        JOptionPane.showMessageDialog(null,
            "<html>" + Format.style() + "<strong><center>Resultado</center></strong><br>"
                + "Diagonal principal ? " + principal + "<br>" + "Suma del triángulo superior ? "
                + sumaSuperior + "<br><br>" + "Diagonal secundaria ? " + secundaria + "<br>"
                + "Suma del triángulo inferior ? " + sumaInferior + "</html>",
            "Diagonales de la Matriz", JOptionPane.PLAIN_MESSAGE);
      }
    }
  }

  private boolean esCuadrada(int f, int c) {

    if (f != c) {

      JOptionPane
          .showMessageDialog(null,
              "<html>" + Format.style() + "<strong><center>Matriz inválida</center></strong><br>"
                  + "La matriz actual no es cuadrada." + "</html>",
              "¡Verifique!", JOptionPane.PLAIN_MESSAGE);

      return false;
    }

    return true;
  }

  private void manualFill(ActionEvent e, JButton[][] matriz, int f, int c) {

    for (int i = 0; i < f; i++) {
      for (int j = 0; j < c; j++) {

        if (e.getSource() == matriz[i][j]) {
          matriz[i][j].setText(
              inputNumber("Ingrese el valor de la posición [" + (i + 1) + "][" + (j + 1) + "]", 2));
        }
      }
    }
  }

  public int[][] matrizxmatriz(double[][] a, double[][] b) {
    int[][] c = new int[a.length][b[0].length];

    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < b[0].length; j++) {
        for (int k = 0; k < a[0].length; k++) {
          // aquí se multiplica la matriz
          c[i][j] += a[i][k] * b[k][j];
        }
      }
    }

    return c;
  }

  public double[][] getMatrizB(int filas, int columnas) {

    JDialog result = new JDialog(this, true);
    result.setLayout(null);

    JButton[][] matrizResult = new JButton[filas][columnas];
    double[][] matrizB = new double[filas][columnas];

    int x = 30;
    int y = 30;

    for (int f = 0; f < filas; f++) {
      for (int c = 0; c < columnas; c++) {
        matrizResult[f][c] = new JButton();
        matrizResult[f][c].setBounds(x, y, 45, 45);

        matrizResult[f][c].addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            manualFill(e, matrizResult, filas, columnas);
          }
        });

        matrizResult[f][c].setFont(MEDIUM);
        result.add(matrizResult[f][c]);
        x = x + 45;
      }

      x = 30;
      y = y + 45;
    }

    JButton btnsend = new JButton("Finished");
    btnsend.setBackground(MAIN_COLOR);
    btnsend.setBounds(250, 455, 120, 25);
    result.add(btnsend);
    btnsend.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        if (entradasFull(matrizResult, filas, columnas)) {

          for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
              matrizB[i][j] = Double.parseDouble(matrizResult[i][j].getText());
            }
          }

          result.dispose();
        }
      }
    });

    JButton btnauto = new JButton("Autofill");
    btnauto.setBackground(TEXT_COLOR);
    btnauto.setBounds(100, 455, 120, 25);
    result.add(btnauto);
    btnauto.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        autoFillAP(matrizResult, filas, columnas);
      }
    });

    result.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    result.setSize(480, 530);
    result.setTitle("Llenar B");
    result.setResizable(false);
    result.setLocationRelativeTo(null);
    result.setVisible(true);

    return matrizB;
  }

  private void transpuestaAP() {

    if (entradasFull(arrayBoard, Integer.parseInt(txtRows.getText()),
        Integer.parseInt(txtColumns.getText()))) {

      for (int i = 0; i < Integer.parseInt(txtRows.getText()); i++) {
        for (int j = 0; j < Integer.parseInt(txtColumns.getText()); j++) {
          arrayBoard[i][j].setBackground(getBackground());
        }
      }

      JDialog result = new JDialog(this, true);
      result.setLayout(null);

      int fi = Integer.parseInt(txtColumns.getText());
      int ci = Integer.parseInt(txtRows.getText());

      JButton[][] matrizResult = new JButton[fi][ci];

      int x = 30;
      int y = 30;

      for (int f = 0; f < fi; f++) {
        for (int c = 0; c < ci; c++) {
          matrizResult[f][c] = new JButton();
          matrizResult[f][c].setBounds(x, y, 45, 45);
          matrizResult[f][c].setFont(MEDIUM);
          matrizResult[f][c].setText(arrayBoard[c][f].getText());
          result.add(matrizResult[f][c]);
          x = x + 45;
        }

        x = 30;
        y = y + 45;
      }

      result.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      result.setSize(480, 480);
      result.setTitle("Resultado de Matriz Transpuesta");
      result.setResizable(false);
      result.setLocationRelativeTo(null);
      result.setVisible(true);
    }
  }

  private boolean entradasFull(JButton[][] matriz, int f, int c) {

    for (int i = 0; i < f; i++) {
      for (int j = 0; j < c; j++) {
        if (matriz[i][j].getText().equals("")) {

          JOptionPane.showMessageDialog(null,
              "<html>" + Format.style()
                  + "<strong><center>Entradas indefinidas</center></strong><br>"
                  + "Alguna de las entradas está indefinida, por favor introduzca un valor."
                  + "</html>",
              "¡Verifique!", JOptionPane.PLAIN_MESSAGE);

          return false;
        }
      }
    }

    return true;
  }

  private void emptyAP() {

    for (int i = 0; i < Integer.parseInt(txtRows.getText()); i++) {
      for (int j = 0; j < Integer.parseInt(txtColumns.getText()); j++) {
        arrayBoard[i][j].setText("");
        arrayBoard[i][j].setBackground(getBackground());
        arrayBoard[i][j].setFont(MEDIUM);
      }
    }
  }

  private void autoFillAP(JButton[][] matriz, int f, int c) {

    for (int i = 0; i < f; i++) {
      for (int j = 0; j < c; j++) {
        matriz[i][j].setText(String.valueOf(intRandom(0, 9)));
        matriz[i][j].setBackground(getBackground());
        matriz[i][j].setFont(MEDIUM);
      }
    }
  }

  private void generarMatrizAP() {

    if (btnAction.getText().equals("Show")) {

      if (txtRows.getText().equals("") || txtColumns.getText().equals("")) {
        JOptionPane.showMessageDialog(null,
            "<html>" + Format.style() + "<strong><center>Campos vacíos</center></strong><br>"
                + "Alguno de los campos está vacío, por favor introduzca un valor." + "</html>",
            "¡Verifique!", JOptionPane.PLAIN_MESSAGE);

        isWork = false;

      } else {

        isWork = true;

        deleteAll();

        for (int i = 0; i < Integer.parseInt(txtRows.getText()); i++) {
          for (int j = 0; j < Integer.parseInt(txtColumns.getText()); j++) {
            arrayBoard[i][j].setVisible(true);
          }
        }

        btnAuto.setEnabled(true);
        btnDeterminant.setEnabled(true);
        btnClean.setEnabled(true);
        btnTransposed.setEnabled(true);
        btnDiagonals.setEnabled(true);
        btnMultiply.setEnabled(true);
      }

      if (isWork) {
        txtRows.setEnabled(false);
        txtColumns.setEnabled(false);

        btnAction.setText("New");
        btnAction.setBackground(MAIN_COLOR);
      }

    } else if (btnAction.getText().equals("New")) {

      if (isWork) {

        emptyAP();

        deleteAll();

        btnDeterminant.setEnabled(false);
        btnAuto.setEnabled(false);
        btnClean.setEnabled(false);
        btnTransposed.setEnabled(false);
        btnDiagonals.setEnabled(false);
        btnMultiply.setEnabled(false);

        txtRows.setEnabled(true);
        txtColumns.setEnabled(true);

        btnAction.setText("Show");
        btnAction.setBackground(TEXT_COLOR);
      }
    }
  }

  private void deleteAll() {

    for (int i = 0; i < arrayBoard.length; i++) {
      for (int j = 0; j < arrayBoard.length; j++) {
        arrayBoard[i][j].setVisible(false);
        arrayBoard[i][j].setText("");
        arrayBoard[i][j].setBackground(getBackground());
        arrayBoard[i][j].setFont(MEDIUM);
      }
    }
  }
}

class Determinante {

  /**
   * Calcula el determinante de la matriz. Para ello coge la primera fila y se va multiplicando cada
   * coeficiente por el determinante de la matriz de orden n-1 que resulta de suprimir la fila y
   * columna del coeficiente. Hay que ir alternando los signos. Ver http://www.marcevm.com/determinantes/determinantes_def.php
   *
   * @param matriz
   * @return
   */
  public static double determinante(double[][] matriz) {

    /*
     * Aunque se ponga assert, su ejecución es opcional con -ea. Podemos arrancar la
     * aplicación en modo "detección de fallos" o en modo
     * "haz lo que puedas, pero aguanta". Se detectan los fallos en la ejecución lo
     * más pronto posible. De esta forma, si ejecutamos nuestro código java de forma
     * normal, ese assert es ignorado, por lo que es como si no hubiéramos puesto
     * nada. Nuestra aplicación funcionará igual de mal que antes y no se caerá. Sin
     * embargo, si ejecutamos con la opción -ea (enable asserts posiblemente)
     * entonces sí se hace caso de los assert y saltará una excepción inmediatamente
     * si nuestro parámetro es null. En vez de ir evitando el error de un sitio a
     * otro y ver que la aplicación no funciona, arrancando con -ea veremos
     * inmediatamente cual es el primer sitio en el que falla algo.
     */

    assert matriz != null;
    assert matriz.length > 0;
    assert matriz.length == matriz[0].length;

    double determinante = 0.0;

    int filas = matriz.length;
    int columnas = matriz[0].length;

    // Si la matriz es 1x1, el determinante es el elemento de la matriz
      if ((filas == 1) && (columnas == 1)) {
          return matriz[0][0];
      }

    int signo = 1;

    for (int columna = 0; columna < columnas; columna++) {
      // Obtiene el adjunto de fila=0, columna=columna, pero sin el signo.
      double[][] submatriz = getSubmatriz(matriz, filas, columnas, columna);
      determinante = determinante + signo * matriz[0][columna] * determinante(submatriz);
      signo *= -1;
    }

    return determinante;
  }

  /**
   * Obtiene la matriz que resulta de eliminar la primera fila y la columna que se pasa como
   * parámetro.
   *
   * @param matriz   Matriz original
   * @param filas    Numero de filas de la matriz original
   * @param columnas Numero de columnas de la matriz original
   * @param columna  Columna que se quiere eliminar, junto con la fila=0
   * @return Una matriz de N-1 x N-1 elementos
   */
  public static double[][] getSubmatriz(double[][] matriz, int filas, int columnas, int columna) {
    double[][] submatriz = new double[filas - 1][columnas - 1];
    int contador = 0;
    for (int j = 0; j < columnas; j++) {
        if (j == columna) {
            continue;
        }
        for (int i = 1; i < filas; i++) {
            submatriz[i - 1][contador] = matriz[i][j];
        }
      contador++;
    }
    return submatriz;
  }

  public static double getDeterminante(JButton[][] matriz, int f, int c) {

    double[][] matrizAux = new double[f][c];

    for (int i = 0; i < f; i++) {
      for (int j = 0; j < c; j++) {
        matrizAux[i][j] = Double.parseDouble(matriz[i][j].getText());
      }
    }

    return determinante(matrizAux);
  }
}