package money;

import inventory.Drink;
import static money.Currency.*;
import java.util.List;

public class MoneyTransaction {
    private final MoneyCollection moneyCollection;
    private final Refund refundProcessor;

    public MoneyTransaction(MoneyCollection moneyCollection) {
        this.moneyCollection = moneyCollection;
        this.refundProcessor = new Refund(moneyCollection);
    }

    /**
     * お金を投入する処理
     * @param money 投入するお金
     */
    public void insertMoney(Money money) {
        moneyCollection.addMoney(money);
        if (money.equals(new Money(ONE_THOUSAND))) System.out.println(money.amount() + "円札を投入しました ｳｨｰﾝ");
        else System.out.println(money.getAmount() + "円玉を投入しました ﾁｬﾘﾝ");
    }

    /**
     * 返金処理
     */
    public void refund() {
        List<Money> refundMoney = moneyCollection.refund();
        if (refundMoney.isEmpty()) {
            System.out.println("✨ありがとうございました✨");
            return;
        }

        System.out.println("返金額: " + moneyCollection.calTotalAmount() + "円");
        for (Money money : refundMoney) {
            if (money.equals(new Money(ONE_THOUSAND))) System.out.println(money.amount() + "円札 ｳｨｰﾝ");
            else System.out.println(money.getAmount() + "円玉 ﾁｬﾘﾝ");
        }
        System.out.println("✨ありがとうございました✨");
    }

    /**
     * 投入済み金額が，購入希望の飲み物の価格より大きいか判定
     * @param price 飲み物の値段
     * @return 飲み物を購入できる場合はtrue, できない場合はfalse
     */
    public boolean hasEnoughMoney(int price) {
        return moneyCollection.calTotalAmount() >= price;
    }

    /**
     * お釣りの計算処理
     * @param drink 購入希望の飲み物
     * @return お釣りが生成できる場合はtrue, できない場合はfalse
     */
    public boolean isCalculateRefund(Drink drink) {
        return refundProcessor.calculateRefund(drink);
    }
}
