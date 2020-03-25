package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.AppConfig.BIG;
import static com.bookverse.development.packapps.core.AppConfig.HAND;
import static com.bookverse.development.packapps.core.AppConfig.MAIN_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.TEXT_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.getBorder;
import static com.bookverse.development.packapps.core.AppConfig.inputNumber;

import com.bookverse.development.packapps.core.AppConfig;
import com.bookverse.development.packapps.models.Resources;
import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.utils.Format;
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

public class Queue extends JDialog implements ActionListener, MouseListener {

  private JLabel[] actions = new JLabel[8];
  private int i = 0, x = 50, y = 400, countPairs = 0, selectedDate = 0;
  private double add = 0, totalAdd = 0, sum = 0;
  private String result = "";
  private boolean sw = true;
  private Resources resources = new Resources();
  private JLabel title, message, bonus;
  private JButton[] elementsQueue = new JButton[50];

  public Queue(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JDialog parent) {
    setBounds(0, 0, 900, 600);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle("Queue");
    AppConfig.fadeIn(this);
    parent.setVisible(false);
    setVisible(true);
  }

  private void setPanel() {

    JPanel panel = new JPanel(new FlowLayout());

    String[] images = {"push.png", "pop.png", "peek.png", "contar.png", "sumar.png", "promedio.png",
        "pares.png",
        "vaciar.png"};

    panel.setBorder(getBorder("Select Action"));

    IntStream.range(0, actions.length).forEach(i -> {
      actions[i] = new JLabel();
      actions[i].setIcon(new ImageIcon(resources.getImage(images[i])));
      actions[i].addMouseListener(this);
      panel.add(actions[i]);
    });

    panel.setBounds(50, 480, 480, 100);
    add(panel);
  }

