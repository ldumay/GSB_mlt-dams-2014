package gsb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DonneesClient {
	public String TempLogin = GuiMainPanel.Identifiant;
	
	// Instanciation du type client connecté avec un cast de modification entre l'objet et le string de la donnée transférée
	public static String ClientType = "";
    // Instanciation des données Communes
	public static String Nom = "";
	public static String Prenom = "";
	public static String Adresse = "";
	public static String CP = "";
	public static String Ville = "";
	// Instanciation des données Visiteur
	public static String Matricule = "";
	public static String Login = "";
	public static String DateEmbauche = "";
	public static String CodeSEC = "";
	public static String CodeLAB = "";
	
	public DonneesClient(){
		
		// Méthode de récupération des information de connexion à la BDD
		String[] infosConnexionBDD = InfosConnexionBDD.InfosConnexionBDD();
		String BDD = infosConnexionBDD[0];
        String url = infosConnexionBDD[1];
        String user = infosConnexionBDD[2];
        String passwd = infosConnexionBDD[3];
		
		try {
            Class.forName("com.mysql.jdbc.Driver");
            // Connexion à la BDD
            Connection con = DriverManager.getConnection(url, user, passwd);
            Statement stmt = con.createStatement();
            // Création des données temporaires nécessaires
            String tmpClientType = null;
            String tmpMatricule = null;
            // Récupération du l'id de l'utilisateur
            ResultSet resultat = null;
			resultat = stmt.executeQuery("SELECT VIS_MATRICULE,VIS_NOM FROM visiteur WHERE VIS_NOM='" + TempLogin + "'");
			if (resultat.next()) {
				tmpMatricule = resultat.getString("VIS_MATRICULE");
			}
			// Récupération du type de l'utilisateur
			resultat = null;
			resultat = stmt.executeQuery("SELECT VIS_MATRICULE,JJMMAA,TRA_ROLE FROM travailler WHERE JJMMAA=(SELECT max(JJMMAA) FROM travailler WHERE VIS_MATRICULE='" + tmpMatricule + "') AND VIS_MATRICULE='" + tmpMatricule + "'");
			if (resultat.next()) {
				tmpClientType = resultat.getString("TRA_ROLE");
			}
			// Mémorisation des données de l'utilisateur en session
			resultat = null;
			resultat = stmt.executeQuery("SELECT * FROM visiteur WHERE VIS_NOM='" + TempLogin + "'");
			if (resultat.next()) {
				// Infos basiques
				Matricule = tmpMatricule;
				Login = resultat.getString("VIS_NOM");
				// Infos complètes
				Nom = resultat.getString("VIS_NOM");
				Prenom = resultat.getString("VIS_PRENOM");
				ClientType = tmpClientType;
				Adresse = resultat.getString("VIS_ADRESSE");
				CP = resultat.getString("VIS_CP");
				Ville = resultat.getString("VIS_VILLE");
				DateEmbauche =  resultat.getString("VIS_DATEEMBAUCHE");
				CodeSEC = resultat.getString("SEC_CODE");
				CodeLAB = resultat.getString("LAB_CODE");
			}
		} catch (Exception e){
            e.printStackTrace();
        }
	}
}
