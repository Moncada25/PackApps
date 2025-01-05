package com.bookverse.packapps.automation.utils;

import lombok.Getter;
import lombok.Setter;
import net.datafaker.Faker;
import com.bookverse.packapps.automation.models.BookverseUser;

public final class GeneralUtils {

  @Getter @Setter
  private static BookverseUser user;

  public static BookverseUser getRegisterUser() {

    Faker faker = new Faker();

    return new BookverseUser(
        faker.name().firstName(),
        faker.name().lastName(),
        faker.phoneNumber().cellPhone(),
        "Otro",
        faker.address().fullAddress(),
        faker.internet().username(),
        faker.random().hex(),
        faker.internet().emailAddress(),
        "Masculino",
        ""
    );
  }

  private GeneralUtils() {
    throw new IllegalStateException("Utility class");
  }
}
