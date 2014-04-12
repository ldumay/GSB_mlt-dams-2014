package gsb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DonneesClient {
	public static String TempLogin = GuiMainPanel.Identifiant;
	public DonneesClient(){
		
		// Méthode de récupération des information de connexion à la BDD
		String[] infosConnexionBDD = InfosConnexionBDD.InfosConnexionBDD();
		String BDD = infosConnexionBDD[0];
        String url = infosConnexionBDD[1];
        String user = infosConnexionBDD[2];
        String passwd = infosConnexionBDD[3];
        // Instanciation du type client connecté avec un cast de modification entre l'objet et le string de la donnée transférée
        String ClientType = "";
        // Instanciation des données Communes
		String Nom = "";
		String Prenom = "";
		String Adresse = "";
		String CP = "";
		String Ville = "";
		// Instanciation des données Visiteur
		String Matricule = "";
		String Login = "";
		String DateEmbauche = "";
		String CodeSEC = "";
		String CodeLAB = "";
		
		try {
            Class.forName("com.mysql.jdbc.Driver");
            // Connexion à la BDD
            Connection con = DriverManager.getConnection(url, user, passwd);
            Statement stmt = con.createStatement();
            // Récupération des données
			ResultSet resultat = null;
			resultat = stmt.executeQuery("SELECT * FROM visiteur WHERE VIS_NOM='" + TempLogin + "'");
			if (resultat.next()) {
				Matricule = resultat.getString("VIS_MATRICULE");
				Nom = resultat.getString("VIS_NOM");
				Prenom = resultat.getString("VIS_PRENOM");
				Login = resultat.getString("VIS_Login");
				Adresse = resultat.getString("VIS_Mdp");
				CP = resultat.getString("VIS_CP");
				Ville = resultat.getString("VIS_ADRESSE");
				DateEmbauche =  resultat.getString("VIS_DATEEMBAUCHE");
				CodeSEC = resultat.getString("SEC_CODE");
				if(CodeSEC != null){
					ClientType = "Délégué";
				}
				else{
					ClientType = "Visiteur";
				}
			}
        } catch (Exception e){
            e.printStackTrace();
        }
	}
}
