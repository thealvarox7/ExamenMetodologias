package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MatrixDeterminant {
    public static void main(String[] args) {
        try {
            int[][] matrix = readMatrixFromFile("input.txt");
            if (isSquareMatrix(matrix)) {
                int determinant = calculateDeterminant(matrix);
                System.out.println("Determinant: " + determinant);
            } else {
                throw new IllegalArgumentException("The matrix is not square.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no existe: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Numeros teinen que ser de tipo int : " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static int[][] readMatrixFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        int numRows = 0;
        int numCols = -1;

        // Determine matrix dimensions
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] numbers = line.trim().split("\\s+");

            if (numCols == -1) {
                numCols = numbers.length;
            } else if (numCols != numbers.length) {
                scanner.close();
                throw new IllegalArgumentException("Matriz no es cuadrada.");
            }

            numRows++;
        }

        scanner.close();

        // Create matrix array
        int[][] matrix = new int[numRows][numCols];

        // Read matrix elements from file
        scanner = new Scanner(file);
        for (int i = 0; i < numRows; i++) {
            String line = scanner.nextLine();
            String[] numbers = line.trim().split("\\s+");

            for (int j = 0; j < numCols; j++) {
                matrix[i][j] = Integer.parseInt(numbers[j]);
            }
        }

        scanner.close();

        return matrix;
    }

    private static boolean isSquareMatrix(int[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        return numRows == numCols;
    }

    private static int calculateDeterminant(int[][] matrix) {
        int n = matrix.length;
        if (n == 1) {
            return matrix[0][0];
        } else if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else {
            int determinant = 0;
            for (int j = 0; j < n; j++) {
                determinant += matrix[0][j] * getCofactor(matrix, 0, j);
            }
            return determinant;
        }
    }

    private static int getCofactor(int[][] matrix, int row, int col) {
        int sign = (row + col) % 2 == 0 ? 1 : -1;
        int[][] submatrix = createSubmatrix(matrix, row, col);
        return sign * calculateDeterminant(submatrix);
    }

    private static int[][] createSubmatrix(int[][] matrix, int row, int col) {
        int n = matrix.length;
        int[][] submatrix = new int[n - 1][n - 1];
        int r = 0;
        int c = 0;
        for (int i = 0; i < n; i++) {
            if (i != row) {
                for (int j = 0; j < n; j++) {
                    if (j != col) {
                        submatrix[r][c++] = matrix[i][j];
                    }
                }
                r++;
                c = 0;
            }
        }
        return submatrix;
    }
}