package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.AppConfig.MAIN_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.MEDIUM;
import static com.bookverse.development.packapps.core.AppConfig.TEXT_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.verifyConnection;
import static com.bookverse.development.packapps.core.AppConfig.getDate;
import static com.bookverse.development.packapps.core.AppConfig.saveGame;
import static com.bookverse.development.packapps.utils.AppConstants.DICES;

import com.bookverse.development.packapps.core.AppConfig;
import com.bookverse.development.packapps.models.Database;
import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.Format;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Dices extends JDialog implements ActionListener {

  Dice d1 = new Dice();
  Dice d2 = new Dice();
  Dice d3 = new Dice();
  private JLabel dice1, dice2, dice3, lblPoints1, lblPoints2, lblPoints3;
  private JButton btnExit, btnThrow, btnReset;
  private JTextField player1, player2, player3;
  private int points1 = 0, points2 = 0, points3 = 0, round = 1, turn = 1;
  private boolean winner = false;
  private Resources resources = new Resources();

  public Dices(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public Dices(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {

    setLayout(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(resources.getImage("dado.png")).getImage());

    btnExit = resources.getButton("Return", MAIN_COLOR, this, this);
    btnExit.setBounds(330, 320, 86, 30);

    btnThrow = resources.getButton("Throw", TEXT_COLOR, this, this);
    btnThrow.setBounds(25, 320, 86, 30);

    btnReset = resources.getButton("Reset", TEXT_COLOR, this, this);
    btnReset.setBounds(185, 320, 86, 30);
    btnReset.setEnabled(false);

    dice1 = resources.getLabel("", null, this, null);
    dice1.setBounds(25, 10, 80, 80);

    dice2 = resources.getLabel("", null, this, null);
    dice2.setBounds(185, 10, 80, 80);

    dice3 = resources.getLabel("", null, this, null);
    dice3.setBounds(330, 10, 80, 80);

    player1 = new JTextField("Player 1");
    player1.setBounds(25, 150, 100, 30);
    player1.setHorizontalAlignment(JTextField.CENTER);
    add(player1);

    player1.addKeyListener(new KeyAdapter() {

      public void keyTyped(KeyEvent e) {
        player1KeyTyped(e);
      }

      private void player1KeyTyped(KeyEvent e) {
        Format.onlyText(e.getKeyChar(), e, player1.getText(), 10);
      }
    });

    lblPoints1 = resources.getLabel("", MAIN_COLOR, this, MEDIUM);
    lblPoints1.setBounds(45, 185, 120, 60);

    player2 = new JTextField("Player 2");
    player2.setBounds(170, 150, 100, 30);
    player2.setHorizontalAlignment(JTextField.CENTER);
    add(player2);

    player2.addKeyListener(new KeyAdapter() {

      public void keyTyped(KeyEvent e) {
        player2KeyTyped(e);
      }

      private void player2KeyTyped(KeyEvent e) {
        Format.onlyText(e.getKeyChar(), e, player2.getText(), 10);
      }
    });

    lblPoints2 = resources.getLabel("", MAIN_COLOR, this, MEDIUM);
    lblPoints2.setBounds(195, 189, 120, 60);

    player3 = new JTextField("Player 3");
    player3.setBounds(317, 150, 100, 30);
    player3.setHorizontalAlignment(JTextField.CENTER);
    add(player3);

    player3.addKeyListener(new KeyAdapter() {

      public void keyTyped(KeyEvent e) {
        player3KeyTyped(e);
      }

      private void player3KeyTyped(KeyEvent e) {
        Format.onlyText(e.getKeyChar(), e, player3.getText(), 10);
      }
    });

    lblPoints3 = resources.getLabel("", MAIN_COLOR, this, MEDIUM);
    lblPoints3.setBounds(340, 185, 120, 60);
  }

  @NotNull
  @Contract("_ -> new")
  private ImageIcon getIcon(int n) {
    return new ImageIcon(resources.getImage(n + ".png"));
  }

  public void start(JFrame parent) {
    setSize(450, 400);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle(DICES + ", throw them!");
    AppConfig.fadeIn(this);
    parent.setVisible(false);
    AppConfig.instruccionesDados();
    setVisible(true);
  }

  public void start(JDialog parent) {
    setSize(450, 400);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle(DICES + ", throw them!");
    AppConfig.fadeIn(this);
    parent.setVisible(false);
    AppConfig.instruccionesDados();
    setVisible(true);
  }

  private void btnThrowAP() {

    if (!player1.getText().equals(player2.getText()) && !player1.getText().equals(player3.getText())
        && !player2.getText().equals(player3.getText()) && !player1.getText().equals("") && !player2
        .getText().equals("") && !player3.getText().equals("")) {

      if (btnThrow.getText().equals("Throw")) {

        dice1.setIcon(new ImageIcon(resources.getImage("01.gif")));
        dice2.setIcon(new ImageIcon(resources.getImage("02.gif")));
        dice3.setIcon(new ImageIcon(resources.getImage("03.gif")));

        btnThrow.setText("Stop");
        btnThrow.setBackground(MAIN_COLOR);

        btnExit.setEnabled(false);
        player1.setEnabled(false);
        player2.setEnabled(false);
        player3.setEnabled(false);

        if (turn == 1) {
          setTitle("Round " + round + " - Turn of " + player1.getText());
        } else if (turn == 2) {
          setTitle("Round " + round + " - Turn of " + player2.getText());
        } else if (turn == 3) {
          setTitle("Round " + round + " - Turn of " + player3.getText());
        }

      } else if (btnThrow.getText().equals("Stop")) {

        if (round <= 5 && !winner) {

          if (turn == 1) {

            dice1.setIcon(getIcon(d1.throwDices()));
            dice2.setIcon(getIcon(d2.throwDices()));
            dice3.setIcon(getIcon(d3.throwDices()));

            if ((d1.getValor() == d2.getValor()) && (d1.getValor() == d3.getValor())) {
              Alerts.message("Congratulations",
                  player1.getText() + " has won, took all three equals dices!");

              dice1.setIcon(null);
              dice2.setIcon(null);
              dice3.setIcon(null);

              insertResults(player1.getText(), "Equals dices!");
              winner = true;
              btnReset.setEnabled(true);
              btnThrow.setEnabled(false);

            } else {
              points1 += d1.getValor() + d2.getValor() + d3.getValor();
              lblPoints1.setText("<html><center><strong>Points</strong><br>" + points1
                  + "</center></html>");
            }
            turn = 2;
          } else if (turn == 2) {

            dice1.setIcon(getIcon(d1.throwDices()));
            dice2.setIcon(getIcon(d2.throwDices()));
            dice3.setIcon(getIcon(d3.throwDices()));

            if ((d1.getValor() == d2.getValor()) && (d1.getValor() == d3.getValor())) {
              Alerts.message("Congratulations",
                  player2.getText() + " has won, took all three equals dices!");

              dice1.setIcon(null);
              dice2.setIcon(null);
              dice3.setIcon(null);

              insertResults(player2.getText(), "Equals dices!");
              btnReset.setEnabled(true);
              btnThrow.setEnabled(false);
              winner = true;

            } else {
              points2 += d1.getValor() + d2.getValor() + d3.getValor();
              lblPoints2.setText("<html><center><strong>Points</strong><br>" + points2
                  + "</center></html>");
            }
            turn = 3;
          } else if (turn == 3) {

            dice1.setIcon(getIcon(d1.throwDices()));
            dice2.setIcon(getIcon(d2.throwDices()));
            dice3.setIcon(getIcon(d3.throwDices()));

            if ((d1.getValor() == d2.getValor()) && (d1.getValor() == d3.getValor())) {
              Alerts.message("Congratulations",
                  player3.getText() + " has won, took all three equals dices!");

              dice1.setIcon(null);
              dice2.setIcon(null);
              dice3.setIcon(null);

              insertResults(player3.getText(), "Equals dices!");
              btnReset.setEnabled(true);
              btnThrow.setEnabled(false);
              winner = true;

            } else {
              points3 += d1.getValor() + d2.getValor() + d3.getValor();
              lblPoints3.setText("<html><center><strong>Points</strong><br>" + points3
                  + "</center></html>");
            }
            turn = 1;
            round++;
          }
        } else if (!winner && round > 5) {

          dice1.setIcon(null);
          dice2.setIcon(null);
          dice3.setIcon(null);

          highestScore();
        }

        if (round < 6) {
          btnThrow.setText("Throw");
          btnThrow.setBackground(TEXT_COLOR);
        } else if (!winner) {
          round--;
          highestScore();
        }
      }

    } else {
      Alerts.message("Warning", "Some fields are empty or their text are repeated.");
    }
  }

  private void highestScore() {

    if (points1 > points2 && points1 > points3) {
      Alerts.message("Congratulations",
          player1.getText() + " is the winner, scored " + points1 + " points!");
      insertResults(player1.getText(), points1 + " points");
    } else if (points2 > points1 && points2 > points3) {
      Alerts.message("Congratulations",
          player2.getText() + " is the winner, scored " + points2 + " points!");
      insertResults(player2.getText(), points2 + " points");
    } else if (points3 > points1 && points3 > points2) {
      Alerts.message("Congratulations",
          player3.getText() + " is the winner, scored " + points3 + " points!");
      insertResults(player3.getText(), points3 + " points");
    } else {

      if (points1 == points2 && points1 == points3) {
        Alerts.message("Congratulations", "The game ended, there was a triple tie!");
        insertResults(player1.getText() + ", " + player2.getText() + " & " + player3.getText(),
            points1 + " points");
      } else if (points1 == points2) {
        Alerts.message("Congratulations", "The game ended, there was a tie!");
        insertResults(player1.getText() + " & " + player2.getText(), points1 + " points");
      } else if (points1 == points3) {
        Alerts.message("Congratulations", "The game ended, there was a tie!");
        insertResults(player1.getText() + " & " + player3.getText(), points1 + " points");
      } else {
        Alerts.message("Congratulations", "The game ended, there was a tie!");
        insertResults(player2.getText() + " & " + player3.getText(), points2 + " points");
      }
    }

    btnReset.setEnabled(true);
    btnThrow.setEnabled(false);
    setTitle("GAME OVER");
  }

  private void insertResults(String name, String win) {

    if (verifyConnection("Data don't saved", true) && saveGame()) {
      try {
        String[] data = {DICES, name, win, String.valueOf(round),
            getDate()};
        Database.insertData(data);
      } catch (Exception e) {
        Alerts.error(e, DICES);
      }
    }
  }

  private void btnResetAP() {

    round = 1;
    turn = 1;
    winner = false;
    setTitle(DICES + ", throw them!");
    btnThrow.setText("Throw");
    btnThrow.setBackground(TEXT_COLOR);
    btnThrow.setEnabled(true);
    btnReset.setEnabled(false);
    btnExit.setEnabled(true);

    points1 = 0;
    lblPoints1.setText("");
    player1.setEnabled(true);

    points2 = 0;
    lblPoints2.setText("");
    player2.setEnabled(true);

    points3 = 0;
    lblPoints3.setText("");
    player3.setEnabled(true);

    dice1.setIcon(null);
    dice2.setIcon(null);
    dice3.setIcon(null);
  }

  @Override
  public void actionPerformed(@NotNull ActionEvent e) {

    if (e.getSource() == btnThrow) {
      btnThrowAP();
    }

    if (e.getSource() == btnExit) {
      AppConfig.fadeOut(this);
    }

    if (e.getSource() == btnReset) {
      btnResetAP();
    }
  }
}

class Dice {

  private int valor;

  public int throwDices() {
    valor = (1 + (int) (Math.random() * 6));
    return valor;
  }

  public int getValor() {
    return valor;
  }
}