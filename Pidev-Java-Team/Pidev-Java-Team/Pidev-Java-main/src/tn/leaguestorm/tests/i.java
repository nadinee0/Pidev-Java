/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.tests;

/**
 *
 * @author Dell
 */
/*----------------------------------------------------------------------------------------------------
----------------------------------TEAM----------------------------------------------------------------
--------------------AJOUTER---------------------------------------------------------------------------

    ServiceTeam t3 = new ServiceTeam();
    Team newTeam = new Team();
    newTeam.setNom_team("FC TUNISIA");
    newTeam.setDescription_team("ici c tunisie ");
    newTeam.setWins_team(10);
    newTeam.setLosses_team(5);
    newTeam.setRate_team(0.66f);
    newTeam.setColor("#FF0EE1");
    newTeam.setLogo_team("logoTun.png");
    newTeam.setDate_de_creation_team(new java.sql.Date(System.currentTimeMillis()));
    newTeam.setOrganisme_id(2); // l'ID de l'organisme parent auquel cette équipe appartient
    
    t3.ajouter2(newTeam);

-----------------UPDATE---------------------------------------------------------------------------------

 ServiceTeam t3 = new ServiceTeam();
        Team updatedTeam = new Team();
        
        updatedTeam.setNom_team("FC CHELSEA");
        updatedTeam.setOrganisme_id(1);
        updatedTeam.setDescription_team("The blues never lose");
        updatedTeam.setWins_team(30);
        updatedTeam.setLosses_team(2);
        updatedTeam.setRate_team(0.12f);
        updatedTeam.setColor("#01E000");
        updatedTeam.setLogo_team("logo.png");
        updatedTeam.setId(2);
        updatedTeam.setDate_de_creation_team(new java.sql.Date(System.currentTimeMillis()));
        t3.modifier(updatedTeam);

--------------DELETE-----------------------------------------------------------------------------------------


     ServiceTeam t = new ServiceTeam();
        int idToDelete = 6;
        t.supprimer(idToDelete);

---------------GETALL(LIST)--------------------------------------------------------------------------------------
public static void main(String[] args) throws SQLException {
    ServiceOrganism serviceOrganism = new ServiceOrganism();
    List<Organism> organisms = serviceOrganism.getAll();

    ServiceTeam serviceTeam = new ServiceTeam();
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (Organism organism : organisms) {
        sb.append(organism.getNom_commercial());
        sb.append(", ");
    }
    sb.delete(sb.length() - 2, sb.length());
    sb.append("]");

    System.out.println("Teams for organisms " + sb.toString());

    for (Organism organism : organisms) {
        System.out.println("Teams for organism " + organism.getNom_commercial());
        List<Team> teams = serviceTeam.getAll();
        boolean found = false;
        sb.setLength(0);
        for (Team team : teams) {
            if (organism.getId() == team.getOrganisme_id()) {
                sb.append(team.getNom_team());
                sb.append(", ");
                found = true;
            }
        }
        if (found) {
            sb.delete(sb.length() - 2, sb.length());
            System.out.println("\t[" + sb.toString() + "]");
        } else {
            System.out.println("\tNo team found for this organism.");
        }
    }

---------------->     
        ServiceTeam t = new ServiceTeam();
        try {
        List<Team> teams = t.getAll();
        for (Team team : teams) {
        System.out.println(team);
        }
        } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        }
}





-------------------------------------------------------------------------------------------------------------------
/*----------------------------------------------------------------------------------------------------
----------------------------------ORGANISM----------------------------------------------------------------
--------------------AJOUTER---------------------------------------------------------------------------
        ServiceOrganism o = new ServiceOrganism();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = null;
        try {
        utilDate = dateFormat.parse("2021-04-09");
        } catch (ParseException e) {
        System.out.println("Invalid date format!");
        return;
        }
        Date sqlDate = new java.sql.Date(utilDate.getTime());
        Organism o2 = new Organism( "FC ATELTICO .", " Corporation", sqlDate, 123457218, "alaa@barcelona.com", "Barcelona.png", "This is SUIIII bluegrana");
        o.ajouter2(o2);







-----------------UPDATE---------------------------------------------------------------------------------


            ServiceOrganism orgService = new ServiceOrganism();
        Organism updatedOrg = new Organism();
        updatedOrg.setId(10);
        updatedOrg.setNom_commercial("Fanatics Org");
        updatedOrg.setNom_juridique("Sehliano  Org Juridique");
        updatedOrg.setDate_de_fondation(new java.sql.Date(System.currentTimeMillis()));
        updatedOrg.setPhone_organisation(12367890);
        updatedOrg.setEmail_organisation("yoyoy@example.com");
        updatedOrg.setImage("sure .png");
        updatedOrg.setMore("This is the updated organism");
        orgService.modifier(updatedOrg);







--------------DELETE-----------------------------------------------------------------------------------------


     public static void main(String[] args) {
        ServiceOrganism o = new ServiceOrganism();
        int idToDelete = 23;
        o.supprimer(idToDelete);





---------------GETALL--------------------------------------------------------------------------------------


  ServiceOrganism o = new ServiceOrganism();
        List<Organism> organisms = o.getAll();
        for (Organism organism : organisms) {
            System.out.println(organism);
        }








 */
