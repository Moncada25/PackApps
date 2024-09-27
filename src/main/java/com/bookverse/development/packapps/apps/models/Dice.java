package com.bookverse.development.packapps.apps.models;

import lombok.Getter;
import com.bookverse.development.packapps.apps.utils.other.GeneralUtils;

@Getter
public class Dice {

  private int value;

  public int throwDices() {
    value = GeneralUtils.getIntRandom(1, 6);
    return value;
  }
}