public class Square extends  Shape
{
    private double length; //side length, 4 equal sides
    private final int numberOfSides = 4;

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

    public double getArea(){
        return this.length*this.length;
    }

    @Override
    public String toString(){
        return "This is a "+ this.name +" and it has " +  this.numberOfSides + " sides of " +length+ " centimeters";
    }
}
