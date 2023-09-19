package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class applicationTest {
    @Test
    public void shouldTest() {
        open ("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79307500000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    public void shouldNegativeTestName() {
        open ("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иванов Ivan");
        $("[data-test-id=phone] input").setValue("+79307500000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    public void shouldNegativeTestPhone() {
        open ("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+7930750000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    public void shouldNegativeTestCheckbox() {
        open ("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79307500000");
        $("button").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
    @Test
    public void shouldNameFieldNotFilledIn() {
        open ("http://localhost:9999/");
        $("[data-test-id=phone] input").setValue("+79307500000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }
    @Test
    public void shouldPhoneFieldNotFilledIn() {
        open ("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    }

