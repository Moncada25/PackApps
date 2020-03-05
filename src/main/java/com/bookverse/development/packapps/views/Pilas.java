package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.core.Resources;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Pilas extends JDialog implements MouseListener, ActionListener {

    private JLabel[] botones = new JLabel[8];
    private int i = 0, y = 400, x = 50, cont = 0, num, con = 0;
    private double suma = 0, sumaT = 0, sum = 0;
    private String cadena = "";
    private Resources h = new Resources();
    private JLabel titulo, mensaje;
    private JButton[] pila = new JButton[50];

    public Pilas(JDialog parent, boolean modal) {

        super(parent, modal);
        Componentes();
    }

    public Pilas() {
        Componentes();
    }

    public void getPanel() {

        JPanel panel = new JPanel(new FlowLayout());

        String[] imgs = {"push.png", "pop.png", "peek.png", "contar.png", "sumar.png", "promedio.png", "pares.png",
                "vaciar.png"};

        panel.setBorder(h.cr.bordeAzul("Select Action"));

        /* ICONOS */
        for (int i = 0; i < botones.length; i++) {
            botones[i] = new JLabel();
            botones[i].setIcon(new ImageIcon(h.getImage(imgs[i])));
            botones[i].addMouseListener(this);
            panel.add(botones[i]);
        }

        panel.setBounds(50, 480, 480, 100);
        add(panel);
    }

    public void Componentes() {

        setLayout(null);

        int c = 0;

        for (int j = 0; j < pila.length; j++) {

            pila[j] = h.getButton("", null, this, this);
            pila[j].setBounds(x, y, 80, 40);
            pila[j].setForeground(h.cr.ROJO);
            pila[j].setVisible(false);
            y -= 40;

            c++;

            if (c == 10) {
                x += 100;
                y = 400;

                c = 0;
            }
        }

        getPanel();

        titulo = h.getLabel("", h.cr.ROJO, this, h.cr.BIG);
        titulo.setBounds(550, 70, 600, 200);

        mensaje = h.getLabel("", h.cr.ROJO, this, h.cr.BIG);
        mensaje.setBounds(620, 480, 200, 85);
    }

    public void btnApilarAP() {

        titulo.setText("");

        if (pila.length > i) {

            do {
                num = Integer.parseInt(h.cr.ingreseNumero("Ingresa un número", 6));

                Push(num);
            } while (JOptionPane.showConfirmDialog(null, "¿Desea ingresar más datos?", "Ingreso de datos",
                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION && pila.length > i);

            titulo.setText("");

        } else {
            JOptionPane.showMessageDialog(null,
                    "<html>" + h.cr.styleJOption() + "<strong>La pila está llena</strong></html>", "Mensaje",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void btnDesapilarAP() {

        titulo.setText("");

        if (i > 0) {
            pila[i - 1].setVisible(false);

            titulo.setText("<html><strong>Se desapiló ? " + Pop(pila, i) + "</strong></html>");

            if (pila[i - 1].getBackground() == h.cr.AZUL) {
                con--;
                sum -= Double.parseDouble((pila[i - 1].getText()));
                pila[i - 1].setBackground(getBackground());
            }

            y += 40;
            i--;
        } else {
            JOptionPane.showMessageDialog(null,
                    "<html>" + h.cr.styleJOption() + "<strong>La pila está vacía</strong></html>", "Mensaje",
                    JOptionPane.PLAIN_MESSAGE);
            reset();
        }
    }

    public void btnMostrarAP() {

        if (i > 0) {
            titulo.setText("<html><strong>Próximo dato ? " + Peek() + "</strong></html>");
        } else {
            JOptionPane.showMessageDialog(null,
                    "<html>" + h.cr.styleJOption() + "<strong>La pila está vacía</strong></html>", "Mensaje",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void reset() {
        i = 0;
        y = 400;
        x = 50;
        con = 0;
        sum = 0;
        suma = 0;
        sumaT = 0;
    }

    public void btnReturnAP() {

        reset();

        dispose();
    }

    public void Vaciar() {

        if (i > 0) {

            for (int j = 0; j < i; j++) {
                pila[j].setText("");
                pila[j].setVisible(false);
                pila[j].setBackground(getBackground());
            }

            reset();

            titulo.setText("<html><strong>Stack Empty</strong></html>");

        } else {
            JOptionPane.showMessageDialog(null,
                    "<html>" + h.cr.styleJOption() + "<strong>La pila está vacía</strong></html>", "Mensaje",
                    JOptionPane.PLAIN_MESSAGE);
            reset();
        }
    }

    public int Contar() {

        if (i == 0) {
            JOptionPane.showMessageDialog(null,
                    "<html>" + h.cr.styleJOption() + "<strong>La pila está vacía</strong></html>", "Mensaje",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            titulo.setText("<html><strong>Hay " + i + " elementos en la pila</strong></html>");
        }
        return i;
    }

    public double Sumar() {

        if (i == 0) {
            JOptionPane.showMessageDialog(null,
                    "<html>" + h.cr.styleJOption() + "<strong>La pila está vacía</strong></html>", "Mensaje",
                    JOptionPane.PLAIN_MESSAGE);
        } else {

            for (int n = 0; n < i; n++) {

                suma += Double.parseDouble(pila[n].getText());
            }
            titulo.setText("<html><strong>Los elementos de la pila<br>suman ? " + (int) suma + "</strong></html>");

            sumaT = suma;
            suma = 0;
        }
        return sumaT;
    }

    public void Promediar() {

        if (i == 0) {
            JOptionPane.showMessageDialog(null,
                    "<html>" + h.cr.styleJOption() + "<strong>La pila está vacía</strong></html>", "Mensaje",
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            titulo.setText(
                    "<html><strong>Promedio ? " + String.format("%.2f", Sumar() / Contar()) + "</strong></html>");
        }
    }

    public void Pares() {

        for (int n = 0; n < i; n++) {

            if (Integer.parseInt(pila[n].getText()) % 2 == 0) {

                if (cont % 10 == 0) {
                    cadena += "\n";
                }

                cadena += "[" + pila[n].getText() + "]";
                cont++;
            }
        }

        if (i == 0) {
            JOptionPane.showMessageDialog(null,
                    "<html>" + h.cr.styleJOption() + "<strong>La pila está vacía</strong></html>", "Mensaje",
                    JOptionPane.PLAIN_MESSAGE);
        } else if (cont == 0) {
            titulo.setText("<html><strong>No hay pares en la pila</strong></html>");
        } else {
            titulo.setText("<html><strong>Cantidad de pares ? " + cont + "</strong></html>");
            JOptionPane.showMessageDialog(null,
                    "<html>" + h.cr.styleJOption() + "<strong>Números pares de la pila</strong></html><br>" + cadena,
                    "Resultado", JOptionPane.PLAIN_MESSAGE);
        }

        cont = 0;
        cadena = "";
    }

    public Object Pop(JButton[] p1, int i) {
        return p1[i - 1].getText();
    }

    public Object Peek() {
        return pila[i - 1].getText();
    }

    public void Push(int dato) {

        pila[i].setVisible(true);
        pila[i].setText(String.valueOf(dato));
        titulo.setText("<html><strong>Se apiló ? " + pila[i].getText() + "</strong></html>");
        i++;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == botones[0]) {
            btnApilarAP();
        } else if (e.getSource() == botones[1]) {
            btnDesapilarAP();
        } else if (e.getSource() == botones[2]) {
            btnMostrarAP();
        } else if (e.getSource() == botones[3]) {
            Contar();
        } else if (e.getSource() == botones[4]) {
            Sumar();
        } else if (e.getSource() == botones[5]) {
            Promediar();
        } else if (e.getSource() == botones[6]) {
            Pares();
        } else if (e.getSource() == botones[7]) {
            Vaciar();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if (e.getSource() == botones[0]) {
            botones[0].setCursor(h.cr.MANO);
            mensaje.setText("<html><strong>Push( )</strong></html>");
        } else if (e.getSource() == botones[1]) {
            botones[1].setCursor(h.cr.MANO);
            mensaje.setText("<html><strong>Pop( )</strong></html>");
        } else if (e.getSource() == botones[2]) {
            botones[2].setCursor(h.cr.MANO);
            mensaje.setText("<html><strong>Peek( )</strong></html>");
        } else if (e.getSource() == botones[3]) {
            botones[3].setCursor(h.cr.MANO);
            mensaje.setText("<html><strong>Contar</strong></html>");
        } else if (e.getSource() == botones[4]) {
            botones[4].setCursor(h.cr.MANO);
            mensaje.setText("<html><strong>Sumar</strong></html>");
        } else if (e.getSource() == botones[5]) {
            botones[5].setCursor(h.cr.MANO);
            mensaje.setText("<html><strong>Promediar</strong></html>");
        } else if (e.getSource() == botones[6]) {
            botones[6].setCursor(h.cr.MANO);
            mensaje.setText("<html><strong>Números Pares</strong></html>");
        } else if (e.getSource() == botones[7]) {
            botones[7].setCursor(h.cr.MANO);
            mensaje.setText("<html><strong>Vaciar</strong></html>");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        if (e.getSource() == botones[0]) {
            mensaje.setText("");
        } else if (e.getSource() == botones[1]) {
            mensaje.setText("");
        } else if (e.getSource() == botones[2]) {
            mensaje.setText("");
        } else if (e.getSource() == botones[3]) {
            mensaje.setText("");
        } else if (e.getSource() == botones[4]) {
            mensaje.setText("");
        } else if (e.getSource() == botones[5]) {
            mensaje.setText("");
        } else if (e.getSource() == botones[6]) {
            mensaje.setText("");
        } else if (e.getSource() == botones[7]) {
            mensaje.setText("");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int j = 0; j < i; j++) {

            if (e.getSource() == pila[j]) {

                if (pila[j].getBackground() == h.cr.AZUL) {
                    pila[j].setBackground(getBackground());
                    con--;
                    sum -= Double.parseDouble(pila[j].getText());
                } else {
                    pila[j].setBackground(h.cr.AZUL);
                    con++;
                    sum += Double.parseDouble(pila[j].getText());
                }

                if (con > 0) {
                    titulo.setText("<html><strong>Datos seleccionados ? " + con + "<br>" + "Suman ? " + (int) sum
                            + "<br>" + "Promedian ? " + String.format("%.2f", sum / con) + "</strong></html>");
                } else {
                    titulo.setText("<html><strong>Ningun dato seleccionado</strong></html>");
                }
            }
        }
    }
}