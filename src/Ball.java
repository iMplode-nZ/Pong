import java.awt.*;

public class Ball extends GraphicsObject{


    protected double xVelocity=0;
    protected double yVelocity=0;
    public Boolean isLeftHit;
    private final static double acceleration = 1.1;
    int width;
    int height;
    public Ball(int pWidth, int pHeight) {
        x = pWidth/2;
        y = pHeight/2;
        width = pWidth;
        height = pHeight;
    }
    public void update() {
        x += xVelocity;
        y += yVelocity;
        xVelocity /= acceleration;
        yVelocity /= acceleration;
        x = ModUtils.mod(x,width);
        y = ModUtils.mod(y,height);
    }
    public void draw(Graphics g) {
        g.setColor(Color.black);
        if(isLeftHit != null) {
            g.setColor(ColorUtils.findColor(isLeftHit));
        }
        g.fillOval((int) x-getSize(),(int) y-getSize(),getSize()*2, getSize()*2);
    }
    public int getSize() {
        return 25;
    }

    public void resetLocation() {
        x = width/2;
        y = height/2;
        xVelocity = 0;
        yVelocity = 0;
    }

}
