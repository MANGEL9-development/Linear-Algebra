import LinearAlgebra.Exceptions.IndeterminableMatrixException;
import LinearAlgebra.Matrix;

/**
 * @author Mark Angelot
 * @since November 24, 2023
 */
public class testDriver{
    public static void main(String[] args){
        Matrix matrix1=Matrix.generateRandomMatrix(4,4);
        System.out.println(matrix1);
        System.out.println();
        try{
            System.out.println("Determinant: "+matrix1.determinant());
        }catch(IndeterminableMatrixException e){
            System.err.println(e.getMessage());
        }
    }
}
