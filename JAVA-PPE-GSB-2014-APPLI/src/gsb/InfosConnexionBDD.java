package gsb;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JButton;

public class InfosConnexionBDD {
  public static boolean EtatConnexion = false;
  
  static final JButton btnRafraichir = new JButton("Rafraichir");
  
  public static String EtatAff = null;
  
  public static String tmpBDD = null;
  
  public static String tmpURL = null;
  
  public static String tmpUser = null;
  
  public static String tmpPass = null;
  
  public static String[] InfosConnexionBDD() {
    String serveur = GuiMainPanel.serveur;
    if (serveur == "localhost") {
      tmpBDD = "gsb";
      tmpURL = "localhost";
      tmpUser = "root";
      tmpPass = "";
    } else if (serveur == "localhostMac") {
      tmpBDD = "gsb";
      tmpURL = "localhost";
      tmpUser = "root";
      tmpPass = "root";
    } else if (serveur == "personnelLoic") {
      tmpBDD = "mectrank_gsb";
      tmpURL = "109.234.161.177";
      tmpUser = "mectrank_user";
      tmpPass = "oZ,oT~}wx!;3";
    } else {
      tmpBDD = null;
      tmpURL = null;
      tmpUser = null;
      tmpPass = null;
    } 
    String BDD = tmpBDD;
    String url = "jdbc:mysql://" + tmpURL + ":3306/" + BDD;
    String user = tmpUser;
    String passwd = tmpPass;
    String[] TotalInfosBDD = { BDD, url, user, passwd };
    return TotalInfosBDD;
  }
  
  public InfosConnexionBDD() {
    ConnexionBDD();
  }
  
  public boolean ConnexionBDD() {
    String serveur = GuiMainPanel.serveur;
    String indic = "-> ";
    String[] infosConnexionBDD = InfosConnexionBDD();
    String BDD = infosConnexionBDD[0];
    String url = infosConnexionBDD[1];
    String user = infosConnexionBDD[2];
    String passwd = infosConnexionBDD[3];
    try {
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println(String.valueOf(indic) + "Driver O.K.");
      Connection conn = DriverManager.getConnection(url, user, passwd);
      System.out.println(String.valueOf(indic) + "Connexion reussi !");
      System.out.println(String.valueOf(indic) + "Serveur connecte : " + serveur);
      System.out.println(String.valueOf(indic) + "Adresse du serveur : " + url);
      System.out.println(String.valueOf(indic) + "Base de donnee connectee : '" + BDD + "'");
      System.out.println(String.valueOf(indic) + "Application connectee en tant que : '" + user + "'");
      EtatConnexion = true;
    } catch (Exception exception) {}
    System.out.println(String.valueOf(indic) + "Etat du serveur : " + EtatConnexion);
    VerifServiceBDD(EtatConnexion);
    System.out.println("# - - - - - - - - - - - - - - - - - - - - - - - - - - - #");
    return EtatConnexion;
  }
  
  public static String VerifServiceBDD(boolean EtatConnexion) {
    if (EtatConnexion) {
      EtatAff = "ON";
      btnRafraichir.setVisible(false);
      System.out.println("-> Etat du serveur : " + EtatAff);
    } 
    if (!EtatConnexion) {
      EtatAff = "OFF";
      btnRafraichir.setVisible(true);
      System.out.println("-> Etat du serveur : " + EtatAff);
    } 
    return EtatAff;
  }
}
