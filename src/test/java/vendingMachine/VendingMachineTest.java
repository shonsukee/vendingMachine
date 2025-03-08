package vendingMachine;

import inventory.Drink;
import inventory.Inventory;
import money.Money;
import money.MoneyCollection;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class VendingMachineTest {
    @Test
    public void testVendingMachine() {
        Inventory inventory = new Inventory();
        MoneyCollection moneyCollection = new MoneyCollection();
        VendingMachine vm = new VendingMachine(inventory, moneyCollection);

        Drink apple_juice = new Drink("なっちゃんりんご", 120);
        Drink orange_juice = new Drink("なっちゃんオレンジ", 140);
        inventory.addInventory(apple_juice, 10);
        inventory.addInventory(orange_juice, 20);

        Money one_hundred = new Money(100);
        Money ten = new Money(10);
        moneyCollection.addMoney(one_hundred);
        moneyCollection.addMoney(ten);
        moneyCollection.addMoney(ten);
        assertThat(vm.purchase(apple_juice), is(true));
    }

    @Test
    public void testInsertMoney() {
        Inventory inventory = new Inventory();
        MoneyCollection moneyCollection = new MoneyCollection();
        VendingMachine vm = new VendingMachine(inventory, moneyCollection);

        Money ten = new Money(10);
        Money fifty = new Money(50);
        Money one_hundred = new Money(100);
        Money five_hundred = new Money(500);
        Money one_thousand = new Money(1000);

        vm.insertMoney(ten);
        vm.insertMoney(fifty);
        vm.insertMoney(one_hundred);
        vm.insertMoney(five_hundred);
        vm.insertMoney(one_thousand);
    }

    @Test
    public void testRefundMoney() {
        Inventory inventory = new Inventory();
        MoneyCollection moneyCollection = new MoneyCollection();
        VendingMachine vm = new VendingMachine(inventory, moneyCollection);

        Money ten = new Money(10);
        Money fifty = new Money(50);
        Money one_hundred = new Money(100);
        Money five_hundred = new Money(500);
        Money one_thousand = new Money(1000);

        vm.insertMoney(ten);
        vm.insertMoney(fifty);
        vm.insertMoney(one_hundred);
        vm.insertMoney(five_hundred);
        vm.insertMoney(one_thousand);

        vm.refund();
    }
}
