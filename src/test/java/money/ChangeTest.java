package money;

import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import java.util.List;

public class ChangeTest {
    @Test
    public void testAllReturn() {
        MoneyCollection moneyCollection = new MoneyCollection();
        Money ten = new Money(10);
        Money one_hundred = new Money(100);
        moneyCollection.addMoney(ten);
        moneyCollection.addMoney(ten);
        moneyCollection.addMoney(ten);
        moneyCollection.addMoney(one_hundred);

        List<Money> change = moneyCollection.change();
        assertThat(change.stream().mapToInt(Money::getAmount).sum(), is(130));
        assertThat(moneyCollection.getTotalAmount(), is(0));
    }
}
