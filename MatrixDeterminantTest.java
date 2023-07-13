package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MatrixDeterminantTest {

    @Test
    void testCalculateDeterminant() {
        // ...
    }

    @Test
    void testIsSquareMatrix() {
        // ...
    }

    @Test
    void testGetCofactor() {
        // ...
    }

    @Test
    void testCreateSubmatrix() {
        int[][] matrix = {
                {2, 1, 3},
                {4, 2, 1},
                {1, 0, 2}
        };

        int[][] expectedSubmatrix = {
                {2, 3},
                {1, 2}
        };

        int[][] actualSubmatrix = MatrixDeterminantHelper.createSubmatrix(matrix, 0, 1);

        Assertions.assertArrayEquals(expectedSubmatrix, actualSubmatrix, "Submatrix creation is incorrect");
    }
}


      