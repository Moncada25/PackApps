package com.bookverse.packapps.apps.feedback.comment;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.bookverse.packapps.utils.constants.Styles;
import com.bookverse.packapps.utils.ui.Resources;
import com.bookverse.packapps.utils.ui.Effects;
import com.bookverse.packapps.utils.ui.factory.Button;
import com.bookverse.packapps.utils.ui.factory.Label;
import com.bookverse.packapps.utils.ui.KeyBindingsUtil;

public class FeedbackView extends JDialog {

  private JTextArea text;

  public FeedbackView(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
    KeyBindingsUtil.addCopyPasteKeyBindings(text, null, null);
  }

  public void start(JFrame parent) {
    setSize(485, 480);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Send Comment");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void createComponents() {

    setLayout(new BorderLayout(10, 10));
    setIconImage(new ImageIcon(Resources.getImage("feedback.png")).getImage());
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    JLabel title = new Label().setText("Write feedback")
        .setColor(Styles.MAIN_COLOR)
        .setFont(Styles.BIG)
        .build();
    JPanel titlePanel = new JPanel();
    titlePanel.add(title);
    add(titlePanel, BorderLayout.NORTH);

    JPanel formPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    JLabel lblUser = new Label().setText("User").setColor(Styles.TEXT_COLOR).setFont(Styles.MEDIUM).build();
    formPanel.add(lblUser, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.CENTER;
    JTextField txtUser = new JTextField();
    txtUser.setHorizontalAlignment(SwingConstants.CENTER);
    txtUser.setPreferredSize(new Dimension(150, 30));
    formPanel.add(txtUser, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    JLabel message = new Label().setText("Message").setColor(Styles.TEXT_COLOR).setFont(Styles.MEDIUM).build();
    formPanel.add(message, gbc);

    gbc.gridy = 3;
    text = new JTextArea();
    JScrollPane scroll = new JScrollPane(text);
    scroll.setPreferredSize(new Dimension(420, 200));
    formPanel.add(scroll, gbc);

    add(formPanel, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
    JButton btnSend = new Button().setText("Send").setColor(Styles.TEXT_COLOR).build();
    btnSend.addActionListener(e -> FeedbackService.clickOnSend(text, txtUser));
    btnSend.setPreferredSize(new Dimension(100, 30));
    buttonPanel.add(btnSend);

    JButton btnExit = new Button().setText("Return").setColor(Styles.MAIN_COLOR).build();
    btnExit.addActionListener(e -> Effects.fadeOut(this));
    btnExit.setPreferredSize(new Dimension(100, 30));
    buttonPanel.add(btnExit);

    add(buttonPanel, BorderLayout.SOUTH);
  }
}