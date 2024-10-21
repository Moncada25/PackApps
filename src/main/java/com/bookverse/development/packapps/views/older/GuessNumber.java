package com.bookverse.development.packapps.views.older;

import static com.bookverse.development.packapps.utils.constants.Styles.BIG;
import static com.bookverse.development.packapps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.utils.constants.Styles.MEDIUM;
import static com.bookverse.development.packapps.utils.constants.Styles.TEXT_COLOR;
import static com.bookverse.development.packapps.utils.ui.Effects.fadeIn;
import static com.bookverse.development.packapps.utils.other.Format.getDate;
import static com.bookverse.development.packapps.utils.other.GeneralUtils.getIntRandom;
import static com.bookverse.development.packapps.utils.other.GeneralUtils.verifyConnection;
import static com.bookverse.development.packapps.utils.constants.DatabaseConstants.GUESS_NUMBER;

import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.repositories.OlderRepository;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.other.Format;
import com.bookverse.development.packapps.utils.ui.Effects;
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

public class GuessNumber extends JDialog implements ActionListener {

  
  private JButton btnPlay, btnReturn;
  private JLabel message, response, help, question;
  private JTextField txtNumber;
  private int numberUser, numberRandom;
  private int high;
  private int attempts = 0;
  private boolean isHard;

  public GuessNumber(JFrame parent, boolean modal, boolean isHard) {
    super(parent, modal);
    this.isHard = isHard;
    createComponents();
  }

  public GuessNumber(JDialog parent, boolean modal, boolean isHard) {
    super(parent, modal);
    this.isHard = isHard;
    createComponents();
  }

  private void createComponents() {

    setLayout(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(Resources.getImage("adivinar.png")).getImage());

    btnReturn = Resources.getButton("Return", MAIN_COLOR, this, this);
    btnReturn.setBounds(310, 245, 86, 30);

    btnPlay = Resources.getButton("Play", TEXT_COLOR, this, this);
    btnPlay.setBounds(310, 200, 86, 30);

    txtNumber = new JTextField();
    txtNumber.setBounds(180, 190, 80, 35);
    txtNumber.setHorizontalAlignment(JTextField.CENTER);
    add(txtNumber);
    txtNumber.setVisible(false);

    txtNumber.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        txtNumKeyPressed(e);
      }

      public void txtNumKeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

          try {

            if (!txtNumber.getText().isEmpty()) {
              numberUser = Integer.parseInt(txtNumber.getText());
              guessNumber();
              txtNumber.setText("");

              if (isHard) {
                response.setVisible(false);
              } else {
                help.setVisible(false);
              }

              txtNumber.requestFocus();
            } else {
              help.setVisible(false);
              response.setVisible(true);
              response.setText("<html>"
                  + "<center><strong>Enter a number</strong></center>"
                  + "</html>");
            }

          } catch (Exception exception) {
            Alerts.error(exception, GUESS_NUMBER);
          }
        }
      }

      public void keyTyped(KeyEvent e) {
        txtNumKeyTyped(e);
      }

      public void txtNumKeyTyped(KeyEvent e) {
        Format.onlyNumbers(e.getKeyChar(), e, txtNumber.getText(), 6);
      }
    });

    message = Resources.getLabel("", MAIN_COLOR, this, BIG);
    message.setBounds(90, 5, 320, 100);

    response = Resources.getLabel("", TEXT_COLOR, this, MEDIUM);
    response.setBounds(90, 110, 300, 70);

    help = Resources.getLabel("", TEXT_COLOR, this, MEDIUM);
    help.setBounds(90, 110, 300, 70);

    question = Resources.getLabel("", null, this, null);
    question.setBounds(30, 200, 80, 80);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnReturn) {
      Effects.fadeOut(this);
    } else if (e.getSource() == btnPlay) {
      btnPlayAP();
    }
  }

  private void btnPlayAP() {

    high = Integer.parseInt(Alerts.inputNumber("Maximum number to guess", 6));

    int minimum = 1;
    numberRandom = getIntRandom(minimum, high);

    message.setText("<html><em>"
        + "<center><strong>Guess the number!</strong></center>"
        + "<center>Is between " + minimum + " and " + high + "</center>"
        + "<em></html>");

    help.setText("<html>"
        + "<center><strong>What number am I thinking?</strong></center>"
        + "</html>");
    help.setVisible(true);
    question.setIcon(new ImageIcon(Resources.getImage("x.png")));

    txtNumber.setVisible(true);
    btnPlay.setEnabled(false);

    btnReturn.setEnabled(isHard);

    attempts = 0;

    txtNumber.setText("");
  }

  public void start(JFrame parent) {

    if (isHard) {
      setTitle(GUESS_NUMBER + " - Level Hard");
    } else {
      setTitle(GUESS_NUMBER + " - Level Easy");
    }

    setSize(430, 330);
    setResizable(false);
    setLocationRelativeTo(parent);
    Effects.fadeIn(this);
    parent.setVisible(false);
    Alerts.instruccionesAdivinar();
    setVisible(true);
  }

  public void start(JDialog parent) {

    if (isHard) {
      setTitle(GUESS_NUMBER + " - Level Hard");
    } else {
      setTitle(GUESS_NUMBER + " - Level Easy");
    }

    setSize(430, 330);
    setResizable(false);
    setLocationRelativeTo(parent);
    fadeIn(this);
    parent.setVisible(false);
    Alerts.instruccionesAdivinar();
    setVisible(true);
  }

  private void guessNumber() {

    if (numberUser < numberRandom) {
      attempts++;
      response.setText("<html>"
          + "<center><strong>The number you are looking for is greater</strong></center>"
          + "</html>");

    } else if (numberUser > numberRandom) {
      attempts++;
      response.setText("<html>"
          + "<center><strong>The number you are looking for is smaller</strong></center>"
          + "</html>");
    } else {
      attempts++;
      help.setText("");
      response.setVisible(true);
      response.setBounds(115, 110, 300, 70);
      response.setText("<html>"
          + "<center><strong>You found it!</strong></center>"
          + "<center>The number was " + numberRandom + "</center>"
          + "<center>You did it in " + attempts + " attempts</center>"
          + "</html>");
      btnPlay.setEnabled(true);
      btnReturn.setEnabled(true);

      if (verifyConnection("Data don't saved", true) && Alerts.saveGame()) {

        String level;

        if (isHard) {
          level = "Hard";
        } else {
          level = "Easy";
        }

        try {
          String[] data = {GUESS_NUMBER,
              Alerts.inputText("Enter a Nickname", 20),
              String.valueOf(high), level + " - " + attempts,
              getDate()};
          OlderRepository.insertData(data);
        } catch (Exception e) {
          Alerts.error(e, GUESS_NUMBER);
        }
      }

      txtNumber.setVisible(false);
      message.setText("");
      response.setText("");
      response.setBounds(90, 110, 300, 70);
      question.setIcon(null);
    }
  }
}