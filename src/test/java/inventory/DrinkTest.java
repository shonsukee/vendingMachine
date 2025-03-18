package inventory;

import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

public class DrinkTest {
    @Test
    public void testInvalidName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Drink(null, 100);
            new Drink("", 100);
        });
        assertThat(exception.getMessage(), is("不正な商品名です。"));
    }

    @Test
    public void testInvalidPrice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Drink("soda", -10);
            new Drink("orange", 0);
            new Drink("orange", 155);
        });
        assertThat(exception.getMessage(), is("不正な価格です。"));
    }

    @Test
    public void testValidDrink() {
        Drink drink = new Drink("apple", 120);
        assertThat(drink.name(), is("apple"));
        assertThat(drink.price(), is(120));
    }
}
