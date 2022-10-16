package com.bookverse.development.packapps.apps.services;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jetbrains.annotations.NotNull;

import com.bookverse.development.packapps.apps.utils.other.Format;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

import static com.bookverse.development.packapps.apps.utils.other.GeneralUtilities.getIntRandom;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.MEDIUM;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.SMALL;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.TEXT_COLOR;

public final class ArraysService {

  private static boolean isWork = false;

  public static void clickOnDeterminant(JButton[][] board, JTextField txtRows, JTextField txtColumns) {

    if (fullInputs(board, Integer.parseInt(txtRows.getText()), Integer.parseInt(txtColumns.getText()))) {

      int f = Integer.parseInt(txtRows.getText());
      int c = Integer.parseInt(txtColumns.getText());

      if (isSquared(f, c)) {
        Alerts.message("Determinant of the matrix", "|A| = " + getDeterminant(board, f, c));
      }
    }
  }

  public static void clickOnMultiply(JButton[][] board, JTextField txtRows, JTextField txtColumns, JDialog parent) {

    if (fullInputs(board, Integer.parseInt(txtRows.getText()),
        Integer.parseInt(txtColumns.getText()))) {

      for (int i = 0; i < Integer.parseInt(txtRows.getText()); i++) {
        for (int j = 0; j < Integer.parseInt(txtColumns.getText()); j++) {
          board[i][j].setBackground(parent.getBackground());
        }
      }

      Object option = JOptionPane.showInputDialog(null,
          "<html>" + Format.style()
              + "<strong><em>What do you want to multiply the matrix with?</em></strong></html>",
          "Matrix product", JOptionPane.PLAIN_MESSAGE, null,
          new Object[]{"A scalar", "A vector", "A matrix"}, "A scalar");

      getMenu(board, txtRows, txtColumns, option, parent);
    }
  }

