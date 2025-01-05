package com.bookverse.packapps.apps.puzzle;

import javax.swing.JButton;
import javax.swing.JLabel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PuzzleViewModel {
  private JButton[][] board;
  private JLabel lblTurn;
  private JButton btnPlay;
  private JButton btnStop;
  private JButton btnExit;
  private JLabel timer;
}
