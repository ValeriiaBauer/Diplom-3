package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.MainPage;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ConstructorTest extends BaseTest {

    public ConstructorTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "Browser: {0}")
    public static Collection<Object[]> browsers() {
        return Arrays.asList(new Object[][]{
                {"chrome"},
                {"yandex"}
        });
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void navigateToBunsSectionTest() {
        MainPage mainPage = new MainPage(driver, wait);

        boolean isActive = mainPage.open()
                .clickSaucesSection()
                .clickBunsSection()
                .isBunsSectionActive();

        assertTrue("Раздел 'Булки' не активирован", isActive);
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void navigateToSaucesSectionTest() {
        MainPage mainPage = new MainPage(driver, wait);

        boolean isActive = mainPage.open()
                .clickSaucesSection()
                .isSaucesSectionActive();

        assertTrue("Раздел 'Соусы' не активирован", isActive);
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void navigateToFillingsSectionTest() {
        MainPage mainPage = new MainPage(driver, wait);

        boolean isActive = mainPage.open()
                .clickFillingsSection()
                .isFillingsSectionActive();

        assertTrue("Раздел 'Начинки' не активирован", isActive);
    }
}