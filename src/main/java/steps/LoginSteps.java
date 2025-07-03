package steps;

import io.qameta.allure.Step;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

public class LoginSteps {
    private final LoginPage loginPage;

    public LoginSteps(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    @Step("Ввести email")
    public LoginSteps enterEmail(String email) {
        loginPage.enterEmail(email);
        return this;
    }

    @Step("Ввести пароль")
    public LoginSteps enterPassword(String password) {
        loginPage.enterPassword(password);
        return this;
    }

    @Step("Нажать кнопку Войти")
    public MainPage clickLoginButton() {
        return loginPage.clickLoginButton();
    }

    @Step("Нажать ссылку Зарегистрироваться")
    public RegisterPage clickRegisterLink() {
        return loginPage.clickRegisterLink();
    }

    @Step("Нажать ссылку Восстановить пароль")
    public ForgotPasswordPage clickForgotPasswordLink() {
        return loginPage.clickForgotPasswordLink();
    }

    @Step("Нажать кнопку входа с ожиданием ошибки")
    public LoginSteps clickLoginButtonWithError() {
        loginPage.clickLoginButtonWithError();
        return this;
    }

    @Step("Проверить отображение ошибки")
    public boolean isErrorDisplayed() {
        return loginPage.isErrorDisplayed();
    }

    @Step("Проверить видимость формы входа")
    public boolean isLoginFormVisible() {
        return loginPage.isLoginFormVisible();
    }
}