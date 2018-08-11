import java.awt.geom.Point2D;

public class PositionUtils {
    public static Point2D.Double findPosition(int pWidth, int pHeight, double radius, double angle) {
        return new Point2D.Double(pWidth/2+radius*Math.cos(angle),pHeight/2+radius*Math.sin(angle));
    }
}
