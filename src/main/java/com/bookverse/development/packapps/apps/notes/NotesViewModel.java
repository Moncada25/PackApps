package com.bookverse.development.packapps.apps.notes;

import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotesViewModel {
  private JRadioButton scale1;
  private JRadioButton scale2;
  private JLabel image;
  private JTextField txtName;
  private JTextField[] notesFields;
  private JComboBox<String>[] percentagesBoxes;
  private JButton btnAddNote;
  private JButton btnDeleteNote;
  private JButton btnReset;
  private Container container;
}
