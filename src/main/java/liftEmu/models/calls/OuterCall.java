package liftEmu.models.calls;

public class OuterCall extends InnerCall{
    private Direction dir;

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public OuterCall(int floor, Direction dir) {
        super(floor);
        this.dir = dir;
    }

}
