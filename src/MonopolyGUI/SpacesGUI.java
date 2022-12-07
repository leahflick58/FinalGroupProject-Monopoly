package MonopolyGUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class SpacesGUI extends JPanel{
    private String name;
    JLabel nameLabel;

    /**
     * Spaces constructor.
     * All GUI components are taken from https://github.com/limoneren/Monopoly-Game-Prototype.git
     * @param xCoord
     * @param yCoord
     * @param labelString
     * @param rotationDegrees
     */
    public SpacesGUI(int xCoord, int yCoord, String labelString, int rotationDegrees) {
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBounds(xCoord, yCoord, 75, 30);
        name = labelString;
        this.setLayout(null);

        if (rotationDegrees == 135 || rotationDegrees == -135 || rotationDegrees == -45 || rotationDegrees == 45) {
            // rotating a Jlabel: https://www.daniweb.com/programming/software-development/threads/390060/rotate-jlabel-or-image-in-label

            nameLabel = new JLabel(name) {
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D)g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                    AffineTransform aT = g2.getTransform();
                    Shape oldshape = g2.getClip();
                    double x = getWidth()/2.0;
                    double y = getHeight()/2.0;
                    aT.rotate(Math.toRadians(rotationDegrees), x, y);
                    g2.setTransform(aT);
                    g2.setClip(oldshape);
                    super.paintComponent(g);
                }
            };
                nameLabel.setBounds(0, 0, this.getWidth(), this.getHeight());


            this.add(nameLabel);
        } else {
            nameLabel = new JLabel(name);
            nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
            nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            nameLabel.setBounds(0,10,this.getWidth(),10);
            this.add(nameLabel);
        }
        nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    /**
     * Assigns the correct color to each color group depending on property name
     * All GUI components are taken from https://github.com/limoneren/Monopoly-Game-Prototype.git
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        if(this.name.equals("Broad St") || this.name.equals("Collage")){
            g.drawRect(0,this.getHeight()-10,this.getWidth(),10);
            g.setColor(new Color(61, 21, 21));
            g.fillRect(0,this.getHeight()-10,this.getWidth(),10);
        }
        if(this.name.equals("Center St") || this.name.equals("PEW") || this.name.equals("Lincoln Ave")){
            g.drawRect(0,this.getHeight()-10,this.getWidth(),10);
            g.setColor(new Color(133, 238, 238, 255));
            g.fillRect(0,this.getHeight()-10,this.getWidth(),10);
        }
        if(this.name.equals("SAC") || this.name.equals("E Pine St") || this.name.equals("Thorn Field")){
            g.drawRect(0,0,10,this.getHeight());
            g.setColor(Color.PINK);
            g.fillRect(0,0,10,this.getHeight());
        }
        if(this.name.equals("Commuter Lounge") || this.name.equals("Rathburn") || this.name.equals("TLC")){
            g.drawRect(0,0,10,this.getHeight());
            g.setColor(Color.ORANGE);
            g.fillRect(0,0,10,this.getHeight());
        }
        if(this.name.equals("Dunkin") || this.name.equals("STEM") || this.name.equals("Hoyt")){
            g.drawRect(0,0,this.getWidth(),10);
            g.setColor(Color.RED);
            g.fillRect(0,0,this.getWidth(),10);
        }
        if(this.name.equals("Library") || this.name.equals("Beans") || this.name.equals("E Main St")){
            g.drawRect(0,0,this.getWidth(),10);
            g.setColor(Color.YELLOW);
            g.fillRect(0,0,this.getWidth(),10);
        }
        if(this.name.equals("Guthrie Theater") || this.name.equals("Carnegie") || this.name.equals("Chapel")){
            g.drawRect(this.getWidth()-10,0,10,this.getHeight());
            g.setColor(Color.GREEN);
            g.fillRect(this.getWidth()-10,0,10,this.getHeight());
        }
        if(this.name.equals("HAL") || this.name.equals("McNulty's House")){
            g.drawRect(this.getWidth()-10,0,10,this.getHeight());
            g.setColor(new Color(6, 41, 98, 239));
            g.fillRect(this.getWidth()-10,0,10,this.getHeight());
        }
    }


    /**
     * An action is performed based on the inherited class of Space
     * @param p active PlayerGUI
     */
    abstract void action(PlayerGUI p);
    abstract String spaceName();
}
