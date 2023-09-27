package Part1;

import java.util.*;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GraphModel {

    private SimpleListProperty<Vertex> vertexListProperty;
    private HashMap<Vertex, Set<Vertex>> adjacencyList;
    private int radius, label;

    public GraphModel(int radius) {
        ArrayList<Vertex> list = new ArrayList<>();
        ObservableList<Vertex> observableList = FXCollections.observableArrayList(list);
        vertexListProperty = new SimpleListProperty<>(observableList);
        this.adjacencyList = new HashMap<>();
        label = 0;
        this.radius = radius;
    }
    public SimpleListProperty<Vertex> vertexListProperty(){
        return vertexListProperty;
    }
    public void addVertex2(Vertex v) {
        this.adjacencyList.put(v, new HashSet<Vertex>());
    }
    public void removeVertex(Vertex v) {
        this.adjacencyList.remove(v);
        for (Vertex u: this.getAllVertices()) {
            this.adjacencyList.get(u).remove(v);
        }
    }
    public void addVertex(double x, double y) {
        Vertex newVertex = new Vertex(label, x, y, radius,radius);
        label++;
        addVertex2(newVertex);
        vertexListProperty.add(newVertex);
    }

    public Iterable<Vertex> getAllVertices() {
        return this.adjacencyList.keySet();
    }
    public void addEdge(Vertex a, Vertex b){
        if(a!= null && a!=b && b!=null){
            Edge edge = new Edge(a,b,a.x,a.y,b.x,b.y);
            a.getEdges().add(edge);
            b.getEdges().add(edge);
            System.out.println(edge);
        }
    }
    public Vertex getVertexat(double x, double y) {
        Vertex vertex = null;
        for (Vertex s : getAllVertices()) {
            if (s.containsPoint(x, y)) {
                vertex = s;
            }
        }
        return vertex;
    }
    public Vertex getVertexat2(double x, double y) {
        Vertex vertex = null;
        for (Vertex s : getAllVertices()) {
            if (s.containsPoint(x, y)) {
                vertex = s;
            }
        }
        return vertex;
    }
}