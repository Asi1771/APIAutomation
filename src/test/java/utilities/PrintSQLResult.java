package utilities;

import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintSQLResult {

    @Test
    public void printResults() throws SQLException {
        // print all products: id, title, price
        Connection connection = DBUtilities.getDBConnection();
        String sqlQuery = "select distinct id, title, price from products";
        PreparedStatement preparedStatement = connection.prepareStatement("");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String[] title = resultSet.getString("title").trim().split("\\s+");
            if(title.length == 3){
                System.out.println(resultSet.getString("title") + "\t" + resultSet.getString("id") + "\t" + resultSet);
            }
        }


    }
}
