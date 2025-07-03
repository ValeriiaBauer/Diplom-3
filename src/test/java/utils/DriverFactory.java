package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    public static WebDriver createDriver(String browserType) {
        String browser = browserType.toLowerCase();
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        if (browser.equals("yandex")) {
            return setupYandexDriver(options);
        }
        return new ChromeDriver(options);
    }

    private static WebDriver setupYandexDriver(ChromeOptions options) {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        return new ChromeDriver(options);
    }
}