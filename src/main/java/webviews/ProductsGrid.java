package webviews;

import org.example.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsGrid {
    @FindBy(css = ".category-products .button[title='View Details']")
    private WebElement viewDetailsButton;

    public WebElement getViewDetailsButton() {
        return viewDetailsButton;
    }

    public WebElement getAddToCartButtonByProductName(String productName) {
        return DriverFactory.getDriver().findElement(
                By.xpath("//div[@class='product-info' and ./descendant::*[text()" +
                        "='" + productName + "']]//button[contains(@class, 'btn-cart')]"));
    }
}
