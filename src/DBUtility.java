import java.sql.*;
import java.util.ArrayList;

public class DBUtility {
    private static String user = "student";
    private static String password = "student";

    public static ArrayList<FoodItem> getFoodItems() throws SQLException {
        ArrayList<FoodItem> foodItems = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/F20COMP1011T1S1", user, password);
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM edibleProducts");

            while(resultSet.next()){
                FoodItem newFood = new FoodItem(
                        resultSet.getInt("productID"),
                        resultSet.getInt("calories"),
                        resultSet.getInt("protein"),
                        resultSet.getInt("sugars"),
                        resultSet.getString("productName")
                );
                foodItems.add(newFood);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
            if (statement != null) statement.close();
            if (resultSet != null) resultSet.close();
            return foodItems;
        }
    }
}
