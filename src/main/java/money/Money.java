package money;

// 個々の貨幣
public record Money(int amount) {
    public Money(int amount) {
        if (amount == 10 || amount == 50 || amount == 100 || amount == 500 || amount == 1000) {
            this.amount = amount;
        } else {
            throw new IllegalArgumentException("有効な貨幣を入力してください。");
        }
    }

    /**
     * 金額を取得する
     *
     * @return この貨幣の金額
     */
    @Override
    public int amount() {
        return amount;
    }

    /**
     * この {@code Money} オブジェクトと指定されたオブジェクトが等しいかを判定する。
     *
     * <p>このメソッドは、オブジェクトの参照が同じ場合に {@code true} を返し、
     * それ以外の場合、クラスが一致し、かつ {@code amount} フィールドの値が等しい場合に {@code true} を返す。</p>
     *
     * @param object 比較対象のオブジェクト
     * @return 指定されたオブジェクトがこのオブジェクトと等しい場合は {@code true}、それ以外は {@code false}
     */
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Money money = (Money) object;
        return amount == money.amount;
    }
}
