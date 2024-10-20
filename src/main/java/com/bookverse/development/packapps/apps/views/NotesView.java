package com.bookverse.development.packapps.apps.views;

import javax.swing.SwingConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import com.bookverse.development.packapps.apps.utils.ui.Resources;
import com.bookverse.development.packapps.apps.services.NotesService;
import com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants;
import com.bookverse.development.packapps.apps.utils.constants.Styles;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;
import com.bookverse.development.packapps.apps.utils.other.Format;
import com.bookverse.development.packapps.apps.utils.ui.Effects;

@SuppressWarnings("unchecked")
public class NotesView extends JDialog implements ActionListener {

  private transient NotesService service = new NotesService();
  private JTextField txtName;
  private JButton btnCalculate;
  private JButton btnAddNote;
  private JButton btnDeleteNote;
  private JButton btnReset;
  private JButton btnExit;
  private JTextField[] notesFields = new JTextField[service.getAllNotes()];
  private JComboBox<String>[] percentagesBoxes = new JComboBox[service.getAllNotes()];
  private JRadioButton scale1;
  private JRadioButton scale2;
  private JLabel image;
  private JLabel notes;

  public NotesView(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public NotesView(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JFrame parent) {
    setSize(300, 400);
    setResizable(true);
    setLocationRelativeTo(parent);
    setTitle(DatabaseConstants.NOTES);
    Effects.fadeIn(this);
    parent.setVisible(false);
    Alerts.instruccionesNotas();
    setVisible(true);
  }

  public void start(JDialog parent) {
    setSize(300, 400);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle(DatabaseConstants.NOTES);
    Effects.fadeIn(this);
    parent.setVisible(false);
    Alerts.instruccionesNotas();
    setVisible(true);
  }

  private void createComponents() {
    setLayout(new GridBagLayout());
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(Resources.getImage("notas.png")).getImage());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(7, 7, 7, 7);
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;

    // Header
    JLabel name = Resources.getLabel("<html><strong>Name</strong></html>", Styles.MAIN_COLOR, this, Styles.MEDIUM);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    add(name, gbc);

    txtName = new JTextField();
    txtName.setHorizontalAlignment(SwingConstants.CENTER);
    txtName.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        Format.onlyAlfa(e.getKeyChar(), e, txtName.getText(), 20);
      }
    });
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 2;
    add(txtName, gbc);

    JLabel scale = Resources.getLabel("<html><strong>Scale</strong></html>", Styles.MAIN_COLOR, this, Styles.MEDIUM);
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    add(scale, gbc);

    ButtonGroup buttonGroup = new ButtonGroup();

    scale1 = new JRadioButton("<html><strong>0 to 5</strong></html>");
    scale1.setForeground(Styles.TEXT_COLOR);
    scale1.setSelected(true);
    buttonGroup.add(scale1);
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    add(scale1, gbc);

    scale2 = new JRadioButton("<html><strong>0 to 10</strong></html>");
    scale2.setForeground(Styles.TEXT_COLOR);
    buttonGroup.add(scale2);
    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    add(scale2, gbc);

    notes = Resources.getLabel("<html><strong>Notes "+service.getThereAreNotes()+"</strong></html>", Styles.MAIN_COLOR, this, Styles.MEDIUM);
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 2;
    add(notes, gbc);

    // Center
    gbc.gridwidth = 1;
    for (int i = 0; i < notesFields.length; i++) {
      notesFields[i] = new JTextField();
      notesFields[i].setHorizontalAlignment(SwingConstants.CENTER);
      int finalI = i;
      notesFields[i].addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
          Format.onlyNumbersAndPoint(e.getKeyChar(), e, notesFields[finalI].getText(), 3);
          Format.middlePoint(e.getKeyChar(), e, notesFields[finalI].getText());
        }
      });
      gbc.gridx = 0;
      gbc.gridy = 5 + i;
      add(notesFields[i], gbc);

      percentagesBoxes[i] = new JComboBox<>(getPercentages());
      percentagesBoxes[i].setSelectedIndex(1);
      gbc.gridx = 1;
      gbc.gridy = 5 + i;
      add(percentagesBoxes[i], gbc);

      notesFields[i].setVisible(false);
      percentagesBoxes[i].setVisible(false);
    }

    notesFields[0].setVisible(true);
    percentagesBoxes[0].setVisible(true);

    // Footer
    btnAddNote = Resources.getButton("Add", Styles.TEXT_COLOR, this, this);
    gbc.gridx = 0;
    gbc.gridy = 6 + service.getAllNotes();
    add(btnAddNote, gbc);

    btnDeleteNote = Resources.getButton("Delete", Styles.MAIN_COLOR, this, this);
    btnDeleteNote.setEnabled(false);
    gbc.gridx = 1;
    gbc.gridy = 6 + service.getAllNotes();
    add(btnDeleteNote, gbc);

    btnCalculate = Resources.getButton("Show", Styles.TEXT_COLOR, this, this);
    gbc.gridx = 0;
    gbc.gridy = 7 + service.getAllNotes();
    add(btnCalculate, gbc);

    btnReset = Resources.getButton("Reset", Styles.MAIN_COLOR, this, this);
    btnReset.setEnabled(false);
    gbc.gridx = 1;
    gbc.gridy = 7 + service.getAllNotes();
    add(btnReset, gbc);

    btnExit = Resources.getButton("Return", Styles.MAIN_COLOR, this, this);
    gbc.gridx = 0;
    gbc.gridy = 8 + service.getAllNotes();
    gbc.gridwidth = 2;
    add(btnExit, gbc);

    image = Resources.getLabel("", null, this, null);
    gbc.gridx = 0;
    gbc.gridy = 9 + service.getAllNotes();
    gbc.gridwidth = 2;
    add(image, gbc);
  }

  private String[] getPercentages() {
    // Retorna un array con porcentajes para el JComboBox
    return new String[]{"10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnAddNote) {
      setSize(getWidth(), getHeight() + 50);
      setLocationRelativeTo(null);
      service.clickOnAdd(notesFields, percentagesBoxes, btnAddNote, btnDeleteNote, btnReset);
      notes.setText("<html><strong>Notes "+service.getThereAreNotes()+"</strong></html>");
    } else if (e.getSource() == btnDeleteNote) {
      setSize(getWidth(), getHeight() - 50);
      setLocationRelativeTo(null);
      service.clickOnDelete(notesFields, percentagesBoxes, btnAddNote, btnDeleteNote, btnReset);
      notes.setText("<html><strong>Notes "+service.getThereAreNotes()+"</strong></html>");
    } else if (e.getSource() == btnCalculate) {
      service.clickOnCalculate(scale1, scale2, image, txtName, notesFields, percentagesBoxes, btnAddNote, btnDeleteNote, btnReset, this);
    } else if (e.getSource() == btnReset) {
      service.clickOnReset(image, txtName, notesFields, percentagesBoxes, btnAddNote, btnDeleteNote, btnReset, this);
    } else if (e.getSource() == btnExit) {
      Effects.fadeOut(this);
    }
  }
}