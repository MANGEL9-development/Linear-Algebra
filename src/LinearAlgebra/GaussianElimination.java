package LinearAlgebra;

/**
 * @author Mark Angelot
 * @since August 29, 2023
 */
public class GaussianElimination{
    public static void main(String[] args){
        Matrix matrix=generateMatrix(3,3,true);

        System.out.print(matrix);
        solveMatrix(matrix);
        System.out.print(matrix);
    }

    /**
     * Solves a matrix using Gaussian Elimination. Gaussian Elimination is an algorithm that is
     * used to solve linear equations. It works by using row operations to set the entries above and
     * below the main diagonal to 0 and to set the entries on the main diagonal to 0. If the
     * matrix has one more column than it has rows (size n × n+1), then the entries on the rightmost
     * column are the values of the solution to this matrix.
     * @param matrix the matrix to be solved.
     */
    private static void solveMatrix(Matrix matrix){
        // TODO: implement this
    }

    // TODO: document this
    private static Matrix generateMatrix(int rows,int columns,boolean addAugmentedColumn){
        // TODO: implement this
        return new Matrix(rows,columns);
    }
}
