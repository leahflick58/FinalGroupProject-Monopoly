package MonopolyGUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class SpacesGUI extends JPanel{
    private String name;
    JLabel nameLabel;

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

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        if(this.name.equals("Mediterranean Avenue") || this.name.equals("Baltic Avenue")){
            g.drawRect(0,this.getHeight()-10,this.getWidth(),10);
            g.setColor(new Color(61, 21, 21));
            g.fillRect(0,this.getHeight()-10,this.getWidth(),10);
        }
        if(this.name.equals("Oriental Avenue") || this.name.equals("Vermont Avenue") || this.name.equals("Connecticut Avenue")){
            g.drawRect(0,this.getHeight()-10,this.getWidth(),10);
            g.setColor(new Color(133, 238, 238, 255));
            g.fillRect(0,this.getHeight()-10,this.getWidth(),10);
        }
        if(this.name.equals("St Charles Place") || this.name.equals("States Avenue") || this.name.equals("Virginia Avenue")){
            g.drawRect(0,0,10,this.getHeight());
            g.setColor(Color.PINK);
            g.fillRect(0,0,10,this.getHeight());
        }
        if(this.name.equals("St James Place") || this.name.equals("Tennessee Avenue") || this.name.equals("New York Avenue")){
            g.drawRect(0,0,10,this.getHeight());
            g.setColor(Color.ORANGE);
            g.fillRect(0,0,10,this.getHeight());
        }
        if(this.name.equals("Kentucky Avenue") || this.name.equals("Indiana Avenue") || this.name.equals("Illinois Avenue")){
            g.drawRect(0,0,this.getWidth(),10);
            g.setColor(Color.RED);
            g.fillRect(0,0,this.getWidth(),10);
        }
        if(this.name.equals("Atlantic Avenue") || this.name.equals("Ventnor Avenue") || this.name.equals("Marvin Gardens")){
            g.drawRect(0,0,this.getWidth(),10);
            g.setColor(Color.YELLOW);
            g.fillRect(0,0,this.getWidth(),10);
        }
        if(this.name.equals("Pacific Avenue") || this.name.equals("North Carolina Avenue") || this.name.equals("Pennsylvania Avenue")){
            g.drawRect(this.getWidth()-10,0,10,this.getHeight());
            g.setColor(Color.GREEN);
            g.fillRect(this.getWidth()-10,0,10,this.getHeight());
        }
        if(this.name.equals("Park Place") || this.name.equals("Boardwalk")){
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
