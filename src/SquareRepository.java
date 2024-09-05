import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SquareRepository implements ShapeRepository<Square> {

    DatabaseConnector myDatabase = new DatabaseConnector();

    public SquareRepository(){
        myDatabase.connect();
    }

    public void insert(Square square) {
       myDatabase.executeQuery("insert into square VALUES(NULL, '" + square.getName() + "', " + square.getLength() + ");");
    }

    public void delete(long id) {
      myDatabase.executeQuery("delete from square where id=" + id + ";");
    }

    public List<Square> getAll() {
        ResultSet result = myDatabase.executeQuery("Select* from square;");
        ArrayList<Square> squareList = new ArrayList<>();
        try {
            while (result.next()) {
                squareList.add(new Square(result.getString("name"), result.getDouble("length")));
            }
        }catch (Exception e){
            System.out.println("Couldn't get the list of squares due to an error!");
        }
        return squareList;
    }
}
