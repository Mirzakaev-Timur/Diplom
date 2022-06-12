package tests;

import Page.BuyWithCredit;
import Page.StartPage;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQL;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestCreditCard {
    @BeforeAll
    static void setUpAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUpEach() {
        String url = System.getProperty("sut.url");
        open(url);
        SQL.clearData();
    }

    @AfterAll
    static void tearDownAll() {

        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldCreditByCardWithStatusApproved() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getApprovedCard());
        buyWithCredit.waitNotificationOk();
       }

    @Test
    void shouldCreditByCardWithStatusDecline() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getDeclinedCard());
        buyWithCredit.waitNotificationError();
    }

    @Test
    void shouldShortNameInOwnerApproved() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getShortNameInOwnerApprovedCard());
        buyWithCredit.waitNotificationOk();
    }

    @Test
    void shouldShortNameInOwnerDeclined() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getShortNameInOwnerDeclinedCard());
        buyWithCredit.waitNotificationError();
    }

    @Test
    void shouldInvalidFieldMessageEmptyForm() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getEmptyForm());
        buyWithCredit.getInputInvalid();
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageInvalidMonthApproved() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getInvalidMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageInvalidMonthDeclined() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getInvalidMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCredit.getInputInvalid());
    }
     @Test
    void shouldInvalidFieldMessageBygoneMonthApproved() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getBygoneMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneMonthDeclined() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getBygoneMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageIncompleteField() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getIncompleteField());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldCharactersInFieldOwnerApproved() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getCharactersInFieldOwnerApprovedCard());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldCharactersInFieldOwnerDeclined() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getCharactersInFieldOwnerDeclinedCard());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldOneCharacterInFieldOwnerApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getOneCharacterInFieldOwnerApprovedCard());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldOneCharacterInFieldOwnerDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getOneCharacterInFieldOwnerDeclinedCard());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneYearApprovedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getBygoneYearApprovedCard());
        assertEquals("Истёк срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneYearDeclinedCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCredit();
        buyWithCredit.fillData(DataHelper.getBygoneYearDeclinedCard());
        assertEquals("Истёк срок действия карты", buyWithCredit.getInputInvalid());
    }
}

