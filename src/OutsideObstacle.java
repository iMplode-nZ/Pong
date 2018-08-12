import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class OutsideObstacle extends GraphicsObject {
    public double change;
    Ball ball;
    Color color;
    int theSize;
    ArrayList<Paddle> allPaddles;
    public OutsideObstacle(Ball thisBall, double xPos, double yPos, double changer, Color whichColor, int size, ArrayList<Paddle> paddles) {
        x = xPos;
        y = yPos;
        ball = thisBall;
        change = changer;
        color = whichColor;
        theSize = size;
        allPaddles = paddles;
    }

    @Override
    public int getSize() {
        return theSize;
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawOval((int) x-getSize(),(int) y-getSize(),getSize()*2,getSize()*2);
    }

    @Override
    public void update() {
        updatePositionOfPaddle();
        updateVelocityOfBall();
    }
    private void updatePositionOfPaddle() {
        for(int i = 0; i < allPaddles.size(); i ++) {
            Paddle p = allPaddles.get(i);
            if (getLocation().distance(p.getLocation()) > getSize() - p.getSize()) {
                double theta = Math.atan2((p.y - y),(p.x - x));
                double r = getSize() - p.getSize();
                p.x = r*Math.cos(theta)+x;
                p.y = r*Math.sin(theta)+y;
            }
        }
    }
    private void updateVelocityOfBall() {
        if((getLocation().distance(ball.getLocation())<(getSize()-ball.getSize()))) {
            return;
        }

        double distance =  getLocation().distance(ball.getLocation());
        Point2D.Double direction = new Point2D.Double(ball.x-getLocation().x,ball.y-getLocation().y);

        direction = new Point2D.Double(direction.x/distance, direction.y/distance);
        double secondDistance =  getLocation().distance(ball.getLocation())-getSize()+ball.getSize();
        direction = new Point2D.Double( (direction.x*secondDistance*change), (direction.y*secondDistance*change));
        ball.xVelocity += direction.x;
        ball.yVelocity += direction.y;
    }
}
