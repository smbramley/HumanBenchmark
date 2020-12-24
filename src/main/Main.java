package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.backend.Home;
import javafx.scene.media.AudioClip;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Human Benchmark By Shane Bramley");

        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("resources/home.fxml"));

        GridPane root = loader.load();
        root.setStyle("-fx-background-color: #e1e1e1;");
        Scene scene = new Scene(root);

        Home home = loader.getController();
        home.setScene(scene);

        Image cursorImage = new Image(getClass().getResource("/resource/Cursor.png").toURI().toString());
        scene.setCursor(new ImageCursor(cursorImage));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
