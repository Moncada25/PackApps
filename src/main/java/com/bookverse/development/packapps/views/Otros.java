package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.AppConfig.*;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Otros extends JDialog implements ActionListener, MouseListener {

    private JButton btncolor, btnstart1, btnstart2, btnstart3;
    private JLabel mensaje, primero, segundo, forma1, forma2;
    private JTextField txt1, txt2, txt3;
    private JLabel red, green, blue;
    private JComboBox<String> redcom, greencom, bluecom;

    Resources resources = new Resources();

    public Otros(JDialog parent, boolean modal) {

        super(parent, modal);
        componentes();
    }

    public void componentes() {

        setLayout(null);

        btnstart1 = resources.getButton("Show", TEXT_COLOR, this, this);
        btnstart1.setBounds(330, 125, 75, 25);

        btnstart3 = resources.getButton("Show", TEXT_COLOR, this, this);
        btnstart3.setBounds(330, 225, 75, 25);

        btnstart2 = resources.getButton("Show", TEXT_COLOR, this, this);
        btnstart2.setBounds(330, 175, 75, 25);

        primero = resources.getLabel("<html><strong>Generar RGB</strong></html>", MAIN_COLOR, this, MEDIUM);
        primero.setBounds(25, 70, 280, 30);

        btncolor = resources.getButton("Color", null, this, this);
        btncolor.setBounds(350, 75, 70, 25);

        red = resources
            .getLabel("<html><strong>Red</strong></html>", MAIN_COLOR, this, null);
        red.setBounds(170, 95, 50, 20);

        redcom = new JComboBox<String>();
        redcom.setBounds(170, 75, 57, 25);
        for (int i = 0; i <= 255; i++) {
            redcom.addItem(String.valueOf(i));
        }
        add(redcom);

        green = resources
            .getLabel("<html><strong>Green</strong></html>", new Color(100, 220, 0), this, null);
        green.setBounds(230, 95, 50, 20);

        greencom = new JComboBox<String>();
        greencom.setBounds(230, 75, 57, 25);
        for (int i = 0; i <= 255; i++) {
            greencom.addItem(String.valueOf(i));
        }
        add(greencom);

        blue = resources
            .getLabel("<html><strong>Blue</strong></html>", TEXT_COLOR, this, null);
        blue.setBounds(290, 95, 50, 20);

        bluecom = new JComboBox<String>();
        bluecom.setBounds(290, 75, 57, 25);
        for (int i = 0; i <= 255; i++) {
            bluecom.addItem(String.valueOf(i));
        }
        add(bluecom);

        forma1 = resources.getLabel("<html><strong>Entero ? Binario</strong></html>", MAIN_COLOR, this, MEDIUM);
        forma1.setBounds(25, 120, 200, 30);

        segundo = resources.getLabel("<html><strong>Invertir cifras</strong></html>", MAIN_COLOR, this, MEDIUM);
        segundo.setBounds(25, 220, 260, 30);

        forma2 = resources.getLabel("<html><strong>Binario ? Entero</strong></html>", MAIN_COLOR, this, MEDIUM);
        forma2.setBounds(25, 170, 280, 30);

        txt1 = new JTextField();
        txt1.setBounds(200, 125, 100, 25);
        txt1.setHorizontalAlignment(JTextField.CENTER);
        add(txt1);

        txt1.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                txt1KeyPressed(e);
            }

            private void txt1KeyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER && txt1.getText().length() > 0) {
                    enteroBinario(Integer.parseInt(txt1.getText()));
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
                Format.onlyNumbers(e.getKeyChar(), e, txt1.getText(), 9);
            }
        });

        txt2 = new JTextField();
        txt2.setBounds(200, 175, 100, 25);
        txt2.setHorizontalAlignment(JTextField.CENTER);
        add(txt2);

        txt2.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                txt2KeyPressed(e);
            }

            private void txt2KeyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER && txt2.getText().length() > 0) {
                    binarioEntero(txt2.getText());
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
                Format.onlyBinary(e.getKeyChar(), e, txt2.getText());
            }
        });

        txt3 = new JTextField();
        txt3.setBounds(200, 225, 100, 25);
        txt3.setHorizontalAlignment(JTextField.CENTER);
        add(txt3);

        txt3.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                txt3KeyPressed(e);
            }

            private void txt3KeyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER && txt3.getText().length() > 0) {
                    invertirCifras(txt3.getText());
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
                Format.onlyNumbers(e.getKeyChar(), e, txt3.getText(), 9);
            }
        });

        mensaje = resources
            .getLabel("<html><em><strong>Otras cositas...</strong></em></html>", MAIN_COLOR, this, BIG);
        mensaje.addMouseListener(this);
        mensaje.setBounds(120, 20, 200, 30);
    }


    private void binarioEntero(String num) {

        int numero = Integer.parseInt(num);

        int aux = numero;
        long digito, decimal = 0; // será el equivalente en base decimal
        int exponente = 0;

        while (numero != 0) {
            // se toma la última cifra
            digito = numero % 10;
            // se multiplica por la potencia de 2 correspondiente y se suma al número
            decimal = decimal + digito * (int) Math.pow(2, exponente);
            // se aumenta el exponente
            exponente++;
            // se quita la última cifra para repetir el proceso
            numero = numero / 10;
        }

        JOptionPane.showMessageDialog(null,
            "<html>" + Format.style() + "<strong>" + aux + " en decimal</strong> ?  " + decimal
                + "</html>",
            "Resultado", JOptionPane.PLAIN_MESSAGE);
    }

    private void enteroBinario(int decimal) {

        int cociente = decimal;// El cociente inicia con el valor del número decimal.

        String binario = " ";// Inicio de la cifra binaria

        while (cociente > 1) {// Repetir mientras el cociente de dividir por 2 sea mayor a 1.

            int digito = cociente % 2;// Obtener el residuo de dividir por 2, éste será el dígito binario.

            cociente = (cociente - digito) / 2;// Obtener el cociente de la división entera por 2

            binario = digito + binario;// Agregar el dígito binario a la cifra
        }

        binario = cociente + binario;// Agregar el último cociente para completar la cifra.

        JOptionPane.showMessageDialog(null,
            "<html>" + Format.style() + "<strong>" + decimal + " en binario</strong> ? "
                + binario
                + "</html>", "Resultado",
            JOptionPane.PLAIN_MESSAGE);
    }

    private void invertirCifras(String num) {

        String numeroInvertido = "";
        int longitudNumero = 0;

        longitudNumero = num.length();

        while (longitudNumero != 0) {
            numeroInvertido += num.substring(longitudNumero - 1, longitudNumero);
            longitudNumero--;
        }

        JOptionPane.showMessageDialog(null,
            "<html>" + Format.style() + "<strong>Número invertido</strong> ? " + numeroInvertido
                + "</html>",
            "Resultado", JOptionPane.PLAIN_MESSAGE);
    }

    public void btnColorAP() {

        String cad1 = (String) redcom.getSelectedItem();
        String cad2 = (String) greencom.getSelectedItem();
        String cad3 = (String) bluecom.getSelectedItem();

        int rojo = Integer.parseInt(cad1);
        int verde = Integer.parseInt(cad2);
        int azul = Integer.parseInt(cad3);

        Color color = new Color(rojo, verde, azul);
        btncolor.setBackground(color);
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

        if (e.getSource() == btncolor) {
            btnColorAP();
        } else if (e.getSource() == btnstart1) {

            if (txt1.getText().length() > 0) {
                enteroBinario(Integer.parseInt(txt1.getText()));
            } else {
                Alerts.inputSomethingText();
            }

        } else if (e.getSource() == btnstart2) {

            if (txt2.getText().length() > 0) {
                binarioEntero(txt2.getText());
            } else {
                Alerts.inputSomethingText();
            }
        } else if (e.getSource() == btnstart3) {

            if (txt3.getText().length() > 0) {
                invertirCifras(txt3.getText());
            } else {
                Alerts.inputSomethingText();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == mensaje) {
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