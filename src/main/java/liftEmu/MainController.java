package liftEmu;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import liftEmu.models.Lift;

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

    private Lift lift = Lift.getInstance();

    @FXML
    void initialize() {
        outerCallFloor.setItems(lift.getListFloors());
        outerCallFloor.setOnAction(event -> {
            int newFloor = Integer.valueOf(outerCallFloor.getValue().toString());
            lift.setCurrentFloor(newFloor);
        });

        innerCallFloor.setItems(lift.getListFloors());

        outerCallDir.setItems(lift.getListDirections());
        outerCallDir.setOnAction(event -> {
            lift.setCurrentDirection(outerCallDir.getValue().toString());
        });

        lift.currentFloorProperty().addListener((event) ->
            textCurFloor.setText(String.valueOf(lift.getCurrentFloor())));
        textCurDir.textProperty().bind(lift.currentDirectionProperty());

    }
}
