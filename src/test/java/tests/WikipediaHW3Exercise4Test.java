package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WikipediaHW3Exercise4Test extends TestBase {
	private final String SEARCHED_WORD = "Java";


	@Test
	public void checkSearchWordTest() {

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

		List<WebElement> elementList = waitForCollectionElementsPresent(
				By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
				"Articles no found",
				15

		);

		assertTitleContainsWord(elementList);

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

	private List<WebElement> waitForCollectionElementsPresent(By by, String errorMessage, long timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.withMessage(errorMessage + "\n");
		List<WebElement> resultList = wait.until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(by)
		);
		Assert.assertFalse(
				errorMessage,
				resultList.isEmpty()
		);
		return resultList;
	}

	private void assertTitleContainsWord(List<WebElement> elementList) {
		elementList.forEach(element -> Assert.assertTrue(
				"At least one result not contains the searched word " + SEARCHED_WORD,
				element.getText().contains("SEARCHED_WORD"))
		);
	}
}
