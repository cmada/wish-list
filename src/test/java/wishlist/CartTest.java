package wishlist;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import webviews.CartPage;
import webviews.Header;
import webviews.ProductDetailsPage;
import webviews.ProductsGrid;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CartTest extends TestBase {
    @Test
    public void addSimpleProductToCart() {
        Header header = PageFactory.initElements(driver, Header.class);
        header.search("vase");

        String productName = "Herald Glass Vase";

        ProductsGrid productsGrid = PageFactory.initElements(driver, ProductsGrid.class);
        productsGrid.getAddToCartButtonByProductName(productName).click();

        assertProductAddedToCart(productName);
    }

    @Test
    public void addProductWithSpecificSizeAndColorToCart() {
        By womenCategoryLocator = By.linkText("WOMEN");
        By newArrivalsSubCategoryLocator = By.linkText("New Arrivals");
        mouseOverAndClickLast(Arrays.asList(womenCategoryLocator, newArrivalsSubCategoryLocator));

        ProductsGrid productsGrid = PageFactory.initElements(driver, ProductsGrid.class);
        productsGrid.getViewDetailsButton().click();

        ProductDetailsPage productDetailsPage = PageFactory.initElements(driver, ProductDetailsPage.class);

        String productName = productDetailsPage.getProductNameContainer().getText();

        productDetailsPage.getPinkColorSelector().click();
        productDetailsPage.getSmallSizeSelector().click();
        productDetailsPage.getAddToCartButton().click();

        assertProductAddedToCart(productName);

        CartPage cartPage = PageFactory.initElements(driver, CartPage.class);
        assertThat("Unexpected product color in cart.", cartPage.getProductColorContainer().getText(),
                containsString("Pink"));
        assertThat("Unexpected product size in cart.", cartPage.getProductSizeContainer().getText().trim(),
                is("S"));
    }

    @Test
    public void updateProductQuantityFromCartPage() {
        addSimpleProductToCart();

        CartPage cartPage = PageFactory.initElements(driver, CartPage.class);

        String initialSubtotalText = cartPage.getProductSubtotalContainer().getText();
        String initialSubtotalWithoutCurrency = initialSubtotalText.split(" ")[0];
        double initialSubtotal = Double.parseDouble(initialSubtotalWithoutCurrency.replace(",", "."));

        cartPage.getQuantityField().clear();
        cartPage.getQuantityField().sendKeys("2");
        cartPage.getUpdateQuantityButton().click();

        String updatedSubtotalText = cartPage.getProductSubtotalContainer().getText();
        String updatedSubtotalWithoutCurrency = updatedSubtotalText.split(" ")[0];
        double updatedSubtotal = Double.parseDouble(updatedSubtotalWithoutCurrency.replace(",", "."));

        assertThat("Subtotal not updated.", updatedSubtotal, equalTo(initialSubtotal * 2));
    }

    @Test
    public void removeTheOneProductFromCart() {
        addSimpleProductToCart();

        CartPage cartPage = PageFactory.initElements(driver, CartPage.class);
        cartPage.getRemoveProductButton().click();

        String pageTitle = getPageTitle();
        assertThat("Cart is not empty.", pageTitle, is("SHOPPING CART IS EMPTY"));
    }

    @Test
    public void emptyCartFromCartFooter() {
        addSimpleProductToCart();

        CartPage cartPage = PageFactory.initElements(driver, CartPage.class);
        cartPage.getEmptyCartButton().click();

        String pageTitle = getPageTitle();
        assertThat("Cart is not empty.", pageTitle, is("SHOPPING CART IS EMPTY"));
    }

    private void assertProductAddedToCart(String productName) {
        WebElement successMessageContainer = getSuccessMessageContainer();
        assertThat("Product not mentioned in confirmation message.",
                successMessageContainer.getText(),
                equalTo(productName + " was added to your shopping cart."));

        CartPage cartPage = PageFactory.initElements(driver, CartPage.class);
        assertThat("No product is present in cart.", cartPage.getProductNameContainer(), notNullValue());
        assertThat("Added product is not present in cart.",
                cartPage.getProductNameContainer().getText(), is(productName.toUpperCase()));
    }
}
