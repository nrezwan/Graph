package Part2;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
    public static final int radius = 50;
    public static GraphModel graphmodel = new GraphModel(radius);
    public static GraphView graphView = new GraphView();
    public static GraphViewController graphViewController = new GraphViewController();
    public static InteractionModel interactionModel;
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane border = new BorderPane();
        interactionModel = new InteractionModel();
        border.setTop(interactionModel);
        border.setCenter(graphView);
        scene = new Scene(border, 500, 500);
        scene.setCursor(Cursor.DEFAULT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Scene getScene(){
        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
