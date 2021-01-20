package webviews;

import org.example.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage {
    @FindBy(name = "firstname")
    private WebElement firstNameField;

    @FindBy(name = "middlename")
    private WebElement middleNameField;

    @FindBy(name = "lastname")
    private WebElement lastNameField;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "confirmation")
    private WebElement confirmPasswordField;

    @FindBy(className = "checkbox")
    private WebElement newsletterCheckbox;

    @FindBy(xpath = "//div[@class='account-create']//button[@title='Register']")
    private WebElement registerButton;

    @FindBy(id = "advice-validate-email-email_address")
    private WebElement emailValidationMessageContainer;

    @FindBy(id = "advice-validate-cpassword-confirmation")
    private WebElement confirmPasswordValidationMessageContainer;

    @FindBy(id = "advice-validate-password-password")
    private WebElement passwordValidationMessageContainer;

    public WebElement getFirstNameField() {
        return firstNameField;
    }

    public WebElement getMiddleNameField() {
        return middleNameField;
    }

    public WebElement getLastNameField() {
        return lastNameField;
    }

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public WebElement getNewsletterCheckbox() {
        return newsletterCheckbox;
    }

    public WebElement getRegisterButton() {
        return registerButton;
    }

    public WebElement getEmailValidationMessageContainer() {
        return emailValidationMessageContainer;
    }

    public WebElement getConfirmPasswordValidationMessageContainer() {
        return confirmPasswordValidationMessageContainer;
    }

    public WebElement getPasswordValidationMessageContainer() {
        return passwordValidationMessageContainer;
    }

    public WebElement getFieldByName(String name) {
        return DriverFactory.getDriver().findElement(By.name(name));
    }

    public WebElement getFieldRequiredErrorMessageContainer(String fieldNameAttribute) {
        if (fieldNameAttribute.equals("email")) {
            fieldNameAttribute += "_address";
        }
        return DriverFactory.getDriver().findElement(By.id("advice-required-entry-" + fieldNameAttribute));
    }
}
