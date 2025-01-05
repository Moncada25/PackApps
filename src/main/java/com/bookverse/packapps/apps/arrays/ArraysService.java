package com.bookverse.packapps.apps.arrays;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import com.bookverse.packapps.utils.constants.Styles;
import com.bookverse.packapps.utils.GeneralUtils;
import com.bookverse.packapps.utils.Format;
import com.bookverse.packapps.utils.ui.Alerts;

@Data
public class ArraysService {

  private boolean isWork = false;

  public void clickOnAction(ArraysViewModel model) {

    if (model.getBtnAction().getText().equals("Show")) {

      if (model.getTxtRows().getText().isEmpty() || model.getTxtColumns().getText().isEmpty()) {
        Alerts.inputSomethingText();
        isWork = false;
      } else {

        if (Integer.parseInt(model.getTxtRows().getText()) < 2
            || Integer.parseInt(model.getTxtColumns().getText()) < 2) {
          Alerts.message("Verify!", "Dimensions must be greater than or equal to 2");
          isWork = false;
        } else {
          litteMatrix(model);
        }
      }

      if (isWork) {
        model.getTxtRows().setEnabled(false);
        model.getTxtColumns().setEnabled(false);
        model.getBtnAction().setText("New");
        model.getBtnAction().setBackground(Styles.MAIN_COLOR);
      }

    } else if (model.getBtnAction().getText().equals("New") && isWork) {

      clickOnClear(model);

      deleteAll(model);

      model.getBtnDeterminant().setEnabled(false);
      model.getBtnAuto().setEnabled(false);
      model.getBtnClean().setEnabled(false);
      model.getBtnTransposed().setEnabled(false);
      model.getBtnDiagonals().setEnabled(false);
      model.getBtnMultiply().setEnabled(false);

      model.getTxtRows().setEnabled(true);
      model.getTxtColumns().setEnabled(true);

      model.getBtnAction().setText("Show");
      model.getBtnAction().setBackground(Styles.TEXT_COLOR);
    }
  }

  public void clickOnDeterminant(ArraysViewModel model) {

    if (fullInputs(model)) {

      int f = Integer.parseInt(model.getTxtRows().getText());
      int c = Integer.parseInt(model.getTxtColumns().getText());

      if (isSquared(f, c)) {
        Alerts.message("Determinant of the matrix",
            "|A| = " + getDeterminant(model.getBoard(), f, c));
      }
    }
  }

  public void clickOnMultiply(ArraysViewModel model) {

    if (fullInputs(model)) {

      for (int i = 0; i < Integer.parseInt(model.getTxtRows().getText()); i++) {
        for (int j = 0; j < Integer.parseInt(model.getTxtColumns().getText()); j++) {
          model.getBoard()[i][j].setBackground(model.getParent().getBackground());
        }
      }

      Object option = JOptionPane.showInputDialog(null,
          "<html>" + Format.style()
              + "<strong><em>What do you want to multiply the matrix with?</em></strong></html>",
          "Matrix product", JOptionPane.PLAIN_MESSAGE, null,
          new Object[]{"A scalar", "A vector", "A matrix"}, "A scalar");

      getMenu(model, option);
    }
  }

  public void clickOnClear(ArraysViewModel model) {

    int f = Integer.parseInt(model.getTxtRows().getText());
    int c = Integer.parseInt(model.getTxtColumns().getText());

    for (int i = 0; i < f; i++) {
      for (int j = 0; j < c; j++) {
        model.getBoard()[i][j].setText("");
        model.getBoard()[i][j].setBackground(model.getParent().getBackground());
        model.getBoard()[i][j].setFont(Styles.MEDIUM);
      }
    }
  }

  public void clickOnAutoFill(JButton[][] matrix, int f, int c, JDialog parent) {

    for (int i = 0; i < f; i++) {
      for (int j = 0; j < c; j++) {
        matrix[i][j].setText(String.valueOf(GeneralUtils.getIntRandom(0, 9)));
        matrix[i][j].setBackground(parent.getBackground());
        matrix[i][j].setFont(Styles.MEDIUM);
      }
    }
  }

