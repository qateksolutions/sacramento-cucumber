package page_objects;

import command_providers.ActOn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class RealApr extends NavigationBar {
    private final By CalculatorTab = By.xpath("//label[text()='Calculator']");
    private final By HomePriceInputField = By.name("HomeValue");
    private final By DownPaymentInputField = By.name("DownPayment");
    private final By DownPaymentInDollar = By.id("DownPaymentSel0");
    private final By InterestRateInputField = By.name("Interest");
    private final By CalculateRateButton = By.name("calculate");
    private final By ActualAprRate = By.xpath("//strong[text()='Actual APR:']/parent::td/following-sibling::td/strong");
    private static final Logger LOGGER = LogManager.getLogger(RealApr.class);

    public RealApr(WebDriver driver) {
        super(driver);
    }

    public RealApr waitForPageToLoad() {
        LOGGER.debug("Waiting for Real APR page to load");
        ActOn.wait(driver, CalculatorTab).waitForElementToBeVisible();
        return this;
    }

    public RealApr typeHomePrice(String value) {
        LOGGER.debug("Typing Home Value: " + value);
        ActOn.element(driver, HomePriceInputField).setValue(value);
        return this;
    }

    public RealApr clickDownPaymentInDollar() {
        LOGGER.debug("Clicking on the $ as down payment");
        ActOn.element(driver, DownPaymentInDollar).click();
        return this;
    }

    public RealApr typeDownPayment(String value) {
        LOGGER.debug("Typing Down Payment Value: " + value);
        ActOn.element(driver, DownPaymentInputField).setValue(value);
        return this;
    }

    public RealApr typeInterestRate(String value) {
        LOGGER.debug("Typing Interest Rate Value: " + value);
        ActOn.element(driver, InterestRateInputField).setValue(value);
        return this;
    }

    public RealApr clickOnCalculateRateButton() {
        LOGGER.debug("Clicking on the Calculate Rate Button");
        ActOn.element(driver, CalculateRateButton).click();
        return this;
    }

    public RealApr validateRealAprRate(String expectedValue) throws Exception {
        LOGGER.debug("Validate the Real Apr Rate is: " + expectedValue);
        String actualRealAprRate = ActOn.element(driver, ActualAprRate).getTextValue();
        if (!Objects.equals(actualRealAprRate, expectedValue)) {
            throw new Exception("The actual apr rate: " + actualRealAprRate + " did not match with expected APR rate: " + expectedValue);
        }
        return this;
    }
}
