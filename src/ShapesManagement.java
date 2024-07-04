import org.jetbrains.annotations.NotNull;
import java.io.*;
import java.util.Scanner;

public final class ShapesManagement {

    public static void importShapes(String path) {
        try {

            File csvFile = new File(path);
            Scanner csvScanner = new Scanner(csvFile);
            int objectIndex = 1;
            while(csvScanner.hasNextLine())
            {
                String inputLine = csvScanner.nextLine();
                importShape(inputLine,objectIndex);
                objectIndex++;
            }

        }catch (FileNotFoundException e){
            System.out.println("the specified file does not exist!");
        }
    }

    private static void importSquare(Square square){
        try {
            Canvas.getInstance().addShape(square);
        } catch (Exception e) {
            System.out.println("Error Square");
        }
    }

    private static void importRectangle(Rectangle rectangle){
        try {
            Canvas.getInstance().addShape(rectangle);
        } catch (Exception e) {
            System.out.println("Error Rectangle");
        }
    }

    private static void importCircle(Circle circle){
        try {
            Canvas.getInstance().addShape(circle);
        } catch (Exception e) {
            System.out.println("Error Circle");
        }
    }

    private static void importShape(@NotNull String inputLine, int index) {
        String[] lineContent = inputLine.split(",");
        switch (lineContent[0]) {
            case "Square":
                importSquare(new Square(lineContent[1], Double.parseDouble(lineContent[2])));
                break;
            case "Rectangle":
                importRectangle(new Rectangle(lineContent[1], Double.parseDouble(lineContent[2]), Double.parseDouble(lineContent[3])));
                break;
            case "Circle":
                importCircle(new Circle(lineContent[1], Double.parseDouble(lineContent[2])));
                break;
            default:
                System.out.println("Unrecognized object at line "+index);
                break;
        }
    }
}
