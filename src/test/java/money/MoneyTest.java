package money;

import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {
    @Test
    public void testEquality(){
        Money money1 = new Money(100);
        Money money2 = new Money(100);

        assertThat(money1.equals(money2), is(true));
    }

    @Test
    public void testInequality(){
        Money money1 = new Money(100);
        Money money2 = new Money(10);

        assertThat(money1.equals(money2), is(false));
    }

    @Test
    public void testValidMoney(){
        Money ten = new Money(10);
        Money fifty = new Money(50);
        Money one_hundred = new Money(100);
        Money five_hundred = new Money(500);
        Money one_thousand = new Money(1000);

        assertThat(ten.getAmount(), is(10));
        assertThat(fifty.getAmount(), is(50));
        assertThat(one_hundred.getAmount(), is(100));
        assertThat(five_hundred.getAmount(), is(500));
        assertThat(one_thousand.getAmount(), is(1000));
    }

    @Test
    public void testInvalidMoney(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Money(0);
            new Money(1);
            new Money(5);
            new Money(5000);
            new Money(10000);
        });

        assertThat(exception.getMessage(), is("有効な貨幣を入力してください"));
    }
}
