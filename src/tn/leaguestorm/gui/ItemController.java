/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;

import tn.leaguestorm.tests.Front;
import tn.leaguestorm.tests.MyListener;
import tn.leaguestorm.entities.Article;

/**
 * FXML Controller class
 *
 * @author Nadine
 */
public class ItemController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click(MouseEvent event) {
           myListener.onClickListener(article);
          }    
    private Article article;
    private MyListener myListener;

    public void setData(Article article, MyListener myListener) {
        this.article = article;
        this.myListener = myListener;
        nameLabel.setText(article.getTitre());
        priceLable.setText(Front.CURRENCY + article.getPrix());
        Image image = new Image(getClass().getResourceAsStream(article.getImage()));
        img.setImage(image);
    }

  
    
}
