package com.bookverse.development.packapps.apps.calculator;

import javax.swing.JButton;
import javax.swing.JTextField;
import lombok.Data;

@Data
public class CalculatorViewModel {
  private JButton[][] numbers;
  private JButton btnZero;
  private JButton btnPoint;
  private JButton btnAdd;
  private JButton btnLess;
  private JButton btnDivide;
  private JButton btnMultiply;
  private JButton btnNegative;
  private JButton btnRoot;
  private JButton btnEuler;
  private JButton btnPower;
  private JButton btnEqual;
  private JButton btnDelete;
  private JButton btnClean;
  private JTextField txtResult;
}
