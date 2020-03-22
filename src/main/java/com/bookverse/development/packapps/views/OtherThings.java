package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.AppConfig.BIG;
import static com.bookverse.development.packapps.core.AppConfig.MAIN_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.MEDIUM;
import static com.bookverse.development.packapps.core.AppConfig.TEXT_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.fadeIn;

import com.bookverse.development.packapps.core.AppConfig;
import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.Format;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.stream.IntStream;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class OtherThings extends JDialog implements ActionListener, MouseListener {

  Resources resources = new Resources();
  private JButton btnChangeColor, btnStart1, btnStart2, btnStart3;
  private JLabel tittle;
  private JTextField txtintToBinary, txtBinaryToInt, txtDigits;
  private JComboBox<String> listRed, listGreen, listBlue;

  public OtherThings(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {

    setLayout(null);

    btnStart1 = resources.getButton("Show", TEXT_COLOR, this, this);
    btnStart1.setBounds(330, 125, 75, 25);

    btnStart3 = resources.getButton("Show", TEXT_COLOR, this, this);
    btnStart3.setBounds(330, 225, 75, 25);

    btnStart2 = resources.getButton("Show", TEXT_COLOR, this, this);
    btnStart2.setBounds(330, 175, 75, 25);

    JLabel changeRGB = resources
        .getLabel("<html><strong>RGB Generator</strong></html>", MAIN_COLOR, this, MEDIUM);
    changeRGB.setBounds(25, 70, 280, 30);

    btnChangeColor = resources.getButton("Color", null, this, this);
    btnChangeColor.setBounds(350, 75, 70, 25);

    JLabel lblRed = resources
        .getLabel("<html><strong>Red</strong></html>", MAIN_COLOR, this, null);
    lblRed.setBounds(170, 95, 50, 20);

    listRed = new JComboBox<>();
    listRed.setBounds(170, 75, 57, 25);
    IntStream.rangeClosed(0, 255).forEach(i -> listRed.addItem(String.valueOf(i)));
    add(listRed);

    JLabel lblGreen = resources
        .getLabel("<html><strong>Green</strong></html>", new Color(100, 220, 0), this, null);
    lblGreen.setBounds(230, 95, 50, 20);

    listGreen = new JComboBox<>();
    listGreen.setBounds(230, 75, 57, 25);
    IntStream.rangeClosed(0, 255).forEach(i -> listGreen.addItem(String.valueOf(i)));
    add(listGreen);

    JLabel lblBlue = resources
        .getLabel("<html><strong>Blue</strong></html>", TEXT_COLOR, this, null);
    lblBlue.setBounds(290, 95, 50, 20);

    listBlue = new JComboBox<>();
    listBlue.setBounds(290, 75, 57, 25);
    IntStream.rangeClosed(0, 255).forEach(i -> listBlue.addItem(String.valueOf(i)));
    add(listBlue);

    JLabel intToBinary = resources
        .getLabel("<html><strong>Int to Binary</strong></html>", MAIN_COLOR, this, MEDIUM);
    intToBinary.setBounds(25, 120, 200, 30);

    JLabel revertDigits = resources
        .getLabel("<html><strong>Revert digits</strong></html>", MAIN_COLOR, this, MEDIUM);
    revertDigits.setBounds(25, 220, 260, 30);

    JLabel binaryToInt = resources
        .getLabel("<html><strong>Binary to Int</strong></html>", MAIN_COLOR, this, MEDIUM);
    binaryToInt.setBounds(25, 170, 280, 30);

    txtintToBinary = new JTextField();
    txtintToBinary.setBounds(200, 125, 100, 25);
    txtintToBinary.setHorizontalAlignment(JTextField.CENTER);
    add(txtintToBinary);

    txtintToBinary.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        txt1KeyPressed(e);
      }

      private void txt1KeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER && txtintToBinary.getText().length() > 0) {
          intToBinary(Integer.parseInt(txtintToBinary.getText()));
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          Alerts.inputSomethingText();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      public void keyTyped(KeyEvent e) {
        txt1KeyTyped(e);
      }

      private void txt1KeyTyped(KeyEvent e) {
        Format.onlyNumbers(e.getKeyChar(), e, txtintToBinary.getText(), 9);
      }
    });

    txtBinaryToInt = new JTextField();
    txtBinaryToInt.setBounds(200, 175, 100, 25);
    txtBinaryToInt.setHorizontalAlignment(JTextField.CENTER);
    add(txtBinaryToInt);

    txtBinaryToInt.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        txt2KeyPressed(e);
      }

      private void txt2KeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER && txtBinaryToInt.getText().length() > 0) {
          binaryToInt(txtBinaryToInt.getText());
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          Alerts.inputSomethingText();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      public void keyTyped(KeyEvent e) {
        txt2KeyTyped(e);
      }

      private void txt2KeyTyped(KeyEvent e) {
        Format.onlyBinary(e.getKeyChar(), e, txtBinaryToInt.getText());
      }
    });

    txtDigits = new JTextField();
    txtDigits.setBounds(200, 225, 100, 25);
    txtDigits.setHorizontalAlignment(JTextField.CENTER);
    add(txtDigits);

    txtDigits.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        txt3KeyPressed(e);
      }

      private void txt3KeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER && txtDigits.getText().length() > 0) {
          revertDigits(txtDigits.getText());
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          Alerts.inputSomethingText();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      public void keyTyped(KeyEvent e) {
        txt3KeyTyped(e);
      }

      private void txt3KeyTyped(KeyEvent e) {
        Format.onlyNumbers(e.getKeyChar(), e, txtDigits.getText(), 9);
      }
    });

    tittle = resources
        .getLabel("<html><em><strong>Other things...</strong></em></html>", MAIN_COLOR, this, BIG);
    tittle.addMouseListener(this);
    tittle.setBounds(120, 20, 200, 35);
  }

  private void binaryToInt(String num) {

    int number = Integer.parseInt(num);

    int aux = number;
    long digit, decimal = 0;
    int exponente = 0;

    while (number != 0) {
      digit = number % 10;
      decimal = decimal + digit * (int) Math.pow(2, exponente);
      exponente++;
      number = number / 10;
    }
    Alerts.message("Result", aux + " in decimal is " + decimal);
  }

  private void intToBinary(int decimal) {

    int quotient = decimal;

    String binary = " ";

    while (quotient > 1) {
      int digit = quotient % 2;
      quotient = (quotient - digit) / 2;
      binary = digit + binary;
    }

    binary = quotient + binary;
    Alerts.message("Result", decimal + " in binary is " + binary);
  }

  private void revertDigits(String num) {

    String revertNumber = "";
    int lengthNumber = 0;

    lengthNumber = num.length();

    while (lengthNumber != 0) {
      revertNumber += num.substring(lengthNumber - 1, lengthNumber);
      lengthNumber--;
    }
    Alerts.message("Result", "Invert number: " + revertNumber);
  }

  private void btnColorAP() {

    String cad1 = (String) listRed.getSelectedItem();
    String cad2 = (String) listGreen.getSelectedItem();
    String cad3 = (String) listBlue.getSelectedItem();

    int red = Integer.parseInt(cad1);
    int green = Integer.parseInt(cad2);
    int blue = Integer.parseInt(cad3);

    Color color = new Color(red, green, blue);
    btnChangeColor.setBackground(color);
  }

  public void start(JDialog parent) {
    setBounds(0, 0, 430, 300);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Hacks");
    fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnChangeColor) {
      btnColorAP();
    } else if (e.getSource() == btnStart1) {

      if (txtintToBinary.getText().length() > 0) {
        intToBinary(Integer.parseInt(txtintToBinary.getText()));
      } else {
        Alerts.inputSomethingText();
      }

    } else if (e.getSource() == btnStart2) {

      if (txtBinaryToInt.getText().length() > 0) {
        binaryToInt(txtBinaryToInt.getText());
      } else {
        Alerts.inputSomethingText();
      }
    } else if (e.getSource() == btnStart3) {

      if (txtDigits.getText().length() > 0) {
        revertDigits(txtDigits.getText());
      } else {
        Alerts.inputSomethingText();
      }
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == tittle) {
      AppConfig.fadeOut(this);
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }
}