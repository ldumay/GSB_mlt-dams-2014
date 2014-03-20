package gsb;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class InfosConnexionBDD {
	
	public static boolean connexion = false;
	
	public static String[] InfosConnexionBDD(){
        String BDD = "gsb";
        String url = "jdbc:mysql://localhost:3306/" + BDD;
        String user = "root";
        String passwd = "";
        
        String TotalInfosBDD[] = {BDD, url, user, passwd};
		return TotalInfosBDD;
	}
	
	public InfosConnexionBDD(){
		System.out.println("# - - - - - - - - - - - - - - - - - - - - - - - - - - - #");
    	System.out.println("Connexion a la BDD");
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
            System.out.println(indic + "Base de donnee connectee : '" + BDD + "'");
            System.out.println(indic + "Application connectee en tant que : '" + user + "'");
            connexion = true;
        } catch (Exception e){
            // e.printStackTrace();
        }
		System.out.println(indic + "Etat du serveur : " + connexion);
        System.out.println("# - - - - - - - - - - - - - - - - - - - - - - - - - - - #");
	}
}
