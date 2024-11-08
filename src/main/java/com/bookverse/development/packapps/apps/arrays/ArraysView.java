package com.bookverse.development.packapps.apps.arrays;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.utils.Format;
import com.bookverse.development.packapps.utils.ui.Effects;
import com.bookverse.development.packapps.utils.ui.factory.Button;
import com.bookverse.development.packapps.utils.ui.factory.Label;

public class ArraysView extends JDialog {

  private transient ArraysService service = new ArraysService();
  private transient ArraysViewModel model = null;

  public ArraysView(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
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

  private void createComponents() {

    setLayout(null);

    JButton[][] arrayBoard = new JButton[9][9];
    int x = 250;
    int y = 80;

    for (int f = 0; f < arrayBoard.length; f++) {
      for (int c = 0; c < arrayBoard.length; c++) {
        arrayBoard[f][c] = new Button().setText("").build();
        arrayBoard[f][c].setBounds(x, y, 45, 45);
        arrayBoard[f][c].setVisible(false);
        add(arrayBoard[f][c]);
        x = x + 45;
      }

      x = 250;
      y = y + 45;
    }

    JLabel title = new Label().setText("<html><em><strong>Matrix operations</strong></em></html>")
        .setColor(Styles.MAIN_COLOR)
        .setFont(Styles.BIG)
        .build();
    title.setBounds(200, 10, 500, 40);
    add(title, BorderLayout.NORTH);

    JButton btnAction = new Button().setText("Show").setColor(Styles.TEXT_COLOR).build();
    btnAction.setBounds(20, 190, 70, 25);
    btnAction.addActionListener(e -> service.clickOnAction(model));
    add(btnAction);

    JButton btnAuto = new Button().setText("Auto fill").setColor(Styles.TEXT_COLOR).build();
    btnAuto.setBounds(120, 100, 90, 25);
    btnAuto.addActionListener(e -> service.clickOnAutoFill(
            model.getBoard(),
            Integer.parseInt(model.getTxtRows().getText()),
            Integer.parseInt(model.getTxtColumns().getText()),
            model.getParent()
        )
    );
    btnAuto.setEnabled(false);
    add(btnAuto);

    JButton btnClean = new Button().setText("Clean").setColor(Styles.MAIN_COLOR).build();
    btnClean.setBounds(120, 150, 90, 25);
    btnClean.addActionListener(e -> service.clickOnClear(model));
    btnClean.setEnabled(false);
    add(btnClean);

    JButton btnTransposed = new Button().setText("Transpose").setColor(Styles.TEXT_COLOR).build();
    btnTransposed.setBounds(720, 100, 120, 25);
    btnTransposed.addActionListener(e -> service.clickOnTransposed(model));
    btnTransposed.setEnabled(false);
    add(btnTransposed);

    JButton btnMultiply = new Button().setText("Multiply").setColor(Styles.TEXT_COLOR).build();
    btnMultiply.setBounds(720, 200, 120, 25);
    btnMultiply.addActionListener(e -> service.clickOnMultiply(model));
    btnMultiply.setEnabled(false);
    add(btnMultiply);

    JButton btnDeterminant = new Button().setText("|A|").setColor(Styles.TEXT_COLOR).build();
    btnDeterminant.setBounds(720, 250, 120, 25);
    btnDeterminant.addActionListener(e -> service.clickOnDeterminant(model));
    btnDeterminant.setEnabled(false);
    add(btnDeterminant);

    JButton btnDiagonals = new Button().setText("Diagonals").setColor(Styles.TEXT_COLOR).build();
    btnDiagonals.setBounds(720, 150, 120, 25);
    btnDiagonals.addActionListener(e -> service.clickOnDiagonals(model));
    btnDiagonals.setEnabled(false);
    add(btnDiagonals);

    JLabel rows = new Label().setText("<html><em>Rows</em></html>").setColor(Styles.TEXT_COLOR)
        .setFont(Styles.SMALL).build();
    rows.setBounds(28, 80, 50, 25);
    add(rows);

    JTextField txtRows = new JTextField();
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

    JLabel columns = new Label().setText("<html><em>Columns</em></html>")
        .setColor(Styles.TEXT_COLOR).setFont(Styles.SMALL).build();
    columns.setBounds(10, 130, 90, 25);
    add(columns);

    JTextField txtColumns = new JTextField();
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

    model = new ArraysViewModel(
        btnDeterminant,
        btnAction,
        btnAuto,
        btnClean,
        btnTransposed,
        btnDiagonals,
        btnMultiply,
        txtRows,
        txtColumns,
        arrayBoard,
        this
    );
  }
}