package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.utils.constants.Styles.BIG;
import static com.bookverse.development.packapps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.utils.constants.Styles.MEDIUM;
import static com.bookverse.development.packapps.utils.constants.Styles.TEXT_COLOR;
import static com.bookverse.development.packapps.utils.constants.DatabaseConstants.HANGMAN;
import static java.awt.Font.PLAIN;

import com.bookverse.development.packapps.utils.GeneralUtilities;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.apps.repositories.Database;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.constants.ArrayData;
import com.bookverse.development.packapps.utils.Format;
import com.bookverse.development.packapps.utils.ui.Effects;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;
import java.util.stream.IntStream;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Hangman extends JDialog implements ActionListener, KeyListener, Runnable,
    MouseListener {

  private JComboBox<String> options;
  private JLabel attempts, time, lyricsNumber, lyricsPressed, image;
  private JTextField txtWord;
  private JButton btnPlay, btnExit;
  private Resources resources = new Resources();
  private int countAttempts = 0;
  private boolean lyricsEquals = false, chronometerActive;
  private String lines = "", randomWord, lyrics = "";
  private char[] secretWord = new char[13];
  private char[] word = new char[13];

  public Hangman(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public Hangman(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {

    setLayout(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(resources.getImage("ahorcado.png")).getImage());

    btnPlay = resources.getButton("Play", TEXT_COLOR, this, this);
    btnPlay.setBounds(30, 160, 86, 30);

    btnExit = resources.getButton("Return", MAIN_COLOR, this, this);
    btnExit.setBounds(140, 160, 86, 30);

    JLabel title = resources.getLabel("<html>"
        + "<strong><em>Category</em></strong>" +
        "</html>", MAIN_COLOR, this, BIG);
    title.setBounds(30, 10, 120, 35);

    attempts = resources.getLabel("", TEXT_COLOR, this, MEDIUM);
    attempts.setBounds(30, 200, 250, 30);

    time = resources.getLabel("", MAIN_COLOR, this, new Font("Times New Roman",
        PLAIN, 50));
    time.setBounds(75, 220, 200, 120);

    lyricsPressed = resources.getLabel("", TEXT_COLOR, this, MEDIUM);
    lyricsPressed.setBounds(30, 400, 600, 40);

    options = new JComboBox<>();
    options.setBounds(30, 50, 220, 30);
    options.setFont(MEDIUM);
    add(options);

    options.addItem("Fruits");
    options.addItem("Animals");
    options.addItem("Countries");
    options.addItem("Colors");
    options.addItem("Sports");
    options.addItem("Irregular Verbs");

    options.setModel(new DefaultComboBoxModel<>(new String[]{"Select a option", "Animals",
        "Colors", "Sports", "Fruits", "Irregular Verbs", "Countries"}));

    txtWord = new JTextField();
    txtWord.setBounds(30, 100, 220, 40);
    add(txtWord);

    lyricsNumber = resources.getLabel("", TEXT_COLOR, this, MEDIUM);
    lyricsNumber.setBounds(260, 100, 130, 60);

    addKeyListener(this);
    addMouseListener(this);

    txtWord.setEditable(false);

    image = resources.getLabel("", null, this, null);
    image.setBounds(620, 20, 96, 96);
  }

  public void start(JFrame parent) {
    setSize(750, 500);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle(HANGMAN);
    Effects.fadeIn(this);
    parent.setVisible(false);
    Alerts.instruccionesAhorcado();
    setVisible(true);
  }

  public void start(JDialog parent) {
    setSize(750, 500);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle(HANGMAN);
    Effects.fadeIn(this);
    parent.setVisible(false);
    Alerts.instruccionesAhorcado();
    setVisible(true);
  }

  private void btnPlayAP() {

    if (Objects.requireNonNull(options.getSelectedItem()).toString().equals("Select a option")) {
      Alerts.message("Message", "Select a category");
      countAttempts = 0;
      attempts.setText("");
      txtWord.setText("");
      time.setText("");
    } else {
      startChronometer();
      options.setEnabled(false);
      btnExit.setEnabled(false);
      btnPlay.setEnabled(false);
      randomWord = null;
      countAttempts = 0;
      txtWord.setText("");
      time.setText("");
      attempts.setText("Made mistakes: " + countAttempts);
      lines = "";
      repaint();

      IntStream.rangeClosed(0, word.length - 1).forEach(i -> {
        word[i] = ' ';
        secretWord[i] = ' ';
      });

      String option = options.getSelectedItem().toString();
      int randomInt = (int) (Math.random() * 15);

      int size;
      switch (option) {
        case "Fruits":
          randomWord = ArrayData.getSecretWord(0, randomInt);
          size = randomWord.length() - 1;

          for (int i = 0; i <= size; i++) {
            secretWord[i] = randomWord.charAt(i);
            lines += "_" + " ";
          }

          break;
        case "Animals":
          randomWord = ArrayData.getSecretWord(1, randomInt);
          size = randomWord.length() - 1;

          for (int i = 0; i <= size; i++) {
            secretWord[i] = randomWord.charAt(i);
            lines += "_" + " ";
          }

          break;
        case "Countries":
          randomWord = ArrayData.getSecretWord(2, randomInt);
          size = randomWord.length() - 1;

          for (int i = 0; i <= size; i++) {
            secretWord[i] = randomWord.charAt(i);
            lines += "_" + " ";
          }

          break;
        case "Colors":
          randomWord = ArrayData.getSecretWord(3, randomInt);
          size = randomWord.length() - 1;

          for (int i = 0; i <= size; i++) {
            secretWord[i] = randomWord.charAt(i);
            lines += "_" + " ";
          }

          break;
        case "Irregular Verbs":
          randomWord = ArrayData.getSecretWord(4, randomInt);
          size = randomWord.length() - 1;

          for (int i = 0; i <= size; i++) {
            secretWord[i] = randomWord.charAt(i);
            lines += "_" + " ";
          }

          break;
        case "Sports":
          randomWord = ArrayData.getSecretWord(5, randomInt);
          size = randomWord.length() - 1;

          for (int i = 0; i <= size; i++) {
            secretWord[i] = randomWord.charAt(i);
            lines += "_" + " ";
          }
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + option);
      }

      IntStream.rangeClosed(0, size).forEach(i -> word[i] = lines.charAt(i));

      lyricsNumber.setText("Lyrics: " + randomWord.length());
      txtWord.setText(lines);
    }
  }

  private void compare(char lyricActual) {

    txtWord.setText("");
    String lyric = lines;
    lines = "";

    IntStream.rangeClosed(0, randomWord.length() - 1).forEach(j -> {
      if (randomWord.charAt(j) == lyricActual) {
        word[j] = lyricActual;
        lyricsEquals = true;
      }
      lines += word[j] + " ";
    });

    if (!lyricsEquals) {
      countAttempts++;
      attempts.setText("Made mistakes: " + countAttempts);
      lines = lyric;
    }

    txtWord.setText(lines);

    lyricsEquals = false;

    if (countAttempts == 7) {
      stopChronometer();
      txtWord.setText("");
      lines = "";

      image.setIcon(new ImageIcon(resources.getImage("dead.png")));

      Alerts.message("You lose", "Correct word: " + randomWord);

      insert("Loser");

      reset();
    }

    repaint();
    win();
  }

  private void win() {

    boolean win = false;

    for (int i = 0; i <= secretWord.length - 1; i++) {

      if (word[i] == secretWord[i]) {
        win = true;
      } else {
        win = false;
        break;
      }
    }

    if (win) {

      stopChronometer();

      image.setIcon(new ImageIcon(resources.getImage("win.png")));

      if (countAttempts >= 0 && countAttempts <= 4) {
        Alerts.message("You win!", "Pretty easy, right?");
      } else {
        Alerts.message("You win!", "All right, you made it!");
      }

      insert("Winner");

      reset();
    }
  }

  private void reset() {
    btnPlay.setEnabled(true);
    options.setEnabled(true);
    btnExit.setEnabled(true);
    image.setIcon(null);
    countAttempts = 0;
    lyrics = "";
    options.setSelectedIndex(0);
    attempts.setText("");
    txtWord.setText("");
    lyricsNumber.setText("");
    lyricsPressed.setText("");
    time.setText("");
    stopChronometer();
  }

  private void insert(String state) {

    if (GeneralUtilities.verifyConnection("Data don't saved", true) && Alerts.saveGame()) {

      String[] data = {HANGMAN, Alerts.inputText("Enter a Nickname", 20),
          String.valueOf(countAttempts), state,
          Objects.requireNonNull(options.getSelectedItem()).toString(),
          Format.getDate()};

      Database.insertData(data);
    }
  }

  private void onlyLyrics(char lyric, KeyEvent evt) {
    if ((lyric < 'a' || lyric > 'z') && (lyric != KeyEvent.VK_BACK_SPACE || lyric != ' ')
        && lyric != 'ñ') {
      Alerts.message("Warning", "Only lowercase letters are allowed");
      evt.consume();
    } else {
      String l = Character.toString(lyric);

      if (!lyrics.contains("[" + l + "]")) {
        lyrics += "[" + l + "]";
        lyricsPressed.setText("Lyrics pressed " + lyrics);
      }
      compare(lyric);
    }
  }

  public void keyPressed(KeyEvent e) {
  }

  public void keyReleased(KeyEvent e) {
  }

  public void keyTyped(KeyEvent e) {

    if (!options.getSelectedItem().toString().equals("Select a option") && !txtWord.getText()
        .equals("")) {
      onlyLyrics(e.getKeyChar(), e);
      repaint();
    }
  }

  public boolean isFocusable() {
    return true;
  }

  public void mousePressed(MouseEvent e) {
  }

  public void mouseReleased(MouseEvent e) {
  }

  public void mouseClicked(MouseEvent e) {
    this.requestFocus();
  }

  public void mouseEntered(MouseEvent e) {
    this.requestFocus();
  }

  public void mouseExited(MouseEvent e) {
  }

  public void paint(Graphics g) {
    super.paint(g);
    if (countAttempts == 0) {
      g.setColor(TEXT_COLOR);
      g.drawLine(400, 400, 400, 100);
      g.drawLine(400, 100, 550, 100);
      g.drawLine(550, 150, 550, 100);
    } else if (countAttempts == 1) {
      g.setColor(TEXT_COLOR);
      g.drawLine(400, 400, 400, 100);
      g.drawLine(400, 100, 550, 100);
      g.drawLine(550, 150, 550, 100);
      g.setColor(MAIN_COLOR);
      g.drawOval(500, 150, 100, 100);
    } else if (countAttempts == 2) {
      g.setColor(TEXT_COLOR);
      g.drawLine(400, 400, 400, 100);
      g.drawLine(400, 100, 550, 100);
      g.drawLine(550, 150, 550, 100);
      g.setColor(MAIN_COLOR);
      g.drawOval(500, 150, 100, 100);
      g.drawLine(650, 270, 550, 250);
    } else if (countAttempts == 3) {
      g.setColor(TEXT_COLOR);
      g.drawLine(400, 400, 400, 100);
      g.drawLine(400, 100, 550, 100);
      g.drawLine(550, 150, 550, 100);
      g.setColor(MAIN_COLOR);
      g.drawOval(500, 150, 100, 100);
      g.drawLine(650, 270, 550, 250);
      g.drawLine(450, 270, 550, 250);
    } else if (countAttempts == 4) {
      g.setColor(TEXT_COLOR);
      g.drawLine(400, 400, 400, 100);
      g.drawLine(400, 100, 550, 100);
      g.drawLine(550, 150, 550, 100);
      g.setColor(MAIN_COLOR);
      g.drawOval(500, 150, 100, 100);
      g.drawLine(650, 270, 550, 250);
      g.drawLine(450, 270, 550, 250);
      g.drawLine(550, 320, 550, 250);
    } else if (countAttempts == 5) {
      g.setColor(TEXT_COLOR);
      g.drawLine(400, 400, 400, 100);
      g.drawLine(400, 100, 550, 100);
      g.drawLine(550, 150, 550, 100);
      g.setColor(MAIN_COLOR);
      g.drawOval(500, 150, 100, 100);
      g.drawLine(650, 270, 550, 250);
      g.drawLine(450, 270, 550, 250);
      g.drawLine(550, 320, 550, 250);
      g.drawLine(600, 420, 550, 320);
    } else if (countAttempts == 6) {
      g.setColor(TEXT_COLOR);
      g.drawLine(400, 400, 400, 100);
      g.drawLine(400, 100, 550, 100);
      g.drawLine(550, 150, 550, 100);
      g.setColor(MAIN_COLOR);
      g.drawOval(500, 150, 100, 100);
      g.drawLine(650, 270, 550, 250);
      g.drawLine(450, 270, 550, 250);
      g.drawLine(550, 320, 550, 250);
      g.drawLine(600, 420, 550, 320);
      g.drawLine(500, 420, 550, 320);
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

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnPlay) {
      btnPlayAP();
    } else if (e.getSource() == btnExit) {
      Effects.fadeOut(this);
    }
  }

  @Override
  public void run() {

    int minutes = 0, seconds = 59, thousandths = 1000;
    String min, seg;

    try {

      while (chronometerActive) {
        Thread.sleep(4);
        thousandths -= 4;

        if (thousandths == 0) {
          thousandths = 1000;
          seconds -= 1;

          if (seconds == 0) {

            if (minutes > 0) {
              seconds = 59;
              minutes--;
            }
          }
        }

        if (minutes < 10) {
          min = "0" + minutes;
        } else {
          min = Integer.toString(minutes);
        }

        if (seconds < 10) {
          seg = "0" + seconds;
        } else {
          seg = String.valueOf(seconds);
        }

        time.setText(min + ":" + seg);

        if (minutes <= 0 && seconds <= 0) {
          Alerts.message("You lose!", "Right word: " + randomWord);

          insert("Loser");
          reset();
        }
      }

    } catch (Exception e) {
      Alerts.error(e, HANGMAN);
    }
  }
}