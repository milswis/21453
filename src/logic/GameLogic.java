package logic;

import main.Main;
import storage.Statistics;

public class GameLogic {

    public static void playGame() {
        char[][] board = initialMap(Main.size);
        char[] players = {'X', 'O'};
        int playerTurn = 0;
        boolean isGameActive = true;

        while (isGameActive) {
            playerTurn++;
            renderMap(board);

            int x, y;
            do {
                System.out.println("Введіть координати x і y для " +
                        (players[playerTurn % 2] == 'X' ? Main.player1 : Main.player2) +
                        " (" + players[playerTurn % 2] + "), в межах (1 - " + Main.size + "):");
                x = Main.sc.nextInt() * 2;
                y = Main.sc.nextInt() * 2;
            } while (x <= 0 || y <= 0 || x >= board.length || y >= board.length || board[y][x] != ' ');

            board[y][x] = players[playerTurn % 2];
            if (checkWin(players[playerTurn % 2], board)) {
                renderMap(board);
                String winner = (players[playerTurn % 2] == 'X') ? Main.player1 : Main.player2;
                System.out.println("Гра завершена, переможець: " + winner + " (" + players[playerTurn % 2] + ")");
                Statistics.saveStats(winner, players[playerTurn % 2]);
                isGameActive = false;
            }
        }
    }

    public static char[][] initialMap(int size) {
        char[][] board = new char[(size + 1) * 2 - 1][(size + 1) * 2 - 1];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i % 2 == 0 && j % 2 == 0) board[i][j] = ' ';
                else if (i % 2 == 0) board[i][j] = '|';
                else if (j % 2 == 0) board[i][j] = '_';
                else board[i][j] = '#';
            }
        }
        for (int i = 0; i < size + 1; i++) {
            board[0][i * 2] = (char) ('0' + i);
            board[i * 2][0] = (char) ('0' + i);
        }
        return board;
    }

    public static void renderMap(char[][] board) {
        System.out.println();
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + "  ");
            }
            System.out.println();
        }
    }

    public static boolean checkWin(char player, char[][] board) {
        for (int i = 2; i < board.length; i += 2) {
            for (int j = 2; j < board.length - 4; j += 2) {
                if (j + 4 < board.length && board[i][j] == player && board[i][j + 2] == player && board[i][j + 4] == player) return true;
                if (i + 4 < board.length && board[j][i] == player && board[j + 2][i] == player && board[j + 4][i] == player) return true;
            }
        }
        for (int i = 2; i < board.length - 4; i += 2) {
            for (int j = 2; j < board.length - 4; j += 2) {
                if (board[i][j] == player && board[i + 2][j + 2] == player && board[i + 4][j + 4] == player) return true;
                if (board[i][j + 4] == player && board[i + 2][j + 2] == player && board[i + 4][j] == player) return true;
            }
        }
        return false;
    }
}
