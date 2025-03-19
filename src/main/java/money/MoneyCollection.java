package money;

import java.util.ArrayList;
import java.util.List;

public class MoneyCollection {
    private static final int MAX_CAPACITY = 20;
    private final List<Money> moneyList = new ArrayList<>();

    /**
     * 指定された {@code Money} オブジェクトをコレクションに追加する。
     * @param money 追加する {@code Money} オブジェクト
     * @throws IllegalArgumentException 貨幣の枚数が最大枚数を超えた場合
     */
    public void addMoney(Money money) {
        if (moneyList.size() >= MAX_CAPACITY) throw new IllegalArgumentException("貨幣の最大枚数は20枚です。");
        moneyList.add(money);
    }

    /**
     * 貨幣をお金リストに追加する。
     * @param money `moneyList`へ追加する {@code Money} オブジェクト
     * @param amountRatio {@code Money} オブジェクトを`moneyList`へ追加する数
     */
    void addMoneyList(Money money, int amountRatio) {
        for (int i = 0; i < amountRatio; i++) {
            moneyList.add(money);
        }
    }

    /**
     * 現在コレクションに含まれているすべての貨幣の合計金額を計算する。
     * @return 現在の合計金額（整数値）
     */
    public int calTotalAmount() {
        return moneyList.stream().mapToInt(Money::getAmount).sum();
    }

    /**
     * コレクションの初期化。
     */
    public void clear() {
        moneyList.clear();
    }

    /**
     * 保持しているお金をすべて返すメソッド
     * @return 保持していた {@code Money} オブジェクトのリスト
     */
    public List<Money> refund() {
        return new ArrayList<>(moneyList);
    }
}