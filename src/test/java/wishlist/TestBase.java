package wishlist;

import org.example.AppConfig;
import org.example.DriverFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class TestBase {
    protected WebDriver driver;

    @Before
    public void setup() {
        String browser = System.getProperty("browser", "chrome");

        driver = DriverFactory.initWebDriver(browser);

        driver.get(AppConfig.getSiteUrl());
    }

    @After
    public void teardown() {
        driver.quit();
    }

    protected void mouseOverAndClickLast(List<By> locators) {
        Actions actions = new Actions(driver);

        for (By locator : locators) {
            actions.moveToElement(driver.findElement(locator))
                    .perform();
        }

        actions.click().perform();
    }

    // useful when running tests in Firefox
    protected void waitForPageToLoad(long timeout) {
        long waited = 0;
        long pauseMillis = 500;
        do {
            try {
                Thread.sleep(pauseMillis);
            } catch (InterruptedException e) {
                System.out.println("Wait process interrupted.");
            }
            waited += pauseMillis;
        }
        while (waited <= timeout && !((JavascriptExecutor) driver)
                .executeScript("return document.readyState")
                .equals("complete"));
    }

    protected WebElement getSuccessMessageContainer() {
        return driver.findElement(By.cssSelector(".success-msg"));
    }

    protected String getPageTitle() {
        WebElement pageTitleContainer = driver.findElement(By.className("page-title"));
        if (pageTitleContainer == null) {
            return null;
        } else {
            return pageTitleContainer.getText();
        }
    }
}
