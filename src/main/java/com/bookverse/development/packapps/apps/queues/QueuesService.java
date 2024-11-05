package com.bookverse.development.packapps.apps.queues;

import com.bookverse.development.packapps.utils.other.Format;
import com.bookverse.development.packapps.utils.ui.Alerts;
import com.bookverse.development.packapps.utils.constants.Styles;

public class QueuesService {

  public int i = 0;
  public int countPairs = 0;
  public int dataSelected = 0;
  public double add = 0;
  public double totalAdd = 0;
  public double sum = 0;
  public String result = "";

  public void clickOnPeek(QueuesViewModel model) {

    if (i > 0) {
      model.getQueueTitle().setText(
          "<html>" + Format.style() + "<strong>Next data → " + peek(model) + "</strong></html>"
      );
    } else {
      Alerts.message("Message", "The queue is empty");
    }
  }
  
  public void clickOnQueue(int j, QueuesViewModel model) {

    if (model.getQueue()[j].getBackground() == Styles.TEXT_COLOR) {
      model.getQueue()[j].setBackground(model.getParent().getBackground());
      dataSelected--;
      sum -= Double.parseDouble(model.getQueue()[j].getText());
    } else {
      model.getQueue()[j].setBackground(Styles.TEXT_COLOR);
      dataSelected++;
      sum += Double.parseDouble(model.getQueue()[j].getText());
    }

    if (dataSelected > 0) {
      model.getQueueTitle().setText(
          "<html><strong>Selected data → " + dataSelected + "<br>" + "Sum → "
              + sum
              + "<br>" + "Average → " + String.format("%.2f",
              sum / dataSelected)
              + "</strong></html>");
    } else {
      model.getQueueTitle().setText("<html><strong>Nothing selected</strong></html>");
    }
  }

  public void clickOnClean(QueuesViewModel model) {

    if (i > 0) {

      for (int j = 0; j < i; j++) {
        model.getQueue()[j].setText("");
        model.getQueue()[j].setVisible(false);
        model.getQueue()[j].setBackground(model.getParent().getBackground());
      }

      model.getBonus().setVisible(false);
      reset();

      model.getQueueTitle().setText("<html><strong>Queue empty</strong></html>");

    } else {
      Alerts.message("Message", "The queue is empty");
      reset();
    }
  }

  public void clickOnPairs(QueuesViewModel model) {

    StringBuilder resultBuilder = new StringBuilder();

    for (int n = 0; n < i; n++) {
      int elementValue = Integer.parseInt(model.getQueue()[n].getText());
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
      model.getQueueTitle().setText(
          "<html>" + Format.style() + "<strong>There is not pairs in the queue</strong></html>"
      );
    } else {
      model.getQueueTitle().setText(
          "<html>" + Format.style() + "<strong>Number of pairs → " + countPairs + "</strong></html>"
      );

      Alerts.message("Result", "Numbers pairs in the queue <br> " + result);
    }

    countPairs = 0;
    result = "";
  }

  public void clickOnAverage(QueuesViewModel model) {

    if (i == 0) {
      Alerts.message("Message", "The queue is empty");
    } else {

      int count = clickOnCount(model);

      if(count != 0){

        model.getQueueTitle().setText(
            "<html>" + Format.style() + "<strong>Average of the queue → " +
                String.format("%.2f", clickOnAdd(model) / count) + "</strong></html>"
        );
      }
    }
  }

  public double clickOnAdd(QueuesViewModel model) {

    if (i == 0) {
      Alerts.message("Message", "The queue is empty");
    } else {

      for (int n = 0; n < i; n++) {
        add += Double.parseDouble(model.getQueue()[n].getText());
      }

      model.getQueueTitle().setText(
          "<html>" + Format.style() + "<strong>Queue items add up → " + add + "</strong></html>"
      );

      totalAdd = add;
      add = 0;
    }
    return totalAdd;
  }

  public int clickOnCount(QueuesViewModel model) {

    if (i == 0) {
      Alerts.message("Message", "The queue is empty");
    } else {
      model.getQueueTitle().setText(
          "<html><strong>There are " + i + " items in the queue</strong></html>"
      );
    }

    return i;
  }

  public void clickOnDecouple(QueuesViewModel model) {

    model.getQueueTitle().setText("");

    if (i > 0) {
      model.getQueueTitle().setText("<html><strong>Decouples → " + pop(model) + "</strong></html>");
    } else {
      Alerts.message("Message", "The queue is empty");
      reset();
    }
  }

  public void clickPush(QueuesViewModel model) {

    model.getQueueTitle().setText("");

    if (i < model.getQueue().length) {
      do {
        int num = Integer.parseInt(Alerts.inputNumber("Enter a numeric data", 6));
        push(num, model);
        i++;

      } while (Alerts.requestResponse("Do you want to enter more data?", "Enter data")
          && i < model.getQueue().length);

      model.getQueueTitle().setText("");

    } else {
      Alerts.message("Message", "The queue is full");
    }
  }

  private void reset() {
    i = 0;
    dataSelected = 0;
    countPairs = 0;
    sum = 0;
    add = 0;
    totalAdd = 0;
  }

  private void push(int data, QueuesViewModel model) {
    model.getBonus().setVisible(true);
    model.getQueue()[i].setVisible(true);
    model.getQueue()[i].setBackground(model.getParent().getBackground());
    model.getQueue()[i].setText(String.valueOf(data));
    model.getQueueTitle().setText(
        "<html><strong>Is coupled → " + model.getQueue()[i].getText() + "</strong></html>"
    );
  }

  private Object pop(QueuesViewModel model) {

    Object aux = model.getQueue()[0].getText();

    i--;

    if (model.getQueue()[0].getBackground() == Styles.TEXT_COLOR) {
      dataSelected--;
      sum -= Double.parseDouble((model.getQueue()[0].getText()));
      model.getQueue()[0].setBackground(model.getParent().getBackground());
    }

    model.getQueue()[i].setVisible(false);

    if (i < 1) {
      model.getBonus().setVisible(false);
    }

    for (int k = 0; k < i; k++) {
      model.getQueue()[k].setText(model.getQueue()[k + 1].getText());
      model.getQueue()[k].setBackground(model.getQueue()[k + 1].getBackground());
    }

    return aux;
  }

  private Object peek(QueuesViewModel model) {
    return model.getQueue()[0].getText();
  }
}
