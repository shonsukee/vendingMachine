package money;

import inventory.Drink;
import static money.Currency.*;

public class Refund {
    private final MoneyCollection moneyCollection;

    public Refund(MoneyCollection moneyCollection) {
        this.moneyCollection = moneyCollection;
    }

    /**
     * 購入時にお釣りを計算し、適切な金額の貨幣をリストに追加する。
     * @param drink 購入する {@code Drink} オブジェクト（価格を取得）
     * @return 購入可能でお釣りを計算できた場合は {@code true}、残高不足の場合は {@code false}
     */
    public boolean calculateRefund(Drink drink) {
        int totalAmount = moneyCollection.calTotalAmount();
        int remainingAmount = totalAmount - drink.price();
        if (remainingAmount < 0) return false;
        moneyCollection.clear();

        // お釣りの計算
        Money[] availableMoney = {
                new Money(ONE_THOUSAND),
                new Money(FIVE_HUNDRED),
                new Money(ONE_HUNDRED),
                new Money(FIFTY),
                new Money(TEN)
        };
        for (Money money : availableMoney) {
            int number = remainingAmount / money.getAmount();
            remainingAmount -= number * money.getAmount();
            addRefundMoney(money, number);
        }
        return remainingAmount == 0;
    }

    /**
     * コレクションにお釣りを追加。
     * @param money 追加する貨幣の種類
     * @param number 追加する枚数
     */
    private void addRefundMoney(Money money, int number) {
        for (int i = 0; i < number; i++) {
            moneyCollection.addMoney(money);
        }
    }
}
