package visualization;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 *
 * The class stands for an RGB color produced from three values of type
 * "double". The values must be greater than 0.0
 */
public class Color {

    /**
     * r value stands for red
     */
    public final double r;

    /**
     * g value stands for green
     */
    public final double g;

    /**
     * b value stands for blue
     */
    public final double b;

    public Color(final double r, final double g, final double b) {

        if (r > 1 || r < 0) {
            if (r > 1) {
                this.r = 1.0;
            } else {
                this.r = 0.0;
            }
        } else {
            this.r = r;
        }

        if (g > 1 || g < 0) {
            if (g > 1) {
                this.g = 1.0;
            } else {
                this.g = 0.0;
            }
        } else {
            this.g = g;
        }

        if (b > 1 || b < 0) {
            if (b > 1) {
                this.b = 1.0;
            } else {
                this.b = 0.0;
            }
        } else {
            this.b = b;
        }
    }

    public Color(java.awt.Color color) {
        this.r = color.getRed() / 255.0;
        this.g = color.getGreen() / 255.0;
        this.b = color.getBlue() / 255.0;
    }

    /**
     * Adds the given Color c to the object color.
     *
     * @param c
     * @return new Color
     */
    public Color add(final Color c) {
        double new_r = this.r + c.r;
        double new_g = this.g + c.g;
        double new_b = this.b + c.b;
        return new Color(new_r, new_g, new_b);
    }

    /**
     * Subtracts the given Color c from the object color.
     *
     * @param c
     * @return new Color
     */
    public Color sub(final Color c) {
        double new_r = this.r - c.r;
        double new_g = this.g - c.g;
        double new_b = this.b - c.b;

        return new Color(new_r, new_g, new_b);
    }

    /**
     * Multiplies the given Color c with the object color.
     *
     * @param c
     * @return new Color
     */
    public Color mul(final Color c) {
        double new_r = this.r * c.r;
        double new_g = this.g * c.g;
        double new_b = this.b * c.b;

        return new Color(new_r, new_g, new_b);

    }

    /**
     * Multiplies the given double value with the object color.
     *
     * @param v
     * @return new Color
     */
    public Color mul(final double v) {
        double new_r = this.r * v;
        double new_g = this.g * v;
        double new_b = this.b * v;
        return new Color(new_r, new_g, new_b);

    }

    public float[] toFloatAr() {
        return new float[]{(float) r, (float) g, (float) b, 1};
    }

    @Override
    public String toString() {
        return "Color{"
                + "r=" + r
                + ", g=" + g
                + ", b=" + b
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Color color = (Color) o;

        if (Double.compare(color.b, b) != 0) {
            return false;
        }
        if (Double.compare(color.g, g) != 0) {
            return false;
        }
        if (Double.compare(color.r, r) != 0) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(r);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(g);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
