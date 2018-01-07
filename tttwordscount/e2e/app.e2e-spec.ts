import { TttwordscountPage } from './app.po';

describe('tttwordscount App', function() {
  let page: TttwordscountPage;

  beforeEach(() => {
    page = new TttwordscountPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
