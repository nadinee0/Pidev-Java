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
import javafx.scene.layout.HBox;
import pidev.techstorm.entities.Events;
import pidev.techstorm.tests.MyListener;
import pidev.techstorm.entities.Tickets;
import pidev.techstorm.tests.MyListener1;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class CardController implements Initializable {

    @FXML
    private HBox box;
    @FXML
    private ImageView image;
    @FXML
    private Label bookname;
    @FXML
    private Label authorname;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void click(MouseEvent event) {
           myListener1.onClickListener1(tickets);
          }    
    private Tickets tickets;
    private MyListener1 myListener1;

    public void setData(Events events, MyListener myListener) {
        this.tickets = tickets;
        this.myListener1 = myListener1;
        bookname.setText(tickets.getType());
        authorname.setText(FirstWindow.CURRENCY + tickets.getQuantite());
//        Image image = new Image(getClass().getResourceAsStream(events.getImage()));
//        image.setImage(image);
    }
    
    
}
