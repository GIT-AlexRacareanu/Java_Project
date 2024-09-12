public class Circle extends Shape
{
    private double radius;
    private static final int numberOfSides = 0;

    public Circle(String name,double radius){
        this.radius=radius;
        this.name=name;
    }


    public int getNumberOfSides() {
        return numberOfSides;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea(){
        return Math.PI * this.radius * this.radius;
    }

    @Override
    public String toString(){
        return "This is a " + this.getClass().getName() + ", it's name is " + this.name + " and it has a radius of " + this.radius + " centimeters";
    }
}
