package com.bookverse.packapps.apps.dices;

import java.awt.Component;
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
import com.bookverse.packapps.utils.constants.DatabaseConstants;
import com.bookverse.packapps.utils.ui.Resources;
import com.bookverse.packapps.utils.ui.Alerts;
import com.bookverse.packapps.utils.ui.Effects;
import com.bookverse.packapps.utils.Format;
import com.bookverse.packapps.utils.constants.Styles;
import com.bookverse.packapps.utils.ui.factory.Button;

public class DicesGameView extends JDialog {

  private transient DicesGameService service = new DicesGameService();
  private transient DicesGameViewModel model = null;

  public DicesGameView(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public DicesGameView(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(Component parent) {
    setSize(450, 400);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle(DatabaseConstants.DICES + ", throw them!");
    Effects.fadeIn(this);
    parent.setVisible(false);
    Alerts.instruccionesDados();
    setVisible(true);
  }

  private void createComponents() {
    setLayout(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(Resources.getImage("dice.png")).getImage());

    JButton btnExit = new Button().setText("Return").setColor(Styles.MAIN_COLOR).build();
    btnExit.setBounds(330, 320, 86, 30);
    btnExit.addActionListener(e -> Effects.fadeOut(this));
    add(btnExit);

    JButton btnThrow = new Button().setText("Throw").setColor(Styles.TEXT_COLOR).build();
    btnThrow.setBounds(25, 320, 86, 30);
    btnThrow.addActionListener(e -> service.clickOnThrow(model));
    add(btnThrow);

    JButton btnReset = new Button().setText("Reset").setColor(Styles.TEXT_COLOR).build();
    btnReset.setBounds(185, 320, 86, 30);
    btnReset.setEnabled(false);
    btnReset.addActionListener(e -> service.btnResetAP(model));
    add(btnReset);

    List<JLabel> dices = new ArrayList<>();
    List<JTextField> players = new ArrayList<>();
    List<JLabel> lblPoints = new ArrayList<>();

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
    createPlayer(players, player1);

    JLabel lblPoints1 = Resources.getLabel("", Styles.MAIN_COLOR, this, Styles.MEDIUM);
    lblPoints1.setBounds(25, 185, 100, 30);
    lblPoints.add(lblPoints1);

    JTextField player2 = new JTextField("Player 2");
    player2.setBounds(170, 150, 100, 30);
    createPlayer(players, player2);

    JLabel lblPoints2 = Resources.getLabel("", Styles.MAIN_COLOR, this, Styles.MEDIUM);
    lblPoints2.setBounds(170, 185, 100, 30);
    lblPoints.add(lblPoints2);

    JTextField player3 = new JTextField("Player 3");
    player3.setBounds(317, 150, 100, 30);
    createPlayer(players, player3);

    JLabel lblPoints3 = Resources.getLabel("", Styles.MAIN_COLOR, this, Styles.MEDIUM);
    lblPoints3.setBounds(317, 185, 100, 30);
    lblPoints.add(lblPoints3);

    model = new DicesGameViewModel(dices, players, lblPoints, btnExit, btnThrow, btnReset, this);
  }

  private void createPlayer(List<JTextField> players, JTextField player) {
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
}