package bibliothek;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public final class Point3 {
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

    public Point3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Subtracts the given Point3 p from the Point3 object.
     * @param p some point
     * @return new vector
     */
    public Vector3 sub(Point3 p){
        double new_x = this.x - p.x;
        double new_y = this.y - p.y;
        double new_z = this.z - p.z;
        return new Vector3(new_x, new_y, new_z);
    }

    /**
     * Subtracts the given Vector3 v from the Point3 object.
     * @param v some vector
     * @return new point
     */
    public Point3 sub(Vector3 v){
        double new_x = this.x - v.x;
        double new_y = this.y - v.y;
        double new_z = this.z - v.z;
        return new Point3(new_x, new_y, new_z); 
    }

    /**
     * Adds the given Vector3 v to the Point3 object.
     * @param v some vector
     * @return new point
     */
    public Point3 add(Vector3 v){
        double new_x = this.x + v.x;
        double new_y = this.y + v.y;
        double new_z = this.z + v.z;
        return new Point3(new_x, new_y, new_z);
    }
    
    public Normal3 asNormal(){
        return new Normal3(this.x, this.y, this.z);
    }
    
    public String toString(){
        return this.x + "\n" + this.y + "\n" + this.z + "\n" + "------------";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point3 point3 = (Point3) o;

        if (Double.compare(point3.x, x) != 0) return false;
        if (Double.compare(point3.y, y) != 0) return false;
        if (Double.compare(point3.z, z) != 0) return false;

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
