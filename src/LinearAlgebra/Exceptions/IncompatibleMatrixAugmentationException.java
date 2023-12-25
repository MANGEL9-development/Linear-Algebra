package LinearAlgebra.Exceptions;

import LinearAlgebra.Matrix;

/**
 * Thrown when a matrix is attempted to be augmented to another matrix of an incompatible size. A
 * matrix (a×b) and another matrix (c×d) are compatible only if a==c
 * @author Mark Angelot
 * @since December 24, 2023
 */
public class IncompatibleMatrixAugmentationException extends Exception{
    //Maybe make this a runtime exception
    /**
     * Constructs an IncompatibleMatrixAugmentationException
     */
    public IncompatibleMatrixAugmentationException(){
        super("A matrix can be augmented to another matrix only if both matrices have the same " +
                "amount of rows");
    }

    public IncompatibleMatrixAugmentationException(Matrix mainMatrix, Matrix augmentation){
        super("A "+augmentation.getDimensions()+" matrix cannot be augmented to a "+
                mainMatrix.getDimensions()+" matrix");
    }

    /**
     * Constructs an IncompatibleMatrixAugmentationException with a message
     * @param message the detail message. The detail message is saved for later retrieval by the
     *                getMessage() method.
     */
    public IncompatibleMatrixAugmentationException(String message){
        super(message);
    }
}
