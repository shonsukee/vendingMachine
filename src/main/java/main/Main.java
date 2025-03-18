package main;

import inventory.Inventory;
import inventory.Drink;
import money.Money;
import money.MoneyCollection;
import vendingMachine.VendingMachine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Inventory inv = new Inventory();
        MoneyCollection mc = new MoneyCollection();
        VendingMachine vm = new VendingMachine(inv, mc);

        // 商品を事前に登録
        inv.addInventory(new Drink("🥤コーラ", 160), 10);
        inv.addInventory(new Drink("🍊なっちゃんオレンジ", 140), 10);
        inv.addInventory(new Drink("🧃なっちゃんリンゴ", 120), 10);

        Scanner scanner = new Scanner(System.in);
        System.out.println("数字を入力すると入金、'q' の後に商品番号を入力すると商品購入、'exit' で終了");

        // 商品一覧を表示
        System.out.println("【商品一覧】");
        inv.displayProducts();

        paymentProcess(scanner, mc, vm);
        merchandisePurchaseProcess(scanner, vm, inv);
    }

    private static boolean isValidInput(String input, Scanner scanner, VendingMachine vm){
        System.out.print("> ");
        if (input.equalsIgnoreCase("exit")) exit(scanner, vm);

        return !input.equalsIgnoreCase("q");
    }

    private static void exit(Scanner scanner, VendingMachine vm){
        scanner.close();
        vm.refund();
        System.exit(0);
    }

    private static boolean isCorrectMoney(MoneyCollection mc, String input) {
        try {
            int amount = Integer.parseInt(input);
            Money money = new Money(amount);
            mc.addMoney(money);
            System.out.println(amount + "円を入金しました。");
            System.out.println("総額 " + mc.getTotalAmount() + " 円です。");
            return true;
        } catch (NumberFormatException e) {
            System.out.println("無効な入力です。数字を入力してください。");
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "入金できませんでした。");
            return false;
        }
    }

    private static void paymentProcess(Scanner scanner, MoneyCollection mc, VendingMachine vm) {
        while (true) {
            System.out.println("お金を入れてください。");
            String input = scanner.nextLine().trim();
            if (isValidInput(input, scanner, vm)) break;
            if (isCorrectMoney(mc, input)) break;
        }
    }

    private static boolean isMerchandisePurchaseProcess(String input, Scanner scanner, VendingMachine vm, Inventory inv) {
        try {
            int drinkNumber = Integer.parseInt(input);
            Drink drink = inv.searchDrink(drinkNumber);

            if (vm.purchase(drink)) exit(scanner, vm);
            else System.out.println("'q' の後に、お金を追加してください。");

            return true;
        } catch (NullPointerException e) {
            System.out.println("指定された番号のドリンクが見つかりません。");
            return false;
        } catch (NumberFormatException e) {
            System.out.println("無効な入力です。数字を入力してください。");
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("無効な入力です。正しい商品番号を入力してください。");
            return false;
        }
    }

    private static void merchandisePurchaseProcess(Scanner scanner, VendingMachine vm, Inventory inv) {
        while (true) {
            System.out.println("商品番号を選択してください。");
            String input = scanner.nextLine().trim();

            if (isValidInput(input, scanner, vm)) break;
            if (isMerchandisePurchaseProcess(input, scanner, vm, inv)) break;
        }
    }
}