  private void createComponents() {

    setLayout(null);

    for (int j = 0; j < elementsQueue.length; j++) {

      elementsQueue[j] = resources.getButton("", null, this, this);
      elementsQueue[j].setBounds(x, y, 80, 40);
      elementsQueue[j].setForeground(MAIN_COLOR);
      elementsQueue[j].setVisible(false);

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

    setPanel();

    bonus = new JLabel();
    bonus.setBounds(0, 380, 80, 80);
    bonus.setIcon(new ImageIcon(resources.getImage("puerta.png")));
    bonus.addMouseListener(this);
    bonus.setVisible(false);
    add(bonus);

    title = resources.getLabel("", MAIN_COLOR, this, BIG);
    title.setBounds(50, 0, 900, 160);

    message = resources.getLabel("", MAIN_COLOR, this, BIG);
    message.setBounds(620, 480, 200, 85);
  }

  private Object peek() {
    return elementsQueue[0].getText();
  }

  private void btnPeekAP() {

    if (i > 0) {
      title
          .setText(
              "<html>" + Format.style() + "<strong>Next data → " + peek() + "</strong></html>");
    } else {
      Alerts.message("Message", "The queue is empty");
    }
  }

  private void reset() {
    i = 0;
    x = 50;
    y = 400;
    add = 0;
    totalAdd = 0;
  }

  private void btnClean() {

    if (i > 0) {

      for (int j = 0; j < i; j++) {
        elementsQueue[j].setText("");
        elementsQueue[j].setVisible(false);
        elementsQueue[j].setBackground(getBackground());
      }

      bonus.setVisible(false);
      reset();

      title.setText("<html><strong>Queue empty</strong></html>");

    } else {
      Alerts.message("Message", "The queue is empty");
      reset();
    }
  }

  private void btnPairs() {

    for (int n = 0; n < i; n++) {

      if (Integer.parseInt(elementsQueue[n].getText()) % 2 == 0) {

        if (countPairs % 10 == 0) {
          result += "\n";
        }

        result += "[" + elementsQueue[n].getText() + "]";
        countPairs++;
      }
    }

    if (i == 0) {
      Alerts.message("Message", "The queue is empty");
    } else if (countPairs == 0) {
      title.setText(
          "<html>" + Format.style() + "<strong>There is not pairs in the queue</strong></html>");
    } else {
      title.setText("<html>" + Format.style() + "<strong>Number of pairs → " + countPairs
          + "</strong></html>");
      Alerts.message("Result", "Numbers pairs in the queue <br> " + result);
    }

    countPairs = 0;
    result = "";
  }

  private void btnAverage() {

    if (i == 0) {
      Alerts.message("Message", "The queue is empty");
    } else {
      title.setText("<html>" + Format.style() + "<strong>Average of the queue → " + String
          .format("%.2f", btnAdd() / btnCount()) + "</strong></html>");
    }
  }

  private double btnAdd() {

    if (i == 0) {
      Alerts.message("Message", "The queue is empty");
    } else {

      for (int n = 0; n < i; n++) {
        add += Double.parseDouble(elementsQueue[n].getText());
      }

      title.setText(
          "<html>" + Format.style() + "<strong>Queue items add up → " + add + "</strong></html>");

      totalAdd = add;
      add = 0;
    }
    return totalAdd;
  }

  private int btnCount() {

    if (i == 0) {
      Alerts.message("Message", "The queue is empty");
    } else {
      title.setText("<html><strong>There are " + i + " items in the queue</strong></html>");
    }
    return i;
  }

  private Object pop() {

    Object aux = elementsQueue[0].getText();

    i--;

    if (elementsQueue[0].getBackground() == TEXT_COLOR) {
      selectedDate--;
      sum -= Double.parseDouble((elementsQueue[0].getText()));
      elementsQueue[0].setBackground(getBackground());
    }

    elementsQueue[i].setVisible(false);

    if (i < 1) {
      bonus.setVisible(false);
    }

    for (int k = 0; k < i; k++) {
      elementsQueue[k].setText(elementsQueue[k + 1].getText());
      elementsQueue[k].setBackground(elementsQueue[k + 1].getBackground());
    }

    return aux;
  }

  private void btnDecoupleAP() {

    title.setText("");

    if (i > 0) {
      title.setText("<html><strong>Decouples → " + pop() + "</strong></html>");
    } else {
      Alerts.message("Message", "The queue is empty");
      reset();
    }
  }

  private void push(int data) {
    bonus.setVisible(true);
    elementsQueue[i].setVisible(true);
    elementsQueue[i].setBackground(getBackground());
    elementsQueue[i].setText(String.valueOf(data));
    title.setText("<html><strong>Is coupled → " + elementsQueue[i].getText() + "</strong></html>");
  }

  private void btnPushAP() {

    title.setText("");

    if (i < elementsQueue.length) {
      do {
        int num = Integer.parseInt(inputNumber("Enter a numeric data", 6));
        push(num);
        i++;

      } while (Alerts.requestResponse("Do you want to enter more data?", "Enter data")
          && i < elementsQueue.length);

      title.setText("");

    } else {
      Alerts.message("Message", "The queue is full");
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == actions[0]) {
      btnPushAP();
    } else if (e.getSource() == actions[1]) {
      btnDecoupleAP();
    } else if (e.getSource() == actions[2]) {
      btnPeekAP();
    } else if (e.getSource() == actions[3]) {
      btnCount();
    } else if (e.getSource() == actions[4]) {
      btnAdd();
    } else if (e.getSource() == actions[5]) {
      btnAverage();
    } else if (e.getSource() == actions[6]) {
      btnPairs();
    } else if (e.getSource() == actions[7]) {
      btnClean();
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

      if (e.getSource() == elementsQueue[j]) {

        if (elementsQueue[j].getBackground() == TEXT_COLOR) {
          elementsQueue[j].setBackground(getBackground());
          selectedDate--;
          sum -= Double.parseDouble(elementsQueue[j].getText());
        } else {
          elementsQueue[j].setBackground(TEXT_COLOR);
          selectedDate++;
          sum += Double.parseDouble(elementsQueue[j].getText());
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