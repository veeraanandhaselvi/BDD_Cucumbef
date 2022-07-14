/**
 * @author Veera Anandha Selvi Saravana Muthu
 *
 * Explanation: Page dedicated to DSL Calculator Functionality
 */

/***************************************************/
package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DSL_CalculatorPage extends BasePage {

	/* Elements mapping */
	@FindBy(id = "mps-label-3")
	private WebElement dslOptionLink;

	@FindBy(xpath = "//input[@name='phonePrefix']")
	private WebElement areaCodeId;

	@FindBy(xpath = "//label[contains(.,'100 MBit/s')]")
	private WebElement bandwidthBtn100;

	@FindBy(xpath = "//label[contains(.,'50 MBit/s')]")
	private WebElement bandwidthBtn50;

	@FindBy(xpath = "//label[contains(.,'250 MBit/s')]")
	private WebElement bandwidthBtn250;

	@FindBy(xpath = "//label[contains(.,'16 MBit/s')]")
	private WebElement bandwidthBtn16;

	@FindBy(xpath = "//form[@action='/internet/vergleich/']// button[text()='Jetzt vergleichen']")
	private WebElement compareNowBtn;

	@FindBy(css = ".summary-tariff")
	private WebElement summartyTarifflbl;

	@FindBy(xpath = "//img[@class='download-icon']//following-sibling::b")
	private List<WebElement> downloadSpeedVal;

	@FindBy(xpath = "//div[@class='col-sm comparison-rank font-weight-bold ml-md-1']")
	private List<WebElement> resultIndicatorTariffsVal;

	@FindBy(xpath = "//button[contains(.,'weitere tarife laden')]")
	private List<WebElement> weitereTariffbutton;

	/* Global Static variables to verify it in test scenario */
	public static String valSummary = null;
	public static int currentValue = 0;
	int selectedbandwidth = 0;
	public static Boolean isWeitereTariffButtonFound = false;

	public DSL_CalculatorPage(WebDriver driver) throws InterruptedException {

		super(driver);
	}

	/* Scenario 1 : Element interaction based on story 1 listed below */
	public void chooseDSL() {
		click(dslOptionLink);
	}

	public void enterAreaCode(String areaCode) {
		clearAndSendKeys(areaCodeId, areaCode);
	}

	public void selectBandwidth(int bandwidth) {
		selectedbandwidth = bandwidth;
		if (bandwidth == 16)
			click(bandwidthBtn16);
		else if (bandwidth == 50)
			click(bandwidthBtn50);
		else if (bandwidth == 100)
			click(bandwidthBtn100);
		else if (bandwidth == 250)
			click(bandwidthBtn250);
		else
			System.out.println("Please choose valid bandwidth (16,50,100,250");

	}

	public void compareNow() {
		click(compareNowBtn);
	}

	/* Verifying the tariff results */
	public Boolean summaryTariffVerification() {
		Boolean issummaryMetCriteria = true;
		valSummary = getElementText(summartyTarifflbl).split(" ")[0];
		if (Integer.parseInt(valSummary) < 5) {
			System.out.println("Results should contains at least 5 internet tariffs");
			issummaryMetCriteria = false;
		}

		return issummaryMetCriteria;
	}

	/* Verifying the downloadspeed criteria based on results */
	public Boolean downloadSpeedVerification() {

		Boolean isDownloadSpeedMetCriteria = true;
		for (WebElement weDownload : downloadSpeedVal) {
			if (Integer.parseInt(getElementText(weDownload)) < selectedbandwidth) {
				isDownloadSpeedMetCriteria = false;
				break;
			}
		}

		return isDownloadSpeedMetCriteria;
	}

	/* Scenario 2 : Element interaction based on story 2 listed below */
	public void scrollToWeitereTariffButton() throws InterruptedException {
		scrollToElementCustom("900");
		Thread.sleep(200);
	}

	public Boolean check20TariffsDisplayed() {
		Boolean is20TariffsDisplayed = false;
		if (resultIndicatorTariffsVal.size() == 20
				&& getElementText(resultIndicatorTariffsVal.get(resultIndicatorTariffsVal.size() - 1)).contains("20")) {
			is20TariffsDisplayed = true;

		} else if (Integer.parseInt(valSummary) > resultIndicatorTariffsVal.size()
				&& resultIndicatorTariffsVal.size() % 20 == 0) {
			is20TariffsDisplayed = true;
		} else if (Integer.parseInt(valSummary) == resultIndicatorTariffsVal.size()) {
			is20TariffsDisplayed = true;
		}

		if (is20TariffsDisplayed) {
			int lastTariffValue = getElementText(resultIndicatorTariffsVal.get(resultIndicatorTariffsVal.size() - 1))
					.length();

			currentValue = Integer
					.parseInt(getElementText(resultIndicatorTariffsVal.get(resultIndicatorTariffsVal.size() - 1))
							.substring(0, lastTariffValue - 1));

		}

		return is20TariffsDisplayed;
	}

	public void loadAdditionalTariffUntillEnd() throws InterruptedException {
		if (currentValue < Integer.parseInt(valSummary)) {
			scrollToWeitereTariffButton();
			clickWeitereTariffLaden();
			if (check20TariffsDisplayed()) {
				loadAdditionalTariffUntillEnd();
			}
		}

		// When finally no button to interact as final result met
		if (currentValue == Integer.parseInt(valSummary)) {
			clickWeitereTariffLaden();

		}
	}

	/*
	 * In case if we need to verify tariff results based on value comes in Button
	 * this may help
	 */
	public String verifyNextPaginationAction() throws InterruptedException {
		String noWeiterButton = null;

		if (weitereTariffbutton.size() == 0) {
			noWeiterButton = "The weitere Tarife laden button is no longer displayed and all the tariffs are visible";
			return noWeiterButton;
		} else {
			for (WebElement weitereTariffbtn : weitereTariffbutton) {
				currentValue = Integer.parseInt(getElementText(weitereTariffbtn).split(" ")[0]);

				if (currentValue == 20 && Integer.parseInt(valSummary) > 20) {
					clickButtonAndScroll(weitereTariffbtn);
					verifyNextPaginationAction();
				} else {
					clickButtonAndScroll(weitereTariffbtn);
				}
			}
		}

		return noWeiterButton;
	}

	public void clickWeitereTariffLaden() throws InterruptedException {
		if (weitereTariffbutton.size() == 0) {
			isWeitereTariffButtonFound = false;
		} else if (Integer
				.parseInt(getElementText(weitereTariffbutton.get(weitereTariffbutton.size() - 1)).split(" ")[0]) > 0) {
			click(weitereTariffbutton.get(weitereTariffbutton.size() - 1));
			isWeitereTariffButtonFound = true;
		}

		// Thread.sleep(500);

	}

	public void clickButtonAndScroll(WebElement weitereTariffbtn) throws InterruptedException {

		click(weitereTariffbtn);
		 Thread.sleep(200);
		scrollToWeitereTariffButton();
		 Thread.sleep(200);

	}
}
