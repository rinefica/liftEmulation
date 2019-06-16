package liftEmu.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface IStateLift {
    enum STATE {
        MOVIE,
        START,
        STOP,
        NONE
    }

    enum DIRECTION {
        UP,
        DOWN
    }

    int COUNT_FLOORS = 7;
    int TIME_ONE_FLOOR_SEC = 10;
    int TIME_START_STOP_SEC = 2;

    int getCurrentFloor();
    void setCurrentFloor(int currentFloor);

    STATE getCurrentState();
    void setCurrentState(Lift.STATE currentState);

    DIRECTION getCurDir();
    void setCurDir(DIRECTION curDir);


    //list to display in JavaFX ChoiceBox
    default ObservableList getListFloors() {
        ObservableList<Integer> floors = FXCollections.observableArrayList();
        for (int i = 1; i <= COUNT_FLOORS; i++)
            floors.add(i);

        return floors;
    }

    //list to display in JavaFX ChoiceBox
    default ObservableList getListDirections() {
        return FXCollections.observableArrayList(DIRECTION.values());
    }
}
