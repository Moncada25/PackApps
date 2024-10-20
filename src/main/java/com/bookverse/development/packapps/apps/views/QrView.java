package com.bookverse.development.packapps.apps.views;

import com.bookverse.development.packapps.apps.utils.ui.KeyBindingsUtil;
import java.net.URI;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.bookverse.development.packapps.apps.utils.constants.Styles;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;
import com.bookverse.development.packapps.apps.utils.ui.Effects;
import com.bookverse.development.packapps.apps.utils.ui.Resources;
import com.bookverse.development.packapps.apps.services.QrService;

public class QrView extends JDialog implements MouseListener {

  private JLabel readQR;
  private JLabel generateQR;
  private JLabel exit;
  private JTextArea text;

  public QrView(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
    KeyBindingsUtil.addCopyPasteKeyBindings(text, null, null);
  }

  private void createComponents() {

    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    text = new JTextArea();
    JScrollPane scroll = new JScrollPane(text);
    scroll.setBounds(30, 60, 210, 100);

    add(scroll, BorderLayout.CENTER);
    add(getPanel(), BorderLayout.SOUTH);

    repaint();
  }

  public void start(JFrame parent) {
    setSize(500, 200);
    setLocationRelativeTo(parent);
    setTitle("QR");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private JPanel getPanel() {

    JPanel panel = new JPanel(new FlowLayout());

    readQR = Resources.getLabel("  READ QR  ", Styles.TEXT_COLOR, panel, Styles.MEDIUM);
    readQR.setBorder(Styles.BORDER_BLUE);
    readQR.addMouseListener(this);

    generateQR = Resources.getLabel("  CREATE QR  ", Styles.TEXT_COLOR, panel, Styles.MEDIUM);
    generateQR.setBorder(Styles.BORDER_BLUE);
    generateQR.addMouseListener(this);

    exit = Resources.getLabel("  RETURN  ", Styles.MAIN_COLOR, panel, Styles.MEDIUM);
    exit.setBorder(Styles.BORDER_RED);
    exit.addMouseListener(this);

    return panel;
  }

  private void showQR(String image) {

    JLabel code = new JLabel();
    code.setIcon(new ImageIcon(image));
    code.setSize(400, 400);

    JDialog result = new JDialog(this, true);

    result.setLayout(null);
    result.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    result.setSize(412, 427);
    result.setTitle("QR code generated");
    result.setResizable(false);
    result.setLocationRelativeTo(null);

    ((JPanel) result.getContentPane()).setOpaque(false);

    result.add(code);
    result.setVisible(true);
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == readQR) {

      String response = QrService.readQR(Resources.getFile(this));

      if ("Error".equals(response)) {
        Alerts.message("Message", "File not found");
        text.setText("");
      } else {
        text.setText(response);

        if (response.contains("www") || response.contains("http")) {

          try {
            Desktop.getDesktop().browse(URI.create(response));
          } catch (Exception ex) {
            Alerts.error(ex, "Opening URL");
          }
        }
      }

    } else if (e.getSource() == generateQR) {

      if (text.getText().isEmpty()) {
        Alerts.message("Verify!", "Ingrese un texto para convertirlo en QR");
        text.requestFocus();
      } else {
        String qr = QrService.createQR(text.getText(), 400, 400);
        Alerts.message("Pass", "QR creado");
        showQR(qr);
      }

    } else if (e.getSource() == exit) {
      Effects.fadeOut(this);
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {
    if (e.getSource() == readQR) {
      readQR.setCursor(Styles.HAND);
    } else if (e.getSource() == generateQR) {
      generateQR.setCursor(Styles.HAND);
    } else if (e.getSource() == exit) {
      exit.setCursor(Styles.HAND);
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
