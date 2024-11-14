package com.bookverse.development.packapps.apps.puzzle;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Levels {
  EASY(4, 55, 3),
  MEDIUM(5, 50, 6),
  HARD(6, 45, 10);

  private final int size;
  private final int side;
  private final int minutes;
}
