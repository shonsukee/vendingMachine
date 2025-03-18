package main;

import inventory.Inventory;
import inventory.Drink;
import money.Money;
import money.MoneyCollection;
import vendingMachine.VendingMachine;

import java.util.Optional;
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

    /**
     * 入力が有効かを判定する。
     * 
     * @param input ユーザーの入力。
     * @param scanner Scanner オブジェクト。
     * @param vm 自動販売機のインスタンス。
     * @return 有効な入力なら true、無効なら false。
     */
    private static boolean isValidInput(String input, Scanner scanner, VendingMachine vm){
        System.out.print("> ");
        if (input.equalsIgnoreCase("exit")) exit(scanner, vm);

        return !input.equalsIgnoreCase("q");
    }

    /**
     * アプリケーションを終了する。
     *
     * @param scanner Scanner オブジェクト。
     * @param vm 自動販売機のインスタンス。
     */
    private static void exit(Scanner scanner, VendingMachine vm){
        scanner.close();
        vm.refund();
        System.exit(0);
    }

    /**
     * 入金処理を行う。
     *
     * @param mc お金の管理オブジェクト。
     * @param input ユーザーの入力。
     * @return 入金が成功した場合は true、失敗した場合は false。
     */
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

    /**
     * ユーザーからの入金を処理する。
     *
     * @param scanner Scanner オブジェクト。
     * @param mc お金の管理オブジェクト。
     * @param vm 自動販売機のインスタンス。
     */
    private static void paymentProcess(Scanner scanner, MoneyCollection mc, VendingMachine vm) {
        while (true) {
            System.out.println("お金を入れてください。");
            String input = scanner.nextLine().trim();
            if (isValidInput(input, scanner, vm)) break;
            if (isCorrectMoney(mc, input)) break;
        }
    }

    /**
     * 商品購入処理を行う。
     *
     * @param input ユーザーの入力。
     * @param scanner Scanner オブジェクト。
     * @param vm 自動販売機のインスタンス。
     * @param inv 在庫管理オブジェクト。
     * @return 購入成功なら true、失敗なら false。
     */
    private static boolean isMerchandisePurchaseProcess(String input, Scanner scanner, VendingMachine vm, Inventory inv) {
        try {
            int drinkNumber = Integer.parseInt(input);
            Optional<Drink> optionalDrink = inv.searchDrink(drinkNumber);
            Drink drink = optionalDrink.orElseThrow(() -> new IllegalArgumentException("指定された番号のドリンクが見つかりません。"));

            if (vm.purchase(drink)) exit(scanner, vm);
            else System.out.println("'q' の後に、お金を追加してください。");

            return true;
        } catch (NumberFormatException e) {
            System.out.println("無効な入力です。数字を入力してください。");
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("無効な入力です。正しい商品番号を入力してください。");
            return false;
        }
    }

    /**
     * 商品購入プロセスを処理する。
     *
     * @param scanner Scanner オブジェクト。
     * @param vm 自動販売機のインスタンス。
     * @param inv 在庫管理オブジェクト。
     */
    private static void merchandisePurchaseProcess(Scanner scanner, VendingMachine vm, Inventory inv) {
        while (true) {
            System.out.println("商品番号を選択してください。");
            String input = scanner.nextLine().trim();

            if (isValidInput(input, scanner, vm)) break;
            if (isMerchandisePurchaseProcess(input, scanner, vm, inv)) break;
        }
    }
}
