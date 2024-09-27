package com.bookverse.development.packapps.apps.views;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.jetbrains.annotations.NotNull;
import com.bookverse.development.packapps.apps.services.DicesGameService;
import com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants;
import com.bookverse.development.packapps.apps.utils.ui.Resources;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;
import com.bookverse.development.packapps.apps.utils.ui.Effects;
import com.bookverse.development.packapps.apps.utils.other.Format;
import com.bookverse.development.packapps.apps.utils.constants.Styles;

public class DicesGameView extends JDialog implements ActionListener {

  private List<JLabel> dices;
  private List<JTextField> players;
  private List<JLabel> lblPoints;
  private JButton btnExit;
  private JButton btnThrow;
  private JButton btnReset;

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
    setIconImage(new ImageIcon(Resources.getImage("dado.png")).getImage());

    btnExit = Resources.getButton("Return", Styles.MAIN_COLOR, this, this);
    btnExit.setBounds(330, 320, 86, 30);

    btnThrow = Resources.getButton("Throw", Styles.TEXT_COLOR, this, this);
    btnThrow.setBounds(25, 320, 86, 30);

    btnReset = Resources.getButton("Reset", Styles.TEXT_COLOR, this, this);
    btnReset.setBounds(185, 320, 86, 30);
    btnReset.setEnabled(false);

    dices = new ArrayList<>();
    players = new ArrayList<>();
    lblPoints = new ArrayList<>();

    JLabel dice1 = Resources.getLabel("", null, this, null);
    dice1.setBounds(25, 10, 80, 80);
    dices.add(dice1);

    JLabel dice2 = Resources.getLabel("", null, this, null);
    dice2.setBounds(185, 10, 80, 80);
    dices.add(dice2);

    JLabel dice3 = Resources.getLabel("", null, this, null);
    dice3.setBounds(330, 10, 80, 80);
    dices.add(dice3);

    JTextField player1 = new JTextField("Player 1");
    player1.setBounds(25, 150, 100, 30);
    createPlayer(player1);

    JLabel lblPoints1 = Resources.getLabel("", Styles.MAIN_COLOR, this, Styles.MEDIUM);
    lblPoints1.setBounds(25, 185, 100, 30);
    lblPoints.add(lblPoints1);

    JTextField player2 = new JTextField("Player 2");
    player2.setBounds(170, 150, 100, 30);
    createPlayer(player2);

    JLabel lblPoints2 = Resources.getLabel("", Styles.MAIN_COLOR, this, Styles.MEDIUM);
    lblPoints2.setBounds(170, 185, 100, 30);
    lblPoints.add(lblPoints2);

    JTextField player3 = new JTextField("Player 3");
    player3.setBounds(317, 150, 100, 30);
    createPlayer(player3);

    JLabel lblPoints3 = Resources.getLabel("", Styles.MAIN_COLOR, this, Styles.MEDIUM);
    lblPoints3.setBounds(317, 185, 100, 30);
    lblPoints.add(lblPoints3);
  }

  private void createPlayer(JTextField player) {
    player.setHorizontalAlignment(SwingConstants.CENTER);
    players.add(player);
    add(player);

    player.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        Format.onlyText(e.getKeyChar(), e, player.getText(), 10);
      }
    });
  }

  public void start(JFrame parent) {
    initializeWindow(parent);
  }

  public void start(JDialog parent) {
    initializeWindow(parent);
  }

  private void initializeWindow(Window parent) {
    setSize(450, 400);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle(DatabaseConstants.DICES + ", throw them!");
    Effects.fadeIn(this);
    parent.setVisible(false);
    Alerts.instruccionesDados();
    setVisible(true);
  }

  @Override
  public void actionPerformed(@NotNull ActionEvent e) {

    if (e.getSource() == btnThrow) {
      DicesGameService.clickOnThrow(players, btnExit, btnThrow, btnReset, lblPoints, dices, this);
    }

    if (e.getSource() == btnExit) {
      Effects.fadeOut(this);
    }

    if (e.getSource() == btnReset) {
      DicesGameService.btnResetAP(players, btnExit, btnThrow, btnReset, lblPoints, dices, this);
    }
  }
}