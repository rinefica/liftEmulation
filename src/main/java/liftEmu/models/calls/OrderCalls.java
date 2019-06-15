package liftEmu.models.calls;

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


    @Override
    public void addCall(Call call) {
        int iToInsert = 0;
        if (!order.isEmpty()) {

            if (call.getType() == Call.TYPE.OUTER) {
                Call.DIRECTION dir = call.getDir() == Call.DIRECTION.UP ? Call.DIRECTION.DOWN : Call.DIRECTION.UP;
                while((iToInsert < order.size()) && (order.get(iToInsert).getDir() == dir)) {
                    iToInsert++;
                }
            }

            Call.DIRECTION curDir = call.getDir();
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
