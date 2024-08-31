import java.sql.ResultSet;

public interface ShapeRepository {

    void insert(Shape shape);

    void update(Shape shape);

    void delete(long id);

    ResultSet getAll();
}
