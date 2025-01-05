package com.bookverse.packapps.apps.hangman;

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
import com.bookverse.packapps.utils.Timer;
import com.bookverse.packapps.utils.constants.DatabaseConstants;
import com.bookverse.packapps.utils.constants.Styles;
import com.bookverse.packapps.utils.ui.Resources;
import com.bookverse.packapps.utils.ui.Alerts;
import com.bookverse.packapps.utils.ui.Effects;
import com.bookverse.packapps.utils.ui.factory.Button;
import com.bookverse.packapps.utils.ui.factory.Label;

public class HangmanView extends JDialog {

  private transient HangmanService service = new HangmanService();
  private transient HangmanViewModel model = null;
  private transient Timer timer;
  private JLabel lblTimer;

  public HangmanView(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public HangmanView(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
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

  private void createComponents() {
    JButton btnExit;

    setLayout(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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

    JLabel title = new Label().setText("Category")
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

    lblTimer = new Label().setText("").setColor(Styles.MAIN_COLOR).setFont(Styles.BIG).build();
    lblTimer.setBounds(75, 220, 200, 120);
    add(lblTimer);

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

    service.categories.forEach(options::addItem);

    DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
    comboBoxModel.addElement("Select a option");
    service.categories.forEach(comboBoxModel::addElement);
    options.setModel(comboBoxModel);

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
        lblTimer,
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

  public void startChronometer() {
    timer = new Timer(
        service.getMinutesTimer(),
        service.getSecondsTimer(),
        () -> {
          Alerts.message("You lose!", "Right word: " + service.getRandomWord());
          service.insert("Loser", model);
          service.reset(model);
        },
        timerText -> lblTimer.setText(timerText)
    );
    timer.start();
  }

  public void stopChronometer() {
    timer.stop();
    service.setSecondsTimer(0);
    service.setMinutesTimer(2);
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