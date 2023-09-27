package Part1;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static final int radius = 50;
    public static GraphModel graphmodel = new GraphModel(radius);
    public static GraphView graphView = new GraphView();
    public static GraphViewController graphViewController = new GraphViewController();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Graph Demo!");
        primaryStage.setScene(new Scene(graphView, 500, 500));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
