package com.bookverse.development.packapps.apps.views;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.stream.IntStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.bookverse.development.packapps.apps.utils.ui.Resources;
import com.bookverse.development.packapps.apps.utils.ui.Alerts;
import com.bookverse.development.packapps.apps.utils.ui.Effects;

import static com.bookverse.development.packapps.apps.services.QueueService.x;
import static com.bookverse.development.packapps.apps.services.QueueService.y;
import static com.bookverse.development.packapps.apps.services.QueueService.sum;
import static com.bookverse.development.packapps.apps.services.QueueService.i;
import static com.bookverse.development.packapps.apps.services.QueueService.selectedDate;
import static com.bookverse.development.packapps.apps.utils.ui.Resources.getBorder;
import static com.bookverse.development.packapps.apps.services.QueueService.clickOnAdd;
import static com.bookverse.development.packapps.apps.services.QueueService.clickOnAverage;
import static com.bookverse.development.packapps.apps.services.QueueService.clickOnClean;
import static com.bookverse.development.packapps.apps.services.QueueService.clickOnCount;
import static com.bookverse.development.packapps.apps.services.QueueService.clickOnDecouple;
import static com.bookverse.development.packapps.apps.services.QueueService.clickOnPairs;
import static com.bookverse.development.packapps.apps.services.QueueService.clickOnPeek;
import static com.bookverse.development.packapps.apps.services.QueueService.clickPush;

import static com.bookverse.development.packapps.apps.utils.constants.Styles.BIG;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.HAND;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.MAIN_COLOR;
import static com.bookverse.development.packapps.apps.utils.constants.Styles.TEXT_COLOR;

public class QueueView extends JDialog implements ActionListener, MouseListener {

  private JLabel[] actions = new JLabel[8];
  private boolean sw = true;
  
  private JLabel title, message, bonus;
  private JButton[] queue = new JButton[50];

  public QueueView(JDialog parent, boolean modal) {
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

  private void getPanel() {

    JPanel panel = new JPanel(new FlowLayout());

    String[] images = {"push.png", "pop.png", "peek.png", "contar.png", "sumar.png", "promedio.png",
        "pares.png",
        "vaciar.png"};

    panel.setBorder(getBorder("Select action"));

    IntStream.range(0, actions.length).forEach(i -> {
      actions[i] = new JLabel();
      actions[i].setIcon(new ImageIcon(Resources.getImage(images[i])));
      actions[i].addMouseListener(this);
      panel.add(actions[i]);
    });

    panel.setBounds(50, 480, 480, 100);
    add(panel);
  }

  private void createComponents() {

    setLayout(null);

    for (int j = 0; j < queue.length; j++) {

      queue[j] = Resources.getButton("", null, this, this);
      queue[j].setBounds(x, y, 80, 40);
      queue[j].setForeground(MAIN_COLOR);
      queue[j].setVisible(false);

      if (sw) {

        if (x == 770) {
          sw = false;
          y = y - 60;
          x -= 80;
        }

       x += 80;

      } else {

        if (x == 50) {
          sw = true;
          y = y - 60;
          x += 80;
        }

        x -= 80;
      }
    }

    getPanel();

    bonus = new JLabel();
    bonus.setBounds(0, 380, 80, 80);
    bonus.setIcon(new ImageIcon(Resources.getImage("puerta.png")));
    bonus.addMouseListener(this);
    bonus.setVisible(false);
    add(bonus);

    title = Resources.getLabel("", MAIN_COLOR, this, BIG);
    title.setBounds(50, 0, 900, 160);

    message = Resources.getLabel("", MAIN_COLOR, this, BIG);
    message.setBounds(620, 480, 200, 85);
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == actions[0]) {
      clickPush(queue, title, bonus, this);
    } else if (e.getSource() == actions[1]) {
      clickOnDecouple(queue, title, bonus, this);
    } else if (e.getSource() == actions[2]) {
      clickOnPeek(queue, title);
    } else if (e.getSource() == actions[3]) {
      clickOnCount(title);
    } else if (e.getSource() == actions[4]) {
      clickOnAdd(queue, title);
    } else if (e.getSource() == actions[5]) {
      clickOnAverage(queue, title);
    } else if (e.getSource() == actions[6]) {
      clickOnPairs(queue, title);
    } else if (e.getSource() == actions[7]) {
      clickOnClean(queue, title, bonus, this);
    } else if (e.getSource() == bonus) {
      Alerts.message("BONUS", "It's a joke! XD");
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

    if (e.getSource() == actions[0]) {
      actions[0].setCursor(HAND);
      message.setText("<html><strong>Push( )</strong></html>");
    } else if (e.getSource() == actions[1]) {
      actions[1].setCursor(HAND);
      message.setText("<html><strong>Pop( )</strong></html>");
    } else if (e.getSource() == actions[2]) {
      actions[2].setCursor(HAND);
      message.setText("<html><strong>Peek( )</strong></html>");
    } else if (e.getSource() == actions[3]) {
      actions[3].setCursor(HAND);
      message.setText("<html><strong>Count</strong></html>");
    } else if (e.getSource() == actions[4]) {
      actions[4].setCursor(HAND);
      message.setText("<html><strong>Sum</strong></html>");
    } else if (e.getSource() == actions[5]) {
      actions[5].setCursor(HAND);
      message.setText("<html><strong>Average</strong></html>");
    } else if (e.getSource() == actions[6]) {
      actions[6].setCursor(HAND);
      message.setText("<html><strong>Pairs numbers</strong></html>");
    } else if (e.getSource() == actions[7]) {
      actions[7].setCursor(HAND);
      message.setText("<html><strong>Clean</strong></html>");
    } else if (e.getSource() == bonus) {
      bonus.setCursor(HAND);
      message.setText("<html><strong>Bonus</strong></html>");
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {

    if (e.getSource() == actions[0]) {
      message.setText("");
    } else if (e.getSource() == actions[1]) {
      message.setText("");
    } else if (e.getSource() == actions[2]) {
      message.setText("");
    } else if (e.getSource() == actions[3]) {
      message.setText("");
    } else if (e.getSource() == actions[4]) {
      message.setText("");
    } else if (e.getSource() == actions[5]) {
      message.setText("");
    } else if (e.getSource() == actions[6]) {
      message.setText("");
    } else if (e.getSource() == actions[7]) {
      message.setText("");
    } else if (e.getSource() == bonus) {
      message.setText("");
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void actionPerformed(ActionEvent e) {

    for (int j = 0; j < i; j++) {

      if (e.getSource() == queue[j]) {

        if (queue[j].getBackground() == TEXT_COLOR) {
          queue[j].setBackground(getBackground());
          selectedDate--;
          sum -= Double.parseDouble(queue[j].getText());
        } else {
          queue[j].setBackground(TEXT_COLOR);
          selectedDate++;
          sum += Double.parseDouble(queue[j].getText());
        }

        if (selectedDate > 0) {
          title.setText("<html><strong>Selected data → " + selectedDate + "<br>" + "Sum → " + sum
              + "<br>" + "Average → " + String.format("%.2f", sum / selectedDate)
              + "</strong></html>");
        } else {
          title.setText("<html><strong>Nothing selected</strong></html>");
        }
      }
    }
  }
}