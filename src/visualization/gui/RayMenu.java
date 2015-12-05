package visualization.gui;

import visualization.Raytracer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Alexander Buyanov on 08/02/15.
 */
public class RayMenu extends JFrame {

    private Raytracer canvas;
    JPanel imagePanel;
    SceneBuilder sb;
    JPanel topPanel;

    public RayMenu(){

        sb = new SceneBuilder(this);

        this.canvas = sb.getRaytracer();

        final JFrame frame = new JFrame();
        frame.setTitle("Image Builder");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.topPanel = new JPanel();
        topPanel.setLayout( new BorderLayout() );

        frame.getContentPane().add( topPanel );

        topPanel.add(new MenuPanel(sb), BorderLayout.WEST);

        frame.add(topPanel);
        frame.setVisible(true);

    }

    public void updateFrame(){
        sb.getRaytracer().repaint();
        imagePanel.removeAll();
        imagePanel.add(sb.getRaytracer());
        this.repaint();
    }

    public static void main(String[] args) {
        new RayMenu();
    }

}
