package LinearAlgebra.Exceptions;

/**
 * Similarly to {@link IncompatibleMatrixMultiplicationException}, this exception is thrown when
 * two vectors of incompatible dimensions are attempted to be dot-multiplied. Two vectors are
 * compatible if and only if they have the same dimension.
 * @author Mark Angelot
 * @since December 26, 2023
 */
public class IncompatibleVectorMultiplicationException extends IncompatibleMatrixMultiplicationException{

    /**
     * Constructs an IncompatibleVectorMultiplicationException based on an
     * IncompatibleMatrixMultiplicationException.
     * @param e the IncompatibleMatrixMultiplicationException that was caught when this exception
     *         was thrown
     */
    public IncompatibleVectorMultiplicationException(IncompatibleMatrixMultiplicationException e){

    }
}
