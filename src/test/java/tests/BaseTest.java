package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.UUID;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String browser;

    @Rule
    public TestRule screenshotRule = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            takeScreenshot(description.getMethodName());
        }
    };

    @Before
    public void setUp() {
        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }
        switch (browser.toLowerCase()) {
            case "yandex":
                initYandexBrowser();
                break;
            case "chrome":
            default:
                initChromeBrowser();
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
    }

    private void initChromeBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--remote-allow-origins=*",
                "--start-maximized",
                "--disable-infobars",
                "--disable-extensions",
                "--no-sandbox",
                "--disable-dev-shm-usage"
        );
        driver = new ChromeDriver(options);
    }

    private void initYandexBrowser() {
        String yandexPath = "/Applications/Yandex.app/Contents/MacOS/Yandex";
        if (!new File(yandexPath).exists()) {
            throw new RuntimeException("Yandex Browser not found at: " + yandexPath);
        }

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary(yandexPath);
        options.addArguments(
                "--remote-allow-origins=*",
                "--start-maximized",
                "--disable-infobars"
        );
        driver = new ChromeDriver(options);
    }

    protected void takeScreenshot(String testName) {
        try {
            Path screenshotsDir = Paths.get("target/screenshots");
            if (!Files.exists(screenshotsDir)) {
                Files.createDirectories(screenshotsDir);
            }

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fileName = testName + "_" + UUID.randomUUID() + ".png";
            Files.copy(screenshot.toPath(), screenshotsDir.resolve(fileName));
        } catch (IOException e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}