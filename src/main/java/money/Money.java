package money;

// 個々の貨幣
public record Money(Currency amount) {
    public int getAmount() {
        return amount.getValue();
    }
}
