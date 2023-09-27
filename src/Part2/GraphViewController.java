package Part2;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class GraphViewController {
    private Vertex moveableVertex;
    private Vertex connectedVertex;
    private Vertex connectVertex;
    public GraphViewController() {
        Main.graphView.addEventHandler(MouseEvent.ANY, new MouseHandler());
    }

    public class MouseHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent e) {
            try {
                if(e.getEventType()==MouseEvent.MOUSE_PRESSED) {
                    double x = e.getX();
                    double y = e.getY();
                    Vertex selectedVertex = Main.graphmodel.getVertexat(x, y);
                    if(!Main.interactionModel.isCreatingVertex()){
                        Main.graphmodel.deleteVertexAt(x,y);
                    }
                    else if(Main.interactionModel.isCreatingVertex()) {
                        if (e.getButton() == MouseButton.PRIMARY && selectedVertex == null && !e.isShiftDown()) {
                            Main.graphmodel.addVertex(x, y);
                        } else if (selectedVertex != null && !e.isShiftDown()) {
                            selectedVertex.selected = true;
                            moveableVertex = selectedVertex;
                        } else if (e.isShiftDown() && Main.interactionModel.isCreatingVertex()) {
                            assert selectedVertex != null;
                            selectedVertex.setConnected(true);
                            connectedVertex = selectedVertex;
                        }
                    }
                    Main.graphView.layoutChildren();
                }
                if(e.getEventType()==MouseEvent.MOUSE_RELEASED) {
                    if (moveableVertex != null) {
                        moveableVertex.selected = false;
                        moveableVertex = null;
                    }
                    double x = e.getX();
                    double y = e.getY();
                    Vertex selectedVertex = Main.graphmodel.getVertexat(x, y);
                    if (connectVertex != null && selectedVertex != null) {
                        Main.graphmodel.addEdge(connectedVertex, connectVertex);
                        connectedVertex.connected = false;
                        connectVertex = null;
                    }
                    if (connectVertex != null) {
                        connectVertex.connected = false;
                        connectedVertex = null;
                    }
                    Main.graphView.layoutChildren();
                }
                if(e.getEventType()==MouseEvent.MOUSE_DRAGGED) {
                    double x = e.getX();
                    double y = e.getY();
                    if (connectedVertex != null && e.isShiftDown()) {
                        connectVertex = Main.graphmodel.getVertexat(x, y);
                        Main.graphView.drag.setStartX(connectedVertex.x);
                        Main.graphView.drag.setStartY(connectedVertex.y);
                        Main.graphView.drag.setEndX(e.getX());
                        Main.graphView.drag.setEndY(e.getY());
                        Main.graphView.dragbool = true;
                        Main.graphView.layoutChildren();
                        Main.graphView.dragbool = false;
                    } else if (moveableVertex != null && !e.isShiftDown()) {
                        moveableVertex.setX(x);
                        moveableVertex.setY(y);
                        Main.graphView.layoutChildren();
                    }
                }
                if(e.getEventType()==MouseEvent.MOUSE_MOVED){
                    if(!Main.interactionModel.isCreatingVertex()) {
                        double x = e.getX();
                        double y = e.getY();
                        Vertex select = Main.graphmodel.getVertexat(x, y);
                        if (select != null) {
                            Main.getScene().setCursor(Cursor.CROSSHAIR);
                        }
                        else{
                            Main.getScene().setCursor(Cursor.DEFAULT);
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}