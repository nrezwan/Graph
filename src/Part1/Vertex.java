package Part1;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

import java.util.LinkedList;

public class Vertex {

    public int label;
    public double x, y, radius,height;
    public boolean selected, connected;
    private LinkedList<Edge> edgeList;

    public Vertex(int label, double xVal, double yVal, double radius, double height) {
        this.label = label;
        this.x = xVal+radius/2;
        this.y = yVal+radius/2;
        this.radius = radius;
        this.height = height;
        this.selected = false;
        this.connected = false;
        edgeList = new LinkedList<>();
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setX(double xVal) {
        this.x = xVal+radius/2;
    }
    public void setY(double yVal) {this.y= yVal+radius/2;}
    public Point2D getPosition() {
        return new Point2D(x, y);
    }
    public boolean isSelected() {
        return selected;
    }
    public boolean isConnected() {
        return connected;
    }
    public double getRadius() {
        return radius;
    }
    public double getHeight() {
        return height;
    }
    public int getLabel() {
        return label;
    }
    public void setSelected(boolean b){
        selected = b;
    }
    public void setConnected(boolean b) {
        connected = b;
    }
    public boolean containsPoint(double x, double y) {
        Circle s = new Circle(this.x,this.y,radius);
        return s.contains(x, y);
    }
    public LinkedList<Edge> getEdges(){
        return edgeList;
    }
}
