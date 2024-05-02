package com.bookverse.development.packapps.apps.models;

import lombok.Getter;

@Getter
public class Dice {

  private int value;

  public int throwDices() {
    value = (1 + (int) (Math.random() * 6));
    return value;
  }
}