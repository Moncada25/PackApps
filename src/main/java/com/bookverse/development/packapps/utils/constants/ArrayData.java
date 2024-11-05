package com.bookverse.development.packapps.utils.constants;

import java.util.HashMap;
import java.util.Map;

public final class ArrayData {

  private ArrayData() {
    throw new IllegalStateException("Utility class");
  }

  public static String getCountryCode(String key) {

    Map<String, String> countryCodes = new HashMap<>();
    countryCodes.put("Argentina", "54");
    countryCodes.put("Bolivia", "591");
    countryCodes.put("Brasil", "55");
    countryCodes.put("Colombia", "57");
    countryCodes.put("Costa Rica", "506");
    countryCodes.put("Chile", "56");
    countryCodes.put("Ecuador", "593");
    countryCodes.put("España", "34");
    countryCodes.put("Estados Unidos", "1");
    countryCodes.put("Venezuela", "58");

    return countryCodes.get(key);
  }
}