  private static void getMenu(JButton[][] board, JTextField txtRows, JTextField txtColumns, Object option, JDialog parent) {

    if (option != null) {

      switch (option.toString()) {

        case "A scalar":

          int scalar = Integer.parseInt(
              Alerts.inputNumber("Enter the scalar by which you want to multiply the matrix", 2));

          for (int i = 0; i < Integer.parseInt(txtRows.getText()); i++) {
            for (int j = 0; j < Integer.parseInt(txtColumns.getText()); j++) {

              if (board[i][j].getText().length() == 1) {
                board[i][j]
                    .setText(
                        String.valueOf(Integer.parseInt(board[i][j].getText()) * scalar));
                board[i][j].setFont(MEDIUM);
              } else if (board[i][j].getText().length() == 2) {
                board[i][j]
                    .setText(
                        String.valueOf(Integer.parseInt(board[i][j].getText()) * scalar));
                board[i][j].setFont(SMALL);
              }
            }
          }

          break;

        case "A vector":

          int fv = Integer.parseInt(txtRows.getText());
          int cv = Integer.parseInt(txtColumns.getText());

          double[][] matrizO = new double[fv][cv];
          double[][] vectorB = getMatrixB(cv, 1, parent);

          for (int i = 0; i < fv; i++) {
            for (int j = 0; j < cv; j++) {
              matrizO[i][j] = Double.parseDouble(board[i][j].getText());
            }
          }

          showMatrixC(multiplyMatrix(matrizO, vectorB), fv, 1, parent);

          break;

        case "A matrix":

          int columnsB = Integer
              .parseInt(
                  Alerts.inputNumber("Enter the number of columns that matrix B will have", 1));

          int f = Integer.parseInt(txtRows.getText());
          int c = Integer.parseInt(txtColumns.getText());

          double[][] matrixA = new double[f][c];
          double[][] matrixB = getMatrixB(c, columnsB, parent);

          for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
              matrixA[i][j] = Double.parseDouble(board[i][j].getText());
            }
          }

          showMatrixC(multiplyMatrix(matrixA, matrixB), f, columnsB, parent);

          break;

        default:
          JOptionPane.showMessageDialog(
              null, "Invalid option", "Error", JOptionPane.PLAIN_MESSAGE
          );
      }
    }
  }

  public static void clickOnDiagonals(JButton[][] board, JTextField txtRows, JTextField txtColumns) {

    if (fullInputs(board, Integer.parseInt(txtRows.getText()),
        Integer.parseInt(txtColumns.getText()))) {

      int f = Integer.parseInt(txtRows.getText());
      int c = Integer.parseInt(txtColumns.getText());

      if (isSquared(f, c)) {

        String principal = "";
        String secundaria = "";
        int sumaSuperior = 0;
        int sumaInferior = 0;

        for (int i = 0; i < f; i++) {
          for (int j = 0; j < c; j++) {

            if (i == j) {
              principal += "[" + board[i][j].getText() + "]";
              board[i][j].setBackground(MAIN_COLOR);
            }

            if (i + j == f - 1) {
              secundaria += "[" + board[i][j].getText() + "]";
              board[i][j].setBackground(TEXT_COLOR);
            }
          }
        }

        for (int i = 0; i < f; i++) {
          for (int j = i; j < c; j++) {

            if (i != j) {
              sumaSuperior += Integer.parseInt(board[i][j].getText());
            }
          }
        }

        for (int i = 1; i < f; i++) {
          for (int j = 0; j < c; j++) {
            if (j < i) {
              sumaInferior += Integer.parseInt(board[i][j].getText());
            }
          }
        }

        JOptionPane.showMessageDialog(null,
            "<html>" + Format.style() + "<strong><center>Result</center></strong><br>"
                + "Main diagonal → " + principal + "<br>" + "Sum of the upper triangle → "
                + sumaSuperior + "<br><br>" + "Secondary diagonal → " + secundaria + "<br>"
                + "Sum of the lower triangle → " + sumaInferior + "</html>",
            "Matrix diagonals", JOptionPane.PLAIN_MESSAGE);
      }
    }
  }

  private static void showMatrixC(int[][] matrixC, int rows, int columns, JDialog parent) {

    JDialog result = new JDialog(parent, true);
    result.setLayout(null);

    JButton[][] matrizResult = new JButton[rows][columns];

    int x = 30;
    int y = 30;

    for (int f = 0; f < rows; f++) {
      for (int c = 0; c < columns; c++) {
        matrizResult[f][c] = new JButton();
        matrizResult[f][c].setBounds(x, y, 45, 45);
        matrizResult[f][c].setText(String.valueOf(matrixC[f][c]));
        matrizResult[f][c].setFont(SMALL);
        result.add(matrizResult[f][c]);
        x = x + 45;
      }

      x = 30;
      y = y + 45;
    }

    result.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    result.setSize(480, 480);
    result.setTitle("Result of multiplying A x B");
    result.setResizable(false);
    result.setLocationRelativeTo(null);
    result.setVisible(true);
  }

  public static boolean isSquared(int f, int c) {

    if (f != c) {

      JOptionPane
          .showMessageDialog(null,
              "<html>" + Format.style() + "<strong><center>Invalid matrix</center></strong><br>"
                  + "The current matrix is not square." + "</html>",
              "Verify!", JOptionPane.PLAIN_MESSAGE);

      return false;
    }

    return true;
  }

  public static void manualFill(ActionEvent e, JButton[][] matrix, int f, int c) {

    for (int i = 0; i < f; i++) {
      for (int j = 0; j < c; j++) {

        if (e.getSource() == matrix[i][j]) {
          matrix[i][j].setText(
              Alerts
                  .inputNumber("Enter the value of the position [" + (i + 1) + "][" + (j + 1) + "]",
                      2));
        }
      }
    }
  }

  @NotNull
  public static int[][] multiplyMatrix(double[][] a, double[][] b) {
    int[][] c = new int[a.length][b[0].length];

    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < b[0].length; j++) {
        for (int k = 0; k < a[0].length; k++) {
          c[i][j] += a[i][k] * b[k][j];
        }
      }
    }

    return c;
  }

  @NotNull
  public static double[][] getMatrixB(int filas, int columnas, JDialog parent) {

    JDialog result = new JDialog(parent, true);
    result.setLayout(null);

    JButton[][] matrizResult = new JButton[filas][columnas];
    double[][] matrizB = new double[filas][columnas];

    int x = 30;
    int y = 30;

    for (int f = 0; f < filas; f++) {
      for (int c = 0; c < columnas; c++) {
        matrizResult[f][c] = new JButton();
        matrizResult[f][c].setBounds(x, y, 45, 45);

        matrizResult[f][c].addActionListener(e -> manualFill(e, matrizResult, filas, columnas));

        matrizResult[f][c].setFont(MEDIUM);
        result.add(matrizResult[f][c]);
        x = x + 45;
      }

      x = 30;
      y = y + 45;
    }

    JButton btnSend = new JButton("Finished");
    btnSend.setBackground(MAIN_COLOR);
    btnSend.setBounds(250, 455, 120, 25);
    result.add(btnSend);
    btnSend.addActionListener(e -> {

      if (fullInputs(matrizResult, filas, columnas)) {

        for (int i = 0; i < filas; i++) {
          for (int j = 0; j < columnas; j++) {
            matrizB[i][j] = Double.parseDouble(matrizResult[i][j].getText());
          }
        }

        result.dispose();
      }
    });

    JButton btnAuto = new JButton("Auto fill");
    btnAuto.setBackground(TEXT_COLOR);
    btnAuto.setBounds(100, 455, 120, 25);
    result.add(btnAuto);
    btnAuto.addActionListener(e -> clickOnAutoFill(matrizResult, filas, columnas, parent));

    result.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    result.setSize(480, 530);
    result.setTitle("Fill in B");
    result.setResizable(false);
    result.setLocationRelativeTo(null);
    result.setVisible(true);

    return matrizB;
  }

  public static void clickOnTransposed(JButton[][] board, JTextField txtRows, JTextField txtColumns, JDialog parent) {

    if (fullInputs(board, Integer.parseInt(txtRows.getText()),
        Integer.parseInt(txtColumns.getText()))) {

      for (int i = 0; i < Integer.parseInt(txtRows.getText()); i++) {
        for (int j = 0; j < Integer.parseInt(txtColumns.getText()); j++) {
          board[i][j].setBackground(parent.getBackground());
        }
      }

      JDialog result = new JDialog(parent, true);
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
          matrizResult[f][c].setText(board[c][f].getText());
          result.add(matrizResult[f][c]);
          x = x + 45;
        }

        x = 30;
        y = y + 45;
      }

      result.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      result.setSize(480, 480);
      result.setTitle("Transposed matrix result");
      result.setResizable(false);
      result.setLocationRelativeTo(null);
      result.setVisible(true);
    }
  }

  public static boolean fullInputs(JButton[][] matriz, int f, int c) {

    for (int i = 0; i < f; i++) {
      for (int j = 0; j < c; j++) {
        if (matriz[i][j].getText().equals("")) {

          JOptionPane.showMessageDialog(null,
              "<html>" + Format.style()
                  + "<strong><center>Undefined values</center></strong><br>"
                  + "Some of the values are not undefined, please enter a value."
                  + "</html>",
              "Verify!", JOptionPane.PLAIN_MESSAGE);

          return false;
        }
      }
    }

    return true;
  }

  public static void clickOnClear(JButton[][] board, JTextField txtRows, JTextField txtColumns, JDialog parent) {

    for (int i = 0; i < Integer.parseInt(txtRows.getText()); i++) {
      for (int j = 0; j < Integer.parseInt(txtColumns.getText()); j++) {
        board[i][j].setText("");
        board[i][j].setBackground(parent.getBackground());
        board[i][j].setFont(MEDIUM);
      }
    }
  }

  public static void clickOnAutoFill(JButton[][] matrix, int f, int c, JDialog parent) {

    for (int i = 0; i < f; i++) {
      for (int j = 0; j < c; j++) {
        matrix[i][j].setText(String.valueOf(getIntRandom(0, 9)));
        matrix[i][j].setBackground(parent.getBackground());
        matrix[i][j].setFont(MEDIUM);
      }
    }
  }

  public static void clickOnAction(
      JButton[][] board, JTextField txtRows, JTextField txtColumns, JDialog parent,
      JButton btnDeterminant, JButton btnAction, JButton btnAuto, JButton btnClean, JButton btnTransposed, JButton btnDiagonals, JButton btnMultiply) {

    if (btnAction.getText().equals("Show")) {

      if (txtRows.getText().equals("") || txtColumns.getText().equals("")) {
        Alerts.inputSomethingText();
        isWork = false;
      } else {

        if (Integer.parseInt(txtRows.getText()) < 2 || Integer.parseInt(txtColumns.getText()) < 2){
          Alerts.message("Verify!", "Dimensions must be greater than or equal to 2");
          isWork = false;
        }else{

          isWork = true;

          deleteAll(board, parent);

          for (int i = 0; i < Integer.parseInt(txtRows.getText()); i++) {
            for (int j = 0; j < Integer.parseInt(txtColumns.getText()); j++) {
              board[i][j].setVisible(true);
            }
          }

          btnAuto.setEnabled(true);
          btnDeterminant.setEnabled(true);
          btnClean.setEnabled(true);
          btnTransposed.setEnabled(true);
          btnDiagonals.setEnabled(true);
          btnMultiply.setEnabled(true);
        }
      }

      if (isWork) {
        txtRows.setEnabled(false);
        txtColumns.setEnabled(false);

        btnAction.setText("New");
        btnAction.setBackground(MAIN_COLOR);
      }

    } else if (btnAction.getText().equals("New")) {

      if (isWork) {

        clickOnClear(board, txtRows, txtColumns, parent);

        deleteAll(board, parent);

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

  public static void deleteAll(JButton[][] board, JDialog parent) {

    for (JButton[] jButtons : board) {
      for (int j = 0; j < board.length; j++) {
        jButtons[j].setVisible(false);
        jButtons[j].setText("");
        jButtons[j].setBackground(parent.getBackground());
        jButtons[j].setFont(MEDIUM);
      }
    }
  }

  private ArraysService() {
  }

  public static double determinant(@NotNull double[][] matrix) {

    double determinantValue = 0.0;

    int rows = matrix.length;
    int columns = matrix[0].length;

    if (rows == 1 && columns == 1) {
      return matrix[0][0];
    }

    int sign = 1;

    for (int column = 0; column < columns; column++) {
      double[][] subMatrix = getSubMatrix(matrix, rows, columns, column);
      determinantValue = determinantValue + sign * matrix[0][column] * determinant(subMatrix);
      sign *= -1;
    }

    return determinantValue;
  }

  @NotNull
  public static double[][] getSubMatrix(double[][] matrix, int rows, int columns, int column) {
    double[][] subMatrix = new double[rows - 1][columns - 1];
    int count = 0;
    for (int j = 0; j < columns; j++) {
      if (j == column) {
        continue;
      }
      for (int i = 1; i < rows; i++) {
        subMatrix[i - 1][count] = matrix[i][j];
      }
      count++;
    }
    return subMatrix;
  }

  public static double getDeterminant(JButton[][] matrix, int f, int c) {

    double[][] matrixAux = new double[f][c];

    for (int i = 0; i < f; i++) {
      for (int j = 0; j < c; j++) {
        matrixAux[i][j] = Double.parseDouble(matrix[i][j].getText());
      }
    }

    return determinant(matrixAux);
  }
}
