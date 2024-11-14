package com.bookverse.development.packapps.apps.dices;

import lombok.Getter;
import com.bookverse.development.packapps.utils.GeneralUtils;

@Getter
public class Dice {

  private int value;

  public int throwDices() {
    value = GeneralUtils.getIntRandom(1, 6);
    return value;
  }
}