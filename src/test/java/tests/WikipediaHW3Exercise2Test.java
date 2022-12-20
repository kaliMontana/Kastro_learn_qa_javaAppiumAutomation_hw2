package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WikipediaHW3Exercise2Test extends TestBase {
	private final String SEARCHED_WORD = "Docker";
	private final String ARTICLE_SUBTITLE = "Software for deploying containerized applications";


	@Test
	public void checkContentSearchElementTest() {
		waitForElementAndClick(
				By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
				"Cannot find 'Search wikipedia' input",
				5
		);

		waitForElementAndSendKey(
				By.xpath("//*[contains(@text, 'Search…')]"),
				SEARCHED_WORD,
				"Cannot find search input",
				5
		);

		waitForInputFieldAndCheckThatHasText(
				By.id("org.wikipedia:id/search_src_text"),
				"Cannot find search filed",
				"The search filed has not text",
				5
		);

		waitForElementAndClick(
				By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Open-source software for deploying containerized applications']"),
				"Cannot find 'Search wikipedia' input",
				5
		);

		assertElementHasText(
				By.id("org.wikipedia:id/view_page_subtitle_text"),
				ARTICLE_SUBTITLE,
				"Cannot find article subtitle",
				"We see unexpected subtitle",
				15
		);
	}

	private void assertElementHasText(By by, String articleSubtitle, String errorMessage, String errorAssertMessage, long timeoutInSeconds) {
		String actualText = waitForElementPresent(by, errorMessage, timeoutInSeconds).getText();
		Assert.assertEquals(
				errorAssertMessage,
				articleSubtitle,
				actualText
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

	private WebElement waitForElementAndSendKey(By by, String value, String errorMessage, long timeoutInSeconds) {
		WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
		element.sendKeys(value);
		return element;
	}

	private WebElement waitForInputFieldAndCheckThatHasText(By by, String errorMessage, String AssertErrorMessage, long timeoutInSeconds) {
		WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
		String text = element.getText();
		Assert.assertNotEquals(
				AssertErrorMessage,
				text
		);
		return element;
	}
}
