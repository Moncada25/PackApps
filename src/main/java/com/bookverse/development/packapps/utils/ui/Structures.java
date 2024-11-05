package com.bookverse.development.packapps.utils.ui;

import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.utils.ui.factory.Label;
import java.awt.FlowLayout;
import java.awt.event.MouseListener;
import java.util.stream.IntStream;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Structures {

  public JLabel message;
  public JLabel[] queueActions = new JLabel[8];

  public void getPanel(JDialog parent, MouseListener listener) {

    JPanel panel = new JPanel(new FlowLayout());

    String[] images = {
        "push.png",
        "pop.png",
        "peek.png",
        "contar.png",
        "sumar.png",
        "promedio.png",
        "pares.png",
        "vaciar.png"
    };

    panel.setBorder(Resources.getBorder("Select action"));

    IntStream.range(0, queueActions.length).forEach(i -> {
      queueActions[i] = new JLabel();
      queueActions[i].setIcon(new ImageIcon(Resources.getImage(images[i])));
      queueActions[i].addMouseListener(listener);
      panel.add(queueActions[i]);
    });

    panel.setBounds(0, 480, 480, 100);
    parent.add(panel);

    message = new Label().setText("").setColor(Styles.MAIN_COLOR).setFont(Styles.BIG).build();
    message.setBounds(620, 480, 200, 85);
    parent.add(message);
  }
}
