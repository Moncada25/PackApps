package com.bookverse.packapps.utils.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import com.bookverse.packapps.utils.constants.Styles;

public class LoadingDialog extends JDialog {

  public LoadingDialog(Window parent) {
    super(parent, "Loading...", ModalityType.APPLICATION_MODAL);

    setUndecorated(true);
    setSize(300, 80);
    setLocationRelativeTo(parent);
    setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    setIconImage(new ImageIcon(Resources.getImage("cat.png")).getImage());

    JProgressBar progressBar = new JProgressBar();
    progressBar.setIndeterminate(true);
    progressBar.setString("Loading data...");
    progressBar.setStringPainted(true);
    progressBar.setFont(Styles.SMALL);

    JPanel panel = new JPanel(new BorderLayout(10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
    panel.add(progressBar, BorderLayout.CENTER);

    panel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.GRAY),
        BorderFactory.createEmptyBorder(15, 20, 15, 20)
    ));

    add(panel);
  }
}