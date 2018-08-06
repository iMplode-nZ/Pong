import java.awt.*;
import java.awt.geom.Point2D;

public abstract class GraphicsObject {
    double x;
    double y;
    abstract public int getSize();
    abstract public void draw(Graphics g);
    abstract public void update();
    public Point2D.Double getLocation() {
        return new Point2D.Double(x,y);
    }
}
