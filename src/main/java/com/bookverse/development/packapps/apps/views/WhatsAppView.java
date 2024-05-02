package com.bookverse.development.packapps.apps.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.bookverse.development.packapps.apps.utils.ui.Resources;
import com.bookverse.development.packapps.apps.utils.other.Format;
import com.bookverse.development.packapps.apps.utils.ui.Effects;

import static com.bookverse.development.packapps.apps.services.WhatsAppService.clickOnOpen;
import static com.bookverse.development.packapps.apps.services.WhatsAppService.clickOnReturn;

import static java.awt.Event.ENTER;
import static javax.swing.SwingConstants.CENTER;

import static com.bookverse.development.packapps.apps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.MEDIUM;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.TEXT_COLOR;

public class WhatsAppView extends JDialog implements ActionListener {

  
  private JTextArea message;
  private JTextField txtNumber;
  private JButton btnOpen, btnReturn;
  private JComboBox<String> listCountry = new JComboBox<>();

  public WhatsAppView(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JFrame parent) {
    setSize(460, 360);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Open conversation on WhatsApp");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void createComponents() {

    setLayout(null);
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

    btnOpen = Resources.getButton("Open", TEXT_COLOR, this, this);
    btnOpen.setBounds(60, 290, 100, 30);

    btnReturn = Resources.getButton("Return", MAIN_COLOR, this, this);
    btnReturn.setBounds(300, 290, 86, 30);

    JLabel lblPhone = Resources
        .getLabel("<html><strong>Phone</strong></html>", TEXT_COLOR, this, MEDIUM);
    lblPhone.setBounds(204, 12, 60, 30);

    txtNumber = new JTextField();
    txtNumber.setBounds(155, 45, 150, 30);
    txtNumber.setHorizontalAlignment(CENTER);
    add(txtNumber);

    txtNumber.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == ENTER) {
          message.requestFocus();
        }
      }

      public void keyTyped(KeyEvent evt) {
        txtUserKeyTyped(evt);
      }

      private void txtUserKeyTyped(KeyEvent evt) {
        Format.onlyNumbers(evt.getKeyChar(), evt, txtNumber.getText(), 20);
      }
    });

    JLabel lblMessage = Resources
        .getLabel("<html><strong>Message</strong></html>", TEXT_COLOR, this, MEDIUM);
    lblMessage.setBounds(195, 80, 85, 30);

    message = new JTextArea();
    JScrollPane scroll = new JScrollPane(message);
    scroll.setBounds(10, 118, 432, 100);
    add(scroll);

    message.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == ENTER) {
          clickOnOpen(txtNumber, listCountry, message);
        }
      }
    });

    listCountry.addItem("Select country");
    listCountry.addItem("Argentina");
    listCountry.addItem("Bolivia");
    listCountry.addItem("Brasil");
    listCountry.addItem("Colombia");
    listCountry.addItem("Costa Rica");
    listCountry.addItem("Chile");
    listCountry.addItem("Ecuador");
    listCountry.addItem("España");
    listCountry.addItem("Estados Unidos");
    listCountry.addItem("Venezuela");

    listCountry.setFont(MEDIUM);
    listCountry.setBounds(130, 240, 200, 30);
    ((JLabel) listCountry.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    add(listCountry);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnReturn) {
      clickOnReturn(txtNumber, this);
    } else if (e.getSource() == btnOpen) {
      clickOnOpen(txtNumber, listCountry, message);
    }
  }
}
