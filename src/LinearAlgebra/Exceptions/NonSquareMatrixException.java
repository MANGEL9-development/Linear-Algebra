package LinearAlgebra.Exceptions;

/**
 * This is the superclass of all exceptions that are called because of non-square matrices.
 * @author Mark Angelot
 * @since December 11, 2023
 */
public abstract class NonSquareMatrixException extends Exception{
    public NonSquareMatrixException(){
        super();
    }
    public NonSquareMatrixException(String message){
        super(message);
    }
}
