import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Canvas {

    //connector
    public static final DatabaseConnector myDatabase = new DatabaseConnector();
    //repositories
    private static final SquareRepository squares = new SquareRepository();
    private static final RectangleRepository rectangles = new RectangleRepository();
    private static final CircleRepository circles = new CircleRepository();
    private static final StarRepository stars = new StarRepository();

    private static Canvas uniqueCanvas;

    private Canvas() { //public constructor for instantiating only once
    }

    public static Canvas getInstance() {
        if(uniqueCanvas==null)
            uniqueCanvas=new Canvas();
        return uniqueCanvas;
    }

    public List<Polygon> getCornerShapes() {
        ArrayList<Polygon> cornerShapes = new ArrayList<>();
        cornerShapes.addAll(squares.getAll());
        cornerShapes.addAll(rectangles.getAll());
        cornerShapes.addAll(stars.getAll());
        return cornerShapes;
    }

    //do I need this tho?
    public void setCornerShapes(List<Polygon> cornerShapes) {
        List<Circle> backupCircles = circles.getAll();
        myDatabase.clear();
        for(Polygon polygon: cornerShapes)
            addCornerShape(polygon);
        for(Circle circle: backupCircles)
            circles.insert(circle);
    }

    public ArrayList<Shape> getShapes() {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.addAll(squares.getAll());
        shapes.addAll(rectangles.getAll());
        shapes.addAll(circles.getAll());
        shapes.addAll(stars.getAll());
        return shapes;
    }

    public void setShapes(ArrayList<Shape> shapes) {
        myDatabase.clear();
        for (Shape shape : shapes)
            addShape(shape);
    }

    public void addShape(Shape shape) {
       switch (shape.getClass().getName()) {
           case "Square":
               squares.insert((Square) shape);
               break;
           case "Rectangle":
               rectangles.insert((Rectangle) shape);
               break;
           case "Circle":
               circles.insert((Circle) shape);
               break;
           case "Star":
               stars.insert((Star) shape);
               break;
           default:
               break;
       }
    }

    public void deleteShape(String name){
       ArrayList<Shape> shapes = getShapes();
       shapes.removeIf(n->n.getName().equalsIgnoreCase(name));
       setShapes(shapes);
    }

    public void printContent()  {
            for (Shape shape : getShapes()) {
                System.out.println(shape.toString());
            }
        System.out.println(howMany());
    }

    public void printContentByName(){
        for(int  i = 1; i <= getShapes().size(); i++){
            System.out.println(i+"."+getShapes().get(i-1).getName());
        }
    }

    public void addCornerShape(Polygon polygon) {
        switch (polygon.getClass().getName()) {
            case "Square":
                squares.insert((Square) polygon);
                break;
            case "Rectangle":
                rectangles.insert((Rectangle) polygon);
                break;
            case "Star":
                stars.insert((Star) polygon);
                break;
            default:
                break;
        }
    }

    public void printCornerShapes() {
        for(Polygon polygon : getCornerShapes())
        {
            System.out.println("This is a " + polygon.getClass().getName() + " and it has " + polygon.getNumberOfCorners() + " corners");
        }
    }

    public String howMany(){
        return "we've got " + squares.getAll().size() + " Squares," + rectangles.getAll().size() + " Rectangles and " + circles.getAll().size() + " Circles and " + stars.getAll().size() + " Stars";
    }

    public static void saveShapes() throws SQLException {
        myDatabase.disconnect();
    }

    public static void loadShapes() {
        myDatabase.connect();
        DatabaseInitializer.initialize(myDatabase);
    }
}