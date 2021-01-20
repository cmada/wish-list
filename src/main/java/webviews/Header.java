package webviews;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header {
    @FindBy(id = "search")
    private WebElement searchField;

    @FindBy(className = "skip-account")
    private WebElement accountLink;

    @FindBy(linkText = "Register")
    private WebElement registerLink;

    public void search(String keyword) {
        searchField.sendKeys(keyword + Keys.ENTER);
        System.out.println("Pressed Enter in search field.");
    }

    public WebElement getSearchField() {
        return searchField;
    }

    public WebElement getAccountLink() {
        return accountLink;
    }

    public WebElement getRegisterLink() {
        return registerLink;
    }
}
