package money;

// 個々の貨幣
public class Money {
    final int amount;
    Money(int amount) {
        if(amount == 10 || amount == 50 || amount == 100 || amount == 500 || amount == 1000){
            this.amount = amount;
        } else {
            throw new IllegalArgumentException("有効な貨幣を入力してください");
        }
    }

    public boolean equals(Object object){
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Money money = (Money) object;
        return amount == money.amount;
    }
}
