package com.bookverse.development.packapps.apps.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import com.bookverse.development.packapps.apps.services.WhatsAppService;
import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.utils.ui.KeyBindingsUtil;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.other.Format;
import com.bookverse.development.packapps.utils.ui.Effects;

public class WhatsAppView extends JDialog implements ActionListener {

  private JTextArea message;
  private JTextField txtNumber;
  private JButton btnOpen;
  private JButton btnReturn;
  private JComboBox<String> listCountry = new JComboBox<>();

  public WhatsAppView(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
    KeyBindingsUtil.addCopyPasteKeyBindings(message, null, null);
  }

  public void start(JFrame parent) {
    setSize(460, 360);
    setResizable(true);
    setLocationRelativeTo(parent);
    setTitle("Open conversation on WhatsApp");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void createComponents() {
    setLayout(new GridBagLayout());
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    btnOpen = Resources.getButton("Open", Styles.TEXT_COLOR, this, this);
    btnReturn = Resources.getButton("Return", Styles.MAIN_COLOR, this, this);

    JLabel lblPhone = Resources.getLabel("<html><strong>Phone</strong></html>", Styles.TEXT_COLOR, this, Styles.MEDIUM);
    txtNumber = new JTextField();
    txtNumber.setHorizontalAlignment(SwingConstants.CENTER);
    txtNumber.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
          message.requestFocus();
        }
      }

      @Override
      public void keyTyped(KeyEvent evt) {
        Format.onlyNumbers(evt.getKeyChar(), evt, txtNumber.getText(), 20);
      }
    });

    JLabel lblMessage = Resources.getLabel("<html><strong>Message</strong></html>", Styles.TEXT_COLOR, this, Styles.MEDIUM);
    message = new JTextArea();
    JScrollPane scroll = new JScrollPane(message);
    message.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
          WhatsAppService.clickOnOpen(txtNumber, listCountry, message);
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
    listCountry.setFont(Styles.MEDIUM);
    ((JLabel) listCountry.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    add(lblPhone, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 2;
    add(txtNumber, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    add(lblMessage, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    add(scroll, gbc);

    JPanel footerPanel = new JPanel(new GridBagLayout());
    GridBagConstraints footerGbc = new GridBagConstraints();
    footerGbc.insets = new Insets(5, 5, 5, 5);
    footerGbc.gridx = 0;
    footerGbc.gridy = 0;
    footerGbc.gridwidth = 2;
    footerPanel.add(listCountry, footerGbc);

    footerGbc.gridx = 0;
    footerGbc.gridy = 1;
    footerGbc.gridwidth = 1;
    footerPanel.add(btnOpen, footerGbc);

    footerGbc.gridx = 1;
    footerPanel.add(btnReturn, footerGbc);

    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 2;
    gbc.fill = GridBagConstraints.NONE;
    add(footerPanel, gbc);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnReturn) {
      WhatsAppService.clickOnReturn(txtNumber, this);
    } else if (e.getSource() == btnOpen) {
      WhatsAppService.clickOnOpen(txtNumber, listCountry, message);
    }
  }
}