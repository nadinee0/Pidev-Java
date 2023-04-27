
package tn.leaguestorm.services;

/**
 *
 * @author Dell
 */

   
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import tn.leaguestorm.entities.Organism;
import tn.leaguestorm.entities.Team;
import tn.leaguestorm.utils.MyConnection;


public class ServiceTeam implements IService<Team>{
    
    private MyConnection ds = MyConnection.getInstance();

@Override
public void ajouter(Team t) {
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(t.getDate_de_creation_team());

        String req = "INSERT INTO `team` (`nom_team`, `Organisme_id`, `description_team`, `wins_team`, `losses_team`, `rate_team`, `color`, `logo_team`, `date_de_creation_team`) VALUES ('" + t.getNom_team() + "','" + t.getOrganisme_id() + "','" + t.getDescription_team() + "','" + t.getWins_team() + "','" + t.getLosses_team() + "','" + t.getRate_team() + "','" + t.getColor() + "','" + t.getLogo_team() + "','" + dateString + "')"; 
         
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req);
        System.out.println("Team ajouté!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

public void ajouter2(Team t) {
    try {
        String req = "INSERT INTO `team` (`nom_team`,`Organisme_id`,`description_team`, `wins_team`, `losses_team`, `rate_team`, `color`, `logo_team`, `date_de_creation_team`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement st = ds.getCnx().prepareStatement(req);
       
        st.setString(1, t.getNom_team());
        st.setInt(2, t.getOrganisme_id());
        st.setString(3, t.getDescription_team());
        st.setInt(4, t.getWins_team());
        st.setInt(5, t.getLosses_team());
        st.setFloat(6, t.getRate_team());
        st.setString(7, t.getColor());
        st.setString(8, t.getLogo_team());
        st.setDate(9, new java.sql.Date(t.getDate_de_creation_team().getTime()));
        
        
        st.executeUpdate();
        System.out.println("Team ajouté !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


@Override
public void modifier(Team t) {
     String sql = "UPDATE team SET nom_team=?, organisme_id=?, description_team=?, wins_team=?, losses_team=?, rate_team=?, color=?, logo_team=?, date_de_creation_team=? WHERE id=?";
    try {                        
        PreparedStatement ps = ds.getCnx().prepareStatement(sql);
        ps.setString(1, t.getNom_team());
        ps.setInt(2, t.getOrganisme_id());
        ps.setString(3, t.getDescription_team());
        ps.setInt(4, t.getWins_team());
        ps.setInt(5, t.getLosses_team());
        ps.setFloat(6, t.getRate_team());
        ps.setString(7, t.getColor());
        ps.setString(8, t.getLogo_team());
        ps.setDate(9, new java.sql.Date(t.getDate_de_creation_team().getTime()));
        ps.setInt(10, t.getId());
        ps.executeUpdate();
        System.out.println("Team Modifié !");
        System.out.println(t);
    } catch(SQLException ex) {
        System.err.println(ex.getMessage());
    }    
}



    @Override
    public void supprimer(int id) {
        try {
        String req = "DELETE FROM `team` WHERE id ="+id;
        Statement st = ds.getCnx().createStatement();
        st.executeUpdate(req); System.out.println("Team supprimé!");}
        catch (SQLException ex){
        
        System.out.println(ex.getMessage());
        }
    }
    /* @Override
public void supprimer(int id) {
    String sql = "DELETE FROM team WHERE id = ?";
    try (PreparedStatement ps = ds.getCnx().prepareStatement(sql)) {
        ps.setInt(1, id);
        int rowsDeleted = ps.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Team supprimé!");
        } else {
            System.out.println("Aucune équipe supprimée!");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
*/

@Override
public List<Team> getAll() throws SQLException {
    List<Team> list = new ArrayList<>();
    try {
        String req = "SELECT * FROM team";
        PreparedStatement st = ds.getCnx().prepareStatement(req);
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
            Team t = new Team(rs.getInt("id"), rs.getString("nom_team"), rs.getString("description_team"), rs.getInt("wins_team"), rs.getInt("losses_team"), rs.getFloat("rate_team"), rs.getString("color"), rs.getString("logo_team"), rs.getDate("date_de_creation_team"), rs.getInt("Organisme_id"));
            list.add(t);
        }
    } catch(SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return list;
}



private String formatDate(Date Date_de_creation_team) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(Date_de_creation_team);
}

  
}


   
    
    

