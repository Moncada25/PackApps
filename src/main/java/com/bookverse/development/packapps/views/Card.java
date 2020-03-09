package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.models.Resources;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Card extends JDialog implements MouseListener {

  private Resources resources = new Resources();
  private JLabel card = new JLabel();

  public Card(JFrame parent, boolean modal) {
    super(parent, modal);

    setIconImage(new ImageIcon(resources.getImage("about.png")).getImage());

    ((JPanel) getContentPane()).setOpaque(false);
    ImageIcon image = new ImageIcon(resources.getImage("business.png"));
    card = new JLabel();
    card.setIcon(image);
    card.setSize(1035, 505);
    card.addMouseListener(this);
    add(card, BorderLayout.CENTER);
  }

  public void start(JFrame parent) {
    setSize(1040, 530);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Developed by");
    resources.core.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == card) {
      resources.core.fadeOut(this);
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