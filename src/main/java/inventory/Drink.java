package inventory;

public record Drink(String name, int price) {
    public Drink {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("不正な商品名です。");
        if (price <= 0 || price % 10 != 0) throw new IllegalArgumentException("不正な価格です。");
    }
}
