package gsb;

import javax.swing.JButton;

public class VerifServiceBDD {
	// Instanciation des variable de l'actualisation de l'état du serveur
	final static JButton btnRafraichir = new JButton("Rafraichir");
	static boolean EtatConnexion = InfosConnexionBDD.connexion;
	public static String EtatAff = null;
		
	// Méthode de vérification de l'état du serveur
	public static void VerifServiceBDD(Boolean EtatConnexion){
		// Vérification de la connexion avec la BDD
		InfosConnexionBDD InfosConnexionBDD = new InfosConnexionBDD();
		System.out.println("-> Classe de vérification BDD.");
		
		System.out.println("-> Etat du serveur - Entrée : " + EtatConnexion);
		
		// Affiche le bouton d'actualisation de l'état du serveur si inactif
		if(EtatConnexion == true){
			EtatAff = "ON";
			btnRafraichir.setVisible(false);
			
			// Vérification console
			System.out.println("-> Etat du serveur - Sortie : " + EtatConnexion);
			System.out.println("-> Etat du serveur - Aff : " + EtatAff);
		}
		if(EtatConnexion == false){
			EtatAff = "OFF";
			btnRafraichir.setVisible(true);
			
			// Vérification console
			System.out.println("-> Etat du serveur - Sortie : " + EtatConnexion);
			System.out.println("-> Etat du serveur - Aff : " + EtatAff);
		}
	}
}
