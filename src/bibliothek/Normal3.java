package bibliothek;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public final class Normal3 {

    /**
     * x coordinate
     */
    public final double x;

    /**
     * y coordinate
     */
    public final double y;

    /**
     * z coordinate
     */
    public final double z;

    /**
     * constructor, creates new Normal3 object
     * @param x, y, z - double numbers
     */
    public Normal3(final double x, final double y, final double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Multiplies the given double number n by the Normal3 object.
     * @param n double number
     * @return new Normal3
     */
    public Normal3 mul(final double n){
        final double x_new = n * this.x;
        final double y_new = n * this.y;
        final double z_new = n * this.z;
        return new Normal3(x_new, y_new, z_new);
    }

    /**
     * Adds the given Normal3 n to the Normal3 object.
     * @param n Normal3
     * @return new Normal3
     */
    public Normal3 add(final Normal3 n){
        final double x_new = this.x + n.x;
        final double y_new = this.y + n.y;
        final double z_new = this.z + n.z;
        
        return new Normal3(x_new, y_new, z_new);
    }

    /**
     * Constructs the scalar product between the given Vector3 v and the Normal3 object.
     * @param v
     * @return double, scalar product
     */
    public double dot(final Vector3 v){
        final double dot = this.x * v.x + this.y * v.y + this.z * v.z;
        return dot;
    }
    
    public String toString(){
        return this.x + "\n" + this.y + "\n" + this.z + "\n" + "------------";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Normal3 normal3 = (Normal3) o;

        if (Double.compare(normal3.x, x) != 0) return false;
        if (Double.compare(normal3.y, y) != 0) return false;
        if (Double.compare(normal3.z, z) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
