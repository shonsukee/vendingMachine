package money;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;


public class MoneyCollectionTest {
    @Test
    public void testUnderMoneyLimit() {
        MoneyCollection moneyCollection = new MoneyCollection();
        Money ten = new Money(10);
        Money fifty = new Money(50);
        Money one_hundred = new Money(100);
        Money five_hundred = new Money(500);
        Money one_thousand = new Money(1000);

        moneyCollection.addMoney(ten);
        moneyCollection.addMoney(one_hundred);
        assertThat(moneyCollection.getTotalAmount(), is(110));

        moneyCollection.addMoney(fifty);
        assertThat(moneyCollection.getTotalAmount(), is(160));

        moneyCollection.addMoney(five_hundred);
        moneyCollection.addMoney(one_thousand);
        assertThat(moneyCollection.getTotalAmount(), is(1660));
    }

    @Test
    public void testOverMoneyLimit(){
        MoneyCollection moneyCollection = new MoneyCollection();
        Money ten = new Money(10);
        for (int i = 0; i < 20; i++) {
            moneyCollection.addMoney(ten);
        }
        assertThat(moneyCollection.getTotalAmount(), is(200));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            moneyCollection.addMoney(ten);
        });
        assertThat(exception.getMessage(), is("貨幣の最大枚数は20枚です"));
    }
}
