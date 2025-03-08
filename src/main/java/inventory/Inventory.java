package inventory;

import java.util.HashMap;
import java.util.Map;

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
    public boolean addDrink(Drink drink, int quantity) {
        if (getTotalQuantity() + quantity > MAX_STOCK) return false;
        if (quantity <= 0) return false;

        // 登録済みのDrink
        if (inventory.containsKey(drink)) {
            inventory.put(drink, getQuantity(drink) + quantity);
        }
        // 未登録のDrink
        else {
            inventory.put(drink, quantity);
        }
        return true;
    }

    /**
     * 指定した飲料が在庫にあるかを判定する。
     *
     * @param drink 在庫を確認する {@code Drink} オブジェクト
     * @return 在庫が1以上ある場合は {@code true}、それ以外は {@code false}
     */
    public boolean hasStock(Drink drink) {
        return getQuantity(drink) > 0;
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
}
