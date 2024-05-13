import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Square testSquare = new Square("magic square", 10);
        System.out.println("square's name is: " + testSquare.getName());
        System.out.println("square's side length is: " + testSquare.getLength() + " centimeters");
        System.out.println("square has " + testSquare.getNumberOfSides() + " sides");
        //now let's change them and try again
        testSquare.setLength(8);
        testSquare.setName("rubik cube face");
        System.out.println("New dimensions of the " + testSquare.name + " are: length->" + testSquare.getLength());
        System.out.println("Area is: " + testSquare.getArea() + " centimeters^2");
        //final test
        System.out.println(testSquare.toString());
        System.out.println("");//newline
        Rectangle testRectangle = new Rectangle("football field", 100000, 50000);
        System.out.println("rectangle's name is: " + testRectangle.getName());
        System.out.println("rectangle's length is: " + testRectangle.getLength() + " centimeters");
        System.out.println("rectangle's width is: " + testRectangle.getWidth() + " centimeters");
        System.out.println("rectangle has " + testRectangle.getNumberOfSides() + " sides");
        //now let's change them and try again
        testRectangle.setLength(30231);
        testRectangle.setWidth(25487);
        testRectangle.setName("graveyard");
        System.out.println("New dimensions of the " + testRectangle.name + " are: length->" + testRectangle.getLength() + " ,width->" + testRectangle.getWidth());
        System.out.println("Area is: " + testRectangle.getArea() + " centimeters^2");
        //final test
        System.out.println(testRectangle.toString());
        System.out.println("");//newline
        Circle testCircle = new Circle("tire", 40);
        System.out.println("circle's name is: " + testCircle.getName());
        System.out.println("circle's radius is: " + testCircle.getRadius() + " centimeters");
        System.out.println("circle has " + testCircle.getNumberOfSides() + " sides");
        //now let's change them and try again
        testCircle.setName("pc fen");
        testCircle.setRadius(12);
        System.out.println("New dimensions of the " + testCircle.name + " are: radius->" + testCircle.getRadius());
        System.out.println("Area is: " + testCircle.getArea() + " centimeters^2");
        //final test
        System.out.println(testCircle.toString());
        //////////////////////////////////////////////////
        System.out.println("");//newline
        Canvas shapeList = new Canvas();
        shapeList.addShape(testCircle);
        shapeList.addShape(testRectangle);
        shapeList.addShape(testSquare);
        shapeList.addShape(new Square("happy square", 200));
        shapeList.addShape(new Square("SpongeBob Square Pants", 529));
        shapeList.addShape(new Circle("Bicycle Wheel", 61.9));
        System.out.println(shapeList.howMany());
        //second test
        System.out.println("");//newline
        shapeList.setShapes(new ArrayList<Shape>()); //free list and test
        System.out.println(shapeList.howMany());
        //last test
        System.out.println("");//newline
        shapeList.addShape(testCircle);
        shapeList.addShape(testRectangle);
        shapeList.addShape(testSquare);
        Canvas shapeList2 = new Canvas();
        shapeList2.setShapes(shapeList.getShapes());//final test for getter and setter
        System.out.println(shapeList2.howMany());
    }
}