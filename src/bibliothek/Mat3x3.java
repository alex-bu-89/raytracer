package bibliothek;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public final class Mat3x3 {

    /**
     *  m11 value of first column first row;
     */
    public final double m11;

    /**
     *  m12 value of second column first row;
     */
    public final double m12;
    /**
     *  m13 value of third column first row;
     */
    public final double m13;

    /**
     *  m21 value of first column second row;
     */
    public final double m21;

    /**
     *  m22 value of second column second row;
     */
    public final double m22;

    /**
     *  m23 value of third column second row;
     */
    public final double m23;
    /**
     *  m31 value of first column third row;
     */
    public final double m31;

    /**
     *  m32 value of second column third row;
     */
    public final double m32;

    /**
     *  m33 value of third column third row;
     */
    public final double m33;

    /**
     *  determinant of Matrix;
     */
    public final double determinant;

    /**
     * creates matrix 3x3
     * @param m11 first column first row, m12, m13, m21, m22, m23, m31, m32, m33
     */
    public Mat3x3(final double m11, final double m12, final double m13, final double m21, final double m22, final double m23, final double m31, final double m32, final double m33) {
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
        this.determinant = m11 * m22 * m33 + m12 * m23 * m31 + m13 * m21*m32 - m31 * m22 * m13 - m32 * m23 * m11 - m33 * m21 * m12;
    }

    /**
     * Multiplies two matrix.
     * @param m matrix
     * @return new matrix Mat3x3
     */
    public Mat3x3 mul(final Mat3x3 m){
        final double m11_new = this.m11 * m.m11 + this.m12 * m.m21 + this.m13 * m.m31;
        final double m12_new = this.m11 * m.m12 + this.m12 * m.m22 + this.m13 * m.m32;
        final double m13_new = this.m11 * m.m13 + this.m12 * m.m23 + this.m13 * m.m33;

        final double m21_new = this.m21 * m.m11 + this.m22 * m.m21 + this.m23 * m.m31;
        final double m22_new = this.m21 * m.m12 + this.m22 * m.m22 + this.m23 * m.m32;
        final double m23_new = this.m21 * m.m13 + this.m22 * m.m23 + this.m23 * m.m33;

        final double m31_new = this.m31 * m.m11 + this.m32 * m.m21 + this.m33 * m.m31;
        final double m32_new = this.m31 * m.m12 + this.m32 * m.m22 + this.m33 * m.m32;
        final double m33_new = this.m31 * m.m13 + this.m32 * m.m23 + this.m33 * m.m33;

        return new Mat3x3(m11_new, m12_new, m13_new, m21_new, m22_new, m23_new, m31_new, m32_new , m33_new);
    }

    /**
     * Multiplies the given vector and matrix
     * @param v vector
     * @return new vector Vector3
     */
    public Vector3 mul(final Vector3 v){
        final double x = m11 * v.x + m12 * v.y + m13 * v.z;
        final double y = m21 * v.x + m22 * v.y + m23 * v.z;
        final double z = m31 * v.x + m32 * v.y + m33 * v.z;
        return new Vector3(x, y, z);
    }

    /**
     * Multiplies the given point and matrix
     * @param p point
     * @return new point Point3
     */
    public Point3 mul(final Point3 p){
        final Vector3 v = new Vector3(p.x, p.y, p.z);
        mul(v);
        return new Point3(v.x, v.y, v.z);
    }

    /**
     * changes first column in the matrix
     * @param v vector
     * @return new matrix Mat3x3
     */
    public Mat3x3 changeCol1(final Vector3 v){
        final double a11 = v.x;
        final double a21 = v.y;
        final double a31 = v.z;

        return new Mat3x3(a11, m12, m13, a21, m22, m23, a31, m32, m33);
    }

    /**
     * changes second column in the matrix
     * @param v vector
     * @return new matrix Mat3x3
     */
    public Mat3x3 changeCol2(final Vector3 v){
        final double a12 = v.x;
        final double a22 = v.y;
        final double a32 = v.z;

        return new Mat3x3(m11, a12, m13, m21, a22, m23, m31, a32, m33);
    }

    /**
     * changes third column in the matrix
     * @param v vector
     * @return new matrix Mat3x3
     */
    public Mat3x3 changeCol3(final Vector3 v){
        final double a13 = v.x;
        final double a23 = v.y;
        final double a33 = v.z;

        return new Mat3x3(m11, m12, a13, m21, m22, a23, m31, m32, a33);
    }

    @Override
    public String toString(){
        return "(" + m11 + " " + m12 + " " +  m13 + ") \n" + "(" + m21  + " " + m22  + " " + m23 + ") \n" + "(" + m31  + " " + m32  + " " + m33 + ")" + "\n" + "------------";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mat3x3 mat3x3 = (Mat3x3) o;

        if (Double.compare(mat3x3.determinant, determinant) != 0) return false;
        if (Double.compare(mat3x3.m11, m11) != 0) return false;
        if (Double.compare(mat3x3.m12, m12) != 0) return false;
        if (Double.compare(mat3x3.m13, m13) != 0) return false;
        if (Double.compare(mat3x3.m21, m21) != 0) return false;
        if (Double.compare(mat3x3.m22, m22) != 0) return false;
        if (Double.compare(mat3x3.m23, m23) != 0) return false;
        if (Double.compare(mat3x3.m31, m31) != 0) return false;
        if (Double.compare(mat3x3.m32, m32) != 0) return false;
        if (Double.compare(mat3x3.m33, m33) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(m11);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m12);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m13);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m21);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m22);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m23);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m31);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m32);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m33);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(determinant);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * check this out
     */
    public static void main(String[] args){
        Mat3x3 mat = new Mat3x3(1,2,3,4,5,6,7,8,9);
        System.out.println(mat.toString());
    }

}
