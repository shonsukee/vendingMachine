package money;

import inventory.Drink;
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
        assertThat(exception.getMessage(), is("貨幣の最大枚数は20枚です。"));
    }

    @Test
    public void testCalculateChange(){
        MoneyCollection moneyCollection = new MoneyCollection();
        Money ten = new Money(10);
        Money one_hundred = new Money(100);
        Money one_thousand = new Money(1000);
        moneyCollection.addMoney(ten);
        moneyCollection.addMoney(ten);
        moneyCollection.addMoney(ten);
        moneyCollection.addMoney(one_thousand);

        Drink drink = new Drink("なっちゃんオレンジ", 130);
        assertThat(moneyCollection.calculateChange(drink), is(true));
        assertThat(moneyCollection.getTotalAmount(), is(900));

        assertThat(moneyCollection.change(), containsInAnyOrder(
                new Money(500),
                new Money(100),
                new Money(100),
                new Money(100),
                new Money(100)
        ));

        MoneyCollection moneyCollection2 = new MoneyCollection();
        moneyCollection2.addMoney(ten);
        moneyCollection2.addMoney(one_hundred);
        moneyCollection2.addMoney(one_thousand);

        assertThat(moneyCollection2.calculateChange(drink), is(true));
        assertThat(moneyCollection2.getTotalAmount(), is(980));

        assertThat(moneyCollection2.change(), containsInAnyOrder(
                new Money(500),
                new Money(100),
                new Money(100),
                new Money(100),
                new Money(100),
                new Money(50),
                new Money(10),
                new Money(10),
                new Money(10)
        ));
    }
}
