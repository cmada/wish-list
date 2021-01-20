package webviews;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage {
    @FindBy(name = "pink")
    private WebElement pinkColorSelector;

    @FindBy(name = "s")
    private WebElement smallSizeSelector;

    @FindBy(css = ".add-to-cart-buttons .btn-cart")
    private WebElement addToCartButton;

    @FindBy(css = ".product-shop .product-name")
    private WebElement productNameContainer;

    public WebElement getPinkColorSelector() {
        return pinkColorSelector;
    }

    public WebElement getSmallSizeSelector() {
        return smallSizeSelector;
    }

    public WebElement getAddToCartButton() {
        return addToCartButton;
    }

    public WebElement getProductNameContainer() {
        return productNameContainer;
    }
}

