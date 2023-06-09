/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import tn.leaguestorm.entities.Category;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 *
 * @author Nadine
 */
public class CategoryListCell extends ListCell<Category> {

    @Override
    protected void updateItem(Category category, boolean empty) {
        super.updateItem(category, empty);

        if (empty || category == null) {
            setText(null);
            setGraphic(null);
        } else {
            ImageView imageView = new ImageView(new Image(category.getImg()));
            imageView.setFitWidth(200); // set the width to 50 pixels
            imageView.setFitHeight(200); // set the height to 50 pixels
            Label label = new Label(category.getNom());
            label.setStyle("-fx-font-size: 18pt; -fx-font-weight: bold;");
            HBox hbox = new HBox(imageView, label);
            hbox.setSpacing(10);
            setGraphic(hbox);
        }
    }

}
