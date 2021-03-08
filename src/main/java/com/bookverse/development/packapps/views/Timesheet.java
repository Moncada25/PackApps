package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.automation.utils.Constants.CHRONUS;
import static com.bookverse.development.packapps.core.Settings.BIG;
import static com.bookverse.development.packapps.core.Settings.MAIN_COLOR;
import static com.bookverse.development.packapps.core.Settings.MEDIUM;
import static com.bookverse.development.packapps.core.Settings.SMALL;
import static com.bookverse.development.packapps.core.Settings.TEXT_COLOR;
import static java.awt.Event.ENTER;
import static javax.swing.SwingConstants.CENTER;

import com.bookverse.development.packapps.automation.models.Chronus;
import com.bookverse.development.packapps.automation.runners.RunTimesheetEntry;
import com.bookverse.development.packapps.core.Resources;
import com.bookverse.development.packapps.core.Settings;
import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.Format;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import org.junit.runner.JUnitCore;

public class Timesheet extends JDialog implements ActionListener {

  private Resources resources = new Resources();
  private JTextField txtUser, txtHours, txtCategory;
  private JTextArea txtDescription;
  private JButton btnRun, btnCancel;
  private JPasswordField txtPassword;

  public Timesheet(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JFrame parent) {
    setSize(460, 500);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Timesheet Entry");
    Settings.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void createComponents() {

    setLayout(null);
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

    btnRun = resources.getButton("Run", TEXT_COLOR, this, this);
    btnRun.setBounds(60, 420, 100, 30);

    btnCancel = resources.getButton("Return", MAIN_COLOR, this, this);
    btnCancel.setBounds(300, 420, 100, 30);

    JLabel title = resources
        .getLabel("<html><strong><em>Timesheet Entry</em></strong></html>", MAIN_COLOR, this,
            BIG);
    title.setBounds(105, 5, 250, 40);

    JLabel user = resources
        .getLabel("<html><strong>Username</strong></html>", TEXT_COLOR, this, MEDIUM);
    user.setBounds(30, 60, 180, 30);

    txtUser = new JTextField("santiago.moncada");
    txtUser.setBounds(250, 65, 150, 30);
    txtUser.setHorizontalAlignment(CENTER);
    txtUser.setFont(SMALL);
    add(txtUser);

    txtUser.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtUserKeyTyped(evt);
      }

      private void txtUserKeyTyped(KeyEvent evt) {
        Format.anyone(evt.getKeyChar(), evt, txtUser.getText(), 30);
      }
    });

    JLabel password = resources
        .getLabel("<html><strong>Password</strong></html>", TEXT_COLOR, this, MEDIUM);
    password.setBounds(30, 110, 180, 30);

    txtPassword = new JPasswordField("");
    txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
    txtPassword.setBounds(250, 115, 150, 30);
    add(txtPassword);

    txtPassword.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtCodKeyTyped(evt);
      }

      private void txtCodKeyTyped(KeyEvent evt) {
        Format.anyone(evt.getKeyChar(), evt, String.valueOf(txtPassword.getPassword()), 30);
      }
    });

    JLabel hours = resources
        .getLabel("<html><strong>Hours</strong></html>", TEXT_COLOR, this, MEDIUM);
    hours.setBounds(30, 160, 180, 30);

    txtHours = new JTextField("9");
    txtHours.setHorizontalAlignment(CENTER);
    txtHours.setBounds(250, 165, 150, 30);
    add(txtHours);

    txtHours.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtHoursKeyTyped(evt);
      }

      private void txtHoursKeyTyped(KeyEvent evt) {
        Format.onlyNumbers(evt.getKeyChar(), evt, txtHours.getText(), 2);
      }
    });

    JLabel category = resources
        .getLabel("<html><strong>Category</strong></html>", TEXT_COLOR, this, MEDIUM);
    category.setBounds(30, 210, 180, 30);

    txtCategory = new JTextField("PMO27189");
    txtCategory.setBounds(250, 215, 150, 30);
    txtCategory.setHorizontalAlignment(CENTER);
    add(txtCategory);

    txtCategory.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtCategoryKeyTyped(evt);
      }

      private void txtCategoryKeyTyped(KeyEvent evt) {
        Format.onlyAlfa(evt.getKeyChar(), evt, txtCategory.getText(), 20);
      }
    });

    JLabel description = resources
        .getLabel("<html><strong>Description</strong></html>", TEXT_COLOR, this, MEDIUM);
    description.setBounds(150, 260, 150, 30);

    txtDescription = new JTextArea();
    JScrollPane scroll = new JScrollPane(txtDescription);
    scroll.setBounds(60, 300, 340, 100);
    add(scroll);

    txtDescription.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == ENTER) {
          btnRunAP();
        }
      }
    });

    txtDescription.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtDescriptionKeyTyped(evt);
      }

      private void txtDescriptionKeyTyped(KeyEvent evt) {
        Format.anyone(evt.getKeyChar(), evt, txtDescription.getText(), 100);
      }
    });
  }

  private void btnReturnAP() {
    txtUser.setText("");
    txtPassword.setText("");
    txtHours.setText("");
    txtUser.setEnabled(true);
    txtPassword.setEnabled(true);
    Settings.fadeOut(this);
  }

  private void btnRunAP() {

    if (txtUser.getText().length() > 5 && String.valueOf(txtPassword.getPassword()).length() > 5
        && txtHours.getText().length() > 0 && txtDescription.getText().length() > 5
        && txtCategory.getText().length() > 5) {
      Resources.setGeneralObject(new Chronus(
          txtUser.getText(),
          String.valueOf(txtPassword.getPassword()),
          CHRONUS,
          txtHours.getText(),
          txtCategory.getText(),
          txtDescription.getText())
      );

      JUnitCore.runClasses(RunTimesheetEntry.class);
    } else {
      Alerts.inputSomethingText();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnCancel) {
      btnReturnAP();
    } else if (e.getSource() == btnRun) {
      btnRunAP();
    }
  }
}