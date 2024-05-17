public class Rectangle extends Shape implements Polygon
{
    private double length; //side length, 2 equal sides
    private double width; //side width, 2 equal sides
    private static final int numberOfSides = 4;
    private static final int numberOfCorners = 4;

    public Rectangle(String name,double length,double width){
        this.length=length;
        this.name=name;
        this.width=width;
    }


    public int getNumberOfSides() {
        return numberOfSides;
    }

    @Override
    public int getNumberOfCorners() {
        return numberOfCorners;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getArea(){
        return this.length*this.width;
    }

    @Override
    public String toString(){
        return "This is a "+ this.name +" and it has " +  numberOfSides + " sides, two of  " +length+ " centimeters, and two of  " + this.width + " centimeters";
    }
}
