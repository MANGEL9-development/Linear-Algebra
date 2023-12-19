package LinearAlgebra;

import LinearAlgebra.Exceptions.IncompatibleMatrixMultiplicationException;
import LinearAlgebra.Exceptions.IndeterminableMatrixException;

import java.util.Arrays;

/**
 * This class represents a matrix. This class is immutable, so any operations done with this
 * matrix is to create and return a new matrix.
 * @author Mark Angelot
 * @since August 30, 2023
 */
public class Matrix{
//--Fields--//

    /** This array contains the entire matrix. The first dimension represents the rows, and the
     * second dimension represents the columns. */
    protected final int[][] matrixArray;
    /** This is true if the matrix is augmented, or false otherwise. */
    private final boolean IS_AUGMENTED;
    /** This is the index at which the augmentation begins. This is -1 if the matrix is not
     * augmented. */
    private final int AUGMENT_INDEX;
    /** This holds how many rows are in the matrix. */
    public final int AMOUNT_OF_ROWS;
    /** This holds how many columns are in the matrix. */
    public final int AMOUNT_OF_COLUMNS;

//--Constructors--//

    /**
     * Constructs an empty unaugmented array with the specified size.
     * @param rows the amount of rows in the matrix
     * @param columns the amount of columns in the matrix
     * @throws IllegalArgumentException if {@code rows} or {@code columns} is negative or zero
     */
    public Matrix(int rows, int columns){
        if(rows<1 || columns<1){
            throw new IllegalArgumentException("Matrix must have positive, nonzero dimensions");
        }

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
     * @param augmentIndex the index after which the augmentation begins
     */
    public Matrix(int rows, int columns, int augmentIndex){
        if(rows<1 || columns<1){
            throw new IllegalArgumentException("Matrix must have positive, nonzero dimensions");
        }
        matrixArray=new int[rows][columns];
        AUGMENT_INDEX=augmentIndex;
        IS_AUGMENTED=isAugmented();
        AMOUNT_OF_ROWS=rows;
        AMOUNT_OF_COLUMNS=columns;
    }

    public Matrix(int[][] matrixArray){
        if(Arrays.deepEquals(matrixArray,new int[][]{})){
            throw new IllegalArgumentException("Matrix cannot be empty");
        }
        for(int i=0,length=matrixArray[0].length;i<matrixArray.length;i++){
            if(matrixArray[i].length!=length){
                throw new IllegalArgumentException("Rows of matrix must be of the same length");
            }
            length=matrixArray[i].length;
        }
        this.matrixArray=matrixArray.clone();
        AUGMENT_INDEX=-1;
        IS_AUGMENTED=isAugmented();
        AMOUNT_OF_ROWS=matrixArray.length;
        AMOUNT_OF_COLUMNS=matrixArray[0].length;
    }

    /**
     * Constructs a new Matrix that is an exact copy of another matrix
     * @param matrix the matrix that is to be copied into this new Matrix
     */
    public Matrix(Matrix matrix){
        matrixArray=matrix.matrixArray.clone();
        AUGMENT_INDEX=matrix.AUGMENT_INDEX;
        IS_AUGMENTED=matrix.isAugmented();
        AMOUNT_OF_ROWS=matrix.AMOUNT_OF_ROWS;
        AMOUNT_OF_COLUMNS=matrix.AMOUNT_OF_ROWS;
    }

    /**
     * Constructs a matrix out of vectors. Each vector is to become a column of this Matrix.
     * @param vectors an array of vectors that are to become the columns of this Matrix
     * @throws IllegalArgumentException if the vectors do not all have the same height
     */
    public Matrix(Vector... vectors){
        final int VECTOR0_HEIGHT=vectors[0].AMOUNT_OF_ROWS; // this is recorded to make sure that
            // every vector has the same height
        matrixArray=new int[VECTOR0_HEIGHT][vectors.length];
        for(int i=0;i<vectors.length;i++){
            if(vectors[i].AMOUNT_OF_ROWS!=VECTOR0_HEIGHT){
                throw new IllegalArgumentException("All vectors must have the same height");
            }
            for(int j=0;j<VECTOR0_HEIGHT;j++){
                matrixArray[j][i]=vectors[i].getEntry(j);
            }
        }
        AUGMENT_INDEX=-1;
        IS_AUGMENTED=false;
        // TODO: create another constructor like this that allows for
        //  augmentation
        AMOUNT_OF_ROWS=VECTOR0_HEIGHT;
        AMOUNT_OF_COLUMNS=vectors.length;
    }

    public static Matrix generateRandomMatrix(int rows,int columns){
        Matrix newMatrix=new Matrix(rows,columns);
        newMatrix.randomizeEntries();
        return newMatrix;
    }

    // TODO: document this

    /**
     * Creates and returns an identity matrix of a specified size. An identity matrix is a matrix
     * with ones on the main diagonal and zeroes everywhere else. Because identity matrices are
     * always square, the dimension parameter will set both the width amount of rows and the amount
     * of columns.
     * @param dimension the amount of rows and columns in this matrix
     * @return an identity matrix of the specified size
     * @throws IllegalArgumentException if {@code dimension} is negative or zero
     */
    public static Matrix identityMatrix(int dimension){
        Matrix matrix=new Matrix(dimension,dimension);
        for(int i=0;i<dimension;i++){
            matrix.matrixArray[i][i]=1;
        }
        return matrix;
    }

//--Getters and Setters--//

    /**
     * Finds and returns the entry at the passed row and column
     * @param row the row in which to find the entry
     * @param column the column in which to find the entry
     * @return the entry at the passed row and column
     */
    public int getEntry(int row,int column){
        return matrixArray[row][column];
    }

    public int[] getRow(int index){
        return matrixArray[index];
    }
    public int[] getColumn(int index){
        int[] column=new int[AMOUNT_OF_COLUMNS];
        for(int i=0;i<AMOUNT_OF_ROWS;i++){
            column[i]=matrixArray[i][index];
        }
        return column;
    }

    /**
     * @return true if this Matrix is augmented, or false otherwise
     */
    public boolean isAugmented(){
        return (AUGMENT_INDEX!=-1);
    }

    /**
     * Sets an entry in the matrix to a new value
     * @param newValue the new value to be in the specified row and column.
     * @param row the row in which the new entry is to go
     * @param column the column in which the new entry is to go
     */
    private void setEntry(int newValue,int row,int column){
        matrixArray[row][column]=newValue;
    }

//--Overridden Methods--//

    /**
     * Compares to arrays and returns whether the two matrices are equal or not. Two matrices are
     * equal if the entries in matching rows and columns are equal. This means that two matrices
     * of different dimensions cannot be equal.
     * @param otherMatrix the matrix to be compared to this matrix
     * @return true if this matrix is equal to {@code otherMatrix} or false otherwise
     */
    public boolean equals(Matrix otherMatrix){
        if(this.AMOUNT_OF_ROWS!=otherMatrix.AMOUNT_OF_ROWS ||
           this.AMOUNT_OF_COLUMNS!=otherMatrix.AMOUNT_OF_COLUMNS
        ){
            return false;
        }
        for(int i=0;i<AMOUNT_OF_ROWS;i++){
            if(Arrays.equals(matrixArray[i],otherMatrix.matrixArray[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a string where all the entries are written in order, from the first row to the
     * last row. For example, a matrix could be written like this: <br /><br />
     * 3 8 13 9 4 <br />
     * 15 5 7 21 5<br />
     * 2 9 4 26 3 <br />
     * @return the matrix in block form
     */
    @Override
    public String toString(){
        // TODO: make the matrix look cleaner
        StringBuilder toString=new StringBuilder();
        for(int[] row:matrixArray){
            for(int i=0;i<row.length;i++){
                toString.append(row[i]).append((IS_AUGMENTED && AUGMENT_INDEX == i)? "|":" ");
            }
            toString.append('\n');
        }
        toString.deleteCharAt(toString.length()-1); // to remove the last '\n'
        return toString.toString();
    }

//--Properties--//

    /**
     * A matrix is square if it has the same number of rows and columns.
     * @return true if this matrix is square, or false otherwise
     */
    public boolean isSquare(){
        return (AMOUNT_OF_ROWS==AMOUNT_OF_COLUMNS);
    }

    /**
     * A symmetric matrix is one whose entry at a,b is equal to its entry at b,a for all a,b in
     * the matrix. If a matrix is equal to its transpose, then it is symmetric.
     * @return true if this matrix is symmetric, or false otherwise
     */
    public boolean isSymmetric(){
        return this.equals(this.transpose());
    }

    /**
     * Sets every entry in this Matrix to a random integer
     */
    public void randomizeEntries(){
        final int MAX_VALUE=20;
        for(int row=0;row<AMOUNT_OF_ROWS;row++){
            for(int column=0;column<AMOUNT_OF_COLUMNS;column++){
                setEntry(randomNumber(0,MAX_VALUE),row,column);
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

    public void solveWithGaussianElimination(){
        createPivots();
        clearColumns();
    }

    private void createPivots(){
        int beginningIndexOfTrivialSolutions=AMOUNT_OF_ROWS;
        /* When a row only has 0's, it is to be moved to the end by swapping it with the last
        non-trivial row */
        // TODO: maybe write code to check to see if it's a good idea to swap rows

        for(int i=0;i<AMOUNT_OF_ROWS;i++){
            //TODO: finish this
        }

        for(int row=0;row<AMOUNT_OF_ROWS;row++){
            for(int column=0;column<row;column++){
                if(matrixArray[row][column]!=0){
                    makeEntryZero(row,column);
                }
            }
            /* TODO: make first nonzero number in row 1 by multiplying that row by the reciprocal of
                the first nonzero number */
        }
    }
/*

 1. Make sure the matrix isn't just 0's
    - Maybe sort the matrix in order of the least to greatest first number
    - Keep track of the first nonzero column
 2. Starting from the first nonzero column, move diagonally from top-left to bottom-right to make
    pivots.
    - Every time a row operation is done (replacing a row with itself added to a factor of another
      row

 */
    private void clearColumns(){
        // TODO: implement this
    }

    private void makeEntryZero(int row, int column){
        matrixArray[row][column]=0;
    }


    public int determinant() throws IndeterminableMatrixException{
        if(!isSquare()){
            throw new IndeterminableMatrixException();
        }

        if(AMOUNT_OF_ROWS==2 && AMOUNT_OF_COLUMNS==2){
            return ((matrixArray[0][0]*matrixArray[1][1])-(matrixArray[0][1]*matrixArray[1][0]));
        }

        int sum=0;
        for(int i=0;i<AMOUNT_OF_COLUMNS;i++){
            /* This method always determines from the top row. This can be changed if a more
            efficient way is found. */
            Matrix subMatrix=this.removeRowAndColumn(0,i);
            sum+=(
                    matrixArray[0][i] *
                    subMatrix.determinant() *
                    ((i%2==0)?1:-1)
                 );
        }

        return sum;// this should be the determinant
    }

    /**
     * Creates and returns a new matrix that doesn't include the passed row and column
     * @param row the index of the row in the original matrix that isn't to be in the new matrix
     * @param column the index of the column in the original matrix that isn't to be in the new
     *               matrix
     * @return a new matrix that doesn't include the passed row and column. The new matrix is to
     * have a dimension whose width and height are 1 less than the original matrix.
     */
    private Matrix removeRowAndColumn(int row,int column){
        Matrix newMatrix=new Matrix(AMOUNT_OF_ROWS-1,AMOUNT_OF_COLUMNS-1);
        // row1 and column1 read from the original matrix, while row2 and column2 write to the new
        //  matrix
        for(int row1=0,row2=0;row1<AMOUNT_OF_ROWS;row1++){
            if(row1!=row){
                for(int column1=0,column2=0;column1<AMOUNT_OF_COLUMNS;column1++){
                    if(column1!=column){
                        newMatrix.matrixArray[row2][column2]=this.matrixArray[row1][column1];
                        column2++;
                    }
                }
                row2++;
            }
        }
        return newMatrix;
    }

    public Matrix inverse(){
        // TODO: implement this
        throw new UnsupportedOperationException();
    }

    /**
     * Creates and returns the transpose of this matrix. The transpose of a matrix is a matrix
     * whose columns match the rows of that matrix.
     * @return
     */
    public Matrix transpose(){
        Matrix newMatrix=new Matrix(AMOUNT_OF_COLUMNS,AMOUNT_OF_ROWS);
        for(int i=0;i<AMOUNT_OF_ROWS;i++){
            for(int j=0;j<AMOUNT_OF_COLUMNS;j++){
                newMatrix.matrixArray[j][i]=this.matrixArray[i][j];
            }
        }
        return newMatrix;
    }

    public int[] eigenvalues(){
        // TODO: implement this
        throw new UnsupportedOperationException();
    }

    public Vector[] eigenvectors(){
        // TODO: implement this
        throw new UnsupportedOperationException();
    }

//--Operations--//

    /**
     * Creates and returns a matrix which is the sum of this matrix and another matrix
     * @param matrix the matrix that is to be added to this matrix
     * @return a matrix which is the sum of this matrix and another matrix
     */
    public Matrix plus(Matrix matrix){
        Matrix newMatrix=new Matrix(AMOUNT_OF_ROWS,AMOUNT_OF_COLUMNS);
        for(int i=0;i<AMOUNT_OF_ROWS;i++){
            for(int j=0;j<AMOUNT_OF_COLUMNS;j++){
                newMatrix.matrixArray[i][j]=(this.matrixArray[i][j]+matrix.matrixArray[i][j]);
            }
        }
        return newMatrix;
    }
    /**
     * Creates and returns a matrix which is the difference of this matrix and another matrix
     * @param matrix the matrix that is to be subtracted from this matrix
     * @return a matrix which is the difference of this matrix and another matrix
     */
    public Matrix minus(Matrix matrix){
        Matrix newMatrix=new Matrix(AMOUNT_OF_ROWS,AMOUNT_OF_COLUMNS);
        for(int i=0;i<AMOUNT_OF_ROWS;i++){
            for(int j=0;j<AMOUNT_OF_COLUMNS;j++){
                newMatrix.matrixArray[i][j]=(this.matrixArray[i][j]-matrix.matrixArray[i][j]);
            }
        }
        return newMatrix;
    }

    public Matrix crossMultiplyBy(Matrix matrix) throws IncompatibleMatrixMultiplicationException{
        if(!matricesCanBeMultiplied(this,matrix)){
            throw new IncompatibleMatrixMultiplicationException(this,matrix);
        }
        Matrix product=new Matrix(this.AMOUNT_OF_ROWS,matrix.AMOUNT_OF_COLUMNS);

        for(int row=0;row<product.AMOUNT_OF_ROWS;row++){
            for(int column=0;column<product.AMOUNT_OF_COLUMNS;column++){
                product.setEntry(rowColumnProduct(this,row,matrix,column),row,column);
            }
        }

        return product;
    }

    /**
     * Multiplies the entries in the <i>row</i>th row of <code>matrix1</code> by the entries in
     * the <i>column</i>th column of <code>matrix1</code>
     * @param matrix1
     * @param row
     * @param matrix2
     * @param column
     * @return the value of
     */
    private int rowColumnProduct(Matrix matrix1,int row,Matrix matrix2,int column){
        //maybe verify that the two matrices can be multiplied
        //this might not be needed because this method should only be accessed by other methods
        // that should already verify this

        int sum=0;
        for(int i=0;i<matrix1.AMOUNT_OF_COLUMNS;i++){
            sum+=(matrix1.getEntry(row,i)*matrix2.getEntry(i,column));
        }
        return sum;
    }

    private static boolean matricesCanBeMultiplied(Matrix matrix1,Matrix matrix2){
        return (matrix1.AMOUNT_OF_COLUMNS==matrix2.AMOUNT_OF_ROWS);
    }

    /**
     * @return the dimensions of this matrix in an "m×n" format (where m is the amount of rows
     * and n is the amount of columns)
     */
    public String getDimensions(){
        return ""+AMOUNT_OF_ROWS+'×'+AMOUNT_OF_COLUMNS;
    }

//--Row Operations--//

    /**
     * Creates and returns a new matrix with the row at {@code index1} swapped with the row at
     * {@code index2}
     * @param index1 the index of the row that is to be swapped with the row at {@code index2}
     * @param index2 the index of the row that is to be swapped with the row at {@code index1}
     * @returna new matrix with the row at {@code index1} swapped with the row at {@code index2}
     */
    public Matrix swapRows(int index1,int index2){
        Matrix newMatrix=new Matrix(this);
        int[] row1=newMatrix.matrixArray[index1];
        int[] row2=newMatrix.matrixArray[index2];
        int[] temp=row2;

        row2=row1;
        row1=temp;

        newMatrix.matrixArray[index2]=row2;
        newMatrix.matrixArray[index1]=row1;

        return newMatrix;
    }

    /**
     * Creates and returns a new matrix in which the specified row is scaled by a factor.
     * @param rowIndex the index of the row that is to be scaled by the passed factor
     * @param factor the factor by which the row at the passed index is to be scaled
     * @return a new matrix in which the specified row is scaled by a factor.
     */
    public Matrix scaleRow(int rowIndex,int factor){
        Matrix newMatrix=new Matrix(this);
        for(int i=0;i<AMOUNT_OF_COLUMNS;i++){
            newMatrix.matrixArray[rowIndex][i]*=factor;
        }
        return newMatrix;
    }
}

/*
    Future Goals:
        - Make this class support entries as doubles instead of just integers

 */