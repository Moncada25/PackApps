package com.bookverse.development.packapps.automation.utils;

import com.bookverse.development.packapps.automation.models.Bookverse;
import com.bookverse.development.packapps.utils.constants.ArrayData;

public class SetUser {

  public static Bookverse toLogin(String user, String password, String book){

    return new Bookverse(
        ArrayData.getDataUser("Name"),
        ArrayData.getDataUser("LastName"),
        ArrayData.getDataUser("Phone"),
        ArrayData.getDataUser("Occupation"),
        ArrayData.getDataUser("Address"),
        user,
        password,
        ArrayData.getDataUser("Email"),
        ArrayData.getDataUser("Gender"),
        book
    );
  }

  public static Bookverse toRegister() {

    return new Bookverse(
        ArrayData.getDataUser("Name"),
        ArrayData.getDataUser("LastName"),
        ArrayData.getDataUser("Phone"),
        ArrayData.getDataUser("Occupation"),
        ArrayData.getDataUser("Address"),
        ArrayData.getDataUser("Username"),
        ArrayData.getDataUser("Password"),
        ArrayData.getDataUser("Email"),
        ArrayData.getDataUser("Gender"),
        "book"
    );
  }
}
