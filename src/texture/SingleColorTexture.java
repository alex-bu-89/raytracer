/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texture;

import visualization.Color;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class SingleColorTexture implements Texture{
    
    public final Color color;

    public SingleColorTexture(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor(double u, double v) {
        return this.color;
    }


    
}
