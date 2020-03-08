package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.core.Resources;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Comment extends JDialog implements ActionListener, MouseListener {

    private JLabel mensaje, usuario, required, message;
    private JButton btnenviar, btnsalir;
    private JTextArea texto;
    private JTextField user;
    private JScrollPane scroll;
    private Resources h = new Resources();

    public Comment(JFrame parent, boolean modal) {

        super(parent, modal);
        componentes();
    }

    public Comment() {
        componentes();
    }

    // Se crean los componentes de la ventana
    public void componentes() {

        setLayout(null); // Permite el posicionamiento absoluto de los componentes
        setIconImage(new ImageIcon(h.getImage("coment.png")).getImage());
        setDefaultCloseOperation(0);

        btnenviar = h.getButton("Send", h.core.AZUL, this, this);
        btnenviar.setBounds(140, 400, 86, 30);

        btnsalir = h.getButton("Return", h.core.ROJO, this, this);
        btnsalir.setBounds(250, 400, 86, 30);

        mensaje = h.getLabel("<html><strong><em>Write Commentary</em></strong></html>", h.core.ROJO, this, h.core.BIG);
        mensaje.setBounds(130, 10, 370, 50);

        usuario = h.getLabel("<html><strong>User</strong></html>", h.core.AZUL, this, h.core.MEDIUM);
        usuario.setBounds(100, 60, 100, 50);

        required = h.getLabel("<html><strong>*</strong></html>", h.core.ROJO, this, h.core.MEDIUM);
        required.setBounds(139, 79, 8, 8);
        required.addMouseListener(this);

        user = new JTextField();
        user.setBounds(30, 100, 190, 30);
        user.setHorizontalAlignment(JTextField.CENTER);
        add(user);

        user.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {
                userKeyTyped(e);
            }

            private void userKeyTyped(KeyEvent e) {
                h.core.soloAlfa(e.getKeyChar(), e, user.getText(), 20);
            }
        });

        message = h.getLabel("<html><strong>Message</strong></html>", h.core.AZUL, this, h.core.MEDIUM);
        message.setBounds(30, 150, 370, 30);

        texto = new JTextArea();
        scroll = new JScrollPane(texto);
        scroll.setBounds(30, 180, 420, 200);
        add(scroll);
    }

    public void enviarComentario(String username, String commentary) {

        if (h.core.comprobarConexion("Aseg�rate de estar conectado a una red", true)) {

            String data[] = {"feedback", username, commentary, h.core.obtenerDate()};

            if (h.database.insertData(data)) {

                JOptionPane.showMessageDialog(null,
                        "<html>" + h.core.styleJOption() + "<strong><center>Comentario enviado</center></strong><br>"
                                + "Feedback enviado exitosamente, su opini�n ser� tomada en cuenta." + "</html>",
                        "��xito!", JOptionPane.PLAIN_MESSAGE);

                user.setText("");
                texto.setText("");
            }

        }
    }

    public void btnEnviarAP() {

        if (user.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "<html>" + h.core.styleJOption() + "<strong>Username empty</strong></html>", "�Verifique!",
                    JOptionPane.PLAIN_MESSAGE);
            user.requestFocus();

        } else {

            if (texto.getText().trim().length() < 3) {

                JOptionPane.showMessageDialog(null, "<html>" + h.core.styleJOption() + "<strong>Mensaje demasiado corto</strong></html>",
                        "�Verifique!", JOptionPane.PLAIN_MESSAGE);
                texto.requestFocus();
            } else {
                enviarComentario(user.getText(), texto.getText());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnenviar) {
            btnEnviarAP();
        } else if (e.getSource() == btnsalir) {
            h.core.fadeOut(this);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if (e.getSource() == required) {
            JOptionPane.showMessageDialog(null,
                    "<html>" + h.core.styleJOption() + "<strong><center>Completa este campo</center></strong><br>"
                            + "Username necesario para almacenar su mensaje,<br>"
                            + "el comentario podr� ser le�do por el desarrollador.</html>",
                    "�Campo requerido!", JOptionPane.PLAIN_MESSAGE);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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