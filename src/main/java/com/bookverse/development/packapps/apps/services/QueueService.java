package com.bookverse.development.packapps.apps.services;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import com.bookverse.development.packapps.utils.other.Format;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.constants.Styles;

public final class QueueService {

  public static int i = 0;
  public static int x = 50;
  public static int y = 400;
  public static int countPairs = 0;
  public static int selectedDate = 0;
  public static double add = 0;
  public static double totalAdd = 0;
  public static double sum = 0;
  public static String result = "";

  private static Object peek(JButton[] elements) {
    return elements[0].getText();
  }

  public static void clickOnPeek(JButton[] elements, JLabel title) {

    if (i > 0) {
      title.setText("<html>" + Format.style() + "<strong>Next data → " + peek(elements) + "</strong></html>");
    } else {
      Alerts.message("Message", "The queue is empty");
    }
  }

  private static void reset() {
    i = 0;
    x = 50;
    y = 400;
    add = 0;
    totalAdd = 0;
  }

  public static void clickOnClean(JButton[] elements, JLabel title, JLabel bonus, JDialog parent) {

    if (i > 0) {

      for (int j = 0; j < i; j++) {
        elements[j].setText("");
        elements[j].setVisible(false);
        elements[j].setBackground(parent.getBackground());
      }

      bonus.setVisible(false);
      reset();

      title.setText("<html><strong>Queue empty</strong></html>");

    } else {
      Alerts.message("Message", "The queue is empty");
      reset();
    }
  }

  public static void clickOnPairs(JButton[] elements, JLabel title) {

    StringBuilder resultBuilder = new StringBuilder();

    for (int n = 0; n < i; n++) {
      int elementValue = Integer.parseInt(elements[n].getText());
      if (elementValue % 2 == 0) {
        if (countPairs % 10 == 0) {
          resultBuilder.append("\n");
        }
        result = resultBuilder.append("[").append(elementValue).append("]").toString();
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

  public static void clickOnAverage(JButton[] elements, JLabel title) {

    if (i == 0) {
      Alerts.message("Message", "The queue is empty");
    } else {

      int count = clickOnCount(title);

      if(count != 0){

        title.setText("<html>" + Format.style() + "<strong>Average of the queue → " + String
            .format("%.2f", clickOnAdd(elements, title) / count) + "</strong></html>");
      }
    }
  }

  public static double clickOnAdd(JButton[] elements, JLabel title) {

    if (i == 0) {
      Alerts.message("Message", "The queue is empty");
    } else {

      for (int n = 0; n < i; n++) {
        add += Double.parseDouble(elements[n].getText());
      }

      title.setText(
          "<html>" + Format.style() + "<strong>Queue items add up → " + add + "</strong></html>");

      totalAdd = add;
      add = 0;
    }
    return totalAdd;
  }

  public static int clickOnCount(JLabel title) {

    if (i == 0) {
      Alerts.message("Message", "The queue is empty");
    } else {
      title.setText("<html><strong>There are " + i + " items in the queue</strong></html>");
    }

    return i;
  }

  private static Object pop(JButton[] elements, JLabel bonus, JDialog parent) {

    Object aux = elements[0].getText();

    i--;

    if (elements[0].getBackground() == Styles.TEXT_COLOR) {
      selectedDate--;
      sum -= Double.parseDouble((elements[0].getText()));
      elements[0].setBackground(parent.getBackground());
    }

    elements[i].setVisible(false);

    if (i < 1) {
      bonus.setVisible(false);
    }

    for (int k = 0; k < i; k++) {
      elements[k].setText(elements[k + 1].getText());
      elements[k].setBackground(elements[k + 1].getBackground());
    }

    return aux;
  }

  public static void clickOnDecouple(JButton[] elements, JLabel title, JLabel bonus, JDialog parent) {

    title.setText("");

    if (i > 0) {
      title.setText("<html><strong>Decouples → " + pop(elements, bonus, parent) + "</strong></html>");
    } else {
      Alerts.message("Message", "The queue is empty");
      reset();
    }
  }

  private static void push(int data, JButton[] elements, JLabel title, JLabel bonus, JDialog parent) {
    bonus.setVisible(true);
    elements[i].setVisible(true);
    elements[i].setBackground(parent.getBackground());
    elements[i].setText(String.valueOf(data));
    title.setText("<html><strong>Is coupled → " + elements[i].getText() + "</strong></html>");
  }

  public static void clickPush(JButton[] elements, JLabel title, JLabel bonus, JDialog parent) {

    title.setText("");

    if (i < elements.length) {
      do {
        int num = Integer.parseInt(Alerts.inputNumber("Enter a numeric data", 6));
        push(num, elements, title, bonus, parent);
        i++;

      } while (Alerts.requestResponse("Do you want to enter more data?", "Enter data")
          && i < elements.length);

      title.setText("");

    } else {
      Alerts.message("Message", "The queue is full");
    }
  }

  private QueueService() {
  }
}
