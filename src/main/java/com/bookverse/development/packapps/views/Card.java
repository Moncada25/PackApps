package com.bookverse.development.packapps.views;

import com.bookverse.development.packapps.core.Resources;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Card extends JDialog implements MouseListener {

    private Resources img = new Resources();
    private JLabel card = new JLabel();

    public Card(JFrame parent, boolean modal) {
        super(parent, modal);

        setIconImage(new ImageIcon(img.getImage("about.png")).getImage());

        ((JPanel) getContentPane()).setOpaque(false);
        ImageIcon imagen = new ImageIcon(img.getImage("carta.png"));
        card = new JLabel();
        card.setIcon(imagen);
        card.setSize(517, 244);
        card.addMouseListener(this);
        add(card, BorderLayout.CENTER);
    }

    public Card() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == card) {
            img.core.fadeOut(this);
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