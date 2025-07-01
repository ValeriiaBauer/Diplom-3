package pages;

package ru.stellarburgers.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    // Локаторы
    private final By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By orderButton = By.xpath("//button[text()='Оформить заказ']");
    private final By bunsSection = By.xpath("//span[text()='Булки']/..");
    private final By saucesSection = By.xpath("//span[text()='Соусы']/..");
    private final By fillingsSection = By.xpath("//span[text()='Начинки']/..");
    private final By activeSection = By.xpath("//div[contains(@class, 'tab_tab_type_current')]");
    private final By bunsSectionTitle = By.xpath("//h2[text()='Булки']");
    private final By saucesSectionTitle = By.xpath("//h2[text()='Соусы']");
    private final By fillingsSectionTitle = By.xpath("//h2[text()='Начинки']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage open() {
        driver.get("https://stellarburgers.nomoreparties.site");
        return this;
    }

    public MainPage clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
        return this;
    }

    public MainPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return this;
    }

    public MainPage clickBunsSection() {
        driver.findElement(bunsSection).click();
        return this;
    }

    public MainPage clickSaucesSection() {
        driver.findElement(saucesSection).click();
        return this;
    }

    public MainPage clickFillingsSection() {
        driver.findElement(fillingsSection).click();
        return this;
    }

    public boolean checkOrderButtonVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderButton));
        return driver.findElement(orderButton).isDisplayed();
    }

    public boolean checkBunsSectionActive() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(bunsSectionTitle));
        String activeClass = driver.findElement(activeSection).getAttribute("class");
        return activeClass.contains("tab_tab_type_current") &&
                driver.findElement(bunsSectionTitle).isDisplayed();
    }

    public boolean checkSaucesSectionActive() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(saucesSectionTitle));
        String activeClass = driver.findElement(activeSection).getAttribute("class");
        return activeClass.contains("tab_tab_type_current") &&
                driver.findElement(saucesSectionTitle).isDisplayed();
    }

    public boolean checkFillingsSectionActive() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingsSectionTitle));
        String activeClass = driver.findElement(activeSection).getAttribute("class");
        return activeClass.contains("tab_tab_type_current") &&
                driver.findElement(fillingsSectionTitle).isDisplayed();
    }
}