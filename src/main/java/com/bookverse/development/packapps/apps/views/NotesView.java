package com.bookverse.development.packapps.apps.views;

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
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.bookverse.development.packapps.apps.services.NotesService;
import com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants;
import com.bookverse.development.packapps.apps.utils.constants.Styles;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;
import com.bookverse.development.packapps.apps.utils.other.Format;
import com.bookverse.development.packapps.apps.utils.ui.Effects;

@SuppressWarnings("unchecked")
public class NotesView extends JDialog implements ActionListener {

  private JTextField txtName;
  private JButton btnCalculate;
  private JButton btnAddNote;
  private JButton btnDeleteNote;
  private JButton btnReset;
  private JButton btnExit;
  private JTextField[] notesFields = new JTextField[NotesService.MAX_NOTES];
  private JComboBox<String>[] percentagesBoxes = new JComboBox[NotesService.MAX_NOTES];
  private JRadioButton scale1;
  private JRadioButton scale2;
  private JLabel image;

  public NotesView(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public NotesView(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JFrame parent) {
    setSize(400, 500);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle(DatabaseConstants.NOTES);
    Effects.fadeIn(this);
    parent.setVisible(false);
    Alerts.instruccionesNotas();
    setVisible(true);
  }

  public void start(JDialog parent) {
    setSize(400, 500);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle(DatabaseConstants.NOTES);
    Effects.fadeIn(this);
    parent.setVisible(false);
    Alerts.instruccionesNotas();
    setVisible(true);
  }

  private void createComponents() {

    setLayout(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(NotesService.resources.getImage("notas.png")).getImage());

    btnExit = NotesService.resources.getButton("Return", Styles.MAIN_COLOR, this, this);
    btnExit.setBounds(248, 330, 86, 30);

    btnCalculate = NotesService.resources.getButton("Show", Styles.TEXT_COLOR, this, this);
    btnCalculate.setBounds(200, 280, 86, 30);

    btnAddNote = NotesService.resources.getButton("Add", Styles.TEXT_COLOR, this, this);
    btnAddNote.setBounds(200, 220, 86, 30);

    btnDeleteNote = NotesService.resources.getButton("Delete", Styles.MAIN_COLOR, this, this);
    btnDeleteNote.setBounds(300, 220, 86, 30);
    btnDeleteNote.setEnabled(false);

    btnReset = NotesService.resources.getButton("Reset", Styles.MAIN_COLOR, this, this);
    btnReset.setBounds(300, 280, 86, 30);
    btnReset.setEnabled(false);

    int x = 30;
    int y = 60;

    for (int i = 0; i < notesFields.length; i++) {

      notesFields[i] = new JTextField();
      notesFields[i].setBounds(x, y, 50, 25);
      notesFields[i].setHorizontalAlignment(JTextField.CENTER);
      add(notesFields[i]);

      if (i != 0) {
        notesFields[i].setVisible(false);
      }

      int j = i;

      notesFields[i].addKeyListener(new KeyAdapter() {

        public void keyTyped(KeyEvent e) {
          txtNumKeyTyped(e);
        }

        public void txtNumKeyTyped(KeyEvent e) {
          Format.onlyNumbersAndPoint(e.getKeyChar(), e, notesFields[j].getText(), 3);
          Format.middlePoint(e.getKeyChar(), e, notesFields[j].getText());
        }
      });
      y += 40;
    }

    x = 130;
    y = 60;

    for (int i = 0; i < percentagesBoxes.length; i++) {
      percentagesBoxes[i] = new JComboBox<>();
      percentagesBoxes[i].setBounds(x, y, 58, 25);
      for (int j = 1; j < 100; j++) {
        percentagesBoxes[i].addItem(String.valueOf(j));
      }
      add(percentagesBoxes[i]);
      percentagesBoxes[i].setSelectedIndex(19);

      if (i != 0) {
        percentagesBoxes[i].setVisible(false);
      }

      y += 40;
    }

    JLabel lblNotes = NotesService.resources
        .getLabel("<html><strong>Notes</strong></html>", Styles.MAIN_COLOR, this,
            Styles.MEDIUM);
    lblNotes.setBounds(30, 30, 100, 30);

    JLabel lblPercentages = NotesService.resources.getLabel(
        "<html><strong>%</strong></html>", Styles.MAIN_COLOR, this, Styles.MEDIUM);
    lblPercentages.setBounds(145, 30, 100, 30);

    JLabel name = NotesService.resources.getLabel(
        "<html><strong>Name</strong></html>", Styles.MAIN_COLOR, this, Styles.MEDIUM);
    name.setBounds(270, 30, 100, 30);

    txtName = new JTextField();
    txtName.setBounds(200, 60, 186, 25);
    txtName.setHorizontalAlignment(JTextField.CENTER);
    add(txtName);

    txtName.addKeyListener(new KeyAdapter() {

      public void keyTyped(KeyEvent e) {
        txtNumKeyTyped(e);
      }

      public void txtNumKeyTyped(KeyEvent e) {
        Format.onlyAlfa(e.getKeyChar(), e, txtName.getText(), 20);
      }
    });

    JLabel scale = NotesService.resources.getLabel(
        "<html><strong>Scale</strong></html>", Styles.MAIN_COLOR, this, Styles.MEDIUM);
    scale.setBounds(270, 110, 100, 30);

    ButtonGroup buttonGroup = new ButtonGroup();

    scale1 = new JRadioButton("<html><strong>0 to 5</strong></html>");
    scale1.setBounds(200, 140, 100, 30);
    scale1.setForeground(Styles.TEXT_COLOR);
    add(scale1);
    buttonGroup.add(scale1);
    scale1.setSelected(true);

    scale2 = new JRadioButton("<html><strong>0 to 10</strong></html>");
    scale2.setBounds(300, 140, 100, 30);
    scale2.setForeground(Styles.TEXT_COLOR);
    add(scale2);
    buttonGroup.add(scale2);

    image = NotesService.resources.getLabel("", null, this, null);
    image.setBounds(240, 360, 96, 96);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnAddNote) {
      NotesService.clickOnAdd(notesFields, percentagesBoxes, btnAddNote, btnDeleteNote, btnReset);
    } else if (e.getSource() == btnDeleteNote) {
      NotesService.clickOnDelete(notesFields, percentagesBoxes, btnAddNote, btnDeleteNote, btnReset);
    } else if (e.getSource() == btnCalculate) {
      NotesService.clickOnCalculate(scale1, scale2, image, txtName, notesFields, percentagesBoxes, btnAddNote, btnDeleteNote, btnReset);
    } else if (e.getSource() == btnReset) {
      NotesService.clickOnReset(image, txtName, notesFields, percentagesBoxes, btnAddNote, btnDeleteNote, btnReset);
    } else if (e.getSource() == btnExit) {
      Effects.fadeOut(this);
    }
  }
}