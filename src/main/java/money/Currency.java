package money;

import java.util.Arrays;

public enum Currency {
    TEN(10),
    FIFTY(50),
    ONE_HUNDRED(100),
    FIVE_HUNDRED(500),
    ONE_THOUSAND(1000);

    private final int currency;

    Currency(int currency) {
        this.currency = currency;
    }

    public static Currency fromValue(int amount) {
        return Arrays.stream(Currency.values())
                .filter(c -> c.currency == amount)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("有効な貨幣を入力してください。"));
    }

    public int getValue() {
        return currency;
    }
}
