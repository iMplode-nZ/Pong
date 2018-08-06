import java.awt.*;

public class ColorUtils {
    public static Color findColor(boolean isLeft) {
        return isLeft? Color.red : Color.blue;
    }
}
