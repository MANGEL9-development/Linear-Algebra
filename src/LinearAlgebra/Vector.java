package LinearAlgebra;

import LinearAlgebra.Exceptions.IncompatibleMatrixMultiplicationException;
import LinearAlgebra.Exceptions.IncompatibleVectorMultiplicationException;

/**
 * A vector is defined as a Matrix with one column.
 *
 * @author Mark Angelot
 * @since November 24, 2023
 */
public class Vector extends Matrix{
    /**
     * Constructs a vector with the passed entries.
     * @param entries the values of the entries that will be in the vector. The entries should be
     *               put in order of top to bottom.
     */
    public Vector(double... entries){
        super(entries.length,1);
        for(int i=0;i<entries.length;i++){
            matrixArray[i][0]=entries[i];
        }
    }

    /**
     * Constructs an empty vector with the specified height.
     * @param height the amount of entries in this vector
     */
    public Vector(int height){
        super(height,1);
    }

    /**
     * @param index the index at which to find the entry
     * @return the entry at the passed index
     */
    public double getEntry(int index){
        return getEntry(index,0);
    }

    /**
     * Calculates and returns the length of this vector
     * @return the length of this vector
     */
    public double length(){
        double sumOfSquares=0;
        for(double entry:matrixArray[0]){
            sumOfSquares=entry*entry;
        }
        return Math.sqrt(sumOfSquares);
    }

    /**
     * Compares this vector to another vector and returns true if the vectors are orthogonal
     * (perpendicular), or false otherwise.
     * @param vector the other vector to which to compare this vector
     * @return true if the vectors are orthogonal, or false otherwise.
     */
    public boolean isOrthogonalTo(Vector vector){
        try{
            return (dotProduct(vector)==0);
        }catch(IncompatibleVectorMultiplicationException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * @param vector the vector with which to multiply this vector
     * @return the dot product of this vector and the passed vector.
     */
    public double dotProduct(Vector vector) throws IncompatibleVectorMultiplicationException{
        /*if(this.AMOUNT_OF_ROWS!=vector.AMOUNT_OF_ROWS){
            throw new IllegalArgumentException("Two vectors can only be multiplied if they have " +
                    "the same dimension");
        }
        double sum=0;
        for(int i=0;i<vector.AMOUNT_OF_ROWS;i++){
            sum+=this.getEntry(i)*vector.getEntry(i);
        }

        return sum;*/

        try{
            return(this.transpose().dotProduct(vector)).getEntry(0,0);
        }
        catch(IncompatibleMatrixMultiplicationException e){
            throw new IncompatibleVectorMultiplicationException(e);
        }
    }

    /**
     * @return an array with all the entries in this vector from top to bottom
     */
    public double[] asArray(){
        double[] array=new double[this.AMOUNT_OF_ROWS];
        for(int i=0;i<array.length;i++){
            array[i]=getEntry(i);
        }
        return array;
    }

    // TODO: document this
    public Vector projectionOnto(Vector otherVector){
        return new Vector(
            otherVector.scaledBy(
                this.dotProduct(otherVector) /
                otherVector.dotProduct(otherVector)
            ).getColumn(0)
        );
    }
}
