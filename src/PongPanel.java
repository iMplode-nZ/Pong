import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class PongPanel extends JPanel implements Runnable {
    boolean isRunning = false;
    private static final int DELAY = 25;
    ArrayList<GraphicsObject> paddleAndBall = new ArrayList<>();
    public PongPanel() {
        // set panel/game dimensions to be those of the entire screen
        Dimension scrDim = Toolkit.getDefaultToolkit().getScreenSize();   // screen size
        setPreferredSize(scrDim);

        int pWidth = scrDim.width;
        int pHeight = scrDim.height;
        // System.out.println("Panel (w,h) : (" + pWidth + ", " + pHeight + ")");

        // listen for key presses
        setFocusable(true);
        requestFocus();    // the JPanel now has focus, so receives key events
        initKeyListener();

        paddleAndBall.add(new Ball(pWidth/2, pHeight/2));
        initPaddlesAndGoal(pWidth, pHeight, (Ball) (paddleAndBall.get(0)));

        new Thread(this).start();   // start updating the panel
    }
    private void initKeyListener()
    // define keys for stopping
    {
        addKeyListener( new KeyAdapter() {
            public void keyPressed(KeyEvent e)
            {
                int keyCode = e.getKeyCode();
                if ((keyCode == KeyEvent.VK_ESCAPE) || (keyCode == KeyEvent.VK_Q) ||
                        ((keyCode == KeyEvent.VK_C) && e.isControlDown()) )
                    // ESC, q, ctrl-c to stop isRunning
                    isRunning = false;
            }
        });
    }  // end of initKeyListener()

    public void initPaddlesAndGoal(int pWidth, int pHeight, Ball ball) {
        paddleAndBall.add(new OutsideObstacle(ball, pWidth/2, pHeight/2 , -1.1, Color.BLACK, pHeight/2-ball.getSize()));
        ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment();
        Controller[] ca = ce.getControllers();
        if (ca.length == 0) {
            System.out.println("No controllers found");
            System.exit(0);
        }

        // collect the IDs of all the mouse controllers
        int[] mouseIDs = new int[ca.length];
        int mouseCount = 0;
        System.out.println("Mouse Controllers:");
        for (int i = 0; i < ca.length; i++) {
            if (ca[i].getType() == Controller.Type.MOUSE) {
                System.out.println("  ID " + i + "; \"" + ca[i].getName() +"\"");
                mouseIDs[mouseCount++] = i;
            }
        }

        if (mouseCount < 2) {
            System.out.println("Not enough found (" + mouseCount + "); 2 needed");
            System.exit(0);
        }
        for(int i = mouseCount - 1; i >= 0; i--) {
            int idx = mouseIDs[i];

            System.out.println("\nInitializing mouse ID " + idx + "...");
            //Wat
            System.out.println(ca[i].getType());
            if(ca[i].getType() == Controller.Type.KEYBOARD) {
                mouseCount--;
                continue;
            }
            double theta = 2 * Math.PI * i / mouseCount;
            Point2D.Double position = PositionUtils.findPosition(pWidth, pHeight, 3 * pHeight / 8, theta);
            paddleAndBall.add(new Paddle(new RealMouse((Mouse) ca[idx]), ColorUtils.findColor(theta), position.x, position.y, ball));
            paddleAndBall.add(new Goal(ball, pWidth/2-(position.x-pWidth/2), pHeight/2-(position.y-pHeight/2),ColorUtils.findColor(theta)));
        }

    }  // end of initFingerTips()

    private void update()
  /* update the finger tip controllers, and then determine how they
     affect the images on screen */ {
        // update the finger tip controllers
        for(int i = 0; i < paddleAndBall.size(); i++) {
            paddleAndBall.get(i).update();
        }
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(int i = 0; i < paddleAndBall.size(); i++) {
            paddleAndBall.get(i).draw(g);
        }
    } // end of paintComponent()
    public void run() {
        long duration;
        isRunning = true;

        while(isRunning) {
            long startTime = System.currentTimeMillis();
            update();
            duration = System.currentTimeMillis() - startTime;
            // System.out.println(" update duration: " + duration);
            repaint();

            if (duration < DELAY) {
                try {
                    Thread.sleep(DELAY-duration);  // wait until DELAY time has passed
                }
                catch (Exception ex) {}
            }
        }
        System.exit(0);
    }
    public void closeDown()
    {  isRunning = false; }

}
