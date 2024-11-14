package com.bookverse.development.packapps.apps.puzzle;

import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import com.bookverse.development.packapps.utils.Timer;
import com.bookverse.development.packapps.utils.constants.DatabaseConstants;
import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.ui.Effects;
import com.bookverse.development.packapps.utils.ui.factory.Button;
import com.bookverse.development.packapps.utils.ui.factory.Label;

public class PuzzleView extends JDialog {

  private transient PuzzleService service = new PuzzleService();
  private transient PuzzleViewModel model = null;
  private transient Timer timer;
  private JLabel lblTimer;

  public PuzzleView(JFrame parent, boolean modal, Levels level) {
    super(parent, modal);
    service.setSize(level.getSize());
    service.setMinutesTimer(level.getMinutes());
    service.setSide(level.getSide());
    createComponents();
  }

  public PuzzleView(JDialog parent, boolean modal, Levels level) {
    super(parent, modal);
    service.setSize(level.getSize());
    service.setMinutesTimer(level.getMinutes());
    service.setSide(level.getSide());
    createComponents();
  }

  public void start(Component parent) {
    setSize(490, 380);
    setResizable(false);
    setLocationRelativeTo(parent);
    if (service.getSize() == 4) {
      setTitle(DatabaseConstants.PUZZLE + " - Level Easy");
    } else if (service.getSize() == 5) {
      setTitle(DatabaseConstants.PUZZLE + " - Level Medium");
    } else {
      setTitle(DatabaseConstants.PUZZLE + " - Level Hard");
    }
    Effects.fadeIn(this);
    parent.setVisible(false);
    Alerts.instruccionesRompe();
    setVisible(true);
  }

  private void createComponents() {

    setLayout(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(Resources.getImage("rompecabezas.png")).getImage());

    JButton btnExit = new Button().setText("Return").setColor(Styles.MAIN_COLOR).build();
    btnExit.setBounds(330, 300, 86, 30);
    btnExit.addActionListener(e -> Effects.fadeOut(this));
    add(btnExit);

    JButton btnPlay = new Button().setText("Play").setColor(Styles.TEXT_COLOR).build();
    btnPlay.setBounds(70, 300, 86, 30);
    btnPlay.addActionListener(e -> {
      service.btnPlayAP(model);
      startChronometer();
    });
    add(btnPlay);

    JButton btnStop = new Button().setText("Stop").setColor(Styles.MAIN_COLOR).build();
    btnStop.setBounds(200, 300, 86, 30);
    btnStop.setEnabled(false);
    btnStop.addActionListener(e -> {
      stopChronometer();
      service.btnResetAP(model);
    });
    add(btnStop);

    JLabel lblTurn = new Label().setText("").setColor(Styles.TEXT_COLOR).setFont(Styles.MEDIUM)
        .build();
    lblTurn.setBounds(250, 90, 200, 150);
    add(lblTurn);

    lblTimer = Resources.getLabel("", Styles.MAIN_COLOR, this,
        new Font("Times New Roman", Font.PLAIN, 45));
    lblTimer.setBounds(250, 5, 200, 80);

    JButton[][] board = new JButton[service.getSize()][service.getSize()];

    int x = 15;
    int y = 15;

    for (int f = 0; f < board.length; f++) {
      for (int c = 0; c < board.length; c++) {
        board[f][c] = new Button().setText(".").build();
        board[f][c].setBounds(x, y, service.getSide(), service.getSide());
        board[f][c].setEnabled(false);
        board[f][c].addActionListener(e -> {
          service.clickOnBoard(e, model);
          if (service.validateVictory(model)) {
            stopChronometer();
          }
        });
        add(board[f][c]);

        x = x + service.getSide();
      }
      x = 15;
      y = y + service.getSide();
    }

    model = new PuzzleViewModel(board, lblTurn, btnPlay, btnStop, btnExit, lblTimer);
  }

  public void startChronometer() {
    timer = new Timer(
        service.getMinutesTimer(),
        service.getSecondsTimer(),
        () -> service.btnResetAP(model),
        timerText -> lblTimer.setText(timerText)
    );
    timer.start();
  }

  public void stopChronometer() {
    timer.stop();
    service.setSecondsTimer(0);
    service.setMinutesTimer(2);
  }
}