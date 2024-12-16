package com.bookverse.development.packapps.apps.numbers.phi;

import lombok.Data;
import com.bookverse.development.packapps.utils.ui.Alerts;

@Data
public class PhiService {

  private String phi;

  public void generateFibonacci(int lim) {

    StringBuilder numbers = new StringBuilder();

    double num1 = 0;
    double num2 = 1;
    double f;

    for (int i = 0; i < lim; i++) {

      if (i % 10 == 0 && i != 0) {
        numbers.append("\n");
      }

      f = num1 + num2;
      num2 = num1;
      num1 = f;

      numbers.append("[").append(String.format("%.0f", f)).append("]");
    }

    if(num2 != 0)
      phi = String.format("%.10f", num1 / num2);

    Alerts.message("Result", numbers.toString());
  }

  public void getFibonacci(int n)  {

    double fi = (1 + Math.sqrt(5)) / 2;
    double number = (1 / Math.sqrt(5)) * (Math.pow(fi, n) - (Math.pow(-1 / fi, n)));

    Alerts.message(
        "Result", "N° " + n + " in the Fibonacci's series is " + String.format("%.0f", number)
    );
  }

  public void proportionAurea(double length) {

    double a = length / ((1 + Math.sqrt(5)) / 2);
    double b = length - a;

    Alerts.message(
        "Result",
        "Portion A: " + String.format("%.2f", a) + " | Portion B: " + String.format("%.2f", b)
    );
  }
}
