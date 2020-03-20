package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.utils.AppConstants.LOANS;

import com.bookverse.development.packapps.core.AppConfig;
import com.bookverse.development.packapps.models.Database;
import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.Format;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Loans extends JDialog implements ActionListener {

  Resources resources = new Resources();
  private JButton btnLoan, btnExit;
  private JTextField txtReference, txtDocument, txtPhone, txtPrice, txtName;
  private JComboBox<String> weeks, months;
  private boolean validReference = false, validPrice = false;

  public Loans(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JDialog parent) {
    setSize(450, 400);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle(LOANS);
    AppConfig.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void createComponents() {

    setLayout(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(resources.getImage("prestar.png")).getImage());

    btnExit = resources.getButton("Return", AppConfig.MAIN_COLOR, this, this);
    btnExit.setBounds(330, 320, 86, 30);

    btnLoan = resources.getButton("Lend", AppConfig.TEXT_COLOR, this, this);
    btnLoan.setBounds(30, 320, 86, 30);

    JLabel tittle = resources.getLabel("<html><strong><em>Register loan</em></strong></html>",
        AppConfig.MAIN_COLOR, this, AppConfig.BIG);
    tittle.setBounds(100, 5, 300, 40);

    JLabel product = resources
        .getLabel("<html><strong>Client</strong></html>", AppConfig.TEXT_COLOR,
            this, AppConfig.MEDIUM);
    product.setBounds(30, 60, 140, 30);

    txtName = new JTextField();
    txtName.setBounds(180, 65, 200, 30);
    txtName.setHorizontalAlignment(JTextField.CENTER);
    add(txtName);

    txtName.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtnombreKeyTyped(evt);
      }

      private void txtnombreKeyTyped(KeyEvent evt) {
          Format.onlyText(evt.getKeyChar(), evt, txtName.getText(), 25);
      }

    });

    JLabel document = resources
        .getLabel("<html><strong>Document</strong></html>", AppConfig.TEXT_COLOR,
            this, AppConfig.MEDIUM);
    document.setBounds(30, 100, 140, 30);

    txtDocument = new JTextField();
    txtDocument.setBounds(180, 105, 200, 30);
    txtDocument.setHorizontalAlignment(JTextField.CENTER);
    add(txtDocument);

    txtDocument.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtDocKeyTyped(evt);
      }

      private void txtDocKeyTyped(KeyEvent evt) {
        Format.onlyNumbers(evt.getKeyChar(), evt, txtDocument.getText(), 10);
      }
    });

    JLabel reference = resources
        .getLabel("<html><strong>Reference</strong></html>", AppConfig.TEXT_COLOR,
            this, AppConfig.MEDIUM);
    reference.setBounds(30, 140, 130, 30);

    txtReference = new JTextField();
    txtReference.setBounds(180, 145, 200, 30);
    txtReference.setHorizontalAlignment(JTextField.CENTER);
    add(txtReference);

    txtReference.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtRefKeyTyped(evt);
      }

      private void txtRefKeyTyped(KeyEvent evt) {
        Format.onlyAlfa(evt.getKeyChar(), evt, txtReference.getText(), 15);
      }
    });

    JLabel phone = resources
        .getLabel("<html><strong>Phone</strong></html>", AppConfig.TEXT_COLOR,
            this, AppConfig.MEDIUM);
    phone.setBounds(30, 180, 130, 30);

    txtPhone = new JTextField();
    txtPhone.setBounds(180, 185, 200, 30);
    txtPhone.setHorizontalAlignment(JTextField.CENTER);
    add(txtPhone);

    txtPhone.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtTelKeyTyped(evt);
      }

      private void txtTelKeyTyped(KeyEvent evt) {
        Format.onlyNumbers(evt.getKeyChar(), evt, txtPhone.getText(), 10);
      }

    });

    JLabel time = resources
        .getLabel("<html><strong>Time</strong></html>", AppConfig.TEXT_COLOR, this,
            AppConfig.MEDIUM);
    time.setBounds(30, 220, 130, 30);

    weeks = new JComboBox<>();
    weeks.setBounds(180, 227, 105, 25);
    weeks.setModel(new DefaultComboBoxModel<>(new String[]{"Weeks", "1", "2", "3"}));
    add(weeks);

    months = new JComboBox<>();
    months.setBounds(295, 227, 85, 25);
    months
        .setModel(new DefaultComboBoxModel<>(new String[]{"Months", "1", "2", "3", "4", "5", "6"}));
    add(months);

    JLabel price = resources
        .getLabel("<html><strong>Loan of</strong></html>", AppConfig.TEXT_COLOR,
            this, AppConfig.MEDIUM);
    price.setBounds(30, 260, 130, 30);

    txtPrice = new JTextField("0");
    txtPrice.setBounds(180, 265, 200, 30);
    txtPrice.setHorizontalAlignment(JTextField.CENTER);
    add(txtPrice);

    txtPrice.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtPrecioKeyTyped(evt);
      }

      private void txtPrecioKeyTyped(KeyEvent evt) {
        Format.onlyNumbers(evt.getKeyChar(), evt, txtPrice.getText(), 9);
      }

    });
  }

  private void btnExitAP() {
    txtReference.setText("");
    txtPhone.setText("");
    txtPrice.setText("");
    txtPhone.setEnabled(true);
    txtPrice.setEnabled(true);
    AppConfig.fadeOut(this);
  }

  private void btnSubmitAP() {

    boolean validDocument = false;
    if (Format.verifyDocument(txtDocument.getText())) {
      txtReference.setEnabled(false);
      txtPhone.setEnabled(false);
      txtPrice.setEnabled(false);
      weeks.setEnabled(false);
      months.setEnabled(false);
      txtDocument.requestFocus();
      validDocument = false;
    } else {
      txtReference.setEnabled(true);
      txtPhone.setEnabled(true);
      txtPrice.setEnabled(true);
      weeks.setEnabled(true);
      months.setEnabled(true);
      validDocument = true;
    }

    if (validDocument) {
      if (Format.verifyReference(txtReference.getText())) {
        txtPhone.setEnabled(false);
        txtPrice.setEnabled(false);
        weeks.setEnabled(false);
        months.setEnabled(false);
        txtReference.requestFocus();
        validReference = false;
      } else {
        txtPhone.setEnabled(true);
        txtPrice.setEnabled(true);
        weeks.setEnabled(true);
        months.setEnabled(true);
        validReference = true;
      }
    }

      boolean validPhone = false;
      if (validReference && validDocument) {
      if (!Format.verifyPhone(txtPhone.getText())) {
        txtPrice.setEnabled(false);
        weeks.setEnabled(false);
        months.setEnabled(false);
      } else {
        txtPrice.setEnabled(true);
        weeks.setEnabled(true);
        months.setEnabled(true);
      }
      validPhone = true;
    } else {
      txtPrice.setEnabled(false);
      weeks.setEnabled(false);
      months.setEnabled(false);
      txtPhone.requestFocus();
      validPhone = false;
    }

    if (validReference && validPhone) {

      if (Format.verifyPrice(Double.parseDouble(txtPrice.getText()))) {
        validPrice = true;
      } else {
        validPrice = false;
        txtPrice.requestFocus();
      }
    }

    if (validReference && validPhone && validPrice
        && (months.getSelectedItem() != "Month" || weeks.getSelectedItem() != "Weeks")) {

      GregorianCalendar date = new GregorianCalendar();
      DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

      if (months.getSelectedItem() == "Month") {
        date.add(Calendar.DATE, Integer.parseInt(String.valueOf(weeks.getSelectedItem())) * 7);
      } else if (weeks.getSelectedItem() == "Weeks") {
        date.add(Calendar.MONTH, Integer.parseInt(String.valueOf(months.getSelectedItem())));
      } else {
        date.add(Calendar.DATE, Integer.parseInt(String.valueOf(weeks.getSelectedItem())) * 7);
        date.add(Calendar.MONTH, Integer.parseInt(String.valueOf(months.getSelectedItem())));
      }

      Database.store.setTotalLoans(Double.parseDouble(txtPrice.getText()));
      String nom = "";

      nom = Database.searchUserLogged("Online");

      if (Database.searchUser(nom)) {
        Database.updateLoan(nom, Database.store.getTotalLoans());
      } else {

        String[] data = {LOANS, nom, String.valueOf(0), String.valueOf(0.0),
            String.valueOf(0), String.valueOf(0.0),
            String.valueOf(Database.store.getTotalLoans())};

        Database.insertData(data);
      }

      String[] data = {LOANS, Database.searchUserLogged("Online"), txtName.getText(),
          txtDocument.getText(),
          txtReference.getText(), txtPhone.getText(), dateFormat.format(date.getTime()),
          txtPrice.getText()};

      Database.insertData(data);

      Database.store.setTotalLoans(Double.parseDouble(txtPrice.getText()));
      Alerts.actionSuccessfully("Lend ", dateFormat.format(date.getTime()),
          Double.parseDouble(txtPrice.getText()));
      Database.store.setTotalLoans(0.0);

      txtName.setText("");
      txtReference.setText("");
      txtDocument.setText("");
      txtPhone.setText("");
      txtPrice.setText("0");
      txtName.requestFocus();
      months.setSelectedItem("Months");
      weeks.setSelectedItem("Weeks");
    } else {

      if (validReference && validPhone && validPrice) {
        Alerts.message("Message", "Time not defined");
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btnExit) {
      btnExitAP();
    } else if (e.getSource() == btnLoan) {
      btnSubmitAP();
    }
  }
}