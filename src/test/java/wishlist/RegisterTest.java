package wishlist;

import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class RegisterTest {
    @Test
    public void registerTest() {

        WebDriver driver = new ChromeDriver();

        driver.get("http://testfasttrackit.info/selenium-test");

        driver.findElement(By.linkText("ACCOUNT")).click();
        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("firstname")).sendKeys("Madlene20201");
        driver.findElement(By.id("middlename")).sendKeys("Ioana");
        driver.findElement(By.id("lastname")).sendKeys("Ciceu");
        driver.findElement(By.name("email")).sendKeys("test.email@gmail.com");
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.name("confirmation")).sendKeys("password");
        driver.findElement(By.className("checkbox")).click();

        driver.findElement(By.xpath("//div[@class='account-create']//button[@title='Register']")).click();

        WebElement successMessageContainer = driver.findElement(By.className("success-msg"));

        assertThat("Confirmation message not displayed.", successMessageContainer, notNullValue());
        assertThat("Unexpected confirmation message.", successMessageContainer.getText(),
                is("Thank you for registering with Madison Island."));

        driver.quit();
    }

}
