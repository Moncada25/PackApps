package com.bookverse.packapps.apps.texts;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.bookverse.packapps.utils.Auth;
import com.bookverse.packapps.utils.constants.Styles;
import com.bookverse.packapps.utils.ui.KeyBindingsUtil;
import com.bookverse.packapps.utils.ui.Resources;
import com.bookverse.packapps.utils.ui.Alerts;
import com.bookverse.packapps.utils.ui.Effects;
import com.bookverse.packapps.utils.ui.factory.Label;

public class TextsView extends JDialog {

  private JLabel encrypt;
  private JLabel decrypt;
  private JLabel upperCase;
  private JLabel textsTitle;
  private JLabel lowerCase;
  private JLabel exit;
  private JTextArea text;

  public TextsView(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
    KeyBindingsUtil.addCopyPasteKeyBindings(text, textsTitle, "<html><strong>Write Text... %s</strong></html>");
  }

  public void start(JFrame parent) {
    setSize(650, 450);
    setLocationRelativeTo(parent);
    setMinimumSize(new Dimension(650, 450));
    setTitle("Texts");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  public JPanel getPanel() {

    JPanel panel = new JPanel(new FlowLayout());
    panel.setBorder(Resources.getBorder("Select action"));

    encrypt = new Label().setText("  Encrypt  ")
        .setColor(Styles.TEXT_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    encrypt.setBorder(Styles.BORDER_BLUE);
    encrypt.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (!text.getText().isEmpty()) {
          text.setText(Auth.encode(text.getText(), false));
        } else {
          Alerts.inputSomethingText();
        }
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        encrypt.setCursor(Styles.HAND);
      }
    });
    panel.add(encrypt);

    upperCase = new Label().setText("  UPPERCASE  ")
        .setColor(Styles.TEXT_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    upperCase.setBorder(Styles.BORDER_BLUE);
    upperCase.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (!text.getText().isEmpty()) {
          text.setText(text.getText().toUpperCase());
        } else {
          Alerts.inputSomethingText();
        }
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        upperCase.setCursor(Styles.HAND);
      }
    });
    panel.add(upperCase);

    exit = new Label().setText("  Return  ")
        .setColor(Styles.MAIN_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    exit.setBorder(Styles.BORDER_RED);
    exit.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        Effects.fadeOut(TextsView.this);
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        exit.setCursor(Styles.HAND);
      }
    });
    panel.add(exit);

    lowerCase = new Label().setText("  lowercase  ")
        .setColor(Styles.TEXT_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    lowerCase.setBorder(Styles.BORDER_BLUE);
    lowerCase.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (!text.getText().isEmpty()) {
          text.setText(text.getText().toLowerCase());
        } else {
          Alerts.inputSomethingText();
        }
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        lowerCase.setCursor(Styles.HAND);
      }
    });
    panel.add(lowerCase);

    decrypt = new Label().setText("  Decrypt  ")
        .setColor(Styles.TEXT_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    decrypt.setBorder(Styles.BORDER_BLUE);
    decrypt.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (!text.getText().isEmpty()) {
          text.setText(Auth.decode(text.getText(), false));
        } else {
          Alerts.inputSomethingText();
        }
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        decrypt.setCursor(Styles.HAND);
      }
    });
    panel.add(decrypt);

    return panel;
  }

  private void createComponents() {

    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(Resources.getImage("texts.png")).getImage());

    textsTitle = Resources.getLabel("<html><strong>Write Text... 0</strong></html>", Styles.MAIN_COLOR, this, Styles.MEDIUM);
    textsTitle.setBounds(30, 15, 370, 50);
    add(textsTitle, BorderLayout.NORTH);

    text = new JTextArea();
    JScrollPane scroll = new JScrollPane(text);
    scroll.setBounds(30, 60, 420, 200);
    add(scroll, BorderLayout.CENTER);

    add(getPanel(), BorderLayout.SOUTH);

    repaint();
  }
}