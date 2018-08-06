public class ModUtils {
    public static double mod(double modee, double modder) {
        return modee - Math.floor(modee/modder)*modder;
    }
}
