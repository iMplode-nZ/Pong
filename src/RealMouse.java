import net.java.games.input.Mouse;

public class RealMouse implements MouseInterface {
    Mouse actualMouse;

    public RealMouse(Mouse mouse) {
        actualMouse = mouse;
    }

    @Override
    public int getXPollData() {
        return (int) actualMouse.getX().getPollData();
    }

    @Override
    public int getYPollData() {
        return (int) actualMouse.getY().getPollData();
    }

    @Override
    public void poll() {
        if(!actualMouse.poll()) {
            System.err.println("Mouse polling is false. Mouse is no longer secure. Shutting down.");
            System.exit(1);
        }
    }
}
