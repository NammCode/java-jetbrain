package project.easy.tictactoe;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean notEnd = true;

        String input = "_________";
        char[] line = input.toCharArray();
        display(line);

        for (int i = 0; i < 9; i++) {
            if (notEnd) {
                // ask user to add coordinates
                getCoordinates(line, i);
                display(line);

                // add input into the array
                Character[][] array = new Character[3][3];

                //add new coordinates into array
                convertToArray(array, line);

                //Check condition of tic tac toe
                notEnd = checkStatus(array);
            } else {
                break;
            }
        }
    }

    private static void getCoordinates(char[] line, int i) {
        Scanner scanner = new Scanner(System.in);
        boolean error = false, notString = true;
        do {
            System.out.print("Enter the coordinates: ");
            String corUser = scanner.nextLine();
            char[] check = corUser.toCharArray();
            for (char c : check) {
                if (!Character.isLetter(c)) {
                    notString = false;
                    break;
                }
            }
            if (notString) {
                System.out.println("You should enter numbers!");
                error = true;
            } else {
                switch (corUser) {
                    case "1 3":
                        error = checkCoordinates(line, 0, i);
                        break;
                    case "2 3":
                        error = checkCoordinates(line, 1, i);
                        break;
                    case "3 3":
                        error = checkCoordinates(line, 2, i);
                        break;
                    case "1 2":
                        error = checkCoordinates(line, 3, i);
                        break;
                    case "2 2":
                        error = checkCoordinates(line, 4, i);
                        break;
                    case "3 2":
                        error = checkCoordinates(line, 5, i);
                        break;
                    case "1 1":
                        error = checkCoordinates(line, 6, i);
                        break;
                    case "2 1":
                        error = checkCoordinates(line, 7, i);
                        break;
                    case "3 1":
                        error = checkCoordinates(line, 8, i);
                        break;
                    default:
                        System.out.println("Coordinates should be from 1 to 3!");
                        error = true;
                        break;
                }
            }
        } while (error);
    }

    private static boolean checkCoordinates(char[] line, int k, int i) {
        if (line[k] == '_') {
            if (i % 2 == 0) line[k] = 'X';
            else line[k] = 'O';
            return false;
        } else {
            System.out.println("This cell is occupied! Choose another one!");
            return true;
        }
    }

    private static void convertToArray(Character[][] array, char[] line) {
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[i][j] = line[k++];
            }
        }
    }

    private static void display(char[] line) {
        System.out.println("---------");
        System.out.print("| ");
        for (int i = 0; i < 3; i++) {
            System.out.print(line[i] + " ");
        }
        System.out.println("|");
        System.out.print("| ");
        for (int i = 3; i < 6; i++) {
            System.out.print(line[i] + " ");
        }
        System.out.println("|");
        System.out.print("| ");
        for (int i = 6; i < 9; i++) {
            System.out.print(line[i] + " ");
        }
        System.out.println("|");
        System.out.println("---------");
    }

    private static boolean checkStatus(Character[][] array) {
        boolean xwin = false, owin = false;
        int countX = 0, countO = 0, countNull = 0;

        //Count the number of X, O & Null
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (array[i][j].equals('X')) {
                    countX++;
                } else if (array[i][j].equals('O')) {
                    countO++;
                } else {
                    countNull++;
                }
            }
        }

        //Check X & O in all row
        for (int i = 0; i < 3; i++) {
            if (array[i][0].equals('X') && array[i][1].equals('X') && array[i][2].equals('X')) {
                xwin = true;
            } else if (array[i][0].equals('O') && array[i][1].equals('O') && array[i][2].equals('O')) {
                owin = true;
            }
        }

        //Check X & O in all count
        for (int i = 0; i < 3; i++) {
            if (array[0][i].equals('X') && array[1][i].equals('X') && array[2][i].equals('X')) {
                xwin = true;
            } else if (array[0][i].equals('O') && array[1][i].equals('O') && array[2][i].equals('O')) {
                owin = true;
            }
        }

        //Check X & O in duong cheo trai
        if (array[0][0].equals('X') && array[1][1].equals('X') && array[2][2].equals('X')) {
            xwin = true;
        } else if (array[0][0].equals('O') && array[1][1].equals('O') && array[2][2].equals('O')) {
            owin = true;
        }

        //Check X & O in duong cheo phai
        if (array[2][0].equals('X') && array[1][1].equals('X') && array[0][2].equals('X')) {
            xwin = true;
        } else if (array[2][0].equals('O') && array[1][1].equals('O') && array[0][2].equals('O')) {
            owin = true;
        }

        // check who wins
        if (xwin) {
            System.out.println("X wins");
            return false;
        } else if (owin) {
            System.out.println("O wins");
            return false;
        } else if (countNull == 0) {
            System.out.println("Draw");
            return false;
        } else {
            return true;
        }
    }

}
