package gsb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class NewRapport {
	// Données à insérer :
	// -> ID
	public String tmpVIS_MATRICULE = GuiMainPanel.tmpVIS_MATRICULE;
	public String tmpPRA_NUM = GuiMainPanel.tmpPRA_NUM;
	// -> Rapport
	public String tmpRAP_BILAN = GuiMainPanel.tmpRAP_BILAN;
	public String tmpRAP_MOTIF = GuiMainPanel.tmpRAP_MOTIF;
	// -> Echantillion
	public String tmpMED_DEPOTLEGAL_1 = GuiMainPanel.tmpMED_DEPOTLEGAL_1;
	public String tmpMED_DEPOTLEGAL_2 = GuiMainPanel.tmpMED_DEPOTLEGAL_2;
	public static String tmpQteMed_1 = GuiMainPanel.tmpQteMed_1;
  	public static String tmpQteMed_2 = GuiMainPanel.tmpQteMed_2;
	
	public NewRapport(){
		// Données de connexion : 
		String[] infosConnexionBDD = InfosConnexionBDD.InfosConnexionBDD();
		// String BDD = infosConnexionBDD[0];
        String url = infosConnexionBDD[1];
        String user = infosConnexionBDD[2];
        String passwd = infosConnexionBDD[3];
        
        // Verif
        System.out.println("Matricule : " + tmpVIS_MATRICULE);
        System.out.println("N° Prat : " + tmpPRA_NUM);
        System.out.println("Txt Bilan : " + tmpRAP_BILAN);
        System.out.println("Txt Motif : " + tmpRAP_MOTIF);
        System.out.println("Med 1 :  " + tmpMED_DEPOTLEGAL_1);
        System.out.println("Med 2 : " + tmpMED_DEPOTLEGAL_2);
        System.out.println("tmpQte 1 : " + tmpQteMed_1);
        System.out.println("tmpQte 2 : " + tmpQteMed_2);
        
        // Init
        int x = 0;
        int QteMed_1 = 0;
        int QteMed_2 = 0;
        QteMed_1 = Integer.parseInt(tmpQteMed_1);
        QteMed_2 = Integer.parseInt(tmpQteMed_2);
        System.out.println("Qte 1 : " + QteMed_1);
        System.out.println("Qte 2 : " + QteMed_2);
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, passwd);
            
            // But, découper la donnée "tmpPRA_NUM" en 2 pour avoir le Nom & le Prénom. la valeur " "  sépare ses 2 données.
            Char tmpNOM = tmpPRA_NUM.varPhp.strstr(" ");
            Char tmpPRENOM = tmpPRA_NUM.varPhp.strstr(" ");
            
            ResultSet resultat = null;
            Statement stmt = conn.createStatement();
            resultat = stmt.executeQuery("SELECT PRA_NUM FROM praticien WHERE PRA_NOM='"+tmpNOM+"' AND PRA_PRENOM='"+tmpPRENOM+"'");
            while (resultat.next()) {
            	tmpPRA_NUM = resultat.getString("PRA_NUM");
            }
            
            // Création du rapport
            PreparedStatement prepare1 = conn.prepareStatement("INSERT INTO `rapport_visite` (`PRA_NUM`,`VIS_MATRICULE`,`RAP_DATE`,`RAP_BILAN`,`RAP_MOTIF`) VALUES ('"+tmpPRA_NUM+"','"+tmpVIS_MATRICULE+"',NOW(),'"+tmpRAP_BILAN+"','"+tmpRAP_MOTIF+"')");
            prepare1.executeUpdate();
            prepare1.close();
            System.out.println("Etape 1 - OK");
            
            // Récupération du N° du rapport
            int tmpRAP_NUM = 0;
            resultat = stmt.executeQuery("SELECT RAP_NUM,VIS_MATRICULE,RAP_MOTIF FROM rapport_visite WHERE VIS_MATRICULE='"+tmpVIS_MATRICULE+"' AND RAP_MOTIF='"+tmpRAP_MOTIF+"'");
            while (resultat.next()) {
            	tmpRAP_NUM = resultat.getInt("RAP_NUM");
            }
            System.out.println("Etape 2 - OK");
            
            // Insertion des échantillions pour le rapport créé
            while(x<2){
            	x++;
            	if((x==1) && (tmpMED_DEPOTLEGAL_1!=null) && (QteMed_1!=0)){
            		// --> 
                    PreparedStatement prepare = conn.prepareStatement("INSERT INTO `offrir` (`VIS_MATRICULE`,`RAP_NUM`,`MED_DEPOTLEGAL`,`OFF_QTE`) VALUES (`"+tmpVIS_MATRICULE+"`,`"+tmpRAP_NUM+"`,`"+tmpMED_DEPOTLEGAL_1+"`,`"+QteMed_1+"`)");
                    prepare.executeUpdate();
                    prepare.close();
            	}
            	if((x==2) && (tmpMED_DEPOTLEGAL_2!=null) && (QteMed_2!=0)){
            		// --> 
                    PreparedStatement prepare = conn.prepareStatement("INSERT INTO `offrir` (`VIS_MATRICULE`,`RAP_NUM`,`MED_DEPOTLEGAL`,`OFF_QTE`) VALUES (`"+tmpVIS_MATRICULE+"`,`"+tmpRAP_NUM+"`,`"+tmpMED_DEPOTLEGAL_2+"`,`"+QteMed_2+"`)");
                    prepare.executeUpdate();
                    prepare.close();
            	}
            }
            System.out.println("Etape 3 - OK");
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
