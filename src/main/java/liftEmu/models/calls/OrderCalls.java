package liftEmu.models.calls;

import liftEmu.models.IStateLift;
import liftEmu.models.Lift;

import java.util.LinkedList;

public class OrderCalls implements IOrderCalls{

    private volatile LinkedList<Call> order = new LinkedList<>();

    @Override
    public int getNextStop() {
        if (order.isEmpty()) {
            return 0;
        }

        return order.peekFirst().getFloor();
    }

    /**
     *
     * @param call
     * call is inserted in list of calls in right order: list`s sort depends on type of call and
     * target\current floor
     */
    @Override
    public void addCall(Call call) {
        int iToInsert = 0;
        if (!order.isEmpty()) {

            if (call.getType() == Call.TYPE.OUTER) {
                IStateLift.DIRECTION dir = call.getDir() == IStateLift.DIRECTION.UP ?
                    IStateLift.DIRECTION.DOWN : IStateLift.DIRECTION.UP;
                while((iToInsert < order.size()) && (order.get(iToInsert).getDir() == dir)) {
                    iToInsert++;
                }
            }

            IStateLift.DIRECTION curDir = call.getDir();
            int distance = Math.abs(getCurrentFloor() - call.getFloor());

            while ((iToInsert < order.size()) && (order.get(iToInsert).getDir() == curDir) &&
                (Math.abs(getCurrentFloor() - order.get(iToInsert).getFloor()) < distance)) {
                iToInsert++;
            }
        }

        if (iToInsert >= order.size()) {
            order.addLast(call);
        } else {
            order.add(iToInsert, call);
        }
    }

    @Override
    public void doStop() {
        order.removeFirst();
    }

    int getCurrentFloor() {
        return Lift.getInstance().getCurrentFloor();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Call s : order){
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
}
