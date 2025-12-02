import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class PageObject {

    SelenideElement inputCity = $(".q-field__input.q-placeholder.col"),
            buttonOfStorageRoom = $(".col-auto.q-pl-xs.location-select__luggage-button__col-sm"),
            calendar = $("div.gt-xs div div:nth-child(2) span.q-btn__content"),
            calendarDay = $("div.q-date__calendar-days.fit"),
            submitButton = $("div.col-12 button.q-btn.q-btn-item.non-selectable.no-outline.q-btn--unelevated.q-btn--rectangle.bg-accent"),
            zabronirovatButton = $("div.col-0 div.col-auto button"),
            sbpButton = $("div.sbp-button div.col button");
    ElementsCollection bookButtons = $$(".q-responsive.company-card.q-mb-md");

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("/ru");
        getWebDriver().manage().window().maximize();
    }

    @Step("В поле поиска вводим {CityName}")
    public void inputCityName(String CityName) {
        inputCity.shouldBe(Condition.visible, Duration.ofSeconds(10))
                .setValue(CityName)
                .click();
        Selenide.sleep(2000);
    }

    @Step("Выбираем первый результат из выпадающего списка")
    public void selectFirstResult() {
        inputCity.press(Keys.ARROW_DOWN).press(Keys.ENTER);
    }

    @Step("Нажимаем на кнопку 'Найти камеру хранения'")
    public void pressbuttonOfStorageRoom() {
        buttonOfStorageRoom.shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldBe(Condition.enabled, Duration.ofSeconds(10))
                .click();
        Selenide.sleep(3000);
    }

    @Step("Нажимаем на календарь")
    public void calendarClick() {
        calendar.click();
    }

    @Step("Выбираем день бронирования")
    public void calendarDayChoise(String day) {
        Selenide.sleep(1000);
        calendarDay.$$("div.q-date__calendar-item")
                .findBy(Condition.text(day))
                .shouldNotHave(Condition.cssClass("disabled"))
                .click();
    }

    @Step("Нажимаем на кнопку 'Подтвердить' в поп-апе выбора дат")
    public void submitButtonClick() {
        submitButton.click();
    }

    @Step("Нажимаем на кнопку 'Забронировать' в карточке камеры хранения")
    public void bookButtonClick() {
        zabronirovatButton.click();
    }

    @Step("Проверка: кнопка 'Забронировать через СБП активна'")
    public void buttonSBPActive() {
        sbpButton.shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldNotHave(Condition.attribute("disabled"))
                .shouldNotHave(Condition.cssClass("disabled"))
                .shouldBe(Condition.interactable);
    }

    @Step("Выбираем {element} камеру хранения в меню справа")
    public void bookChoise(int element) {
        Selenide.sleep(2000);
        bookButtons.get(element).doubleClick();
    }

}
