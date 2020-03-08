package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.core.Core;
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

public class Colas extends JDialog implements ActionListener, MouseListener {

    private JLabel[] botones = new JLabel[8];
    private int i = 0, x = 50, y = 400, cont = 0, num, con = 0;
    private double suma = 0, sumaT = 0, sum = 0;
    private String cadena = "";
    private boolean sw = true;
    private Resources h = new Resources();
    private JLabel titulo, mensaje, puerta;
    private JButton[] cola = new JButton[50];

    public Colas(JDialog parent, boolean modal) {

        super(parent, modal);
        Componentes();
    }

    public Colas() {
        Componentes();
    }

    public void getPanel() {

        JPanel panel = new JPanel(new FlowLayout());

        String[] imgs = {"push.png", "pop.png", "peek.png", "contar.png", "sumar.png", "promedio.png", "pares.png",
                "vaciar.png"};

        panel.setBorder(h.core.bordeAzul("Select Action"));

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

        for (int j = 0; j < cola.length; j++) {

            cola[j] = h.getButton("", null, this, this);
            cola[j].setBounds(x, y, 80, 40);
            cola[j].setForeground(h.core.ROJO);
            cola[j].setVisible(false);

            if (sw) {

                if (x == 770) {
                    sw = false;
                    y = y - 60;
                    x -= 80;
                }

                x += 80;

            } else {

                if (x == 50) {
                    sw = true;
                    y = y - 60;
                    x += 80;
                }

                x -= 80;
            }
        }

        getPanel();

        puerta = new JLabel();
        puerta.setBounds(0, 380, 80, 80);
        puerta.setIcon(new ImageIcon(h.getImage("puerta.png")));
        puerta.addMouseListener(this);
        puerta.setVisible(false);
        add(puerta);

        titulo = h.getLabel("", h.core.ROJO, this, h.core.BIG);
        titulo.setBounds(50, 0, 900, 150);

        mensaje = h.getLabel("", h.core.ROJO, this, h.core.BIG);
        mensaje.setBounds(620, 480, 200, 85);
    }

    public Object Peek() {
        // debe retornar el primero de la cola, el próximo en desencolarse
        return cola[0].getText();
    }

    public void btnMostrar() {

        if (i > 0) {
            titulo.setText("<html>" + h.core.styleJOption() + "<strong>Próximo dato ? " + Peek() + "</strong></html>");
        } else {
            h.core.mostrarMensaje("Message", "La cola está vacía");
        }
    }

    public void reset() {
        i = 0;
        x = 50;
        y = 400;
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
                cola[j].setText("");
                cola[j].setVisible(false);
                cola[j].setBackground(getBackground());
            }

            puerta.setVisible(false);
            reset();

