package ru.netology.web.test;

import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {
    DashboardPage dashboardPage;
    @BeforeEach
    void shouldTransferMoneyBetweenOwnCardsV1() {
        open("http://localhost:9999");

        var loginPage = new LoginPage();
        var dashboardPage = new DashboardPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }
    @Test
    void transferMoneyFromFirstToSecondCard(){
        var firstCardInfo = DataHelper.getFirstCard();
        var secondCardInfo = DataHelper.getSecondCard();
        var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
        var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
        var amount = DataHelper.transferValidAmount(firstCardBalance);
        var expectedFirstCardBalance = firstCardBalance - amount;
        var expectedSecondCardBalance = secondCardBalance + amount;
        var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = transferPage.makeValidTransferPage(String.valueOf(amount), firstCardInfo);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        Assertions.assertEquals(expectedFirstCardBalance, actualBalanceFirstCard);
        Assertions.assertEquals(expectedSecondCardBalance, actualBalanceSecondCard);



    }


}
