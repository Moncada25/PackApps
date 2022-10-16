package com.bookverse.development.packapps.apps.views.older;

import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.CASH_REGISTER;
import static com.bookverse.development.packapps.apps.utils.constants.DatabaseConstants.LOANS;

import com.bookverse.development.packapps.apps.utils.constants.Styles;
import com.bookverse.development.packapps.apps.utils.ui.Resources;
import com.bookverse.development.packapps.apps.repositories.Database;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;
import com.bookverse.development.packapps.apps.utils.other.Format;
import com.bookverse.development.packapps.apps.utils.ui.Effects;
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
  private JTextField txtReference, txtDocument, txtPhone, txtValue, txtName;
  private JComboBox<String> weeks, months;

  public Loans(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JDialog parent) {
    setSize(450, 400);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Lend");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void createComponents() {

    setLayout(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(resources.getImage("prestar.png")).getImage());

    btnExit = resources.getButton("Return", Styles.MAIN_COLOR, this, this);
    btnExit.setBounds(330, 320, 86, 30);

    btnLoan = resources.getButton("Lend", Styles.TEXT_COLOR, this, this);
    btnLoan.setBounds(30, 320, 86, 30);

    JLabel title = resources.getLabel("<html><strong><em>Register loan</em></strong></html>",
        Styles.MAIN_COLOR, this, Styles.BIG);
    title.setBounds(135, 5, 300, 40);

    JLabel product = resources
        .getLabel("<html><strong>Client</strong></html>", Styles.TEXT_COLOR,
            this, Styles.MEDIUM);
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
        .getLabel("<html><strong>Document</strong></html>", Styles.TEXT_COLOR,
            this, Styles.MEDIUM);
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
        .getLabel("<html><strong>Reference</strong></html>", Styles.TEXT_COLOR,
            this, Styles.MEDIUM);
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
        .getLabel("<html><strong>Phone</strong></html>", Styles.TEXT_COLOR,
            this, Styles.MEDIUM);
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
        .getLabel("<html><strong>Time</strong></html>", Styles.TEXT_COLOR, this,
            Styles.MEDIUM);
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
        .getLabel("<html><strong>Loan of</strong></html>", Styles.TEXT_COLOR,
            this, Styles.MEDIUM);
    price.setBounds(30, 260, 130, 30);

    txtValue = new JTextField("0");
    txtValue.setBounds(180, 265, 200, 30);
    txtValue.setHorizontalAlignment(JTextField.CENTER);
    add(txtValue);

    txtValue.addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent evt) {
        txtPriceKeyTyped(evt);
      }

      private void txtPriceKeyTyped(KeyEvent evt) {
        Format.onlyNumbers(evt.getKeyChar(), evt, txtValue.getText(), 9);
      }

    });
  }

  private void btnExitAP() {
    txtReference.setText("");
    txtPhone.setText("");
    txtValue.setText("");
    txtPhone.setEnabled(true);
    txtValue.setEnabled(true);
    Effects.fadeOut(this);
  }

  private void btnSubmitAP() {

    if (txtName.getText().length() < 8) {
      Alerts.message("Verify!", "Input a valid client name.");
      txtName.requestFocus();
    } else {

      if (Format.verifyDocument(txtDocument.getText())) {
        Alerts.message("Verify!", "Input a valid document.");
        txtDocument.requestFocus();
      } else {

        if (Format.verifyReference(txtReference.getText())) {
          Alerts.message("Verify!", "Input a valid reference.");
          txtReference.requestFocus();
        } else {

          if (Format.verifyPhone(txtPhone.getText())) {
            Alerts.message("Verify!", "Input a valid phone.");
            txtPhone.requestFocus();
          } else {

            if (months.getSelectedItem() == "Months" && weeks.getSelectedItem() == "Weeks") {
              Alerts.message("Verify!", "Input a valid time.");
              weeks.requestFocus();
            } else {

              if (Format.verifyPrice(Double.parseDouble(txtValue.getText()))) {
                Alerts.message("Verify!", "Input a valid value.");
                txtValue.requestFocus();
              } else {

                GregorianCalendar date = new GregorianCalendar();
                DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

                if (months.getSelectedItem() == "Months") {
                  date.add(Calendar.DATE,
                      Integer.parseInt(String.valueOf(weeks.getSelectedItem())) * 7);
                } else if (weeks.getSelectedItem() == "Weeks") {
                  date.add(Calendar.MONTH,
                      Integer.parseInt(String.valueOf(months.getSelectedItem())));
                } else {
                  date.add(Calendar.DATE,
                      Integer.parseInt(String.valueOf(weeks.getSelectedItem())) * 7);
                  date.add(Calendar.MONTH,
                      Integer.parseInt(String.valueOf(months.getSelectedItem())));
                }

                String user = new HomeStore().getUserLogged();

                if (Database.searchDataUserInCashRegister(user)) {
                  Database.updateLoan(user, Double.parseDouble(txtValue.getText()));
                } else {
                  String[] data = {CASH_REGISTER, user, String.valueOf(0), String.valueOf(0.0),
                      String.valueOf(0), String.valueOf(0.0),
                      String.valueOf(txtValue.getText())};
                  Database.insertData(data);
                }

                String[] data = {LOANS, user, txtName.getText(), txtDocument.getText(),
                    txtReference.getText(), txtPhone.getText(), dateFormat.format(date.getTime()),
                    txtValue.getText()};

                Database.insertData(data);

                Database.store.setTotalLoans(Double.parseDouble(txtValue.getText()));
                Alerts.lendSuccessfully(dateFormat.format(date.getTime()), Double.parseDouble(
                    txtValue.getText()));
                Database.store.setTotalLoans(0.0);

                txtName.setText("");
                txtReference.setText("");
                txtDocument.setText("");
                txtPhone.setText("");
                txtValue.setText("0");
                txtName.requestFocus();
                months.setSelectedItem("Months");
                weeks.setSelectedItem("Weeks");
              }
            }
          }
        }
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