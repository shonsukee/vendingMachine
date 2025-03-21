package inventory;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Inventory {
    private static final int MAX_STOCK = 200;
    private final Map<Drink, Integer> inventory = new HashMap<>();

    /**
     * 指定した飲料を在庫に追加する。
     * @param drink 追加する {@code Drink} オブジェクト
     * @param quantity 追加する数量
     * @return 追加が成功した場合は {@code true}、在庫制限を超える場合は {@code false}
     */
    public boolean addInventory(Drink drink, int quantity) {
        final int totalQuantity = inventory.values().stream().mapToInt(Integer::intValue).sum();
        if (totalQuantity + quantity > MAX_STOCK) return false;
        if (quantity <= 0) return false;
        if (inventory.containsKey(drink)) {
            inventory.computeIfPresent(drink, (key, value) -> value + quantity);
            return true;
        }

        inventory.put(drink, quantity);
        return true;
    }

    /**
     * 指定された飲料の在庫を1つ減らす。
     * @param drink 在庫を減らす対象の {@code Drink} オブジェクト
     * @return 在庫が1つ以上あり減らせた場合は {@code true}、在庫がない場合は {@code false}
     */
    public boolean reduceInventory(Drink drink) {
        if (isDeadStock(drink)) return false;
        inventory.computeIfPresent(drink, (key, value) -> value - 1);
        return true;
    }

    /**
     * 指定した飲料が在庫にないかを判定する。
     * @param drink 在庫を確認する {@code Drink} オブジェクト
     * @return 在庫がない場合は {@code true}、在庫がある場合は {@code false}
     */
    public boolean isDeadStock(Drink drink) {
        return inventory.getOrDefault(drink, 0) <= 0;
    }

    /**
     * 商品一覧を表示する。
     */
    public void displayInventory() {
        AtomicInteger idx = new AtomicInteger(1);
        inventory.entrySet().stream()
                .filter(entry -> !isDeadStock(entry.getKey()))  // デッドストックを除外
                .forEach(entry -> System.out.println("【" + idx.getAndIncrement() + "】" +
                        entry.getKey().name() + ": ¥" + entry.getKey().price()));
    }

    /**
     * 商品番号を指定してドリンクを検索する。
     * @param drinkNumber 検索する商品の番号。
     * @return 該当する Drink の Optional。
     */
    public Optional<Drink> searchDrink(int drinkNumber) {
        Drink foundDrink = findDrinkByNumber(drinkNumber);
        return Optional.ofNullable(foundDrink);
    }

    /**
     * 指定された番号のドリンクを見つける。
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
