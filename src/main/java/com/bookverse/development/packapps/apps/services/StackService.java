package com.bookverse.development.packapps.apps.services;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import org.jetbrains.annotations.NotNull;
import com.bookverse.development.packapps.utils.other.Format;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.constants.Styles;

public final class StackService {

  public static int i = 0;
  public static int y = 400;
  public static int x = 50;
  public static int countPairs = 0;
  public static int con = 0;
  public static double add = 0;
  public static double addTotal = 0;
  public static double sum = 0;
  public static String result = "";

  public static void clickOnPush(JButton[] stack, JLabel title) {

    title.setText("");

    if (stack.length > i) {

      do {
        int num = Integer.parseInt(Alerts.inputNumber("Enter a numeric data", 6));

        push(num, stack, title);
      } while (Alerts.requestResponse("Do you want to enter more data?", "Enter data")
          && stack.length > i);
      title.setText("");
    } else {
      Alerts.message("Message", "The stack is full");
    }
  }

  public static void clickOnPop(JButton[] stack, JLabel title, JDialog parent) {

    title.setText("");

    if (i > 0) {
      stack[i - 1].setVisible(false);

      title.setText("<html><strong>Unstacked → " + pop(stack, i) + "</strong></html>");

      if (stack[i - 1].getBackground() == Styles.TEXT_COLOR) {
        con--;
        sum -= Double.parseDouble((stack[i - 1].getText()));
        stack[i - 1].setBackground(parent.getBackground());
      }

      y += 40;
      i--;
    } else {
      Alerts.message("Message", "The stack is empty");
      reset();
    }
  }

  public static void clickOnPeek(JButton[] stack, JLabel title) {

    if (i > 0) {
      title.setText("<html><strong>Next data → " + peek(stack) + "</strong></html>");
    } else {
      Alerts.message("Message", "The stack is empty");
    }
  }

  public static void reset() {
    i = 0;
    y = 400;
    x = 50;
    con = 0;
    sum = 0;
    add = 0;
    addTotal = 0;
  }

  public static void clickOnClean(JButton[] stack, JLabel title, JDialog parent) {

    if (i > 0) {

      for (int j = 0; j < i; j++) {
        stack[j].setText("");
        stack[j].setVisible(false);
        stack[j].setBackground(parent.getBackground());
      }

      reset();

      title.setText("<html><strong>Stack empty</strong></html>");

    } else {
      Alerts.message("Message", "The stack is empty");
      reset();
    }
  }

  public static int clickOnCount(JLabel title) {

    if (i == 0) {
      Alerts.message("Message", "The stack is empty");
    } else {
      title.setText("<html><strong>There are " + i + " items</strong></html>");
    }
    return i;
  }

  public static double clickOnAdd(JButton[] stack, JLabel title) {

    if (i == 0) {
      Alerts.message("Message", "The stack is empty");
    } else {

      for (int n = 0; n < i; n++) {

        add += Double.parseDouble(stack[n].getText());
      }
      title.setText(
          "<html>" + Format.style() + "<strong>The items add up → " + add + "</strong></html>");

      addTotal = add;
      add = 0;
    }
    return addTotal;
  }

  public static void average(JButton[] stack, JLabel title) {

    if (i == 0) {
      Alerts.message("Message", "The stack is empty");
    } else {

      int count = clickOnCount(title);

      if (count != 0) {

        title.setText(
            "<html><strong>Average → " + String.format("%.2f", clickOnAdd(stack, title) / count)
                + "</strong></html>");
      }
    }
  }

  public static void clickOnPairs(JButton[] stack, JLabel title) {

    StringBuilder resultBuilder = new StringBuilder();

    for (int n = 0; n < i; n++) {

      if (Integer.parseInt(stack[n].getText()) % 2 == 0) {

        if (countPairs % 10 == 0) {
          resultBuilder.append("\n");
        }
        result = resultBuilder.append("[").append(
            Integer.parseInt(stack[n].getText())).append("]"
        ).toString();
        countPairs++;
      }
    }

    if (i == 0) {
      Alerts.message("Message", "The stack is empty");
    } else if (countPairs == 0) {
      title.setText(
          "<html>" + Format.style() + "<strong>There is not pairs</strong></html>");
    } else {
      Alerts.message("Result", "Numbers pairs in the stack " + result);
    }

    countPairs = 0;
    result = "";
  }

  private static Object pop(@NotNull JButton[] p1, int i) {
    return p1[i - 1].getText();
  }

  private static Object peek(JButton[] stack) {
    return stack[i - 1].getText();
  }

  private static void push(int data, JButton[] stack, JLabel title) {

    stack[i].setVisible(true);
    stack[i].setText(String.valueOf(data));
    title.setText("<html><strong>Stacked → " + stack[i].getText() + "</strong></html>");
    i++;
  }

  private StackService() {
  }
}
