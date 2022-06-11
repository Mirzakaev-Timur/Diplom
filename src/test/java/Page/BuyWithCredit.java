package Page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BuyWithCredit {
    private SelenideElement heading = $$("h3").find(Condition.text("Кредит по данным карты"));
    private SelenideElement cardNumberField = $(byText("Номер карты")).parent().$(".input__control");
    private SelenideElement monthField = $(byText("Месяц")).parent().$(".input__control");
    private SelenideElement yearField = $(byText("Год")).parent().$(".input__control");
    private SelenideElement ownerField = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cvcField = $(byText("CVC/CVV")).parent().$(".input__control");
    private SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
    private SelenideElement statusOK = $(".notification_status_ok");
    private SelenideElement statusError = $(".notification_status_error");
    private SelenideElement inputInvalid = $(".input__sub");

    public BuyWithCredit() {
        heading.shouldBe(visible);
    }
    public void waitNotificationOk() {
        statusOK.shouldBe(visible, Duration.ofMillis(15000));
    }
    public void waitNotificationError() {
        statusError.shouldBe(visible, Duration.ofMillis(15000));
    }
    public void fillData(CardInfo card) {
        cardNumberField.setValue(card.getNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        ownerField.setValue(card.getName());
        cvcField.setValue(card.getCvc());
        continueButton.click();
    }
    public String getInputInvalid() {

        return inputInvalid.getText();
    }
}
