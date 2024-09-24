import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CircleRepository implements ShapeRepository<Circle> {
    DatabaseConnector myDatabase = new DatabaseConnector();

    public CircleRepository(){
        myDatabase.connect();
    }

    public void insert(Circle circle) {
        myDatabase.executeQuery("insert into circle VALUES(NULL, '" + circle.getName() + "', " + circle.getRadius() + ");");
    }

    public void delete(long id) {
        myDatabase.executeQuery("delete from circle where id=" + id + ";");
    }

    public List<Circle> getAll() {
        ResultSet result = myDatabase.executeQuery("Select* from circle;");
        ArrayList<Circle> circleList = new ArrayList<>();
        try {
            while (result.next()) {
                circleList.add(new Circle(result.getString("name"), result.getDouble("radius")));
                circleList.getLast().setId(result.getInt("id"));
            }
        }catch (Exception e){
            System.out.println("Couldn't get the list of circles due to an error!");
        }
        return circleList;
    }
}