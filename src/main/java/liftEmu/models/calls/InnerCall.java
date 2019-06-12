package liftEmu.models.calls;

public class InnerCall {
    public enum Direction {
        UP,
        DOWN
    }

    private int floor;

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public InnerCall(int floor) {
        this.floor = floor;
    }
}
