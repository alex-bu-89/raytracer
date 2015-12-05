package texture;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class TextCoord2 {

    public double u;

    public double v;

    public TextCoord2(double u, double v) {
        this.u = u;
        this.v = v;
    }

    public TextCoord2 add(final TextCoord2 t) {
        return new TextCoord2(u + t.u, v + t.v);
    }

    public TextCoord2 mul(final double n) {
        return new TextCoord2(u * n, v * n);
    }

}
