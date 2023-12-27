import LinearAlgebra.Exceptions.IncompatibleMatrixAugmentationException;
import LinearAlgebra.Exceptions.IndeterminableMatrixException;
import LinearAlgebra.Matrix;
import LinearAlgebra.Vector;

/**
 * @author Mark Angelot
 * @since November 24, 2023
 */
public class testDriver{
    public static void main(String[] args){
        testSubmatrix();
    }

    private static void testDeterminant(){
        Matrix matrix1=Matrix.generateRandomMatrix(4,4);
        System.out.println(matrix1);
        System.out.println();
        try{
            System.out.println("Determinant: "+matrix1.determinant());
        }catch(IndeterminableMatrixException e){
            System.err.println(e.getMessage());
        }
    }

    private static void test2DArrayConstructor(){
        Matrix matrix=new Matrix(new double[][]{
                new double[]{1, 2, 3, 4, 5},
                new double[]{6, 7, 8, 9, 10},
                new double[]{11,12,13,14,15}
        });
        System.out.println(matrix);
    }

    private static void testSwappingRows(){
        Matrix matrix=new Matrix(new double[][]{
                new double[]{1, 2, 3, 4, 5},
                new double[]{6, 7, 8, 9, 10},
                new double[]{11,12,13,14,15}
        });
        System.out.println("Original Matrix:\n"+matrix);
        System.out.println("Matrix with second and third rows swapped:\n"+matrix.swapRows(1,2));
    }

    private static void testDiagonalMatrix(){
        try{
            System.out.println(
                Matrix
                    .generateDiagonalMatrix(new Vector(1,2,3,4,5))
                    .augmentedWith(
                        Matrix.generateIdentityMatrix(5)
                    )
            );
        }
        catch(IncompatibleMatrixAugmentationException e){
            e.printStackTrace();
        }
    }

    private static void testSubmatrix(){
        Matrix matrix=new Matrix(new double[][]{
                new double[]{1, 2, 3, 4, 5},
                new double[]{6, 7, 8, 9, 10},
                new double[]{11,12,13,14,15}
        });
        System.out.println(matrix.submatrix(1,1,3,5));
    }
}
