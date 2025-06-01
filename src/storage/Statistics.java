package storage;

import main.Main;

import java.io.*;
import java.time.LocalDateTime;

public class Statistics {
    public static void saveStats(String winner, char symbol) {
        try {
            FileWriter writer = new FileWriter("stats.txt", true);
            LocalDateTime now = LocalDateTime.now();
            writer.write("Гравець 1: " + Main.player1 + " (X), Гравець 2: " + Main.player2 + " (O), ");
            writer.write("Переміг: " + winner + " (" + symbol + "), ");
            writer.write("Поле: " + Main.size + "x" + Main.size + ", Дата: " + now + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Помилка при збереженні статистики.");
        }
    }

    public static void showStats() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("stats.txt"));
            System.out.println("\n--- Статистика ігор ---");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Статистика відсутня.");
        } catch (IOException e) {
            System.out.println("Помилка при читанні статистики.");
        }
    }
}
