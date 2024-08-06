import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public final class ShapesCsvParser {

    public ArrayList<Shape> importShapes(String path) {
        ArrayList<Shape> shapeList = new ArrayList<>();
        int objectIndex = 1;
        File csvFile = new File(path);
        try {
            Scanner csvScanner = new Scanner(csvFile);
            String inputLine;
            while (csvScanner.hasNextLine()) {
                inputLine = csvScanner.nextLine();
                importShape(inputLine, shapeList ,objectIndex);
                objectIndex++;
            }
        }catch (FileNotFoundException e){
            System.out.println("Unrecognized file!");
        }
        return shapeList;
    }

    private Square importSquare(String[] line) {
    return new Square(line[1],Double.parseDouble(line[2]));
    }

    private Rectangle importRectangle(String[] line){
            return new Rectangle(line[1],Double.parseDouble(line[2]),Double.parseDouble(line[3]));
    }

    private Circle importCircle(String[] line) {
        return new Circle(line[1],Double.parseDouble(line[2]));
    }

    private Star importStar(String[] line) {
        return new Star(line[1],Double.parseDouble(line[2]));
    }

    private void importShape(String inputLine,ArrayList<Shape> list ,int index) {
        String[] lineContent = inputLine.split(",");
        switch (lineContent[0]) {
            case "Square":
                list.add(importSquare(lineContent));
                break;
            case "Rectangle":
                list.add(importRectangle(lineContent));
                break;
            case "Circle":
                list.add(importCircle(lineContent));
                break;
            case "Star":
                list.add(importStar(lineContent));
                break;
            default:
                System.out.println("Unrecognized object at line "+index);
                break;
        }
    }
}
