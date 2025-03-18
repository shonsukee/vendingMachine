package money;

import inventory.Drink;
import static money.Currency.*;

import java.util.ArrayList;
import java.util.List;

// 投入金額を保持
public class MoneyCollection {
    private static final int MAX_CAPACITY = 20;
    private final List<Money> moneyList = new ArrayList<>();

    /**
     * 指定された {@code Money} オブジェクトをコレクションに追加する。
     *
     * <p>追加できる貨幣の最大枚数は {@code MAX_CAPACITY}（20枚）であり、
     * それを超える場合は {@code IllegalArgumentException} をスローする。</p>
     *
     * @param money 追加する {@code Money} オブジェクト
     * @throws IllegalArgumentException 貨幣の枚数が最大枚数を超えた場合
     */
    public void addMoney(Money money) {
        if (moneyList.size() >= MAX_CAPACITY) {
            throw new IllegalArgumentException("貨幣の最大枚数は20枚です。");
        } else {
            moneyList.add(money);
        }
    }

    /**
     * 購入時にお釣りを計算し、適切な金額の貨幣をリストに追加する。
     *
     * <p>このメソッドは、投入された合計金額から商品の価格を引いた後の残額を
     * 可能な限り大きな単位の貨幣でお釣りとして返すように計算する。</p>
     *
     * @param drink 購入する {@code Drink} オブジェクト（価格を取得）
     * @return 購入可能でお釣りを計算できた場合は {@code true}、残高不足の場合は {@code false}
     */
    public boolean calculateChange(Drink drink) {
        int totalAmount = calTotalAmount();
        int remainingAmount = totalAmount - drink.price();

        if (remainingAmount < 0) return false;

        moneyList.clear();

        // お釣りの計算
        Money[] availableMoney = {
            new Money(ONE_THOUSAND),
            new Money(FIVE_HUNDRED),
            new Money(ONE_HUNDRED),
            new Money(FIFTY),
            new Money(TEN)
        };
        for (Money money : availableMoney) {
            int amountRatio = remainingAmount / money.getAmount();
            remainingAmount -= amountRatio * money.getAmount();
            addMoneyList(money, amountRatio);
        }

        return remainingAmount == 0;
    }

    /**
     * 貨幣をお金リストに追加する。
     *
     * <p>このメソッドは、`moneyList`へmoneyをamount個分追加します。</p>
     *
     * @param money `moneyList`へ追加する {@code Money} オブジェクト
     * @param amountRatio {@code Money} オブジェクトを`moneyList`へ追加する数
     */
    private void addMoneyList(Money money, int amountRatio) {
        for (int i = 0; i < amountRatio; i++) {
            moneyList.add(money);
        }
    }

    /**
     * 現在コレクションに含まれているすべての貨幣の合計金額を計算する。
     *
     * <p>このメソッドは、コレクション内の {@code Money} オブジェクトの
     * 金額を合計して返す。</p>
     *
     * @return 現在の合計金額（整数値）
     */
    public int calTotalAmount() {
        return moneyList.stream().mapToInt(Money::getAmount).sum();
    }

    /**
     * 保持しているお金をすべて返し、リストを空にするメソッド
     *
     * <p>このメソッドを呼び出すと、現在 `moneyList` に格納されているすべての
     * {@code Money} オブジェクトを新しいリストとして返し、その後 `moneyList` をクリアする。</p>
     *
     * @return 保持していた {@code Money} オブジェクトのリスト（呼び出し後、元のリストは空になる）
     */
    public List<Money> change() {
        List<Money> change = new ArrayList<>(this.moneyList);
        this.moneyList.clear();

        return change;
    }
}