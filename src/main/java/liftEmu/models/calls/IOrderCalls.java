package liftEmu.models.calls;

public interface IOrderCalls {

    long getTimeNextStop();
    int getNextStop();

    void addCall(Call call);

}
