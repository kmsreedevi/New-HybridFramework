package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class SearchResutlsPage extends BasePageWeb {
	private Logger log = Logger.getLogger(SearchResutlsPage.class);

	public SearchResutlsPage() {
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//a[@class='app-aware-link'][contains(.,'See all people results')]")
	private WebElement seeAllPeopleResultsLink;

	@FindBy(xpath = "//div[@class='search-results-container']/h2")
	private WebElement searchResultsText;
	
	@FindBy(xpath = "//ul[contains(@class,'global-nav__primary-items')]/li/a")
	private WebElement homeTab;
	

	String searchResultsPgTitle = "Search | LinkedIn";

	public void verifySearchResultsPgTitle() {
		log.debug("verify the Search Results page title:" + searchResultsPgTitle);
		wait.until(ExpectedConditions.titleContains(searchResultsPgTitle));
		Assert.assertTrue(driver.getTitle().contains(searchResultsPgTitle));
	}

	public void clickOnSeeAllResutlsLink() throws InterruptedException {
		log.debug("click on the see all restuls link");
		click(seeAllPeopleResultsLink);
	}
	public void clickOnHomeTab() throws InterruptedException
	{
		log.debug("click on Home tab");
		click(homeTab);
	}

	public long getResultsCount() throws InterruptedException  {
		verifySearchResultsPgTitle();
		if (isElementPresent(By.xpath("//a[@class='app-aware-link'][contains(.,'See all people results')]"))) {
            
			clickOnSeeAllResutlsLink();
			return fetchResutsCount();
		} else {
			return fetchResutsCount();

		}
	}

	public long fetchResutsCount() {
		log.debug("fetch the results count text");
		String results = searchResultsText.getText();
		log.debug("Search results text is:" + results);
		/*
		 * String results='About 297,000 results'
		 */
		String[] str = results.split(" ");
		long count=0;
		if(str.length>=3)
		{
        //str[1]=[About 297,000 results]
		String s1 = str[1].replace(",", "");
       //convert the String into long primative format
		count = Long.parseLong(s1);
	}else {
		//str[1]=[297,000 results]
				String s1 = str[0];
				count = Long.parseLong(s1);
		
	}
		return count;
	

	}

}
