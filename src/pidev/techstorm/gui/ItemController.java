/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.techstorm.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pidev.techstorm.entities.Events;
import pidev.techstorm.tests.MyListener;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ItemController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLabel;
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
           myListener.onClickListener(events);
          }    
    private Events events;
    private MyListener myListener;

    public void setData(Events events, MyListener myListener) {
        this.events = events;
        this.myListener = myListener;
        nameLabel.setText(events.getTitle());
        priceLabel.setText(FirstWindow.CURRENCY + events.getLocation());
        Image image = new Image(getClass().getResourceAsStream(events.getImage()));
        img.setImage(image);
    }
    
}
