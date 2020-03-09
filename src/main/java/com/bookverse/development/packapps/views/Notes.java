package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.models.Resources;
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

public class Notes extends JDialog implements ActionListener {

  private static final int CANTIDAD_NOTAS = 10;
  private int notaMaxima, notaMinima;
  private JLabel notas, porc, name, scale, img;
  private JTextField nombre;
  private JButton btncalcular, btnaddNote, btndeleteNote, btnreset, btnsalir;
  private Resources resources = new Resources();
  private float[] porcent = new float[CANTIDAD_NOTAS];
  private float[] notes = new float[CANTIDAD_NOTAS];
  private float notaTotal = 0, porcTotal = 0, notaFalta = 0;
  private JTextField[] notitas = new JTextField[CANTIDAD_NOTAS];
  private JComboBox<String>[] porcentajes = new JComboBox[CANTIDAD_NOTAS];
  private JRadioButton scale1, scale2;
  private ButtonGroup btngroup;
  private int notasHay = 1;

  public Notes(JFrame parent, boolean modal) {
    super(parent, modal);
    Componentes();
  }

    public Notes(JDialog parent, boolean modal) {
        super(parent, modal);
        Componentes();
    }

  public void start(JFrame parent) {
    setSize(400, 500);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Calcular Notas");
    resources.core.fadeIn(this);
    parent.setVisible(false);
    resources.core.instruccionesNotas();
    setVisible(true);
  }

    public void start(JDialog parent) {
        setSize(400, 500);
        setResizable(false);
        setLocationRelativeTo(parent);
        setTitle("Calcular Notas");
        resources.core.fadeIn(this);
        parent.setVisible(false);
        resources.core.instruccionesNotas();
        setVisible(true);
    }

