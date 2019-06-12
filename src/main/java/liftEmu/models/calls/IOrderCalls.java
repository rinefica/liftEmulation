package liftEmu.models.calls;

import javafx.util.Pair;

public interface IOrderCalls {

    long getTimeNextStop();
    int getNextStop();

    void addCall(Call call);

}
