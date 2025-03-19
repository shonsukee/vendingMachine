package main;

import money.Currency;
import money.Money;
import money.MoneyCollection;
import vendingMachine.VendingMachine;
import java.util.Scanner;
import static main.Main.isValidInput;

public class Payment {
    /**
     * ユーザーからの入金を受け付ける。
     * @param scanner Scanner オブジェクト。
     * @param mc お金の管理オブジェクト。
     * @param vm 自動販売機のインスタンス。
     */
    public static void paymentProcess(Scanner scanner, MoneyCollection mc, VendingMachine vm) {
        while (true) {
            System.out.println("お金を入れてください。");
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (isValidInput(input, scanner, vm)) break;
            deposit(mc, input);
        }
    }

    /**
     * 入金処理を行う。
     * @param mc お金の管理オブジェクト。
     * @param input ユーザーの入力。
     */
    private static void deposit(MoneyCollection mc, String input) {
        try {
            int amount = Integer.parseInt(input);
            Currency currency = Currency.fromValue(amount);
            Money money = new Money(currency);
            mc.addMoney(money);
            System.out.println(amount + "円を入金しました。");
            System.out.println("総額 " + mc.calTotalAmount() + " 円です。");
        } catch (NumberFormatException e) {
            System.out.println("無効な入力です。数字を入力してください。");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "入金できませんでした。");
        }
    }
}