  @SuppressWarnings("unchecked")
  public void Componentes() {

    setLayout(null);
    setDefaultCloseOperation(0);
    setIconImage(new ImageIcon(resources.getImage("notas.png")).getImage());

    btnsalir = resources.getButton("Return", resources.core.MAIN_COLOR, this, this);
    btnsalir.setBounds(248, 330, 86, 30);

    btncalcular = resources.getButton("Show", resources.core.TEXT_COLOR, this, this);
    btncalcular.setBounds(200, 280, 86, 30);

    btnaddNote = resources.getButton("Add", resources.core.TEXT_COLOR, this, this);
    btnaddNote.setBounds(200, 220, 86, 30);

    btndeleteNote = resources.getButton("Delete", resources.core.MAIN_COLOR, this, this);
    btndeleteNote.setBounds(300, 220, 86, 30);
    btndeleteNote.setEnabled(false);

    btnreset = resources.getButton("Reset", resources.core.MAIN_COLOR, this, this);
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
          resources.core.solonumerosYpunto(e.getKeyChar(), e, notitas[j].getText(), 3);
          resources.core.PuntoMedio(e.getKeyChar(), e, notitas[j].getText());
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

    notas = resources
        .getLabel("<html><strong>Notes</strong></html>", resources.core.MAIN_COLOR, this,
            resources.core.MEDIUM);
    notas.setBounds(30, 30, 100, 30);

    porc = resources
        .getLabel("<html><strong>%</strong></html>", resources.core.MAIN_COLOR, this,
            resources.core.MEDIUM);
    porc.setBounds(145, 30, 100, 30);

    name = resources
        .getLabel("<html><strong>Name</strong></html>", resources.core.MAIN_COLOR, this,
            resources.core.MEDIUM);
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
        resources.core.soloAlfa(e.getKeyChar(), e, nombre.getText(), 20);
      }
    });

    scale = resources
        .getLabel("<html><strong>Scale</strong></html>", resources.core.MAIN_COLOR, this,
            resources.core.MEDIUM);
    scale.setBounds(270, 110, 100, 30);

    btngroup = new ButtonGroup();

    scale1 = new JRadioButton("<html><strong>0 o 5</strong></html>");
    scale1.setBounds(200, 140, 100, 30);
    scale1.addActionListener(this);
    scale1.setForeground(resources.core.TEXT_COLOR);
    add(scale1);
    btngroup.add(scale1);
    scale1.setSelected(true);

    scale2 = new JRadioButton("<html><strong>0 o 10</strong></html>");
    scale2.setBounds(300, 140, 100, 30);
    scale2.setForeground(resources.core.TEXT_COLOR);
    scale2.addActionListener(this);
    add(scale2);
    btngroup.add(scale2);

    img = resources.getLabel("", null, this, null);
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

    if (resources.core.comprobarConexion("Datos no guardados", true) && resources.core.saveGame()) {

      try {

        String op = "";

        if (scale1.isSelected()) {
          op = "0 to 5";
        } else if (scale2.isSelected()) {
          op = "1 to 9";
        }

        String data[] = {"notas", nombre.getText(), op, String.format("%.0f", porcTotal),
            String.format("%.2f", notaTotal), state, resources.core.obtenerDate()};
        resources.database.insertData(data);
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
          img.setIcon(new ImageIcon(resources.getImage("dead.png")));
        } else {
          img.setIcon(new ImageIcon(resources.getImage("win.png")));
        }

        JOptionPane.showMessageDialog(null,
            "<html>" + resources.core.styleJOption() + "<strong>Name: </strong>" + nombre.getText()
                + "<br>" + "<strong>Nota: </strong>"
                + String.format("%.2f", notaTotal) + "</html>",
            "Definitiva", JOptionPane.PLAIN_MESSAGE);

        if (notaTotal < notaMinima) {
          insertar("Reproved");
        } else {
          insertar("Approved");
        }

      } else if (porcTotal > 100) {

        JOptionPane.showMessageDialog(null, "<html>" + resources.core.styleJOption()
                + "<strong>Porcentaje excedido</strong></html>", "�Verifica!",
            JOptionPane.PLAIN_MESSAGE);

      } else if (notaFalta > notaMaxima) {

        img.setIcon(new ImageIcon(resources.getImage("dead.png")));

        JOptionPane.showMessageDialog(null,
            "<html>" + resources.core.styleJOption()
                + "<strong>No hay nada que hacer, ya mejor cancela...</strong><br><br>"
                + "<strong>Nota acumulada: </strong>" + String.format("%.2f", notaTotal) + "<br>"
                + "<strong>Necesitar�as sacar " + String.format("%.2f", notaFalta) + " en el "
                + String.format("%.0f", 100 - porcTotal) + "% restante</strong></html>",
            "Ay :(", JOptionPane.PLAIN_MESSAGE);

        insertar("Reproved");

      } else {

        if (notaTotal >= 0 && notaTotal < notaMinima) {

          JOptionPane.showMessageDialog(null,
              "<html>" + resources.core.styleJOption() + "<strong>Name: </strong>" + nombre
                  .getText() + "<br>"
                  + "<strong>Nota necesaria para ganar: </strong>" + String
                  .format("%.2f", notaFalta) + "<br>"
                  + "<strong>Porcentaje restante: </strong>" + String
                  .format("%.0f", 100 - porcTotal)
                  + "%" + "</html>",
              "Resultado", JOptionPane.PLAIN_MESSAGE);

          insertar("Maybe");

        } else if (notaTotal >= notaMinima) {

          img.setIcon(new ImageIcon(resources.getImage("win.png")));

          JOptionPane.showMessageDialog(null,
              "<html>" + resources.core.styleJOption()
                  + "<strong>�Felicidades, ya has aprovado!</strong><br><br>"
                  + "<strong>ID: </strong>" + nombre.getText()
                  + "<br>" + "<strong>Nota acumulada: </strong>" + String.format("%.2f", notaTotal)
                  + "</html>",
              "�Ganaste!", JOptionPane.PLAIN_MESSAGE);
          insertar("Approved");
        }
      }

    } else {

      JOptionPane.showMessageDialog(null,
          "<html>" + resources.core.styleJOption()
              + "<strong>Revisa los siguientes datos</strong><br><br>"
              + "<strong>Notas: </strong>menor o igual a " + notaMaxima + "<br>"
              + "<strong>Campos: </strong>vac�os" + "</html>",
          "�Verifica!", JOptionPane.PLAIN_MESSAGE);
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
      resources.core.fadeOut(this);
    }
  }
}