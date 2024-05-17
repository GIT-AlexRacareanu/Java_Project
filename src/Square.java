public class Square extends Shape implements  Polygon
{
    private double length; //side length, 4 equal sides
    private static final int numberOfSides = 4;
    private static final int numberOfCorners = 4;

    public Square(String name,double length)
    {
        this.length=length;
        this.name=name;
    }


    public int getNumberOfSides() {
        return numberOfSides;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public int getNumberOfCorners() {
        return numberOfCorners;
    }

    public double getArea(){
        return this.length*this.length;
    }

    @Override
    public String toString(){
        return "This is a "+ this.name +" and it has " +  numberOfSides + " sides of " +length+ " centimeters";
    }
}
