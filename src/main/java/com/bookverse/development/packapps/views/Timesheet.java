package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.automation.utils.Paths.ULTIMATIX;
import static com.bookverse.development.packapps.core.AppConfig.BIG;
import static com.bookverse.development.packapps.core.AppConfig.MAIN_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.MEDIUM;
import static com.bookverse.development.packapps.core.AppConfig.TEXT_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.fadeIn;
import static com.bookverse.development.packapps.core.AppConfig.fadeOut;
import static javax.swing.SwingConstants.CENTER;

import com.bookverse.development.packapps.automation.models.UltimatixData;
import com.bookverse.development.packapps.automation.stepdefinitions.TimesheetStepDefinitions;
import com.bookverse.development.packapps.automation.utils.TextUtility;
import com.bookverse.development.packapps.models.Resources;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import org.junit.runner.JUnitCore;

public class Timesheet extends JDialog implements ActionListener {

  Resources resources = new Resources();

  private JLabel tittle, user, password, hours;
  private JTextField txtUser, txtHours;
  private JButton btnRun, btnCancel;
  private JPasswordField txtPassword;

  public Timesheet(JFrame parent, boolean modal) {

    super(parent, modal);

    componentes();
  }

  public void start(JFrame parent) {
    setSize(460, 300);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Timesheet Entry");
    fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  // Se crean los componentes de la ventana
  private void componentes() {

    setLayout(null);
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

    btnRun = resources.getButton("Run", TEXT_COLOR, this, this);
    btnRun.setBounds(60, 215, 100, 30);

    btnCancel = resources.getButton("Return", MAIN_COLOR, this, this);
    btnCancel.setBounds(300, 215, 86, 30);

    tittle = resources
        .getLabel("<html><strong><em>Timesheet Entry</em></strong></html>", MAIN_COLOR, this,
            BIG);
    tittle.setBounds(120, 5, 250, 40);

    user = resources
        .getLabel("<html><strong>Username</strong></html>", TEXT_COLOR, this, MEDIUM);
    user.setBounds(30, 60, 180, 30);

    txtUser = new JTextField();
    txtUser.setBounds(250, 65, 150, 30);
    txtUser.setHorizontalAlignment(CENTER);
    add(txtUser);

    txtUser.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtUsuarioKeyTyped(evt);
      }

      private void txtUsuarioKeyTyped(KeyEvent evt) {
        Format.onlyAlfa(evt.getKeyChar(), evt, txtUser.getText(), 20);
      }
    });

    password = resources
        .getLabel("<html><strong>Password</strong></html>", TEXT_COLOR, this, MEDIUM);
    password.setBounds(30, 110, 120, 30);

    txtPassword = new JPasswordField();
    txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
    txtPassword.setBounds(250, 115, 150, 30);
    add(txtPassword);

    txtPassword.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtCodKeyTyped(evt);
      }

      private void txtCodKeyTyped(KeyEvent evt) {
        TextUtility.textPass(evt.getKeyChar(), evt, String.valueOf(txtPassword.getPassword()), 30);
      }
    });

    hours = resources
        .getLabel("<html><strong>Hours</strong></html>", TEXT_COLOR, this, MEDIUM);
    hours.setBounds(30, 160, 180, 30);

    txtHours = new JTextField();
    txtHours.setHorizontalAlignment(CENTER);
    txtHours.setBounds(250, 165, 150, 30);
    add(txtHours);

    txtHours.addKeyListener(new KeyAdapter() {

      public void keyTyped(KeyEvent evt) {
        txtHoursTyped(evt);
      }

      private void txtHoursTyped(KeyEvent evt) {
        Format.onlyNumbers(evt.getKeyChar(), evt, txtHours.getText(), 20);
      }

      public void keyPressed(KeyEvent evt) {
        txtHoursPressed(evt);
      }

      private void txtHoursPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER && txtUser.getText().length() > 0
            && txtHours.getText().length() > 0) {
          btnRunAP();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }
    });
  }

  private void btnCancelarAP() {

    txtUser.setText("");
    txtPassword.setText("");
    txtHours.setText("");
    txtUser.setEnabled(true);
    txtPassword.setEnabled(true);
    fadeOut(this);
  }

  private void btnRunAP() {

    if (txtUser.getText().length() > 5 && txtUser.getText().length() > 5
        && txtHours.getText().length() > 0) {
      UltimatixData data = new UltimatixData(txtUser.getText(),
          String.valueOf(txtPassword.getPassword()),
          ULTIMATIX,
          txtHours.getText());
      Resources.generalObject = data;
      JUnitCore.runClasses(TimesheetStepDefinitions.class);
    } else {
      Alerts.inputSomethingText();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnCancel) {
      btnCancelarAP();
    } else if (e.getSource() == btnRun) {
      btnRunAP();
    }
  }
}
