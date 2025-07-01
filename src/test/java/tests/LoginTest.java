package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.MainPage;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class LoginTest extends BaseTest {
    private final String email = "test@example.com";
    private final String password = "password123";

    public LoginTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "Browser: {0}")
    public static Collection<Object[]> browsers() {
        return Arrays.asList(new Object[][]{
                {"chrome"},
                {"yandex"}
        });
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной")
    public void loginViaMainPageButtonTest() {
        boolean isLoggedIn = new MainPage(driver, wait)
                .open()
                .clickLoginButton()
                .enterEmail(email)
                .enterPassword(password)
                .clickLoginButton()
                .isOrderButtonVisible();

        assertTrue("Кнопка 'Оформить заказ' не отобразилась после входа", isLoggedIn);
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    public void loginViaPersonalAccountButtonTest() {
        boolean isLoggedIn = new MainPage(driver, wait)
                .open()
                .clickPersonalAccountButton()
                .enterEmail(email)
                .enterPassword(password)
                .clickLoginButton()
                .isOrderButtonVisible();

        assertTrue("Вход через личный кабинет не выполнен", isLoggedIn);
    }

    @Test
    @DisplayName("Вход через форму регистрации")
    public void loginViaRegisterFormTest() {
        boolean isLoggedIn = new MainPage(driver, wait)
                .open()
                .clickPersonalAccountButton()
                .clickRegisterLink()
                .clickLoginLink()
                .enterEmail(email)
                .enterPassword(password)
                .clickLoginButton()
                .isOrderButtonVisible();

        assertTrue("Вход через форму регистрации не выполнен", isLoggedIn);
    }
}