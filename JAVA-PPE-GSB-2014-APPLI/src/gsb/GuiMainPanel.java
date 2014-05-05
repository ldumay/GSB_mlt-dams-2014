package gsb;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;

public class GuiMainPanel extends JFrame {

	private JPanel contentPane;
	private JTextField txtIdentifiant;
	public static String Identifiant;
	public String MotDePasse;
	// Type d'utilisateur
	public static String TypeUser;
	// Instanciation des variable de l'actualisation de l'état du serveur
	final static JButton btnRafraichir = new JButton("Rafraichir");
	public static String EtatAff = null;
	static boolean EtatConnexion = InfosConnexionBDD.EtatConnexion;
	// Choix du serveur
	final JComboBox serveurListe = new JComboBox();
	private Object serveurchoix;
	public static String serveur = null;
	// Instanciation de toutes autres intéractions
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField txtRapport;
	private JTextField txtRapportPraticien;
	private JTextField txtPraticienNumero;
	private JTextField txtPraticienNom;
	private JTextField txtPraticienPrenom;
	private JTextField txtPraticienAdresse;
	private JTextField txtPraticienVille;
	private JTextField txtPraticienCoef;
	private JTextField txtPraticienTypeCode;
	private JTextField txtPraticienCP;
	private JPasswordField txtMotDePasse;
	// Code Erreur
	public String ErrorLog = "Erreur de connexion";
	public String DEBUGG_MODE = "DEBUGG MODE : ";
	// Instanciation du type client connecté
	public String ClientType = null;
    // Instanciation des données Communes
	public String Nom = null;
	public String Prenom = null;
	public String Adresse = null;
	public String CP = null;
	public String Ville = null;
	// Instanciation des données Visiteur
	public String Matricule = null;
	public String Login = null;
	public String DateEmbauche = null;
	public String CodeSEC = null;
	public String CodeLAB = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// Démarrage de l'interface primaire de l'application
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Vérification de l'état du serveur BDD
					// InfosConnexionBDD connexionTest = new InfosConnexionBDD();
					// EtatConnexion = InfosConnexionBDD.EtatConnexion;
					// EtatAff = InfosConnexionBDD.EtatAff;
					EtatAff = "OFF";
					
					// Fait appel à la JFrame principale
					GuiMainPanel frame = new GuiMainPanel();
					// Permet de centré la JFrame principale
					frame.setLocationRelativeTo(null);
					// Permet d'affiché la JFrame principale
					frame.setVisible(true);
					
					final JLabel lblEtat = new JLabel(EtatAff);
					// Vérification de EtatAff
					if(EtatAff == "ON"){
						lblEtat.setText(EtatAff);
						btnRafraichir.setVisible(false);
					}
					if(EtatAff == "OFF"){
						lblEtat.setText(EtatAff);
						btnRafraichir.setVisible(true);
					}
					
