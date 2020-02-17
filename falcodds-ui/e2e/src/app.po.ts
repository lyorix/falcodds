import { browser, by, element } from 'protractor';

export class AppPage {

  navigateTo(): Promise<unknown> {
    return browser.get(browser.baseUrl) as Promise<unknown>;
  }

  getHeaderTitleTest(): Promise<string> {
    return element(by.css('.header__title')).getText() as Promise<string>;
  }

  getEmpireFormSubmitButtonEnabled(): Promise<boolean> {
    return element(by.css('.empire-form__submit-btn')).isEnabled() as Promise<boolean>;
  }
}
