package tictactoe;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String input;
    static char[][] board = new char[3][3];
    static int round = 1;
    static int count = 0;
    static int countX = 0;
    static int countO = 0;
    static boolean x = false;
    static boolean o = false;
    static boolean finished = false;
    static boolean check = true;
    static boolean xWins = false;
    static boolean oWins = false;
    static boolean draw = false;
    static boolean impossible = false;
    static boolean running = true;

    public static boolean checkFinished() {
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '_') {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkRunning() {
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '_') {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean xWins() {
        if (board[0][0] == 'X') {
            if (board[1][1] == 'X' && board[2][2] == 'X') {
                return true;
            }
        }
        if (board[0][2] == 'X') {
            if (board[1][1] == 'X' && board[2][0] == 'X') {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == 'X' && board[i][1] == 'X' && board[i][2] == 'X') {
                return true;
            }
            if (board[0][i] == 'X' && board[1][i] == 'X' && board[2][i] == 'X') {
                return true;
            }
        }
        return false;
    }

    public static boolean oWins() {
        if (board[0][0] == 'O') {
            if (board[1][1] == 'O' && board[2][2] == 'O') {
                return true;
            }
        }
        if (board[0][2] == 'O') {
            if (board[1][1] == 'O' && board[2][0] == 'O') {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == 'O' && board[i][1] == 'O' && board[i][2] == 'O') {
                return true;
            }
            if (board[0][i] == 'O' && board[1][i] == 'O' && board[2][i] == 'O') {
                return true;
            }
        }
        return false;
    }

    public static boolean draw() {
        if (checkFinished() && !xWins() && !oWins()) {
            return true;
        }
        return false;
    }

    public static boolean impossible() {
        int countX = 0;
        int countO = 0;
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 'X') {
                    countX++;
                }
                if (board[row][col] == 'O') {
                    countO++;
                }
            }
        }
        if (countX > countO + 1 || countO > countX + 1) {
            return true;
        }
        if (xWins() && oWins()) {
            return true;
        }
        return false;
    }

    public static void printBoard() {
        System.out.println("----------");
        for (int row = 0; row < 3; row++) {
            System.out.print("|");
            for (int col = 0; col < 3; col++) {
                System.out.print(" " + board[row][col]);
            }
            System.out.println(" |");
        }
        System.out.println("----------");
    }

    public static void mark() {
        char play;
        if (round % 2 == 0) {
            play = 'O';
        } else {
            play = 'X';
        }
        round++;
        check = true;
        while (check) {
            System.out.print("Enter the coordinates: ");
            input = scanner.nextLine();
            int row;
            int col;
            try {
                String [] pieces = input.split(" ");
                row = Integer.parseInt(pieces[0]);
                col = Integer.parseInt(pieces[1]);
                if (row >= 1 && row <= 3 && col >= 1 && col <= 3) {
                    check = false;
                }
                if (row < 1 || row > 3 || col < 1 || col > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
                if (board[3 - col][row - 1] == '_') {
                    board[3 - col][row - 1] = play;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                    check = true;
                }


            }catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            } catch (ArrayIndexOutOfBoundsException e) {
                //System.out.println("Coordinates should be from 1 to 3!");
            }
        }
        checkRunning();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '_';
            }
        }

        while (running) {
            printBoard();
            mark();
            if (xWins() && !oWins()) {
                running = false;
            }
            if (oWins() && !xWins()) {
                running = false;
            }
            if (draw()) {
                running = false;
            }
        }
        printBoard();
        if (xWins() && !oWins()) {
            System.out.println("X wins");
        }
        if (oWins() && !xWins()) {
            System.out.println("O wins");
        }
        if (draw()) {
            System.out.println("Draw");
        }



        /*
        while (running) {
            while (check) {
                System.out.print("Enter the coordinates: ");
                input = scanner.nextLine();
                int row;
                int col;
                try {
                    String [] pieces = input.split(" ");
                    row = Integer.parseInt(pieces[0]);
                    col = Integer.parseInt(pieces[1]);
                    if (row >= 1 && row <= 3 && col >= 1 && col <= 3) {
                        check = false;
                    }
                    if (row < 1 || row > 3 || col < 1 || col > 3) {
                        System.out.println("Coordinates should be from 1 to 3!");
                    }
                    if (board[3 - col][row - 1] == '_') {
                        board[3 - col][row - 1] = 'X';
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                        check = true;
                    }


                }catch (NumberFormatException e) {
                    System.out.println("You should enter numbers!");
                    continue;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            }
            System.out.println("----------");
            for (int row = 0; row < 3; row++) {
                System.out.print("|");
                for (int col = 0; col < 3; col++) {
                    System.out.print(" " + board[row][col]);
                }
                System.out.println(" |");
            }
            System.out.println("----------");


            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == 'X') {
                        countX++;
                    }
                    if (board[row][col] == 'O') {
                        countO++;
                    }
                }
                if (board[row][0] == '_' || board[row][1] == '_' || board[row][2] == '_') {
                    finished = false;
                }
                if (board[0][0] == 'X') {
                    if (board[1][1] == 'X' && board[2][2] == 'X') {
                        System.out.println("X wins");
                        return;
                    }
                }
                if (board[0][2] == 'X') {
                    if (board[1][1] == 'X' && board[2][0] == 'X') {
                        System.out.println("X wins");
                        return;
                    }
                }
                if (board[0][row] == 'X' && board[1][row] == 'X' && board[2][row] == 'X') {
                    x = true;
                }
                if (board[0][row] == 'O' && board[1][row] == 'O' && board[2][row] == 'O') {
                    o = true;
                }
                if (board[0][0] == 'O') {
                    if (board[1][1] == 'O' && board[2][2] == 'O') {
                        System.out.println("O wins");
                        return;
                    }
                }
                if (board[0][2] == 'O') {
                    if (board[1][1] == 'O' && board[2][0] == 'O') {
                        System.out.println("O wins");
                        return;
                    }
                }
                if (board[row][0] == 'X' && board[row][1] == 'X' && board[row][2] == 'X') {
                    if (count == 2) {
                        System.out.println("Impossible");
                        return;
                    }
                    count = 1;
                }
                if (board[row][0] == 'O' && board[row][1] == 'O' && board[row][2] == 'O') {
                    if (count == 1) {
                        System.out.println("Impossible");
                        return;
                    }
                    count = 2;
                }
            }
            //System.out.println(countX);
            //System.out.println(countO);

            if(x && o || countX > countO + 1 || countO > countX + 1) {
                System.out.println("Impossible");
            } else if(x != o) {
                System.out.println(x ? "X wins" : "O wins");
            } else if (count == 1) {
                System.out.println("X wins");
            } else if (count == 2) {
                System.out.println("O wins");
            } else if (!x && !o && !finished){
                System.out.println("Game not finished");
            } else {
                System.out.println("Draw");
            }
        }*/
    }
}