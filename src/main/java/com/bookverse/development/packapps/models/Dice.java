package com.bookverse.development.packapps.models;

public class Dice {

  private int valor;

  public int throwDices() {
    valor = (1 + (int) (Math.random() * 6));
    return valor;
  }

  public int getValor() {
    return valor;
  }
}