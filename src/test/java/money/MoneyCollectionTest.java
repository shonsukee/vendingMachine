package money;

import inventory.Drink;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;
import static money.Currency.*;

public class MoneyCollectionTest {
    @Test
    public void testUnderMoneyLimit() {
        MoneyCollection moneyCollection = new MoneyCollection();
        Money ten = new Money(TEN);
        Money fifty = new Money(FIFTY);
        Money one_hundred = new Money(ONE_HUNDRED);
        Money five_hundred = new Money(FIVE_HUNDRED);
        Money one_thousand = new Money(ONE_THOUSAND);

        moneyCollection.addMoney(ten);
        moneyCollection.addMoney(one_hundred);
        assertThat(moneyCollection.calTotalAmount(), is(110));

        moneyCollection.addMoney(fifty);
        assertThat(moneyCollection.calTotalAmount(), is(160));

        moneyCollection.addMoney(five_hundred);
        moneyCollection.addMoney(one_thousand);
        assertThat(moneyCollection.calTotalAmount(), is(1660));
    }

    @Test
    public void testOverMoneyLimit(){
        MoneyCollection moneyCollection = new MoneyCollection();
        Money ten = new Money(TEN);
        for (int i = 0; i < 20; i++) {
            moneyCollection.addMoney(ten);
        }
        assertThat(moneyCollection.calTotalAmount(), is(200));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            moneyCollection.addMoney(ten);
        });
        assertThat(exception.getMessage(), is("貨幣の最大枚数は20枚です。"));
    }

    @Test
    public void testCalculateChange(){
        MoneyCollection moneyCollection = new MoneyCollection();
        Money ten = new Money(TEN);
        Money one_hundred = new Money(ONE_HUNDRED);
        Money one_thousand = new Money(ONE_THOUSAND);
        moneyCollection.addMoney(ten);
        moneyCollection.addMoney(ten);
        moneyCollection.addMoney(ten);
        moneyCollection.addMoney(one_thousand);

        Drink drink = new Drink("なっちゃんオレンジ", 130);
        assertThat(moneyCollection.calculateChange(drink), is(true));
        assertThat(moneyCollection.calTotalAmount(), is(900));

        assertThat(moneyCollection.change(), containsInAnyOrder(
                new Money(FIVE_HUNDRED),
                new Money(ONE_HUNDRED),
                new Money(ONE_HUNDRED),
                new Money(ONE_HUNDRED),
                new Money(ONE_HUNDRED)
        ));

        MoneyCollection moneyCollection2 = new MoneyCollection();
        moneyCollection2.addMoney(ten);
        moneyCollection2.addMoney(one_hundred);
        moneyCollection2.addMoney(one_thousand);

        assertThat(moneyCollection2.calculateChange(drink), is(true));
        assertThat(moneyCollection2.calTotalAmount(), is(980));

        assertThat(moneyCollection2.change(), containsInAnyOrder(
                new Money(FIVE_HUNDRED),
                new Money(ONE_HUNDRED),
                new Money(ONE_HUNDRED),
                new Money(ONE_HUNDRED),
                new Money(ONE_HUNDRED),
                new Money(FIFTY),
                new Money(TEN),
                new Money(TEN),
                new Money(TEN)
        ));
    }
}
