package main;

import inventory.Inventory;
import inventory.Drink;
import money.MoneyCollection;
import vendingMachine.VendingMachine;
import java.util.Scanner;
import static main.Merchandise.merchandisePurchaseProcess;
import static main.Payment.paymentProcess;

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

        System.out.println("【商品一覧】");
        inv.displayInventory();

        paymentProcess(scanner, mc, vm);
        merchandisePurchaseProcess(scanner, vm, inv);
    }

    /**
     * 入力が有効かを判定する。
     * @param input ユーザーの入力。
     * @param scanner Scanner オブジェクト。
     * @param vm 自動販売機のインスタンス。
     * @return 有効な入力なら true、無効なら false。
     */
    public static boolean isValidInput(String input, Scanner scanner, VendingMachine vm){
        if (input.equalsIgnoreCase("exit")) exit(scanner, vm);
        return input.equalsIgnoreCase("q");
    }

    /**
     * アプリケーションを終了する。
     * @param scanner Scanner オブジェクト。
     * @param vm 自動販売機のインスタンス。
     */
    public static void exit(Scanner scanner, VendingMachine vm){
        scanner.close();
        vm.refund();
        System.exit(0);
    }
}
