import java.awt.*;

public class Ball extends GraphicsObject{


    protected double xVelocity=0;
    protected double yVelocity=0;
    private final static double acceleration = 1.1;
    double begX;
    double begY;
    public Ball(double startX, double startY) {
        x = startX;
        y = startY;
        begX = x;
        begY = y;
    }
    public void update() {
        x += xVelocity;
        y += yVelocity;
        xVelocity /= acceleration;
        yVelocity /= acceleration;
    }
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillOval((int) x-getSize(),(int) y-getSize(),getSize()*2, getSize()*2);
    }
    public int getSize() {
        return 25;
    }

    public void resetLocation() {
        x = begX;
        y = begY;
        xVelocity = 0;
        yVelocity = 0;
    }

}
