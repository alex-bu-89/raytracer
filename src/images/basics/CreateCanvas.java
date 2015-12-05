package images.basics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 *
 * Class creates a black image with a red diagonal line
 */
public class CreateCanvas extends Canvas {

    private static final int IMAGE_WIDTH = 640;
    private static final int IMAGE_HEIGHT = 480;
    private BufferedImage image;

    public CreateCanvas(){
        final JFrame frame = new JFrame();
        frame.setTitle("Create Image");
        frame.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JMenuBar menuBar = new JMenuBar();
        final JMenu menu = new JMenu("File");
        final JMenuItem menuItem = new JMenuItem("Speichern");

        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ImageSaver saver = new ImageSaver(image);
            }
        });

        menu.add(menuItem);
        menuBar.add(menu);

        frame.setJMenuBar(menuBar);
        frame.add(this);
        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        final WritableRaster raster = image.getRaster();
        final ColorModel model = image.getColorModel();
        for (int i = 0; i < this.image.getWidth(); i++) {
            for (int j = 0; j < this.image.getHeight(); j++) {
                if(i == j){
                    raster.setDataElements(i,j,model.getDataElements(Color.RED.getRGB(), null));
                } else {
                    raster.setDataElements(i,j,model.getDataElements(Color.BLACK.getRGB(), null));
                }
            }
        }
        g.drawImage(this.image, 0, 0, null);
    }

    public static void main(String[] args) {
        CreateCanvas c = new CreateCanvas();
    }
}
