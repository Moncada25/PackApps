package com.bookverse.packapps.apps.tasks;

import com.bookverse.packapps.utils.LoadingUtil;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
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
import com.bookverse.packapps.utils.constants.Styles;
import com.bookverse.packapps.repositories.OlderRepository;
import com.bookverse.packapps.utils.Format;
import com.bookverse.packapps.utils.ui.Effects;
import com.bookverse.packapps.utils.ui.factory.Button;
import com.bookverse.packapps.utils.ui.factory.Label;

public class SearchBookView extends JDialog {

  private transient SearchBookService service = new SearchBookService();
  private JComboBox<String> books = new JComboBox<>();

  public SearchBookView(JFrame parent, boolean modal) {
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

    JLabel searchBookTitle = new Label().setText("Search book").setColor(Styles.MAIN_COLOR)
        .setFont(Styles.BIG).build();
    searchBookTitle.setBounds(95, 5, 250, 40);
    add(searchBookTitle);

    JLabel user = new Label().setText("Username").setColor(Styles.TEXT_COLOR).setFont(Styles.MEDIUM)
        .build();
    user.setBounds(30, 60, 120, 30);
    add(user);

    JTextField txtUser = new JTextField();
    txtUser.setBounds(250, 65, 120, 30);
    txtUser.setHorizontalAlignment(SwingConstants.CENTER);
    txtUser.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent evt) {
        Format.numbersAndText(evt.getKeyChar(), evt, txtUser.getText(), 20);
      }
    });
    add(txtUser);

    JLabel password = new Label().setText("Password").setColor(Styles.TEXT_COLOR)
        .setFont(Styles.MEDIUM).build();
    password.setBounds(30, 113, 120, 30);
    add(password);

    JPasswordField txtPassword = new JPasswordField();
    txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
    txtPassword.setBounds(250, 115, 120, 30);
    txtPassword.addKeyListener(new KeyAdapter() {

      @Override
      public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
          service.clickOnRun(txtUser, txtPassword, books);
        }
      }

      @Override
      public void keyTyped(KeyEvent evt) {
        Format.numbersAndText(evt.getKeyChar(), evt, String.valueOf(txtPassword.getPassword()), 30);
      }
    });
    add(txtPassword);

    AtomicReference<List<String>> listBooks = new AtomicReference<>();

    Boolean result = LoadingUtil.executeWithLoading(this, () -> {
      listBooks.set(OlderRepository.getListBook());
      return true;
    });

    if (result != null && result) {
      IntStream.range(0, listBooks.get().size()).forEach(i ->
          books.addItem(listBooks.get().get(i))
      );
    }

    books.setFont(Styles.SMALL);
    books.setBounds(50, 165, 320, 30);
    ((JLabel) books.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    add(books);

    JButton btnRun = new Button().setText("Run").setColor(Styles.TEXT_COLOR).build();
    btnRun.setBounds(60, 215, 100, 30);
    btnRun.addActionListener(e -> service.clickOnRun(txtUser, txtPassword, books));
    add(btnRun);

    JButton btnReturn = new Button().setText("Return").setColor(Styles.MAIN_COLOR).build();
    btnReturn.setBounds(300, 215, 86, 30);
    btnReturn.addActionListener(e -> {
      txtUser.setText("");
      txtPassword.setText("");
      txtUser.setEnabled(true);
      txtPassword.setEnabled(true);
      Effects.fadeOut(this);
    });
    add(btnReturn);
  }
}