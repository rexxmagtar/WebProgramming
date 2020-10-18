package com.company.sample;

import com.company.PresentManager;
import com.company.goodies.Goody;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Controller {

    public class FloatPropertyListener implements javafx.beans.value.ChangeListener<String>
    {
private TextField fieldToFollow;
        public FloatPropertyListener(TextField fieldToFollow){
this.fieldToFollow = fieldToFollow;
        }

        @Override
        public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
            if (!newValue.matches("([0-9]*[.])?[0-9]+")) {
                fieldToFollow.setText(newValue.replaceAll("[^\\d.]",""));
            }
        }
    }

    public ArrayList<Goody> present;
    public PresentManager manager;
    public TextArea OutputLabel;
    public TextField maxWeight;
    public TextField minWeight;
    public TextField minCalories;
    public TextField minShelfLife;
    public TextField maxCalories;
    public TextField maxShelfLife;

    public Controller(){
        System.out.println("Controller created");
        manager = new PresentManager();
        present = new ArrayList<>();

    }

    public void Init(){
        maxWeight.textProperty().addListener(new FloatPropertyListener(maxWeight));
        minWeight.textProperty().addListener(new FloatPropertyListener(minWeight));
        maxCalories.textProperty().addListener(new FloatPropertyListener(maxCalories));
        minCalories.textProperty().addListener(new FloatPropertyListener(minCalories));
        minShelfLife.textProperty().addListener(new FloatPropertyListener(minShelfLife));
        maxShelfLife.textProperty().addListener(new FloatPropertyListener(maxShelfLife));
    }

    public void CreatePresentButton(ActionEvent actionEvent) {
        present = manager.createPresent();
        OutputLabel.setText(manager.getPresentInfoString(present));
    }

    public void SortButton(ActionEvent actionEvent) {
        manager.sortGoodiesByWeight(present);
        OutputLabel.setText(manager.getPresentInfoString(present));
    }


    public void GetWeightButton(ActionEvent actionEvent) {
        OutputLabel.setText(Float.toString(manager.getGoodiesWight(present)));
    }

    public void ShowButton(ActionEvent actionEvent) {
        OutputLabel.setText(manager.getPresentInfoString(present));
    }

    public void SearchButton(ActionEvent actionEvent) {
        OutputLabel.setText(manager.getPresentInfoString( manager.findGoodies(
                present,
                Float.parseFloat( minWeight.getText()),
                Float.parseFloat( maxWeight.getText()),
                Float.parseFloat( minCalories.getText()),
                Float.parseFloat( maxCalories.getText()),
                Float.parseFloat( minShelfLife.getText()),
                Float.parseFloat( maxShelfLife.getText()),
                ""
        )));
    }
}
