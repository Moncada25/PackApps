package com.bookverse.packapps.apps.numbers.phi;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.bookverse.packapps.utils.constants.Styles;
import com.bookverse.packapps.utils.ui.Alerts;
import com.bookverse.packapps.utils.Format;
import com.bookverse.packapps.utils.ui.Effects;
import com.bookverse.packapps.utils.ui.factory.Label;

public class PhiView extends JDialog {

  private transient PhiService service = new PhiService();

  public PhiView(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);

    JLabel value = new JLabel();
    value.setForeground(Styles.TEXT_COLOR);
    value.setFont(Styles.MEDIUM);
    gbc.gridx = 1;
    gbc.gridy = 4;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.WEST;
    add(value, gbc);

    JLabel phiTitle = new Label().setText("Approach to φ")
        .setFont(Styles.BIG)
        .setColor(Styles.MAIN_COLOR)
        .build();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 3;
    gbc.anchor = GridBagConstraints.CENTER;
    add(phiTitle, gbc);

    createFirstSection(gbc, value);
    createSecondSection(gbc);
    createThirdSection(gbc);

    JLabel lblSecond = new Label()
        .setText("Approximate value")
        .setFont(Styles.MEDIUM)
        .setColor(Styles.MAIN_COLOR)
        .build();
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    gbc.anchor = GridBagConstraints.WEST;
    add(lblSecond, gbc);
  }

  public void start(JDialog parent) {
    setSize(450, 310);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Phi φ");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void createFirstSection(GridBagConstraints gbc, JLabel value) {

    JLabel lblFirst = new Label()
        .setText("Fibonacci's series")
        .setFont(Styles.MEDIUM)
        .setColor(Styles.MAIN_COLOR)
        .build();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.anchor = GridBagConstraints.WEST;
    add(lblFirst, gbc);

    JTextField txtFirst = new JTextField(5);
    txtFirst.setHorizontalAlignment(SwingConstants.CENTER);
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    add(txtFirst, gbc);

    txtFirst.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && !txtFirst.getText().isEmpty()) {
          service.generateFibonacci(Integer.parseInt(txtFirst.getText()));
          value.setText(service.getPhi() + " ...");
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          Alerts.inputSomethingText();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      @Override
      public void keyTyped(KeyEvent e) {
        Format.onlyNumbers(e.getKeyChar(), e, txtFirst.getText(), 2);
      }
    });

    JButton btnCalculateFirst = new JButton("Show");
    btnCalculateFirst.setBackground(Styles.TEXT_COLOR);
    btnCalculateFirst.addActionListener(e -> {
      if (!txtFirst.getText().isEmpty()) {
        service.generateFibonacci(Integer.parseInt(txtFirst.getText()));
        value.setText(service.getPhi() + "...");
      } else {
        Alerts.inputSomethingText();
      }
    });
    btnCalculateFirst.setPreferredSize(new Dimension(70, 25));
    gbc.gridx = 2;
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.NONE;
    add(btnCalculateFirst, gbc);
    btnCalculateFirst.addActionListener(e -> {
      if (!txtFirst.getText().isEmpty()) {
        service.generateFibonacci(Integer.parseInt(txtFirst.getText()));
        value.setText(service.getPhi() + "...");
      } else {
        Alerts.inputSomethingText();
      }
    });
  }

  private void createSecondSection(GridBagConstraints gbc) {

    JLabel firstFormula = new Label()
        .setText("Nth Fibonacci number")
        .setFont(Styles.MEDIUM)
        .setColor(Styles.MAIN_COLOR)
        .build();
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.anchor = GridBagConstraints.WEST;
    add(firstFormula, gbc);

    JTextField txtSecond = new JTextField();
    txtSecond.setHorizontalAlignment(SwingConstants.CENTER);
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    add(txtSecond, gbc);

    txtSecond.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && !txtSecond.getText().isEmpty()) {
          service.getFibonacci(Integer.parseInt(txtSecond.getText()));
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          Alerts.inputSomethingText();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      @Override
      public void keyTyped(KeyEvent e) {
        Format.onlyNumbers(e.getKeyChar(), e, txtSecond.getText(), 2);
      }
    });

    JButton btnCalculateSecond = new JButton("Show");
    btnCalculateSecond.setBackground(Styles.TEXT_COLOR);
    btnCalculateSecond.addActionListener(e -> {
      if (!txtSecond.getText().isEmpty()) {
        service.getFibonacci(Integer.parseInt(txtSecond.getText()));
      } else {
        Alerts.inputSomethingText();
      }
    });
    btnCalculateSecond.setPreferredSize(new Dimension(70, 25));
    gbc.gridx = 2;
    gbc.gridy = 2;
    add(btnCalculateSecond, gbc);
    btnCalculateSecond.addActionListener(e -> {
      if (!txtSecond.getText().isEmpty()) {
        service.getFibonacci(Integer.parseInt(txtSecond.getText()));
      } else {
        Alerts.inputSomethingText();
      }
    });
  }

  private void createThirdSection(GridBagConstraints gbc) {

    JLabel secondFormula = new Label()
        .setText("Golden ratio")
        .setFont(Styles.MEDIUM)
        .setColor(Styles.MAIN_COLOR)
        .build();
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    gbc.anchor = GridBagConstraints.WEST;
    add(secondFormula, gbc);

    JTextField txtProportion = new JTextField();
    txtProportion.setHorizontalAlignment(SwingConstants.CENTER);
    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    add(txtProportion, gbc);

    JButton btnProportion = new JButton("Show");
    btnProportion.setBackground(Styles.TEXT_COLOR);
    btnProportion.addActionListener(e -> {
      if (!txtProportion.getText().isEmpty()) {
        service.proportionAurea(Double.parseDouble(txtProportion.getText()));
      } else {
        Alerts.inputSomethingText();
      }
    });
    btnProportion.setPreferredSize(new Dimension(70, 25));
    gbc.gridx = 2;
    gbc.gridy = 3;
    add(btnProportion, gbc);
    btnProportion.addActionListener(e -> {
      if (!txtProportion.getText().isEmpty()) {
        service.proportionAurea(Double.parseDouble(txtProportion.getText()));
      } else {
        Alerts.inputSomethingText();
      }
    });

    txtProportion.addKeyListener(new KeyAdapter() {

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && !txtProportion.getText().isEmpty()) {
          service.proportionAurea(Double.parseDouble(txtProportion.getText()));
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          Alerts.inputSomethingText();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      @Override
      public void keyTyped(KeyEvent e) {
        Format.onlyNumbers(e.getKeyChar(), e, txtProportion.getText(), 4);
      }
    });
  }
}