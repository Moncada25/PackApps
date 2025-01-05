package com.bookverse.packapps.apps.guessnumber;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.bookverse.packapps.utils.constants.DatabaseConstants;
import com.bookverse.packapps.utils.constants.Styles;
import com.bookverse.packapps.utils.ui.Resources;
import com.bookverse.packapps.utils.ui.Alerts;
import com.bookverse.packapps.utils.Format;
import com.bookverse.packapps.utils.ui.Effects;
import com.bookverse.packapps.utils.ui.factory.Button;
import com.bookverse.packapps.utils.ui.factory.Label;

public class GuessNumberView extends JDialog {

  private transient GuessNumberService service = new GuessNumberService();
  private transient GuessNumberViewModel model = null;

  public GuessNumberView(JFrame parent, boolean modal, boolean isHard) {
    super(parent, modal);
    service.setHard(isHard);
    createComponents();
  }

  public GuessNumberView(JDialog parent, boolean modal, boolean isHard) {
    super(parent, modal);
    service.setHard(isHard);
    createComponents();
  }

  public void start(Component parent) {

    if (service.isHard()) {
      setTitle(DatabaseConstants.GUESS_NUMBER + " - Level Hard");
    } else {
      setTitle(DatabaseConstants.GUESS_NUMBER + " - Level Easy");
    }

    setSize(430, 330);
    setResizable(false);
    setLocationRelativeTo(parent);
    Effects.fadeIn(this);
    parent.setVisible(false);
    Alerts.instruccionesAdivinar();
    setVisible(true);
  }

  private void createComponents() {

    setLayout(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(Resources.getImage("adivinar.png")).getImage());

    JLabel guessNumberTitle = new Label().setText("")
        .setColor(Styles.MAIN_COLOR)
        .setFont(Styles.BIG)
        .build();
    guessNumberTitle.setBounds(65, 5, 300, 80);
    add(guessNumberTitle);

    JTextField txtNumber = new JTextField();
    txtNumber.setBounds(170, 190, 80, 35);
    txtNumber.setHorizontalAlignment(SwingConstants.CENTER);
    add(txtNumber);
    txtNumber.setVisible(false);
    txtNumber.addKeyListener(new KeyAdapter() {

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          service.tryToGuess(model);
        }
      }

      @Override
      public void keyTyped(KeyEvent e) {
        Format.onlyNumbers(e.getKeyChar(), e, txtNumber.getText(), 6);
      }
    });

    JLabel response = new Label()
        .setText("")
        .setColor(Styles.TEXT_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    response.setBounds(70, 110, 300, 70);
    add(response);

    JLabel help = new Label()
        .setText("")
        .setColor(Styles.TEXT_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    help.setBounds(70, 110, 300, 70);
    add(help);

    JLabel question = new Label().setText("").build();
    question.setBounds(30, 200, 80, 80);
    add(question);

    JButton btnReturn = new Button()
        .setText("Return")
        .setColor(Styles.MAIN_COLOR)
        .build();
    btnReturn.setBounds(310, 245, 86, 30);
    btnReturn.addActionListener(e -> Effects.fadeOut(this));
    add(btnReturn);

    JButton btnPlay = new Button()
        .setText("Play")
        .setColor(Styles.TEXT_COLOR)
        .build();
    btnPlay.addActionListener(e -> service.clickOnPlay(model));
    btnPlay.setBounds(310, 200, 86, 30);
    add(btnPlay);

    model = new GuessNumberViewModel(
        btnPlay,
        btnReturn,
        guessNumberTitle,
        response,
        help,
        question,
        txtNumber
    );
  }
}