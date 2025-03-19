package money;

public record Money(Currency amount) {
    public int getAmount() {
        return amount.getValue();
    }
}
