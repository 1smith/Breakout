package breakout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author James Smith 1396274
 * 
 * @extension
 * - There is an Orange block which when hit releases three balls at once
 * - Each ball is on an indivdual thread
 * - The player loses when there are no balls remaining
 * 
 * - There is a pause function which is initated with the tab key
 * 
 * - Once the game is finished, either by victory or game over, 
 * an option is presented to exit or restart the game
 * 
 * - There is a give up button (enter) which causes an instant game over
 * 
 * - There is a pop-up at the start to instruct the player on the controls of the game
 * only on the first start up
 * 
 * - The reflection has been modified so when a brick/paddle is hit by the ball
 * the reflection type depends on if the hit has horizontal or vertical
 * 
 * - The number of bricks and there is type is randomly generated each game
 * with some limiations
 * 
 * 
 */
public class Breakout extends TimerTask {
    public static Vector <Brick> bricks;
    public static Vector <Ball> balls;
    public static Paddle paddle;
    public static PaintingPanel panel;
    public static boolean gameRunning;
    public static int width = 500;
    public static int height = 500;
    public static Vector contents;
    public static Keys key = new Keys();
    public static int ballY;
    public static int radius = 10;
    public static int speed = 10;
    public static int paddleX = width / 2;
    public static JFrame frame = new JFrame("Breakout");
    public static boolean isWinner;
    
    public Breakout(){
        KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        kfm.setDefaultFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        kfm.setDefaultFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        
        bricks = new Vector();
        balls = new Vector();
        
        isWinner = false;
        int paddleY = height - 35;
        paddle = new Paddle(paddleX, paddleY);
                
        ballY = paddleY - paddle.getPADDLE_THICKNESS()- 3;
        createBall(paddleX, ballY);
        
        contents = new Vector();
        
        createBreaks();
        contents.add(bricks);
        contents.add(paddle);
        contents.add(balls);
       
        panel = new PaintingPanel(contents);
        
        width = 500;
        height = 500;
        showGUI();
    }
    
    //Start the game.
    public void beginGame(){
        gameRunning = true;
    }

    @Override
    public void run(){
        if(gameRunning == true){
            for(Ball ball : balls)
                ball.move();
            handleCollisions();
            panel.repaint();
            
        }
        if(gameRunning == false) {
            panel.repaint();
        }
    }
    
    public void createBreaks(){
        double k = 8;
        int l= 5 + (int)(Math.random() * ((12 - 5) + 1));
        
        for (int i = 0; i < k; i++){
            for (int j = 0; j < l; j++){
                int special = 1 + (int)(Math.random() * ((1 - 20) + 1));
                Color colour = Color.CYAN;
                if(special == 1) {
                    colour = Color.orange;
                }
                Brick ba = new Brick(i * 60 + 17, j * 20 + 40, colour);
                if(special == 1) {
                    ba.isSpecial = true;
                }
                bricks.add(ba);
            }   
        }
    }
    
    public void stopGame(){
        gameRunning = false;
    }
    
