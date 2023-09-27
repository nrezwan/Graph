package Part2;

import java.util.*;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GraphModel {

    private SimpleListProperty<Vertex> vertexListProperty;
    private LinkedList<Edge> edgeListProperty;
    private HashMap<Vertex, Set<Vertex>> adjacencyList;
    private int radius, label;

    public GraphModel(int radius) {
        ArrayList<Vertex> list = new ArrayList<>();
        ObservableList<Vertex> observableList = FXCollections.observableArrayList(list);
        ArrayList<Edge> list2 = new ArrayList<>();
        ObservableList<Edge> observableList2 = FXCollections.observableArrayList(list2);
        vertexListProperty = new SimpleListProperty<>(observableList);
        edgeListProperty = new LinkedList<>();
        this.adjacencyList = new HashMap<>();
        label = 0;
        this.radius = radius;
    }
    public SimpleListProperty<Vertex> vertexListProperty(){
        return vertexListProperty;
    }
    public LinkedList<Edge> edgeListProperty(){
        return edgeListProperty;
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
        Vertex newVertex = new Vertex(label, x, y, radius,radius, Main.interactionModel.getSelectedColor());
        label++;
        addVertex2(newVertex);
        vertexListProperty.add(newVertex);
    }

    public void updateLabel(){
        int x = 0;
        for(int i=0; i<vertexListProperty.size(); i++){
            Vertex s = vertexListProperty.get(i);
            s.label = x;
            x++;
        }
    }
    public Iterable<Vertex> getAllVertices() {
        return this.adjacencyList.keySet();
    }
    public void addEdge(Vertex a, Vertex b){
        if(a!= null && a!=b && b!=null){
            Edge edge = new Edge(a,b,a.x,a.y,b.x,b.y);
            edgeListProperty.add(edge);
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
    public Edge getEdgeat2(double x, double y) {
        for (Edge s : edgeListProperty) {
            if ( (Math.abs(x-s.x1)<0.01 && Math.abs(y-s.y1)<.01) || (Math.abs(x-s.x2)<0.01 && Math.abs(y-s.y2)<0.01)) {
                return s;
            }
        }
        return null;
    }

    public void deleteVertexAt(double x, double y) {
        Vertex delVertex = getVertexat(x, y);
        Vertex delVertex2 = getVertexat2(x, y);
        deleteE(delVertex.getX(),delVertex.getY());
        removeVertex(delVertex);
        delVertex.getEdges().clear();
        vertexListProperty.remove(delVertex2);
        updateLabel();
        Edge ed=getEdgeat2(x,y);
        if(ed!=null)
            edgeListProperty.remove(ed);
    }

    public void deleteE(double x,double y){
        LinkedList<Edge> ll=new LinkedList<Edge>();
        for (Edge s : edgeListProperty) {
            if ( (Math.abs(x-s.x1)<0.01 && Math.abs(y-s.y1)<.01) || (Math.abs(x-s.x2)<0.01 && Math.abs(y-s.y2)<0.01)) {
                ll.add(s);
            }
        }
        for (Edge s : ll) {
            edgeListProperty.remove(s);
        }
    }
}