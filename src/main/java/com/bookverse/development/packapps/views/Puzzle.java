package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.AppConfig.MAIN_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.TEXT_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.inputText;
import static com.bookverse.development.packapps.utils.DatabaseConstants.PUZZLE;

import com.bookverse.development.packapps.core.AppConfig;
import com.bookverse.development.packapps.models.Database;
import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.utils.Alerts;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Puzzle extends JDialog implements Runnable, ActionListener {

  Resources resources = new Resources();
  private JButton[][] board;
  private JLabel time, lblTurn;
  private JButton btnExit, btnPlay, btnReset;
  private int moves = 0;
  private boolean chronometerActive;
  private int size, side, minutes;

  public Puzzle(JFrame parent, boolean modal, int size, int side, int minutes) {
    super(parent, modal);
    this.size = size;
    this.minutes = minutes;
    this.side = side;
    createComponents();
  }

  public Puzzle(JDialog parent, boolean modal, int size, int side, int minutes) {
    super(parent, modal);
    this.size = size;
    this.minutes = minutes;
    this.side = side;
    createComponents();
  }

  public void start(JFrame parent) {
    setSize(490, 380);
    setResizable(false);
    setLocationRelativeTo(parent);
    if (size == 4) {
      setTitle(PUZZLE + " - Level Easy");
    } else if (size == 5) {
      setTitle(PUZZLE + " - Level Medium");
    } else {
      setTitle(PUZZLE + " - Level Hard");
    }
    AppConfig.fadeIn(this);
    parent.setVisible(false);
    AppConfig.instruccionesRompe();
    setVisible(true);
  }

  public void start(JDialog parent) {
    setSize(490, 380);
    setResizable(false);
    setLocationRelativeTo(parent);
    if (size == 4) {
      setTitle(PUZZLE + " - Level Easy");
    } else if (size == 5) {
      setTitle(PUZZLE + " - Level Medium");
    } else {
      setTitle(PUZZLE + " - Level Hard");
    }
    AppConfig.fadeIn(this);
    parent.setVisible(false);
    AppConfig.instruccionesRompe();
    setVisible(true);
  }

  private void createComponents() {

    setLayout(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(resources.getImage("rompecabezas.png")).getImage());

    btnExit = resources.getButton("Return", MAIN_COLOR, this, this);
    btnExit.setBounds(330, 300, 86, 30);

    btnPlay = resources.getButton("Play", TEXT_COLOR, this, this);
    btnPlay.setBounds(70, 300, 86, 30);

    btnReset = resources.getButton("Stop", MAIN_COLOR, this, this);
    btnReset.setBounds(200, 300, 86, 30);
    btnReset.setEnabled(false);

    lblTurn = resources.getLabel("", MAIN_COLOR, this, AppConfig.MEDIUM);
    lblTurn.setBounds(335, 90, 200, 100);

    time = resources.getLabel("", MAIN_COLOR, this, new Font("Times New Roman", Font.PLAIN, 45));
    time.setBounds(335, 5, 200, 60);

    board = new JButton[size][size];

    int x = 15;
    int y = 15;

    for (int f = 0; f < board.length; f++) {
      for (int c = 0; c < board.length; c++) {
        board[f][c] = resources.getButton(".", null, this, this);
        board[f][c].setBounds(x, y, side, side);
        board[f][c].setEnabled(false);

        x = x + side;
      }
      x = 15;
      y = y + side;
    }
  }

  private void btnResetAP() {

    stopChronometer();
    btnPlay.setEnabled(true);
    btnReset.setEnabled(false);
    btnExit.setEnabled(true);

    insert("Loser");

    lblTurn.setText("");
    time.setText("");

    for (JButton[] jButtons : board) {
      for (int j = 0; j < board.length; j++) {
        jButtons[j].setEnabled(false);
        jButtons[j].setText(".");
      }
    }
  }

  private void btnPlayAP() {

    int n = (int) Math.pow(size, 2) - 1;

    board[(int) (Math.random() * size)][(int) (Math.random() * size)].setText("");

    for (JButton[] jButtons : board) {
      for (int c = 0; c < board.length; c++) {

        if (!jButtons[c].getText().equals("")) {
          jButtons[c].setText(String.valueOf(n));
          n--;
        }
      }
    }

    unlock();

    lblTurn.setText("");
    moves = 0;

    btnReset.setEnabled(true);
    btnPlay.setEnabled(false);
    btnExit.setEnabled(false);

    startChronometer();
  }

  private boolean validateVictory() {

    int n = 1;
    boolean win = true;

    for (JButton[] jButtons : board) {

      for (int c = 0; c < board.length; c++) {

        if (n >= size * size) {
          break;
        }

        if (!jButtons[c].getText().equals(String.valueOf(n))) {
          win = false;
        }

        n++;
      }
    }

    return win;
  }

  private void getWinner() {

    lblTurn.setText("Moves: " + moves);

    if (validateVictory()) {

      stopChronometer();

      lblTurn.setText("<html>"
          + "<center><strong>You won!</strong></center>"
          + "<center>With " + moves + " moves</center>"
          + "</html>");
      lblTurn.setForeground(MAIN_COLOR);

      insert("Winner");

      lblTurn.setText("");
      time.setText("");

      for (JButton[] jButtons : board) {
        for (int j = 0; j < board.length; j++) {
          jButtons[j].setEnabled(false);
          jButtons[j].setText(".");
        }
      }

      btnExit.setEnabled(true);
      btnPlay.setEnabled(true);
      btnReset.setEnabled(false);
      lblTurn.setText("");
      time.setText("");
    }
  }

  private String getLevel() {

    String level = "";

    if (size == 4) {
      level = "Easy";
    } else if (size == 5) {
      level = "Medium";
    } else if (size == 6) {
      level = "Hard";
    }

    return level + " - " + time.getText();
  }

  private void insert(String state) {

    if (AppConfig.verifyConnection("Data don't saved", true) && AppConfig.saveGame()) {

      String[] data = {PUZZLE, inputText("Enter a Nickname", 20), state,
          getLevel(), String.valueOf(moves), AppConfig.getDate()};

      Database.insertData(data);
    }
  }

  private void startChronometer() {
    chronometerActive = true;
    Thread timeThread = new Thread(this);
    timeThread.start();
  }

  private void stopChronometer() {
    chronometerActive = false;
  }

  private void unlock() {

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {

        if (board[i][j].getText().equals("")) {

          if ((i == 0 && j == 0)) {
            board[0][1].setEnabled(true);
            board[0][1].setBackground(TEXT_COLOR);

            board[1][0].setEnabled(true);
            board[1][0].setBackground(TEXT_COLOR);
          } else if (i == board.length - 1 && j == board.length - 1) {
            board[board.length - 2][board.length - 1].setEnabled(true);
            board[board.length - 2][board.length - 1].setBackground(TEXT_COLOR);

            board[board.length - 1][board.length - 2].setEnabled(true);
            board[board.length - 1][board.length - 2].setBackground(TEXT_COLOR);
          } else if (i == 0 && j == board.length - 1) {
            board[0][board.length - 2].setEnabled(true);
            board[0][board.length - 2].setBackground(TEXT_COLOR);

            board[1][board.length - 1].setEnabled(true);
            board[1][board.length - 1].setBackground(TEXT_COLOR);
          } else if (i == board.length - 1 && j == 0) {
            board[board.length - 2][0].setEnabled(true);
            board[board.length - 2][0].setBackground(TEXT_COLOR);

            board[board.length - 1][1].setEnabled(true);
            board[board.length - 1][1].setBackground(TEXT_COLOR);
          } else if (i == 0 && j != 0 && j != board.length - 1) {
            board[0][j - 1].setEnabled(true);
            board[0][j - 1].setBackground(TEXT_COLOR);

            board[0][j + 1].setEnabled(true);
            board[0][j + 1].setBackground(TEXT_COLOR);

            board[1][j].setEnabled(true);
            board[1][j].setBackground(TEXT_COLOR);
          } else if (j == board.length - 1 && i != 0 && i != board.length - 1) {
            board[i - 1][j].setEnabled(true);
            board[i - 1][j].setBackground(TEXT_COLOR);

            board[i + 1][j].setEnabled(true);
            board[i + 1][j].setBackground(TEXT_COLOR);

            board[i][board.length - 2].setEnabled(true);
            board[i][board.length - 2].setBackground(TEXT_COLOR);
          } else if (i == board.length - 1 && j != 0 && j != board.length - 1) {
            board[board.length - 1][j - 1].setEnabled(true);
            board[board.length - 1][j - 1].setBackground(TEXT_COLOR);

            board[board.length - 1][j + 1].setEnabled(true);
            board[board.length - 1][j + 1].setBackground(TEXT_COLOR);

            board[board.length - 2][j].setEnabled(true);
            board[board.length - 2][j].setBackground(TEXT_COLOR);
          } else if (j == 0 && i != 0 && i != board.length - 1) {
            board[i - 1][j].setEnabled(true);
            board[i - 1][j].setBackground(TEXT_COLOR);

            board[i + 1][j].setEnabled(true);
            board[i + 1][j].setBackground(TEXT_COLOR);

            board[i][j + 1].setEnabled(true);
            board[i][j + 1].setBackground(TEXT_COLOR);
          } else {
            board[i - 1][j].setEnabled(true);
            board[i - 1][j].setBackground(TEXT_COLOR);

            board[i + 1][j].setEnabled(true);
            board[i + 1][j].setBackground(TEXT_COLOR);

            board[i][j + 1].setEnabled(true);
            board[i][j + 1].setBackground(TEXT_COLOR);

            board[i][j - 1].setEnabled(true);
            board[i][j - 1].setBackground(TEXT_COLOR);
          }
        }
      }
    }
  }

  private void mover(int f, int c) {

    for (JButton[] jButtons : board) {
      for (int j = 0; j < board.length; j++) {

        if (jButtons[j].getText().equals("")) {
          jButtons[j].setText(board[f][c].getText());
          jButtons[j].setEnabled(true);
          board[f][c].setEnabled(false);
          board[f][c].setText("");
        }
        jButtons[j].setEnabled(false);
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnPlay) {
      btnPlayAP();
    } else if (e.getSource() == btnExit) {
      AppConfig.fadeOut(this);
    } else if (e.getSource() == btnReset) {
      btnResetAP();
    }

    for (int f = 0; f < board.length; f++) {
      for (int c = 0; c < board.length; c++) {

        if (e.getSource() == board[f][c]) {
          moves++;
          mover(f, c);
          unlock();
          getWinner();
        }
      }
    }
  }

  @Override
  public void run() {

    int newMinutes = minutes - 1, second = 59, thousandths = 1000;
    String min, seg;

    try {

      while (chronometerActive) {
        Thread.sleep(4);
        thousandths -= 4;

        if (thousandths == 0) {
          thousandths = 1000;
          second -= 1;

          if (second == 0) {

            if (newMinutes > 0) {
              second = 59;
              newMinutes--;
            }
          }
        }

        if (newMinutes < 10) {
          min = "0" + newMinutes;
        } else {
          min = Integer.toString(newMinutes);
        }

        if (second < 10) {
          seg = "0" + second;
        } else {
          seg = String.valueOf(second);
        }

        time.setText(min + ":" + seg);

        if (newMinutes <= 0 && second <= 0) {
          btnResetAP();
        }
      }

    } catch (Exception e) {
      Alerts.error(e, PUZZLE);
    }
  }
}