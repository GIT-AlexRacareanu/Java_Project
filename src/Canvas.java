import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Canvas implements Serializable {

    private ArrayList<Shape> shapes; //private feature

    private List<Polygon> cornerShapes;//private feature

    private static Canvas uniqueCanvas;//the one and only canvas instance

    private Canvas() { //public constructor for instantiating only once
            shapes = new ArrayList<>();
            cornerShapes = new ArrayList<>();
    }

    public static Canvas getInstance() {
        if(uniqueCanvas==null)
            uniqueCanvas=new Canvas();
        return uniqueCanvas;
    }

    ////////methods continue to be public, so we can use them outside the class

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    public void addShape(Shape shape) {
       shapes.add(shape);
    }

    public void printContent()  {
        for(Shape shape : shapes){
            System.out.println(shape.toString());
        }
    }

    public List<Polygon> getCornerShapes() {
        return cornerShapes;
    }

    public void setCornerShapes(List<Polygon> cornerShapes) {
        this.cornerShapes = cornerShapes;
    }

    public void addCornerShape(Polygon polygon) {
        cornerShapes.add(polygon);
    }

    public void printCornerShapes() {
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

    public void saveShapes() {
         try
         {
             ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src//database.txt"));//store data into file
             out.writeObject(uniqueCanvas);
             out.close();
         }catch(Exception e){
             System.out.println("ERROR while saving to file!");
         }
    }

    public void loadShapes(){
        try
        {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("src//database.txt"));//read stored data from file
            uniqueCanvas = (Canvas)in.readObject();
            in.close();
        }catch(Exception e){
            System.out.println("Canvas is NULL");
        }
    }

}