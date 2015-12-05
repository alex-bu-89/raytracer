package visualization.gui;

import images.basics.ImageSaver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Alexander Buyanov on 08/02/15.
 */
public class MenuPanel extends JPanel {

    SceneBuilder sb;
    JComboBox lightList;
    JComboBox geometryList;
    JComboBox materialList;
    JFormattedTextField aliasing;

    public MenuPanel(SceneBuilder sb){
        this.sb = sb;

        JLabel aliasingLabel = new JLabel( "Aliasing:" );

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
        decimalFormat.setGroupingUsed(false);

        this.aliasing = new JFormattedTextField(decimalFormat);
        aliasing.setText("1");

        JLabel geometryLabel = new JLabel( "Geometry:" );
        String[] geometry = { "Sphere", "Triangle", "Box", "All"};

        this.geometryList = new JComboBox(geometry);
        geometryList.setSelectedIndex(0);

        JLabel materialLabel = new JLabel( "Material:" );
        String[] materials = { "SingleMaterial", "Lambert", "Phong"};

        this.materialList = new JComboBox(materials);
        materialList.setSelectedIndex(0);

        JLabel lightLabel = new JLabel( "Lights:" );
        String[] lights = { "No licht", "Point", "Directional", "Spot"};

        this.lightList = new JComboBox(lights);
        lightList.setSelectedIndex(0);

        JButton b1 = new JButton( "Draw" );

        addActionButton(b1);

        /**
         * die aufgaben
         */
        JButton b2 = new JButton( "Transormation task" );

        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create the layout
        GroupLayout layout = new GroupLayout( this );
        this.setLayout(layout);
        layout.setAutoCreateGaps( true );

        // Horizontally, we want to align the labels and the text fields
        // along the left (LEADING) edge
        layout.setHorizontalGroup( layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(geometryLabel)
                                        .addComponent(lightLabel)
                                        .addComponent(materialLabel)
                                        .addComponent(aliasingLabel)
                                        .addComponent(b1)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(geometryList)
                                        .addComponent(lightList)
                                        .addComponent(materialList)
                                        .addComponent(aliasing)
                        )

        );

        // Vertically, we want to align each label with his textfield
        // on the baseline of the components
        layout.setVerticalGroup( layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(geometryLabel)
                                        .addComponent(geometryList)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lightLabel)
                                        .addComponent(lightList)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(materialLabel)
                                        .addComponent(materialList)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(aliasingLabel)
                                        .addComponent(aliasing)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(b1)
                        )
        );
    }

    public void addActionButton(JButton b){

        if(b.getActionCommand().equals("Draw")){
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Drawing...");
                    sb.buildImg((String)geometryList.getSelectedItem(), (String)lightList.getSelectedItem(), (String)materialList.getSelectedItem(), Integer.parseInt(aliasing.getText()));
                }
            });
        }

    }

}
