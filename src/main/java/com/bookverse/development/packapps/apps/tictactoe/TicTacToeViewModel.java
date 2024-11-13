package com.bookverse.development.packapps.apps.tictactoe;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import lombok.Data;
import com.bookverse.development.packapps.utils.constants.Styles;

@Data
public class TicTacToeViewModel {
  private Color colorO = Styles.MAIN_COLOR;
  private Color colorX = Styles.TEXT_COLOR;
  private JButton[][] board = new JButton[3][3];
  private JTextField txtNameX = new JTextField();
  private JTextField txtNameO = new JTextField();
  private JLabel pointsX;
  private JLabel pointsO;
  private JLabel lblTurn;
  private JLabel image;
  private JButton btnExit;
  private JButton btnReset;
  private JButton btnPlay;
  private JDialog parent;
}
