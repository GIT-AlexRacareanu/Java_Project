import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public final class Application {

    private static final DatabaseConnector myDatabase = new DatabaseConnector();
    private static final Scanner input = new Scanner(System.in).useDelimiter("\n");//private feature

    public static void runApp() throws InterruptedException, IOException, ClassNotFoundException, SQLException {
        myDatabase.connect();
        DatabaseInitializer.initialize(myDatabase);
        Canvas.loadShapes();
        runMenu();
        Canvas.saveShapes();
        myDatabase.disconnect();
    }

    public static void runMenu() throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("----------------------------------------MENU----------------------------------------");
        System.out.println("1.Create a shape");
        System.out.println("2.Delete a shape");
        System.out.println("3.Show canvas content");
        System.out.println("4.Work with MySql database");
        System.out.println("5.Close the app");
        System.out.print("Write your choice: ");
        switch(input.nextInt()) {
            case 1://shape creation menu
                System.out.println("----------------------------------SHAPE_CREATION_MENU-------------------------------");
                System.out.println("1.Square");
                System.out.println("2.Rectangle");
                System.out.println("3.Circle");
                System.out.println("4.Star");
                System.out.println("5.Import shapes from file");
                System.out.print("Write your choice: ");
                switch (input.nextInt()) {
                    case 1://square
                        createSquare();
                        break;
                    case 2://rectangle
                        createRectangle();
                        break;
                    case 3://circle
                        createCircle();
                        break;
                    case 4:
                        createStar();
                        break;
                    case 5:
                        importFromFile();
                        break;
                    default://error
                        System.out.println("//////////////////////////////////////////////////////////////////////////////");
                        System.out.println("Wrong input!!!!");
                        redirect();
                        break;
                }
                break;
            case 2://shape deletion menu
                System.out.println("----------------------------------SHAPE_DELETION_MENU-------------------------------");
                if (Canvas.getInstance().getShapes().isEmpty()) {
                    System.out.println("The list is empty! Try adding some shapes.");
                } else{
                    System.out.println("Your list is: ");
                    Canvas.getInstance().printContentByName();
                    System.out.println("Write the name of the one you want to delete: ");
                    Canvas.getInstance().deleteShape(input.next());
                }
                redirect();
                break;
            case 3:
                System.out.println("-----------------------------------LISTING_MENU--------------------------------------");
                if(Canvas.getInstance().getShapes().isEmpty()) {
                    System.out.println("The list is empty! Try adding some shapes.");
                    redirect();
                }
                else{
                    System.out.println("What would you like to print?");
                    System.out.println("1.All Shapes");
                    System.out.println("2.Cornered Shapes");
                    System.out.print("Write your choice: ");
                    switch (input.nextInt())
                    {
                        case 1:
                            System.out.println("There is the content you added:");
                            Canvas.getInstance().printContent();//all the shapes,including circles
                            redirect();
                            break;
                        case 2:
                            if(Canvas.getInstance().getCornerShapes().isEmpty()) {
                                System.out.println("The list is empty! Try adding some cornered shapes.");
                            }
                            else {
                                System.out.println("There is the content you added:");
                                Canvas.getInstance().printCornerShapes();//only the cornered ones
                            }
                            redirect();
                            break;
                        default:
                            System.out.println("Wrong input!!!");
                            redirect();
                            break;
                    }
                }
                break;
            case 4:
                System.out.println("-----------------------------------DATABASE_MENU--------------------------------------");
                databaseMenu();
                break;
            case 5:
                closeApp();
                break;
            default:
                System.out.println("Wrong input!!!!");
                redirect();
                break;
        }

    }

    public static void databaseMenu() throws IOException, InterruptedException, ClassNotFoundException {
        ResultSet result = null;
          System.out.println("Would you like to write a query?");
          System.out.println("1.Yes");
          System.out.println("2.No");
          System.out.print("Write your choice: ");
        if(input.nextInt()==1) {
            System.out.print("write a query:");
            result = myDatabase.executeQuery(input.next());
        }
          System.out.println("Would you like to print the result?");
          System.out.println("1.Yes");
          System.out.println("2.No");
          System.out.print("Write your choice: ");
        if(input.nextInt()==1)
            myDatabase.printResult(result);
        redirect();
    }

    public static void createSquare() throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("---------------------------------SQUARE_CREATION_MENU-------------------------------");
        System.out.println("A square needs a name and a length");
        System.out.println("introduce the name:");
        String name = input.next();
        System.out.println("introduce the length(centimeters):");
        double length = input.nextDouble();
        Canvas.getInstance().addShape(new Square(name,length));
        Canvas.getInstance().addCornerShape(new Square(name,length));
        System.out.println("Shape added!");
        redirect();
    }

    public static void createRectangle() throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("-------------------------------RECTANGLE_CREATION_MENU------------------------------");
        System.out.println("A rectangle needs a name,a length,and a width");
        System.out.println("introduce the name:");
        String name = input.next();
        System.out.println("introduce the length(centimeters):");
        double length = input.nextDouble();
        System.out.println("introduce the width(centimeters):");
        double width = input.nextDouble();
        Canvas.getInstance().addShape(new Rectangle(name,length,width));
        Canvas.getInstance().addCornerShape(new Rectangle(name,length,width));
        System.out.println("Shape added");
        redirect();
    }

    public static void createCircle() throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("------------------------------CIRCLE_CREATION_MENU----------------------------");
        System.out.println("A circle needs a name and a radius");
        System.out.println("introduce the name:");
        String name = input.next();
        System.out.println("introduce the radius(centimeters):");
        double radius = input.nextDouble();
        Canvas.getInstance().addShape(new Circle(name,radius));
        System.out.println("Shape added");
        redirect();
    }

    public static void createStar() throws IOException, InterruptedException, ClassNotFoundException {
        System.out.println("--------------------------------STAR_CREATION_MENU------------------------------");
        System.out.println("A star needs a name and the length of a side");
        System.out.println("introduce the name:");
        String name = input.next();
        System.out.println("introduce the side length(centimeters):");
        double sideLength = input.nextDouble();
        Canvas.getInstance().addShape(new Star(name,sideLength));
        Canvas.getInstance().addCornerShape(new Star(name,sideLength));
        System.out.println("Shape added");
        redirect();
    }

    public static void importFromFile() throws IOException, InterruptedException, ClassNotFoundException {
        System.out.println("paste your path here(ex:Parent//Child//file.csv):");
        String path = input.next();
        ArrayList<Shape> tempList = new ShapesCsvParser().importShapes(path);
        if(tempList.isEmpty()){
            System.out.println("The list is empty!");
        }
        else {
            System.out.println("This is the list written in the specified path:");
            for(Shape shape: tempList)
                System.out.println(shape);
            System.out.println("Would you like to add the objects to canvas?");
            System.out.println("1.Yes");
            System.out.println("2.No");
            if(input.nextInt()==1){
                for(Shape shape: tempList){
                    Canvas.getInstance().getShapes().add(shape);
                    if(shape.isCornered())
                        Canvas.getInstance().getCornerShapes().add((Polygon)shape);
                }
                System.out.println("Successfully added to canvas");
            }
        }
        redirect();
    }

    public static void loading() throws InterruptedException {
        for(int i = 1; i<=3; i++) {
            Thread.sleep(300);
            System.out.print(".");
        }
        Thread.sleep(150);
        System.out.println();//newline
    }

    public static void redirect() throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("Would you like to continue?");
        System.out.println("1.Yes");
        System.out.println("2.No");
        System.out.print("Write your choice: ");
        switch(input.nextInt()){
            case 1://back to start
                System.out.print("You will be redirected soon");
                loading();
                runMenu();
                break;
            case 2://end app
                closeApp();
                break;
            default://error
                System.out.println("Wrong input,try again!");
                redirect();
                break;
        }

    }

    public static void closeApp() throws InterruptedException {
        System.out.print("The program will close soon");
        loading();
    }
}
