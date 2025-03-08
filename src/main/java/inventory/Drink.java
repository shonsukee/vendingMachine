package inventory;

public class Drink {
    private final String name;
    private final int price;

    Drink(String name, int price) {
        if(name == null || name.isEmpty()) throw new IllegalArgumentException("不正な商品名です");
        if(price <= 0) throw new IllegalArgumentException("不正な価格です");

        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }
}
