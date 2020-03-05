package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.core.Resources;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

//Se hereda la clase JFrame y se implementa la interfaz ActionListener
public class Calculadora extends JDialog implements ActionListener {

    private JButton[][] num;
    private JButton cero, punto, mas, menos, div, multi, nega, raiz, euler, poten, igual, borrar, vaciar;
    private JTextField resul;
    private int sw = 1, s = 1;
    private double resultado;
    private boolean point = false;
    private String valor1, valor2, signo, contenido;

    private Resources img = new Resources();

    // Constructor que recibe la ventana padre y el valor modal
    public Calculadora(JDialog parent, boolean modal) {

        super(parent, modal);
        Componentes();
    }

    public Calculadora() {
        Componentes();
    }

    // Se crean los componentes de la ventana
    private void Componentes() {

        setLayout(null); // Permite el posicionamiento absoluto de los componentes
        setIconImage(new ImageIcon(img.getImage("numeritos.png")).getImage());

        resul = new JTextField("");
        resul.setBounds(50, 14, 215, 43);
        resul.setHorizontalAlignment(JTextField.CENTER);
        add(resul);

        resul.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                txtResulKeyPressed(e);
            }

            // Eventos al presionar una tecla
            private void txtResulKeyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ADD) {
                    btnMasAP();
                } else if (e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
                    btnMenosAP();
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    teclaIgual();
                } else if (e.getKeyCode() == KeyEvent.VK_DIVIDE) {
                    btnDivAP();
                } else if (e.getKeyCode() == KeyEvent.VK_MULTIPLY) {
                    btnMultiAP();
                } else if (e.getKeyCode() == 110 || e.getKeyCode() == 46) {
                    teclaPunto();
                } else if (e.getKeyCode() == 8) {
                    teclaBorrar();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose();
                }
            }

            public void keyTyped(KeyEvent e) {
                txtResulKeyTyped(e);
            }

            private void txtResulKeyTyped(KeyEvent e) {
                img.cr.solonumerosCalc(e.getKeyChar(), e, resul.getText(), 15);
                img.cr.soloUnPunto(e.getKeyChar(), e, resul.getText());
            }
        });

        num = new JButton[3][3];
        int x = 50;
        int y = 100;
        int n = 7;

        for (int f = 0; f < 3; f++) {
            for (int c = 0; c < 3; c++) {
                num[f][c] = img.getButton(resul.getText() + String.valueOf(n), null, this, this);
                num[f][c].setBounds(x, y, 43, 43);
                n++;
                x = x + 43;
            }
            n -= 6;
            x = 50;
            y = y + 43;
        }

        cero = img.getButton("0", null, this, this);
        cero.setBounds(50, 229, 86, 43);

        punto = img.getButton(".", null, this, this);
        punto.setBounds(136, 229, 43, 43);

        mas = img.getButton("+", null, this, this);
        mas.setBounds(179, 229, 43, 43);

        menos = img.getButton("-", null, this, this);
        menos.setBounds(179, 186, 43, 43);

        multi = img.getButton("*", null, this, this);
        multi.setBounds(179, 143, 43, 43);

        div = img.getButton("/", null, this, this);
        div.setBounds(179, 100, 43, 43);

        igual = img.getButton("=", img.cr.AZUL, this, this);
        igual.setBounds(222, 186, 43, 86);

        raiz = img.getButton("?", null, this, this);
        raiz.setBounds(222, 143, 43, 43);

        poten = img.getButton("^", null, this, this);
        poten.setBounds(222, 100, 43, 43);

        borrar = img.getButton("?", img.cr.ROJO, this, this);
        borrar.setBounds(179, 57, 86, 43);

        vaciar = img.getButton("C", null, this, this);
        vaciar.setBounds(136, 57, 43, 43);

        euler = img.getButton("e^", null, this, this);
        euler.setBounds(93, 57, 43, 43);

        nega = img.getButton("±", null, this, this);
        nega.setBounds(50, 57, 43, 43);
    }

    // Recibe los valores a operar y el signo respectivo, devuelve el resultado de
    // la operación
    public String Operaciones(String valor1, String valor2, String signo) {

        double resultadocalc = 0.0;
        String respuesta;

        if (signo.equals("+")) {
            resultadocalc = Double.parseDouble(valor1) + Double.parseDouble(valor2);
        } else if (signo.equals("-")) {
            resultadocalc = Double.parseDouble(valor1) - Double.parseDouble(valor2);
        } else if (signo.equals("*")) {
            resultadocalc = Double.parseDouble(valor1) * Double.parseDouble(valor2);
        } else if (signo.equals("/")) {
            resultadocalc = Double.parseDouble(valor1) / Double.parseDouble(valor2);
        } else if (signo.equals("x^y")) {
            resultadocalc = Math.pow(Double.parseDouble(valor1), Double.parseDouble(valor2));
        }

        respuesta = String.valueOf(resultadocalc);
        return respuesta;
    }

    private void btnCeroAP() {

        if (resul.getText().length() < 15) {
            resul.setText(resul.getText() + "0");
        }
    }

    private void btnPuntoAP() {

        contenido = resul.getText();

        if (resul.getText().length() < 15) {

            if (contenido.length() <= 0) {
                resul.setText("0.");
            } else if (!resul.getText().contains(".")) {
                resul.setText(resul.getText() + ".");
                point = false;
            }
        }
    }

    private void teclaPunto() {

        contenido = resul.getText();

        if (contenido.length() <= 0 && point != true) {
            resul.setText("0");
            point = true;
        } else if (resul.getText().contains(".")) {
            point = true;
        }
    }

    private void btnMasAP() {

        if (!resul.getText().equals("")) {
            valor1 = resul.getText();
            signo = "+";
            resul.setText("");
        }
    }

    private void btnMenosAP() {

        if (!resul.getText().equals("")) {
            valor1 = resul.getText();
            signo = "-";
            resul.setText("");
        }
    }

    private void btnPotenAP() {

        try {

            if (!resul.getText().equals("")) {
                valor1 = resul.getText();
                signo = "x^y";
                resul.setText("");
            }

        } catch (Exception e) {
            img.cr.mostrarMensaje("Error", "Operación inválida.");
        }
    }

    private void btnRaizAP() {

        try {

            contenido = resul.getText();

            if (contenido.length() > 0) {
                resultado = Math.sqrt(Double.parseDouble(contenido));
                resul.setText(String.valueOf(resultado));
            }

        } catch (Exception e) {
            img.cr.mostrarMensaje("Error", "Operación inválida.");
        }
    }

    private void btnVaciarAP() {
        resul.setText("");
        point = false;
        resultado = 0;
        resul.requestFocus();
    }

    private void btnMultiAP() {

        if (!resul.getText().equals("")) {
            valor1 = resul.getText();
            signo = "*";
            resul.setText("");
        }
    }

    private void btnDivAP() {

        if (!resul.getText().equals("")) {
            valor1 = resul.getText();
            signo = "/";
            resul.setText("");
        }
    }

    private void btnEulerAP() {

        try {

            contenido = resul.getText();

            if (contenido.length() > 0) {
                resultado = Math.exp(Double.parseDouble(contenido));
                resul.setText(String.valueOf(resultado));
            }

        } catch (Exception e) {
            img.cr.mostrarMensaje("Error", "Operación inválida.");
        }
    }

    private void btnNegaAP() {

        try {

            contenido = resul.getText();

            if (contenido.length() > 0) {
                resultado = (-1) * Double.parseDouble(contenido);
                resul.setText(String.valueOf(resultado));
            }

        } catch (Exception e) {
            img.cr.mostrarMensaje("Error", "Operación inválida.");
        }
    }

    private void btnBorrarAP() {

        if (!resul.getText().equals("") && !resul.getText().equals("0")) {
            resul.setText(resul.getText().substring(0, resul.getText().length() - 1));
        } else {
            resul.setText("");
            point = false;
            resultado = 0;
            sw = 1;
            s = 1;
        }
    }

    private void teclaBorrar() {

        if (resul.getText().equals("") && !resul.getText().equals("0")) {
            resul.setText("");
            point = false;
            resultado = 0;
            sw = 1;
            s = 1;
        }
    }

    private void btnIgualAP() {

        try {

            String resultadoTot;
            valor2 = resul.getText();

            if (!valor2.equals("")) {
                resultadoTot = Operaciones(valor1, valor2, signo);
                resul.setText(resultadoTot);
            }

        } catch (Exception e) {
            img.cr.mostrarMensaje("Error", "Operación inválida.");
        }
    }

    private void teclaIgual() {

        try {

            String resultadoTot;
            valor2 = resul.getText().substring(1, resul.getText().length());

            if (!valor2.equals("")) {
                resultadoTot = Operaciones(valor1, valor2, signo);
                resul.setText(resultadoTot);
            }

        } catch (Exception e) {
            img.cr.mostrarMensaje("Error", "Operación inválida.");
        }
    }

    // Eventos al presionar un botón
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == cero) {
            btnCeroAP();
        } else if (e.getSource() == punto) {
            btnPuntoAP();
        } else if (e.getSource() == borrar) {
            btnBorrarAP();
        } else if (e.getSource() == vaciar) {
            btnVaciarAP();
        } else if (e.getSource() == nega) {
            btnNegaAP();
        } else if (e.getSource() == euler) {
            btnEulerAP();
        } else if (e.getSource() == div) {
            btnDivAP();
        } else if (e.getSource() == multi) {
            btnMultiAP();
        } else if (e.getSource() == menos) {
            btnMenosAP();
        } else if (e.getSource() == mas) {
            btnMasAP();
        } else if (e.getSource() == raiz) {
            btnRaizAP();
        } else if (e.getSource() == poten) {
            btnPotenAP();
        } else if (e.getSource() == igual) {
            btnIgualAP();
        }

        for (int f = 0; f < 3; f++) {
            for (int c = 0; c < 3; c++) {

                if (resul.getText().length() >= 15) {
                    break;
                }

                if (e.getSource() == num[f][c] && sw == 1 && s == 1) {
                    resul.setText("");
                    resul.setText(resul.getText() + num[f][c].getText());
                    sw = 0;

                } else if (e.getSource() == num[f][c] && (sw == 0 || s == 0)) {
                    resul.setText(resul.getText() + num[f][c].getText());
                }
            }
        }
    }
}