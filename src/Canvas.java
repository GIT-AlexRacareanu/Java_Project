import java.util.ArrayList;
import java.util.List;

public class Canvas {

    private ArrayList<Shape> shapes;

    private List<Polygon> cornerShapes;

    public Canvas(){
        shapes = new ArrayList<>();
        cornerShapes = new ArrayList<>();
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    public void addShape(Shape shape){
        shapes.add(shape);
    }

    public List<Polygon> getCornerShapes() {
        return cornerShapes;
    }

    public void setCornerShapes(List<Polygon> cornerShapes) {
        this.cornerShapes = cornerShapes;
    }

    public void addCornerShape(Polygon polygon)
    {
        cornerShapes.add(polygon);
    }

    public void printCornerShapes ()
    {
        for(Polygon polygon : cornerShapes)
        {
            System.out.println("This is a " + polygon.getClass().getName() + " and it has " + polygon.getNumberOfCorners() + " corners");
        }
    }

    public String howMany(){
        int nrSquares=0,nrRectangles=0,nrCircles=0;
        for (Shape shape : shapes) {
            switch (shape.getClass().getName()) {
                case "Square":
                    nrSquares++;
                    break;
                case "Rectangle":
                    nrRectangles++;
                    break;
                case "Circle":
                    nrCircles++;
                    break;
                default:
                    break;
            }
        }
        return "we've got " + nrSquares + " Squares," + nrRectangles + " Rectangles and " + nrCircles + " Circles";
    }

}