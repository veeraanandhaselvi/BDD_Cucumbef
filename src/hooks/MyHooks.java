/**
 * @author Veera Anandha Selvi Saravana Muthu
 *
 * Explanation: Before executing the each scenario, here we can do before and after settings depends upon application requirement
 */

/***************************************************/

package hooks;

import static constants.FrameworkConstants.BROWSER_CHROME;
import static constants.FrameworkConstants.PARAMETER_BROWSER;

import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;

import Factory.DriverFactory;
import context.TestContext;

public class MyHooks {

	private WebDriver driver;

	/*@Before
	public void getDriver(String baseURL) {
		driver = DriverFactory.initializeDriver(System.getProperty(PARAMETER_BROWSER, BROWSER_CHROME),baseURL);
		context.driver=driver;
	}

	@After
	public void after(Scenario scenario) {

		driver.quit();
	}*/

}
