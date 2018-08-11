import java.awt.*;

public class ColorUtils {
    public static Color findColor(double theta) {
        return Color.getHSBColor((float) (theta/(2*Math.PI)),1,1);
    }
}
