package LinearAlgebra.Exceptions;

/**
 * @author Mark Angelot
 * @since November 24, 2023
 */
public class IndeterminableMatrixException extends Exception{
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
