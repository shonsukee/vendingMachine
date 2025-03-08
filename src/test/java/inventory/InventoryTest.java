package inventory;

import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class InventoryTest {
    @Test
    public void testRegisterNewDrink() {
        Inventory inventory = new Inventory();
        Drink apple_juice = new Drink("なっちゃんりんご", 120);

        assertThat(inventory.addDrink(apple_juice, 10), is(true));
        assertThat(inventory.getQuantity(apple_juice), is(10));
    }

    @Test
    public void testAddDrinkInventory() {
        Inventory inventory = new Inventory();
        Drink apple_juice = new Drink("なっちゃんりんご", 120);

        assertThat(inventory.addDrink(apple_juice, 5), is(true));
        assertThat(inventory.addDrink(apple_juice, 10), is(true));
        assertThat(inventory.getQuantity(apple_juice), is(15));
    }

    @Test
    public void testAddInvalidDrink() {
        Inventory inventory = new Inventory();
        Drink apple_juice = new Drink("なっちゃんりんご", 120);

        assertThat(inventory.addDrink(apple_juice, 0), is(false));
        assertThat(inventory.addDrink(apple_juice, -10), is(false));
        assertThat(inventory.getQuantity(apple_juice), is(0));
    }

    @Test
    public void testOverStockLimit() {
        Inventory inventory = new Inventory();
        Drink apple_juice = new Drink("なっちゃんりんご", 120);

        assertThat(inventory.addDrink(apple_juice, 200), is(true));
        assertThat(inventory.addDrink(apple_juice, 1), is(false));
        assertThat(inventory.getQuantity(apple_juice), is(200));
    }

    @Test
    public void testDrinkStockAvailable() {
        Inventory inventory = new Inventory();
        Drink apple_juice = new Drink("なっちゃんりんご", 120);
        assertThat(inventory.addDrink(apple_juice, 10), is(true));
        assertThat(inventory.hasStock(apple_juice), is(true));

        Drink orange_juice = new Drink("なっちゃんオレンジ", 120);
        assertThat(inventory.addDrink(orange_juice, 0), is(false));
        assertThat(inventory.hasStock(orange_juice), is(false));
    }
}
