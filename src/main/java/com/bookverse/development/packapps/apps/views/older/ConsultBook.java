package com.bookverse.development.packapps.apps.views.older;

import static com.bookverse.development.packapps.apps.utils.constants.Styles.BIG;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.MEDIUM;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.TEXT_COLOR;
import static java.awt.Event.ENTER;
import static javax.swing.SwingConstants.CENTER;

import com.bookverse.development.packapps.automation.runners.SearchBookTest;
import com.bookverse.development.packapps.automation.utils.SetUser;
import com.bookverse.development.packapps.apps.utils.constants.Styles;
import com.bookverse.development.packapps.apps.utils.ui.Resources;
import com.bookverse.development.packapps.apps.repositories.OlderRepository;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;
import com.bookverse.development.packapps.apps.utils.other.Format;
import com.bookverse.development.packapps.apps.utils.ui.Effects;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.stream.IntStream;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import org.junit.runner.JUnitCore;

public class ConsultBook extends JDialog implements ActionListener {

  private final JComboBox<String> listBooksBox = new JComboBox<>();
  private JTextField txtUser;
  private JButton btnRun, btnReturn;
  private JPasswordField txtPassword;

  public ConsultBook(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JFrame parent) {
    setSize(460, 300);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Bookverse Test");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void createComponents() {

    setLayout(null);
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

    btnRun = Resources.getButton("Run", TEXT_COLOR, this, this);
    btnRun.setBounds(60, 215, 100, 30);

    btnReturn = Resources.getButton("Return", MAIN_COLOR, this, this);
    btnReturn.setBounds(300, 215, 86, 30);

    JLabel title = Resources
        .getLabel("<html><strong><em>Search book</em></strong></html>", MAIN_COLOR, this,
            BIG);
    title.setBounds(95, 5, 250, 40);

    JLabel user = Resources
        .getLabel("<html><strong>Username</strong></html>", TEXT_COLOR, this, MEDIUM);
    user.setBounds(30, 60, 120, 30);

    txtUser = new JTextField();
    txtUser.setBounds(250, 65, 120, 30);
    txtUser.setHorizontalAlignment(CENTER);
    add(txtUser);

    txtUser.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtUserKeyTyped(evt);
      }

      private void txtUserKeyTyped(KeyEvent evt) {
        Format.onlyAlfa(evt.getKeyChar(), evt, txtUser.getText(), 20);
      }
    });

    JLabel password = Resources
        .getLabel("<html><strong>Password</strong></html>", TEXT_COLOR, this, MEDIUM);
    password.setBounds(30, 113, 120, 30);

    txtPassword = new JPasswordField();
    txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
    txtPassword.setBounds(250, 115, 120, 30);
    add(txtPassword);

    txtPassword.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == ENTER) {
          btnRunAP();
        }
      }

      public void keyTyped(KeyEvent evt) {
        txtCodKeyTyped(evt);
      }

      private void txtCodKeyTyped(KeyEvent evt) {
        Format.onlyAlfa(evt.getKeyChar(), evt, String.valueOf(txtPassword.getPassword()), 30);
      }
    });

    List<String> listBooks = OlderRepository.getListBook();

    IntStream.range(0, listBooks.size()).forEach(i -> listBooksBox.addItem(listBooks.get(i)));

    listBooksBox.setFont(Styles.SMALL);
    listBooksBox.setBounds(95, 165, 260, 30);
    ((JLabel) listBooksBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    add(listBooksBox);
  }

  private void btnReturnAP() {
    txtUser.setText("");
    txtPassword.setText("");
    txtUser.setEnabled(true);
    txtPassword.setEnabled(true);
    Effects.fadeOut(this);
  }

  private void btnRunAP() {

    if (txtUser.getText().length() >= 4 && String.valueOf(txtPassword.getPassword()).length() >= 4) {
      Resources.setObject(SetUser.toLogin(
          txtUser.getText(),String.valueOf(txtPassword.getPassword()),String.valueOf(listBooksBox.getSelectedItem()))
      );
      JUnitCore.runClasses(SearchBookTest.class);
    } else {
      Alerts.inputSomethingText();
      txtUser.requestFocus();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnReturn) {
      btnReturnAP();
    } else if (e.getSource() == btnRun) {
      btnRunAP();
    }
  }
}