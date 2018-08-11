import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

class PongPanelTest {

    @Test
    public void paddleIntersectionTest() {
        Ball thisBall = new Ball(4000,1000);
        Paddle thisPaddle = new Paddle(new FakeMouse(), Color.blue,4000,1000, thisBall);
        thisBall.x= 35 + (int) thisPaddle.getLocation().getX();
        thisBall.y = (int) thisPaddle.getLocation().getY();
        thisBall.xVelocity = 0;
        thisBall.yVelocity = 0;
        thisPaddle.update();
        thisBall.update();
        System.out.println(thisBall.xVelocity);
        assertTrue(thisBall.xVelocity > 0, "xVelocity =" + thisBall.xVelocity);
    }
    @Test
    public void diagonalTest() {
        Ball thisBall = new Ball(2000,500);
        Paddle thisPaddle = new Paddle(new FakeMouse(),Color.green,4000,1000, thisBall);
        thisBall.x= 25 + (int) thisPaddle.getLocation().getX();
        thisBall.y = 25 + (int) thisPaddle.getLocation().getY();
        thisBall.xVelocity = 0;
        thisBall.yVelocity = 0;
        thisPaddle.update();
        thisBall.update();
        assertTrue(thisBall.xVelocity > 0 && thisBall.xVelocity == thisBall.yVelocity, "xVelocity =" + thisBall.xVelocity + " yVelocity = "+ thisBall.yVelocity);
    }
    @Test
    public void modTest() {
        assertTrue(ModUtils.mod(-26.2930, 10) == -26.2930+30, ""+ModUtils.mod(-26.2930, 10));
    }
}
