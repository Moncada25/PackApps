package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.AppConfig.BIG;
import static com.bookverse.development.packapps.core.AppConfig.HAND;
import static com.bookverse.development.packapps.core.AppConfig.MAIN_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.TEXT_COLOR;
import static com.bookverse.development.packapps.core.AppConfig.getBorder;
import static com.bookverse.development.packapps.core.AppConfig.inputNumber;

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

public class Stack extends JDialog implements MouseListener, ActionListener {

  private JLabel[] actions = new JLabel[8];
  private int i = 0, y = 400, x = 50, countPairs = 0, con = 0;
  private double add = 0, addTotal = 0, sum = 0;
  private String result = "";
  private Resources resources = new Resources();
  private JLabel tittle, message;
  private JButton[] elementsStack = new JButton[50];

  public Stack(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
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

    int c = 0;

    for (int j = 0; j < elementsStack.length; j++) {
      elementsStack[j] = resources.getButton("", null, this, this);
      elementsStack[j].setBounds(x, y, 80, 40);
      elementsStack[j].setForeground(MAIN_COLOR);
      elementsStack[j].setVisible(false);
      y -= 40;

      c++;

      if (c == 10) {
        x += 100;
        y = 400;

        c = 0;
      }
    }

    setPanel();

    tittle = resources.getLabel("", MAIN_COLOR, this, BIG);
    tittle.setBounds(550, 70, 600, 200);

    message = resources.getLabel("", MAIN_COLOR, this, BIG);
    message.setBounds(620, 480, 200, 85);
  }

  private void btnPushAP() {

    tittle.setText("");

    if (elementsStack.length > i) {

      do {
        int num = Integer.parseInt(inputNumber("Enter a numeric data", 6));

        push(num);
      } while (Alerts.requestResponse("Do you want to enter more data?", "Enter data") && elementsStack.length > i);
      tittle.setText("");
    } else {
      Alerts.message("Message", "The stack is full");
    }
  }

  private void btnPopAP() {

    tittle.setText("");

    if (i > 0) {
      elementsStack[i - 1].setVisible(false);

      tittle.setText("<html><strong>Unstacked: " + pop(elementsStack, i) + "</strong></html>");

      if (elementsStack[i - 1].getBackground() == TEXT_COLOR) {
        con--;
        sum -= Double.parseDouble((elementsStack[i - 1].getText()));
        elementsStack[i - 1].setBackground(getBackground());
      }

      y += 40;
      i--;
    } else {
      Alerts.message("Message", "The stack is empty");
      reset();
    }
  }

  private void btnPeekAP() {

    if (i > 0) {
      tittle.setText("<html><strong>Next data: " + peek() + "</strong></html>");
    } else {
      Alerts.message("Message", "The stack is empty");
    }
  }

  private void reset() {
    i = 0;
    y = 400;
    x = 50;
    con = 0;
    sum = 0;
    add = 0;
    addTotal = 0;
  }

  private void btnClean() {

    if (i > 0) {

      for (int j = 0; j < i; j++) {
        elementsStack[j].setText("");
        elementsStack[j].setVisible(false);
        elementsStack[j].setBackground(getBackground());
      }

      reset();

      tittle.setText("<html><strong>Stack empty</strong></html>");

    } else {
      Alerts.message("Message", "The stack is empty");
      reset();
    }
  }

  private int btnCount() {

    if (i == 0) {
      Alerts.message("Message", "The stack is empty");
    } else {
      tittle.setText("<html><strong>There are " + i + " items in the stack</strong></html>");
    }
    return i;
  }

  private double btnAdd() {

    if (i == 0) {
      Alerts.message("Message", "The stack is empty");
    } else {

      for (int n = 0; n < i; n++) {

        add += Double.parseDouble(elementsStack[n].getText());
      }
      tittle.setText(
          "<html>" + Format.style() + "<strong>Stack items add up: " + add + "</strong></html>");

      addTotal = add;
      add = 0;
    }
    return addTotal;
  }

  private void average() {

    if (i == 0) {
      Alerts.message("Message", "The stack is empty");
    } else {
      tittle.setText(
          "<html><strong>Average: " + String.format("%.2f", btnAdd() / btnCount())
              + "</strong></html>");
    }
  }

  private void btnPairs() {

    for (int n = 0; n < i; n++) {

      if (Integer.parseInt(elementsStack[n].getText()) % 2 == 0) {

        if (countPairs % 10 == 0) {
          result += "\n";
        }

        result += "[" + elementsStack[n].getText() + "]";
        countPairs++;
      }
    }

    if (i == 0) {
      Alerts.message("Message", "The stack is empty");
    } else if (countPairs == 0) {
      tittle.setText("<html>" + Format.style() + "<strong>There is not pairs in the stack</strong></html>");
    } else {
      Alerts.message("Result", "Numbers pairs in the stack " + result);
    }

    countPairs = 0;
    result = "";
  }

  private Object pop(JButton[] p1, int i) {
    return p1[i - 1].getText();
  }

  private Object peek() {
    return elementsStack[i - 1].getText();
  }

  private void push(int data) {

    elementsStack[i].setVisible(true);
    elementsStack[i].setText(String.valueOf(data));
    tittle.setText("<html><strong>Se apiló ? " + elementsStack[i].getText() + "</strong></html>");
    i++;
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getSource() == actions[0]) {
      btnPushAP();
    } else if (e.getSource() == actions[1]) {
      btnPopAP();
    } else if (e.getSource() == actions[2]) {
      btnPeekAP();
    } else if (e.getSource() == actions[3]) {
      btnCount();
    } else if (e.getSource() == actions[4]) {
      btnAdd();
    } else if (e.getSource() == actions[5]) {
      average();
    } else if (e.getSource() == actions[6]) {
      btnPairs();
    } else if (e.getSource() == actions[7]) {
      btnClean();
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

      if (e.getSource() == elementsStack[j]) {

        if (elementsStack[j].getBackground() == TEXT_COLOR) {
          elementsStack[j].setBackground(getBackground());
          con--;
          sum -= Double.parseDouble(elementsStack[j].getText());
        } else {
          elementsStack[j].setBackground(TEXT_COLOR);
          con++;
          sum += Double.parseDouble(elementsStack[j].getText());
        }

        if (con > 0) {
          tittle.setText(
              "<html><strong>Datos seleccionados ? " + con + "<br>" + "Suman ? " + (int) sum
                  + "<br>" + "Promedian ? " + String.format("%.2f", sum / con)
                  + "</strong></html>");
        } else {
          tittle.setText("<html><strong>Ningun dato seleccionado</strong></html>");
        }
      }
    }
  }
}