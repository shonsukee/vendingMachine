package money;

import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;
import static money.Currency.*;

public class MoneyTest {
    @Test
    public void testEquality(){
        Money money1 = new Money(ONE_HUNDRED);
        Money money2 = new Money(ONE_HUNDRED);

        assertThat(money1.equals(money2), is(true));
    }

    @Test
    public void testInequality(){
        Money money1 = new Money(ONE_HUNDRED);
        Money money2 = new Money(TEN);

        assertThat(money1.equals(money2), is(false));
    }

    @Test
    public void testValidMoney(){
        Money ten = new Money(TEN);
        Money fifty = new Money(FIFTY);
        Money one_hundred = new Money(ONE_HUNDRED);
        Money five_hundred = new Money(FIVE_HUNDRED);
        Money one_thousand = new Money(ONE_THOUSAND);

        assertThat(ten.amount(), is(TEN));
        assertThat(fifty.amount(), is(FIFTY));
        assertThat(one_hundred.amount(), is(ONE_HUNDRED));
        assertThat(five_hundred.amount(), is(FIVE_HUNDRED));
        assertThat(one_thousand.amount(), is(ONE_THOUSAND));
    }

    @Test
    public void testInvalidMoney(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Money(Currency.fromValue(0));
            new Money(Currency.fromValue(1));
            new Money(Currency.fromValue(5));
            new Money(Currency.fromValue(5000));
            new Money(Currency.fromValue(10000));
        });

        assertThat(exception.getMessage(), is("有効な貨幣を入力してください。"));
    }
}
