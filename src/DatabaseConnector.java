import java.sql.*;

public class DatabaseConnector {

    private Connection connection;

    public void connect(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/storage_schema");
        }
        catch (Exception e){
            System.out.println("error while communicating with database!");
        }
    }

    public ResultSet executeQuery(String sqlString) {
        ResultSet resultSet = null;
        try {
                Statement statement = connection.createStatement();
                String[] query = sqlString.split(" ");
                if(query[0].equalsIgnoreCase("SELECT*")) {
                    resultSet = statement.executeQuery(sqlString);
                }
                else {
                    statement.executeUpdate(sqlString);
                }
        }catch (Exception e){
               System.out.println("couldn't execute the specified query!");
        }
            return resultSet;
    }

    public void printResult(ResultSet resultSet){
        try {
            System.out.print("Table: " + resultSet.getMetaData().getTableName(1));
             while(resultSet.next()){
                 System.out.println();
                 for(int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++)
                    System.out.print(resultSet.getMetaData().getColumnLabel(i) + ":" +resultSet.getString(i)+ ", ");
             }
            System.out.println();
        }catch (Exception e){
            System.out.println("the query did not return any results!");
        }
    }

    public void disconnect() throws SQLException {
           if(connection!=null)
            connection.close();
    }

}
