package webviews;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage {
    @FindBy(css = ".product-cart-info .product-name")
    private WebElement productNameContainer;

    @FindBy(xpath = "//*[@class='product-cart-info']//*[@class='item-options']//dd[preceding-sibling::dt[contains(text(), 'Color')]]")
    private WebElement productColorContainer;

    @FindBy(xpath = "//*[@class='product-cart-info']//*[@class='item-options']//dd[preceding-sibling::dt[contains(text(), 'Size')]]")
    private WebElement productSizeContainer;

    @FindBy(css = ".product-cart-actions .qty")
    private WebElement quantityField;

    @FindBy(css = ".product-cart-actions button[value='update_qty']")
    private WebElement updateQuantityButton;

    @FindBy(css = ".product-cart-total .cart-price")
    private WebElement productSubtotalContainer;

    @FindBy(css = ".product-cart-remove a")
    private WebElement removeProductButton;

    @FindBy(css = ".cart-footer-actions .btn-empty")
    private WebElement emptyCartButton;

    public WebElement getProductColorContainer() {
        return productColorContainer;
    }

    public WebElement getProductSizeContainer() {
        return productSizeContainer;
    }

    public WebElement getProductNameContainer() {
        return productNameContainer;
    }

    public WebElement getQuantityField() {
        return quantityField;
    }

    public WebElement getUpdateQuantityButton() {
        return updateQuantityButton;
    }

    public WebElement getProductSubtotalContainer() {
        return productSubtotalContainer;
    }

    public WebElement getRemoveProductButton() {
        return removeProductButton;
    }

    public WebElement getEmptyCartButton() {
        return emptyCartButton;
    }
}
