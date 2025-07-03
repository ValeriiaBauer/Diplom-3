package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import client.UserClient;
import model.User;
import static org.junit.Assert.assertTrue;

@Epic("Регистрация пользователя")
@Feature("Функционал регистрации")
public class RegistrationTest extends BaseTest {
    private User testUser;
    private UserClient userClient;

    @Before
    @Step("Подготовка тестовых данных")
    public void setUp() {
        super.setUp();
        userClient = new UserClient();
        testUser = User.getRandomUser();
    }

    @After
    @Step("Очистка тестовых данных")
    public void tearDown() {
        try {
            if (testUser != null) {
                String token = userClient.login(testUser).path("accessToken");
                if (token != null) {
                    userClient.delete(token);
                }
            }
        } catch (Exception e) {
            Allure.addAttachment("Ошибка при очистке", "text/plain",
                    "Не удалось удалить тестового пользователя: " + e.getMessage());
        }
        super.tearDown();
    }

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    @Description("Проверка успешной регистрации с валидными данными")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Позитивный сценарий регистрации")
    public void successfulRegistrationTest() {
        Allure.step("Открываем главную страницу", () -> {
            MainPage mainPage = new MainPage(driver, wait).open();
            LoginPage loginPage = mainPage.clickPersonalAccountButton();
            RegisterPage registerPage = loginPage.clickRegisterLink();

            Allure.step("Заполняем форму регистрации", () -> {
                registerPage.enterName(testUser.getName())
                        .enterEmail(testUser.getEmail())
                        .enterPassword(testUser.getPassword());
            });

            Allure.step("Нажимаем кнопку регистрации", () -> {
                LoginPage returnedLoginPage = registerPage.clickRegisterButton();
                assertTrue("Форма входа не отобразилась после регистрации",
                        returnedLoginPage.isLoginFormVisible());
            });
        });

        Allure.addAttachment("Данные пользователя", "text/plain",
                String.format("Email: %s\nPassword: %s\nName: %s",
                        testUser.getEmail(), testUser.getPassword(), testUser.getName()));
    }

    @Test
    @DisplayName("Ошибка при регистрации с некорректным паролем")
    @Description("Проверка валидации пароля при регистрации")
    @Severity(SeverityLevel.NORMAL)
    @Story("Негативный сценарий регистрации")
    public void registrationWithShortPasswordTest() {
        User invalidUser = new User(
                "testuser" + System.currentTimeMillis() + "@example.com",
                "12345",
                "Test User"
        );

        Allure.step("Открываем страницу регистрации", () -> {
            MainPage mainPage = new MainPage(driver, wait).open();
            LoginPage loginPage = mainPage.clickPersonalAccountButton();
            RegisterPage registerPage = loginPage.clickRegisterLink();

            Allure.step("Заполняем форму с коротким паролем", () -> {
                registerPage.enterName(invalidUser.getName())
                        .enterEmail(invalidUser.getEmail())
                        .enterPassword(invalidUser.getPassword());
            });

            Allure.step("Проверяем сообщение об ошибке", () -> {
                assertTrue("Ошибка о коротком пароле не отобразилась",
                        registerPage.isPasswordErrorVisible());
            });

            Allure.addAttachment("Ожидаемая ошибка", "Пароль должен быть не менее 6 символов");
        });
    }
}