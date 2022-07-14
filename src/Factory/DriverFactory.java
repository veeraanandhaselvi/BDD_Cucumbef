/**
 * @author Veera Anandha Selvi Saravana Muthu
 *
 * Explanation: It contains Browser Settings, driver launch information.
 */

/***************************************************/

package Factory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import static constants.FrameworkConstants.BROWSER_CHROME;
import static constants.FrameworkConstants.BROWSER_FIREFOX;
import static constants.FrameworkConstants.BROWSER_SAFARI;
import static constants.FrameworkConstants.BROWSER_EDGE;
import static constants.FrameworkConstants.BROWSER_OPERA;

public class DriverFactory {

	public WebDriver initializeDriver(String browser, String basePageURL) {
		WebDriver driver;
		switch (browser) {

		case BROWSER_CHROME: {
			//WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/resources/chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(basePageURL);

			break;
		}
		case BROWSER_FIREFOX: {
			WebDriverManager.firefoxdriver().setup();
			System.setProperty("webdriver. gecko. driver",
					System.getProperty("user.dir") + "/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		}
		case BROWSER_SAFARI: {
			driver = new SafariDriver();
			break;
		}
		case BROWSER_EDGE: {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		}
		case BROWSER_OPERA: {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
			break;
		}
		default:
			throw new IllegalStateException("INVALID BROWSER: " + browser);
		}
		driver.manage().window().maximize();
		return driver;
	}
}
