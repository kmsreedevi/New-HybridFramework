package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinHomePage extends BasePageWeb {
	private Logger log = Logger.getLogger(LinkedinHomePage.class);

	// create a construtor
	public LinkedinHomePage() {
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "a.nav__logo-link")
	private WebElement linkedinLogo;

	@FindBy(css = "a.nav__button-secondary")
	private WebElement signinLink;

	@FindBy(css = "h1[data-test-id='hero__headline']")
	private WebElement linkedinHomePgHeadTxt;

	@FindBy(xpath = "//h1[@class='header__content__heading '][contains(.,'Sign in')]")
	private WebElement signInHeaderText;

	@FindBy(id = "username")
	private WebElement emailEditbox;

	@FindBy(name = "session_password")
	private WebElement passwordEditbox;

	@FindBy(xpath = "//button[text()='Sign in']")
	private WebElement signInBtn;

	String linkedinHomePgTitle = "LinkedIn: Log In or Sign Up";

	String linkedinLoginPgTitle = "LinkedIn Login, Sign in | LinkedIn";

	public void verifyLinkedinHomepgTitle() {
		log.debug("verify the linkedin home page title:" + linkedinHomePgTitle);
		wait.until(ExpectedConditions.titleIs(linkedinHomePgTitle));
		Assert.assertEquals(driver.getTitle(), linkedinHomePgTitle);
	}

	public void verifyLinkedinpgHeaderText() throws InterruptedException {
		log.debug("verify the linkedin home page header text element:");
		Assert.assertTrue(isDisplayedElement(linkedinHomePgHeadTxt));
	}

	public void verifyLinkedinLogo() throws InterruptedException {
		log.debug("verify the linkedin logo element:");
		Assert.assertTrue(isDisplayedElement(linkedinLogo));

	}

	public void clickSigninLink() throws InterruptedException {
		log.debug("click on the Linkedin HomePage Sign link");
		click(signinLink);
	}

	public void verifyLinkedinSignpgTitle() {
		log.debug("verify the linkedin home page title:" + linkedinLoginPgTitle);
		wait.until(ExpectedConditions.titleIs(linkedinLoginPgTitle));
		Assert.assertEquals(driver.getTitle(), linkedinLoginPgTitle);
	}

	public void verifyLinkedinSignpgHeaderText() throws InterruptedException {
		log.debug("verify the linkedin sign page header text element:");
		Assert.assertTrue(isDisplayedElement(signInHeaderText));
	}

	public void clickSigninButton() throws InterruptedException {
		log.debug("click on the Signin page Signin Button");
		click(signInBtn);
	}

	public LinkedinLoggedinPage doLogin(String uname, String pwd) throws InterruptedException {
		log.debug("Started executing the doLogin()");
		log.debug("clear the content in the email edit box");
		clearText(emailEditbox);
		log.debug("type the email:" + uname + "in emailEditbox");
		sendKey(emailEditbox, uname);
		log.debug("type the pwd:" + uname + "in passwordEditbox");
		sendKey(passwordEditbox, pwd);
		clickSigninButton();
		return new LinkedinLoggedinPage();

	}

}
