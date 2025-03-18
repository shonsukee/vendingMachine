package inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// 在庫を管理
public class Inventory {
    private static final int MAX_STOCK = 200;
    private final Map<Drink, Integer> inventory = new HashMap<>();

    /**
     * 指定した飲料を在庫に追加する。
     *
     * <p>追加後の在庫数が {@code MAX_STOCK} を超える場合は追加できない。</p>
     *
     * @param drink 追加する {@code Drink} オブジェクト
     * @param quantity 追加する数量
     * @return 追加が成功した場合は {@code true}、在庫制限を超える場合は {@code false}
     */
    public boolean addInventory(Drink drink, int quantity) {
        if (getTotalQuantity() + quantity > MAX_STOCK) return false;
        if (quantity <= 0) return false;

        if (inventory.containsKey(drink)) {
            inventory.put(drink, getQuantity(drink) + quantity);
        } else {
            inventory.put(drink, quantity);
        }
        return true;
    }

    /**
     * 指定された飲料の在庫を1つ減らす。
     *
     * <p>このメソッドは、指定された {@code Drink} の在庫を1つ減らし、
     * 在庫がない場合は {@code false} を返して処理を行わない。</p>
     *
     * @param drink 在庫を減らす対象の {@code Drink} オブジェクト
     * @return 在庫が1つ以上あり減らせた場合は {@code true}、在庫がない場合は {@code false}
     */
    public boolean reduceInventory(Drink drink) {
        if (getQuantity(drink) <= 0) return false;
        inventory.put(drink, getQuantity(drink) - 1);
        return true;
    }

    /**
     * 指定した飲料が在庫にあるかを判定する。
     *
     * @param drink 在庫を確認する {@code Drink} オブジェクト
     * @return 在庫がない場合は {@code true}、在庫がある場合は {@code false}
     */
    public boolean isDeadStock(Drink drink) {
        return getQuantity(drink) <= 0;
    }

    /**
     * 指定した飲料の在庫数を取得する。
     *
     * @param drink 在庫数を取得する {@code Drink} オブジェクト
     * @return 指定した飲料の在庫数（在庫がない場合は {@code 0}）
     */
    public int getQuantity(Drink drink) {
        return inventory.getOrDefault(drink, 0);
    }

    /**
     * 全体の在庫数を取得する。
     *
     * @return 現在の在庫合計数
     */
    private int getTotalQuantity() {
        int total = 0;
        for (Map.Entry<Drink, Integer> entry : inventory.entrySet()) {
            total += entry.getValue();
        }
        return total;
    }

    /**
     * 商品一覧を表示する。
     */
    public void displayProducts() {
        int idx = 1;
        for (Map.Entry<Drink, Integer> entry : inventory.entrySet()) {
            if(isDeadStock(entry.getKey())) continue;
            System.out.println("【" + idx + "】" + entry.getKey().name() + ": ¥" + entry.getKey().price());
            idx++;
        }
    }

    /**
     * 商品番号を指定してドリンクを検索する。
     *
     * @param drinkNumber 検索する商品の番号。
     * @return 該当する Drink の Optional。
     */
    public Optional<Drink> searchDrink(int drinkNumber) {
        Drink foundDrink = findDrinkByNumber(drinkNumber);
        return Optional.ofNullable(foundDrink);
    }

    /**
     * 指定された番号のドリンクを見つける。
     *
     * @param drinkNumber 検索する商品の番号。
     * @return 該当する Drink（見つからなければ null）。
     */
    private Drink findDrinkByNumber(int drinkNumber) {
        int idx = 1;
        for (Map.Entry<Drink, Integer> entry : inventory.entrySet()) {
            if (isDeadStock(entry.getKey())) continue;
            if (idx == drinkNumber) return entry.getKey();
            idx++;
        }
        return null;
    }
}
