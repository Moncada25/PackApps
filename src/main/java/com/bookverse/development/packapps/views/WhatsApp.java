package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.utils.constants.Styles.MEDIUM;
import static com.bookverse.development.packapps.utils.constants.Styles.TEXT_COLOR;
import static java.awt.Event.ENTER;
import static javax.swing.SwingConstants.CENTER;

import com.bookverse.development.packapps.utils.GeneralUtilities;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.constants.ArrayData;
import com.bookverse.development.packapps.utils.Format;
import com.bookverse.development.packapps.utils.ui.Effects;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
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

public class WhatsApp extends JDialog implements ActionListener {

  private Resources resources = new Resources();
  private JTextArea message;
  private JTextField txtNumber;
  private JButton btnOpen, btnReturn;
  private JComboBox<String> listCountries = new JComboBox<>();

  public WhatsApp(JFrame parent, boolean modal) {
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

    btnOpen = resources.getButton("Open", TEXT_COLOR, this, this);
    btnOpen.setBounds(60, 290, 100, 30);

    btnReturn = resources.getButton("Return", MAIN_COLOR, this, this);
    btnReturn.setBounds(300, 290, 86, 30);

    JLabel lblPhone = resources
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

    JLabel lblMessage = resources
        .getLabel("<html><strong>Message</strong></html>", TEXT_COLOR, this, MEDIUM);
    lblMessage.setBounds(195, 80, 85, 30);

    message = new JTextArea();
    JScrollPane scroll = new JScrollPane(message);
    scroll.setBounds(10, 118, 432, 100);
    add(scroll);

    message.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == ENTER) {
          btnRunAP();
        }
      }
    });

    listCountries.addItem("Select country");
    listCountries.addItem("Argentina");
    listCountries.addItem("Bolivia");
    listCountries.addItem("Brasil");
    listCountries.addItem("Colombia");
    listCountries.addItem("Costa Rica");
    listCountries.addItem("Chile");
    listCountries.addItem("Ecuador");
    listCountries.addItem("España");
    listCountries.addItem("Estados Unidos");
    listCountries.addItem("Venezuela");

    listCountries.setFont(MEDIUM);
    listCountries.setBounds(130, 240, 200, 30);
    ((JLabel) listCountries.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    add(listCountries);
  }

  private void btnReturnAP() {
    txtNumber.setText("");
    txtNumber.setEnabled(true);
    Effects.fadeOut(this);
  }

  private void btnRunAP() {

    if (GeneralUtilities.verifyConnection("Connect to see more!", true)) {

      if (txtNumber.getText().length() >= 8) {

        if (!"Select country".equals(listCountries.getSelectedItem().toString())) {

          try {
            Desktop.getDesktop().browse(new URL(
                "https://web.whatsapp.com/send?phone=" + ArrayData.getCountryCode(listCountries.getSelectedItem().toString()) + txtNumber.getText()
                    + "&text=" + message.getText().replaceAll("\\s", "+")).toURI());
          } catch (Exception ex) {
            Alerts.error(ex, "Opening URL");
          }

        } else {
          Alerts.message("Verify!", "Select a country");
        }

      } else {

        Alerts.message("Verify!", "Phone number too short.");
        txtNumber.requestFocus();
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnReturn) {
      btnReturnAP();
    } else if (e.getSource() == btnOpen) {
      btnRunAP();
    }
  }
}
