package com.bookverse.development.packapps.apps.hangman;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HangmanViewModel {
  private JComboBox<String> options;
  private JLabel attempts;
  private JLabel time;
  private JLabel lyricsNumber;
  private JLabel lyricsPressed;
  private JLabel image;
  private JTextField txtWord;
  private JButton btnPlay;
  private JButton btnExit;
  private JDialog parent;
}
