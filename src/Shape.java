import java.io.Serializable;

public abstract class Shape implements Serializable
{
    public String name;//common feature

    public abstract double getArea();//to be implemented in each of them

    public abstract int getNumberOfSides();//same as getArea

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCornered()
    {
        return getNumberOfSides()>0;
    }
}