  public void clickOnTransposed(ArraysViewModel model) {

    if (fullInputs(model)) {

      for (int i = 0; i < Integer.parseInt(model.getTxtRows().getText()); i++) {
        for (int j = 0; j < Integer.parseInt(model.getTxtColumns().getText()); j++) {
          model.getBoard()[i][j].setBackground(model.getParent().getBackground());
        }
      }

      JDialog result = new JDialog(model.getParent(), true);
      result.setLayout(null);

      int fi = Integer.parseInt(model.getTxtColumns().getText());
      int ci = Integer.parseInt(model.getTxtRows().getText());

      JButton[][] matrizResult = new JButton[fi][ci];

      int x = 30;
      int y = 30;

      for (int f = 0; f < fi; f++) {
        for (int c = 0; c < ci; c++) {
          matrizResult[f][c] = new JButton();
          matrizResult[f][c].setBounds(x, y, 45, 45);
          matrizResult[f][c].setFont(Styles.MEDIUM);
          matrizResult[f][c].setText(model.getBoard()[c][f].getText());
          result.add(matrizResult[f][c]);
          x = x + 45;
        }

        x = 30;
        y = y + 45;
      }

      result.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      result.setSize(480, 480);
      result.setTitle("Transposed matrix result");
      result.setResizable(false);
      result.setLocationRelativeTo(null);
      result.setVisible(true);
    }
  }

  public void clickOnDiagonals(ArraysViewModel model) {

    if (fullInputs(model)) {

      int f = Integer.parseInt(model.getTxtRows().getText());
      int c = Integer.parseInt(model.getTxtColumns().getText());

      if (isSquared(f, c)) {

        List<String> diagonals = getDiagonals(model);

        JOptionPane.showMessageDialog(null,
            "<html>" + Format.style() + "<strong><center>Result</center></strong><br>"
                + "Main diagonal → " + diagonals.getFirst() + "<br>"
                + "Sum of the upper triangle → "
                + getSumUpperTriangle(model) + "<br><br>" + "Secondary diagonal → "
                + diagonals.getLast() + "<br>"
                + "Sum of the lower triangle → " + getSumLowerTriangle(model) + "</html>",
            "Matrix diagonals", JOptionPane.PLAIN_MESSAGE);
      }
    }
  }

  private void litteMatrix(ArraysViewModel model) {

    isWork = true;

    deleteAll(model);

    for (int i = 0; i < Integer.parseInt(model.getTxtRows().getText()); i++) {
      for (int j = 0; j < Integer.parseInt(model.getTxtColumns().getText()); j++) {
        model.getBoard()[i][j].setVisible(true);
      }
    }

    model.getBtnAuto().setEnabled(true);
    model.getBtnDeterminant().setEnabled(true);
    model.getBtnClean().setEnabled(true);
    model.getBtnTransposed().setEnabled(true);
    model.getBtnDiagonals().setEnabled(true);
    model.getBtnMultiply().setEnabled(true);
  }

  private void scalarCase(ArraysViewModel model) {
    int scalar = Integer.parseInt(
        Alerts.inputNumber("Enter the scalar by which you want to multiply the matrix", 2)
    );

    for (int i = 0; i < Integer.parseInt(model.getTxtRows().getText()); i++) {
      for (int j = 0; j < Integer.parseInt(model.getTxtColumns().getText()); j++) {

        if (model.getBoard()[i][j].getText().length() == 1) {
          model.getBoard()[i][j].setText(
              String.valueOf(Integer.parseInt(model.getBoard()[i][j].getText()) * scalar)
          );
          model.getBoard()[i][j].setFont(Styles.MEDIUM);
        } else if (model.getBoard()[i][j].getText().length() == 2) {
          model.getBoard()[i][j].setText(
              String.valueOf(Integer.parseInt(model.getBoard()[i][j].getText()) * scalar)
          );
          model.getBoard()[i][j].setFont(Styles.SMALL);
        }
      }
    }
  }

