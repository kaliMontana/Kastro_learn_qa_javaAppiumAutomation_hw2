package tests;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;

public class WikipediaTests extends TestBase {
	@Test
	public void sendKeysTest() throws InterruptedException {
		WebElement element_to_init_search = driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]");
		element_to_init_search.click();

		WebElement element_to_enter_search_line = driver.findElementByXPath("//*[contains(@text, 'Search…')]");
		element_to_enter_search_line.sendKeys("Appium");
		sleep(6000);
	}

	@Test
	public void sendKeysWithWaitTest() {
		WebElement element_to_init_search = driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]");
		element_to_init_search.click();

		WebElement element_to_enter_search_line = waitForElementPresentByXpath(
				"//*[contains(@text, 'Search…')]",
				"Cannot find seaerch input",
				5
		);

		element_to_enter_search_line.sendKeys("Appium");
	}

	@Test
	public void sendKeysWithWaitAndOverloadingMethodsTest() {
		WebElement element_to_init_search = driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]");
		element_to_init_search.click();

		WebElement element_to_enter_search_line = waitForElementPresentByXpath(
				"//*[contains(@text, 'Search…')]",
				"Cannot find search input"
		);

		element_to_enter_search_line.sendKeys("Appium");
	}

	private WebElement waitForElementPresentByXpath(String xpath, String errorMessage, long timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.withMessage(errorMessage + "\n");
		By by = By.xpath(xpath);
		return wait.until(
				ExpectedConditions.presenceOfElementLocated(by)
		);
	}

	private WebElement waitForElementPresentByXpath(String xpath, String errorMessage) {
		return waitForElementPresentByXpath(xpath, errorMessage, 5);
	}

	@Test
	public void sendKeysWithWaitAndXpathConcatTest() {
		WebElement element_to_init_search = driver.findElementByXPath("//*[contains(@text, 'Search Wikipedia')]");
		element_to_init_search.click();

		WebElement element_to_enter_search_line = waitForElementPresentByXpath(
				"//*[contains(@text, 'Search…')]",
				"Cannot find search input"
		);

		element_to_enter_search_line.sendKeys("Java");
		waitForElementPresentByXpath(
				"//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']",
				"Cannot find 'Object-oriented programming language' topic searching by 'Java'",
				15
		);
	}

	@Test
	public void sendKeysWithMethodsClickAndSendKeysTest() {
		waitForElementByXpathAndClick(
				"//*[contains(@text, 'Search Wikipedia')]",
				"Cannot find search wikipedia input",
				5
		);

		waitForElementByXpathAndSendKeys(
				"//*[contains(@text, 'Search…')]",
				"Java",
				"Cannot find search input",
				5
		);

		waitForElementPresentByXpath(
				"//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']",
				"Cannot find 'Object-oriented programming language' topic searching by 'Java'",
				15
		);
	}

	private WebElement waitForElementByXpathAndClick(String xpath, String errorMessage, long timeoutInSeconds) {
		WebElement element = waitForElementPresentByXpath(xpath, errorMessage, timeoutInSeconds);
		element.click();
		return element;
	}

	private WebElement waitForElementByXpathAndSendKeys(String xpath, String value, String errorMessage, long timeoutInSeconds) {
		WebElement element = waitForElementPresentByXpath(xpath, errorMessage, timeoutInSeconds);
		element.sendKeys(value);
		return element;
	}

	@Test
	public void cancelSearchTest() {
		waitForElementByIdAndClick(
				"org.wikipedia:id/search_container",
				"Cannot find 'search wikipedia' input",
				5
		);

		waitForElementByIdAndClick(
				"org.wikipedia:id/search_close_btn",
				"Cannot find X to cancel search input",
				5
		);

		waitForElementNotPresent(
				"org.wikipedia:id/search_close_btn",
				"X is still present on the page",
				5
		);
	}

	private WebElement waitElementWaitForElementPresentById(String id, String errorMessage, long timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.withMessage(errorMessage + "\n");
		By by = By.id(id);
		return wait.until(
				ExpectedConditions.presenceOfElementLocated(by)
		);
	}

	private WebElement waitForElementByIdAndClick(String id, String errorMessage, long timeoutInSeconds) {
		WebElement element = waitElementWaitForElementPresentById(id, errorMessage, timeoutInSeconds);
		element.click();
		return element;
	}

	private boolean waitForElementNotPresent(String id, String errorMessage, long timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.withMessage(errorMessage + "\n");
		By by = By.id(id);
		return wait.until(
				ExpectedConditions.invisibilityOfElementLocated(by)
		);
	}

	@Test
	public void generalTestSendKeysWithMethodsClickAndSendKeysTest() {
		waitForElementAndClick(
				By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
				"Cannot find search wikipedia input",
				5
		);

		waitForElementAndSendKeys(
				By.xpath("//*[contains(@text, 'Search…')]"),
				"Java",
				"Cannot find search input",
				5
		);

		waitForElementPresent(
				By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
				"Cannot find 'Object-oriented programming language' topic searching by 'Java'",
				15
		);
	}

	@Test
	public void generalTestCancelSearchTest() {
		waitForElementAndClick(
				By.id("org.wikipedia:id/search_container"),
				"Cannot find 'search wikipedia' input",
				5
		);

		waitForElementAndClick(
				By.id("org.wikipedia:id/search_close_btn"),
				"Cannot find X to cancel search input",
				5
		);

		waitForElementNotPresent(
				By.id("org.wikipedia:id/search_close_btn"),
				"X is still present on the page",
				5
		);
	}


	private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.withMessage(errorMessage + "\n");
		return wait.until(
				ExpectedConditions.presenceOfElementLocated(by)
		);
	}

	private WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds) {
		WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
		element.click();
		return element;
	}

	private WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds) {
		WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
		element.sendKeys(value);
		return element;
	}

	private boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.withMessage(errorMessage + "\n");
		return wait.until(
				ExpectedConditions.invisibilityOfElementLocated(by)
		);
	}

	@Test
	public void compareArticleTitleTest() {
		waitForElementAndClick(
				By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
				"Cannot find 'Search wikipedia' input",
				5
		);

		waitForElementAndSendKeys(
				By.xpath("//*[contains(@text, 'Search…')]"),
				"Java",
				"Cannot find search input",
				5
		);

		waitForElementAndClick(
				By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
				"Cannot find 'Search wikipedia' input",
				5
		);

		WebElement title_element = waitForElementAndClick(
				By.id("org.wikipedia:id/view_page_title_text"),
				"Cannot find article title",
				15
		);

		String article_title = title_element.getAttribute("text");

		Assert.assertEquals(
				"We see unexpected title",
				"Java (programming language)",
				article_title
		);
	}

	@Test
	public void CancelSearchWithClearTest() {
		waitForElementAndClick(
				By.id("org.wikipedia:id/search_container"),
				"Cannot find 'search wikipedia' input",
				5
		);

		waitForElementAndSendKeys(
				By.xpath("//*[contains(@text, 'Search…')]"),
				"Java",
				"Cannot find search input",
				5
		);

		waitForElementAndClear(
				By.id("org.wikipedia:id/search_src_text"),
				"Cannot find search filed",
				5
		);

		waitForElementAndClick(
				By.id("org.wikipedia:id/search_close_btn"),
				"Cannot find X to cancel search input",
				5
		);

		waitForElementNotPresent(
				By.id("org.wikipedia:id/search_close_btn"),
				"X is still present on the page",
				5
		);
	}

	private WebElement waitForElementAndClear(By by, String errorMessage, long timeoutInSeconds) {
		WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
		element.clear();
		return element;
	}
}
