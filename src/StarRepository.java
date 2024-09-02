import java.sql.ResultSet;

public class StarRepository {

    DatabaseConnector myDatabase = new DatabaseConnector();

    public StarRepository(){
        myDatabase.connect();
    }

    public void insert(Shape shape) {
        myDatabase.executeQuery("insert into star VALUES(NULL, '" + shape.getName() + "', " + ((Star) shape).getSideLength() + ");");
    }

    public void delete(long id) {
        myDatabase.executeQuery("delete from star where id=" + id + ";");
    }

    public ResultSet getAll() {
        return myDatabase.executeQuery("Select* from star;");
    }
}
