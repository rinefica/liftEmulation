package liftEmu.models.calls;

import liftEmu.models.Lift;

import java.util.LinkedList;
import java.util.Queue;

public class OrderCalls implements IOrderCalls{

    LinkedList<Stop> order = new LinkedList<>();

    @Override
    public long getTimeNextStop() {
        if (order.size() == 0) {
            return 0;
        }

        return order.peek().getTime();
    }

    @Override
    public int getNextStop() {
        if (order.size() == 0) {
            return 0;
        }

        return order.poll().getFloor();
    }

    @Override
    public void addCall(Call call) {
        if (order.size() == 0) {
            long currentTime = System.currentTimeMillis() / 1000;
            long time = currentTime +
                Lift.TIME_ONE_FLOOR_SEC * Math.abs(getCurrentFloor() - call.getFloor());
            order.add(new Stop(call, time));

//            System.out.println("now: " + currentTime + " add " + call.getFloor() + " time: " + time );
        }
        if (call.type == Call.TYPE.INNER) {
            int curIndex = -1;

            for (int i = 0; i < order.size(); i++) {
                if (order.get(i).type == Call.TYPE.OUTER) {
                    curIndex = i;
                    break;
                }
            }

            if (curIndex == -1) {
                order.addLast(new Stop(call, 0));
            } else {
                order.add(curIndex, new Stop(call, 0));
            }
        }
    }

    int getCurrentFloor() {
        return Lift.getInstance().getCurrentFloor();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Stop s : order){
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    public class Stop extends Call {
        private long time;

        public Stop(Call call, long time) {
            super(call.type, call.dir, call.floor);
            this.time = time;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "Stop: " +
                "in time: " + time +
                "\t" + type +
                "\t" + dir +
                "\tfloor: " + floor + '\n';
        }
    }
}
