package LinearAlgebra.Exceptions;

/**
 * Thrown when an action is attempted to change the width of a vector.
 * @author Mark Angelot
 * @since November 24, 2023
 */
public class VectorAugmentationException extends RuntimeException{ // TODO: maybe rename this to something else
    /**
     * Constructs a VectorAugmentationException
     */
    public VectorAugmentationException(){
        super();
    }

    /**
     * Constructs a VectorAugmentationException with a message
     * @param message the detail message. The detail message is saved for later retrieval by the
     *                getMessage() method.
     */
    public VectorAugmentationException(String message){
        super(message);
    }
}
