
public abstract class Shape
{
    private String name;//common feature

    private long id;//common feature

    public abstract double getArea();//to be implemented in each of them

    public abstract int getNumberOfSides();//same as getArea

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isCornered()
    {
        return getNumberOfSides()>0;
    }
}
