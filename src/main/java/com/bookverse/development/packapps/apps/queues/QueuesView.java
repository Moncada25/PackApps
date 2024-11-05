package com.bookverse.development.packapps.apps.queues;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.ui.Effects;
import com.bookverse.development.packapps.utils.ui.Structures;
import com.bookverse.development.packapps.utils.ui.factory.Label;
import com.bookverse.development.packapps.utils.ui.factory.Button;

public class QueuesView extends JDialog implements MouseListener {

  private transient QueuesService service = new QueuesService();
  private transient QueuesViewModel model = null;
  private transient Structures structures = new Structures();
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

    structures.getPanel(this, this);

    JLabel bonus = new JLabel();
    bonus.setBounds(0, 380, 80, 80);
    bonus.setIcon(new ImageIcon(Resources.getImage("puerta.png")));
    bonus.addMouseListener(this);
    bonus.setVisible(false);
    add(bonus);

    JLabel queueTitle = new Label().setText("").setColor(Styles.MAIN_COLOR).setFont(Styles.BIG)
        .build();
    queueTitle.setBounds(50, 0, 900, 160);
    add(queueTitle);

    model = new QueuesViewModel(bonus, queueTitle, queue, this);
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == structures.queueActions[0]) {
      service.clickPush(model);
    } else if (e.getSource() == structures.queueActions[1]) {
      service.clickOnDecouple(model);
    } else if (e.getSource() == structures.queueActions[2]) {
      service.clickOnPeek(model);
    } else if (e.getSource() == structures.queueActions[3]) {
      service.clickOnCount(model);
    } else if (e.getSource() == structures.queueActions[4]) {
      service.clickOnAdd(model);
    } else if (e.getSource() == structures.queueActions[5]) {
      service.clickOnAverage(model);
    } else if (e.getSource() == structures.queueActions[6]) {
      service.clickOnPairs(model);
    } else if (e.getSource() == structures.queueActions[7]) {
      service.clickOnClean(model);
    } else if (e.getSource() == model.getBonus()) {
      Alerts.message("BONUS", "It's a joke! XD");
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

    if (e.getSource() == structures.queueActions[0]) {
      structures.queueActions[0].setCursor(Styles.HAND);
      structures.message.setText("<html><strong>Push( )</strong></html>");
    } else if (e.getSource() == structures.queueActions[1]) {
      structures.queueActions[1].setCursor(Styles.HAND);
      structures.message.setText("<html><strong>Pop( )</strong></html>");
    } else if (e.getSource() == structures.queueActions[2]) {
      structures.queueActions[2].setCursor(Styles.HAND);
      structures.message.setText("<html><strong>Peek( )</strong></html>");
    } else if (e.getSource() == structures.queueActions[3]) {
      structures.queueActions[3].setCursor(Styles.HAND);
      structures.message.setText("<html><strong>Count</strong></html>");
    } else if (e.getSource() == structures.queueActions[4]) {
      structures.queueActions[4].setCursor(Styles.HAND);
      structures.message.setText("<html><strong>Sum</strong></html>");
    } else if (e.getSource() == structures.queueActions[5]) {
      structures.queueActions[5].setCursor(Styles.HAND);
      structures.message.setText("<html><strong>Average</strong></html>");
    } else if (e.getSource() == structures.queueActions[6]) {
      structures.queueActions[6].setCursor(Styles.HAND);
      structures.message.setText("<html><strong>Pairs numbers</strong></html>");
    } else if (e.getSource() == structures.queueActions[7]) {
      structures.queueActions[7].setCursor(Styles.HAND);
      structures.message.setText("<html><strong>Clean</strong></html>");
    } else if (e.getSource() == model.getBonus()) {
      model.getBonus().setCursor(Styles.HAND);
      structures.message.setText("<html><strong>Bonus</strong></html>");
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {

    for (JLabel queueAction : structures.queueActions) {
      if (e.getSource() == queueAction) {
        structures.message.setText("");
      }
    }

    if (e.getSource() == model.getBonus()) {
      structures.message.setText("");
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }
}