import java.io.IOException;
import java.util.Scanner;

public final class Application {

    private static final Scanner input = new Scanner(System.in).useDelimiter("\n");//private feature

    public static void runApp() throws InterruptedException, IOException, ClassNotFoundException {
        Canvas.getInstance().loadShapes();
        runMenu();
        Canvas.getInstance().saveShapes();
    }

    public static void runMenu() throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("----------------------------------------MENU----------------------------------------");
        System.out.println("1.Create a shape");
        System.out.println("2.Delete a shape");
        System.out.println("3.Show canvas content");
        System.out.println("4.Close the app");
        System.out.print("Write your choice: ");
        switch(input.nextInt()) {
            case 1://shape creation menu
                System.out.println("----------------------------------SHAPE_CREATION_MENU-------------------------------");
                System.out.println("1.Square");
                System.out.println("2.Rectangle");
                System.out.println("3.Circle");
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
                    Canvas.getInstance().printContent(true);
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
                            Canvas.getInstance().printContent(false);//all the shapes,including circles
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
                closeApp();
                break;
            default:
                System.out.println("Wrong input!!!!");
                redirect();
                break;
        }

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