  private void vectorCase(ArraysViewModel model) {
    int fv = Integer.parseInt(model.getTxtRows().getText());
    int cv = Integer.parseInt(model.getTxtColumns().getText());

    double[][] matrizO = new double[fv][cv];
    double[][] vectorB = getMatrixB(cv, 1, model);

    for (int i = 0; i < fv; i++) {
      for (int j = 0; j < cv; j++) {
        matrizO[i][j] = Double.parseDouble(model.getBoard()[i][j].getText());
      }
    }

    showMatrixC(multiplyMatrix(matrizO, vectorB), fv, 1, model.getParent());
  }

  private void matrixCase(ArraysViewModel model) {
    int columnsB = Integer.parseInt(
        Alerts.inputNumber("Enter the number of columns that matrix B will have", 1)
    );

    int f = Integer.parseInt(model.getTxtRows().getText());
    int c = Integer.parseInt(model.getTxtColumns().getText());

    double[][] matrixA = new double[f][c];
    double[][] matrixB = getMatrixB(c, columnsB, model);

    for (int i = 0; i < f; i++) {
      for (int j = 0; j < c; j++) {
        matrixA[i][j] = Double.parseDouble(model.getBoard()[i][j].getText());
      }
    }

    showMatrixC(multiplyMatrix(matrixA, matrixB), f, columnsB, model.getParent());
  }

  private void getMenu(ArraysViewModel model, Object option) {

    if (option != null) {

      switch (option.toString()) {

        case "A scalar":
          scalarCase(model);
          break;

        case "A vector":
          vectorCase(model);
          break;

        case "A matrix":
          matrixCase(model);
          break;

        default:
          JOptionPane.showMessageDialog(
              null, "Invalid option", "Error", JOptionPane.PLAIN_MESSAGE
          );
      }
    }
  }

  private List<String> getDiagonals(ArraysViewModel model) {

    int f = Integer.parseInt(model.getTxtRows().getText());
    int c = Integer.parseInt(model.getTxtColumns().getText());

    StringBuilder principal = new StringBuilder();
    StringBuilder secundaria = new StringBuilder();

    for (int i = 0; i < f; i++) {
      for (int j = 0; j < c; j++) {

        if (i == j) {
          principal.append("[").append(model.getBoard()[i][j].getText()).append("]");
          model.getBoard()[i][j].setBackground(Styles.MAIN_COLOR);
        }

        if (i + j == f - 1) {
          secundaria.append("[").append(model.getBoard()[i][j].getText()).append("]");
          model.getBoard()[i][j].setBackground(Styles.TEXT_COLOR);
        }
      }
    }

    return List.of(principal.toString(), secundaria.toString());
  }

  private int getSumUpperTriangle(ArraysViewModel model) {

    int f = Integer.parseInt(model.getTxtRows().getText());
    int c = Integer.parseInt(model.getTxtColumns().getText());
    int sumUpper = 0;

    for (int i = 0; i < f; i++) {
      for (int j = i; j < c; j++) {
        if (i != j) {
          sumUpper += Integer.parseInt(model.getBoard()[i][j].getText());
        }
      }
    }

    return sumUpper;
  }

  private int getSumLowerTriangle(ArraysViewModel model) {

    int f = Integer.parseInt(model.getTxtRows().getText());
    int c = Integer.parseInt(model.getTxtColumns().getText());
    int sumLower = 0;

    for (int i = 0; i < f; i++) {
      for (int j = 0; j < c; j++) {
        if (i > j) {
          sumLower += Integer.parseInt(model.getBoard()[i][j].getText());
        }
      }
    }

    return sumLower;
  }

  private void showMatrixC(int[][] matrixC, int rows, int columns, JDialog parent) {

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
        matrizResult[f][c].setFont(Styles.SMALL);
        result.add(matrizResult[f][c]);
        x = x + 45;
      }

