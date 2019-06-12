package liftEmu.models;

import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;
import liftEmu.models.calls.InnerCall;

public class Lift {
    public final int COUNT_FLOORS = 7;
    public final int TIME_ONE_FLOOR_SEC = 10;
    public final int TIME_START_STOP_SEC = 2;
//    public final int TIME_DELAY_SEC = 5;


    private IntegerProperty currentFloor = new SimpleIntegerProperty(1);
    private StringProperty currentDirection =
        new SimpleStringProperty(InnerCall.Direction.UP.toString());


    public int getCurrentFloor() {
        return currentFloor.get();
    }

    public IntegerProperty currentFloorProperty() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor.set(currentFloor);
    }

    public String getCurrentDirection() {
        return currentDirection.get();
    }

    public StringProperty currentDirectionProperty() {
        return currentDirection;
    }

    public void setCurrentDirection(String currentDirection) {
        this.currentDirection.set(currentDirection);
    }

    public ObservableList getListFloors() {
        ObservableList<Integer> floors = FXCollections.observableArrayList();
        for (int i = 1; i <= COUNT_FLOORS; i++)
            floors.add(i);

        return floors;
    }

    public ObservableList getListDirections() {
        return FXCollections.observableArrayList(InnerCall.Direction.values());
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
