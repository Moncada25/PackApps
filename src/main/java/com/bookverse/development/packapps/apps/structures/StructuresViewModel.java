package com.bookverse.development.packapps.apps.structures;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.stream.IntStream;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lombok.Data;
import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.ui.factory.Label;

@Data
public class StructuresViewModel {

  private JLabel message;
  private JLabel[] structureActions = new JLabel[8];

  public void getFooter(JDialog parent, MouseListener listener, String title) {

    JPanel actions = new JPanel(new FlowLayout());
    JPanel panel = new JPanel(new BorderLayout());
    message = new Label().setText(title)
        .setColor(Styles.MAIN_COLOR)
        .setFont(Styles.BIG)
        .build();

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

    IntStream.range(0, structureActions.length).forEach(i -> {
      structureActions[i] = new JLabel();
      structureActions[i].setIcon(new ImageIcon(Resources.getImage(images[i])));
      structureActions[i].addMouseListener(listener);
      actions.add(structureActions[i]);
    });

    panel.add(actions, BorderLayout.WEST);
    panel.add(message, BorderLayout.EAST);

    parent.setLayout(new BorderLayout());
    parent.add(panel, BorderLayout.SOUTH);
  }
  
  public void handleMouseEvent(MouseEvent e) {

    if (e.getSource() == structureActions[0]) {
      structureActions[0].setCursor(Styles.HAND);
      message.setText("Push()");
    } else if (e.getSource() == structureActions[1]) {
      structureActions[1].setCursor(Styles.HAND);
      message.setText("Pop()");
    } else if (e.getSource() == structureActions[2]) {
      structureActions[2].setCursor(Styles.HAND);
      message.setText("Peek()");
    } else if (e.getSource() == structureActions[3]) {
      structureActions[3].setCursor(Styles.HAND);
      message.setText("Count");
    } else if (e.getSource() == structureActions[4]) {
      structureActions[4].setCursor(Styles.HAND);
      message.setText("Sum");
    } else if (e.getSource() == structureActions[5]) {
      structureActions[5].setCursor(Styles.HAND);
      message.setText("Average");
    } else if (e.getSource() == structureActions[6]) {
      structureActions[6].setCursor(Styles.HAND);
      message.setText("Pairs numbers");
    } else if (e.getSource() == structureActions[7]) {
      structureActions[7].setCursor(Styles.HAND);
      message.setText("Clean");
    }
  }
}


