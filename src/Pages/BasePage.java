/**
 * @author Veera Anandha Selvi Saravana Muthu
 *
 * Explanation: Base page which is reusable across multiple functionalities
 */

/***************************************************/
package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Factory.DriverFactory;
import utilities.ConfigLoader;
import static constants.FrameworkConstants.BROWSER_CHROME;
import static constants.FrameworkConstants.PARAMETER_BROWSER;
import static constants.FrameworkConstants.EXPLICIT_WAIT;
import static constants.FrameworkConstants.JETZT_VERGLEICHEN;

import java.time.Duration;
import java.util.List;

public class BasePage extends DriverFactory {
	public static WebDriver driver;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) throws InterruptedException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void load(String endPoint) throws InterruptedException {
		driver = initializeDriver(System.getProperty(PARAMETER_BROWSER, BROWSER_CHROME),
				ConfigLoader.getInstance().getBaseUrl().toString());
		wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		Thread.sleep(100);
		enableCookies();
		PageFactory.initElements(driver, this);
	}

	protected void enableCookies() {
		WebElement allCookie = driver.findElement(By.xpath("//button[@id='uc-btn-accept-banner']"));
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitForElementToBeClickable(allCookie).click();
	}

	public void waitForOverlaysToDisappear(By overlay) {
		List<WebElement> overlays = driver.findElements(overlay);
		System.out.println("OVERLAY SIZE" + overlays.size());
		if (overlays.size() > 0) {
			wait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
			System.out.println("OVERLAYS INVISIBLE");
		} else {
			System.out.println("OVERLAY NOT FOUND");
		}
	}

	public void clearAndSendKeys(WebElement element, String value) {
		element = waitForElementVisibility(element);
		element.clear();
		element.sendKeys(value);
	}

	public void click(WebElement element) {
		try {
			Thread.sleep(500);
			if (element.getText().equals(JETZT_VERGLEICHEN)) {
				waitForElementToBeClickable(element).submit();
			} else {
				waitForElementToBeClickable(element).click();
			}
			// driver.navigate().refresh();
		} catch (InterruptedException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void click(By by) {
		waitForElementToBeClickable(by).click();
	}

	public WebElement waitForElementVisibility(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public WebElement waitForElementToBeClickable(WebElement element) {
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public WebElement waitForElementToBeClickable(By by) {
		return wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollToElementCustom(String value) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + value + ")");

	}

	public String getElementText(WebElement element) {
		return waitForElementVisibility(element).getText();
	}

	public String getElementElementAttribute_Value(WebElement element) {
		return null; // waitForElementVisibility(element).getAttribute(ATTRIBUTE_VALUE);
	}

	public String getElementElementCustomAttribute(WebElement element, String customAttribute) {
		return waitForElementVisibility(element).getAttribute(customAttribute);
	}

}
