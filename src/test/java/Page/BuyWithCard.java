package Page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.CardInfo;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class BuyWithCard {
    private SelenideElement heading = $$("h3").find(Condition.text("Оплата по карте"));
    private final SelenideElement numberCard = $(By.linkText("Номер карты"));
    private final SelenideElement monthRelease = $(By.linkText("Месяц"));
    private final SelenideElement yearRelease = $(By.linkText("Год"));
    private final SelenideElement owner = $(By.linkText("Владелец"));
    private final SelenideElement codeSecurity = $(By.linkText("CVC/CVV"));
    private final SelenideElement button = $$("button").find(exactText("Продолжить"));

    private final SelenideElement statusOk = $(".notification_status_ok");
    private final SelenideElement statusError = $(".notification_status_error");
    private SelenideElement inputInvalid = $(".input__sub");

    public void BuyWithCredit() {
        heading.shouldBe(visible);
    }
    public void waitNotificationOk() {
        statusOk.shouldBe(visible, Duration.ofMillis(15000));
    }
    public void waitNotificationError() {
        statusError.shouldBe(visible, Duration.ofMillis(15000));
    }
    public void fillData(CardInfo card) {
        numberCard.setValue(card.getNumber());
        monthRelease.setValue(card.getMonth());
        yearRelease.setValue(card.getYear());
        owner.setValue(card.getName());
        codeSecurity.setValue(card.getCvc());
        button.click();
    }
    public String getInputInvalid() {

        return inputInvalid.getText();
    }
}


