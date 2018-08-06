import java.awt.*;
import java.awt.geom.Point2D;

public class Obstacle extends GraphicsObject {
    public double change;
    Ball ball;
    Color color;

    public Obstacle(Ball thisBall, double xPos, double yPos, double changer, Color whichColor) {
        x = xPos;
        y = yPos;
        ball = thisBall;
        change = changer;
        color = whichColor;
    }

    @Override
    public int getSize() {
        return 50;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int) x-getSize(),(int) y-getSize(),getSize()*2,getSize()*2);
    }

    @Override
    public void update() {
        updateVelocityOfBall();
    }
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
}
