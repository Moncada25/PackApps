package com.bookverse.development.packapps.apps.views;

import com.bookverse.development.packapps.apps.utils.ui.Resources;
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

import com.bookverse.development.packapps.apps.services.NotesService;
import com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants;
import com.bookverse.development.packapps.apps.utils.constants.Styles;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;
import com.bookverse.development.packapps.apps.utils.other.Format;
import com.bookverse.development.packapps.apps.utils.ui.Effects;
import javax.swing.SwingConstants;

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
    setLayout(new GridBagLayout());
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(Resources.getImage("notas.png")).getImage());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;

    // Header
    JLabel name = Resources.getLabel("<html><strong>Name</strong></html>", Styles.MAIN_COLOR, this, Styles.MEDIUM);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    add(name, gbc);

    txtName = new JTextField();
    txtName.setHorizontalAlignment(SwingConstants.CENTER);
    txtName.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        Format.onlyAlfa(e.getKeyChar(), e, txtName.getText(), 20);
      }
    });
    gbc.gridx = 1;
    gbc.gridy = 0;
    add(txtName, gbc);

    JLabel scale = Resources.getLabel("<html><strong>Scale</strong></html>", Styles.MAIN_COLOR, this, Styles.MEDIUM);
    gbc.gridx = 0;
    gbc.gridy = 1;
    add(scale, gbc);

    ButtonGroup buttonGroup = new ButtonGroup();

    scale1 = new JRadioButton("<html><strong>0 to 5</strong></html>");
    scale1.setForeground(Styles.TEXT_COLOR);
    scale1.setSelected(true);
    buttonGroup.add(scale1);
    gbc.gridx = 1;
    gbc.gridy = 1;
    add(scale1, gbc);

    scale2 = new JRadioButton("<html><strong>0 to 10</strong></html>");
    scale2.setForeground(Styles.TEXT_COLOR);
    buttonGroup.add(scale2);
    gbc.gridx = 1;
    gbc.gridy = 2;
    add(scale2, gbc);

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
      gbc.gridy = 3 + i;
      add(notesFields[i], gbc);

      percentagesBoxes[i] = new JComboBox<>();
      for (int j = 1; j < 100; j++) {
        percentagesBoxes[i].addItem(String.valueOf(j));
      }
      percentagesBoxes[i].setSelectedIndex(19);
      gbc.gridx = 1;
      gbc.gridy = 3 + i;
      add(percentagesBoxes[i], gbc);

      if (i != 0) {
        notesFields[i].setVisible(false);
        percentagesBoxes[i].setVisible(false);
      }
    }

    // Footer
    btnAddNote = Resources.getButton("Add", Styles.TEXT_COLOR, this, this);
    gbc.gridx = 0;
    gbc.gridy = 3 + notesFields.length;
    add(btnAddNote, gbc);

    btnDeleteNote = Resources.getButton("Delete", Styles.MAIN_COLOR, this, this);
    btnDeleteNote.setEnabled(false);
    gbc.gridx = 1;
    gbc.gridy = 3 + notesFields.length;
    add(btnDeleteNote, gbc);

    btnCalculate = Resources.getButton("Show", Styles.TEXT_COLOR, this, this);
    gbc.gridx = 0;
    gbc.gridy = 4 + notesFields.length;
    add(btnCalculate, gbc);

    btnReset = Resources.getButton("Reset", Styles.MAIN_COLOR, this, this);
    btnReset.setEnabled(false);
    gbc.gridx = 1;
    gbc.gridy = 4 + notesFields.length;
    add(btnReset, gbc);

    btnExit = Resources.getButton("Return", Styles.MAIN_COLOR, this, this);
    gbc.gridx = 0;
    gbc.gridy = 5 + notesFields.length;
    gbc.gridwidth = 2;
    add(btnExit, gbc);

    image = Resources.getLabel("", null, this, null);
    gbc.gridx = 0;
    gbc.gridy = 6 + notesFields.length;
    gbc.gridwidth = 2;
    add(image, gbc);
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