package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.utils.ArrayData.WORD_LIST;

import com.bookverse.development.packapps.core.Resources;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Ahorcado extends JDialog implements ActionListener, KeyListener, Runnable, MouseListener {

    private JComboBox<String> opciones;
    private JLabel titulo, jugadas, tiempo, numeroLetras, letras_precionadas, img;
    private JTextField txtletras;
    private JButton btnplay, btnsalir;
    private Thread hiloTime;
    Resources h = new Resources();
    private int cont = 0, tamano;
    private boolean bandera = false, cronometroActivo;
    private String lineas = "", cadena, letras = "", leter = "";
    private char[] palabra_secreta = new char[13];
    private char[] palabra = new char[13];


    public Ahorcado(JFrame parent, boolean modal) {

        super(parent, modal);
        componentes();
    }

    public Ahorcado() {
        componentes();
    }

    // Se crean los componentes de la ventana
    public void componentes() {

        setLayout(null); //Permite el posicionamiento absoluto de los componentes
        setDefaultCloseOperation(0);
        setIconImage(new ImageIcon(h.getImage("ahorcado.png")).getImage());

        btnplay = h.getBoton("Play", h.cr.AZUL, this, this);
        btnplay.setBounds(30, 160, 86, 30);

        btnsalir = h.getBoton("Return", h.cr.ROJO, this, this);
        btnsalir.setBounds(140, 160, 86, 30);

        titulo = h.getLabel("<html>"
                + "<strong><em>Categoría</em></strong>" +
                "</html>", h.cr.ROJO, this, h.cr.BIG);
        titulo.setBounds(30, 10, 120, 35);

        jugadas = h.getLabel("", h.cr.AZUL, this, h.cr.MEDIUM);
        jugadas.setBounds(30, 200, 250, 30);

        tiempo = h.getLabel("", h.cr.ROJO, this, new Font("Times New Roman", 0, 50));
        tiempo.setBounds(75, 220, 200, 120);

        letras_precionadas = h.getLabel("", h.cr.AZUL, this, h.cr.MEDIUM);
        letras_precionadas.setBounds(30, 400, 600, 40);

        opciones = new JComboBox<String>();
        opciones.setBounds(30, 50, 220, 30);
        opciones.setFont(h.cr.MEDIUM);
        add(opciones);

        opciones.addItem("Frutas");
        opciones.addItem("Animales");
        opciones.addItem("Países");
        opciones.addItem("Colores");
        opciones.addItem("Irregular Verbs");

        opciones.setModel(new DefaultComboBoxModel<String>(new String[]{"Seleccione una opción", "Animales",
                "Colores", "Deportes", "Frutas", "Irregular Verbs", "Países"}));

        txtletras = new JTextField();
        txtletras.setBounds(30, 100, 220, 40);
        add(txtletras);

        numeroLetras = h.getLabel("", h.cr.AZUL, this, h.cr.MEDIUM);
        numeroLetras.setBounds(260, 100, 130, 60);

        addKeyListener(this);
        addMouseListener(this);

        txtletras.setEditable(false);

        img = h.getLabel("", null, this, null);
        img.setBounds(620, 20, 96, 96);
    }

    public void btnPlayAP() {

        if (opciones.getSelectedItem().toString().equals("Seleccione una opción")) {
            h.cr.mostrarMensaje("Advertencia", "Seleccione una categoría.");
            cont = 0;
            jugadas.setText("");
            txtletras.setText("");
            tiempo.setText("");
        } else {

            iniciarCronometro();
            opciones.setEnabled(false);
            btnsalir.setEnabled(false);
            btnplay.setEnabled(false);
            cadena = null;
            cont = 0;
            txtletras.setText("");
            tiempo.setText("");
            jugadas.setText("Errores cometidos: " + cont);
            lineas = "";
            repaint();

            for (int i = 0; i <= palabra.length - 1; i++) {
                palabra[i] = ' ';
                palabra_secreta[i] = ' ';
            }

            String opcion = opciones.getSelectedItem().toString();
            int x = (int) (Math.random() * 15);

            if (opcion.equals("Frutas")) {
                cadena = WORD_LIST[0][x];
                tamano = cadena.length() - 1;

                for (int i = 0; i <= tamano; i++) {
                    palabra_secreta[i] = cadena.charAt(i);
                    lineas += "_" + " ";
                }

            } else if (opcion.equals("Animales")) {
                cadena = WORD_LIST[1][x];
                tamano = cadena.length() - 1;

                for (int i = 0; i <= tamano; i++) {
                    palabra_secreta[i] = cadena.charAt(i);
                    lineas += "_" + " ";
                }

            } else if (opcion.equals("Países")) {
                cadena = WORD_LIST[2][x];
                tamano = cadena.length() - 1;

                for (int i = 0; i <= tamano; i++) {
                    palabra_secreta[i] = cadena.charAt(i);
                    lineas += "_" + " ";
                }

            } else if (opcion.equals("Colores")) {
                cadena = WORD_LIST[3][x];
                tamano = cadena.length() - 1;

                for (int i = 0; i <= tamano; i++) {
                    palabra_secreta[i] = cadena.charAt(i);
                    lineas += "_" + " ";
                }

            } else if (opcion.equals("Irregular Verbs")) {
                cadena = WORD_LIST[4][x];
                tamano = cadena.length() - 1;

                for (int i = 0; i <= tamano; i++) {
                    palabra_secreta[i] = cadena.charAt(i);
                    lineas += "_" + " ";
                }

            } else if (opcion.equals("Deportes")) {
                cadena = WORD_LIST[5][x];
                tamano = cadena.length() - 1;

                for (int i = 0; i <= tamano; i++) {
                    palabra_secreta[i] = cadena.charAt(i);
                    lineas += "_" + " ";
                }
            }

            for (int i = 0; i <= tamano; i++) {
                palabra[i] = lineas.charAt(i);
            }

            numeroLetras.setText("Letras: " + cadena.length());
            txtletras.setText(lineas);
        }
    }

    // Verifica si la letra presionada está en la palabra secreta
    public void compara(char letra) {

        txtletras.setText("");
        leter = lineas;
        lineas = "";

        for (int j = 0; j <= cadena.length() - 1; j++) {

            if (cadena.charAt(j) == letra) {
                palabra[j] = letra;
                bandera = true;
                //lineas += palabra[j] + " ";
                //lineas.replace(lineas.charAt(j + (cadena.length() - j + 1)), letra);
            }
			
			/*if(!bandera) {
				lineas += "_" + " ";
			}*/

            lineas += palabra[j] + " ";
        }

        if (!bandera) {
            cont++;
            jugadas.setText("Errores cometidos: " + cont);
            lineas = leter;
        }

        txtletras.setText(lineas);

        bandera = false;

        if (cont == 7) {
            pararCronometro();
            txtletras.setText("");
            lineas = "";

            img.setIcon(new ImageIcon(h.getImage("dead.png")));

            h.cr.mostrarMensaje("¡Has perdido!", "Palabra correcta: " + cadena);

            insertar("Loser");

            reset();
        }

        repaint();
        gano();
    }

    // Comprueba si las letras presionadas forman la palabra secreta
    private void gano() {

        boolean win = false;

        for (int i = 0; i <= palabra_secreta.length - 1; i++) {

            if (palabra[i] == palabra_secreta[i]) {
                win = true;
            } else {
                win = false;
                break;
            }
        }

        if (win) {

            pararCronometro();

            img.setIcon(new ImageIcon(h.getImage("win.png")));

            if (cont >= 0 && cont <= 4) {
                h.cr.mostrarMensaje("¡Has ganado!", "Bastante fácil, ¿no?");
            } else if (cont == 5 || cont == 6) {
                h.cr.mostrarMensaje("¡Has ganado!", "¡Muy bien, lo lograste!");
            } else {
                h.cr.mostrarMensaje("¡Has ganado!", "Por poco, casi mueres.");
            }

            insertar("Winner");

            reset();
        }
    }

    public void reset() {
        btnplay.setEnabled(true);
        opciones.setEnabled(true);
        btnsalir.setEnabled(true);
        img.setIcon(null);
        cont = 0;
        letras = "";
        opciones.setSelectedIndex(0);
        jugadas.setText("");
        txtletras.setText("");
        numeroLetras.setText("");
        letras_precionadas.setText("");
        tiempo.setText("");
        pararCronometro();
    }

    public void insertar(String state) {

        if (h.cr.comprobarConexion("Datos no guardados", true) && h.cr.saveGame()) {

            try {

                String data[] = {"ahorcado", h.cr.ingreseNickname("Ingrese un Nickname", 20), String.valueOf(cont), state,
                        opciones.getSelectedItem().toString(), h.cr.obtenerDate()};

                h.db.insertData(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Valida que solo se ingresen letras minúsculas
    public void soloLetras(char letra, KeyEvent evt) {
        if ((letra < 'a' || letra > 'z') && (letra != KeyEvent.VK_BACK_SPACE || letra != ' ') && letra != 'ñ') {
            h.cr.mostrarMensaje("Advertencia", "¡Sólo se permiten letras minúsculas!");
            evt.consume();
        } else {
            String l = Character.toString(letra);

            if (!letras.contains("[" + l + "]")) {
                letras += "[" + l + "]";
                letras_precionadas.setText("Letras presionadas: " + letras);
            }
            compara(letra);
        }
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {

        if (!opciones.getSelectedItem().toString().equals("Seleccione una opción") && !txtletras.getText().equals("")) {
            soloLetras(e.getKeyChar(), e);
            repaint();
        }
    }

    public boolean isFocusable() {
        return true;
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        this.requestFocus();
    }

    public void mouseEntered(MouseEvent e) {
        this.requestFocus();
    }

    public void mouseExited(MouseEvent e) {
    }

    public void paint(Graphics g) {
        super.paint(g);
        // x,y,ancho,alto
        if (cont == 0) {
            g.setColor(h.cr.AZUL);
            g.drawLine(400, 400, 400, 100);
            g.drawLine(400, 100, 550, 100);
            g.drawLine(550, 150, 550, 100);
        } else if (cont == 1) {
            g.setColor(h.cr.AZUL);
            g.drawLine(400, 400, 400, 100);
            g.drawLine(400, 100, 550, 100);
            g.drawLine(550, 150, 550, 100);
            g.setColor(h.cr.ROJO);
            g.drawOval(500, 150, 100, 100);
        } else if (cont == 2) {
            g.setColor(h.cr.AZUL);
            g.drawLine(400, 400, 400, 100);
            g.drawLine(400, 100, 550, 100);
            g.drawLine(550, 150, 550, 100);
            g.setColor(h.cr.ROJO);
            g.drawOval(500, 150, 100, 100);
            g.drawLine(650, 270, 550, 250);
        } else if (cont == 3) {
            g.setColor(h.cr.AZUL);
            g.drawLine(400, 400, 400, 100);
            g.drawLine(400, 100, 550, 100);
            g.drawLine(550, 150, 550, 100);
            g.setColor(h.cr.ROJO);
            g.drawOval(500, 150, 100, 100);
            g.drawLine(650, 270, 550, 250);
            g.drawLine(450, 270, 550, 250);
        } else if (cont == 4) {
            g.setColor(h.cr.AZUL);
            g.drawLine(400, 400, 400, 100);
            g.drawLine(400, 100, 550, 100);
            g.drawLine(550, 150, 550, 100);
            g.setColor(h.cr.ROJO);
            g.drawOval(500, 150, 100, 100);
            g.drawLine(650, 270, 550, 250);
            g.drawLine(450, 270, 550, 250);
            g.drawLine(550, 320, 550, 250);
        } else if (cont == 5) {
            g.setColor(h.cr.AZUL);
            g.drawLine(400, 400, 400, 100);
            g.drawLine(400, 100, 550, 100);
            g.drawLine(550, 150, 550, 100);
            g.setColor(h.cr.ROJO);
            g.drawOval(500, 150, 100, 100);
            g.drawLine(650, 270, 550, 250);
            g.drawLine(450, 270, 550, 250);
            g.drawLine(550, 320, 550, 250);
            g.drawLine(600, 420, 550, 320);
        } else if (cont == 6) {
            g.setColor(h.cr.AZUL);
            g.drawLine(400, 400, 400, 100);
            g.drawLine(400, 100, 550, 100);
            g.drawLine(550, 150, 550, 100);
            g.setColor(h.cr.ROJO);
            g.drawOval(500, 150, 100, 100);
            g.drawLine(650, 270, 550, 250);
            g.drawLine(450, 270, 550, 250);
            g.drawLine(550, 320, 550, 250);
            g.drawLine(600, 420, 550, 320);
            g.drawLine(500, 420, 550, 320);
        }
    }

    // Se inicia el cronómetro
    public void iniciarCronometro() {
        cronometroActivo = true;
        hiloTime = new Thread((Runnable) this);
        hiloTime.start();
    }

    // Esto es para parar el crónometro
    public void pararCronometro() {
        cronometroActivo = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnplay) {
            btnPlayAP();
        } else if (e.getSource() == btnsalir) {
            h.cr.fadeOut(this);
        }
    }

    @Override
    public void run() {

        Integer minutos = 0, segundos = 59, milesimas = 1000;
        // min es minutos, seg es segundos y mil es milesimas de segundo
        String min = "", seg = "";

        try {
            // Mientras cronometroActivo sea verdadero entonces seguira
            // aumentando el tiempo
            while (cronometroActivo) {
                Thread.sleep(4); // recibe la cantidad de milisegundos de pausa que se hará cada que se ejecute
                // el hilo
                // Decrementamos 4 milesimas de segundo
                milesimas -= 4;

                // Cuando llega a 0 osea 1 segundo, disminuye 1 segundo
                // y las milesimas de segundo de nuevo a 1000
                if (milesimas == 0) {
                    milesimas = 1000;
                    segundos -= 1;
                    // Si los segundos llegan a 0 entonces disminuye 1 los minutos
                    // y los segundos vuelven a 59
                    if (segundos == 0) {

                        if (minutos > 0) {
                            segundos = 59;
                            minutos--;
                        }
                    }
                }

                // Esto solamente es estetica para que siempre este en formato
                // 00:00
                if (minutos < 10) {
                    min = "0" + minutos;
                } else {
                    min = minutos.toString();
                }

                if (segundos < 10) {
                    seg = "0" + segundos;
                } else {
                    seg = segundos.toString();
                }

                // Colocamos en la etiqueta la informacion
                tiempo.setText(min + ":" + seg);

                if (minutos <= 0 && segundos <= 0) {
                    h.cr.mostrarMensaje("¡Has perdido!", "Palabra correcta ? " + cadena);

                    insertar("Loser");
                    reset();
                }
            }

        } catch (Exception e) {
            h.cr.mostrarMensaje("Error", e.getMessage());
        }
    }
}