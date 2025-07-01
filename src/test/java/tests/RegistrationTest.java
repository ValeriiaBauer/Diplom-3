package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class RegistrationTest extends BaseTest {

    public RegistrationTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "{0} browser")
    public static Collection<Object[]> browsers() {
        return Arrays.asList(new Object[][]{
                {"chrome"},
                {"yandex"}
        });
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void successfulRegistrationTest() {
        String email = "testuser" + System.currentTimeMillis() + "@example.com";
        String password = "password123";
        String name = "Test User";

        MainPage mainPage = new MainPage(driver, wait);
        LoginPage loginPage = mainPage.open()
                .clickPersonalAccountButton();

        RegisterPage registerPage = loginPage.clickRegisterLink();

        LoginPage returnedLoginPage = registerPage.enterName(name)
                .enterEmail(email)
                .enterPassword(password)
                .clickRegisterButton();

        assertTrue("Форма входа не отобразилась после регистрации",
                returnedLoginPage.isLoginFormVisible());
    }

    @Test
    @DisplayName("Ошибка при регистрации с некорректным паролем")
    public void registrationWithShortPasswordTest() {
        String email = "testuser" + System.currentTimeMillis() + "@example.com";
        String password = "12345";
        String name = "Test User";

        MainPage mainPage = new MainPage(driver, wait);
        LoginPage loginPage = mainPage.open()
                .clickPersonalAccountButton();

        RegisterPage registerPage = loginPage.clickRegisterLink();

        registerPage.enterName(name)
                .enterEmail(email)
                .enterPassword(password);

        assertTrue("Ошибка о коротком пароле не отобразилась",
                registerPage.isPasswordErrorVisible());
    }
}