import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Selenide.*;

public class Uitests {

    @BeforeAll
    static void setup() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://qeepl.com";
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        clearBrowserCookies();
        clearBrowserLocalStorage();
        closeWebDriver();
    }

    @Test
    @DisplayName("UI Тест")
    @Owner("AlanDzhigkaev")
    public void uiTest() {
        PageObject steps = new PageObject();

        steps.openMainPage();
        steps.inputCityName("Москва");
        steps.selectFirstResult();
        steps.pressbuttonOfStorageRoom();
        steps.calendarClick();
        steps.calendarDayChoise("20");
        steps.calendarDayChoise("31");
        steps.submitButtonClick();
        steps.bookChoise(1);
        steps.bookButtonClick();
        steps.buttonSBPActive();
    }

    @ParameterizedTest(name = "UI тест параметризованный. Старотовый день = {0}, конечный день {1}")
    @CsvFileSource(resources = "TestData.csv")
    @Owner("AlanDzhigkaev")
    public void uiParametrizedTest(String day1, String day2) {
        PageObject steps = new PageObject();

        steps.openMainPage();
        steps.inputCityName("Москва");
        steps.selectFirstResult();
        steps.pressbuttonOfStorageRoom();
        steps.calendarClick();
        steps.calendarDayChoise(day1);
        steps.calendarDayChoise(day2);
        steps.submitButtonClick();
        steps.bookChoise(1);
        steps.bookButtonClick();
        steps.buttonSBPActive();
    }
}
