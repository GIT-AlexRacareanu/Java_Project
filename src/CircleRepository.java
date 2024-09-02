import java.sql.ResultSet;

public class CircleRepository {
    DatabaseConnector myDatabase = new DatabaseConnector();

    public CircleRepository(){
        myDatabase.connect();
    }

    public void insert(Shape shape) {
        myDatabase.executeQuery("insert into circle VALUES(NULL, '" + shape.getName() + "', " + ((Circle) shape).getRadius() + ");");
    }

    public void delete(long id) {
        myDatabase.executeQuery("delete from circle where id=" + id + ";");
    }

    public ResultSet getAll() {
        return myDatabase.executeQuery("Select* from circle;");
    }
}