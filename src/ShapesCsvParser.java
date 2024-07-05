import org.jetbrains.annotations.NotNull;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public final class ShapesCsvParser {

    private static ArrayList<Shape> shapes = new ArrayList<>();

    public static ArrayList<Shape> importShapes(String path) {
        int objectIndex = 1;
        File csvFile = new File(path);
        try {
            Scanner csvScanner = new Scanner(csvFile);
            String inputLine;
            while (csvScanner.hasNextLine()) {
                inputLine = csvScanner.nextLine();
                importShape(inputLine, objectIndex);
                objectIndex++;
            }
        }catch (FileNotFoundException e){
            System.out.println("Unrecognized file!");
        }
        return shapes;
    }

    private static void importSquare(String[] line){
        try{
            shapes.add(new Square(line[1],Double.parseDouble(line[2])));
        }catch(Exception e){
            System.out.println("Error");
        }
    }

    private static void importRectangle(String[] line){
        try{
            shapes.add(new Rectangle(line[1],Double.parseDouble(line[2]),Double.parseDouble(line[3])));
        }catch(Exception e){
            System.out.println("Error");
        }
    }

    private static void importCircle(String[] line){
        try{
            shapes.add(new Circle(line[1],Double.parseDouble(line[2])));
        }catch(Exception e){
            System.out.println("Error");
        }
    }

    private static void importShape(@NotNull String inputLine, int index) {
        String[] lineContent = inputLine.split(",");
        switch (lineContent[0]) {
            case "Square":
                importSquare(lineContent);
                break;
            case "Rectangle":
                importRectangle(lineContent);
                break;
            case "Circle":
                importCircle(lineContent);
                break;
            default:
                System.out.println("Unrecognized object at line "+index);
                break;
        }
    }
}
