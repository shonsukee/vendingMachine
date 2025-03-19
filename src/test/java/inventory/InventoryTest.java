package inventory;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class InventoryTest {
    @Test
    public void testRegisterNewDrink() {
        Inventory inventory = new Inventory();
        Drink apple_juice = new Drink("なっちゃんりんご", 120);

        assertThat(inventory.addInventory(apple_juice, 10), is(true));
        assertThat(inventory.isDeadStock(apple_juice), is(false));
    }

    @Test
    public void testAddInventory() {
        Inventory inventory = new Inventory();
        Drink apple_juice = new Drink("なっちゃんりんご", 120);

        assertThat(inventory.addInventory(apple_juice, 5), is(true));
        assertThat(inventory.addInventory(apple_juice, 10), is(true));
        assertThat(inventory.isDeadStock(apple_juice), is(false));
    }

    @Test
    public void testAddInvalidDrink() {
        Inventory inventory = new Inventory();
        Drink apple_juice = new Drink("なっちゃんりんご", 120);

        assertThat(inventory.addInventory(apple_juice, 0), is(false));
        assertThat(inventory.addInventory(apple_juice, -10), is(false));
        assertThat(inventory.isDeadStock(apple_juice), is(true));
    }

    @Test
    public void testOverStockLimit() {
        Inventory inventory = new Inventory();
        Drink apple_juice = new Drink("なっちゃんりんご", 120);

        assertThat(inventory.addInventory(apple_juice, 200), is(true));
        assertThat(inventory.addInventory(apple_juice, 1), is(false));
        assertThat(inventory.isDeadStock(apple_juice), is(false));
    }

    @Test
    public void testDrinkStockAvailable() {
        Inventory inventory = new Inventory();
        Drink apple_juice = new Drink("なっちゃんりんご", 120);
        assertThat(inventory.addInventory(apple_juice, 10), is(true));
        assertThat(inventory.isDeadStock(apple_juice), is(false));
    }

    @Test
    public void testDrinkStockNotAvailable() {
        Inventory inventory = new Inventory();
        Drink orange_juice = new Drink("なっちゃんオレンジ", 120);
        assertThat(inventory.addInventory(orange_juice, 0), is(false));
        assertThat(inventory.isDeadStock(orange_juice), is(true));
    }

    @Test
    public void testReduceQuantity() {
        Inventory inventory = new Inventory();
        Drink apple_juice = new Drink("なっちゃんりんご", 120);
        assertThat(inventory.addInventory(apple_juice, 1), is(true));

        assertThat(inventory.reduceInventory(apple_juice), is(true));
        assertThat(inventory.reduceInventory(apple_juice), is(false));
    }
}
