package com.bookverse.development.packapps.utils.ui.factory;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.bookverse.development.packapps.utils.Format;
import com.bookverse.development.packapps.utils.constants.Styles;

public class Form {
  private String title;
  private Map<String, JComponent> formFields;
  private List<JButton> buttons;
  private String extraText;
  private JButton extraButton;
  private int gridX;
  private int gridY;

  public Form() {
    this.gridX = 2;
    this.gridY = 2;
    this.buttons = new ArrayList<>();
  }

  public Form setTitle(String title) {
    this.title = title;
    return this;
  }

  public Form setFormFields(Map<String, JComponent> formFields) {
    this.formFields = formFields;
    return this;
  }

  public Form addButton(JButton button) {
    this.buttons.add(button);
    return this;
  }

  public Form setExtraText(String extraText) {
    this.extraText = extraText;
    return this;
  }

  public Form setExtraButton(JButton extraButton) {
    this.extraButton = extraButton;
    return this;
  }

  public Form setGrid(int gridX, int gridY) {
    this.gridX = gridX;
    this.gridY = gridY;
    return this;
  }

  public void setUserAndPassword(
      JTextField txtUser,
      JPasswordField txtPassword,
      Map<String, JComponent> formFields
  ) {
    txtUser.setHorizontalAlignment(SwingConstants.CENTER);
    txtUser.setPreferredSize(new Dimension(200, 30));
    txtUser.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent evt) {
        Format.numbersAndText(evt.getKeyChar(), evt, txtUser.getText(), 12);
      }
    });
    formFields.put("Username", txtUser);

    txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
    txtPassword.setPreferredSize(new Dimension(200, 30));
    formFields.put("Password", txtPassword);
  }

  public JPanel build() {
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 20, 10, 20);
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;

    JLabel titleLabel = new JLabel(title);
    titleLabel.setForeground(Styles.MAIN_COLOR);
    titleLabel.setFont(Styles.BIG);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = gridX;
    gbc.anchor = GridBagConstraints.CENTER;
    panel.add(titleLabel, gbc);

    int row = 1;
    for (Map.Entry<String, JComponent> entry : formFields.entrySet()) {
      JLabel label = new JLabel(entry.getKey());
      label.setForeground(Styles.TEXT_COLOR);
      label.setFont(Styles.MEDIUM);
      gbc.gridx = 0;
      gbc.gridy = row;
      gbc.gridwidth = 1;
      gbc.anchor = GridBagConstraints.WEST;
      panel.add(label, gbc);

      JComponent component = entry.getValue();
      gbc.gridx = 1;
      gbc.gridy = row++;
      panel.add(component, gbc);
    }

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
    for (JButton button : buttons) {
      button.setPreferredSize(new Dimension(120, 30));
      buttonPanel.add(button);
    }
    gbc.gridx = 0;
    gbc.gridy = row++;
    gbc.gridwidth = gridX;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.NONE;
    panel.add(buttonPanel, gbc);

    if (extraButton == null || extraText == null) {
      return panel;
    }

    JLabel extraLabel = new JLabel(extraText);
    extraLabel.setForeground(Styles.MAIN_COLOR);
    extraLabel.setFont(Styles.SMALL);
    gbc.gridx = 0;
    gbc.gridy = row++;
    gbc.gridwidth = gridX;
    gbc.anchor = GridBagConstraints.CENTER;
    panel.add(extraLabel, gbc);

    extraButton.setPreferredSize(new Dimension(120, 30));
    gbc.gridx = 0;
    gbc.gridy = row + 1;
    gbc.gridwidth = gridX;
    gbc.weightx = 1;
    gbc.weighty = 0;
    gbc.fill = GridBagConstraints.NONE;
    panel.add(extraButton, gbc);

    return panel;
  }
}