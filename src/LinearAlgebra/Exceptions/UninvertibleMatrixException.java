package LinearAlgebra.Exceptions;

import LinearAlgebra.Matrix;

/**
 * This exception is thrown when an attempt is made to calculate the inverse of a matrix that
 * cannot be inverted. Only square matrices can be inverted.
 * @author Mark Angelot
 * @since December 11, 2023
 */
public class UninvertibleMatrixException extends NonSquareMatrixException{
    /**
     * Constructs an UninvertibleMatrixException
     */
    public UninvertibleMatrixException(){
        super();
    }

    /**
     * Constructs a UninvertibleMatrixException with a message
     * @param message the detail message. The detail message is saved for later retrieval by the
     *                getMessage() method.
     */
    public UninvertibleMatrixException(String message){
        super(message);
    }

    public UninvertibleMatrixException(Matrix matrix){
        super("This is a " + matrix.getDimensions() +
                " matrix so it cannot be inverted. Only square matrices can be inverted.");
    }
}
