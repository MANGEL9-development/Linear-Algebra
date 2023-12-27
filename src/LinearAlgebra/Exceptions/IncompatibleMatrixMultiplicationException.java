package LinearAlgebra.Exceptions;

import LinearAlgebra.Matrix;

/**
 * Thrown when two matrices of incompatible size are attempted to be multiplied. Two matrices (of
 * size a×b and c×d) can only be multiplied if b==c
 * @author Mark Angelot
 * @since November 24, 2023
 */
public class IncompatibleMatrixMultiplicationException extends Exception{
    //Maybe make this a runtime exception

    protected Matrix matrix1;
    protected Matrix matrix2;

    /**
     * Constructs an IncompatibleMatrixMultiplicationException
     */
    public IncompatibleMatrixMultiplicationException(){
        super();
    }

    /**
     * Constructs an IncompatibleMatrixMultiplicationException with a message
     * @param message the detail message. The detail message is saved for later retrieval by the
     *                getMessage() method.
     */
    public IncompatibleMatrixMultiplicationException(String message){
        super(message);
    }

    public IncompatibleMatrixMultiplicationException(Matrix matrix1,Matrix matrix2){
        super("Matrices of incompatible dimensions attempted to be multiplied. A " +
                matrix1.getDimensions()+" matrix cannot be multiplied by a " +
                matrix2.getDimensions()+" matrix. The amount of columns in the first matrix must " +
                "match the amount of rows in the second matrix"
        );
        this.matrix1=matrix1;
        this.matrix2=matrix2;
    }
}
