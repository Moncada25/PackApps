package com.bookverse.development.packapps.apps.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.bookverse.development.packapps.apps.services.ArraysService;
import com.bookverse.development.packapps.apps.utils.constants.Styles;
import com.bookverse.development.packapps.apps.utils.ui.Resources;
import com.bookverse.development.packapps.apps.utils.other.Format;
import com.bookverse.development.packapps.apps.utils.ui.Effects;

public class ArraysView extends JDialog implements ActionListener {

  private JButton btnDeterminant;
  private JButton btnAction;
  private JButton btnAuto;
  private JButton btnClean;
  private JButton btnTransposed;
  private JButton btnDiagonals;
  private JButton btnMultiply;
  private JTextField txtRows;
  private JTextField txtColumns;
  
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
        arrayBoard[f][c] = Resources.getButton("", null, this, this);
        arrayBoard[f][c].setBounds(x, y, 45, 45);
        arrayBoard[f][c].setVisible(false);
        x = x + 45;
      }

      x = 250;
      y = y + 45;
    }

    JLabel welcome = Resources
        .getLabel("<html><em><strong>Matrix operations</strong></em></html>", Styles.MAIN_COLOR, this,
            Styles.BIG);
    welcome.setBounds(220, 10, 500, 40);

    btnAction = Resources.getButton("Show", Styles.TEXT_COLOR, this, this);
    btnAction.setBounds(20, 190, 70, 25);

    btnAuto = Resources.getButton("Auto fill", Styles.TEXT_COLOR, this, this);
    btnAuto.setBounds(120, 100, 90, 25);
    btnAuto.setEnabled(false);

    btnClean = Resources.getButton("Clean", Styles.MAIN_COLOR, this, this);
    btnClean.setBounds(120, 150, 90, 25);
    btnClean.setEnabled(false);

    btnTransposed = Resources.getButton("Transpose", Styles.TEXT_COLOR, this, this);
    btnTransposed.setBounds(720, 100, 120, 25);
    btnTransposed.setEnabled(false);

    btnMultiply = Resources.getButton("Multiply", Styles.TEXT_COLOR, this, this);
    btnMultiply.setBounds(720, 200, 120, 25);
    btnMultiply.setEnabled(false);

    btnDeterminant = Resources.getButton("|A|", Styles.TEXT_COLOR, this, this);
    btnDeterminant.setBounds(720, 250, 120, 25);
    btnDeterminant.setEnabled(false);

    btnDiagonals = Resources.getButton("Diagonals", Styles.TEXT_COLOR, this, this);
    btnDiagonals.setBounds(720, 150, 120, 25);
    btnDiagonals.setEnabled(false);

    JLabel rows = Resources
        .getLabel("<html><em>Rows</em></html>", Styles.TEXT_COLOR, this,
            Styles.SMALL);
    rows.setBounds(28, 80, 50, 25);

    txtRows = new JTextField();
    txtRows.setBounds(20, 100, 70, 25);
    txtRows.setHorizontalAlignment(SwingConstants.CENTER);
    add(txtRows);

    txtRows.addKeyListener(new KeyAdapter() {

      @Override
      public void keyTyped(KeyEvent e) {
        txtNumKeyTyped(e);
      }

      public void txtNumKeyTyped(KeyEvent e) {
        Format.onlyNumbers(e.getKeyChar(), e, txtRows.getText(), 1);
      }
    });

    JLabel columns = Resources.getLabel("<html><em>Columns</em></html>", Styles.TEXT_COLOR, this,
        Styles.SMALL);
    columns.setBounds(10, 130, 90, 25);

    txtColumns = new JTextField();
    txtColumns.setBounds(20, 150, 70, 25);
    txtColumns.setHorizontalAlignment(SwingConstants.CENTER);
    add(txtColumns);

    txtColumns.addKeyListener(new KeyAdapter() {

      @Override
      public void keyTyped(KeyEvent e) {
        txtNumKeyTyped(e);
      }

      @Override
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
      ArraysService.clickOnAction(arrayBoard, txtRows, txtColumns, this, btnDeterminant, btnAction, btnAuto, btnClean, btnTransposed, btnDiagonals, btnMultiply);
    } else if (e.getSource() == btnAuto) {
      ArraysService.clickOnAutoFill(arrayBoard, Integer.parseInt(txtRows.getText()), Integer.parseInt(txtColumns.getText()), this);
    } else if (e.getSource() == btnClean) {
      ArraysService.clickOnClear(arrayBoard, txtRows, txtColumns, this);
    } else if (e.getSource() == btnTransposed) {
      ArraysService.clickOnTransposed(arrayBoard, txtRows, txtColumns, this);
    } else if (e.getSource() == btnDiagonals) {
      ArraysService.clickOnDiagonals(arrayBoard, txtRows, txtColumns);
    } else if (e.getSource() == btnMultiply) {
      ArraysService.clickOnMultiply(arrayBoard, txtRows, txtColumns, this);
    } else if (e.getSource() == btnDeterminant) {
      ArraysService.clickOnDeterminant(arrayBoard, txtRows, txtColumns);
    }

    if (btnAction.getText().equals("New")) {
      ArraysService.manualFill(e, arrayBoard, Integer.parseInt(txtRows.getText()), Integer.parseInt(txtColumns.getText()));
    }
  }
}