package LinearAlgebra;

import java.io.PrintStream;

/**
 * @author Mark Angelot
 * @since August 29, 2023
 */
public class GaussianElimination{
    public static void main(String[] args){
        Matrix matrix=generateMatrix(3,3,true);

        printMatrix(matrix,System.out);
        solveMatrix(matrix);
        printMatrix(matrix,System.out);
    }

    // TODO: document this
    private static void solveMatrix(Matrix matrix){
        // TODO: implement this
    }

    // TODO: document this
    private static void printMatrix(Matrix matrix,PrintStream printStream){
        // TODO: implement this
    }

    // TODO: document this
    private static Matrix generateMatrix(int rows,int columns,boolean addAugmentedColumn){
        // TODO: implement this
        return new int[rows][columns];
    }
}
