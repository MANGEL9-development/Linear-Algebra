package LinearAlgebra.Exceptions;

/**
 * This exception is thrown when an attempt is made to calculate the determinants of a matrix that
 * does not have determinants. Only square matrices have determinants.
 * @author Mark Angelot
 * @since November 24, 2023
 */
public class IndeterminableMatrixException extends NonSquareMatrixException{
    /**
     * Constructs a IndeterminableMatrixException
     */
    public IndeterminableMatrixException(){
        super();
    }

    /**
     * Constructs a IndeterminableMatrixException with a message
     * @param message the detail message. The detail message is saved for later retrieval by the
     *                getMessage() method.
     */
    public IndeterminableMatrixException(String message){
        super(message);
    }
}
