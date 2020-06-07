package problem.syntax.array;

import java.util.*;

class Sudoku {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int h = n * n;
        int[][] array = new int[h][h];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < h; j++) {
                array[i][j] = scanner.nextInt();
            }
        }

        int matrixCol[][] = sortCol(array);
        int matrixRow[][] = sortRow(array);
        if (checkNumber(matrixCol) && checkNumber(matrixRow) && checkSquare(array)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean checkSquare(int[][] array) {
        int n = (int) Math.sqrt(array.length);
        List<Integer> correct = new ArrayList<>();
        for (int i = 1; i <= n * n; i++) {
            correct.add(i);
        }
        Collections.sort(correct);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!extractCell(n, i, j, array).equals(correct)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static List<Integer> extractCell(int n, int r, int c, int[][] arr) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ret.add(arr[r * n + i][c * n + j]);
            }
        }
        Collections.sort(ret);
        return ret;
    }


    private static int[][] sortCol(int[][] array) {
        int matrix[][] = new int[array.length][array.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = array[j][i];
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            Arrays.sort(matrix[i]);
        }
        return matrix;
    }

    private static int[][] sortRow(int[][] array) {
        int matrix[][] = new int[array.length][array.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = array[i][j];
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            Arrays.sort(matrix[i]);
        }
        return matrix;
    }

    public static boolean checkNumber(int[][] matrix) {
        boolean check = true;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (j < matrix.length - 1) {
                    if (matrix[i][j] + 1 != matrix[i][j + 1]) {
                        check = false;
                        break;
                    }
                }
                if (!(matrix[i][0] == 1 && matrix[i][matrix.length - 1] == matrix.length)) {
                    check = false;
                    break;
                }
            }
        }
        return check;
    }

    public static void display(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}