package Part1;

import javafx.scene.input.MouseButton;

public class GraphViewController {
    private Vertex moveableVertex;
    private Vertex connectedVertex;
    private Vertex connectVertex;
    public GraphViewController() {
        try {
            Main.graphView.setOnMousePressed(e -> {
                double x = e.getX();
                double y = e.getY();
                Vertex selectedVertex = Main.graphmodel.getVertexat(x, y);
                if (e.getButton() == MouseButton.PRIMARY && selectedVertex == null && !e.isShiftDown()) {
                    Main.graphmodel.addVertex(x, y);
                } else if (selectedVertex != null && !e.isShiftDown()) {
                    selectedVertex.selected = true;
                    moveableVertex = selectedVertex;
                } else if (e.isShiftDown()) {
                    assert selectedVertex != null;
                    selectedVertex.setConnected(true);
                    connectedVertex = selectedVertex;
                }
                Main.graphView.layoutChildren();
            });
            Main.graphView.setOnMouseReleased(e -> {
                if (moveableVertex!=null) {
                    moveableVertex.selected = false;
                    moveableVertex=null;
                }
                double x =  e.getX();
                double y =  e.getY();
                Vertex selectedVertex = Main.graphmodel.getVertexat(x, y);
                if (connectVertex !=null && selectedVertex !=null) {
                    Main.graphmodel.addEdge(connectedVertex,connectVertex);
                    connectedVertex.connected = false;
                    connectVertex=null;
                }
                if(connectVertex!=null) {
                    connectVertex.connected = false;
                    connectedVertex=null;
                }
                Main.graphView.layoutChildren();
            });
            Main.graphView.setOnMouseDragged(e -> {
                double x =  e.getX();
                double y = e.getY();
                if (connectedVertex != null && e.isShiftDown()) {
                    connectVertex = Main.graphmodel.getVertexat(x, y);
                    Main.graphView.drag.setStartX(connectedVertex.x);
                    Main.graphView.drag.setStartY(connectedVertex.y);
                    Main.graphView.drag.setEndX(e.getX());
                    Main.graphView.drag.setEndY(e.getY());
                    Main.graphView.dragbool=true;
                    Main.graphView.layoutChildren();
                    Main.graphView.dragbool=false;
                } else if (moveableVertex != null && !e.isShiftDown()) {
                    moveableVertex.setX(x);
                    moveableVertex.setY(y);
                    Main.graphView.layoutChildren();
                }
            });
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}