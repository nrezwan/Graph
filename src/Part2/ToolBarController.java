package Part2;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

public class ToolBarController {
    @FXML ToggleGroup colorGroup;
    @FXML ToggleGroup actionGroup;
    @FXML ToggleButton redButton;
    @FXML ToggleButton greenButton;
    @FXML ToggleButton blueButton;
    @FXML ToggleButton createButton;
    @FXML ToggleButton deleteButton;

    public ToolBarController(){}
    public void greenButtonClick() {
        greenButton.setSelected(true);
        redButton.setSelected(false);
        blueButton.setSelected(false);
        Main.interactionModel.setSelectedColor(Color.LIGHTGREEN);
    }
    public void redButtonClick() {
        redButton.setSelected(true);
        greenButton.setSelected(false);
        blueButton.setSelected(false);
        Main.interactionModel.setSelectedColor(Color.SALMON);
    }
    public void blueButtonClick() {
        blueButton.setSelected(true);
        redButton.setSelected(false);
        greenButton.setSelected(false);
        Main.interactionModel.setSelectedColor(Color.LIGHTBLUE);
    }
    public void createButtonClick() {
        Main.interactionModel.setIsCreatingVertex();
        createButton.setSelected(true);
        deleteButton.setSelected(false);
    }
    public void deleteButtonClick() {
        Main.interactionModel.setIsDeletingVertex();
        createButton.setSelected(false);
        deleteButton.setSelected(true);
    }
}

