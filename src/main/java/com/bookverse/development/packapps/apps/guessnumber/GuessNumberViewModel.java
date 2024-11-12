package com.bookverse.development.packapps.apps.guessnumber;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GuessNumberViewModel {
  private JButton btnPlay;
  private JButton btnReturn;
  private JLabel guessNumberTitle;
  private JLabel response;
  private JLabel help;
  private JLabel question;
  private JTextField txtNumber;
}
