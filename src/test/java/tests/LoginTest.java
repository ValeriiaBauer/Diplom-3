package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;
import client.UserClient;
import model.User;

import static org.junit.Assert.*;

@Epic("Авторизация пользователя")
@Feature("Функционал входа в систему")
public class LoginTest extends BaseTest {
    private User testUser;
    private UserClient userClient;

    @Before
    @Step("Подготовка тестовых данных")
    public void setUp() {
        super.setUp();
        userClient = new UserClient();
        testUser = User.getRandomUser();

        Response createResponse = userClient.create(testUser);
        assertTrue("Не удалось создать тестового пользователя",
                createResponse.statusCode() == 200 &&
                        Boolean.TRUE.equals(createResponse.path("success")));

        Allure.addAttachment("Созданный пользователь", "text/plain",
                String.format("Email: %s\nPassword: %s",
                        testUser.getEmail(), testUser.getPassword()));
    }

    @After
    @Step("Очистка тестовых данных")
    public void tearDown() {
        try {
            if (testUser != null) {
                Response loginResponse = userClient.login(testUser);
                String token = loginResponse.path("accessToken");

                if (token != null && !token.isEmpty()) {
                    Response deleteResponse = userClient.delete(token);
                    assertTrue("Не удалось удалить пользователя",
                            deleteResponse.statusCode() == 202);
                }
            }
        } catch (Exception e) {
            Allure.addAttachment("Ошибка очистки",
                    "text/plain",
                    "Не удалось удалить тестового пользователя: " + e.getMessage());
        } finally {
            super.tearDown();
        }
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной")
    @Description("Проверка успешного входа при использовании кнопки на главной странице")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Позитивный сценарий авторизации")
    public void loginViaMainPageButtonTest() {
        boolean isLoggedIn = new MainPage(driver, wait)
                .open()
                .clickLoginButton()
                .enterEmail(testUser.getEmail())
                .enterPassword(testUser.getPassword())
                .clickLoginButton()
                .isOrderButtonVisible();

        assertTrue("Кнопка 'Оформить заказ' не отобразилась после входа", isLoggedIn);
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверка успешного входа через кнопку личного кабинета")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Позитивный сценарий авторизации")
    public void loginViaPersonalAccountButtonTest() {
        boolean isLoggedIn = new MainPage(driver, wait)
                .open()
                .clickPersonalAccountButton()
                .enterEmail(testUser.getEmail())
                .enterPassword(testUser.getPassword())
                .clickLoginButton()
                .isOrderButtonVisible();

        assertTrue("Вход через личный кабинет не выполнен", isLoggedIn);
    }

    @Test
    @DisplayName("Вход через форму регистрации")
    @Description("Проверка перехода к авторизации со страницы регистрации")
    @Severity(SeverityLevel.NORMAL)
    @Story("Позитивный сценарий авторизации")
    public void loginViaRegisterFormTest() {
        boolean isLoggedIn = new MainPage(driver, wait)
                .open()
                .clickPersonalAccountButton()
                .clickRegisterLink()
                .clickLoginLink()
                .enterEmail(testUser.getEmail())
                .enterPassword(testUser.getPassword())
                .clickLoginButton()
                .isOrderButtonVisible();

        assertTrue("Вход через форму регистрации не выполнен", isLoggedIn);
    }

    @Test
    @DisplayName("Попытка входа с неверным паролем")
    @Description("Проверка отображения ошибки при неверном пароле")
    @Severity(SeverityLevel.NORMAL)
    @Story("Негативный сценарий авторизации")
    public void loginWithWrongPasswordTest() {
        boolean isErrorDisplayed = new MainPage(driver, wait)
                .open()
                .clickLoginButton()
                .enterEmail(testUser.getEmail())
                .enterPassword("wrong_password")
                .clickLoginButtonWithError()
                .isErrorDisplayed();

        assertTrue("Сообщение об ошибке не отобразилось", isErrorDisplayed);
    }
}