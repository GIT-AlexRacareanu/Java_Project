import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Canvas implements Serializable {

    private ArrayList<Shape> shapes;

    private List<Polygon> cornerShapes;

    private static Canvas uniqueCanvas;

    private Canvas() { //public constructor for instantiating only once
            shapes = new ArrayList<>();
            cornerShapes = new ArrayList<>();
    }

    public static Canvas getInstance() {
        if(uniqueCanvas==null)
            uniqueCanvas=new Canvas();
        return uniqueCanvas;
    }


    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    public void addShape(Shape shape) {
       shapes.add(shape);
    }

    public void printContent(boolean byName)  {
        ///if byName is true,then print by name,else, use toString for each
        if(byName) {
            for(int  i = 1; i <= shapes.size(); i++){
                System.out.println(i+"."+shapes.get(i-1).getName());
            }
        }
        else{
            for (Shape shape : shapes) {
                System.out.println(shape.toString());
            }
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
             FileOutputStream fos = new FileOutputStream("src//database.txt");
             ObjectOutputStream out = new ObjectOutputStream(fos);//store data into file
             out.writeObject(uniqueCanvas);
         } catch (FileNotFoundException e) {
             System.out.println("File not found: " + e.getMessage());
         } catch (IOException e) {
             System.out.println("I/O error: " + e.getMessage());
         } catch (ClassCastException e) {
             System.out.println("Class cast error: " + e.getMessage());
         } catch (Exception e) {
             System.out.println("An unexpected error occurred: " + e.getMessage());
         }
    }

    public void loadShapes() {
        try {
            FileInputStream fis = new FileInputStream("src//database.txt");
            ObjectInputStream in = new ObjectInputStream(fis); // read stored data from file
            uniqueCanvas = (Canvas) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        } catch (ClassCastException e) {
            System.out.println("Class cast error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public void importShapes(String path) {

        try {
            int objectIndex = 1;
            File csvFile = new File(path);
            Scanner csvScanner = new Scanner(csvFile);
            while (csvScanner.hasNextLine()) {
                String inputLine = csvScanner.nextLine();
                String[] lineContent = inputLine.split(",");
                switch (lineContent[0]) {
                    case "Square":
                        try {
                            uniqueCanvas.addShape(new Square(lineContent[1], Double.parseDouble(lineContent[2])));
                        } catch (Exception e) {
                            System.out.println("Error Square at line " + objectIndex);
                        }
                        break;
                    case "Rectangle":
                        try {
                            uniqueCanvas.addShape(new Rectangle(lineContent[1], Double.parseDouble(lineContent[2]), Double.parseDouble(lineContent[3])));
                        } catch (Exception e) {
                            System.out.println("Error Rectangle at line " + objectIndex);
                        }
                        break;
                    case "Circle":
                        try {
                            uniqueCanvas.addShape(new Circle(lineContent[1], Double.parseDouble(lineContent[2])));
                        } catch (Exception e) {
                            System.out.println("Error Circle at line " + objectIndex);
                        }
                        break;
                    default:
                        System.out.println("Unrecognized object type(" +  lineContent[0] + ") at line " + objectIndex);
                        break;
                }
                objectIndex++;
            }
        }catch (FileNotFoundException e){
            System.out.println("the specified file does not exist!");
        }
    }

    public void deleteShape(String name){
       try {
         shapes.removeIf(n -> (n.getName().equals(name)));
       }catch (Exception e){
          System.out.println("Element not found!");
       }
     }
}