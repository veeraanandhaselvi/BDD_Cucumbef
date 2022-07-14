package Factory;

import org.openqa.selenium.WebDriver;
import Pages.DSL_CalculatorPage;

public class PageFactory {
	private static DSL_CalculatorPage dsl_CalPage;

	public static DSL_CalculatorPage getDSLCalculator_Page(WebDriver driver) throws InterruptedException {
		return dsl_CalPage == null ? new DSL_CalculatorPage(driver) : dsl_CalPage;
	}

}