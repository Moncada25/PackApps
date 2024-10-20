package com.bookverse.development.packapps.apps.notes;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.SwingConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.constants.DatabaseConstants;
import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.other.Format;
import com.bookverse.development.packapps.utils.ui.Effects;
import com.bookverse.development.packapps.utils.ui.factory.Button;

@SuppressWarnings("unchecked")
public class NotesView extends JDialog {

  private transient NotesService service = new NotesService();
  private transient NotesViewModel model = null;

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

    JLabel name = Resources.getLabel("<html><strong>Name</strong></html>", Styles.MAIN_COLOR, this, Styles.MEDIUM);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    add(name, gbc);

    JTextField txtName = new JTextField();
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

    JRadioButton scale1 = new JRadioButton("<html><strong>0 to 5</strong></html>");
    scale1.setForeground(Styles.TEXT_COLOR);
    scale1.setSelected(true);
    buttonGroup.add(scale1);
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    add(scale1, gbc);

    JRadioButton scale2 = new JRadioButton("<html><strong>0 to 10</strong></html>");
    scale2.setForeground(Styles.TEXT_COLOR);
    buttonGroup.add(scale2);
    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    add(scale2, gbc);

    JLabel notes = Resources.getLabel("<html><strong>Notes "+service.getThereAreNotes()+"</strong></html>", Styles.MAIN_COLOR, this, Styles.MEDIUM);
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 2;
    add(notes, gbc);

    JTextField[] notesFields = new JTextField[service.getAllNotes()];
    JComboBox<String>[] percentagesBoxes = new JComboBox[service.getAllNotes()];

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

      percentagesBoxes[i] = new JComboBox<>(service.getPercentages());
      percentagesBoxes[i].setSelectedIndex(1);
      percentagesBoxes[i].setRenderer(new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
          JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
          label.setHorizontalAlignment(SwingConstants.CENTER);
          return label;
        }
      });

      gbc.gridx = 1;
      gbc.gridy = 5 + i;
      add(percentagesBoxes[i], gbc);

      notesFields[i].setVisible(false);
      percentagesBoxes[i].setVisible(false);
    }

    notesFields[0].setVisible(true);
    percentagesBoxes[0].setVisible(true);

    JButton btnAddNote = new Button().setText("Add").setColor(Styles.TEXT_COLOR).build();
    gbc.gridx = 0;
    gbc.gridy = 6 + service.getAllNotes();
    add(btnAddNote, gbc);
    btnAddNote.addActionListener(e -> {
      setSize(getWidth(), getHeight() + 50);
      setLocationRelativeTo(null);
      service.clickOnAdd(model);
      notes.setText("<html><strong>Notes "+service.getThereAreNotes()+"</strong></html>");
    });

    JButton btnDeleteNote = new Button().setText("Delete").setColor(Styles.MAIN_COLOR).build();
    btnDeleteNote.setEnabled(false);
    gbc.gridx = 1;
    gbc.gridy = 6 + service.getAllNotes();
    add(btnDeleteNote, gbc);
    btnDeleteNote.addActionListener(e -> {
      setSize(getWidth(), getHeight() - 50);
      setLocationRelativeTo(null);
      service.clickOnDelete(model);
      notes.setText("<html><strong>Notes "+service.getThereAreNotes()+"</strong></html>");
    });

    JButton btnCalculate = new Button().setText("Show").setColor(Styles.TEXT_COLOR).build();
    gbc.gridx = 0;
    gbc.gridy = 7 + service.getAllNotes();
    add(btnCalculate, gbc);
    btnCalculate.addActionListener(e -> service.clickOnCalculate(model));

    JButton btnReset = new Button().setText("Reset").setColor(Styles.MAIN_COLOR).build();
    btnReset.setEnabled(false);
    gbc.gridx = 1;
    gbc.gridy = 7 + service.getAllNotes();
    add(btnReset, gbc);
    btnReset.addActionListener(e -> service.clickOnReset(model));

    JButton btnExit = new Button().setText("Return").setColor(Styles.MAIN_COLOR).build();
    gbc.gridx = 0;
    gbc.gridy = 8 + service.getAllNotes();
    gbc.gridwidth = 2;
    add(btnExit, gbc);
    btnExit.addActionListener(e -> Effects.fadeOut(this));

    JLabel image = Resources.getLabel("", null, this, null);
    gbc.gridx = 0;
    gbc.gridy = 9 + service.getAllNotes();
    gbc.gridwidth = 2;
    add(image, gbc);

    model = new NotesViewModel(
        scale1,
        scale2,
        image,
        txtName,
        notesFields,
        percentagesBoxes,
        btnAddNote,
        btnDeleteNote,
        btnReset,
        this
    );
  }
}