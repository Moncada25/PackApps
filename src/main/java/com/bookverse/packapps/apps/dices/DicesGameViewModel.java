package com.bookverse.packapps.apps.dices;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DicesGameViewModel {
  private List<JLabel> dices;
  private List<JTextField> players;
  private List<JLabel> lblPoints;
  private JButton btnExit;
  private JButton btnThrow;
  private JButton btnReset;
  private JDialog parent;
}
