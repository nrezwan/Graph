package Part2;


import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class InteractionModel extends Pane {
    private Color selectedColor;
    private boolean creatingVertex;
    private FlowPane toolBar;
    InteractionModel() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("toolbar.fxml"));
        this.toolBar = fxmlLoader.load();
        this.getChildren().add(toolBar);
    }
    public Color getSelectedColor() {
        return selectedColor;
    }
    public void setSelectedColor(Color color) {
        this.selectedColor = color;
    }
    public void setIsCreatingVertex() {
        this.creatingVertex = true;
    }
    public void setIsDeletingVertex() {
        this.creatingVertex = false;
    }
    public boolean isCreatingVertex() {
        return creatingVertex;
    }
}
