package storage;

import main.Main;

import java.io.*;

public class Config {
    public static void saveConfig() {
        try {
            FileWriter writer = new FileWriter("config.txt");
            writer.write("player1=" + Main.player1 + "\n");
            writer.write("player2=" + Main.player2 + "\n");
            writer.write("size=" + Main.size + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Помилка при збереженні конфігурації.");
        }
    }

    public static void loadConfig() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("config.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    if (parts[0].equals("player1")) Main.player1 = parts[1];
                    if (parts[0].equals("player2")) Main.player2 = parts[1];
                    if (parts[0].equals("size")) Main.size = Integer.parseInt(parts[1]);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            System.out.println("Помилка при завантаженні конфігурації.");
        }
    }
}
