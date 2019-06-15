package liftEmu.models.calls;

public interface IOrderCalls {

    int getNextStop();
    void addCall(Call call);
    void doStop();

}