					// Beta
					JOptionPane.showMessageDialog(null,"Merci de sélectionné un serveur actif.\n>>>>>>>>>>>> En cours <<<<<<<<<<<\n\nDSL. Ne fonctionne pour le moment \nseulement un serveur installé \nen local.", "Version Beta - Serveur Multiples", JOptionPane.WARNING_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GuiMainPanel() {
		setTitle("GSB - Compte Rendu - v1.13.20");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Instanciation des variables réccurentes
		final JPanel panelLog = new JPanel();
		final JPanel panelAccueil = new JPanel();
		final JPanel panelMenu = new JPanel();
		final JPanel panelRapport = new JPanel();
		final JPanel panelMedicaments = new JPanel();
		final JPanel panelPraticiens = new JPanel();
		final JPanel panelAutresVisiteurs = new JPanel();
		final JPanel panelNewRapport = new JPanel();
		
		final JLabel lblEtat = new JLabel(EtatAff);
		panelRapport.setVisible(false);
		panelAccueil.setVisible(false);
		panelMedicaments.setVisible(false);
		panelPraticiens.setVisible(false);
		panelAutresVisiteurs.setVisible(false);
		panelNewRapport.setVisible(false);
		
		panelLog.setVisible(true);
		panelLog.setBounds(10, 11, 914, 489);
		contentPane.add(panelLog);
		panelLog.setLayout(null);
		
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = CHOIX SERVEUR = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 
		
		serveurListe.setModel(new DefaultComboBoxModel (new String[] {"Veulliez choisir un serveur", "Serveur Local", "Serveur Local Mac", "Serveur Hitema [DUMAY Loic]", "Serveur Hitema [COUTEILLON Damien]", "Serveur Personnel [DUMAY Loic]", "Serveur Personnel [COUTEILLON Damien]"}));
		serveurListe.setBounds(10, 458, 277, 20);
		panelLog.add(serveurListe);
		
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = CONNEXION = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 
		JLabel lblConnexion = new JLabel("Connexion");
		lblConnexion.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblConnexion.setBounds(798, 365, 106, 23);
		panelLog.add(lblConnexion);
		
		JLabel lblIdentifiant = new JLabel("Identifiant :");
		lblIdentifiant.setBounds(741, 399, 64, 14);
		panelLog.add(lblIdentifiant);
		
		JLabel lblMotDePasse = new JLabel("  Mot de passe :");
		lblMotDePasse.setBounds(716, 427, 89, 14);
		panelLog.add(lblMotDePasse);
		
		txtIdentifiant = new JTextField();
		txtIdentifiant.setBounds(815, 396, 89, 20);
		panelLog.add(txtIdentifiant);
		txtIdentifiant.setColumns(10);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Récupération des Inputs de Log
				Identifiant = txtIdentifiant.getText();
				MotDePasse = txtMotDePasse.getText();
				
				// Vérification de l'état du serveur BDD
				InfosConnexionBDD connexionTest2 = new InfosConnexionBDD();
				EtatConnexion = InfosConnexionBDD.EtatConnexion;
				EtatAff = InfosConnexionBDD.EtatAff;
				
				if(EtatAff == "OFF"){
					lblEtat.setText(EtatAff);
					btnRafraichir.setVisible(true);
					JOptionPane.showMessageDialog(null,"Dsl, le serveur ne semble pas accessible !", ErrorLog, JOptionPane.WARNING_MESSAGE);
				}
				else if(TypeUser == "Choisir un type"){
					JOptionPane.showMessageDialog(null,"Veuillez choisir un type SVP", ErrorLog, JOptionPane.WARNING_MESSAGE);
				}
				else if((Identifiant.isEmpty()) && (MotDePasse.isEmpty()) ){
					JOptionPane.showMessageDialog(null,"L'identifiant et le mot de passe n'ont pas été saisi !", ErrorLog, JOptionPane.WARNING_MESSAGE);
				}
				else if(Identifiant.isEmpty()){
					JOptionPane.showMessageDialog(null,"L'identifiant n'a pas été saisi !", ErrorLog, JOptionPane.WARNING_MESSAGE);
				}
				else if(MotDePasse.isEmpty()){
					JOptionPane.showMessageDialog(null,"Le mot de passe n'a pas été saisi !", ErrorLog, JOptionPane.WARNING_MESSAGE);
				}
				else{
					String pilote = "com.mysql.jdbc.Driver";
					try {
						Class.forName(pilote);
						
						// Méthode de récupération des information de connexion à la BDD
						String[] infosConnexionBDD = InfosConnexionBDD.InfosConnexionBDD();
						String BDD = infosConnexionBDD[0];
				        String url = infosConnexionBDD[1];
				        String user = infosConnexionBDD[2];
				        String passwd = infosConnexionBDD[3];
				        Connection con = DriverManager.getConnection(url, user, passwd);
				        
						Statement stmt = con.createStatement();
				
						ResultSet resultat = null;
						
						String idUser = null;
						
						// Vérification du type d'utilisateur
						/*
						resultat = stmt.executeQuery("SELECT VIS_MATRICULE,VIS_NOM FROM visiteur WHERE VIS_NOM='"+ txtIdentifiant.getText() + "'");
						if (resultat.next()) {
							idUser = resultat.getString("VIS_MATRICULE");
						}
						resultat = stmt.executeQuery("SELECT VIS_MATRICULE,JJMMAA,TRA_ROLE FROM travailler WHERE JJMMAA=(SELECT max(JJMMAA) FROM travailler WHERE VIS_MATRICULE='" + idUser + "') AND VIS_MATRICULE='" + idUser + "'");
						if (resultat.next()) {
							idUser = resultat.getString("TRA_ROLE");
						}
						*/
						
						resultat = stmt.executeQuery("SELECT * FROM visiteur WHERE VIS_NOM='"+ txtIdentifiant.getText() + "'");
						if (resultat.next()) {
							String idClient = resultat.getString("VIS_MATRICULE");
							String date = resultat.getString("VIS_DATEEMBAUCHE");
							String[] dateSplit2 = date.split(" ");
							String[] dateSplit = dateSplit2[0].split("-");
							String jour = dateSplit[2];
							String mois = dateSplit[1];
							String annee = dateSplit[0];
							
							switch(mois){
								case "01" : {mois = "jan"; break;}
								case "02" : {mois = "feb"; break;}
								case "03" : {mois = "mar"; break;}
								case "04" : {mois = "apr"; break;}
								case "05" : {mois = "may"; break;}
								case "06" : {mois = "jun"; break;}
								case "07" : {mois = "jul"; break;}
								case "08" : {mois = "aug"; break;}
								case "09" : {mois = "sep"; break;}
								case "10" : {mois = "oct"; break;}
								case "11" : {mois = "nov"; break;}
								case "12" : {mois = "dec"; break;}
								default : {break;}
							}
							
							annee = annee.substring(2, 4);
							String date_emb = jour + "-" + mois + "-" + annee;
							String mdp = txtMotDePasse.getText();
							
							// Vérification du MDP
							// JOptionPane.showMessageDialog(null,"DONNEES : "+date_emb+" - "+mdp);
							
							if( mdp.equals(date_emb)){
								// Récupération des informations du client connecté
								DonneesClient Client = new DonneesClient();
								
								// Vérification des données de l'utilisateur connecté
								JOptionPane.showMessageDialog(null,"\nClientType : " + ClientType + "\nNom : " + Nom + "\n Prenom : " + Prenom + "\nAdresse : " + Adresse + "\nCP : " + CP  + "\nVille : " + Ville + "\nMatricule : " + Matricule + "\nLogin : " + Login + "\nDateEmbauche : " + DateEmbauche + "\nCodeSEC : " + CodeSEC + "\nCodeLAB : " + CodeLAB + "\n", DEBUGG_MODE + " Données Clients", JOptionPane.INFORMATION_MESSAGE);
								
								recupDonneesClient();

								// Vérification des données de l'utilisateur connecté
								JOptionPane.showMessageDialog(null,"\nClientType : " + ClientType + "\nNom : " + Nom + "\n Prenom : " + Prenom + "\nAdresse : " + Adresse + "\nCP : " + CP  + "\nVille : " + Ville + "\nMatricule : " + Matricule + "\nLogin : " + Login + "\nDateEmbauche : " + DateEmbauche + "\nCodeSEC : " + CodeSEC + "\nCodeLAB : " + CodeLAB + "\n", DEBUGG_MODE + " Données Clients", JOptionPane.INFORMATION_MESSAGE);
								
								// Passage en client connecter
								panelLog.setVisible(false);
								panelMenu.setVisible(true);
								panelAccueil.setVisible(true);
							}
							else{
								JOptionPane.showMessageDialog(null,"Oups, le mot de passe n'est pas correcte ! \n\n Assurez-vous d'entrer les 3 premières lettres du mois \n dans le mot de passe, tel que : XX-XXX-XX", ErrorLog, JOptionPane.WARNING_MESSAGE);
								// JOptionPane.showMessageDialog(null,txtMotDePasse.getText().length()+" et " +date_emb.length());
							}
						}
						else{
							JOptionPane.showMessageDialog(null,"L'identifiant saisie ne fais pas parti des " + TypeUser + "s !");
						}
						}catch (SQLException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
			}
		});
		
		txtMotDePasse = new JPasswordField();
		txtMotDePasse.setToolTipText("");
		txtMotDePasse.setBounds(815, 424, 89, 20);
		panelLog.add(txtMotDePasse);
		btnValider.setBounds(716, 455, 89, 23);
		panelLog.add(btnValider);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Modifications des Input de Log
				txtIdentifiant.setText("");
				txtMotDePasse.setText("");
			}
		});
		btnAnnuler.setBounds(815, 455, 89, 23);
		panelLog.add(btnAnnuler);
		
		JLabel lblEtatDuServeur = new JLabel("Etat du serveur : ");
		lblEtatDuServeur.setBounds(297, 453, 95, 23);
		panelLog.add(lblEtatDuServeur);
		
		lblEtat.setBounds(402, 453, 29, 23);
		panelLog.add(lblEtat);
		
		JButton btnAbout = new JButton("A propos");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Infos infos = new Infos();
				// Permet de centré la JFrame A propos
				infos.setLocationRelativeTo(null);
				infos.setVisible(true);
			}
		});
		btnAbout.setBounds(10, 425, 89, 23);
		panelLog.add(btnAbout);
		
		btnRafraichir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Vérification de la sélection
				serveurchoix = serveurListe.getSelectedItem();
				if(serveurchoix != "Veulliez choisir un serveur"){
					if(serveurchoix == "Serveur Local"){
						serveur = "localhost";
					}
					if(serveurchoix == "Serveur Local Mac"){
						serveur = "localhostMac";
					}
					else if(serveurchoix == "Serveur Hitema [DUMAY Loic]"){
						serveur = "hitemaLoic";
					}
					else if(serveurchoix == "Serveur Hitema [COUTEILLON Damien]"){
						serveur = "hitemaDamien";
					}
					else if(serveurchoix == "Serveur Personnel [DUMAY Loic]"){
						serveur = "personnelLoic";
					}
					else if(serveurchoix == "Serveur Personnel [COUTEILLON Damien]"){
						serveur = "personnelDamien";
					}
					// JOptionPane.showMessageDialog(null," " + serveurchoix + " - " + serveur, "Informations du serveur choisit", JOptionPane.WARNING_MESSAGE);
					// Vérification de l'état du serveur BDD
					InfosConnexionBDD connexionTest2 = new InfosConnexionBDD();
					EtatConnexion = InfosConnexionBDD.EtatConnexion;
					EtatAff = InfosConnexionBDD.EtatAff;
					// Vérification de EtatAff
					if(EtatAff == "ON"){
						lblEtat.setText(EtatAff);
						btnRafraichir.setVisible(false);
					}
					if(EtatAff == "OFF"){
						lblEtat.setText(EtatAff);
						btnRafraichir.setVisible(true);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Oups ! " + serveurchoix + ", SVP !", ErrorLog, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnRafraichir.setBounds(441, 455, 95, 23);
		panelLog.add(btnRafraichir);
		
		panelMenu.setVisible(false);
		
		panelMenu.setBounds(10, 11, 198, 489);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JLabel lblTitleMenu = new JLabel("Menu");
		lblTitleMenu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitleMenu.setBounds(10, 11, 178, 24);
		panelMenu.add(lblTitleMenu);
		
		JButton btnRapport = new JButton("Rapport de visite");
		btnRapport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAccueil.setVisible(false);
				panelRapport.setVisible(true);
				panelMedicaments.setVisible(false);
				panelPraticiens.setVisible(false);
				panelAutresVisiteurs.setVisible(false);
			}
		});
		
		JButton btnAccueil = new JButton("Accueil");
		btnAccueil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAccueil.setVisible(true);
				panelRapport.setVisible(false);
				panelMedicaments.setVisible(false);
				panelPraticiens.setVisible(false);
				panelAutresVisiteurs.setVisible(false);
				panelNewRapport.setVisible(false);
			}
		});
		btnAccueil.setBounds(10, 56, 178, 37);
		panelMenu.add(btnAccueil);
		btnRapport.setBounds(10, 104, 178, 37);
		panelMenu.add(btnRapport);
		
		JButton btnMedicaments = new JButton("M\u00E9dicaments");
		btnMedicaments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAccueil.setVisible(false);
				panelRapport.setVisible(false);
				panelMedicaments.setVisible(true);
				panelPraticiens.setVisible(false);
				panelAutresVisiteurs.setVisible(false);
				panelNewRapport.setVisible(false);
			}
		});
		btnMedicaments.setBounds(10, 152, 178, 37);
		panelMenu.add(btnMedicaments);
		
		JButton btnPraticiens = new JButton("Praticiens");
		btnPraticiens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAccueil.setVisible(false);
				panelRapport.setVisible(false);
				panelMedicaments.setVisible(false);
				panelPraticiens.setVisible(true);
				panelAutresVisiteurs.setVisible(false);
				panelNewRapport.setVisible(false);
			}
		});
		btnPraticiens.setBounds(10, 200, 178, 37);
		panelMenu.add(btnPraticiens);
		
		JButton btnAutresVisiteurs = new JButton("Autres Visiteurs");
		btnAutresVisiteurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAccueil.setVisible(false);
				panelRapport.setVisible(false);
				panelMedicaments.setVisible(false);
				panelPraticiens.setVisible(false);
				panelAutresVisiteurs.setVisible(true);
				panelNewRapport.setVisible(false);
			}
		});
		btnAutresVisiteurs.setBounds(10, 248, 178, 37);
		panelMenu.add(btnAutresVisiteurs);
		
		JButton btnAjoutRapport = new JButton("Nouveau Rapport");
		btnAjoutRapport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelMenu.setVisible(true);
				panelRapport.setVisible(false);
				panelAccueil.setVisible(false);
				panelMedicaments.setVisible(false);
				panelPraticiens.setVisible(false);
				panelAutresVisiteurs.setVisible(false);
				panelNewRapport.setVisible(true);
			}
		});
		btnAjoutRapport.setBounds(10, 329, 178, 37);
		panelMenu.add(btnAjoutRapport);
		
		JButton btnLogOut = new JButton("D\u00E9connexion");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Modifications des Input de Log
				txtIdentifiant.setText("");
				txtMotDePasse.setText("");
				
				panelMenu.setVisible(false);
				panelRapport.setVisible(false);
				panelAccueil.setVisible(false);
				panelMedicaments.setVisible(false);
				panelPraticiens.setVisible(false);
				panelAutresVisiteurs.setVisible(false);
				panelNewRapport.setVisible(false);
				panelLog.setVisible(true);
			}
		});
		
		btnLogOut.setBounds(10, 441, 178, 37);
		panelMenu.add(btnLogOut);
		
		// Masquage
		panelMenu.setVisible(false);
		
		panelAccueil.setBounds(214, 11, 710, 489);
		contentPane.add(panelAccueil);
		panelAccueil.setLayout(null);
		
		JLabel lblTitleHome = new JLabel("Bienvenue dans l'application de Compte rendu de GSB");
		lblTitleHome.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitleHome.setBounds(10, 11, 690, 24);
		panelAccueil.add(lblTitleHome);
		
		JLabel lblClientTitle = new JLabel("Bonjour, " + Nom + " " + Prenom + " .");
		lblClientTitle.setBounds(10, 70, 138, 14);
		panelAccueil.add(lblClientTitle);
		JLabel lblClientStatut = new JLabel("Vous \u00EAtes : " + ClientType + " .");
		lblClientStatut.setBounds(10, 95, 138, 14);
		panelAccueil.add(lblClientStatut);
		
		panelRapport.setBounds(214, 11, 710, 489);
		contentPane.add(panelRapport);
		panelRapport.setLayout(null);
		
		JLabel lblTitleRapport = new JLabel("Rapports");
		lblTitleRapport.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitleRapport.setBounds(321, 5, 68, 19);
		panelRapport.add(lblTitleRapport);
		
		JComboBox listRapport = new JComboBox();
		listRapport.setModel(new DefaultComboBoxModel(new String[] {"Choisir la date du rendez-vous", "Rapport n°x du 00/00/0000"}));
		listRapport.setBounds(27, 67, 271, 20);
		panelRapport.add(listRapport);
		
		JButton btnRapportValider = new JButton("Valider");
		btnRapportValider.setBounds(308, 66, 89, 23);
		panelRapport.add(btnRapportValider);
		
		JLabel lblRapportNumber = new JLabel("Num\u00E9ro : ");
		lblRapportNumber.setBounds(27, 101, 68, 14);
		panelRapport.add(lblRapportNumber);
		
		txtRapport = new JTextField();
		txtRapport.setBounds(105, 98, 113, 20);
		panelRapport.add(txtRapport);
		txtRapport.setColumns(10);
		
		JLabel lblRapportPraticien = new JLabel("Praticien :");
		lblRapportPraticien.setBounds(27, 126, 68, 14);
		panelRapport.add(lblRapportPraticien);
		
		txtRapportPraticien = new JTextField();
		txtRapportPraticien.setBounds(105, 123, 113, 20);
		panelRapport.add(txtRapportPraticien);
		txtRapportPraticien.setColumns(10);
		
		JLabel lblRapportBilan = new JLabel("Bilan : ");
		lblRapportBilan.setBounds(27, 151, 46, 14);
		panelRapport.add(lblRapportBilan);
		
		JTextArea textRapportBilan = new JTextArea();
		textRapportBilan.setBounds(105, 154, 292, 77);
		panelRapport.add(textRapportBilan);
		
		JLabel lblRapportMotif = new JLabel("Motif : ");
		lblRapportMotif.setBounds(27, 242, 46, 14);
		panelRapport.add(lblRapportMotif);
		
		JTextArea txtRapportMotif = new JTextArea();
		txtRapportMotif.setBounds(105, 242, 292, 77);
		panelRapport.add(txtRapportMotif);
		
		panelMedicaments.setBounds(214, 11, 710, 489);
		contentPane.add(panelMedicaments);
		panelMedicaments.setLayout(null);
		
		JLabel lblTitleMedicaments = new JLabel("Médicaments");
		lblTitleMedicaments.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitleMedicaments.setBounds(306, 5, 98, 19);
		panelMedicaments.add(lblTitleMedicaments);
		
		JButton button = new JButton("<");
		button.setBounds(10, 455, 41, 23);
		panelMedicaments.add(button);
		
		JButton button_1 = new JButton(">");
		button_1.setBounds(96, 455, 41, 23);
		panelMedicaments.add(button_1);
		
		JLabel label = new JLabel("0/0");
		label.setBounds(61, 459, 25, 14);
		panelMedicaments.add(label);
		
		JLabel lblNewLabel = new JLabel("PRIX ECHANTILLON : ");
		lblNewLabel.setBounds(10, 429, 127, 14);
		panelMedicaments.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(136, 426, 53, 20);
		panelMedicaments.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 145, 690, 67);
		panelMedicaments.add(scrollPane);
		
		JLabel lblNewLabel_1 = new JLabel("DEPOT LEGAL :");
		lblNewLabel_1.setBounds(10, 45, 98, 14);
		panelMedicaments.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("NOM COMMERCIAL :");
		lblNewLabel_2.setBounds(10, 70, 127, 14);
		panelMedicaments.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("FAMILLE :");
		lblNewLabel_3.setBounds(10, 95, 59, 14);
		panelMedicaments.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("COMPOSITION :");
		lblNewLabel_4.setBounds(10, 120, 98, 14);
		panelMedicaments.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("EFFETS :");
		lblNewLabel_5.setBounds(10, 223, 76, 14);
		panelMedicaments.add(lblNewLabel_5);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 248, 690, 67);
		panelMedicaments.add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 351, 690, 67);
		panelMedicaments.add(scrollPane_2);
		
		JLabel lblNewLabel_6 = new JLabel("CONTRE INDIC. :");
		lblNewLabel_6.setBounds(10, 326, 127, 14);
		panelMedicaments.add(lblNewLabel_6);
		
		textField_1 = new JTextField();
		textField_1.setBounds(107, 42, 144, 20);
		panelMedicaments.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(137, 67, 150, 20);
		panelMedicaments.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(79, 92, 41, 20);
		panelMedicaments.add(textField_3);
		textField_3.setColumns(10);
		panelMedicaments.setVisible(false);
		
		panelPraticiens.setBounds(214, 11, 710, 489);
		contentPane.add(panelPraticiens);
		panelPraticiens.setLayout(null);
		
		JLabel lblTitlePraticiens = new JLabel("Praticiens");
		lblTitlePraticiens.setBounds(317, 5, 76, 19);
		lblTitlePraticiens.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelPraticiens.add(lblTitlePraticiens);
		
		JLabel lblPraticienNumero = new JLabel("Num\u00E9ro : ");
		lblPraticienNumero.setBounds(34, 114, 127, 14);
		panelPraticiens.add(lblPraticienNumero);
		
		txtPraticienNumero = new JTextField();
		txtPraticienNumero.setBounds(182, 111, 200, 20);
		panelPraticiens.add(txtPraticienNumero);
		txtPraticienNumero.setColumns(10);
		
		JLabel lblPraticienNom = new JLabel("Nom : ");
		lblPraticienNom.setBounds(34, 145, 127, 14);
		panelPraticiens.add(lblPraticienNom);
		
		txtPraticienNom = new JTextField();
		txtPraticienNom.setBounds(182, 142, 200, 20);
		panelPraticiens.add(txtPraticienNom);
		txtPraticienNom.setColumns(10);
		
		JLabel lblPraticienPrenom = new JLabel("Pr\u00E9nom : ");
		lblPraticienPrenom.setBounds(34, 176, 127, 14);
		panelPraticiens.add(lblPraticienPrenom);
		
		txtPraticienPrenom = new JTextField();
		txtPraticienPrenom.setBounds(182, 173, 200, 20);
		panelPraticiens.add(txtPraticienPrenom);
		txtPraticienPrenom.setColumns(10);
		
		JLabel lblPraticienAdresse = new JLabel("Adresse :");
		lblPraticienAdresse.setBounds(34, 207, 127, 14);
		panelPraticiens.add(lblPraticienAdresse);
		
		txtPraticienAdresse = new JTextField();
		txtPraticienAdresse.setBounds(182, 204, 200, 20);
		panelPraticiens.add(txtPraticienAdresse);
		txtPraticienAdresse.setColumns(10);
		
		JLabel lblPraticienVille = new JLabel("Ville :");
		lblPraticienVille.setBounds(34, 238, 127, 14);
		panelPraticiens.add(lblPraticienVille);
		
		txtPraticienVille = new JTextField();
		txtPraticienVille.setBounds(182, 235, 200, 20);
		panelPraticiens.add(txtPraticienVille);
		txtPraticienVille.setColumns(10);
		
		txtPraticienCP = new JTextField();
		txtPraticienCP.setBounds(392, 235, 200, 20);
		panelPraticiens.add(txtPraticienCP);
		txtPraticienCP.setColumns(10);
		
		JLabel lblPraticienCoef = new JLabel("Coef. Notori\u00E9t\u00E9");
		lblPraticienCoef.setBounds(34, 269, 127, 14);
		panelPraticiens.add(lblPraticienCoef);
		
		txtPraticienCoef = new JTextField();
		txtPraticienCoef.setBounds(182, 266, 200, 20);
		panelPraticiens.add(txtPraticienCoef);
		txtPraticienCoef.setColumns(10);
		
		JLabel lblPraticienTypeCode = new JLabel("Type code");
		lblPraticienTypeCode.setBounds(34, 300, 127, 14);
		panelPraticiens.add(lblPraticienTypeCode);
		
		txtPraticienTypeCode = new JTextField();
		txtPraticienTypeCode.setBounds(182, 297, 200, 20);
		panelPraticiens.add(txtPraticienTypeCode);
		txtPraticienTypeCode.setColumns(10);
		
		JButton btnPraticienPrecedent = new JButton("<");
		btnPraticienPrecedent.setBounds(21, 363, 51, 23);
		panelPraticiens.add(btnPraticienPrecedent);
		
		JLabel lblPraticienTtl = new JLabel("00/00");
		lblPraticienTtl.setBounds(82, 367, 46, 14);
		panelPraticiens.add(lblPraticienTtl);
		
		JButton btnPraticienSuivant = new JButton(">");
		btnPraticienSuivant.setBounds(138, 363, 51, 23);
		panelPraticiens.add(btnPraticienSuivant);
		panelPraticiens.setVisible(false);
		
		panelAutresVisiteurs.setBounds(214, 11, 710, 489);
		contentPane.add(panelAutresVisiteurs);
		
		JLabel lblTitleAutresVisiteurs = new JLabel("Autres Visiteurs");
		lblTitleAutresVisiteurs.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitleAutresVisiteurs.setBounds(10, 11, 690, 24);
		panelAutresVisiteurs.add(lblTitleAutresVisiteurs);
		
		panelNewRapport.setBounds(10, 11, 914, 489);
		contentPane.add(panelNewRapport);
		panelNewRapport.setLayout(null);
		
		JLabel lblNewRapportBilan = new JLabel("Bilan : ");
		lblNewRapportBilan.setBounds(344, 72, 46, 14);
		panelNewRapport.add(lblNewRapportBilan);
		
		JLabel lblNewRapportMotif = new JLabel("Motif : ");
		lblNewRapportMotif.setBounds(344, 231, 46, 14);
		panelNewRapport.add(lblNewRapportMotif);
		
		final JTextPane textNewRapportBilan = new JTextPane();
		textNewRapportBilan.setBounds(344, 97, 485, 123);
		panelNewRapport.add(textNewRapportBilan);
		
		final JTextPane textNewRapportMotif = new JTextPane();
		textNewRapportMotif.setBounds(344, 256, 485, 123);
		panelNewRapport.add(textNewRapportMotif);
		
		JButton btnNewRapportEffacer = new JButton("EFFACER");
		btnNewRapportEffacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textNewRapportBilan.setText("");
				textNewRapportMotif.setText("");
			}
		});
		btnNewRapportEffacer.setBounds(344, 413, 89, 23);
		panelNewRapport.add(btnNewRapportEffacer);
		
		JButton btnNewRapportValider = new JButton("VALIDER");
		btnNewRapportValider.setBounds(443, 413, 89, 23);
		panelNewRapport.add(btnNewRapportValider);
		
		JLabel lblNouveauRapportDe = new JLabel("Nouveau rapport de visite");
		lblNouveauRapportDe.setBounds(502, 27, 136, 14);
		panelNewRapport.add(lblNouveauRapportDe);
		/* A vérifié
		panelAccueil.setVisible(false);
		panelAutresVisiteurs.setVisible(false);
		// panelLog.setVisible(false);
		*/
	}
	
	public void recupDonneesClient(){
		// Récupération et enregistrement des données du client connecté
		ClientType = DonneesClient.ClientType;
	    // Instanciation des données Communes
		Nom = DonneesClient.Nom;
		Prenom = DonneesClient.Prenom;
		Adresse = DonneesClient.Adresse;
		CP = DonneesClient.CP;
		Ville = DonneesClient.Ville;
		// Instanciation des données Visiteur
		Matricule = DonneesClient.Matricule;
		Login = DonneesClient.Login;
		DateEmbauche = DonneesClient.DateEmbauche;
		CodeSEC = DonneesClient.CodeSEC;
		CodeLAB = DonneesClient.CodeLAB;
	}
}
