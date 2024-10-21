package com.bookverse.development.packapps.apps.qr;

import java.awt.event.MouseAdapter;
import java.net.URI;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.ui.Effects;
import com.bookverse.development.packapps.utils.ui.KeyBindingsUtil;
import com.bookverse.development.packapps.utils.ui.factory.Label;
import com.bookverse.development.packapps.utils.ui.Resources;

public class QrView extends JDialog {

  private transient QrService service = new QrService();
  private transient QrViewModel model = new QrViewModel();

  public QrView(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
    KeyBindingsUtil.addCopyPasteKeyBindings(model.getText(), null, null);
  }

  public void start(JFrame parent) {
    setSize(500, 200);
    setLocationRelativeTo(parent);
    setTitle("QR");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void createComponents() {

    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    JTextArea text = new JTextArea();
    JScrollPane scroll = new JScrollPane(text);
    scroll.setBounds(30, 60, 210, 100);

    add(scroll, BorderLayout.CENTER);
    add(getPanel(), BorderLayout.SOUTH);

    model.setText(text);

    repaint();
  }

  private JPanel getPanel() {

    JPanel panel = new JPanel(new FlowLayout());

    JLabel readQR = new Label().setText("  READ QR  ")
        .setColor(Styles.TEXT_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    readQR.setBorder(Styles.BORDER_BLUE);
    panel.add(readQR);
    readQR.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String response = service.readQR(Resources.getFile(QrView.this));

        if ("Error".equals(response)) {
          Alerts.message("Message", "File not found");
          model.getText().setText("");
        } else {
          model.getText().setText(response);

          if (response.contains("www") || response.contains("http")) {

            try {
              Desktop.getDesktop().browse(URI.create(response));
            } catch (Exception ex) {
              Alerts.error(ex, "Opening URL");
            }
          }
        }
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        readQR.setCursor(Styles.HAND);
      }
    });

    JLabel generateQR = new Label().setText("  CREATE QR  ")
        .setColor(Styles.TEXT_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    generateQR.setBorder(Styles.BORDER_BLUE);
    panel.add(generateQR);
    generateQR.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (model.getText().getText().isEmpty()) {
          Alerts.message("Verify!", "Ingrese un texto para convertirlo en QR");
          model.getText().requestFocus();
        } else {
          String qr = service.createQR(model.getText().getText(), 400, 400);
          Alerts.message("Pass", "QR creado");
          service.showQRGenerated(qr, QrView.this);
        }
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        generateQR.setCursor(Styles.HAND);
      }
    });

    JLabel exit = new Label().setText("  RETURN  ")
        .setColor(Styles.MAIN_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    exit.setBorder(Styles.BORDER_RED);
    panel.add(exit);
    exit.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        Effects.fadeOut(QrView.this);
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        exit.setCursor(Styles.HAND);
      }
    });

    return panel;
  }
}
