package com.kivi.game;

import java.util.Scanner;

public class TicTacGame {
    private enum CellValue {X, O, EMPTY}

    private final CellValue[][] board;
    private boolean isXTurn;

    public TicTacGame() {
        board = new CellValue[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = CellValue.EMPTY;
            }
        }
        isXTurn = true;
    }

    private void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (board[i][j]) {
                    case X:
                        System.out.print(" X ");
                        break;
                    case O:
                        System.out.print(" O ");
                        break;
                    case EMPTY:
                        System.out.print(" - ");
                        break;
                }
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("-----------");
        }
    }

    private boolean checkWinner(CellValue player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player)
                return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player)
                return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player)
            return true;
        return board[0][2] == player && board[1][1] == player && board[2][0] == player;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == CellValue.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean playMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != CellValue.EMPTY) {
            System.out.println("Неверный ход. Попробуйте снова.");
            return false;
        }
        board[row][col] = isXTurn ? CellValue.X : CellValue.O;
        return true;
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        while (!gameOver) {
            printBoard();
            System.out.println("Ход игрока " + (isXTurn ? "X" : "O") + ". Введите строку и столбец (0-2): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (playMove(row, col)) {
                if (checkWinner(isXTurn ? CellValue.X : CellValue.O)) {
                    printBoard();
                    System.out.println("Игрок " + (isXTurn ? "X" : "O") + " победил!");
                    gameOver = true;
                } else if (isBoardFull()) {
                    printBoard();
                    System.out.println("Ничья!");
                    gameOver = true;
                } else {
                    isXTurn = !isXTurn;
                }
            }
        }
        scanner.close();
    }
}
