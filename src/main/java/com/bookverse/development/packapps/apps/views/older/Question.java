package com.bookverse.development.packapps.apps.views.older;

import static com.bookverse.development.packapps.utils.constants.Styles.BORDER_BLUE;
import static com.bookverse.development.packapps.utils.constants.Styles.MEDIUM;
import static com.bookverse.development.packapps.utils.constants.Styles.TEXT_COLOR;

import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.ui.Effects;
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
  
  private String question;

  public Question(JFrame parent, boolean modal) {
    super(parent, modal);
    this.question = "Do you want to be my girlfriend?";
    createComponents();
  }

  public void start(JFrame parent) {
    setSize(300, 100);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Do the question!");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private JPanel getPanel() {

    JPanel panel = new JPanel(new FlowLayout());

    btnYes = Resources.getLabel("  Yes  ", TEXT_COLOR, panel, MEDIUM);
    btnYes.setBorder(BORDER_BLUE);
    btnYes.addMouseListener(this);

    btnNo = Resources.getLabel("  No  ", TEXT_COLOR, panel, MEDIUM);
    btnNo.setBorder(BORDER_BLUE);
    btnNo.addMouseListener(this);

    return panel;
  }

  private void createComponents() {

    setIconImage(new ImageIcon(Resources.getImage("question.png")).getImage());
    add(getPanel(), BorderLayout.SOUTH);

    JLabel lblQuestion = Resources
        .getLabel("<html><strong><em><center>" + question + "</center></em></strong></html>",
            TEXT_COLOR, this,
            MEDIUM);

    add(lblQuestion, BorderLayout.NORTH);
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == btnYes || e.getSource() == btnNo) {
      Alerts.message("OMG", "I love you!");
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