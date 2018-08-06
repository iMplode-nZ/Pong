import net.java.games.input.Component;
import net.java.games.input.Mouse;

import java.awt.*;
import java.awt.geom.Point2D;

public class Paddle extends GraphicsObject {
    private static final double TIP_OFFSET = 100; // start posn info for finger tip images
    private MouseInterface mouseCtrl;
    // finger movement is implemented as mouse movement
    // finger pressing is implemented as left button pressing
    private static final double change =  -1;
    private double earX;
    private double earY;
    Ball ball;
    boolean isLeft;
    private int pWidth, pHeight;
    public Paddle(MouseInterface mc, boolean isLeftPaddle, int pWidth, int pHeight, Ball ball)
    {
        mouseCtrl = mc;
        System.out.print( (isLeftPaddle ? "Left " : "Right "));

        this.pWidth = pWidth;
        this.pHeight = pHeight;

        // initialize finger tip images


        if (!isLeftPaddle) {

            x = pWidth / 2 + getSize() / 2 + 400;
            y = pHeight / 2;
        }
        else { // right tip
            x = pWidth / 2 -getSize() / 2 - 400;
            y=pHeight / 2;
        }
        earX=x;
        earY=y;

        this.ball = ball;
        isLeft = isLeftPaddle;
    }  // end of FingerTipController()
    public int getSize() {
        return 50;
    }

    public Point2D.Double getPrevLoc()
    {  return new Point2D.Double(earX, earY);  }

    // ----------------- updating data ------------------


    public void update()
    // update the component values in the mouse
    {
        mouseCtrl.poll();
        updatePosition();
        updateVelocityOfBall();
    }  // end of update()





    private void updatePosition()
    {
        earX=x;
        earY=y;

        double xNew = x + mouseCtrl.getXPollData();

        xNew = ModUtils.mod(xNew,pWidth);

        double yNew = y + mouseCtrl.getYPollData();

        yNew = ModUtils.mod(yNew,pHeight);

        x=xNew;
        y=yNew;

    }  // end of updatePosition()

    private void updateVelocityOfBall() {
        if(getLocation().distance(ball.getLocation())>(getSize()+ball.getSize())) {
            return;
        }

        double distance =  getLocation().distance(ball.getLocation());
        Point2D.Double direction = new Point2D.Double(ball.x-getLocation().x,ball.y-getLocation().y);

        direction = new Point2D.Double(direction.x/distance, direction.y/distance);
        double secondDistance =  getLocation().distance(ball.getLocation())-getSize()-ball.getSize();
        direction = new Point2D.Double( (direction.x*secondDistance*change), (direction.y*secondDistance*change));
        ball.xVelocity += direction.x;
        ball.yVelocity += direction.y;

    }

    public void draw(Graphics g)
    {

        g.setColor(ColorUtils.findColor(isLeft));
        g.fillOval((int) x-getSize(),(int) y-getSize(),getSize()*2,getSize()*2);
    }  // end of draw()
}
