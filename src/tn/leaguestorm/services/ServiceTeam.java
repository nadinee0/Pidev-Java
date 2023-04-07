
package tn.leaguestorm.services;

/**
 *
 * @author dell
 */

   
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.leaguestorm.entities.Team;
import tn.leaguestorm.utils.MyConnection;


public class ServiceTeam implements IService<Team>{
    
    private MyConnection ds = MyConnection.getInstance();

    @Override
    public void ajouter(Team t) throws SQLException{
        String req = "INSERT INTO `team` (`nom`, `description`,`wins`,`losses`,`rate`,`color`,`logo`) VALUES ('"+t.getNom_team()+"', '"+t.getDescription_team()+"',,'"+t.getWins_team()+"','"+t.getLosses_team()+"','"+t.getRate_team()+"','"+t.getColor()+"','"+t.getLogo_team()+"' )"; 
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }
    
public void ajouter2(Team t) throws SQLException {
    String req = "INSERT INTO `team` ( `nom`, `description`, `wins`, `losses`, `rate`, `color`, `logo`) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement st = ds.getCnx().prepareStatement(req);
   
    st.setString(2, t.getNom_team());
    st.setString(3, t.getDescription_team());
    st.setInt(4, t.getWins_team());
    st.setInt(5, t.getLosses_team());
    st.setFloat(6, t.getRate_team());
    st.setString(7, t.getColor());
    st.setString(8, t.getLogo_team());
   
    st.executeUpdate();
}




    @Override
    public void modifier(Team t) throws SQLException{
        String req = "UPDATE `team` SET `nom` = '"+t.getNom_team()+"', `description` = '"+t.getDescription_team()+"', `wins`= '"+t.getWins_team()+"',`Losses` = '"+t.getLosses_team()+"',`rate` = '"+t.getRate_team()+"',,`Color` ='"+t.getColor()+"',`logo` = '"+t.getLogo_team()+"' WHERE `team`.`id` = "+t.getId();
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void supprimer(int id) throws SQLException{
        String req = "DELETE FROM `team` WHERE id ="+id;
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
    }

@Override
public List<Team> getAll() throws SQLException{
    List<Team> list = new ArrayList<>();
    
    String req = "SELECT * FROM team";
    PreparedStatement st = ds.getCnx().prepareStatement(req);
    ResultSet rs = st.executeQuery();
    while(rs.next()){
        Team t = new Team(rs.getInt("id"), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getFloat(6), rs.getString(7) , rs.getString(8));
        list.add(t);
    }
    
    return list;
}


   
    
    
}
