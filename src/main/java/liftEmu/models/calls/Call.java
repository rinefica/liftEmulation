package liftEmu.models.calls;

import liftEmu.models.IStateLift;

public class Call {
    public enum TYPE {
        INNER,
        OUTER
    }

    private TYPE type;
    private IStateLift.DIRECTION dir;

    private int floor;

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setDir(IStateLift.DIRECTION dir) {
        this.dir = dir;
    }

    public IStateLift.DIRECTION getDir() {
        return dir;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public Call(TYPE type, IStateLift.DIRECTION dir, int floor) {
        this.type = type;
        this.dir = dir;
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Call: " +
            "\t" + type +
            "\t" + dir +
            "\tfloor: " + floor +
            '\n';
    }
}
