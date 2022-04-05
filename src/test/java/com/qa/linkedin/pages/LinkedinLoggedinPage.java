package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinLoggedinPage extends BasePageWeb {
	private Logger log = Logger.getLogger(LinkedinHomePage.class);

	public LinkedinLoggedinPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(@class,'feed-identity-module')]")
	private WebElement porfileRailCard;

	@FindBy(xpath = "//img[contains(@class,'global-nav__me-photo ghost-person ember-view')]")
	private WebElement porfilePicIcon;

	@FindBy(xpath = "//a[@class='global-nav__secondary-link mv1'][contains(.,'Sign Out')]")
	private WebElement SignOutlink;

	@FindBy(xpath = "//input [contains(@class,'search-global-type')]")
	private WebElement SearchEditBox;

	String linkedinPgTitle = "Feed | LinkedIn";

	public void verifyLinkedinLoggedInPgTitle() {
		log.debug("verify the linkedi page title:" + linkedinPgTitle);
		wait.until(ExpectedConditions.titleContains(linkedinPgTitle));
		Assert.assertTrue(driver.getTitle().contains(linkedinPgTitle));
	}

	public void verifyProfileRailCard() throws InterruptedException {
		log.debug("Verify the linkedin Profile rail card element");
		Assert.assertTrue(isDisplayedElement(porfileRailCard));

	}

	public void doLogOut() throws InterruptedException {
		log.debug("Start executing the doLogOut()......");
		wait.until(ExpectedConditions.elementToBeClickable(porfilePicIcon));
		wait.until(ExpectedConditions.visibilityOf(porfilePicIcon));
		log.debug("Click on profile image icon");
		click(porfilePicIcon);
		log.debug("wait for the sigout link");
		wait.until(ExpectedConditions.elementToBeClickable(SignOutlink));
		wait.until(ExpectedConditions.visibilityOf(SignOutlink));
		log.debug("Click on signout link");
		click(SignOutlink);

	}

	public SearchResutlsPage doPeopelSearch(String keyword) throws InterruptedException {
		log.debug("Started executing  the doPeopelSearch() for the keyword::::" + keyword);
		log.debug("clear the content in the search editbox");
		clearText(SearchEditBox);
		log.debug("type the search keyword:" + keyword + "in search editbox");
		sendKey(SearchEditBox, keyword);
		log.debug("Press the ENTER key on searchEditbox");
		SearchEditBox.sendKeys(Keys.ENTER);
		return new SearchResutlsPage();

	}

}
