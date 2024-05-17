import java.util.Scanner;

public final class Application {

    private static final Scanner input = new Scanner(System.in).useDelimiter("\n");//private feature

    public static void runApp() throws InterruptedException {
        System.out.println("----------------------------------------MENU----------------------------------------");
        System.out.println("1.Create a shape");
        System.out.println("2.Show canvas content");
        System.out.println("3.Close the app");
        System.out.print("Write your choice: ");
        switch(input.nextInt())
        {
            case 1://shape creation menu
                System.out.println("----------------------------------SHAPE_CREATION_MENU-------------------------------");
                System.out.println("1.Square");
                System.out.println("2.Rectangle");
                System.out.println("3.Circle");
                System.out.print("Write your choice: ");
                switch (input.nextInt()){
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
                        jumpOut();
                        System.out.println("Wrong input!!!!");
                        redirect();
                        break;
                }
                break;
            case 2:
                System.out.println("-----------------------------------LISTING_MENU--------------------------------------");
                if(Canvas.getCanvas().getShapes().isEmpty()) {
                    System.out.println("The list is empty! Try adding some shapes.");
                    redirect();
                    break;
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
                             Canvas.getCanvas().printContent();//all the shapes,including circles
                             redirect();
                             break;
                         case 2:
                             if(Canvas.getCanvas().getCornerShapes().isEmpty()) {
                                 System.out.println("The list is empty! Try adding some cornered shapes.");
                             }
                             else {
                                System.out.println("There is the content you added:");
                                Canvas.getCanvas().printCornerShapes();//only the cornered ones
                             }
                             redirect();
                             break;
                         default:
                             System.out.println("Wrong input!!!");
                             redirect();
                     }
                }
                break;
            case 3:
                System.out.print("The program will close soon");
                loading();
                break;
            default:
                System.out.println("Wrong input!!!!");
                redirect();
                break;
        }

    }

    public static void createSquare() throws InterruptedException {
        System.out.println("---------------------------------SQUARE_CREATION_MENU-------------------------------");
        System.out.println("A square needs a name and a length");
        System.out.println("introduce the name:");
        String name = input.next();
        System.out.println("introduce the length(centimeters):");
        double length = input.nextDouble();
        Canvas.getCanvas().addShape(new Square(name,length));
        Canvas.getCanvas().addCornerShape(new Square(name,length));
        System.out.println("Shape added!");
        redirect();
    }

    public static void createRectangle() throws InterruptedException {
        System.out.println("-------------------------------RECTANGLE_CREATION_MENU------------------------------");
        System.out.println("A rectangle needs a name,a length,and a width");
        System.out.println("introduce the name:");
        String name = input.next();
        System.out.println("introduce the length(centimeters):");
        double length = input.nextDouble();
        System.out.println("introduce the width(centimeters):");
        double width = input.nextDouble();
        Canvas.getCanvas().addShape(new Rectangle(name,length,width));
        Canvas.getCanvas().addCornerShape(new Rectangle(name,length,width));
        System.out.println("Shape added");
        redirect();
    }

    public static void createCircle() throws InterruptedException {
        System.out.println("------------------------------CIRCLE_CREATION_MENU----------------------------");
        System.out.println("A circle needs a name and a radius");
        System.out.println("introduce the name:");
        String name = input.next();
        System.out.println("introduce the radius(centimeters):");
        double radius = input.nextDouble();
        Canvas.getCanvas().addShape(new Circle(name,radius));
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

    public static void redirect() throws InterruptedException {
        System.out.println("Would you like to continue?");
        System.out.println("1.Yes");
        System.out.println("2.No");
        System.out.print("Write your choice: ");
        switch(input.nextInt()){
            case 1://back to start
                System.out.print("You will be redirected soon");
                loading();
                runApp();
                break;
            case 2://end app
                System.out.print("The app will close soon");
                loading();
                break;
            default://error
                System.out.println("Wrong input,try again!");
                redirect();
                break;
        }

    }

    public static void jumpOut() {
        System.out.println("///////////////////////////////////////////////////////////////////////////////////////");
    }
}
