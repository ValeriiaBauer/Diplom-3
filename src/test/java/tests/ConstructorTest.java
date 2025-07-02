package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.MainPage;
import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу Булки")
    public void navigateToBunsSectionTest() {
        boolean isActive = new MainPage(driver, wait)
                .open()
                .clickSaucesSection()
                .clickBunsSection()
                .isBunsSectionActive();

        assertTrue("Раздел Булки не активирован", isActive);
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    public void navigateToSaucesSectionTest() {
        boolean isActive = new MainPage(driver, wait)
                .open()
                .clickSaucesSection()
                .isSaucesSectionActive();

        assertTrue("Раздел Соусы не активирован", isActive);
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    public void navigateToFillingsSectionTest() {
        boolean isActive = new MainPage(driver, wait)
                .open()
                .clickFillingsSection()
                .isFillingsSectionActive();

        assertTrue("Раздел Начинки не активирован", isActive);
    }
}