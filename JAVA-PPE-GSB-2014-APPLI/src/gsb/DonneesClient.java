package gsb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DonneesClient {
	public static String TempLogin = GuiMainPanel.Identifiant;
	public DonneesClient(){
		
		// M�thode de r�cup�ration des information de connexion � la BDD
		String[] infosConnexionBDD = InfosConnexionBDD.InfosConnexionBDD();
		String BDD = infosConnexionBDD[0];
        String url = infosConnexionBDD[1];
        String user = infosConnexionBDD[2];
        String passwd = infosConnexionBDD[3];
        // Instanciation du type client connect� avec un cast de modification entre l'objet et le string de la donn�e transf�r�e
        String ClientType = "";
        // Instanciation des donn�es Communes
		String Nom = "";
		String Prenom = "";
		String Adresse = "";
		String CP = "";
		String Ville = "";
		// Instanciation des donn�es Visiteur
		String Matricule = "";
		String Login = "";
		String DateEmbauche = "";
		String CodeSEC = "";
		String CodeLAB = "";
		
		try {
            Class.forName("com.mysql.jdbc.Driver");
            // Connexion � la BDD
            Connection con = DriverManager.getConnection(url, user, passwd);
            Statement stmt = con.createStatement();
            // R�cup�ration des donn�es
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
					ClientType = "D�l�gu�";
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
