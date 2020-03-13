package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.AppConfig.MAIN_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.TEXT_COLOR;
import static com.bookverse.development.packapps.utils.ViewConstants.NOTES;

import com.bookverse.development.packapps.core.AppConfig;
import com.bookverse.development.packapps.models.Database;
import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.Format;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.stream.IntStream;
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

  private int numberOfNotes = 10;
  private int maxNote, minNote;
  private JLabel image;
  private JTextField txtName;
  private JButton btnCalculate, btnAddNote, btnDeleteNote, btnReset, btnExit;
  private Resources resources = new Resources();
  private float[] percentagesNumbers = new float[numberOfNotes];
  private float[] notesNumbers = new float[numberOfNotes];
  private float totalNote = 0, totalPercentage = 0, missingNote = 0;
  private JTextField[] notesFields = new JTextField[numberOfNotes];
  private JComboBox<String>[] percentagesBoxes = new JComboBox[numberOfNotes];
  private JRadioButton scale1, scale2;
  private int thereAreNotes = 1;

  public Notes(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public Notes(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JFrame parent) {
    setSize(400, 500);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle(NOTES);
    AppConfig.fadeIn(this);
    parent.setVisible(false);
    AppConfig.instruccionesNotas();
    setVisible(true);
  }

  public void start(JDialog parent) {
    setSize(400, 500);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle(NOTES);
    AppConfig.fadeIn(this);
    parent.setVisible(false);
    AppConfig.instruccionesNotas();
    setVisible(true);
  }

  private void createComponents() {

    setLayout(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(resources.getImage("notas.png")).getImage());

    btnExit = resources.getButton("Return", MAIN_COLOR, this, this);
    btnExit.setBounds(248, 330, 86, 30);

    btnCalculate = resources.getButton("Show", TEXT_COLOR, this, this);
    btnCalculate.setBounds(200, 280, 86, 30);

    btnAddNote = resources.getButton("Add", TEXT_COLOR, this, this);
    btnAddNote.setBounds(200, 220, 86, 30);

    btnDeleteNote = resources.getButton("Delete", MAIN_COLOR, this, this);
    btnDeleteNote.setBounds(300, 220, 86, 30);
    btnDeleteNote.setEnabled(false);

    btnReset = resources.getButton("Reset", MAIN_COLOR, this, this);
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
      for (int j = 1; j <= 100; j++) {
        percentagesBoxes[i].addItem(String.valueOf(j));
      }
      add(percentagesBoxes[i]);
      percentagesBoxes[i].setSelectedIndex(19);

      if (i != 0) {
        percentagesBoxes[i].setVisible(false);
      }

      y += 40;
    }

    JLabel lblNotes = resources
        .getLabel("<html><strong>Notes</strong></html>", MAIN_COLOR, this,
            AppConfig.MEDIUM);
    lblNotes.setBounds(30, 30, 100, 30);

    JLabel lblPercentages = resources
        .getLabel("<html><strong>%</strong></html>", MAIN_COLOR, this,
            AppConfig.MEDIUM);
    lblPercentages.setBounds(145, 30, 100, 30);

    JLabel name = resources
        .getLabel("<html><strong>Name</strong></html>", MAIN_COLOR, this,
            AppConfig.MEDIUM);
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

    JLabel scale = resources
        .getLabel("<html><strong>Scale</strong></html>", MAIN_COLOR, this,
            AppConfig.MEDIUM);
    scale.setBounds(270, 110, 100, 30);

    ButtonGroup buttonGroup = new ButtonGroup();

    scale1 = new JRadioButton("<html><strong>0 to 5</strong></html>");
    scale1.setBounds(200, 140, 100, 30);
    scale1.addActionListener(this);
    scale1.setForeground(TEXT_COLOR);
    add(scale1);
    buttonGroup.add(scale1);
    scale1.setSelected(true);

    scale2 = new JRadioButton("<html><strong>0 to 10</strong></html>");
    scale2.setBounds(300, 140, 100, 30);
    scale2.setForeground(TEXT_COLOR);
    scale2.addActionListener(this);
    add(scale2);
    buttonGroup.add(scale2);

    image = resources.getLabel("", null, this, null);
    image.setBounds(240, 360, 96, 96);
  }

  private void adjustScale() {

    if (scale1.isSelected()) {
      maxNote = 5;
      minNote = 3;
    } else if (scale2.isSelected()) {
      maxNote = 10;
      minNote = 5;
    }
  }

  private boolean validateFields() {
    return IntStream.range(0, thereAreNotes).noneMatch(i -> notesFields[i].getText().equals("")
        || Float.parseFloat(notesFields[i].getText()) > maxNote
        || txtName.getText().trim().equals(""));
  }

  private void btnAddAP() {

    notesFields[thereAreNotes].setVisible(true);
    percentagesBoxes[thereAreNotes].setVisible(true);
    btnReset.setEnabled(true);
    btnDeleteNote.setEnabled(true);

    thereAreNotes++;

    if (thereAreNotes == 10) {
      btnAddNote.setEnabled(false);
    }
  }

  private void btnDeleteAP() {

    notesFields[thereAreNotes - 1].setVisible(false);
    percentagesBoxes[thereAreNotes - 1].setVisible(false);
    notesFields[thereAreNotes - 1].setText("");
    percentagesBoxes[thereAreNotes - 1].setSelectedIndex(19);
    btnAddNote.setEnabled(true);

    thereAreNotes--;

    if (thereAreNotes == 1) {
      btnReset.setEnabled(false);
      btnDeleteNote.setEnabled(false);
    }
  }

  private void parseData() {
    IntStream.range(0, thereAreNotes).forEach(i -> {
      percentagesNumbers[i] = Integer.parseInt(percentagesBoxes[i].getSelectedItem().toString());
      notesNumbers[i] = Float.parseFloat(notesFields[i].getText());
    });
  }

  private void addNotes() {

    IntStream.range(0, thereAreNotes).forEach(i -> {
      totalPercentage += percentagesNumbers[i];
      totalNote += (notesNumbers[i] * percentagesNumbers[i]) / 100;
    });

    missingNote = (minNote - totalNote) / ((100 - totalPercentage) / 100);
  }

  private void insert(String state) {

    if (AppConfig.verifyConnection("Data don't saved", true) && AppConfig.saveGame()) {

      try {

        String option = "";

        if (scale1.isSelected()) {
          option = "0 to 5";
        } else if (scale2.isSelected()) {
          option = "1 to 9";
        }

        String[] data = {NOTES, txtName.getText(), option, String.format("%.0f", totalPercentage),
            String.format("%.2f", totalNote), state, AppConfig.getDate()};
        Database.insertData(data);
      } catch (Exception e) {
        Alerts.error(e, NOTES);
      }
    }
    btnResetAP();
  }

  private void btnCalculateAP() {

    adjustScale();

    if (validateFields()) {

      totalNote = 0;
      totalPercentage = 0;

      parseData();

      addNotes();

      if (totalPercentage == 100) {

        if (totalNote < minNote) {
          image.setIcon(new ImageIcon(resources.getImage("dead.png")));
        } else {
          image.setIcon(new ImageIcon(resources.getImage("win.png")));
        }

        JOptionPane.showMessageDialog(null,
            "<html>" + Format.style() + "<strong>Name: </strong>" + txtName.getText()
                + "<br>" + "<strong>Note: </strong>"
                + String.format("%.2f", totalNote) + "</html>",
            "Definitive", JOptionPane.PLAIN_MESSAGE);

        if (totalNote < minNote) {
          insert("Reproved");
        } else {
          insert("Approved");
        }

      } else if (totalPercentage > 100) {

        JOptionPane.showMessageDialog(null, "<html>" + Format.style()
                + "<strong>Percentage exceeded</strong></html>", "Verify!",
            JOptionPane.PLAIN_MESSAGE);

      } else if (missingNote > maxNote) {

        image.setIcon(new ImageIcon(resources.getImage("dead.png")));

        JOptionPane.showMessageDialog(null,
            "<html>" + Format.style()
                + "<strong>There is nothing to do, better cancel...</strong><br><br>"
                + "<strong>Accumulated note: </strong>" + String.format("%.2f", totalNote) + "<br>"
                + "<strong>You would have to get " + String.format("%.2f", missingNote) + " in the "
                + String.format("%.0f", 100 - totalPercentage) + " % remaining</strong></html>",
            "Ay :(", JOptionPane.PLAIN_MESSAGE);

        insert("Reproved");

      } else {

        if (totalNote >= 0 && totalNote < minNote) {

          JOptionPane.showMessageDialog(null,
              "<html>" + Format.style() + "<strong>Name: </strong>" + txtName
                  .getText() + "<br>"
                  + "<strong>Note necessary to win: </strong>" + String
                  .format("%.2f", missingNote) + "<br>"
                  + "<strong>Remaining percentage: </strong>" + String
                  .format("%.0f", 100 - totalPercentage)
                  + "%" + "</html>",
              "Result", JOptionPane.PLAIN_MESSAGE);

          insert("Maybe");

        } else if (totalNote >= minNote) {

          image.setIcon(new ImageIcon(resources.getImage("win.png")));

          JOptionPane.showMessageDialog(null,
              "<html>" + Format.style()
                  + "<strong>Congratulations, you've already approved it!</strong><br><br>"
                  + "<strong>Name: </strong>" + txtName.getText()
                  + "<br>" + "<strong>Accumulated note: </strong>" + String
                  .format("%.2f", totalNote)
                  + "</html>",
              "You won!", JOptionPane.PLAIN_MESSAGE);
          insert("Approved");
        }
      }

    } else {

      JOptionPane.showMessageDialog(null,
          "<html>" + Format.style()
              + "<strong>Review the following data</strong><br><br>"
              + "<strong>Notes: </strong>less than or equal to " + maxNote + "<br>"
              + "<strong>Fields: </strong>empty" + "</html>",
          "Verify!", JOptionPane.PLAIN_MESSAGE);
    }
  }

  private void btnResetAP() {

    IntStream.range(1, thereAreNotes).forEach(i -> {
      notesFields[i].setVisible(false);
      percentagesBoxes[i].setVisible(false);
      notesFields[i].setText("");
      percentagesBoxes[i].setSelectedIndex(19);
    });

    txtName.setText("");
    notesFields[0].setText("");
    percentagesBoxes[0].setSelectedIndex(19);
    thereAreNotes = 1;
    image.setIcon(null);
    btnAddNote.setEnabled(true);
    btnDeleteNote.setEnabled(false);
    btnReset.setEnabled(false);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnAddNote) {
      btnAddAP();
    } else if (e.getSource() == btnDeleteNote) {
      btnDeleteAP();
    } else if (e.getSource() == btnCalculate) {
      btnCalculateAP();
    } else if (e.getSource() == btnReset) {
      btnResetAP();
    } else if (e.getSource() == btnExit) {
      AppConfig.fadeOut(this);
    }
  }
}