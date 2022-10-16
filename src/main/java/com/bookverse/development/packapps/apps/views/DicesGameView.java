package com.bookverse.development.packapps.apps.views;

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

import org.jetbrains.annotations.NotNull;

import com.bookverse.development.packapps.apps.utils.ui.Resources;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;
import com.bookverse.development.packapps.apps.utils.ui.Effects;
import com.bookverse.development.packapps.apps.utils.other.Format;

import static com.bookverse.development.packapps.apps.services.DicesGameService.btnResetAP;
import static com.bookverse.development.packapps.apps.services.DicesGameService.clickOnThrow;

import static com.bookverse.development.packapps.apps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.MEDIUM;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.TEXT_COLOR;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.DICES;

public class DicesGameView extends JDialog implements ActionListener {

  private JLabel dice1, dice2, dice3, lblPoints1, lblPoints2, lblPoints3;
  private JButton btnExit, btnThrow, btnReset;
  private JTextField player1, player2, player3;
  private Resources resources = new Resources();

  public DicesGameView(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public DicesGameView(JDialog parent, boolean modal) {
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

  public void start(JFrame parent) {
    setSize(450, 400);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle(DICES + ", throw them!");
    Effects.fadeIn(this);
    parent.setVisible(false);
    Alerts.instruccionesDados();
    setVisible(true);
  }

  public void start(JDialog parent) {
    setSize(450, 400);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle(DICES + ", throw them!");
    Effects.fadeIn(this);
    parent.setVisible(false);
    Alerts.instruccionesDados();
    setVisible(true);
  }

  @Override
  public void actionPerformed(@NotNull ActionEvent e) {

    if (e.getSource() == btnThrow) {
      clickOnThrow(player1, player2, player3, btnExit, btnThrow, btnReset,
          lblPoints1, lblPoints2, lblPoints3, dice1, dice2, dice3, this
      );
    }

    if (e.getSource() == btnExit) {
      Effects.fadeOut(this);
    }

    if (e.getSource() == btnReset) {
      btnResetAP(player1, player2, player3, btnExit, btnThrow, btnReset,
          lblPoints1, lblPoints2, lblPoints3, dice1, dice2, dice3, this);
    }
  }
}