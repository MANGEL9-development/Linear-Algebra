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
     *
     * @param height the amount of entries in this vector
     */
    public Vector(int height){
        super(height,1);
    }

    // TODO: make a method that calculates and returns the length

    // TODO: make a method that calculates and returns a projection onto another Vector
}
