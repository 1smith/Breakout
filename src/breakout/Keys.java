/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package breakout;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author jmssmith047
 */
public class Keys implements KeyListener{
    public static boolean pause;
    
    public Keys() {
        pause = false;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == e.VK_RIGHT || e.getKeyCode() == e.VK_D){
                if(Breakout.gameRunning == true) {
                    Breakout.paddle.moveRight();
                }
            }
            if(e.getKeyCode() == e.VK_LEFT  || e.getKeyCode() == e.VK_A){
               if(Breakout.gameRunning == true) {
                    Breakout.paddle.moveLeft();
               }
            }
            if(e.getKeyCode() == e.VK_SPACE){
                if(pause == false)
                    Breakout.gameRunning = true;
                if(Breakout.panel.gameOver && pause == false) {
                    Breakout.frame.dispose();
                    Breakout n = new Breakout();
                    Breakout.frame.removeKeyListener(this);
                }
            }

            if(e.getKeyCode() == e.VK_ENTER){
                    Breakout.panel.gameOver = true;
                    Breakout.gameRunning = false;
            }

            if(e.getKeyCode() == e.VK_TAB){
                if(Breakout.panel.gameOver == false) {
                if(pause && Breakout.gameRunning == false) {
                    Breakout.gameRunning = true;
                    pause = false;
                }
                else {
                    Breakout.gameRunning = false;
                    pause = true;
                }
                }
            }
            if(e.getKeyCode() == e.VK_SHIFT){
                if(Breakout.panel.gameOver && pause == false) {
                    System.exit(0);
                }
            }
            /* Testing code, no longer needed
            if(e.getKeyCode() == e.VK_O){
                Breakout.createBall(100, 100);
            }
            */
    }
    public void keyTyped(KeyEvent e){
        
    }
    public void keyReleased(KeyEvent e){
        
    }
	
}
