package com.bookverse.development.packapps.automation.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class BookverseSearch {

  public static final Target SEARCH_RESULT = Target.the("Result find")
      .locatedBy("//p[contains(. , '{0}')]");
  public static final Target OPEN_BOOK = Target.the("Open book in modal")
      .locatedBy(
          "//tbody//td[contains(. , '{0}')]//following-sibling::td/a[@data-title = 'Abrir PDF']/i");
  public static final Target TITLE_MODAL = Target.the("Title of book in modal")
      .locatedBy("//h3[contains(@class, 'modal-title')]");
  public static final Target AUTHOR_BOOK = Target.the("Author of book")
      .locatedBy("//tbody//td[contains(. , '{0}')]//following-sibling::td[1]");

  private BookverseSearch() {
  }
}