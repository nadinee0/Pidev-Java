/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.utils;

import tn.leaguestorm.entities.Badge;

/**
 *
 * @author Bellalouna Iheb
 */
public class SelectedBadge {
      private static Badge selectedBadge;

    public static void setBadge(Badge b) {
        selectedBadge = b;
    }
    
    public static Badge getBadge() {
        return selectedBadge;
    }
    
}
