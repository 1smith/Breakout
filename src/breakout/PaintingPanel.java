/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package breakout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Vector;

/**
 *
 * @author jmssmith047
 */
class PaintingPanel extends javax.swing.JPanel {
    public Vector contents;
    public boolean gameOver;

    public PaintingPanel(Vector contents){
        gameOver = false;
        this.contents = contents;
        this.setBackground(Color.WHITE);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);;
        for(Object o : contents) {
            if(o instanceof Vector) {
                Vector v = (Vector) o;
                for(Object i : v) {
                    if(i instanceof Brick) {
                        Brick b = (Brick) i;
                        b.paintThis(g);
                    }
                    if(i instanceof Ball) {
                        Ball b = (Ball) i;
                        b.paintThis(g);
                    }
                }
            }
            
            if(o instanceof Paddle) {
                Paddle p = (Paddle) o;
                p.paintThis(g);
            }
        }
        if(Breakout.gameRunning == false && !gameOver && Keys.pause == false) {
            Font font = new Font("Serif", Font.BOLD, 36);
            g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawString("Press Space to Start", 100, 100);
        }
        if(gameOver && Breakout.key.pause == false && Breakout.isWinner == false){
            Font font = new Font("Serif", Font.CENTER_BASELINE, 36);
            g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawString("Game Over", 160, 100);
            Font a = new Font("Serif", Font.CENTER_BASELINE, 20);
            g.setFont(a);
            g.drawString("Press space to restart the game", 100, 200);
            g.drawString("Press shift to close", 100, 220); 
        }
        if(Breakout.key.pause == true){
            Font font = new Font("Serif", Font.BOLD, 36);
            g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawString("Paused", 200, 200);
        }
        
        if(Breakout.isWinner) {
            Font font = new Font("Serif", Font.BOLD, 36);
            g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawString("Winner", 200, 200);
            Font a = new Font("Serif", Font.CENTER_BASELINE, 20);
            g.setFont(a);
            g.drawString("Press space to restart the game", 100, 240);
            g.drawString("Press shift to close", 100, 260); 
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
   
}



























