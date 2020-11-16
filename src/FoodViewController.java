import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class FoodViewController implements Initializable {

    @FXML
    private ComboBox<FoodItem> comboBox;

    @FXML
    private Button addButton;

    @FXML
    private ListView<FoodItem> listView;

    @FXML
    private Label caloriesLabel;

    @FXML
    private Label proteinsLabel;

    @FXML
    private Label sugarLabel;

    @FXML
    private Label errMsgLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.setPromptText("Select Food Items");
        try {
            ArrayList<FoodItem> foodItems = DBUtility.getFoodItems();
            // You have to implement Comparable in FoodItem class
            Collections.sort(foodItems);
            comboBox.getItems().addAll(foodItems);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        updateLabels();
        errMsgLabel.setText("");

    }

    @FXML
    private void deleteFoodItem(){
        errMsgLabel.setText("");
        FoodItem foodItemToRemove = listView.getSelectionModel().getSelectedItem();
        if (foodItemToRemove == null)
            errMsgLabel.setText("You need to select a food item");
        else {
            listView.getItems().remove(foodItemToRemove);
            updateLabels();
        }
    }

    @FXML
    private void addToOrder()
    {
        FoodItem foodItem = comboBox.getValue();
        if(foodItem != null){
            listView.getItems().add(foodItem);
            Collections.sort(listView.getItems());
            updateLabels();
        } else {
            errMsgLabel.setText("Select a food item from the drop down");
        }
    }

    private void updateLabels()
    {
        int sugars = 0;
        int proteins = 0;
        int calories = 0;

        for (FoodItem foodItem : listView.getItems()){
            sugars += foodItem.getSugar();
            proteins += foodItem.getProtein();
            calories += foodItem.getCalories();
        }

        proteinsLabel.setText(String.format("%3d g", proteins));
        sugarLabel.setText(String.format("%3d g", sugars));
        caloriesLabel.setText(String.format("%3d cal", calories));
    }
}