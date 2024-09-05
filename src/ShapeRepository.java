import java.util.List;

public interface ShapeRepository<GenericShape extends Shape> {

    void insert(GenericShape shape);

    void delete(long id);

    List<GenericShape> getAll();
}
