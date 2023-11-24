package LinearAlgebra;

/**
 * @author Mark Angelot
 * @since November 24, 2023
 */
public class Vector extends Matrix{
    /**
     * TODO: document this
     * @param entries
     */
    public Vector(int... entries){
        super(entries.length,1);
    }

    /**
     * Constructs an empty unaugmented array with the specified size.
     *
     * @param rows    the amount of rows in the matrix
     * @param columns the amount of columns in the matrix
     */
    public Vector(int rows,int columns){
        super(rows,1);
    }
}
