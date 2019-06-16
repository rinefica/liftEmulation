package liftEmu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import liftEmu.models.IStateLift;
import liftEmu.models.Lift;
import liftEmu.models.calls.Call;
import liftEmu.models.calls.IOrderCalls;
import liftEmu.models.calls.OrderCalls;

import java.util.Timer;
import java.util.TimerTask;

public class MainController {
    @FXML
    public Text textCurFloor;
    @FXML
    public ChoiceBox outerCallFloor;
    @FXML
    public ChoiceBox innerCallFloor;
    @FXML
    public Text textCurDir;
    @FXML
    public ChoiceBox outerCallDir;
    @FXML
    public Button btnOuterCall;
    @FXML
    public Button btnInnerCall;
    @FXML
    public TextArea textOrderStop;
    @FXML
    public Text textCurState;

    private IStateLift lift = Lift.getInstance();
    private IOrderCalls calls = new OrderCalls();
    private static Timer timer = new Timer();
    private static Timer timerMovie = new Timer();
    private static Timer timerStart = new Timer();

    private static TimerTask taskStart;
    private static TimerTask taskFloor;

    @FXML
    void initialize() {
        outerCallFloor.setItems(lift.getListFloors());
        innerCallFloor.setItems(lift.getListFloors());
        outerCallDir.setItems(lift.getListDirections());

        btnOuterCall.setOnAction(event -> {
            doCall(Call.TYPE.OUTER,
            IStateLift.DIRECTION.valueOf(outerCallDir.getValue().toString()),
                Integer.valueOf(outerCallFloor.getValue().toString()));
        });

        btnInnerCall.setOnAction(event -> {
            int selectedFloor = Integer.valueOf(innerCallFloor.getValue().toString());

            IStateLift.DIRECTION dir = lift.getCurrentFloor() - selectedFloor > 0 ?
                IStateLift.DIRECTION.DOWN : IStateLift.DIRECTION.UP;

            doCall(Call.TYPE.INNER, dir, selectedFloor);
        });

        Lift.getInstance().currentFloorProperty().addListener((event) ->
            textCurFloor.setText(String.valueOf(lift.getCurrentFloor())));
        textCurDir.textProperty().bind(Lift.getInstance().currentDirectionProperty());
        textCurState.textProperty().bind(Lift.getInstance().currentStateProperty());

        setTimer();
    }

    /**
     *
     * @param type - outer\inner
     * @param dir - up\down
     * @param floor - target floor
     *
     * creates call, adds it to lift`s order and change current lift`s direction
     * create timer for start movie
     */
    private void doCall(Call.TYPE type, IStateLift.DIRECTION dir, int floor) {
        calls.addCall(new Call(type, dir, floor));
        if (lift.getCurrentState() == IStateLift.STATE.NONE) {
            timerStart();

            if (floor > lift.getCurrentFloor()) {
                lift.setCurDir(IStateLift.DIRECTION.UP);
            } else {
                lift.setCurDir(IStateLift.DIRECTION.DOWN);
            }
        }
    }

    /**
     * timer works every sec and checks current data as floor and target floor
     */
    private void setTimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                textOrderStop.setText(calls.toString());
                if (lift.getCurrentFloor() == calls.getNextStop()) {
                    calls.doStop();
                    if (calls.getNextStop() != 0) {
                        if (calls.getNextStop() > lift.getCurrentFloor()) {
                            lift.setCurDir(IStateLift.DIRECTION.UP);
                        } else {
                            lift.setCurDir(IStateLift.DIRECTION.DOWN);
                        }
                    }
                    lift.setCurrentState(IStateLift.STATE.STOP);
                    taskFloor.cancel();
                    timerStart();
                }
            }
        };

        timer.schedule(task, 1000, 1000);
    }

    /**
     * timer sets start state of lift and set timer for movie
     */
    private void timerStart() {
        taskStart = new TimerTask() {
            @Override
            public void run() {
                if (calls.getNextStop() != 0) {
                    lift.setCurrentState(IStateLift.STATE.START);
                    timerMovie();
                } else {
                    lift.setCurrentState(IStateLift.STATE.NONE);
                }
            }
        };

        timerStart.schedule(taskStart, IStateLift.TIME_START_STOP_SEC * 1000);
    }

    /**
     * timer sets movie state of lift and every 10 sec check current floor
     */
    private void timerMovie() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                lift.setCurrentState(Lift.STATE.MOVIE);
            }
        };

        taskFloor = new TimerTask() {
            @Override
            public void run() {
                int newFloor = lift.getCurrentFloor();
                if (lift.getCurDir() == IStateLift.DIRECTION.UP) {
                    newFloor++;
                } else {
                    newFloor--;
                }
                lift.setCurrentFloor(newFloor);
            }
        };

        timerMovie.schedule(task, IStateLift.TIME_START_STOP_SEC * 1000);
        timerMovie.schedule(taskFloor,
             IStateLift.TIME_START_STOP_SEC * 1000 + IStateLift.TIME_ONE_FLOOR_SEC * 1000,
            IStateLift.TIME_ONE_FLOOR_SEC * 1000);
    }


    public static void cancelTimer(){
        timer.cancel();
        timerMovie.cancel();
        timerStart.cancel();
    }


}
