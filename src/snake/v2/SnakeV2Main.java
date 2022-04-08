package snake.v2;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
//import snake.v2.SnakeV2FXMLController;

public class SnakeV2Main extends Application {

    public static KeyCode key = KeyCode.UP;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("snakeV2FXML.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Snake v2");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                key = event.getCode();
            }
        });
//        SnakeV2FXMLController.canvas;
    }

    public static void main(String[] args) {
        launch(args);
    }

}

//enum tableData {
//    ABYSS, HEAD, SNAKE, FOOD, BRICK
//}
//
//enum pointOfView {
//    UP, RIGHT, DOWN, LEFT
//}
