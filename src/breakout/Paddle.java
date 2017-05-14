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
class Paddle {

    public static final int PADDLE_SIZE = 100;
    public static final int PADDLE_THICKNESS = 30;
    public int centreX, centreY;
    public boolean moveRight = false;
    public boolean moveLeft = false;
    
    public Paddle(int centreX, int centreY) {
        this.centreX = centreX;
        this.centreY = centreY;
    }
    
    public void paintThis(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(centreX - PADDLE_SIZE/2, centreY - PADDLE_THICKNESS/2, PADDLE_SIZE, PADDLE_THICKNESS);
        g.fillRect(centreX - PADDLE_SIZE/2, centreY - PADDLE_THICKNESS/2, PADDLE_SIZE, PADDLE_THICKNESS);
    }
    
    public Rectangle getBounds() {
        int x = centreX - PADDLE_SIZE/2;
        int y = centreY - PADDLE_THICKNESS/2;
        Rectangle rectangle = new Rectangle(x, y, PADDLE_SIZE, PADDLE_THICKNESS);
        return rectangle;
    }
    
    public void moveLeft() {
        if(!this.getBounds().intersectsLine(0, 0, 0, 500))
            centreX -= 20;
    }
    
    public void moveRight() {
        if(!this.getBounds().intersectsLine(500, 0, 500, 500))
            centreX += 20;
    }

    public int getCentreX() {
        return centreX;
    }
    public void setCentreX(int centreX) {
        this.centreX = centreX;
    }
    public int getCentreY() {
        return centreY;
    }
    public void setCentreY(int centreY) {
        this.centreY = centreY;
    }    
    public int getPADDLE_SIZE() {
        return PADDLE_SIZE;
    }
    public int getPADDLE_THICKNESS() {
        return PADDLE_THICKNESS;
    }
    public boolean isMoveRight() {
        return moveRight;
    }
    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }
    public boolean isMoveLeft() {
        return moveLeft;
    }
    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }
}
