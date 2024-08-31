import java.sql.ResultSet;

public class SquareRepository implements ShapeRepository {

    DatabaseConnector myDatabase = new DatabaseConnector();

    public SquareRepository(){
        myDatabase.connect();
    }

    public void insert(Shape shape) {
       myDatabase.executeQuery("insert into square VALUES(id," + shape.getName() + ", " + ((Square) shape).getLength() + ");");
    }

    public void update(Shape shape) {

    }

    public void delete(long id) {
      myDatabase.executeQuery("delete from square where id=" + id + ";");
    }

    public ResultSet getAll() {
        return myDatabase.executeQuery("Select* from square;");
    }
}
