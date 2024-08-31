import java.sql.ResultSet;

public class RectangleRepository {

    DatabaseConnector myDatabase = new DatabaseConnector();

    public RectangleRepository(){
        myDatabase.connect();
    }

    public void insert(Shape shape) {
        myDatabase.executeQuery("insert into square VALUES(id," + shape.getName() + ", "+ ((Rectangle) shape).getLength() + ", "+ ((Rectangle) shape).getWidth() + ");");
    }

    public void update(Shape shape) {

    }

    public void delete(long id) {
        myDatabase.executeQuery("delete from rectangle where id=" + id + ";");
    }

    public ResultSet getAll() {
        return myDatabase.executeQuery("Select* from rectangle;");
    }
}
