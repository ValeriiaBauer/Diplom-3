package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By emailField = By.xpath("//input[@type='text']");
    private final By recoverButton = By.xpath("//button[text()='Восстановить']");
    private final By loginLink = By.xpath("//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public ForgotPasswordPage enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        return this;
    }

    public void clickRecoverButton() {
        wait.until(ExpectedConditions.elementToBeClickable(recoverButton)).click();
    }

    public LoginPage clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        return new LoginPage(driver, wait);
    }
}