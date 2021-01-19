package wishlist;

import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegisterTest {
    @Test
    public void registerTest() {

        WebDriver driver = new ChromeDriver();

        driver.get("http://testfasttrackit.info/selenium-test");

        driver.findElement(By.linkText("ACCOUNT")).click();
        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("firstname")).sendKeys("Madalina");
        driver.findElement(By.id("middlename")).sendKeys("Ioana");
        driver.findElement(By.id("lastname")).sendKeys("Ciceu");
        driver.findElement(By.name("email")).sendKeys("madalina.ciceu@gmail.com");
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.name("confirmation")).sendKeys("password");
        driver.findElement(By.className("checkbox")).click();

        driver.quit();
    }

}
