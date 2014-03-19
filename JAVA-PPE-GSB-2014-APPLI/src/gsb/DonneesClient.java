package gsb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DonneesClient {
	public static String TempLogin = GuiMainPanel.Identifiant;
	public static Object TempType = GuiMainPanel.TypeUser;
	public static String[] DonneesClient(){
		
		// Méthode de récupération des information de connexion à la BDD
		String[] infosConnexionBDD = InfosConnexionBDD.InfosConnexionBDD();
		String BDD = infosConnexionBDD[0];
        String url = infosConnexionBDD[1];
        String user = infosConnexionBDD[2];
        String passwd = infosConnexionBDD[3];
        // Instanciation du type client connecté avec un cast de modification entre l'objet et le string de la donnée transférée
        String ClientType = (String) TempType;
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
		// Instanciation des données Praticien
		String Num = "";
		String Coef = "";
		String CodeTYP = "";
		
		try {
            Class.forName("com.mysql.jdbc.Driver");
            // Connexion à la BDD
            Connection con = DriverManager.getConnection(url, user, passwd);
            Statement stmt = con.createStatement();
            // Récupération des données
			ResultSet resultat = null;
			if(TempType == "Visiteur"){
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
					if(CodeSEC == null){
						CodeSEC = "n.c";
					}
					CodeLAB = resultat.getString("LAB_CODE");
					if(CodeLAB == null){
						CodeLAB = "n.c";
					}
				}
			}
			else if(TempType == "Praticien"){
				resultat = stmt.executeQuery("SELECT * FROM praticien WHERE VIS_NOM='" + TempLogin + "'");
				if (resultat.next()) {
					Num = resultat.getString("PRA_NUM");
					Nom = resultat.getString("PRA_NOM");
					Prenom = resultat.getString("PRA_PRENOM");
					Adresse = resultat.getString("PRA_ADRESSE");
					CP = resultat.getString("PRA_CP");
					Ville = resultat.getString("PRA_VILLE");
					Coef = resultat.getString("PRA_COEFNOTORIETE");
					CodeTYP = resultat.getString("TYP_CODE");
				}
			}
        } catch (Exception e){
            e.printStackTrace();
        }
		String ClientInfos [] = {ClientType, Nom, Prenom, Adresse, CP, Ville, Matricule, Login, DateEmbauche, CodeSEC, CodeLAB, Num, Coef, CodeTYP};
		return ClientInfos;
	}
}
