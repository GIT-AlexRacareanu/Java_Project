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
        for(int id=1; id <= squareList.getAll().size(); id++)
            System.out.println(id+". "+squareList.getAll().get(id-1).getName()+ ", " +squareList.getAll().get(id-1).getLength());
        System.out.println("~~~~RECTANGLES~~~");
        for(int id=1; id <= rectangleList.getAll().size(); id++)
            System.out.println(id+"."+rectangleList.getAll().get(id-1).getName()+ ", "+rectangleList.getAll().get(id-1).getLength()+", "+rectangleList.getAll().get(id-1).getWidth());
        System.out.println("~~~~~CIRCLES~~~~~");
        for(int id=1; id<= circleList.getAll().size(); id++)
            System.out.println(id+"."+circleList.getAll().get(id-1).getName()+", "+circleList.getAll().get(id-1).getRadius());
        System.out.println("~~~~~~STARS~~~~~~");
        for(int id=1; id<= starList.getAll().size(); id++)
            System.out.println(id+". "+starList.getAll().get(id-1).getName() + ", " + starList.getAll().get(id-1).getSideLength());
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