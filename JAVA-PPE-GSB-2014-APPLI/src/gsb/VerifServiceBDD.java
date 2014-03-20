package gsb;

import javax.swing.JButton;

public class VerifServiceBDD {
	// Instanciation des variable de l'actualisation de l'�tat du serveur
	final static JButton btnRafraichir = new JButton("Rafraichir");
	static boolean EtatConnexion = InfosConnexionBDD.connexion;
	public static String EtatAff = null;
		
	// M�thode de v�rification de l'�tat du serveur
	public static void VerifServiceBDD(Boolean EtatConnexion){
		// V�rification de la connexion avec la BDD
		InfosConnexionBDD InfosConnexionBDD = new InfosConnexionBDD();
		System.out.println("-> Classe de v�rification BDD.");
		
		System.out.println("-> Etat du serveur - Entr�e : " + EtatConnexion);
		
		// Affiche le bouton d'actualisation de l'�tat du serveur si inactif
		if(EtatConnexion == true){
			EtatAff = "ON";
			btnRafraichir.setVisible(false);
			
			// V�rification console
			System.out.println("-> Etat du serveur - Sortie : " + EtatConnexion);
			System.out.println("-> Etat du serveur - Aff : " + EtatAff);
		}
		if(EtatConnexion == false){
			EtatAff = "OFF";
			btnRafraichir.setVisible(true);
			
			// V�rification console
			System.out.println("-> Etat du serveur - Sortie : " + EtatConnexion);
			System.out.println("-> Etat du serveur - Aff : " + EtatAff);
		}
	}
}
