package com.bookverse.development.packapps.apps.stacks;

import lombok.Data;
import com.bookverse.development.packapps.utils.Format;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.constants.Styles;

@Data
public class StackService {

  private int i = 0;
  private int y = 400;
  private int x = 50;
  private int countPairs = 0;
  private int con = 0;
  private double add = 0;
  private double addTotal = 0;
  private double sum = 0;
  private String result = "";

  public void clickOnStack(int j, StacksViewModel model) {

    if (model.getStack()[j].getBackground() == Styles.TEXT_COLOR) {
      model.getStack()[j].setBackground(model.getParent().getBackground());
      con--;
      sum -= Double.parseDouble(model.getStack()[j].getText());
    } else {
      model.getStack()[j].setBackground(Styles.TEXT_COLOR);
      con++;
      sum += Double.parseDouble(model.getStack()[j].getText());
    }

    if (con > 0) {
      model.getStackTitle().setText(
          "<html>Elements selected → " + con + "<br>" + "Sum up → " + (int) sum
              + "<br>" + "Average → " + String.format("%.2f", sum / con)
              + "</html>"
      );
    } else {
      model.getStackTitle().setText("<html>No elements selected</html>");
    }
  }

  public void clickOnPush(StacksViewModel model) {

    model.getStackTitle().setText("");

    if (model.getStack().length > i) {

      do {
        int num = Integer.parseInt(Alerts.inputNumber("Enter a numeric data", 6));

        push(num, model);
      } while (Alerts.requestResponse("Do you want to enter more data?", "Enter data") && model.getStack().length > i);
      model.getStackTitle().setText("");
    } else {
      Alerts.message("Message", "The stack is full");
    }
  }

  public void clickOnPop(StacksViewModel model) {

    model.getStackTitle().setText("");

    if (i > 0) {
      model.getStack()[i - 1].setVisible(false);

      model.getStackTitle().setText("<html>Unstacked → " + pop(model, i) + "</html>");

      if (model.getStack()[i - 1].getBackground() == Styles.TEXT_COLOR) {
        con--;
        sum -= Double.parseDouble((model.getStack()[i - 1].getText()));
        model.getStack()[i - 1].setBackground(model.getParent().getBackground());
      }

      y += 40;
      i--;
    } else {
      Alerts.message("Message", "The stack is empty");
      reset();
    }
  }

  public void clickOnPeek(StacksViewModel model) {

    if (i > 0) {
      model.getStackTitle().setText("<html>Next element → " + peek(model) + "</html>");
    } else {
      Alerts.message("Message", "The stack is empty");
    }
  }

  public void reset() {
    i = 0;
    y = 400;
    x = 50;
    con = 0;
    sum = 0;
    add = 0;
    addTotal = 0;
  }

  public void clickOnClean(StacksViewModel model) {

    if (i > 0) {

      for (int j = 0; j < i; j++) {
        model.getStack()[j].setText("");
        model.getStack()[j].setVisible(false);
        model.getStack()[j].setBackground(model.getParent().getBackground());
      }

      reset();

      model.getStackTitle().setText("<html>Stack empty</html>");

    } else {
      Alerts.message("Message", "The stack is empty");
      reset();
    }
  }

  public int clickOnCount(StacksViewModel model) {

    if (i == 0) {
      Alerts.message("Message", "The stack is empty");
    } else {
      model.getStackTitle().setText("<html>There are " + i + " items</html>");
    }
    return i;
  }

  public double clickOnAdd(StacksViewModel model) {

    if (i == 0) {
      Alerts.message("Message", "The stack is empty");
    } else {

      for (int n = 0; n < i; n++) {
        add += Double.parseDouble(model.getStack()[n].getText());
      }
      model.getStackTitle().setText(
          "<html>The items add up → " + add + "</html>"
      );

      addTotal = add;
      add = 0;
    }

    return addTotal;
  }

  public void average(StacksViewModel model) {

    if (i == 0) {
      Alerts.message("Message", "The stack is empty");
    } else {

      int count = clickOnCount(model);

      if (count != 0) {

        model.getStackTitle().setText(
            "<html>Average → " + String.format("%.2f", clickOnAdd(model) / count)
                + "</html>"
        );
      }
    }
  }

  public void clickOnPairs(StacksViewModel model) {

    StringBuilder resultBuilder = new StringBuilder();

    for (int n = 0; n < i; n++) {

      if (Integer.parseInt(model.getStack()[n].getText()) % 2 == 0) {

        if (countPairs % 10 == 0) {
          resultBuilder.append("\n");
        }
        result = resultBuilder.append("[").append(
            Integer.parseInt(model.getStack()[n].getText())).append("]"
        ).toString();
        countPairs++;
      }
    }

    if (i == 0) {
      Alerts.message("Message", "The stack is empty");
    } else if (countPairs == 0) {
      model.getStackTitle().setText(
          "<html>" + Format.style() + "There is not pairs</html>"
      );
    } else {
      Alerts.message("Result", "Numbers pairs in the stack " + result);
    }

    countPairs = 0;
    result = "";
  }

  private Object pop(StacksViewModel model, int i) {
    return model.getStack()[i - 1].getText();
  }

  private Object peek(StacksViewModel model) {
    return model.getStack()[i - 1].getText();
  }

  private void push(int data, StacksViewModel model) {

    model.getStack()[i].setVisible(true);
    model.getStack()[i].setText(String.valueOf(data));
    model.getStackTitle().setText(
        "<html>Stacked → " + model.getStack()[i].getText() + "</html>"
    );
    i++;
  }
}