            titulo.setText("<html><strong>Queue Empty</strong></html>");

        } else {
            h.core.mostrarMensaje("Message", "La cola está vacía");
            reset();
        }
    }

    private void Pares() {

        for (int n = 0; n < i; n++) {

            if (Integer.parseInt(cola[n].getText()) % 2 == 0) {

                if (cont % 10 == 0) {
                    cadena += "\n";
                }

                cadena += "[" + cola[n].getText() + "]";
                cont++;
            }
        }

        if (i == 0) {
            h.core.mostrarMensaje("Message", "La cola está vacía");
        } else if (cont == 0) {
            titulo.setText("<html>" + h.core.styleJOption() + "<strong>No hay pares en la cola</strong></html>");
        } else {
            titulo.setText("<html>" + h.core.styleJOption() + "<strong>Cantidad de pares ? " + cont + "</strong></html>");
            h.core.mostrarMensaje("Resultado", "<html>" + h.core.styleJOption() + "<strong>Números pares de la cola</strong></html><br>" + cadena);
        }

        cont = 0;
        cadena = "";
    }

    private void Promediar() {

        if (i == 0) {
            h.core.mostrarMensaje("Message", "La cola está vacía");
        } else {
            titulo.setText("<html>" + h.core.styleJOption() + "<strong>Promedio de la cola ? " + String.format("%.2f", Sumar() / Contar())
                    + "</strong></html>");
        }
    }

    private double Sumar() {

        if (i == 0) {
            h.core.mostrarMensaje("Message", "La cola está vacía");
        } else {

            for (int n = 0; n < i; n++) {

                suma += Double.parseDouble(cola[n].getText());
            }
            titulo.setText("<html>" + h.core.styleJOption() + "<strong>Los elementos de la cola suman ? " + (int) suma + "</strong></html>");

            sumaT = suma;
            suma = 0;
        }
        return sumaT;
    }

    private int Contar() {

        if (i == 0) {
            h.core.mostrarMensaje("Message", "La cola está vacía");
        } else {
            titulo.setText("<html><strong>Hay " + i + " elementos en la cola</strong></html>");
        }
        return i;
    }

    public Object Pop() {

        Object aux = cola[0].getText();

        i--;

        if (cola[0].getBackground() == h.core.AZUL) {
            con--;
            sum -= Double.parseDouble((cola[0].getText()));
            cola[0].setBackground(getBackground());
        }

        cola[i].setVisible(false);

        if (i < 1) {
            puerta.setVisible(false);
        }

        for (int k = 0; k < i; k++) {
            cola[k].setText(cola[k + 1].getText());
            cola[k].setBackground(cola[k + 1].getBackground());
        }

        return aux;
    }

    public void btnDesencolarAP() {

        titulo.setText("");

        if (i > 0) {

            titulo.setText("<html><strong>Se desencoló ? " + Pop() + "</strong></html>");

        } else {
            h.core.mostrarMensaje("Message", "La cola está vacía");
            reset();
        }
    }

    public void Push(int dato) {

        puerta.setVisible(true);
        cola[i].setVisible(true);
        cola[i].setBackground(getBackground());
        cola[i].setText(String.valueOf(dato));
        titulo.setText("<html><strong>Se encoló ? " + cola[i].getText() + "</strong></html>");
    }

    public void btnEncolarAP() {

        titulo.setText("");

        if (i < cola.length) {
            do {
                num = Integer.parseInt(Core.enterNumber("Ingresa un número", 6));

                Push(num);


                i++;

            } while (JOptionPane.showConfirmDialog(null, "¿Desea ingresar más datos?", "Ingreso de datos",
                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION && i < cola.length);

            titulo.setText("");

        } else {
            h.core.mostrarMensaje("Message", "La cola está llena");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == botones[0]) {
            btnEncolarAP();
        } else if (e.getSource() == botones[1]) {
            btnDesencolarAP();
        } else if (e.getSource() == botones[2]) {
            btnMostrar();
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
        } else if (e.getSource() == puerta) {
            h.core.mostrarMensaje("Message", "Te la creíste, no hay bonus :(");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if (e.getSource() == botones[0]) {
            botones[0].setCursor(h.core.MANO);
            mensaje.setText("<html><strong>Push( )</strong></html>");
        } else if (e.getSource() == botones[1]) {
            botones[1].setCursor(h.core.MANO);
            mensaje.setText("<html><strong>Pop( )</strong></html>");
        } else if (e.getSource() == botones[2]) {
            botones[2].setCursor(h.core.MANO);
            mensaje.setText("<html><strong>Peek( )</strong></html>");
        } else if (e.getSource() == botones[3]) {
            botones[3].setCursor(h.core.MANO);
            mensaje.setText("<html><strong>Contar</strong></html>");
        } else if (e.getSource() == botones[4]) {
            botones[4].setCursor(h.core.MANO);
            mensaje.setText("<html><strong>Sumar</strong></html>");
        } else if (e.getSource() == botones[5]) {
            botones[5].setCursor(h.core.MANO);
            mensaje.setText("<html><strong>Promediar</strong></html>");
        } else if (e.getSource() == botones[6]) {
            botones[6].setCursor(h.core.MANO);
            mensaje.setText("<html><strong>Números Pares</strong></html>");
        } else if (e.getSource() == botones[7]) {
            botones[7].setCursor(h.core.MANO);
            mensaje.setText("<html><strong>Vaciar</strong></html>");
        } else if (e.getSource() == puerta) {
            puerta.setCursor(h.core.MANO);
            mensaje.setText("<html><strong>Bonus</strong></html>");
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
        } else if (e.getSource() == puerta) {
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

            if (e.getSource() == cola[j]) {

                if (cola[j].getBackground() == h.core.AZUL) {
                    cola[j].setBackground(getBackground());
                    con--;
                    sum -= Double.parseDouble(cola[j].getText());
                } else {
                    cola[j].setBackground(h.core.AZUL);
                    con++;
                    sum += Double.parseDouble(cola[j].getText());
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