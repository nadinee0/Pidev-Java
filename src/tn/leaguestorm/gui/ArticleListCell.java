/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import tn.leaguestorm.entities.Article;

/**
 *
 * @author Nadine
 */
public class ArticleListCell extends ListCell<Article> {

     @Override
    protected void updateItem(Article article, boolean empty) {
         super.updateItem(article, empty);

        if (empty || article == null) {
            setText(null);
            setGraphic(null);
        } else {
            ImageView imageView = new ImageView(new Image(article.getImage()));
            imageView.setFitWidth(300); // set the width to 50 pixels
            imageView.setFitHeight(300); // set the height to 50 pixels
            Label label = new Label(article.getTitre());
            label.setStyle("-fx-font-size: 14pt; -fx-font-weight: bold;");
            HBox hbox = new HBox(imageView, label);
            hbox.setSpacing(10);
            setGraphic(hbox);
        }
    }

    }
    

