import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StarRepository implements ShapeRepository<Star> {

    DatabaseConnector myDatabase = new DatabaseConnector();

    public StarRepository(){
        myDatabase.connect();
    }

    public void insert(Star star) {
        myDatabase.executeQuery("insert into star VALUES(NULL, '" + star.getName() + "', " + star.getSideLength() + ");");
    }

    public void delete(long id) {
        myDatabase.executeQuery("delete from star where id=" + id + ";");
    }

    public List<Star> getAll() {
        ResultSet result = myDatabase.executeQuery("Select* from star;");
        ArrayList<Star> starList = new ArrayList<>();
        try {
            while (result.next()) {
                starList.add(new Star(result.getString("name"), result.getDouble("length")));
                starList.getLast().setId(result.getInt("id"));
            }
        }catch (Exception e){
            System.out.println("Couldn't get the list of stars due to an error!");
        }
        return starList;
    }
}
