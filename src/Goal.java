import java.awt.*;

public class Goal extends GraphicsObject {
    int score = 0;
    Ball ball;
    Paddle thisPaddle;
    Color c;
    public Goal (Ball ball, Paddle whichPaddle) {
        this.ball = ball;
        x = whichPaddle.x;
        y = whichPaddle.y;
        c = whichPaddle.thisColor;
        thisPaddle = whichPaddle;
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(c);
        g.fillOval((int) x-getSize(),(int) y-getSize(),getSize()*2,getSize()*2);
        g.setColor(Color.black);
        g.drawString(""+score,(int) x,(int) y);
    }

    @Override
    public int getSize() {
        return 100;
    }

    @Override
    public void update() {
        if (ball.getLocation().distance(getLocation()) < getSize() + ball.getSize()) {
            score++;
            thisPaddle.thisSize += (getSize() - thisPaddle.thisSize)/4;
            ball.resetLocation();
        }
    }
}
