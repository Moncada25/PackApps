package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.AppConfigCore.BIG;
import static com.bookverse.development.packapps.core.AppConfigCore.MAIN_COLOR;
import static com.bookverse.development.packapps.core.AppConfigCore.MEDIUM;
import static com.bookverse.development.packapps.core.AppConfigCore.SMALL;
import static com.bookverse.development.packapps.core.AppConfigCore.TEXT_COLOR;
import static com.bookverse.development.packapps.core.AppConfigCore.getIntRandom;

import com.bookverse.development.packapps.core.AppConfigCore;
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
import org.jetbrains.annotations.NotNull;

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
          getMatrixAP();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      public void txtNumKeyTyped(KeyEvent e) {
        Format.onlyNumbers(e.getKeyChar(), e, txtColumns.getText(), 1);
      }
    });
  }

  public void start(JDialog parent) {
    setBounds(0, 0, 900, 600);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Arrays");
    AppConfigCore.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnAction) {
      getMatrixAP();
    } else if (e.getSource() == btnAuto) {
      autoFillAP(arrayBoard, Integer.parseInt(txtRows.getText()),
          Integer.parseInt(txtColumns.getText()));
    } else if (e.getSource() == btnClean) {
      emptyAP();
    } else if (e.getSource() == btnTransposed) {
      transposedAP();
    } else if (e.getSource() == btnDiagonals) {
      diagonalsAP();
    } else if (e.getSource() == btnMultiply) {
      multiplyAP();
    } else if (e.getSource() == btnDeterminant) {
      determinantAP();
    }

    if (btnAction.getText().equals("New")) {
      manualFill(e, arrayBoard, Integer.parseInt(txtRows.getText()),
          Integer.parseInt(txtColumns.getText()));
    }
  }

  private void determinantAP() {

    if (fullInputs(arrayBoard, Integer.parseInt(txtRows.getText()),
        Integer.parseInt(txtColumns.getText()))) {

      int f = Integer.parseInt(txtRows.getText());
      int c = Integer.parseInt(txtColumns.getText());

      if (isSquared(f, c)) {
        Alerts.message("Determinant of the matrix",
            "|A| = " + Determinant.getDeterminant(arrayBoard, f, c));
      }
    }
  }

  private void multiplyAP() {

    if (fullInputs(arrayBoard, Integer.parseInt(txtRows.getText()),
        Integer.parseInt(txtColumns.getText()))) {

      for (int i = 0; i < Integer.parseInt(txtRows.getText()); i++) {
        for (int j = 0; j < Integer.parseInt(txtColumns.getText()); j++) {
          arrayBoard[i][j].setBackground(getBackground());
        }
      }

      Object option = JOptionPane.showInputDialog(null,
          "<html>" + Format.style()
              + "<strong><em>What do you want to multiply the matrix with?</em></strong></html>",
          "Matrix product", JOptionPane.PLAIN_MESSAGE, null,
          new Object[]{"A scalar", "A vector", "A matrix"}, "A scalar");

      if (option != null) {

        switch (option.toString()) {

          case "A scalar":

            int scalar = Integer.parseInt(
                Alerts.inputNumber("Enter the scalar by which you want to multiply the matrix", 2));

            for (int i = 0; i < Integer.parseInt(txtRows.getText()); i++) {
              for (int j = 0; j < Integer.parseInt(txtColumns.getText()); j++) {

                if (arrayBoard[i][j].getText().length() == 1) {
                  arrayBoard[i][j]
                      .setText(
                          String.valueOf(Integer.parseInt(arrayBoard[i][j].getText()) * scalar));
                  arrayBoard[i][j].setFont(MEDIUM);
                } else if (arrayBoard[i][j].getText().length() == 2) {
                  arrayBoard[i][j]
                      .setText(
                          String.valueOf(Integer.parseInt(arrayBoard[i][j].getText()) * scalar));
                  arrayBoard[i][j].setFont(SMALL);
                }
              }
            }

            break;

          case "A vector":

            int fv = Integer.parseInt(txtRows.getText());
            int cv = Integer.parseInt(txtColumns.getText());

            double[][] matrizO = new double[fv][cv];
            double[][] vectorB = getMatrixB(cv, 1);

            for (int i = 0; i < fv; i++) {
              for (int j = 0; j < cv; j++) {
                matrizO[i][j] = Double.parseDouble(arrayBoard[i][j].getText());
              }
            }

            showMatrixC(multiplyMatrix(matrizO, vectorB), fv, 1);

            break;

          case "A matrix":

            int columnsB = Integer
                .parseInt(
                    Alerts.inputNumber("Enter the number of columns that matrix B will have", 1));

            int f = Integer.parseInt(txtRows.getText());
            int c = Integer.parseInt(txtColumns.getText());

            double[][] matrixA = new double[f][c];
            double[][] matrixB = getMatrixB(c, columnsB);

            for (int i = 0; i < f; i++) {
              for (int j = 0; j < c; j++) {
                matrixA[i][j] = Double.parseDouble(arrayBoard[i][j].getText());
              }
            }

            showMatrixC(multiplyMatrix(matrixA, matrixB), f, columnsB);

            break;

          default:
            JOptionPane
                .showMessageDialog(null, "Invalid option", "Error", JOptionPane.PLAIN_MESSAGE);
        }
      }
    }
  }

  private void showMatrixC(int[][] matrixC, int rows, int columns) {

    JDialog result = new JDialog(this, true);
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

  private void diagonalsAP() {

    if (fullInputs(arrayBoard, Integer.parseInt(txtRows.getText()),
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
            "<html>" + Format.style() + "<strong><center>Result</center></strong><br>"
                + "Main diagonal → " + principal + "<br>" + "Sum of the upper triangle → "
                + sumaSuperior + "<br><br>" + "Secondary diagonal → " + secundaria + "<br>"
                + "Sum of the lower triangle → " + sumaInferior + "</html>",
            "Matrix diagonals", JOptionPane.PLAIN_MESSAGE);
      }
    }
  }

  private boolean isSquared(int f, int c) {

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

  @NotNull
  private int[][] multiplyMatrix(double[][] a, double[][] b) {
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
  private double[][] getMatrixB(int filas, int columnas) {

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

        matrizResult[f][c].addActionListener(e -> manualFill(e, matrizResult, filas, columnas));

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
    btnsend.addActionListener(e -> {

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
    btnAuto.addActionListener(e -> autoFillAP(matrizResult, filas, columnas));

    result.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    result.setSize(480, 530);
    result.setTitle("Fill in B");
    result.setResizable(false);
    result.setLocationRelativeTo(null);
    result.setVisible(true);

    return matrizB;
  }

  private void transposedAP() {

    if (fullInputs(arrayBoard, Integer.parseInt(txtRows.getText()),
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
      result.setTitle("Transposed matrix result");
      result.setResizable(false);
      result.setLocationRelativeTo(null);
      result.setVisible(true);
    }
  }

  private boolean fullInputs(JButton[][] matriz, int f, int c) {

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

  private void emptyAP() {

    for (int i = 0; i < Integer.parseInt(txtRows.getText()); i++) {
      for (int j = 0; j < Integer.parseInt(txtColumns.getText()); j++) {
        arrayBoard[i][j].setText("");
        arrayBoard[i][j].setBackground(getBackground());
        arrayBoard[i][j].setFont(MEDIUM);
      }
    }
  }

  private void autoFillAP(JButton[][] matrix, int f, int c) {

    for (int i = 0; i < f; i++) {
      for (int j = 0; j < c; j++) {
        matrix[i][j].setText(String.valueOf(getIntRandom(0, 9)));
        matrix[i][j].setBackground(getBackground());
        matrix[i][j].setFont(MEDIUM);
      }
    }
  }

  private void getMatrixAP() {

    if (btnAction.getText().equals("Show")) {

      if (txtRows.getText().equals("") || txtColumns.getText().equals("")) {
        Alerts.inputSomethingText();
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

    for (JButton[] jButtons : arrayBoard) {
      for (int j = 0; j < arrayBoard.length; j++) {
        jButtons[j].setVisible(false);
        jButtons[j].setText("");
        jButtons[j].setBackground(getBackground());
        jButtons[j].setFont(MEDIUM);
      }
    }
  }
}

class Determinant {

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