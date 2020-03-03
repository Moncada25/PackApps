package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.automation.utils.Paths.ULTIMATIX;
import static javax.swing.SwingConstants.CENTER;

import com.bookverse.development.packapps.automation.models.UltimatixData;
import com.bookverse.development.packapps.automation.stepdefinitions.TimesheetStepDefinitions;
import com.bookverse.development.packapps.automation.utils.TextUtility;
import com.bookverse.development.packapps.core.Resources;
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

  Resources img = new Resources();

  private JLabel tittle, user, password, hours;
  private JTextField txtUser, txtHours;
  private JButton btnRun, btnCancel;
  private JPasswordField txtPassword;

  public Timesheet(JFrame parent, boolean modal) {

    super(parent, modal);

    componentes();
  }

  // Se crean los componentes de la ventana
  private void componentes() {

    setLayout(null);
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

    btnRun = img.getBoton("Run", img.cr.AZUL, this, this);
    btnRun.setBounds(60, 215, 100, 30);

    btnCancel = img.getBoton("Return", img.cr.ROJO, this, this);
    btnCancel.setBounds(300, 215, 86, 30);

    tittle = img
        .getLabel("<html><strong><em>Timesheet Entry</em></strong></html>", img.cr.ROJO, this,
            img.cr.BIG);
    tittle.setBounds(120, 5, 250, 40);

    user = img.getLabel("<html><strong>Username</strong></html>", img.cr.AZUL, this, img.cr.MEDIUM);
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
        img.cr.soloAlfa(evt.getKeyChar(), evt, txtUser.getText(), 20);
      }
    });

    password = img
        .getLabel("<html><strong>Password</strong></html>", img.cr.AZUL, this, img.cr.MEDIUM);
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

    hours = img.getLabel("<html><strong>Hours</strong></html>", img.cr.AZUL, this, img.cr.MEDIUM);
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
        img.cr.solonumeros(evt.getKeyChar(), evt, txtHours.getText(), 20);
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
    img.cr.fadeOut(this);
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
      img.cr.mostrarMensaje("Error", "Campos requeridos incompletos");
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
