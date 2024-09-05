import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RectangleRepository implements ShapeRepository<Rectangle> {

    DatabaseConnector myDatabase = new DatabaseConnector();

    public RectangleRepository(){
        myDatabase.connect();
    }

    public void insert(Rectangle rectangle) {
        myDatabase.executeQuery("insert into rectangle VALUES(NULL, '" + rectangle.getName() + "' , "+ rectangle.getLength() + ", "+ rectangle.getWidth() + ");");
    }

    public void delete(long id) {
        myDatabase.executeQuery("delete from rectangle where id=" + id + ";");
    }

    public List<Rectangle> getAll() {
        ResultSet result = myDatabase.executeQuery("Select* from rectangle;");
        ArrayList<Rectangle> rectangleList = new ArrayList<>();
        try {
            while (result.next()) {
                rectangleList.add(new Rectangle(result.getString("name"), result.getDouble("length"),result.getDouble("width")));
            }
        }catch (Exception e){
            System.out.println("Couldn't get the list of rectangles due to an error!");
        }
        return rectangleList;
    }
}
