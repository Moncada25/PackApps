package com.bookverse.development.packapps.apps.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.Format;
import com.bookverse.development.packapps.utils.ui.Effects;

import static com.bookverse.development.packapps.apps.services.ArraysService.clickOnAction;
import static com.bookverse.development.packapps.apps.services.ArraysService.clickOnAutoFill;
import static com.bookverse.development.packapps.apps.services.ArraysService.clickOnClear;
import static com.bookverse.development.packapps.apps.services.ArraysService.clickOnDeterminant;
import static com.bookverse.development.packapps.apps.services.ArraysService.clickOnDiagonals;
import static com.bookverse.development.packapps.apps.services.ArraysService.clickOnMultiply;
import static com.bookverse.development.packapps.apps.services.ArraysService.clickOnTransposed;
import static com.bookverse.development.packapps.apps.services.ArraysService.manualFill;

import static com.bookverse.development.packapps.utils.constants.Styles.BIG;
import static com.bookverse.development.packapps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.utils.constants.Styles.SMALL;
import static com.bookverse.development.packapps.utils.constants.Styles.TEXT_COLOR;

public class ArraysView extends JDialog implements ActionListener {

  private JButton btnDeterminant, btnAction, btnAuto, btnClean, btnTransposed, btnDiagonals, btnMultiply;
  private JTextField txtRows, txtColumns;
  private Resources resources = new Resources();
  private JButton[][] arrayBoard;

  public ArraysView(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  private void createComponents() {

    setLayout(null);

    arrayBoard = new JButton[9][9];
    int x = 250;
    int y = 80;

    for (int f = 0; f < arrayBoard.length; f++) {
      for (int c = 0; c < arrayBoard.length; c++) {
        arrayBoard[f][c] = resources.getButton("", null, this, this);
        arrayBoard[f][c].setBounds(x, y, 45, 45);
        arrayBoard[f][c].setVisible(false);
        x = x + 45;
      }

      x = 250;
      y = y + 45;
    }

    JLabel welcome = resources
        .getLabel("<html><em><strong>Matrix operations</strong></em></html>", MAIN_COLOR, this,
            BIG);
    welcome.setBounds(320, 10, 500, 40);

    btnAction = resources.getButton("Show", TEXT_COLOR, this, this);
    btnAction.setBounds(20, 190, 70, 25);

    btnAuto = resources.getButton("Auto fill", TEXT_COLOR, this, this);
    btnAuto.setBounds(120, 100, 90, 25);
    btnAuto.setEnabled(false);

    btnClean = resources.getButton("Clean", MAIN_COLOR, this, this);
    btnClean.setBounds(120, 150, 90, 25);
    btnClean.setEnabled(false);

    btnTransposed = resources.getButton("Transpose", TEXT_COLOR, this, this);
    btnTransposed.setBounds(720, 100, 120, 25);
    btnTransposed.setEnabled(false);

    btnMultiply = resources.getButton("Multiply", TEXT_COLOR, this, this);
    btnMultiply.setBounds(720, 200, 120, 25);
    btnMultiply.setEnabled(false);

    btnDeterminant = resources.getButton("|A|", TEXT_COLOR, this, this);
    btnDeterminant.setBounds(720, 250, 120, 25);
    btnDeterminant.setEnabled(false);

    btnDiagonals = resources.getButton("Diagonals", TEXT_COLOR, this, this);
    btnDiagonals.setBounds(720, 150, 120, 25);
    btnDiagonals.setEnabled(false);

    JLabel rows = resources
        .getLabel("<html><em>Rows</em></html>", TEXT_COLOR, this,
            SMALL);
    rows.setBounds(38, 80, 100, 25);

    txtRows = new JTextField();
    txtRows.setBounds(20, 100, 70, 25);
    txtRows.setHorizontalAlignment(JTextField.CENTER);
    add(txtRows);

    txtRows.addKeyListener(new KeyAdapter() {

      public void keyTyped(KeyEvent e) {
        txtNumKeyTyped(e);
      }

      public void txtNumKeyTyped(KeyEvent e) {
        Format.onlyNumbers(e.getKeyChar(), e, txtRows.getText(), 1);
      }
    });

    JLabel columns = resources.getLabel("<html><em>Columns</em></html>", TEXT_COLOR, this,
        SMALL);
    columns.setBounds(27, 130, 100, 25);

    txtColumns = new JTextField();
    txtColumns.setBounds(20, 150, 70, 25);
    txtColumns.setHorizontalAlignment(JTextField.CENTER);
    add(txtColumns);

    txtColumns.addKeyListener(new KeyAdapter() {

      public void keyTyped(KeyEvent e) {
        txtNumKeyTyped(e);
      }

      public void keyPressed(KeyEvent e) {
        txtNumKeyPressed(e);
      }

      public void txtNumKeyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }

      public void txtNumKeyTyped(KeyEvent e) {
        Format.onlyNumbers(e.getKeyChar(), e, txtColumns.getText(), 1);
      }
    });
  }

  public void start(JDialog parent) {
    setBounds(0, 0, 900, 600);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Arrays");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnAction) {
      clickOnAction(arrayBoard, txtRows, txtColumns, this, btnDeterminant, btnAction, btnAuto, btnClean, btnTransposed, btnDiagonals, btnMultiply);
    } else if (e.getSource() == btnAuto) {
      clickOnAutoFill(arrayBoard, Integer.parseInt(txtRows.getText()), Integer.parseInt(txtColumns.getText()), this);
    } else if (e.getSource() == btnClean) {
      clickOnClear(arrayBoard, txtRows, txtColumns, this);
    } else if (e.getSource() == btnTransposed) {
      clickOnTransposed(arrayBoard, txtRows, txtColumns, this);
    } else if (e.getSource() == btnDiagonals) {
      clickOnDiagonals(arrayBoard, txtRows, txtColumns);
    } else if (e.getSource() == btnMultiply) {
      clickOnMultiply(arrayBoard, txtRows, txtColumns, this);
    } else if (e.getSource() == btnDeterminant) {
      clickOnDeterminant(arrayBoard, txtRows, txtColumns);
    }

    if (btnAction.getText().equals("New")) {
      manualFill(e, arrayBoard, Integer.parseInt(txtRows.getText()), Integer.parseInt(txtColumns.getText()));
    }
  }
}