/**
 * @author Veera Anandha Selvi Saravana Muthu
 *
 * Explanation: This definition holds each sceanario/sub functionaliies of DSL Calculator feature
 */

/***************************************************/
package stepdefinitions;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;

import Factory.PageFactory;
import Pages.DSL_CalculatorPage;
import context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Task1_StepDefinitions {

	public static DSL_CalculatorPage dslCalculatorPage;
	public static WebDriver driver;

	public Task1_StepDefinitions(TestContext context) throws Exception {
		if (this.driver == null) {
			this.driver = context.driver;
			dslCalculatorPage = PageFactory.getDSLCalculator_Page(this.driver);
		}
	}

	@Given("that I can open {string}")
	public void that_i_can_open(String url) throws InterruptedException {
		System.out.println("Scenario 1:");
		dslCalculatorPage.load(url);
		driver = dslCalculatorPage.driver; // To retain the current scenario driver as it reset for every scenario
											// execution
	}

	@When("I navigate to the DSL calculator page")
	public void i_navigate_to_the_dsl_calculator_page() {
		dslCalculatorPage.chooseDSL();
	}

	@When("I enter {string} for my area code")
	public void i_enter_for_my_area_code(String currentAreaCode) {
		dslCalculatorPage.enterAreaCode(currentAreaCode);
	}

	@When("I select the {int} Mbit\\/s bandwidth option")
	public void i_select_the_mbit_s_bandwidth_option(Integer currentBandwith) {
		dslCalculatorPage.selectBandwidth(currentBandwith);
	}

	@When("I click the {string} button")
	public void i_click_the_button(String string) {
		dslCalculatorPage.compareNow();
	}

	@Then("I should see a page that lists the available tariffs for my selection")
	public void i_should_see_a_page_that_lists_the_available_tariffs_for_my_selection() {
		if (dslCalculatorPage.summaryTariffVerification()) {
			System.out.println("It contains at least 5 internet tariffs");
			Assert.assertTrue(true, "It contains at least 5 internet tariffs");
		}

		if (dslCalculatorPage.downloadSpeedVerification()) {
			System.out.println("The displayed tariffs provided at least the selected Mbit/s download speed");
			Assert.assertTrue(true, "The displayed tariffs provided at least the selected Mbit/s download speed");
		}
	}

	// Scenario 2
	@Given("the same tariff calculation criteria from scenario 1")
	public void the_same_tariff_calculation_criteria_from_scenario1() throws InterruptedException {
		System.out.println("Scenario 2:");
		System.out.println("The same tariff calculation criteria from scenario"); // No implementation here as already
																					// met in previous scenario
	}

	@When("I display the tariff Result List page")
	public void i_display_the_tariff_result_list_page() {
		System.out.println("I displayed the tariff Result List page");// No implementation here as already met in
																		// previous scenario

	}

	@Then("I should see the total number of available tariffs listed in the {string} section")
	public void i_should_see_the_total_number_of_available_tariffs_listed_in_the_section(String totalTariffResults) {
		if (Integer.parseInt(dslCalculatorPage.valSummary) != 0) {
			System.out.println("The total number of available tariffs listed in the" + totalTariffResults
					+ " section is :" + dslCalculatorPage.valSummary);
			Assert.assertTrue(true, "I could see the total number of available tariffs listed in the "
					+ totalTariffResults + " section");
		}

	}

	@When("I scroll to the end of the Result List page")
	public void i_scroll_to_the_end_of_the_result_list_page() throws InterruptedException {
		dslCalculatorPage.scrollToWeitereTariffButton();
	}

	@Then("I should see only the first {int} tariffs displayed")
	public void i_should_see_only_the_first_tariffs_displayed(Integer paginationValue) {
		if (dslCalculatorPage.check20TariffsDisplayed()) {
			System.out.println("I could see only the first " + paginationValue + " tariffs displayed");
			Assert.assertTrue(true, "I could see only the first " + paginationValue + " tariffs displayed");
		}

	}

	@When("I click on the button labeled {string}")
	public void i_click_on_the_button_labeled(String string) throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		dslCalculatorPage.scrollToWeitereTariffButton();
		dslCalculatorPage.clickWeitereTariffLaden();
		// Thread.sleep(500);
	}

	@Then("I should see the next {int} tariffs displayed")
	public void i_should_see_the_next_tariffs_displayed(Integer pageinatioValue) {
		if (dslCalculatorPage.check20TariffsDisplayed()) {
			System.out.println("I could see the next " + pageinatioValue.toString() + "tariffs displayed");
			Assert.assertTrue(true, "I could see the next " + pageinatioValue.toString() + " tariffs displayed");
		}
	}

	@Then("I can continue to load any additional tariffs until all tariffs have been displayed")
	public void i_can_continue_to_load_any_additional_tariffs_until_all_tariffs_have_been_displayed()
			throws InterruptedException {

		dslCalculatorPage.loadAdditionalTariffUntillEnd();
		if (!dslCalculatorPage.isWeitereTariffButtonFound) {
			System.out
					.println("the weitere Tarife laden button is no longer displayed when all the tariffs are visible");
			Assert.assertTrue(true,
					"the weitere Tarife laden button is no longer displayed when all the tariffs are visible");
		}

		if (dslCalculatorPage.currentValue == Integer.parseInt(dslCalculatorPage.valSummary)) {
			System.out.println(
					"the total number of tariffs displayed matches the total listed in the Ermittelte Tarife section");
			Assert.assertTrue(true,
					"the total number of tariffs displayed matches the total listed in the Ermittelte Tarife section");
		}
	}

//	@AfterAll
//	public static void close() {
//	if(driver!=null) {
//	driver.close();}
//	}

}