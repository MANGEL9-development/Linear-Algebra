package LinearAlgebra;

/**
 * @author Mark Angelot
 * @since August 30, 2023
 */
public class Matrix{
    /** This array contains the entire matrix. The first dimension represents the rows, and the
     * second dimension represents the columns. */
    private final int[][] matrixArray;
    /** This is true if the matrix is augmented, or false otherwise. */
    private final boolean IS_AUGMENTED;
    /** This is the index at which the augmentation begins. This is -1 if the matrix is not
     * augmented. */
    private final int AUGMENT_INDEX;
    /** This holds how many rows are in the matrix. */
    public final int AMOUNT_OF_ROWS;
    /** This holds how many columns are in the matrix. */
    public final int AMOUNT_OF_COLUMNS;

    /**
     * Constructs an empty unaugmented array with the specified size.
     * @param rows the amount of rows in the matrix
     * @param columns the amount of columns in the matrix
     */
    public Matrix(int rows, int columns){
        matrixArray=new int[rows][columns];
        AUGMENT_INDEX=-1;
        IS_AUGMENTED=isAugmented();
        AMOUNT_OF_ROWS=rows;
        AMOUNT_OF_COLUMNS=columns;
    }

    /**
     * Constructs an empty augmented array with the specified size.
     * @param rows the amount of rows in the matrix
     * @param columns the amount of columns in the matrix
     */
    public Matrix(int rows, int columns, int augmentIndex){
        matrixArray=new int[rows][columns];
        AUGMENT_INDEX=augmentIndex;
        IS_AUGMENTED=isAugmented();
        AMOUNT_OF_ROWS=rows;
        AMOUNT_OF_COLUMNS=columns;
    }

    /**
     * Sets every entry in this Matrix to a random integer
     */
    public void randomizeEntries(){
        for(int row=0;row<AMOUNT_OF_ROWS;row++){
            for(int column=0;column<AMOUNT_OF_COLUMNS;column++){
                matrixArray[row][column]=randomNumber(0,20);
            }
        }
    }

    public void randomizeEntriesToSolvableMatrix(){
        // TODO: figure this out
    }

    /**
     * Generates and returns a random integer between the passed min and max (inclusive)
     * @param min the least possible integer that can be returned
     * @param max the greatest possible integer that can be returned
     * @return a random integer between the passed min and max (inclusive)
     */
    private static int randomNumber(int min, int max){
        return (int)(Math.round(Math.random()*(max-min)) + min);
    }

    /**
     * @return true if this Matrix is augmented, or false otherwise
     */
    public boolean isAugmented(){
        return (AUGMENT_INDEX!=-1);
    }

    public int[] row(int index){
        return matrixArray[index];
    }
    public int[] column(int index){
        int[] column=new int[AMOUNT_OF_COLUMNS];
        for(int i=0;i<AMOUNT_OF_ROWS;i++){
            column[i]=matrixArray[i][index];
        }
        return column;
    }

    public void solveWithGaussianElimination(){
        createPivots();
        clearColumns();
    }

    private void createPivots(){
        // TODO: check to see if it's a good idea to swap rows
        for(int row=0;row<AMOUNT_OF_ROWS;row++){
            for(int column=0;column<row;column++){
                if(matrixArray[row][column]==0){
                    // do nothing
                }
                else{
                    makeEntryZero(row,column);
                }
            }
            /* TODO: make first nonzero number in row 1 by multiplying that row by the reciprocal of
                the first nonzero number */
        }
    }

    private void clearColumns(){
        // TODO: implement this
    }

    private void makeEntryZero(int row, int column){
        matrixArray[row][column]=0;
    }


    public int determinant(){
        // TODO: implement this
        throw new UnsupportedOperationException();
    }

    public int[] eigenvalues(){
        // TODO: implement this
        throw new UnsupportedOperationException();
    }

    public static Matrix multiply(Matrix... matrices){
        // TODO: implement this
        throw new UnsupportedOperationException();
    }
}
