package Part2;

import javafx.geometry.Point2D;

public class Edge {
    public double x1,x2,y1,y2;
    public Vertex vA, vB;

    public Edge(Vertex vA, Vertex vB, double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.vA = vA;
        this.vB = vB;
    }
    public Point2D getStart() {
        x1=vA.x;
        y1=vA.y;
        Point2D start=new Point2D(x1,y1);
        return start;
    }
    public Point2D getEnd() {
        x2=vB.x;
        y2=vB.y;
        Point2D end=new Point2D(x2,y2);
        return end;
    }

}
