public class FakeMouse implements MouseInterface{
    @Override
    public int getXPollData() {
        return 0;
    }

    @Override
    public int getYPollData() {
        return 0;
    }

    @Override
    public void poll() {
    }
}
