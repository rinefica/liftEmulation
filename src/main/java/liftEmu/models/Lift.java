package liftEmu.models;

import javafx.beans.property.*;
import liftEmu.models.calls.Call;

public class Lift implements IStateLift{

    public enum STATE {
        MOVIE,
        START,
        STOP,
        NONE
    }

    private IntegerProperty currentFloor = new SimpleIntegerProperty(1);

    private Call.DIRECTION curDir = Call.DIRECTION.UP;
    private StringProperty currentDirectionProperty = new SimpleStringProperty(curDir.toString());

    private STATE curState = STATE.NONE;
    private StringProperty currentStateProperty = new SimpleStringProperty(curState.toString());


    @Override
    public int getCurrentFloor() {
        return currentFloor.get();
    }

    public IntegerProperty currentFloorProperty() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor.set(currentFloor);
    }

    public Call.DIRECTION getCurDir() {
        return curDir;
    }

    public StringProperty currentDirectionProperty() {
        return currentDirectionProperty;
    }

    public void setCurDir(Call.DIRECTION curDir) {
        this.curDir = curDir;
        this.currentDirectionProperty.set(curDir.toString());
    }

    public STATE getCurrentState() {
        return curState;
    }

    public StringProperty currentStateProperty() {
        return currentStateProperty;
    }

    public void setCurrentState(STATE currentState) {
        this.currentStateProperty.set(currentState.toString());
        this.curState = currentState;
    }

    private static Lift lift;

    private Lift() {
    }

    public static Lift getInstance() {
        if (lift == null) {
            lift = new Lift();
        }

        return lift;
    }
}
