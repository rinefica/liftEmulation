package liftEmu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
            MainApp.class.getResource("/MainView.fxml"));
        AnchorPane page = loader.load();
        Scene scene = new Scene(page);

        stage.setOnCloseRequest(event -> {
            MainController.cancelTimer();
        });

        stage.setTitle("liftEmulation");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
