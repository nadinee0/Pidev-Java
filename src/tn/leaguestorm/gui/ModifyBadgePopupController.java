/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tn.leaguestorm.entities.Badge;

/**
 *
 * @author Bellalouna Iheb
 */
public class ModifyBadgePopupController {

    @FXML
    private TextField tfReference;
    @FXML
    private TextField tfValue;
    @FXML
    private TextArea tfDescription;

    public void setBadgeInfo(Badge badge) {
        tfReference.setText(badge.getLogo());
        tfValue.setText(String.valueOf(badge.getValeur()));
        tfDescription.setText(badge.getDescription());
    }
}