    public void handleCollisions(){
        ArrayList c;
        c = new ArrayList();
        
        ArrayList d;
        d = new ArrayList();
        
        ArrayList e;
        e = new ArrayList();
                
        for(Ball ball : balls) {
            Rectangle rectBall = ball.getBounds();
            Rectangle rectPaddle = paddle.getBounds();
            
            if(rectBall.intersects(rectPaddle)) {
                if(rectBall.intersectsLine(rectPaddle.getMinX(), rectPaddle.getMinY(), rectPaddle.getMaxX(), rectPaddle.getMinY())
                        || (rectBall.intersectsLine(rectPaddle.getMinX(), rectPaddle.getMaxY(), rectPaddle.getMaxX(), rectPaddle.getMaxY()))) {
                    ball.reflect(true, false);
                }

                if(rectBall.intersectsLine(rectPaddle.getMinX(), rectPaddle.getMinY(), rectPaddle.getMinX(), rectPaddle.getMaxY())
                        || (rectBall.intersectsLine(rectPaddle.getMaxX(), rectPaddle.getMinY(), rectPaddle.getMaxX(), rectPaddle.getMaxY()))) {
                    ball.reflect(false, true);
                }
            }
            
            if(ball.y < 1 || ball.y > height) {
                ball.reflect(true, false);
            }
            if(ball.x < 1 || ball.x > width){
                ball.reflect(false, true);
            }
            if(ball.y > height){
                if(balls.size() == 1) {
                    panel.gameOver = true;
                    gameRunning = false;
                }
                c.add(balls.indexOf(ball));
                
            }
            /*
            for(Ball b : balls){
                if(balls.indexOf(b) !=  balls.indexOf(ball)) {
                    Rectangle rectB = b.getBounds();
                    if(rectBall.intersects(rectB))
                        ball.reflect(true, true);
                }     
            }
            */
            if(bricks.isEmpty() == false) {
                for(int i = 0; i < bricks.size(); i++) {
                    if(rectBall.intersects(bricks.get(i).getBounds())) {
                        if(rectBall.intersectsLine(bricks.get(i).topLeftX, bricks.get(i).topLeftY, 
                                bricks.get(i).topLeftX + bricks.get(i).width, bricks.get(i).topLeftY)
                                ||
                                
                                rectBall.intersectsLine(bricks.get(i).topLeftX, bricks.get(i).topLeftY + bricks.get(i).height, 
                                bricks.get(i).topLeftX + bricks.get(i).width, bricks.get(i).topLeftY + bricks.get(i).height)
                                ) {
                            ball.reflect(true, false);
                        }
                        if(rectBall.intersectsLine(bricks.get(i).topLeftX, bricks.get(i).topLeftY, 
                                bricks.get(i).topLeftX, bricks.get(i).topLeftY + bricks.get(i).height)
                                ||
                                
                                rectBall.intersectsLine(bricks.get(i).topLeftX + bricks.get(i).width, bricks.get(i).topLeftY, 
                                bricks.get(i).topLeftX + bricks.get(i).width, bricks.get(i).topLeftY + bricks.get(i).height)
                                ) {
                            ball.reflect(false, true);
                        }
                        if(bricks.get(i).isSpecial) {
                            if(e.contains(i) == false) {
                                e.add(i);
                            }
                        }
                        if(d.contains(i) == false) {
                                d.add(i);
                            }
                        break;
                    }
                }
            }
            else {
               panel.gameOver = true;
               gameRunning = false;
               isWinner = true;
            }  
        }
        
        
        Collections.reverse(c);
        
        for(Object count: c) {
            if(!c.isEmpty()) {
                int o = (int) count;
                balls.remove(o);
            }
        }
        Collections.reverse(e);
        for(Object bCount: e) {
            if(!e.isEmpty()) {
                int o = (int) bCount;
                createBall(bricks.get(o).topLeftX, bricks.get(o).topLeftY);
                createBall(bricks.get(o).topLeftX, bricks.get(o).topLeftY);
                createBall(bricks.get(o).topLeftX, bricks.get(o).topLeftY);
                
            }
        }
        Collections.reverse(d);
        
        for(Object bCount: d) {
            if(!d.isEmpty()) {
                int o = (int) bCount;
                bricks.remove(o);
            }
        }
    }
   
    public static void main(String[] args){
        Timer t = new Timer();
        t.schedule(new Breakout(), 0,40);
        String intro = "Welcome To Breakout \n"
                + "Controls: \n"
                + "Tab: Pause\n"
                + "A or <- : Move Paddle Left\n"
                + "D or -> : Move Paddle Right\n"
                + "Space: Start Game\n"
                + "Enter: End/Restart game\n"
                + "\n"
                + "Have Fun, Press Ok to Begin";
        JOptionPane.showMessageDialog(panel, intro, "Welcome Message", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private static void showGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(key);
        frame.add(panel);
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(width + 7, height + 20));
        frame.pack();
        frame.setResizable(false);
    }
    
    public synchronized static void createBall(final int i, final int j) {
        Thread t1 = new Thread(new Runnable() {
                    public void run () {
                        Ball b = new Ball(i, j, radius, speed);
                        balls.add(b);
                    }
        });
        t1.start();
    }

    public Vector getBricks() {
        return bricks;
    }
    public void setBricks(Vector bricks) {
        this.bricks = bricks;
    }
    public Paddle getPaddle() {
        return paddle;
    }
    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }
    public PaintingPanel getPanel() {
        return panel;
    }
    public void setPanel(PaintingPanel panel) {
        this.panel = panel;
    }
    public boolean isGameRunning() {
        return gameRunning;
    }
    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public Vector getContents() {
        return contents;
    }
    public void setContents(Vector contents) {
        this.contents = contents;
    }
}