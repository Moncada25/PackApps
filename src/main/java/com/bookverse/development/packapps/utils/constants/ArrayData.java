package com.bookverse.development.packapps.utils.constants;

import net.datafaker.Faker;
import java.util.HashMap;
import java.util.Map;

public final class ArrayData {

  private ArrayData() {
    throw new IllegalStateException("Utility class");
  }

  public static String getDataUser(String key) {

    Faker faker = new Faker();

    Map<String, String> dataNewUser = new HashMap<>();
    dataNewUser.put("Name", faker.name().firstName());
    dataNewUser.put("LastName", faker.name().lastName());
    dataNewUser.put("Phone", faker.phoneNumber().cellPhone());
    dataNewUser.put("Occupation", "Otro");
    dataNewUser.put("Address", faker.address().fullAddress());
    dataNewUser.put("Username", faker.internet().username());
    dataNewUser.put("Password", faker.random().hex());
    dataNewUser.put("Email", faker.internet().emailAddress());
    dataNewUser.put("Gender", "Masculino");

    return dataNewUser.get(key);
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