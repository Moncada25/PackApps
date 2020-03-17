/*
@Author: Santiago Moncada
@Date: 23/01/19
@Release: 2.1 final
@Class: Main

@Refactor
@Date: 02/03/2020
*/

package com.bookverse.development.packapps.core;

import com.bookverse.development.packapps.utils.Alerts;
import com.bookverse.development.packapps.views.Index;

public class Start {

  public static void main(String[] args) {

    try {
      Index.main(args);
    } catch (Exception e) {
      e.printStackTrace();
      Alerts.message("Corrupt package", "Error - " + e.getMessage());
    }
  }
}