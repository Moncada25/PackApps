package com.bookverse.development.packapps.apps.hangman;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import lombok.SneakyThrows;
import com.bookverse.development.packapps.utils.constants.DatabaseConstants;
import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.ui.Effects;
import com.bookverse.development.packapps.utils.ui.factory.Button;
import com.bookverse.development.packapps.utils.ui.factory.Label;
import com.bookverse.development.packapps.utils.other.GeneralUtils;

public class HangmanView extends JDialog implements Runnable {

  private transient HangmanService service = new HangmanService();
  private transient HangmanViewModel model = null;
  private JLabel timer;
  private boolean chronometerActive = false;

  public HangmanView(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public HangmanView(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {
    JButton btnExit;

    setLayout(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(Resources.getImage("ahorcado.png")).getImage());

    JButton btnPlay = new Button().setText("Play").setColor(Styles.TEXT_COLOR).build();
    btnPlay.setBounds(30, 160, 86, 30);
    add(btnPlay);
    btnPlay.addActionListener(e -> {
      if (service.btnPlayAP(model)) {
        startChronometer();
      }
    });

    btnExit = new Button().setText("Return").setColor(Styles.MAIN_COLOR).build();
    btnExit.setBounds(140, 160, 86, 30);
    add(btnExit);
    btnExit.addActionListener(e -> Effects.fadeOut(this));

    JLabel title = new Label().setText("<html><strong><em>Category</em></strong></html>")
        .setColor(Styles.MAIN_COLOR)
        .setFont(Styles.BIG)
        .setColor(Styles.MAIN_COLOR)
        .build();
    title.setBounds(30, 10, 140, 35);
    add(title);

    JLabel attempts = new Label().setText("").setColor(Styles.MAIN_COLOR).setFont(Styles.MEDIUM)
        .build();
    attempts.setBounds(30, 200, 250, 30);
    add(attempts);

    timer = new Label().setText("").setColor(Styles.MAIN_COLOR).setFont(Styles.BIG).build();
    timer.setBounds(75, 220, 200, 120);
    add(timer);

    JLabel lyricsPressed = new Label().setText("")
        .setColor(Styles.TEXT_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    lyricsPressed.setBounds(30, 400, 600, 40);
    add(lyricsPressed);

    JComboBox<String> options = new JComboBox<>();
    options.setBounds(30, 50, 220, 30);
    options.setFont(Styles.MEDIUM);
    add(options);

    options.addItem("Fruits");
    options.addItem("Animals");
    options.addItem("Countries");
    options.addItem("Colors");
    options.addItem("Sports");
    options.addItem("Irregular Verbs");

    options.setModel(new DefaultComboBoxModel<>(new String[]{
        "Select a option",
        "Animals",
        "Colors",
        "Sports",
        "Fruits",
        "Irregular Verbs",
        "Countries"})
    );

    JTextField txtWord = new JTextField();
    txtWord.setBounds(30, 100, 220, 40);
    txtWord.setEditable(false);
    add(txtWord);

    JLabel lyricsNumber = new Label().setText("").setColor(Styles.TEXT_COLOR).setFont(Styles.MEDIUM)
        .build();
    lyricsNumber.setBounds(260, 100, 130, 60);
    add(lyricsNumber);

    JLabel image = Resources.getLabel("", null, this, null);
    image.setBounds(620, 20, 96, 96);
    add(image);

    model = new HangmanViewModel(
        options,
        attempts,
        timer,
        lyricsNumber,
        lyricsPressed,
        image,
        txtWord,
        btnPlay,
        btnExit,
        this
    );

    addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {

        if (!Objects.requireNonNull(options.getSelectedItem()).toString().equals("Select a option") && !txtWord.getText().isEmpty()) {
          if (service.onlyLyrics(e.getKeyChar(), e, model) || service.validateWinner()) {
            stopChronometer();
          }
          repaint();
        }
      }
    });

    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        requestFocus();
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        requestFocus();
      }
    });
  }

  public void start(Container parent) {
    setSize(750, 500);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle(DatabaseConstants.HANGMAN);
    Effects.fadeIn(this);
    parent.setVisible(false);
    Alerts.instruccionesAhorcado(
        service.getMinutesTimer(),
        service.getSecondsTimer(),
        service.getMaxAttempts()
    );
    setVisible(true);
  }

  @Override
  @SneakyThrows
  public void run() {

    while (chronometerActive) {
      String min = (service.getMinutesTimer() < 10)
          ? "0" + service.getMinutesTimer()
          : Integer.toString(service.getMinutesTimer());
      String seg = (service.getSecondsTimer() < 10)
          ? "0" + service.getSecondsTimer()
          : Integer.toString(service.getSecondsTimer());

      timer.setText(min + ":" + seg);

      if (service.getMinutesTimer() == 0 && service.getSecondsTimer() == 0) {
        Alerts.message("You lose!", "Right word: " + service.getRandomWord());
        service.insert("Loser", model);
        service.reset(model);
        stopChronometer();
      }

      GeneralUtils.waitSeconds(1);

      if (service.getSecondsTimer() > 0) {
        service.setSecondsTimer(service.getSecondsTimer() - 1);
      }

      if (service.getSecondsTimer() == 0 && service.getMinutesTimer() > 0) {
        service.setSecondsTimer(59);
        service.setMinutesTimer(service.getMinutesTimer() - 1);
      }
    }
  }

  public void startChronometer() {
    chronometerActive = true;
    Thread timeThread = new Thread(this);
    timeThread.start();
  }

  public void stopChronometer() {
    chronometerActive = false;
    service.setSecondsTimer(0);
    service.setMinutesTimer(2);
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    if (service.getCountAttempts() == 0) {
      g.setColor(Styles.TEXT_COLOR);
      draw(g);
    } else if (service.getCountAttempts() == 1) {
      g.setColor(Styles.TEXT_COLOR);
      draw(g);
      g.setColor(Styles.MAIN_COLOR);
      g.drawOval(500, 150, 100, 100);
    } else if (service.getCountAttempts() == 2) {
      g.setColor(Styles.TEXT_COLOR);
      draw(g);
      g.setColor(Styles.MAIN_COLOR);
      g.drawOval(500, 150, 100, 100);
      g.drawLine(650, 270, 550, 250);
    } else if (service.getCountAttempts() == 3) {
      drawLine(g);
    } else if (service.getCountAttempts() == 4) {
      drawLine(g);
      g.drawLine(550, 320, 550, 250);
    } else if (service.getCountAttempts() == 5) {
      drawLine(g);
      g.drawLine(550, 320, 550, 250);
      g.drawLine(600, 420, 550, 320);
    } else if (service.getCountAttempts() == 6) {
      drawLine(g);
      g.drawLine(550, 320, 550, 250);
      g.drawLine(600, 420, 550, 320);
      g.drawLine(500, 420, 550, 320);
    }
  }

  private void drawLine(Graphics g) {
    g.setColor(Styles.TEXT_COLOR);
    draw(g);
    g.setColor(Styles.MAIN_COLOR);
    g.drawOval(500, 150, 100, 100);
    g.drawLine(650, 270, 550, 250);
    g.drawLine(450, 270, 550, 250);
  }

  private void draw(Graphics g) {
    g.drawLine(400, 400, 400, 100);
    g.drawLine(400, 100, 550, 100);
    g.drawLine(550, 150, 550, 100);
  }
}