/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package images.basics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 *
 * Saves an image as png
 */
public class ImageSaver {

    private final BufferedImage toBeSaved;

    public ImageSaver(BufferedImage toBeSaved){
        this.toBeSaved = toBeSaved;
        final JFileChooser chooser = new JFileChooser();
        final FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG images", "png");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            if(filter.accept(chooser.getSelectedFile())){
                saveImage(toBeSaved, chooser.getSelectedFile().getAbsoluteFile());
            } else {
                System.out.println("The image must be .png");
            }
        }
    }

    private void saveImage(BufferedImage toBeSaved, File file) {
        try{
            ImageIO.write(toBeSaved, "png", file);
            System.out.println("Image was saved\n" + file.getAbsolutePath());
        }catch(IOException ex){
            ex.getStackTrace();
        }
    }
}