import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Canvas {

    //connector
    public static final DatabaseConnector myDatabase = new DatabaseConnector();
    //repositories
    private static final SquareRepository squareList = new SquareRepository();
    private static final RectangleRepository rectangleList = new RectangleRepository();
    private static final CircleRepository circleList = new CircleRepository();
    private static final StarRepository starList = new StarRepository();

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
        cornerShapes.addAll(squareList.getAll());
        cornerShapes.addAll(rectangleList.getAll());
        cornerShapes.addAll(starList.getAll());
        return cornerShapes;
    }

    public ArrayList<Shape> getShapes() {
        ArrayList<Shape> shapeList = new ArrayList<>();
        shapeList.addAll(squareList.getAll());
        shapeList.addAll(rectangleList.getAll());
        shapeList.addAll(circleList.getAll());
        shapeList.addAll(starList.getAll());
        return shapeList;
    }

    public void addShapes(ArrayList<Shape> shapeList) { //you can add more than one shape with this method
        for (Shape shape : shapeList)
            addShape(shape);
    }

    public void addShape(Shape shape) { //you can only add one shape with this method
       switch (shape.getClass().getName()) {
           case "Square":
               squareList.insert((Square) shape);
               break;
           case "Rectangle":
               rectangleList.insert((Rectangle) shape);
               break;
           case "Circle":
               circleList.insert((Circle) shape);
               break;
           case "Star":
               starList.insert((Star) shape);
               break;
           default:
               break;
       }
    }

    public void deleteShape(long id, String classType){
      switch (classType.toLowerCase()){
          case "square":
              squareList.delete(id);
              break;
          case "rectangle":
              rectangleList.delete(id);
              break;
          case "circle":
              circleList.delete(id);
              break;
          case "star":
              starList.delete(id);
              break;
          default:
              System.out.println("object not found!");
      }
    }

    public void printContent()  {
        System.out.println("~~~~~SQUARES~~~~~");
        for(Square square: squareList.getAll())
            System.out.println(square.getId() + ". " + square.getName()+ ", " + square.getLength());
        System.out.println("~~~~RECTANGLES~~~");
        for(Rectangle rectangle: rectangleList.getAll())
            System.out.println(rectangle.getId()+"." + rectangle.getName()+ ", " + rectangle.getLength()+", " + rectangle.getWidth());
        System.out.println("~~~~~CIRCLES~~~~~");
        for(Circle circle: circleList.getAll())
            System.out.println(circle.getId() + "." + circle.getName() + ", " + circle.getRadius());
        System.out.println("~~~~~~STARS~~~~~~");
        for(Star star: starList.getAll())
            System.out.println(star.getId() + ". " + star.getName() + ", " + star.getSideLength());
    }

    public void addCornerShape(Polygon polygon) {
        switch (polygon.getClass().getName()) {
            case "Square":
                squareList.insert((Square) polygon);
                break;
            case "Rectangle":
                rectangleList.insert((Rectangle) polygon);
                break;
            case "Star":
                starList.insert((Star) polygon);
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
        return "we've got " + squareList.getAll().size() + " Squares," + rectangleList.getAll().size() + " Rectangles and " + circleList.getAll().size() + " Circles and " + starList.getAll().size() + " Stars";
    }

    public static void saveShapes() throws SQLException {
        myDatabase.disconnect();
    }

    public static void loadShapes() {
        myDatabase.connect();
        DatabaseInitializer.initialize(myDatabase);
    }
}