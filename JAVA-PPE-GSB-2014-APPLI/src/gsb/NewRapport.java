package gsb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class NewRapport {
	// Données à insérer :
	public int PRA_NUM = 0;
	public int VIS_MATRICULE = 0;
	public String RAP_BILAN = "";
	public String RAP_MOTIF = "";
	
	public NewRapport(){
		
		// Données de connexion : 
		String[] infosConnexionBDD = InfosConnexionBDD.InfosConnexionBDD();
		// String BDD = infosConnexionBDD[0];
        String url = infosConnexionBDD[1];
        String user = infosConnexionBDD[2];
        String passwd = infosConnexionBDD[3];
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, passwd);

            //Objet Statement
            PreparedStatement prepare1 = conn.prepareStatement("INSERT INTO `rapport_visite` (`PRA_NUM`,`VIS_MATRICULE`,`RAP_DATE`,`RAP_BILAN`,`RAP_RAP_MOTIF`) VALUES (`"+PRA_NUM+"`,`"+VIS_MATRICULE+"`,`"+RAP_BILAN+"`,`"+RAP_MOTIF+"`)");
            //L'objet ResultSet contient le résultat de la requête SQL
            prepare1.executeUpdate();
            prepare1.close();
            // === 
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println("OK");
            }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("Oups, Erreur!");
            System.err.println("Le problème peut venir de votre\nDriver JDBC de MySQL");
            System.exit(0);
        }
	}
}
