package com.bookverse.packapps.apps.arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArraysViewModel {
  private JButton btnDeterminant;
  private JButton btnAction;
  private JButton btnAuto;
  private JButton btnClean;
  private JButton btnTransposed;
  private JButton btnDiagonals;
  private JButton btnMultiply;
  private JTextField txtRows;
  private JTextField txtColumns;
  private JButton[][] board;
  private JDialog parent;
}
