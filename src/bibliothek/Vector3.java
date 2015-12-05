package bibliothek;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public final class Vector3 {
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
     * lenght of the Vector
     */
    public final double magnitude;
    
    public Vector3(final double x, final double y, final double z){
        this.x = x;
        this.y = y;
        this.z = z;
        this.magnitude = Math.sqrt(dot(this));
    }

    /***
     * Adds the given Vector3 v to the initial Vector3.
     * @param v
     * @return new vector
     */
    public Vector3 add(final Vector3 v){
        final double x_sum = x + v.x;
        final double y_sum = y + v.y;
        final double z_sum = z + v.z;
        return new Vector3(x_sum, y_sum, z_sum);
    }

    /***
     * Adds the given Vector3 v to the n Normal3.
     * @param n
     * @return new vector
     */
    public Vector3 add(final Normal3 n){
        final double new_x = this.x + n.x;
        final double new_y = this.y + n.y;
        final double new_z = this.z + n.z;
        return new Vector3(new_x, new_y, new_z);
    }

    /***
     * Subtracts the given Vector3 v from the initial Vector3.
     * @param v
     * @return new vector
     */
    public Vector3 sub(final Vector3 v){
        final double x_sub = this.x - v.x;
        final double y_sub = this.y - v.y;
        final double z_sub = this.z - v.z;
        return new Vector3(x_sub, y_sub, z_sub);
    }

    /***
     * Subtracts the given Vector3 v from the n Normal3.
     * @param n
     * @return new vector
     */
    public Vector3 sub(final Normal3 n){
        final double x_sub = x - n.x;
        final double y_sub = y - n.y;
        final double z_sub = z - n.z;
        return new Vector3(x_sub, y_sub, z_sub);
    }

    /***
     * Multiplies the Vector3 with the given double.
     * @param c
     * @return new vector
     */
    public Vector3 mul(final double c){
        return new Vector3(c * this.x, c * this.y, c * this.z);
    }

    /***
     * Constructs the scalar product between the given Vector3 v and the initial Vector3.
     * @param v
     * @return double, scalar product
     */
    public double dot(final Vector3 v){
        final double dot = x * v.x + y * v.y + z * v.z;
        return dot;
    }

    /***
     * Constructs the scalar product between the given Normal3 n and the initial Vector3.
     * @param n
     * @return double, scalar product
     */
    public double dot(final Normal3 n){
        final double dot = this.x * n.x + this.y * n.y + this.z * n.z;
        return dot;
    }

    /***
     * Constructs the normalized Vector3 from the initial Vector. We first specify the lenght of the Vector3
     * and then divide the Vector3 with it.
     * @return normalized vector
     */
    public Vector3 normalized(){
        return new Vector3(this.x / this.magnitude, this.y / this.magnitude, this.z / this.magnitude);
    }

    /***
     * Converts Vector3 into Normal3
     * @return 
     */
    public Normal3 asNormal(){
        return new Normal3(this.x, this.y, this.z);
    }

    public Vector3 reflectedOn(final Normal3 n){
        final Vector3 reflected = this.add(n.mul(-2 * this.dot(n))) ;
        return reflected;
    }
    /***
     * Constructs the cross product between the two Vector3. 
     * @param v
     * @return new vector
     */
    public Vector3 x(final Vector3 v){
        final double x_cross = y * v.z - z * v.y;
        final double y_cross = z * v.x - x * v.z;
        final double z_cross = x * v.y - y * v.x;
        return new Vector3(x_cross, y_cross, z_cross);
    }
    
    public String toString(){
        return this.x + "\n" + this.y + "\n" + this.z + "\n" + "------------";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector3 vector3 = (Vector3) o;

        if (Double.compare(vector3.magnitude, magnitude) != 0) return false;
        if (Double.compare(vector3.x, x) != 0) return false;
        if (Double.compare(vector3.y, y) != 0) return false;
        if (Double.compare(vector3.z, z) != 0) return false;

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
        temp = Double.doubleToLongBits(magnitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
