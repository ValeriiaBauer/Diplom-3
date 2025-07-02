package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By orderButton = By.xpath("//button[contains(text(), 'Оформить заказ')]");

    private final By bunsSection = By.xpath("//span[text()='Булки']/parent::div");
    private final By saucesSection = By.xpath("//span[text()='Соусы']/parent::div");
    private final By fillingsSection = By.xpath("//span[text()='Начинки']/parent::div");

    private final By activeTab = By.xpath("//div[contains(@class, 'tab_tab_type_current')]");

    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public MainPage open() {
        driver.get("https://stellarburgers.nomoreparties.site");
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton));
        return this;
    }

    public LoginPage clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return new LoginPage(driver, wait);
    }

    public LoginPage clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
        return new LoginPage(driver, wait);
    }

    public boolean isOrderButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton)).isDisplayed();
    }

    public MainPage clickBunsSection() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsSection)).click();
        return this;
    }

    public MainPage clickSaucesSection() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesSection)).click();
        return this;
    }

    public MainPage clickFillingsSection() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsSection)).click();
        return this;
    }

    public boolean isBunsSectionActive() {
        return isSectionActive("Булки");
    }

    public boolean isSaucesSectionActive() {
        return isSectionActive("Соусы");
    }

    public boolean isFillingsSectionActive() {
        return isSectionActive("Начинки");
    }

    private boolean isSectionActive(String sectionName) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activeTab))
                .findElement(By.xpath(".//span")).getText().equals(sectionName);
    }
}