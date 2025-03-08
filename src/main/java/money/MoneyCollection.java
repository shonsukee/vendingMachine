package money;

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
        if(moneyList.size() >= MAX_CAPACITY){
            throw new IllegalArgumentException("貨幣の最大枚数は20枚です");
        }else{
            moneyList.add(money);
        }
    }

    /**
     * 現在コレクションに含まれているすべての貨幣の合計金額を取得する。
     *
     * <p>このメソッドは、コレクション内の {@code Money} オブジェクトの
     * 金額を合計して返す。</p>
     *
     * @return 現在の合計金額（整数値）
     */
    public int getTotalAmount() {
        return moneyList.stream().mapToInt(val -> val.amount).sum();
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