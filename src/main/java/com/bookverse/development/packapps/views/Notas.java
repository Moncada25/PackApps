package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.core.Resources;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Notas extends JDialog implements ActionListener {

    private static final int CANTIDAD_NOTAS = 10;
    private int notaMaxima, notaMinima;
    private JLabel notas, porc, name, scale, img;
    private JTextField nombre;
    private JButton btncalcular, btnaddNote, btndeleteNote, btnreset, btnsalir;
    private Resources h = new Resources();
    private float[] porcent = new float[CANTIDAD_NOTAS];
    private float[] notes = new float[CANTIDAD_NOTAS];
    private float notaTotal = 0, porcTotal = 0, notaFalta = 0;
    private JTextField[] notitas = new JTextField[CANTIDAD_NOTAS];
    private JComboBox<String>[] porcentajes = new JComboBox[CANTIDAD_NOTAS];
    private JRadioButton scale1, scale2;
    private ButtonGroup btngroup;
    private int notasHay = 1;

    public Notas(JFrame parent, boolean modal) {
        super(parent, modal);
        Componentes();
    }

    @SuppressWarnings("unchecked")
    public void Componentes() {

        setLayout(null);
        setDefaultCloseOperation(0);
        setIconImage(new ImageIcon(h.getImage("notas.png")).getImage());

        btnsalir = h.getBoton("Return", h.cr.ROJO, this, this);
        btnsalir.setBounds(248, 330, 86, 30);

        btncalcular = h.getBoton("Show", h.cr.AZUL, this, this);
        btncalcular.setBounds(200, 280, 86, 30);

        btnaddNote = h.getBoton("Add", h.cr.AZUL, this, this);
        btnaddNote.setBounds(200, 220, 86, 30);

        btndeleteNote = h.getBoton("Delete", h.cr.ROJO, this, this);
        btndeleteNote.setBounds(300, 220, 86, 30);
        btndeleteNote.setEnabled(false);

        btnreset = h.getBoton("Reset", h.cr.ROJO, this, this);
        btnreset.setBounds(300, 280, 86, 30);
        btnreset.setEnabled(false);

        int x = 30;
        int y = 60;

        for (int i = 0; i < notitas.length; i++) {

            notitas[i] = new JTextField();
            notitas[i].setBounds(x, y, 50, 25);
            notitas[i].setHorizontalAlignment(JTextField.CENTER);
            add(notitas[i]);

            if (i != 0) {
                notitas[i].setVisible(false);
            }

            int j = i;

            notitas[i].addKeyListener(new KeyAdapter() {

                public void keyTyped(KeyEvent e) {
                    txtNumKeyTyped(e);
                }

                public void txtNumKeyTyped(KeyEvent e) {
                    h.cr.solonumerosYpunto(e.getKeyChar(), e, notitas[j].getText(), 3);
                    h.cr.PuntoMedio(e.getKeyChar(), e, notitas[j].getText());
                }
            });
            y += 40;
        }

        x = 130;
        y = 60;

        for (int i = 0; i < porcentajes.length; i++) {
            porcentajes[i] = new JComboBox<String>();
            porcentajes[i].setBounds(x, y, 58, 25);
            for (int j = 1; j <= 100; j++) {
                porcentajes[i].addItem(String.valueOf(j));
            }
            add(porcentajes[i]);
            porcentajes[i].setSelectedIndex(19);

            if (i != 0) {
                porcentajes[i].setVisible(false);
            }

            y += 40;
        }

        notas = h.getLabel("<html><strong>Notes</strong></html>", h.cr.ROJO, this, h.cr.MEDIUM);
        notas.setBounds(30, 30, 100, 30);

        porc = h.getLabel("<html><strong>%</strong></html>", h.cr.ROJO, this, h.cr.MEDIUM);
        porc.setBounds(145, 30, 100, 30);

        name = h.getLabel("<html><strong>Name</strong></html>", h.cr.ROJO, this, h.cr.MEDIUM);
        name.setBounds(270, 30, 100, 30);

        nombre = new JTextField();
        nombre.setBounds(200, 60, 186, 25);
        nombre.setHorizontalAlignment(JTextField.CENTER);
        add(nombre);

        nombre.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                txtNumKeyTyped(e);
            }

            public void txtNumKeyTyped(KeyEvent e) {
                h.cr.soloAlfa(e.getKeyChar(), e, nombre.getText(), 20);
            }
        });

        scale = h.getLabel("<html><strong>Scale</strong></html>", h.cr.ROJO, this, h.cr.MEDIUM);
        scale.setBounds(270, 110, 100, 30);

        btngroup = new ButtonGroup();

        scale1 = new JRadioButton("<html><strong>0 o 5</strong></html>");
        scale1.setBounds(200, 140, 100, 30);
        scale1.addActionListener(this);
        scale1.setForeground(h.cr.AZUL);
        add(scale1);
        btngroup.add(scale1);
        scale1.setSelected(true);

        scale2 = new JRadioButton("<html><strong>0 o 10</strong></html>");
        scale2.setBounds(300, 140, 100, 30);
        scale2.setForeground(h.cr.AZUL);
        scale2.addActionListener(this);
        add(scale2);
        btngroup.add(scale2);

        img = h.getLabel("", null, this, null);
        img.setBounds(240, 360, 96, 96);
    }

    public void ajustarEscala() {

        if (scale1.isSelected()) {
            notaMaxima = 5;
            notaMinima = 3;
        } else if (scale2.isSelected()) {
            notaMaxima = 10;
            notaMinima = 5;
        }
    }

    public boolean revisarCampos() {

        for (int i = 0; i < notasHay; i++) {

            if (notitas[i].getText().equals("") || Float.parseFloat(notitas[i].getText()) > notaMaxima
                    || nombre.getText().trim().equals("")) {
                return false;
            }
        }
        return true;
    }

    public void btnAddAP() {

        notitas[notasHay].setVisible(true);
        porcentajes[notasHay].setVisible(true);
        btnreset.setEnabled(true);
        btndeleteNote.setEnabled(true);

        notasHay++;

        if (notasHay == 10) {
            btnaddNote.setEnabled(false);
        }
    }

    public void btnDeleteAP() {

        notitas[notasHay - 1].setVisible(false);
        porcentajes[notasHay - 1].setVisible(false);
        notitas[notasHay - 1].setText("");
        porcentajes[notasHay - 1].setSelectedIndex(19);
        btnaddNote.setEnabled(true);

        notasHay--;

        if (notasHay == 1) {
            btnreset.setEnabled(false);
            btndeleteNote.setEnabled(false);
        }
    }

    public void pasarDatos() {

        for (int i = 0; i < notasHay; i++) {
            porcent[i] = Integer.parseInt(porcentajes[i].getSelectedItem().toString());
            notes[i] = Float.parseFloat(notitas[i].getText());
        }
    }

    public void sumarNotas() {

        for (int i = 0; i < notasHay; i++) {

            porcTotal += porcent[i];
            notaTotal += (notes[i] * porcent[i]) / 100;
        }

        notaFalta = (notaMinima - notaTotal) / ((100 - porcTotal) / 100);
    }

    public void insertar(String state) {

        if (h.cr.comprobarConexion("Datos no guardados", true) && h.cr.saveGame()) {

            try {

                String op = "";

                if (scale1.isSelected()) {
                    op = "0 to 5";
                } else if (scale2.isSelected()) {
                    op = "1 to 9";
                }

                String data[] = {"notas", nombre.getText(), op, String.format("%.0f", porcTotal),
                        String.format("%.2f", notaTotal), state, h.cr.obtenerDate()};
                h.db.insertData(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        btnResetAP();
    }

    public void btnCalculateAP() {

        ajustarEscala();

        if (revisarCampos()) {

            notaTotal = 0;
            porcTotal = 0;

            pasarDatos();

            sumarNotas();

            if (porcTotal == 100) {

                if (notaTotal < notaMinima) {
                    img.setIcon(new ImageIcon(h.getImage("dead.png")));
                } else {
                    img.setIcon(new ImageIcon(h.getImage("win.png")));
                }

                JOptionPane.showMessageDialog(null,
                        "<html>" + h.cr.styleJOption() + "<strong>Name: </strong>" + nombre.getText() + "<br>" + "<strong>Nota: </strong>"
                                + String.format("%.2f", notaTotal) + "</html>",
                        "Definitiva", JOptionPane.PLAIN_MESSAGE);

                if (notaTotal < notaMinima) {
                    insertar("Reproved");
                } else {
                    insertar("Approved");
                }

            } else if (porcTotal > 100) {

                JOptionPane.showMessageDialog(null, "<html>" + h.cr.styleJOption() + "<strong>Porcentaje excedido</strong></html>", "¡Verifica!",
                        JOptionPane.PLAIN_MESSAGE);

            } else if (notaFalta > notaMaxima) {

                img.setIcon(new ImageIcon(h.getImage("dead.png")));

                JOptionPane.showMessageDialog(null,
                        "<html>" + h.cr.styleJOption() + "<strong>No hay nada que hacer, ya mejor cancela...</strong><br><br>"
                                + "<strong>Nota acumulada: </strong>" + String.format("%.2f", notaTotal) + "<br>"
                                + "<strong>Necesitarías sacar " + String.format("%.2f", notaFalta) + " en el " + String.format("%.0f", 100 - porcTotal) + "% restante</strong></html>",
                        "Ay :(", JOptionPane.PLAIN_MESSAGE);

                insertar("Reproved");

            } else {

                if (notaTotal >= 0 && notaTotal < notaMinima) {

                    JOptionPane.showMessageDialog(null,
                            "<html>" + h.cr.styleJOption() + "<strong>Name: </strong>" + nombre.getText() + "<br>"
                                    + "<strong>Nota necesaria para ganar: </strong>" + String.format("%.2f", notaFalta) + "<br>"
                                    + "<strong>Porcentaje restante: </strong>" + String.format("%.0f", 100 - porcTotal)
                                    + "%" + "</html>",
                            "Resultado", JOptionPane.PLAIN_MESSAGE);

                    insertar("Maybe");

                } else if (notaTotal >= notaMinima) {

                    img.setIcon(new ImageIcon(h.getImage("win.png")));

                    JOptionPane.showMessageDialog(null,
                            "<html>" + h.cr.styleJOption() + "<strong>¡Felicidades, ya has aprovado!</strong><br><br>"
                                    + "<strong>ID: </strong>" + nombre.getText()
                                    + "<br>" + "<strong>Nota acumulada: </strong>" + String.format("%.2f", notaTotal)
                                    + "</html>",
                            "¡Ganaste!", JOptionPane.PLAIN_MESSAGE);
                    insertar("Approved");
                }
            }

        } else {

            JOptionPane.showMessageDialog(null,
                    "<html>" + h.cr.styleJOption() + "<strong>Revisa los siguientes datos</strong><br><br>"
                            + "<strong>Notas: </strong>menor o igual a " + notaMaxima + "<br>"
                            + "<strong>Campos: </strong>vacíos" + "</html>",
                    "¡Verifica!", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void btnResetAP() {

        for (int i = 1; i < notasHay; i++) {
            notitas[i].setVisible(false);
            porcentajes[i].setVisible(false);
            notitas[i].setText("");
            porcentajes[i].setSelectedIndex(19);
        }

        nombre.setText("");
        notitas[0].setText("");
        porcentajes[0].setSelectedIndex(19);
        notasHay = 1;
        img.setIcon(null);
        btnaddNote.setEnabled(true);
        btndeleteNote.setEnabled(false);
        btnreset.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnaddNote) {
            btnAddAP();
        } else if (e.getSource() == btndeleteNote) {
            btnDeleteAP();
        } else if (e.getSource() == btncalcular) {
            btnCalculateAP();
        } else if (e.getSource() == btnreset) {
            btnResetAP();
        } else if (e.getSource() == btnsalir) {
            h.cr.fadeOut(this);
        }
    }
}