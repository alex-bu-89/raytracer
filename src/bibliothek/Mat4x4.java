package bibliothek;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class Mat4x4 {

    /**
     *  m11 value of 1 column;
     */
    public final double m11;

    /**
     *  m12 value of 2 column;
     */
    public final double m12;

    /**
     *  m13 value of 3 column;
     */
    public final double m13;

    /**
     *  m14 value of 4 column;
     */
    public final double m14;

    /**
     *  m21 value of 1 column;
     */
    public final double m21;

    /**
     *  m22 value of 2 column;
     */
    public final double m22;

    /**
     *  m23 value of 3 column;
     */
    public final double m23;

    /**
     *  m24 value of 4 column;
     */
    public final double m24;

    /**
     *  m31 value of 1 column;
     */
    public final double m31;

    /**
     *  m32 value of 2 column;
     */
    public final double m32;

    /**
     *  m33 value of 3 column;
     */
    public final double m33;

    /**
     *  m34 value of 4 column;
     */
    public final double m34;

    /**
     *  m41 value of 1 column;
     */
    public final double m41;

    /**
     *  m42 value of 2 column;
     */
    public final double m42;

    /**
     *  m43 value of 3 column;
     */
    public final double m43;

    /**
     *  m44 value of 4 column;
     */
    public final double m44;


    /**
     * creates matrix 4x4
     * @param m11 first column first row, m12, m13, m21, m22, m23, m31, m32, m33, m41, m42, m43, m44
     */
    public Mat4x4(double m11, double m12, double m13, double m14, double m21, double m22, double m23, double m24, double m31, double m32, double m33, double m34, double m41, double m42, double m43, double m44) {
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m14 = m14;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m24 = m24;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
        this.m34 = m34;
        this.m41 = m41;
        this.m42 = m42;
        this.m43 = m43;
        this.m44 = m44;
    }

    /**
     * Multiplies matrix and Vector3.
     * @param v matrix
     * @return new Vector3
     */
    public Vector3 mul(final Vector3 v){
        return new Vector3( m11 * v.x + m12 * v.y + m13 * v.z,
                            m21 * v.x + m22 * v.y + m23 * v.z,
                            m31 * v.x + m32 * v.y + m33 * v.z
        );
    }

    /**
     * Multiplies matrix and Point3.
     * @param p matrix
     * @return new Point3
     */
    public Point3 mul(final Point3 p){
        return new Point3(  m11 * p.x + m12 * p.y + m13 * p.z + m14,
                            m21 * p.x + m22 * p.y + m23 * p.z + m24,
                            m31 * p.x + m32 * p.y + m33 * p.z + m34
        );
    }

    /**
     * Multiplies two matrix.
     * @param m matrix
     * @return new matrix Mat4x4
     */
    public Mat4x4 mul(final Mat4x4 m){

        return new Mat4x4(  m11*m.m11 + m12*m.m21 + m13*m.m31 + m14*m.m41,
                            m11*m.m12 + m12*m.m22 + m13*m.m32 + m14*m.m42,
                            m11*m.m13 + m12*m.m23 + m13*m.m33 + m14*m.m43,
                            m11*m.m14 + m12*m.m24 + m13*m.m34 + m14*m.m44,

                            m21*m.m11 + m22*m.m21 + m23*m.m31 + m24*m.m41,
                            m21*m.m12 + m22*m.m22 + m23*m.m32 + m24*m.m42,
                            m21*m.m13 + m22*m.m23 + m23*m.m33 + m24*m.m43,
                            m21*m.m14 + m22*m.m24 + m23*m.m34 + m24*m.m44,

                            m31*m.m11 + m32*m.m21 + m33*m.m31 + m34*m.m41,
                            m31*m.m12 + m32*m.m22 + m33*m.m32 + m34*m.m42,
                            m31*m.m13 + m32*m.m23 + m33*m.m33 + m34*m.m43,
                            m31*m.m14 + m32*m.m24 + m33*m.m34 + m34*m.m44,

                            m41*m.m11 + m42*m.m21 + m43*m.m31 + m44*m.m41,
                            m41*m.m12 + m42*m.m22 + m43*m.m32 + m44*m.m42,
                            m41*m.m13 + m42*m.m23 + m43*m.m33 + m44*m.m43,
                            m41*m.m14 + m42*m.m24 + m43*m.m34 + m44*m.m44
        );
    }

    /**
     * Transposes the matrix
     * @return new matrix Mat4x4
     */
    public Mat4x4 transposed(){
        return new Mat4x4(  m11, m21, m31, m41,
                            m12, m22, m32, m42,
                            m13, m23, m33, m43,
                            m14, m24, m34, m44
        );
    }

    @Override
    public String toString() {
        return "Mat4x4{" +
                "m11=" + m11 +
                ", m12=" + m12 +
                ", m13=" + m13 +
                ", m14=" + m14 + "\n" +
                ", m21=" + m21 +
                ", m22=" + m22 +
                ", m23=" + m23 +
                ", m24=" + m24 + "\n" +
                ", m31=" + m31 +
                ", m32=" + m32 +
                ", m33=" + m33 +
                ", m34=" + m34 + "\n" +
                ", m41=" + m41 +
                ", m42=" + m42 +
                ", m43=" + m43 +
                ", m44=" + m44 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mat4x4 mat4x4 = (Mat4x4) o;

        if (Double.compare(mat4x4.m11, m11) != 0) return false;
        if (Double.compare(mat4x4.m12, m12) != 0) return false;
        if (Double.compare(mat4x4.m13, m13) != 0) return false;
        if (Double.compare(mat4x4.m14, m14) != 0) return false;
        if (Double.compare(mat4x4.m21, m21) != 0) return false;
        if (Double.compare(mat4x4.m22, m22) != 0) return false;
        if (Double.compare(mat4x4.m23, m23) != 0) return false;
        if (Double.compare(mat4x4.m24, m24) != 0) return false;
        if (Double.compare(mat4x4.m31, m31) != 0) return false;
        if (Double.compare(mat4x4.m32, m32) != 0) return false;
        if (Double.compare(mat4x4.m33, m33) != 0) return false;
        if (Double.compare(mat4x4.m34, m34) != 0) return false;
        if (Double.compare(mat4x4.m41, m41) != 0) return false;
        if (Double.compare(mat4x4.m42, m42) != 0) return false;
        if (Double.compare(mat4x4.m43, m43) != 0) return false;
        if (Double.compare(mat4x4.m44, m44) != 0) return false;

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
        temp = Double.doubleToLongBits(m14);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m21);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m22);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m23);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m24);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m31);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m32);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m33);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m34);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m41);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m42);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m43);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m44);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