      x = 30;
      y = y + 45;
    }

    result.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    result.setSize(480, 480);
    result.setTitle("Result of multiplying A x B");
    result.setResizable(false);
    result.setLocationRelativeTo(null);
    result.setVisible(true);
  }

  private boolean isSquared(int f, int c) {

    if (f != c) {

      JOptionPane.showMessageDialog(
          null, "<html>" + Format.style() + "<strong><center>Invalid matrix</center></strong><br>" +
              "The current matrix is not square." + "</html>", "Verify!", JOptionPane.PLAIN_MESSAGE
      );

      return false;
    }

    return true;
  }

  private void manualFill(ActionEvent e, JButton[][] matrix, int f, int c) {

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

  private int[][] multiplyMatrix(double[][] a, double[][] b) {
    int[][] c = new int[a.length][b[0].length];

    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < b[0].length; j++) {
        for (int k = 0; k < a[0].length; k++) {
          c[i][j] += (int) (a[i][k] * b[k][j]);
        }
      }
    }

    return c;
  }

  private double[][] getMatrixB(int filas, int columnas, ArraysViewModel model) {

    JDialog result = new JDialog(model.getParent(), true);
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

        matrizResult[f][c].setFont(Styles.MEDIUM);
        result.add(matrizResult[f][c]);
        x = x + 45;
      }

      x = 30;
      y = y + 45;
    }

    JButton btnSend = new JButton("Finished");
    btnSend.setBackground(Styles.MAIN_COLOR);
    btnSend.setBounds(250, 455, 120, 25);
    result.add(btnSend);
    btnSend.addActionListener(e -> {

      if (fullInputs(model)) {

        for (int i = 0; i < filas; i++) {
          for (int j = 0; j < columnas; j++) {
            matrizB[i][j] = Double.parseDouble(matrizResult[i][j].getText());
          }
        }

        result.dispose();
      }
    });

    JButton btnAuto = new JButton("Auto fill");
    btnAuto.setBackground(Styles.TEXT_COLOR);
    btnAuto.setBounds(100, 455, 120, 25);
    result.add(btnAuto);
    btnAuto.addActionListener(
        e -> clickOnAutoFill(matrizResult, filas, columnas, model.getParent()));

    result.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    result.setSize(480, 530);
    result.setTitle("Fill in B");
    result.setResizable(false);
    result.setLocationRelativeTo(null);
    result.setVisible(true);

    return matrizB;
  }

  private boolean fullInputs(ArraysViewModel model) {

    int f = Integer.parseInt(model.getTxtRows().getText());
    int c = Integer.parseInt(model.getTxtColumns().getText());

    for (int i = 0; i < f; i++) {
      for (int j = 0; j < c; j++) {
        if (model.getBoard()[i][j].getText().isEmpty()) {

          JOptionPane.showMessageDialog(null,
              "<html>" + Format.style()
                  + "<strong><center>Undefined values</center></strong><br>"
                  + "Some of the values are undefined, please enter a value."
                  + "</html>",
              "Verify!", JOptionPane.PLAIN_MESSAGE);

          return false;
        }
      }
    }

    return true;
  }

  private void deleteAll(ArraysViewModel model) {

    for (JButton[] jButtons : model.getBoard()) {
      for (int j = 0; j < model.getBoard().length; j++) {
        jButtons[j].setVisible(false);
        jButtons[j].setText("");
        jButtons[j].setBackground(model.getParent().getBackground());
        jButtons[j].setFont(Styles.MEDIUM);
      }
    }
  }

  private double determinant(double[] @NotNull [] matrix) {

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

  private double[][] getSubMatrix(double[][] matrix, int rows, int columns, int column) {

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

  private double getDeterminant(JButton[][] matrix, int f, int c) {

    double[][] matrixAux = new double[f][c];

    for (int i = 0; i < f; i++) {
      for (int j = 0; j < c; j++) {
        matrixAux[i][j] = Double.parseDouble(matrix[i][j].getText());
      }
    }

    return determinant(matrixAux);
  }
}
