package com.bookverse.packapps.apps.queues;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import com.bookverse.packapps.utils.constants.Styles;
import com.bookverse.packapps.utils.ui.Resources;
import com.bookverse.packapps.utils.ui.Effects;
import com.bookverse.packapps.apps.structures.StructuresViewModel;
import com.bookverse.packapps.utils.ui.factory.Label;
import com.bookverse.packapps.utils.ui.factory.Button;

public class QueuesView extends JDialog implements MouseListener {

  private transient QueuesService service = new QueuesService();
  private transient QueuesViewModel model = null;
  private transient StructuresViewModel structures = new StructuresViewModel();
  private boolean sw = true;

  public QueuesView(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JDialog parent) {
    setBounds(0, 0, 900, 600);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Queue");
    Effects.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void createComponents() {

    setLayout(null);

    JButton[] queue = new JButton[50];
    int x = 50;
    int y = 400;

    for (int j = 0; j < queue.length; j++) {

      queue[j] = new Button().setText("").build();
      queue[j].setBounds(x, y, 80, 40);
      queue[j].setForeground(Styles.MAIN_COLOR);
      queue[j].setVisible(false);
      int finalJ = j;
      queue[j].addActionListener(e -> service.clickOnQueue(finalJ, model));
      add(queue[j]);

      if (sw) {

        if (x == 770) {
          sw = false;
          y -= 60;
          x -= 80;
        }

        x += 80;

      } else {

        if (x == 50) {
          sw = true;
          y -= 60;
          x += 80;
        }

        x -= 80;
      }
    }

    structures.getFooter(this, this, "Queue Actions");

    JLabel bonus = new JLabel();
    bonus.setBounds(0, 380, 80, 80);
    bonus.setIcon(new ImageIcon(Resources.getImage("puerta.png")));
    bonus.addMouseListener(this);
    bonus.setVisible(false);
    add(bonus);

    JLabel queueTitle = new Label().setText("")
        .setColor(Styles.TEXT_COLOR)
        .setFont(Styles.MEDIUM)
        .build();
    queueTitle.setBounds(50, 0, 900, 160);
    add(queueTitle, BorderLayout.NORTH);

    model = new QueuesViewModel(queueTitle, queue, this);
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == structures.getStructureActions()[0]) {
      service.clickPush(model);
    } else if (e.getSource() == structures.getStructureActions()[1]) {
      service.clickOnDecouple(model);
    } else if (e.getSource() == structures.getStructureActions()[2]) {
      service.clickOnPeek(model);
    } else if (e.getSource() == structures.getStructureActions()[3]) {
      service.clickOnCount(model);
    } else if (e.getSource() == structures.getStructureActions()[4]) {
      service.clickOnAdd(model);
    } else if (e.getSource() == structures.getStructureActions()[5]) {
      service.clickOnAverage(model);
    } else if (e.getSource() == structures.getStructureActions()[6]) {
      service.clickOnPairs(model);
    } else if (e.getSource() == structures.getStructureActions()[7]) {
      service.clickOnClean(model);
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    structures.handleMouseEvent(e);
  }

  @Override
  public void mouseExited(MouseEvent e) {

    for (JLabel queueAction : structures.getStructureActions()) {
      if (e.getSource() == queueAction) {
        structures.getMessage().setText("Queue Actions");
      }
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }
}