package liftEmu.models;

import javafx.beans.property.*;

public class Lift implements IStateLift{

    private IntegerProperty currentFloor = new SimpleIntegerProperty(1);

    private DIRECTION curDir = DIRECTION.UP;
    //field to link with JavaFX text
    private StringProperty currentDirectionProperty = new SimpleStringProperty(curDir.toString());

    private STATE curState = STATE.NONE;
    //field to link with JavaFX text
    private StringProperty currentStateProperty = new SimpleStringProperty(curState.toString());


    @Override
    public int getCurrentFloor() {
        return currentFloor.get();
    }

    public IntegerProperty currentFloorProperty() {
        return currentFloor;
    }

    @Override
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor.set(currentFloor);
    }

    @Override
    public DIRECTION getCurDir() {
        return curDir;
    }

    public StringProperty currentDirectionProperty() {
        return currentDirectionProperty;
    }

    @Override
    public void setCurDir(DIRECTION curDir) {
        this.curDir = curDir;
        this.currentDirectionProperty.set(curDir.toString());
    }

    @Override
    public STATE getCurrentState() {
        return curState;
    }

    public StringProperty currentStateProperty() {
        return currentStateProperty;
    }

    @Override
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
