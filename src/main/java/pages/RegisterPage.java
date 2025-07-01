package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By nameField = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//input[@type='password']");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginLink = By.xpath("//a[text()='Войти']");
    private final By passwordError = By.cssSelector(".input__error.text_type_main-default");

    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public RegisterPage enterName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
        return this;
    }

    public RegisterPage enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        return this;
    }

    public RegisterPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
        return this;
    }

    public LoginPage clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
        return new LoginPage(driver, wait);
    }

    public LoginPage clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        return new LoginPage(driver, wait);
    }

    public boolean isPasswordErrorVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}