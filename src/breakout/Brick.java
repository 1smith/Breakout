/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author jmssmith047
 */
public class Brick {
    public final int topLeftX, topLeftY, width, height;
    public final Color colour;
    public boolean isSpecial = false;
    
    public Brick (int topLeftX, int topLeftY, Color colour){
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.width = 50;
        this.height = 15;
        this.colour = colour;
    }

    public void paintThis(Graphics g){
        g.setColor(Color.black);
        g.drawRect(topLeftX, topLeftY, width, height);
        g.setColor(colour);
        g.fillRect(topLeftX, topLeftY, width, height);
    }
    
    public Rectangle getBounds(){
        Rectangle rectangle = new Rectangle(topLeftX, topLeftY, width, height);
        return rectangle;
    }
}
