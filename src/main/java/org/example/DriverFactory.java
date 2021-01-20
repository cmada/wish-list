package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver initWebDriver(String browser) {
        switch (browser) {
            default:
                System.out.println("Using default browser: Chrome");
            case "chrome":
                if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                    System.setProperty("webdriver.chrome.driver",
                            AppConfig.getChromeDriverPath());
                } else {
                    System.setProperty("webdriver.chrome.driver",
                            AppConfig.getUnixChromeDriverPath());
                }

                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver",
                        AppConfig.getGeckoDriverPath());
                driver = new FirefoxDriver();
                break;
            case "ie":
                System.setProperty("webdriver.ie.driver",
                        AppConfig.getIeDriverPath());
                driver = new InternetExplorerDriver();
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(AppConfig.getImplicitTimeout(), TimeUnit.SECONDS);

        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}

