package main;

import logic.GameLogic;
import storage.Config;
import storage.Statistics;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static String player1 = "Player1";
    public static String player2 = "Player2";
    public static int size = 3;

    public static void main(String[] args) {
        Config.loadConfig();

        boolean gameActive = true;
        while (gameActive) {
            System.out.println("\nЛаскаво просимо в гру TicTacToe!");
            System.out.println("1. Грати");
            System.out.println("2. Налаштування");
            System.out.println("3. Переглянути статистику");
            System.out.println("4. Вихід");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    GameLogic.playGame();
                    break;
                case 2:
                    changeSettings();
                    break;
                case 3:
                    Statistics.showStats();
                    break;
                case 4:
                    gameActive = false;
                    break;
                default:
                    System.out.println("Невірний вибір.");
            }
        }
    }

    public static void changeSettings() {
        System.out.print("Введіть ім’я першого гравця: ");
        player1 = sc.nextLine();
        System.out.print("Введіть ім’я другого гравця: ");
        player2 = sc.nextLine();
        System.out.println("Введіть розмір поля (3, 5, 7, 9):");
        int newSize = sc.nextInt();
        sc.nextLine();
        if (newSize == 3 || newSize == 5 || newSize == 7 || newSize == 9) {
            size = newSize;
        } else {
            System.out.println("Невірний розмір. Використовується попередній.");
        }
        Config.saveConfig();
    }
}
