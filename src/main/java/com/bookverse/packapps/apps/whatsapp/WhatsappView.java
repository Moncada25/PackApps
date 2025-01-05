package com.bookverse.packapps.apps.whatsapp;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import com.bookverse.packapps.utils.constants.Styles;
import com.bookverse.packapps.utils.ui.KeyBindingsUtil;
import com.bookverse.packapps.utils.ui.Resources;
import com.bookverse.packapps.utils.Format;
import com.bookverse.packapps.utils.ui.Effects;
import com.bookverse.packapps.utils.ui.factory.Button;

public class WhatsappView extends JDialog {

  private transient WhatsappService service = new WhatsappService();

  private JTextArea message;

  public WhatsappView(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
    KeyBindingsUtil.addCopyPasteKeyBindings(message, null, null);
  }

  public void start(JFrame parent) {
    setSize(420, 450);
    setResizable(true);
    setLocationRelativeTo(parent);
    setMinimumSize(getSize());
    setTitle("Open conversation on WhatsApp");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void createComponents() {
    setLayout(new GridBagLayout());
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 20, 10, 20);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    JLabel lblPhone = Resources.getLabel("Phone", Styles.TEXT_COLOR, this, Styles.MEDIUM);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    add(lblPhone, gbc);

    JTextField txtNumber = new JTextField();
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
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 2;
    add(txtNumber, gbc);

    JLabel lblMessage = Resources.getLabel("Message", Styles.TEXT_COLOR, this, Styles.MEDIUM);
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    add(lblMessage, gbc);

    JPanel footerPanel = new JPanel(new GridBagLayout());

    JComboBox<String> countries = new JComboBox<>();
    countries.setFont(Styles.MEDIUM);
    service.fillCountries(countries);
    ((JLabel) countries.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    footerPanel.add(countries, gbc);

    message = new JTextArea();
    JScrollPane scroll = new JScrollPane(message);
    message.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
          service.clickOnOpen(txtNumber, countries, message);
        }
      }
    });
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1.0;
    gbc.weighty = 3.0;
    add(scroll, gbc);

    JButton btnOpen = new Button().setText("Open").setColor(Styles.TEXT_COLOR).build();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.fill = GridBagConstraints.NONE;
    btnOpen.setPreferredSize(new Dimension(120, 30));
    btnOpen.addActionListener(e -> service.clickOnOpen(txtNumber, countries, message));
    footerPanel.add(btnOpen, gbc);

    JButton btnReturn = new Button().setText("Return").setColor(Styles.MAIN_COLOR).build();
    gbc.gridx = 1;
    btnReturn.setPreferredSize(new Dimension(120, 30));
    btnReturn.addActionListener(e -> service.clickOnReturn(txtNumber, this));
    footerPanel.add(btnReturn, gbc);

    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 2;
    add(footerPanel, gbc);
  }
}