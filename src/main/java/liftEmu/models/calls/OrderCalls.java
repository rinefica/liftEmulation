package liftEmu.models.calls;


import javafx.util.Pair;
import liftEmu.models.Lift;

import java.util.Date;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class OrderCalls implements IOrderCalls{

    Queue<Pair<Long, Integer>> order = new LinkedList<>();

    @Override
    public long getTimeNextStop() {
        if (order.size() == 0) {
            return 0;
        }
        return order.peek().getKey();
    }

    @Override
    public int getNextStop() {
        if (order.size() == 0) {
            return 0;
        }

        return order.poll().getValue();
    }

    @Override
    public void addCall(Call call) {
        if (order.size() == 0) {
            long currentTime = System.currentTimeMillis() / 1000;
            long time = currentTime + Lift.TIME_ONE_FLOOR_SEC * Math.abs(getCurrentFloor() - call.getFloor());
            order.add(new Pair<>(time, call.getFloor()));

            System.out.println("now: " + currentTime + " add " + call.getFloor() + " time: " + time );
        }
    }

    int getCurrentFloor() {
        return Lift.getInstance().getCurrentFloor();
    }
}
