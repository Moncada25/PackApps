package com.bookverse.development.packapps.apps.feedback;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.ui.Effects;

public class BusinessCardView extends JDialog {

  public BusinessCardView(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JFrame parent) {
    setSize(805, 389);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Developed by");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void createComponents() {

    setIconImage(new ImageIcon(Resources.getImage("about.png")).getImage());
    ((JPanel) getContentPane()).setOpaque(false);

    ImageIcon image = new ImageIcon(Resources.getImage("business.png"));
    JLabel imageCard = new JLabel();
    imageCard.setIcon(image);
    imageCard.setSize(800, 364);
    imageCard.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        Effects.fadeOut(BusinessCardView.this);
      }
    });
    add(imageCard, BorderLayout.CENTER);
  }
}