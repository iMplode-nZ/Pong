import java.awt.*;

public class Goal extends GraphicsObject {
    int score = 0;
    Ball ball;

    Color c;
    public Goal (Ball ball, int xPos, int yPos, boolean isLeft) {
        this.ball = ball;
        x = xPos;
        y = yPos;
        c = ColorUtils.findColor(isLeft);
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(c);
        g.drawOval((int) x-getSize(),(int) y-getSize(),getSize()*2,getSize()*2);
        g.setColor(Color.black);
        g.drawString(""+score,(int) x,(int) y);
    }

    @Override
    public int getSize() {
        return 100;
    }

    @Override
    public void update() {
        if(ball.getLocation().distance(getLocation()) < getSize()+ball.getSize()) {
            score ++;
            ball.resetLocation();
        }
    }
    public int getScore() {
        return score;
    }

}
