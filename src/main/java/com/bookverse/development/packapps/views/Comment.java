package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.Settings.BIG;
import static com.bookverse.development.packapps.core.Settings.MAIN_COLOR;
import static com.bookverse.development.packapps.core.Settings.MEDIUM;
import static com.bookverse.development.packapps.core.Settings.TEXT_COLOR;
import static com.bookverse.development.packapps.utils.DatabaseConstants.FEEDBACK;

import com.bookverse.development.packapps.core.Resources;
import com.bookverse.development.packapps.core.Settings;
import com.bookverse.development.packapps.models.Database;
import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.Format;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Comment extends JDialog implements ActionListener, MouseListener {

  private JLabel required;
  private JButton btnSend, btnExit;
  private JTextArea text;
  private JTextField txtUser;
  private Resources resources = new Resources();

  public Comment(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JFrame parent) {
    setSize(485, 480);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Send Commentary");
    Settings.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void createComponents() {

    setLayout(null);
    setIconImage(new ImageIcon(resources.getImage("coment.png")).getImage());
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    btnSend = resources.getButton("Send", TEXT_COLOR, this, this);
    btnSend.setBounds(140, 400, 86, 30);

    btnExit = resources.getButton("Return", MAIN_COLOR, this, this);
    btnExit.setBounds(250, 400, 86, 30);

    JLabel title = resources
        .getLabel("<html><strong><em>Write commentary</em></strong></html>",
            MAIN_COLOR, this, BIG);
    title.setBounds(130, 10, 370, 50);

    JLabel lblUser = resources
        .getLabel("<html><strong>User</strong></html>", TEXT_COLOR, this,
            MEDIUM);
    lblUser.setBounds(100, 60, 100, 50);

    required = resources.getLabel("*", MAIN_COLOR, this, MEDIUM);
    required.setBounds(139, 78, 12, 12);
    required.addMouseListener(this);

    txtUser = new JTextField();
    txtUser.setBounds(30, 100, 190, 30);
    txtUser.setHorizontalAlignment(JTextField.CENTER);
    add(txtUser);

    txtUser.addKeyListener(new KeyAdapter() {

      public void keyTyped(KeyEvent e) {
        userKeyTyped(e);
      }

      private void userKeyTyped(KeyEvent e) {
        Format.onlyAlfa(e.getKeyChar(), e, txtUser.getText(), 20);
      }
    });

    JLabel message = resources
        .getLabel("<html><strong>Message</strong></html>", TEXT_COLOR, this, MEDIUM);
    message.setBounds(30, 150, 370, 30);

    text = new JTextArea();
    JScrollPane scroll = new JScrollPane(text);
    scroll.setBounds(30, 180, 420, 200);
    add(scroll);
  }

  private void sendCommentary(String username, String commentary) {

    if (Settings.verifyConnection("Make sure you are connected to a network", true)) {

      String[] data = {FEEDBACK, username, commentary, Settings.getDate()};

      if (Database.insertData(data)) {

        JOptionPane.showMessageDialog(null,
            "<html>" + Format.style()
                + "<strong><center>Commentary sent</center></strong><br>"
                + "Feedback sent, your opinion will be taken into account."
                + "</html>",
            "Successfully", JOptionPane.PLAIN_MESSAGE);

        txtUser.setText("");
        text.setText("");
      }
    }
  }

  private void btnSendAP() {

    if (txtUser.getText().equals("")) {

      JOptionPane.showMessageDialog(null,
          "<html>" + Format.style() + "<strong>Username empty</strong></html>",
          "Verify!!",
          JOptionPane.PLAIN_MESSAGE);
      txtUser.requestFocus();

    } else {

      if (text.getText().trim().length() < 3) {
        Alerts.message("Verify", "Message too short");
        text.requestFocus();
      } else {
        sendCommentary(txtUser.getText(), text.getText());
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnSend) {
      btnSendAP();
    } else if (e.getSource() == btnExit) {
      Settings.fadeOut(this);
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

    if (e.getSource() == required) {
      JOptionPane.showMessageDialog(null,
          "<html>" + Format.style()
              + "<strong><center>Complete this field</center></strong><br>"
              + "Username required to send your message,<br>"
              + "the commentary will can be read by the developer.</html>",
          "Required field!", JOptionPane.PLAIN_MESSAGE);
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent arg0) {
  }

  @Override
  public void mousePressed(MouseEvent arg0) {
  }

  @Override
  public void mouseReleased(MouseEvent arg0) {
  }
}