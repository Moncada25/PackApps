package com.bookverse.development.packapps.views;

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
import com.bookverse.development.packapps.services.QueueService;
import com.bookverse.development.packapps.utils.constants.Styles;
import com.bookverse.development.packapps.utils.ui.Resources;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.ui.Effects;

public class QueueView extends JDialog implements ActionListener, MouseListener {

  private JLabel[] queueActions = new JLabel[8];
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
      queueActions[i].addMouseListener(this);
      panel.add(queueActions[i]);
    });

    panel.setBounds(0, 480, 480, 100);
    add(panel);
  }

  private void createComponents() {

    setLayout(null);

    for (int j = 0; j < queue.length; j++) {

      queue[j] = Resources.getButton("", null, this, this);
      queue[j].setBounds(QueueService.x, QueueService.y, 80, 40);
      queue[j].setForeground(Styles.MAIN_COLOR);
      queue[j].setVisible(false);

      if (sw) {

        if (QueueService.x == 770) {
          sw = false;
          QueueService.y = QueueService.y - 60;
          QueueService.x -= 80;
        }

       QueueService.x += 80;

      } else {

        if (QueueService.x == 50) {
          sw = true;
          QueueService.y = QueueService.y - 60;
          QueueService.x += 80;
        }

        QueueService.x -= 80;
      }
    }

    getPanel();

    bonus = new JLabel();
    bonus.setBounds(0, 380, 80, 80);
    bonus.setIcon(new ImageIcon(Resources.getImage("puerta.png")));
    bonus.addMouseListener(this);
    bonus.setVisible(false);
    add(bonus);

    title = Resources.getLabel("", Styles.MAIN_COLOR, this, Styles.BIG);
    title.setBounds(50, 0, 900, 160);

    message = Resources.getLabel("", Styles.MAIN_COLOR, this, Styles.BIG);
    message.setBounds(620, 480, 200, 85);
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == queueActions[0]) {
      QueueService.clickPush(queue, title, bonus, this);
    } else if (e.getSource() == queueActions[1]) {
      QueueService.clickOnDecouple(queue, title, bonus, this);
    } else if (e.getSource() == queueActions[2]) {
      QueueService.clickOnPeek(queue, title);
    } else if (e.getSource() == queueActions[3]) {
      QueueService.clickOnCount(title);
    } else if (e.getSource() == queueActions[4]) {
      QueueService.clickOnAdd(queue, title);
    } else if (e.getSource() == queueActions[5]) {
      QueueService.clickOnAverage(queue, title);
    } else if (e.getSource() == queueActions[6]) {
      QueueService.clickOnPairs(queue, title);
    } else if (e.getSource() == queueActions[7]) {
      QueueService.clickOnClean(queue, title, bonus, this);
    } else if (e.getSource() == bonus) {
      Alerts.message("BONUS", "It's a joke! XD");
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

    if (e.getSource() == queueActions[0]) {
      queueActions[0].setCursor(Styles.HAND);
      message.setText("<html><strong>Push( )</strong></html>");
    } else if (e.getSource() == queueActions[1]) {
      queueActions[1].setCursor(Styles.HAND);
      message.setText("<html><strong>Pop( )</strong></html>");
    } else if (e.getSource() == queueActions[2]) {
      queueActions[2].setCursor(Styles.HAND);
      message.setText("<html><strong>Peek( )</strong></html>");
    } else if (e.getSource() == queueActions[3]) {
      queueActions[3].setCursor(Styles.HAND);
      message.setText("<html><strong>Count</strong></html>");
    } else if (e.getSource() == queueActions[4]) {
      queueActions[4].setCursor(Styles.HAND);
      message.setText("<html><strong>Sum</strong></html>");
    } else if (e.getSource() == queueActions[5]) {
      queueActions[5].setCursor(Styles.HAND);
      message.setText("<html><strong>Average</strong></html>");
    } else if (e.getSource() == queueActions[6]) {
      queueActions[6].setCursor(Styles.HAND);
      message.setText("<html><strong>Pairs numbers</strong></html>");
    } else if (e.getSource() == queueActions[7]) {
      queueActions[7].setCursor(Styles.HAND);
      message.setText("<html><strong>Clean</strong></html>");
    } else if (e.getSource() == bonus) {
      bonus.setCursor(Styles.HAND);
      message.setText("<html><strong>Bonus</strong></html>");
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {

    if (e.getSource() == queueActions[0]) {
      message.setText("");
    } else if (e.getSource() == queueActions[1]) {
      message.setText("");
    } else if (e.getSource() == queueActions[2]) {
      message.setText("");
    } else if (e.getSource() == queueActions[3]) {
      message.setText("");
    } else if (e.getSource() == queueActions[4]) {
      message.setText("");
    } else if (e.getSource() == queueActions[5]) {
      message.setText("");
    } else if (e.getSource() == queueActions[6]) {
      message.setText("");
    } else if (e.getSource() == queueActions[7]) {
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

    for (int j = 0; j < QueueService.i; j++) {

      if (e.getSource() == queue[j]) {

        if (queue[j].getBackground() == Styles.TEXT_COLOR) {
          queue[j].setBackground(getBackground());
          QueueService.selectedDate--;
          QueueService.sum -= Double.parseDouble(queue[j].getText());
        } else {
          queue[j].setBackground(Styles.TEXT_COLOR);
          QueueService.selectedDate++;
          QueueService.sum += Double.parseDouble(queue[j].getText());
        }

        if (QueueService.selectedDate > 0) {
          title.setText("<html><strong>Selected data → " + QueueService.selectedDate + "<br>" + "Sum → " + QueueService.sum
              + "<br>" + "Average → " + String.format("%.2f", QueueService.sum / QueueService.selectedDate)
              + "</strong></html>");
        } else {
          title.setText("<html><strong>Nothing selected</strong></html>");
        }
      }
    }
  }
}