/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Bellalouna Iheb
 */
interface IService<T> {
    public void ajouter(T a) throws SQLException;
    public void modifier(T a) throws SQLException;
    public void supprimer(int id) throws SQLException;
    public List<T> getAll() throws SQLException;  
}
