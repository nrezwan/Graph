package Part2;

import javafx.collections.ListChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineJoin;

public class GraphView extends Pane {
    public static final Color selected_Color = Color.ORANGE;
    private Canvas canvas;
    public Line drag;
    public boolean dragbool;

    public GraphView() {
        drag = new Line();
        canvas = new Canvas();
        this.getChildren().add(canvas);
        Main.graphmodel.vertexListProperty().addListener( new ListChangeListener<Vertex>(){
            @Override
            public void onChanged(Change<? extends Vertex> c) {
                requestLayout();
            }
        });
    }

    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        if (dragbool){
            gc.setLineWidth(3);
            gc.setStroke(Color.BLACK);
            gc.strokeLine(drag.getStartX(),drag.getStartY(),drag.getEndX(), drag.getEndY());
        }
        for (Edge e : Main.graphmodel.edgeListProperty()) {
                gc.setLineWidth(1);
                gc.setStroke(Color.BLACK);
                gc.setLineJoin(StrokeLineJoin.ROUND);
                gc.strokeLine(e.getStart().getX(), e.getStart().getY(), e.getEnd().getX(), e.getEnd().getY());
        }
        for (Vertex v : Main.graphmodel.vertexListProperty()) {
            if (v.isSelected()) {
                gc.setFill(selected_Color);
            }
            else {
                gc.setFill(v.getColor());
            }
            if(v.isConnected()){
                gc.setLineWidth(3);
                gc.setStroke(Color.BLACK);
                gc.strokeOval(v.getX()-v.radius/2,v.getY()-v.radius/2,v.getRadius(),v.getRadius());
            }
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(1);
            gc.fillOval(v.getX()-v.radius/2,v.getY()-v.radius/2,v.getRadius(),v.getRadius());
            gc.strokeOval(v.getX()-v.radius/2,v.getY()-v.radius/2,v.getRadius(),v.getRadius());
            int circleLabel = v.getLabel();
            gc.strokeText(Integer.toString(circleLabel),v.getX()-4,v.getY()+3);
        }
    }
    @Override
    public void layoutChildren() {
        canvas.setWidth(this.getWidth());
        canvas.setHeight(this.getHeight());
        draw();
    }
}