package com.bookverse.development.packapps.utils;

import javax.swing.JButton;
import org.jetbrains.annotations.NotNull;

public class Determinant {

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
