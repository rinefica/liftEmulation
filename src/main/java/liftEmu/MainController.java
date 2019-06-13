package liftEmu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
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

    private Lift lift = Lift.getInstance();
    private IOrderCalls calls = new OrderCalls();
    private static Timer timer = new Timer();

    @FXML
    void initialize() {
        outerCallFloor.setItems(lift.getListFloors());
        innerCallFloor.setItems(lift.getListFloors());
        outerCallDir.setItems(lift.getListDirections());

        btnOuterCall.setOnAction(event ->
            calls.addCall(new Call(Call.TYPE.OUTER,
                Call.DIRECTION.valueOf(outerCallDir.getValue().toString()),
                Integer.valueOf(outerCallFloor.getValue().toString()))));

        btnInnerCall.setOnAction(event -> {
            int selectedFloor = Integer.valueOf(outerCallFloor.getValue().toString());

            Call.DIRECTION dir = lift.getCurrentFloor() - selectedFloor > 0 ?
                Call.DIRECTION.DOWN : Call.DIRECTION.UP;

            calls.addCall(new Call(Call.TYPE.INNER, dir, selectedFloor));
        });

        lift.currentFloorProperty().addListener((event) ->
            textCurFloor.setText(String.valueOf(lift.getCurrentFloor())));
        textCurDir.textProperty().bind(lift.currentDirectionProperty());


        setTimer();

    }

    private void setTimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
//                System.out.println("cur time: " + System.currentTimeMillis() / 1000);
                textOrderStop.setText(calls.toString());
                if (System.currentTimeMillis() / 1000 == calls.getTimeNextStop()) {
//                    System.out.println("YYYY time:" + calls.getTimeNextStop());
                    lift.setCurrentFloor(calls.getNextStop());
                }
            }
        };

        timer.schedule(task, 1000, 1000);
    }



    public static void cancelTimer(){
        timer.cancel();
    }


}
