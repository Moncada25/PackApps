package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.ui.Effects;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProfessionalCardView extends JDialog implements MouseListener {

  private JLabel imageCard;

  public ProfessionalCardView(JFrame parent, boolean modal) {
    super(parent, modal);

    setIconImage(new ImageIcon(Resources.getImage("about.png")).getImage());

    ((JPanel) getContentPane()).setOpaque(false);
    ImageIcon image = new ImageIcon(Resources.getImage("business.jpg"));
    imageCard = new JLabel();
    imageCard.setIcon(image);
    imageCard.setSize(940, 352);
    imageCard.addMouseListener(this);
    add(imageCard, BorderLayout.CENTER);
  }

  public void start(JFrame parent) {
    setSize(945, 377);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Developed by");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == imageCard) {
      Effects.fadeOut(this);
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }
}