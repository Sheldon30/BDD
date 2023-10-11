package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement Transferhead = $(byText("Пополнение карты"));
    private final SelenideElement amountInput = $("[data-test-id='amount'] input");
    private final SelenideElement fromTransfer = $("[data-test-id='from'] input");
    private final SelenideElement button = $("[data-test-id='action-transfer']");
    private final SelenideElement error = $("[data-test-id='error-notification']");

    public TransferPage() {
        Transferhead.shouldBe(visible);
    }
    public void transferMoney(String amountToTransfer, DataHelper.CardInfo cardInfo){
        amountInput.setValue(amountToTransfer);
        fromTransfer.setValue(cardInfo.getCardNumber());
        button.click();
    }
    public DashboardPage makeValidTransferPage(String amountToTransfer, DataHelper.CardInfo cardInfo){
        transferMoney(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

}
