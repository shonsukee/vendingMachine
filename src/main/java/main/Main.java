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

        while (true) {
            // 入金処理
            System.out.println("お金を入れてください。");
            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit")) {
                    vm.refund();
                    scanner.close();
                    System.exit(0);
                }

                if (input.equalsIgnoreCase("q")) {
                    break;
                }

                try {
                    int amount = Integer.parseInt(input);
                    Money money = new Money(amount);
                    mc.addMoney(money);
                    System.out.println(amount + "円を入金しました。");
                    System.out.println("総額 " + mc.getTotalAmount() + " 円です。");
                } catch (NumberFormatException e) {
                    System.out.println("無効な入力です。数字を入力してください。");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage() + "入金できませんでした。");
                }
            }

            // 商品購入処理
            System.out.println("商品番号を選択してください。");
            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit")) {
                    vm.refund();
                    scanner.close();
                    System.exit(0);
                }

                if (input.equalsIgnoreCase("q")) {
                    break;
                }

                try {
                    int drinkNumber = Integer.parseInt(input);
                    Drink drink = inv.searchDrink(drinkNumber);
                    if (drink == null) {
                        System.out.println("指定された番号のドリンクが見つかりません。");
                        continue;
                    }
                    boolean isPurchase = vm.purchase(drink);

                    if (isPurchase) {
                        vm.refund();
                        scanner.close();
                        System.exit(0);
                    } else {
                        System.out.println("'q' の後に、お金を追加してください。");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("無効な入力です。数字を入力してください。");
                } catch (IllegalArgumentException e) {
                    System.out.println("無効な入力です。正しい商品番号を入力してください。");
                }
            }
        }
    }
}
