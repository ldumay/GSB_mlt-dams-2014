package gsb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DonneesClient {
  public String TempLogin = GuiMainPanel.Identifiant;
  
  public static String ClientType = "";
  
  public static String Nom = "";
  
  public static String Prenom = "";
  
  public static String Adresse = "";
  
  public static String CP = "";
  
  public static String Ville = "";
  
  public static String Matricule = "";
  
  public static String Login = "";
  
  public static String DateEmbauche = "";
  
  public static String CodeSEC = "";
  
  public static String CodeLAB = "";
  
  public DonneesClient() {
    String[] infosConnexionBDD = InfosConnexionBDD.InfosConnexionBDD();
    String BDD = infosConnexionBDD[0];
    String url = infosConnexionBDD[1];
    String user = infosConnexionBDD[2];
    String passwd = infosConnexionBDD[3];
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection(url, user, passwd);
      Statement stmt = con.createStatement();
      String tmpClientType = null;
      String tmpMatricule = null;
      ResultSet resultat = null;
      resultat = stmt.executeQuery("SELECT VIS_MATRICULE,VIS_NOM FROM visiteur WHERE VIS_NOM='" + this.TempLogin + "'");
      if (resultat.next())
        tmpMatricule = resultat.getString("VIS_MATRICULE"); 
      resultat = null;
      resultat = stmt.executeQuery("SELECT VIS_MATRICULE,JJMMAA,TRA_ROLE FROM travailler WHERE JJMMAA=(SELECT max(JJMMAA) FROM travailler WHERE VIS_MATRICULE='" + tmpMatricule + "') AND VIS_MATRICULE='" + tmpMatricule + "'");
      if (resultat.next())
        tmpClientType = resultat.getString("TRA_ROLE"); 
      resultat = null;
      resultat = stmt.executeQuery("SELECT * FROM visiteur WHERE VIS_NOM='" + this.TempLogin + "'");
      if (resultat.next()) {
        Matricule = tmpMatricule;
        Login = resultat.getString("VIS_NOM");
        Nom = resultat.getString("VIS_NOM");
        Prenom = resultat.getString("VIS_PRENOM");
        ClientType = tmpClientType;
        Adresse = resultat.getString("VIS_ADRESSE");
        CP = resultat.getString("VIS_CP");
        Ville = resultat.getString("VIS_VILLE");
        DateEmbauche = resultat.getString("VIS_DATEEMBAUCHE");
        CodeSEC = resultat.getString("SEC_CODE");
        CodeLAB = resultat.getString("LAB_CODE");
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
