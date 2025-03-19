package vendingMachine;

import inventory.Drink;
import inventory.Inventory;
import money.Money;
import money.MoneyTransaction;
import money.MoneyCollection;

public class VendingMachine {
    private final Inventory inventory;
    private final MoneyTransaction moneyTransaction;

    public VendingMachine(Inventory inventory, MoneyCollection moneyCollection) {
        this.inventory = inventory;
        this.moneyTransaction = new MoneyTransaction(moneyCollection);
        System.out.println("✨いらっしゃいませ✨");
    }

    /**
     * お金の入金処理呼び出し。
     * @param money 入金する貨幣
     */
    public void insertMoney(Money money) {
        moneyTransaction.insertMoney(money);
    }

    /**
     * お金の返金処理呼び出し。
     */
    public void refund() {
        moneyTransaction.refund();
    }

    /**
     * 指定された飲料を購入する処理を行う。
     * @param drink 購入する {@code Drink} オブジェクト
     * @return 購入成功の場合は {@code true}、失敗した場合は {@code}
     */
    public boolean purchase(Drink drink) {
        if (inventory.isDeadStock(drink)) return false;
        if (!moneyTransaction.hasEnoughMoney(drink.price())) {
            System.out.println("お金が不足しています。");
            return false;
        }
        if (!moneyTransaction.isCalculateRefund(drink)) {
            System.out.println("お釣りが生成できません。");
            return false;
        }
        if (!inventory.reduceInventory(drink)) {
            System.out.println("在庫がありません。");
            return false;
        }
        System.out.println("✨" + drink.name() + "のお買い上げありがとうございます✨");
        return true;
    }
}
