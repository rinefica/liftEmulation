package liftEmu.models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import liftEmu.models.calls.Call;

public class Lift {

    public enum STATE {
        MOVIE,
        START,
        STOP,
        NONE
    }

    public static final int COUNT_FLOORS = 7;
    public static final int TIME_ONE_FLOOR_SEC = 1;
    public static final int TIME_START_STOP_SEC = 2;
//    public static final int TIME_DELAY_SEC = 5;


    private IntegerProperty currentFloor = new SimpleIntegerProperty(1);
    private StringProperty currentDirection =
        new SimpleStringProperty(Call.DIRECTION.UP.toString());
    private StringProperty currentState = new SimpleStringProperty(STATE.NONE.toString());


    public int getCurrentFloor() {
        return currentFloor.get();
    }

    public IntegerProperty currentFloorProperty() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor.set(currentFloor);
    }

    public Call.DIRECTION getCurrentDirection() {
        return Call.DIRECTION.valueOf(currentDirection.get());
    }

    public StringProperty currentDirectionProperty() {
        return currentDirection;
    }

    public void setCurrentDirection(String currentDirection) {
        this.currentDirection.set(currentDirection);
    }

    public STATE getCurrentState() {
        return STATE.valueOf(currentState.get());
    }

    public StringProperty currentStateProperty() {
        return currentState;
    }

    public void setCurrentState(STATE currentState) {
        this.currentState.set(currentState.toString());
    }

    public ObservableList getListFloors() {
        ObservableList<Integer> floors = FXCollections.observableArrayList();
        for (int i = 1; i <= COUNT_FLOORS; i++)
            floors.add(i);

        return floors;
    }

    public ObservableList getListDirections() {
        return FXCollections.observableArrayList(Call.DIRECTION.values());
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
