package LinearAlgebra;

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
    public Vector(int... entries){
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

    public int getEntry(int index){
        return matrixArray[index][0];
    }

    /**
     * Calculates and returns the length of this vector
     * @return the length of this vector
     */
    public double length(){
        int sumOfSquares=0;
        for(int entry:matrixArray[0]){
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
        if(this.AMOUNT_OF_ROWS!= vector.AMOUNT_OF_ROWS){
            throw new IllegalArgumentException("The two vectors must be of the same dimension");
            // ^ this might should be worded better
        }
        int sum=0;
        for(int i=0;i<vector.AMOUNT_OF_ROWS;i++){
            sum+=this.getEntry(i)*vector.getEntry(i);
        }
        return (sum==0);
    }

    // TODO: make a method that calculates and returns a projection onto another Vector
}
