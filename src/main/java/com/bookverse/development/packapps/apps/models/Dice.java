package com.bookverse.development.packapps.apps.models;

public class Dice {

  private int value;

  public int throwDices() {
    value = (1 + (int) (Math.random() * 6));
    return value;
  }

  public int getValue() {
    return value;
  }
}