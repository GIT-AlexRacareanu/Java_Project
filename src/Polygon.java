public abstract class Polygon extends Shape {

    private final int numberOfCorners = getNumberOfSides(); //kind of a setter

    public int getNumberOfCorners() { //basic getter
        return numberOfCorners;
    }

}
