public class Star extends Shape implements Polygon
{
    private double sideLength;
    private static final int numberOfSides = 10;
    private static final int numberOfCorners = 10;

    public Star(String name,double sideLength){
        this.setName(name);
        this.sideLength=sideLength;
    }

    @Override
    public int getNumberOfCorners() {
        return numberOfCorners;
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public int getNumberOfSides() {
        return numberOfSides;
    }

    //weird theorem tho 4.7555(R*R) and R(radius) is sideLength/2*sin(PI/numberOfSides)
    @Override
    public double getArea() {
        return Math.pow(this.sideLength/(2*Math.sin(Math.PI/numberOfSides)),2)*4.7555;
    }

    @Override
    public String toString() {
        return "This is a " + this.getClass().getName() + ", it's name is " + this.getName() +" and it has " +  numberOfSides + " sides of " +sideLength+ " centimeters";
    }
}