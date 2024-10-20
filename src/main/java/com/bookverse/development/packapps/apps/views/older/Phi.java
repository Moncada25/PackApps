package com.bookverse.development.packapps.apps.views.older;

import static com.bookverse.development.packapps.utils.constants.Styles.BIG;
import static com.bookverse.development.packapps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.utils.constants.Styles.MEDIUM;
import static com.bookverse.development.packapps.utils.constants.Styles.TEXT_COLOR;

import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.other.Format;
import com.bookverse.development.packapps.utils.ui.Effects;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Phi extends JDialog implements ActionListener, MouseListener {

  private JButton btnCalculateFirst;
  private JButton btnCalculateSecond;
  private JButton btnProportion;
  private JLabel message;
  private JLabel value;
  private JTextField txtFirst;
  private JTextField txtSecond;
  private JTextField txtProportion;
  private String phi;

  public Phi(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {
    // Cambiamos el layout a GridBagLayout
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10); // Añadimos espacio entre componentes

    // Inicializamos los elementos globales

    // Etiqueta "Approach to φ"
    message = new JLabel("<html><em><strong>Approach to φ</strong></em></html>", SwingConstants.CENTER);
    message.setForeground(MAIN_COLOR);
    message.setFont(BIG);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 3;
    gbc.anchor = GridBagConstraints.CENTER;
    add(message, gbc);

    // Etiqueta "Fibonacci's series"
    JLabel lblFirst = new JLabel("<html><strong>Fibonacci's series</strong></html>", SwingConstants.LEFT);
    lblFirst.setForeground(MAIN_COLOR);
    lblFirst.setFont(MEDIUM);
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.anchor = GridBagConstraints.WEST;
    add(lblFirst, gbc);

    // Campo de texto para "Fibonacci's series"
    txtFirst = new JTextField(5); // Define el número de columnas
    txtFirst.setHorizontalAlignment(SwingConstants.CENTER);
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    add(txtFirst, gbc);

    txtFirst.addKeyListener(new KeyAdapter() {

      @Override
      public void keyPressed(KeyEvent e) {
        txt1KeyPressed(e);
      }

      private void txt1KeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER && txtFirst.getText().length() > 0) {
          generateFibonacci(Integer.parseInt(txtFirst.getText()));
          value.setText(phi + " ...");
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          Alerts.inputSomethingText();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      @Override
      public void keyTyped(KeyEvent e) {
        txt1KeyTyped(e);
      }

      private void txt1KeyTyped(KeyEvent e) {
        Format.onlyNumbers(e.getKeyChar(), e, txtFirst.getText(), 2);
      }
    });

    // Botón "Show" para Fibonacci's series
    btnCalculateFirst = new JButton("Show");
    btnCalculateFirst.setBackground(TEXT_COLOR); // Color de fondo
    btnCalculateFirst.addActionListener(this); // Añadir ActionListener
    btnCalculateFirst.setPreferredSize(new Dimension(70, 25));
    gbc.gridx = 2;
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.NONE;
    add(btnCalculateFirst, gbc);

    // Etiqueta "Nth Fibonacci number"
    JLabel firstFormula = new JLabel("<html><strong>Nth Fibonacci number</strong></html>", SwingConstants.LEFT);
    firstFormula.setForeground(MAIN_COLOR);
    firstFormula.setFont(MEDIUM);
    gbc.gridx = 0;
    gbc.gridy = 2;
    add(firstFormula, gbc);

    // Campo de texto para "Nth Fibonacci number"
    txtSecond = new JTextField(); // Define el número de columnas
    txtSecond.setHorizontalAlignment(SwingConstants.CENTER);
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    add(txtSecond, gbc);

    txtSecond.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        txt2KeyPressed(e);
      }

      private void txt2KeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER && txtSecond.getText().length() > 0) {
          getFibonacci(Integer.parseInt(txtSecond.getText()));
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
        Format.onlyNumbers(e.getKeyChar(), e, txtSecond.getText(), 2);
      }
    });

    // Botón "Show" para Nth Fibonacci number
    btnCalculateSecond = new JButton("Show");
    btnCalculateSecond.setBackground(TEXT_COLOR); // Color de fondo
    btnCalculateSecond.addActionListener(this); // Añadir ActionListener
    btnCalculateSecond.setPreferredSize(new Dimension(70, 25));
    gbc.gridx = 2;
    gbc.gridy = 2;
    add(btnCalculateSecond, gbc);

    // Etiqueta "Golden ratio"
    JLabel secondFormula = new JLabel("<html><strong>Golden ratio</strong></html>", SwingConstants.LEFT);
    secondFormula.setForeground(MAIN_COLOR);
    secondFormula.setFont(MEDIUM);
    gbc.gridx = 0;
    gbc.gridy = 3;
    add(secondFormula, gbc);

    // Campo de texto para "Golden ratio"
    txtProportion = new JTextField(); // Define el número de columnas
    txtProportion.setHorizontalAlignment(JTextField.CENTER);
    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    add(txtProportion, gbc);

    // Botón "Show" para Golden ratio
    btnProportion = new JButton("Show");
    btnProportion.setBackground(TEXT_COLOR); // Color de fondo
    btnProportion.addActionListener(this); // Añadir ActionListener
    btnProportion.setPreferredSize(new Dimension(70, 25));
    gbc.gridx = 2;
    gbc.gridy = 3;
    add(btnProportion, gbc);

    txtProportion.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        txt3KeyPressed(e);
      }

      private void txt3KeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER && txtProportion.getText().length() > 0) {
          proportionAurea(Double.parseDouble(txtProportion.getText()));
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
        Format.onlyNumbers(e.getKeyChar(), e, txtProportion.getText(), 4);
      }
    });

    // Etiqueta "Approximate value"
    JLabel lblSecond = new JLabel("<html><strong>Approximate value</strong></html>", SwingConstants.LEFT);
    lblSecond.setForeground(MAIN_COLOR);
    lblSecond.setFont(MEDIUM);
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.anchor = GridBagConstraints.WEST;
    add(lblSecond, gbc);

    // Valor aproximado
    value = new JLabel();
    value.setForeground(TEXT_COLOR); // Color de texto
    value.setFont(MEDIUM); // Establece el formato igual que los demás labels
    gbc.gridx = 1;
    gbc.gridy = 4;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.WEST;
    add(value, gbc);
  }

  public void start(JDialog parent) {
    setSize(450, 310); // Tamaño fijo inicial
    setResizable(false); // Hacer la ventana redimensionable
    setLocationRelativeTo(parent);
    setTitle("Phi φ");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void generateFibonacci(int lim) {

    String numbers = "";

    double num1 = 0, num2 = 1, f = 0;

    for (int i = 0; i < lim; i++) {

      if (i % 10 == 0 && i != 0 && i != 1 && i != 2) {
        numbers += "\n";
      }

      f = num1 + num2;
      num2 = num1;
      num1 = f;

      numbers += "[" + String.format("%.0f", f) + "]";
    }

    if(num2 != 0)
      phi = String.format("%.10f", num1 / num2);

    Alerts.message("Result", numbers);
  }

  private void getFibonacci(int n)  {

    double fi, number;

    fi = (1 + Math.sqrt(5)) / 2;

    number = (1 / Math.sqrt(5)) * (Math.pow(fi, n) - (Math.pow(-1 / fi, n)));
    Alerts.message("Result",
        "N° " + n + " in the Fibonacci's series is " + String.format("%.0f", number));
  }

  private void proportionAurea(double length) {

    double a, b;

    a = length / ((1 + Math.sqrt(5)) / 2);
    b = length - a;
    Alerts.message("Result",
        "Portion A: " + String.format("%.2f", a) + " | Portion B: " + String.format("%.2f", b));
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnCalculateFirst) {

      if (!txtFirst.getText().isEmpty()) {
        generateFibonacci(Integer.parseInt(txtFirst.getText()));
        value.setText(phi + "...");
      } else {
        Alerts.inputSomethingText();
      }

    } else if (e.getSource() == btnCalculateSecond) {

      if (txtSecond.getText().length() > 0) {
        getFibonacci(Integer.parseInt(txtSecond.getText()));
      } else {
        Alerts.inputSomethingText();
      }
    } else if (e.getSource() == btnProportion) {

      if (txtProportion.getText().length() > 0) {
        proportionAurea(Double.parseDouble(txtProportion.getText()));
      } else {
        Alerts.inputSomethingText();
      }
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == message) {
      Effects.fadeOut(this);
    }
  }

  @Override
  public void mouseEntered(MouseEvent arg0) {

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