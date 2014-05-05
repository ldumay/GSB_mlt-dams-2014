package gsb;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class InfosConnexionBDD {
	
	public static boolean EtatConnexion = false;
	final static JButton btnRafraichir = new JButton("Rafraichir");
	public static String EtatAff = null;
	// Choix du serveur
	public static String tmpBDD = null;
	public static String tmpURL = null;
	public static String tmpUser = null;
	public static String tmpPass = null;
	
	public static String[] InfosConnexionBDD(){
		
		// Liste des serveurs de l'application
		String serveur = GuiMainPanel.serveur;
		if (serveur == "localhost"){
			tmpBDD = "gsb";
			tmpURL = "localhost";
			tmpUser = "root";
			tmpPass = "";
		}
		else if (serveur == "localhostMac"){
			tmpBDD = "gsb";
			tmpURL = "localhost";
			tmpUser = "root";
			tmpPass = "root";
		}
		else if (serveur == "hitemaLoic"){
			tmpBDD = "dumayl";
			tmpURL = "89.158.158.137";
			tmpUser = "dumayl";
			tmpPass = "221093o";
		}
		else if (serveur == "hitemaDamien"){
			tmpBDD = "";
			tmpURL = "";
			tmpUser = "";
			tmpPass = "";
		}
		else if (serveur == "personnelLoic"){
			tmpBDD = "dumayloi_mtl";
			tmpURL = "web1.quatdev.fr";
			tmpUser = "dumayloi_mtl";
			tmpPass = "BPJrtj34r5A4";
		}
		else if (serveur == "personnelDamien"){
			tmpBDD = "";
			tmpURL = "";
			tmpUser = "";
			tmpPass = "";
		}
		else{
			tmpBDD = null;
			tmpURL = null;
			tmpUser = null;
			tmpPass = null;
		}
		
        String BDD = tmpBDD;
        String url = "jdbc:mysql://" + tmpURL + ":3306/" + BDD;
        String user = tmpUser;
        String passwd = tmpPass;
        
        String TotalInfosBDD[] = {BDD, url, user, passwd};
		return TotalInfosBDD;
	}
	
	public InfosConnexionBDD(){
		ConnexionBDD();
	}
	
	public boolean ConnexionBDD(){
		String serveur = GuiMainPanel.serveur;
		
		System.out.println("# - - - - - - - - - - - - - - - - - - - - - - - - - - - #");
        String indic = "-> ";
        
        // Méthode de récupération des information de connexion à la BDD
		String[] infosConnexionBDD = InfosConnexionBDD.InfosConnexionBDD();
		String BDD = infosConnexionBDD[0];
        String url = infosConnexionBDD[1];
        String user = infosConnexionBDD[2];
        String passwd = infosConnexionBDD[3];
        
		try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println(indic + "Driver O.K.");

            Connection conn = DriverManager.getConnection(url, user, passwd);
            System.out.println(indic + "Connexion reussi !");
            System.out.println(indic + "Serveur connecté : " + serveur);
            System.out.println(indic + "Adresse du serveur : " + url);
            System.out.println(indic + "Base de donnee connectee : '" + BDD + "'");
            System.out.println(indic + "Application connectee en tant que : '" + user + "'");
            EtatConnexion = true;
        } catch (Exception e){
            // e.printStackTrace();
        }
		System.out.println(indic + "Etat du serveur : " + EtatConnexion);
		VerifServiceBDD(EtatConnexion);
        System.out.println("# - - - - - - - - - - - - - - - - - - - - - - - - - - - #");
        return EtatConnexion;
	}
	
	// Méthode de vérification de l'état du serveur
	public static String VerifServiceBDD(boolean EtatConnexion){
		
		// Affiche le bouton d'actualisation de l'état du serveur si inactif
		if(EtatConnexion == true){
			EtatAff = "ON";
			btnRafraichir.setVisible(false);
			
			// Vérification console
			System.out.println("-> Etat du serveur : " + EtatAff);
		}
		if(EtatConnexion == false){
			EtatAff = "OFF";
			btnRafraichir.setVisible(true);
			
			// Vérification console
			System.out.println("-> Etat du serveur : " + EtatAff);
		}
		
		return EtatAff;
	}
}
