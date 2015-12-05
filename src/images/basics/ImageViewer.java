package images.basics;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 *
 * Opens an jpg or png image and shows it in SWING
 */
public class ImageViewer {

    public static void main(String[] args) {
        run();
    }

    /**
     * Starts dialog
     */
    private static void run() {
        final JFileChooser chooser = new JFileChooser();
        final FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("File: " + chooser.getSelectedFile().getAbsolutePath());
            showImage(chooser.getSelectedFile().getAbsoluteFile());
        }
    }

    /**
     * Creates JFrame component and shows chosen picture
     * @param image - chosen picture
     */
    private static void showImage(File image) {
        final JFrame frame = new JFrame("Open Image from Filechooser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BufferedImage img = null;
        try {
            img = ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final ImageIcon imageIcon = new ImageIcon(img);
        final JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);
        frame.getContentPane().add(jLabel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}
