import org.example.DriverFactory;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import webviews.Header;
import webviews.RegisterPage;
import wishlist.TestBase;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Enclosed.class)
public class RegisterTest {

    @RunWith(Parameterized.class)
    public static class RequiredFieldsParameterizedTest extends TestBase {

        private String fieldNameAttribute;

        public RequiredFieldsParameterizedTest(String fieldNameAttribute) {
            this.fieldNameAttribute = fieldNameAttribute;
        }

        @Parameterized.Parameters
        public static List<String> requiredFieldNameAttributes() {
            return Arrays.asList("firstname", "lastname", "email", "password", "confirmation");
        }

        @Test
        public void unsuccessfulRegisterWithMissingMandatoryInformation() {
            RegisterPage registerPage = openRegisterPage();
            fillInAllFields(registerPage);

            registerPage.getFieldByName(fieldNameAttribute).clear();

            registerPage.getRegisterButton().click();

            WebElement fieldErrorContainer = registerPage.getFieldRequiredErrorMessageContainer(fieldNameAttribute);

            assertThat("Field error message not displayed.", fieldErrorContainer, notNullValue());
            assertThat("Unexpected field error message.", fieldErrorContainer.getText(), is("This is a required field."));
        }

        @Test
        public void unsuccessfulRegisterWithBlankMandatoryInformation() {
            RegisterPage registerPage = openRegisterPage();
            fillInAllFields(registerPage);

            registerPage.getFieldByName(fieldNameAttribute).clear();
            registerPage.getFieldByName(fieldNameAttribute).sendKeys(" ");

            registerPage.getRegisterButton().click();

            WebElement fieldErrorContainer = registerPage.getFieldRequiredErrorMessageContainer(fieldNameAttribute);

            assertThat("Field error message not displayed.", fieldErrorContainer, notNullValue());
            assertThat("Unexpected field error message.", fieldErrorContainer.getText(), is("This is a required field."));
        }
    }

    public static class RegularRegisterTest extends TestBase {

        @Test
        public void successfulRegisterWithAllDetails() {
            RegisterPage registerPage = openRegisterPage();
            fillInAllFields(registerPage);
            registerPage.getNewsletterCheckbox().click();

            registerPage.getRegisterButton().click();

            WebElement successMessageContainer = getSuccessMessageContainer();

            assertThat("Confirmation message not displayed.", successMessageContainer, notNullValue());
            assertThat("Unexpected confirmation message.", successMessageContainer.getText(),
                    is("Thank you for registering with Madison Island."));
        }

        @Test
        public void successfulRegisterWithMandatoryDetailsOnly() {
            RegisterPage registerPage = openRegisterPage();
            registerPage.getFirstNameField().sendKeys("Flaviu");
            registerPage.getLastNameField().sendKeys("Ratiu");
            registerPage.getEmailField().sendKeys("test" + System.currentTimeMillis() + "@example.com");
            registerPage.getPasswordField().sendKeys("passw0rd");
            registerPage.getConfirmPasswordField().sendKeys("passw0rd");

            registerPage.getRegisterButton().click();

            WebElement successMessageContainer = getSuccessMessageContainer();

            assertThat("Confirmation message not displayed.", successMessageContainer, notNullValue());
            assertThat("Unexpected confirmation message.", successMessageContainer.getText(),
                    is("Thank you for registering with Madison Island."));
        }


        @Test
        public void unsuccessfulRegisterWithInvalidEmailAddress() {
            RegisterPage registerPage = openRegisterPage();
            fillInAllFields(registerPage);

            registerPage.getEmailField().clear();
            registerPage.getEmailField().sendKeys("email@test");

            registerPage.getRegisterButton().click();

            WebElement fieldErrorContainer = registerPage.getEmailValidationMessageContainer();

            assertThat("Field error message not displayed.", fieldErrorContainer, notNullValue());
            assertThat("Unexpected field error message.", fieldErrorContainer.getText(),
                    is("Please enter a valid email address. For example johndoe@domain.com."));
        }

        @Test
        public void unsuccessfulRegisterWithInvalidPasswordConfirmation() {
            RegisterPage registerPage = openRegisterPage();
            fillInAllFields(registerPage);

            registerPage.getConfirmPasswordField().clear();
            registerPage.getConfirmPasswordField().sendKeys("different");

            registerPage.getRegisterButton().click();

            WebElement fieldErrorContainer = registerPage.getConfirmPasswordValidationMessageContainer();

            assertThat("Field error message not displayed.", fieldErrorContainer, notNullValue());
            assertThat("Unexpected field error message.", fieldErrorContainer.getText(),
                    is("Please make sure your passwords match."));
        }

        @Test
        public void unsuccessfulRegisterWithTooShortPassword() {
            RegisterPage registerPage = openRegisterPage();
            fillInAllFields(registerPage);

            registerPage.getPasswordField().clear();
            registerPage.getPasswordField().sendKeys("1");

            registerPage.getRegisterButton().click();

            WebElement fieldErrorContainer = registerPage.getPasswordValidationMessageContainer();

            assertThat("Field error message not displayed.", fieldErrorContainer, notNullValue());
            assertThat("Unexpected field error message.", fieldErrorContainer.getText(),
                    is("Please enter 6 or more characters without leading or trailing spaces."));
        }
    }

    private static RegisterPage openRegisterPage() {
        Header header = PageFactory.initElements(DriverFactory.getDriver(), Header.class);
        header.getAccountLink().click();
        header.getRegisterLink().click();

        return PageFactory.initElements(DriverFactory.getDriver(), RegisterPage.class);
    }

    private static void fillInAllFields(RegisterPage registerPage) {
        registerPage.getFirstNameField().sendKeys("Flaviu");
        registerPage.getMiddleNameField().sendKeys("G");
        registerPage.getLastNameField().sendKeys("Ratiu");
        registerPage.getEmailField().sendKeys("test" + System.currentTimeMillis() + "@example.com");
        registerPage.getPasswordField().sendKeys("passw0rd");
        registerPage.getConfirmPasswordField().sendKeys("passw0rd");
    }
}