
public final class DatabaseInitializer {

    private static final String createSquare = "Create Table IF NOT EXISTS square(id INT AUTO_INCREMENT Primary KEY,name VARCHAR(20),length DOUBLE);";
    private static final String createRectangle = "Create Table IF NOT EXISTS rectangle(id INT AUTO_INCREMENT Primary KEY,name VARCHAR(20),length DOUBLE,width DOUBLE);";
    private static final String createCircle = "Create Table IF NOT EXISTS circle(id INT AUTO_INCREMENT Primary KEY,name VARCHAR(20),radius DOUBLE);";
    private static final String createStar = "Create Table IF NOT EXISTS star ( id INT AUTO_INCREMENT Primary KEY,name VARCHAR(20),length DOUBLE );";

    public static void initialize(DatabaseConnector myDatabase) {
        try{
                myDatabase.executeQuery(createSquare);
                myDatabase.executeQuery(createRectangle);
                myDatabase.executeQuery(createCircle);
                myDatabase.executeQuery(createStar);
        }catch (Exception e) {
            System.out.println("Couldn't initialize the database");
        }
    }

}
