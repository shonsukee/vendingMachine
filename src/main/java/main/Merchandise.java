package main;

import inventory.Drink;
import inventory.Inventory;
import vendingMachine.VendingMachine;

import java.util.Optional;
import java.util.Scanner;

import static main.Main.exit;
import static main.Main.isValidInput;

public class Merchandise {
    /**
     * 商品購入プロセスを処理する。
     * @param scanner Scanner オブジェクト。
     * @param vm 自動販売機のインスタンス。
     * @param inv 在庫管理オブジェクト。
     */
    public static void merchandisePurchaseProcess(Scanner scanner, VendingMachine vm, Inventory inv) {
        while (true) {
            System.out.println("商品番号を選択してください。");
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (isValidInput(input, scanner, vm)) break;
            if (isMerchandisePurchaseProcess(input, scanner, vm, inv)) break;
        }
    }

    /**
     * 商品購入処理を行う。
     *
     * @param input   ユーザーの入力。
     * @param scanner Scanner オブジェクト。
     * @param vm      自動販売機のインスタンス。
     * @param inv     在庫管理オブジェクト。
     * @return 購入成功なら true、失敗なら false。
     */
    private static boolean isMerchandisePurchaseProcess(String input, Scanner scanner, VendingMachine vm, Inventory inv) {
        try {
            int drinkNumber = Integer.parseInt(input);
            Optional<Drink> optionalDrink = inv.searchDrink(drinkNumber);
            Drink drink = optionalDrink.orElseThrow(() -> new IllegalArgumentException("指定された番号のドリンクが見つかりません。"));

            if (vm.purchase(drink)) exit(scanner, vm);

            System.out.println("'q' を入力後に、お金を追加してください。");
            return true;
        } catch (NumberFormatException e) {
            System.out.println("無効な入力です。数字を入力してください。");
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("無効な入力です。正しい商品番号を入力してください。");
            return false;
        }
    }
}
