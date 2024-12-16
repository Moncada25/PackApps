package com.bookverse.development.packapps.apps.numbers.primenumber;

import com.bookverse.development.packapps.utils.ui.Alerts;

public class PrimeNumberService {

  public void verifyPrimeNumber(int num) {
    StringBuilder divisors = new StringBuilder();
    int contD = 0;
    int cont = 0;

    for (int i = 1; i <= num; i++) {
      if (num % i == 0) {
        contD++;
        cont++;
        divisors.append("[").append(i).append("]");
        if (cont == 10) {
          divisors.append("\n");
          cont = 0;
        }
      }
    }

    String message = contD == 2 ?
        num + " is a prime number, Unique divisors: " + divisors :
        num + " it's not a prime number, Divisors: " + divisors;
    Alerts.message("Number of divisors: " + contD, message);
  }

  public void searchPrimeNumbers(int since, int until) {
    if (since > until) {
      Alerts.message("Verify!", "Illogical range");
      return;
    }

    StringBuilder divisors = new StringBuilder();
    int sum = 0;
    int cont = 0;
    int quantity = 0;

    for (int i = since; i <= until; i++) {
      int contD = 0;
      for (int j = 1; j <= i; j++) {
        if (i % j == 0) {
          contD++;
        }
      }

      if (contD == 2) {
        divisors.append("[").append(i).append("]");
        sum += i;
        cont++;
        quantity++;
        if (cont == 30) {
          divisors.append("\n");
          cont = 0;
        }
      }
    }
    Alerts.message("Quantity: " + quantity + " - Sum: " + sum, divisors.toString());
  }
}
