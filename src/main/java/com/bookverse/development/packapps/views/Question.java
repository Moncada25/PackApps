package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.Settings.BORDER_BLUE;
import static com.bookverse.development.packapps.core.Settings.MAIN_COLOR;
import static com.bookverse.development.packapps.core.Settings.MEDIUM;
import static com.bookverse.development.packapps.core.Settings.TEXT_COLOR;

import com.bookverse.development.packapps.core.Resources;
import com.bookverse.development.packapps.core.Settings;
import com.bookverse.development.packapps.utils.Alerts;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Question extends JDialog implements MouseListener {

  private JLabel btnYes, btnNo;
  private Resources resources = new Resources();
  private String question;

  public Question(JFrame parent, boolean modal) {
    super(parent, modal);
    this.question = "Do you want to be my girlfriend?";
    createComponents();
  }

  public void start(JFrame parent) {
    setSize(273, 100);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Do the question!");
    Settings.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private JPanel getPanel() {

    JPanel panel = new JPanel(new FlowLayout());

    btnYes = resources.getLabel("  Yes  ", TEXT_COLOR, panel, MEDIUM);
    btnYes.setBorder(BORDER_BLUE);
    btnYes.addMouseListener(this);

    btnNo = resources.getLabel("  No  ", TEXT_COLOR, panel, MEDIUM);
    btnNo.setBorder(BORDER_BLUE);
    btnNo.addMouseListener(this);

    return panel;
  }

  private void createComponents() {

    setIconImage(new ImageIcon(resources.getImage("question.png")).getImage());
    add(getPanel(), BorderLayout.SOUTH);

    JLabel lblQuestion = resources
        .getLabel("<html><strong><em><center>" + question + "</center></em></strong></html>",
            TEXT_COLOR, this,
            MEDIUM);

    add(lblQuestion, BorderLayout.NORTH);
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == btnYes) {
      Alerts.message("OMG", "I knew it!");
      Settings.fadeOut(this);
    } else if (e.getSource() == btnNo) {
      Alerts.message("OMG", "I knew it!");
      Settings.fadeOut(this);
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
    if (e.getSource() == btnNo) {
      btnNo.setText("  Yes  ");
      btnYes.setText("  No  ");
    } else if (e.getSource() == btnYes) {
      btnNo.setText("  No  ");
      btnYes.setText("  Yes  ");
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}