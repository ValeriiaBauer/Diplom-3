package steps;

import io.qameta.allure.Step;
import pages.ForgotPasswordPage;
import pages.LoginPage;

public class ForgotPasswordSteps {
    private final ForgotPasswordPage forgotPasswordPage;

    public ForgotPasswordSteps(ForgotPasswordPage forgotPasswordPage) {
        this.forgotPasswordPage = forgotPasswordPage;
    }

    @Step("Ввести email для восстановления пароля")
    public ForgotPasswordSteps enterEmail(String email) {
        forgotPasswordPage.enterEmail(email);
        return this;
    }

    @Step("Нажать кнопку Восстановить")
    public void clickRecoverButton() {
        forgotPasswordPage.clickRecoverButton();
    }

    @Step("Нажать ссылку Войти")
    public LoginPage clickLoginLink() {
        return forgotPasswordPage.clickLoginLink();
    }
}