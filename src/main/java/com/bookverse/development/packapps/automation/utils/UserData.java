package com.bookverse.development.packapps.automation.utils;

import java.util.HashMap;
import java.util.Map;
import net.datafaker.Faker;
import com.bookverse.development.packapps.automation.models.BookverseUser;

public class UserData {

  public static BookverseUser toLogin(String user, String password, String book){

    return new BookverseUser(
        getDataUser("Name"),
        getDataUser("LastName"),
        getDataUser("Phone"),
        getDataUser("Occupation"),
        getDataUser("Address"),
        user,
        password,
        getDataUser("Email"),
        getDataUser("Gender"),
        book
    );
  }

  public static BookverseUser toRegister() {

    return new BookverseUser(
        getDataUser("Name"),
        getDataUser("LastName"),
        getDataUser("Phone"),
        getDataUser("Occupation"),
        getDataUser("Address"),
        getDataUser("Username"),
        getDataUser("Password"),
        getDataUser("Email"),
        getDataUser("Gender"),
        "book"
    );
  }

  private static String getDataUser(String key) {

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

  private UserData() {
    throw new IllegalStateException("Utility class");
  }
}
