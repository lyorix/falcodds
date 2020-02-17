import { AppPage } from './app.po';
import { browser, logging } from 'protractor';

describe('app', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  describe('given default page, when initialized', () => {
    beforeEach(() => {
      page.navigateTo();
    });

    it('should display header title', () => {
      expect(page.getHeaderTitleTest()).toEqual('Falc\'odds');
    });

    it('should have empire form submit button disabled', () => {
      expect(page.getEmpireFormSubmitButtonEnabled()).toBe(false);
    });
  });
});
