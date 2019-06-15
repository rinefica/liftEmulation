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

    private long timeNextStop = 0;
    private long timeNextFloor = 0;
    private long timeStartMovie = 0;
    private long timePrevStop = 0;

    @Override
    public long getTimeNextStop() {
        return timeNextStop;
    }

    @Override
    public void resetState() {
        switch (curState){
            case NONE:
                break;

            case MOVIE:
                break;

            case START:
                break;

            case STOP:
                break;
        }
    }

    @Override
    public void startMovie(int toFloor) {
        curState = STATE.START;
        currentStateProperty.set(STATE.START.toString());
        timeNextStop = Math.abs(currentFloor.get() - toFloor) * IStateLift.TIME_ONE_FLOOR_SEC
            + System.currentTimeMillis() / 1000;

    }

    @Override
    public void continueMovie(int toFloor) {
        curState = STATE.START;
        currentStateProperty.set(STATE.START.toString());
        timeNextStop = Math.abs(currentFloor.get() - toFloor) * IStateLift.TIME_ONE_FLOOR_SEC
            + System.currentTimeMillis() / 1000;
    }

    @Override
    public void stopMovie() {
        curState = STATE.STOP;
        currentStateProperty.set(STATE.STOP.toString());

        timePrevStop = timeNextStop + 2;
        timeNextStop = 0;
    }

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
