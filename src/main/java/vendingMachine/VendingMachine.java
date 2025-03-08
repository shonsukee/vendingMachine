package vendingMachine;

import inventory.*;
import money.Money;
import money.MoneyCollection;

import java.util.List;

public class VendingMachine {
    private final MoneyCollection moneyCollection;
    private final Inventory inventory;

    public VendingMachine(Inventory inventory, MoneyCollection moneyCollection) {
        this.inventory = inventory;
        this.moneyCollection = moneyCollection;
        System.out.println("✨いらっしゃいませ✨");
    }

    /**
     * 指定された飲料を購入する処理を行う。
     *
     * <p>このメソッドは、以下の条件を満たす場合に購入を成立させる：</p>
     * <ul>
     *   <li>在庫がある（デッドストックではない）</li>
     *   <li>投入金額が商品の価格以上である</li>
     *   <li>お釣りが正しく計算できる</li>
     *   <li>在庫を1つ減らす処理が成功する</li>
     * </ul>
     *
     * <p>いずれかの条件を満たさない場合、購入は失敗し {@code false} を返す。</p>
     *
     * @param drink 購入する {@code Drink} オブジェクト
     * @return 購入成功の場合は {@code true}、失敗した場合は {@code}
     */
    public boolean purchase(Drink drink) {
        if (inventory.isDeadStock(drink)) return false;
        if (drink.price() > moneyCollection.getTotalAmount()) return false;

        if (!moneyCollection.calculateChange(drink)) {
            System.out.println("お金が不足しています");
            return false;
        }

        if (!inventory.reduceInventory(drink)) {
            System.out.println("在庫がありません");
            return false;
        }

        System.out.println("✨" + drink.name() + "のお買い上げありがとうございます✨");
        return true;
    }

    /**
     * お金を投入する処理
     *
     * @param money 投入するお金
     */
    public void insertMoney(Money money) {
        moneyCollection.addMoney(money);
        if (money.equals(new Money(1000))) System.out.println(money.getAmount() + "円札を投入しました ｳｨｰﾝ");
        else System.out.println(money.getAmount() + "円玉を投入しました ﾁｬﾘﾝ");
    }

    /**
     * 返金処理
     */
    public void refund() {
        if (moneyCollection.getTotalAmount() <= 0) {
            System.out.println("✨ありがとうございました✨");
            return;
        }

        System.out.println("返金額: " + moneyCollection.getTotalAmount() + "円");
        List<Money> change = moneyCollection.change();
        for (Money money : change) {
            if (money.equals(new Money(1000))) System.out.println(money.getAmount() + "円札 ｳｨｰﾝ");
            else System.out.println(money.getAmount() + "円玉 ﾁｬﾘﾝ");
        }
        System.out.println("✨ありがとうございました✨");
    }
}
