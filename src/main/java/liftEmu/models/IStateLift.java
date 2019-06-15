package liftEmu.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import liftEmu.models.calls.Call;

public interface IStateLift {

    int COUNT_FLOORS = 7;
    int TIME_ONE_FLOOR_SEC = 10;
    int TIME_START_STOP_SEC = 2;

    long getTimeNextStop();

    void resetState();

    void startMovie(int toFloor);
    void continueMovie(int toFloor);
    void stopMovie();

    int getCurrentFloor();

    default ObservableList getListFloors() {
        ObservableList<Integer> floors = FXCollections.observableArrayList();
        for (int i = 1; i <= COUNT_FLOORS; i++)
            floors.add(i);

        return floors;
    }

    default ObservableList getListDirections() {
        return FXCollections.observableArrayList(Call.DIRECTION.values());
    }
}
