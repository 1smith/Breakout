/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


/**
 *
 * @author jmssmith047
 */
public class Ball {
    public int x, y;
    public int radius;
    public final Color colour;
    public double dirX;
    public double dirY;
    public int speed;
        
    public Ball(int newX, int newY, int radius, int speed) {
        this.x = newX;
        this.y = newY;
        this.radius = radius;
        this.speed = speed;
        this.colour = Color.RED;
        
        Random random = new Random();
        double number = random.nextDouble();
        double abs = Math.sqrt(Math.pow(number, 2) + Math.pow(1.0-number, 2));
        
        dirX = number / abs;
        dirY = (1.2-number) / abs;
    } 
    
    public void paintThis(Graphics g){
        g.setColor(colour);
        g.drawOval(x - radius, y - radius, 2*radius, 2*radius);
        g.fillOval(x - radius, y - radius, 2*radius, 2*radius);
    }
    
    public Rectangle getBounds(){
        int newX = x - radius;
        int newY = y - radius;
        int width = radius * 2;
        int height = radius * 2;
        Rectangle rectangle = new Rectangle(newX, newY, width, height);
        return rectangle;
    }

    public void move(){
        if(Math.random() % 2 == 0)
            x += dirX * speed;
        else
            x -= dirX * speed;
        y += dirY * speed;
    }

    public void reflect(boolean vertical, boolean horizontal) {
        if(vertical){
            dirY *= -1;
        }
        if(horizontal){
            dirX *= -1;
        }
    }
      
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public double getDirX() {
        return dirX;
    }
    public void setDirX(double dirX) {
        this.dirX = dirX;
    }
    public double getDirY() {
        return dirY;
    }
    public void setDirY(double dirY) {
        this.dirY = dirY;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getRadius() {
        return radius;
    }
    public void setRadius(int radius) {
        this.radius = radius;
    }
    
}












