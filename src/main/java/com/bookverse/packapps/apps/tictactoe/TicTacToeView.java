package com.bookverse.packapps.apps.tictactoe;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import com.bookverse.packapps.utils.constants.Styles;
import com.bookverse.packapps.utils.ui.Alerts;
import com.bookverse.packapps.utils.ui.Resources;
import com.bookverse.packapps.utils.ui.Effects;
import com.bookverse.packapps.utils.ui.factory.Button;
import com.bookverse.packapps.utils.ui.factory.Label;

public class TicTacToeView extends JDialog {

  private transient TicTacToeService service = new TicTacToeService();
  private transient TicTacToeViewModel model = new TicTacToeViewModel();

  public TicTacToeView(JFrame parent, boolean modal, boolean vsCPU) {
    super(parent, modal);
    service.setVsCPU(vsCPU);
    createComponents();
  }

  public void start(JFrame parent) {
    setSize(450, 400);
    setResizable(false);
    setLocationRelativeTo(parent);
    if (service.isVsCPU()) {
      setTitle("Player vs CPU");
    } else {
      setTitle("Player vs Player");
    }
    Effects.fadeIn(this);
    parent.setVisible(false);
    Alerts.instruccionesTriqui();
    setVisible(true);
  }

  private void createComponents() {

    setLayout(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(Resources.getImage("tictactoe.png")).getImage());

    JLabel lblNameX = new Label().setText("Player X → ")
        .setColor(model.getColorX())
        .setFont(Styles.MEDIUM)
        .build();
    lblNameX.setBounds(50, 10, 120, 40);
    add(lblNameX);

    JLabel pointsX = new Label().setText("0")
        .setColor(model.getColorX())
        .setFont(Styles.MEDIUM)
        .build();
    pointsX.setBounds(110, 10, 120, 40);
    add(pointsX);
    model.setPointsX(pointsX);

    JLabel lblNameO;
    if (!service.isVsCPU()) {
      lblNameO = new Label().setText("Player O → ")
          .setColor(model.getColorO())
          .setFont(Styles.MEDIUM)
          .build();
      lblNameO.setBounds(255, 10, 120, 40);
    } else {
      lblNameO = new Label().setText("CPU O → ")
          .setColor(model.getColorO())
          .setFont(Styles.MEDIUM)
          .build();
      lblNameO.setBounds(273, 10, 120, 40);
    }
    add(lblNameO);

    JLabel pointsO = new Label().setText("0")
        .setColor(model.getColorO())
        .setFont(Styles.MEDIUM)
        .build();
    pointsO.setBounds(315, 10, 120, 40);
    add(pointsO);
    model.setPointsO(pointsO);

    int x = 50;
    int y = 50;

    for (int f = 0; f < 3; f++) {
      for (int c = 0; c < 3; c++) {
        model.getBoard()[f][c] = new Button().setText("").build();
        model.getBoard()[f][c].setBounds(x, y, 70, 70);
        model.getBoard()[f][c].setFont(new Font("Times New Roman", Font.PLAIN, 45));
        model.getBoard()[f][c].setOpaque(true);
        model.getBoard()[f][c].addActionListener(e -> service.clickOnBoard(e, model));
        add(model.getBoard()[f][c]);
        x = x + 70;
      }
      x = 50;
      y = y + 70;
    }

    JLabel lblTurn = new Label().setText("").setFont(Styles.MEDIUM).build();
    lblTurn.setBounds(245, 200, 200, 100);
    add(lblTurn);
    model.setLblTurn(lblTurn);

    JLabel image = new Label().setText("").build();
    image.setBounds(270, 80, 150, 150);
    add(image);
    model.setImage(image);

    model.getTxtNameX().setText("X");

    if (!service.isVsCPU()) {
      model.getTxtNameO().setText("O");
    } else {
      model.getTxtNameO().setText("CPU");
    }

    JButton btnExit = new Button().setText("Return").setColor(Styles.MAIN_COLOR).build();
    btnExit.setBounds(310, 300, 86, 30);
    btnExit.addActionListener(e -> Effects.fadeOut(this));
    add(btnExit);
    model.setBtnExit(btnExit);

    JButton btnReset = new Button().setText("Reset").setColor(Styles.TEXT_COLOR).build();
    btnReset.setEnabled(false);
    btnReset.setBounds(180, 300, 86, 30);
    btnReset.addActionListener(e -> service.clickOnReset(model));
    add(btnReset);
    model.setBtnReset(btnReset);

    JButton btnPlay = new Button().setText("Play").setColor(Styles.TEXT_COLOR).build();
    btnPlay.setBounds(50, 300, 86, 30);
    btnPlay.addActionListener(e -> service.clickOnPlay(model));
    add(btnPlay);
    model.setBtnPlay(btnPlay);

    model.setParent(this);
  }
}