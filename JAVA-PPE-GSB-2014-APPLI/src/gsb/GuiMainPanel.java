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

import com.sun.org.apache.xml.internal.security.Init;

public class GuiMainPanel extends JFrame {

	private static String version = "v1.22.12"; 
	private JPanel contentPane;
	private JTextField txtIdentifiant;
	public static String Identifiant;
	public String MotDePasse;
	// Type d'utilisateur
	public static String TypeUser;
	// Instanciation des variable de l'actualisation de l'�tat du serveur
	final static JButton btnRafraichir = new JButton("Rafraichir");
	public static String EtatAff = null;
	static boolean EtatConnexion = InfosConnexionBDD.EtatConnexion;
	// Choix du serveur
	final JComboBox serveurListe = new JComboBox();
	private Object serveurchoix;
	public static String serveur = null;
	// Instanciation de toutes autres int�ractions
	private JTextField txtPrixEchantillon;
	private JTextField txtDepotLegal;
	private JTextField txtNomCommercial;
	private JTextField txtFamCode;
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
	// Instanciation du type client connect�
	public String ClientType = null;
    // Instanciation des donn�es Communes
	public String Nom = null;
	public String Prenom = null;
	public String Adresse = null;
	public String CP = null;
	public String Ville = null;
	// Instanciation des donn�es Visiteur
	public String Matricule = null;
	public String Login = null;
	public String DateEmbauche = null;
	public String CodeSEC = null;
	public String CodeLAB = null;
	// M�dicaments - Donn�es de positionnement dans la liste des m�dicament :
 	public int MedMoveList = 1;
 	public int MedListeMax = 0;
 	public String nbresPages = null;

	// D�mmarre l'application
	public static void main(String[] args) {
		// D�marrage de l'interface primaire de l'application
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// V�rification de l'�tat du serveur BDD
					// InfosConnexionBDD connexionTest = new InfosConnexionBDD();
					// EtatConnexion = InfosConnexionBDD.EtatConnexion;
					// EtatAff = InfosConnexionBDD.EtatAff;
					EtatAff = "OFF";
					
					// Fait appel � la JFrame principale
					GuiMainPanel frame = new GuiMainPanel();
					// Permet de centr� la JFrame principale
					frame.setLocationRelativeTo(null);
					// Permet d'affich� la JFrame principale
					frame.setVisible(true);
					
					final JLabel lblEtat = new JLabel(EtatAff);
					// V�rification de EtatAff
					if(EtatAff == "ON"){
						lblEtat.setText(EtatAff);
						btnRafraichir.setVisible(false);
					}
					if(EtatAff == "OFF"){
						lblEtat.setText(EtatAff);
						btnRafraichir.setVisible(true);
					}
					
					// Beta
					JOptionPane.showMessageDialog(null,"Merci de s�lectionn� un serveur actif.\n>>>>>>>>>>>> En cours <<<<<<<<<<<\n\nDSL. Ne fonctionne pour le moment \nseulement un serveur install� \nen local.", "Version Beta - Serveur Multiples", JOptionPane.WARNING_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Cr�ations des Panel
	final JPanel panelLog = new JPanel();
	final JPanel panelAccueil = new JPanel();
	final JPanel panelMenu = new JPanel();
	final JPanel panelRapport = new JPanel();
	final JPanel panelMedicaments = new JPanel();
	final JPanel panelPraticiens = new JPanel();
	final JPanel panelAutresVisiteurs = new JPanel();
	final JPanel panelNewRapport = new JPanel();
	private JTextField txtAutresVisiteursNom;
	private JTextField txtAutresVisiteursPrenom;
	private JTextField txtAutresVisiteursAdresse;
	private JTextField txtAutresVisiteursCP;
	private JTextField txtAutresVisiteursVille;
	private JTextField txtAutresVisiteursSecteur;
	
	// Classe Principale
	public GuiMainPanel() {
		setTitle("GSB - Compte Rendu - "+version);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/* Seul la page Log doit �tre visible en 1er */
		panelLog();
		
		/* ICI : 
		 * 		D�commenter une classe permet d'appeler directement une classe Panel directement dans la Frame de GuiMainPanel.
		 * 		Cela permet une �dition simple avec Window Builder. 
		 * 		Il faut alors mettre en commentaire le panelLog avant l'�dition en window builder.
		 */
		// panelLog();
		// panelMenu();
		// panelAccueil();
		// panelRapport();
		// panelMedicaments();
		// panelPraticiens();
		// panelAutresVisiteurs();
		// panelNewRapport();
		
	}
	
	// Classes Panel
	public void panelLog(){
		final JLabel lblEtat = new JLabel(EtatAff);
		
		panelLog.setVisible(true);
		panelLog.setBounds(10, 11, 914, 569);
		contentPane.add(panelLog);
		panelLog.setLayout(null);
		
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = CHOIX SERVEUR = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 
		
		serveurListe.setModel(new DefaultComboBoxModel (new String[] {"Veulliez choisir un serveur", "Serveur Local", "Serveur Local Mac", "Serveur Hitema [DUMAY Loic]", "Serveur Hitema [COUTEILLON Damien]", "Serveur Personnel [DUMAY Loic]", "Serveur Personnel [COUTEILLON Damien]"}));
		serveurListe.setBounds(10, 533, 318, 25);
		panelLog.add(serveurListe);
		
		// = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = CONNEXION = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 
		JLabel lblConnexion = new JLabel("Connexion");
		lblConnexion.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblConnexion.setBounds(750, 432, 106, 23);
		panelLog.add(lblConnexion);
		
		JLabel lblIdentifiant = new JLabel("Identifiant :");
		lblIdentifiant.setBounds(716, 466, 76, 14);
		panelLog.add(lblIdentifiant);
		
		JLabel lblMotDePasse = new JLabel("  Mot de passe :");
		lblMotDePasse.setBounds(699, 502, 106, 14);
		panelLog.add(lblMotDePasse);
		
		txtIdentifiant = new JTextField();
		txtIdentifiant.setBounds(798, 461, 106, 25);
		panelLog.add(txtIdentifiant);
		txtIdentifiant.setColumns(10);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// R�cup�ration des Inputs de Log
				Identifiant = txtIdentifiant.getText();
				MotDePasse = txtMotDePasse.getText();
				
				// V�rification de l'�tat du serveur BDD
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
					JOptionPane.showMessageDialog(null,"L'identifiant et le mot de passe n'ont pas �t� saisi !", ErrorLog, JOptionPane.WARNING_MESSAGE);
				}
				else if(Identifiant.isEmpty()){
					JOptionPane.showMessageDialog(null,"L'identifiant n'a pas �t� saisi !", ErrorLog, JOptionPane.WARNING_MESSAGE);
				}
				else if(MotDePasse.isEmpty()){
					JOptionPane.showMessageDialog(null,"Le mot de passe n'a pas �t� saisi !", ErrorLog, JOptionPane.WARNING_MESSAGE);
				}
				else{
					String pilote = "com.mysql.jdbc.Driver";
					try {
						Class.forName(pilote);
						
						// M�thode de r�cup�ration des information de connexion � la BDD
						String[] infosConnexionBDD = InfosConnexionBDD.InfosConnexionBDD();
						String BDD = infosConnexionBDD[0];
				        String url = infosConnexionBDD[1];
				        String user = infosConnexionBDD[2];
				        String passwd = infosConnexionBDD[3];
				        Connection con = DriverManager.getConnection(url, user, passwd);
				        
						Statement stmt = con.createStatement();
				
						ResultSet resultat = null;
						
						String idUser = null;
						
						// V�rification du type d'utilisateur
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
							
							// V�rification du MDP
							// JOptionPane.showMessageDialog(null,"DONNEES : "+date_emb+" - "+mdp);
							
							if( mdp.equals(date_emb)){
								// R�cup�ration des informations du client connect�
								DonneesClient Client = new DonneesClient();
								
								// V�rification des donn�es de l'utilisateur connect�
								// JOptionPane.showMessageDialog(null,"\nMatricule / ID : " + Matricule + "\nClientType : " + ClientType + "\nNom : " + Nom + "\n Prenom : " + Prenom + "\nAdresse : " + Adresse + "\nCP : " + CP  + "\nVille : " + Ville + "\nLogin : " + Login + "\nDateEmbauche : " + DateEmbauche + "\nCodeSEC : " + CodeSEC + "\nCodeLAB : " + CodeLAB + "\n", DEBUGG_MODE + " Donn�es Clients", JOptionPane.INFORMATION_MESSAGE);
								
								recupDonneesClient();

								// V�rification des donn�es de l'utilisateur connect�
								JOptionPane.showMessageDialog(null,"\nMatricule / ID : " + Matricule + "\nClientType : " + ClientType + "\nNom : " + Nom + "\n Prenom : " + Prenom + "\nAdresse : " + Adresse + "\nCP : " + CP  + "\nVille : " + Ville +  "\nLogin : " + Login + "\nDateEmbauche : " + DateEmbauche + "\nCodeSEC : " + CodeSEC + "\nCodeLAB : " + CodeLAB + "\n", DEBUGG_MODE + " Donn�es Clients", JOptionPane.INFORMATION_MESSAGE);
								
								// Passage en client connecter
								panelLog.setVisible(false);
								panelMenu();
								panelAccueil();
							}
							else{
								JOptionPane.showMessageDialog(null,"Oups, le mot de passe n'est pas correcte ! \n\n Assurez-vous d'entrer les 3 premi�res lettres du mois \n dans le mot de passe, tel que : XX-XXX-XX", ErrorLog, JOptionPane.WARNING_MESSAGE);
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
		txtMotDePasse.setBounds(798, 497, 106, 25);
		panelLog.add(txtMotDePasse);
		btnValider.setBounds(716, 533, 89, 25);
		panelLog.add(btnValider);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Modifications des Input de Log
				txtIdentifiant.setText("");
				txtMotDePasse.setText("");
			}
		});
		btnAnnuler.setBounds(815, 533, 89, 25);
		panelLog.add(btnAnnuler);
		
		JLabel lblEtatDuServeur = new JLabel("Etat du serveur : ");
		lblEtatDuServeur.setBounds(338, 533, 117, 25);
		panelLog.add(lblEtatDuServeur);
		
		lblEtat.setBounds(448, 533, 44, 25);
		panelLog.add(lblEtat);
		
		JButton btnAbout = new JButton("A propos");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Infos infos = new Infos();
				// Permet de centr� la JFrame A propos
				infos.setLocationRelativeTo(null);
				infos.setVisible(true);
			}
		});
		btnAbout.setBounds(10, 497, 89, 25);
		panelLog.add(btnAbout);
		
		btnRafraichir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// V�rification de la s�lection
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
					// V�rification de l'�tat du serveur BDD
					InfosConnexionBDD connexionTest2 = new InfosConnexionBDD();
					EtatConnexion = InfosConnexionBDD.EtatConnexion;
					EtatAff = InfosConnexionBDD.EtatAff;
					// V�rification de EtatAff
					if(EtatAff == "ON"){
						lblEtat.setText(EtatAff);
						// btnRafraichir.setVisible(false);
					}
					if(EtatAff == "OFF"){
						lblEtat.setText(EtatAff);
						// btnRafraichir.setVisible(true);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Oups ! " + serveurchoix + ", SVP !", ErrorLog, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnRafraichir.setBounds(502, 533, 95, 25);
		panelLog.add(btnRafraichir);
	}
	
	public void panelMenu(){
		panelLog.setVisible(false);
		panelMenu.setVisible(true);
		
		panelMenu.setBounds(10, 11, 198, 569);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JLabel lblTitleMenu = new JLabel("Menu");
		lblTitleMenu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitleMenu.setBounds(10, 11, 178, 24);
		panelMenu.add(lblTitleMenu);
		
		JButton btnRapport = new JButton("Rapports de visite");
		btnRapport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRapport();
			}
		});
		
		JButton btnAccueil = new JButton("Accueil");
		btnAccueil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAccueil();
			}
		});
		btnAccueil.setBounds(10, 46, 178, 37);
		panelMenu.add(btnAccueil);
		
		JLabel lblComptesrendus = new JLabel("Comptes-rendus");
		lblComptesrendus.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblComptesrendus.setBounds(10, 94, 178, 14);
		panelMenu.add(lblComptesrendus);
		btnRapport.setBounds(10, 119, 178, 37);
		panelMenu.add(btnRapport);
		
		JButton btnMedicaments = new JButton("M\u00E9dicaments");
		btnMedicaments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMedicaments();
			}
		});
		
		JButton btnAjoutRapport = new JButton("Nouveau Rapport");
		btnAjoutRapport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelNewRapport();
			}
		});
		btnAjoutRapport.setBounds(10, 167, 178, 37);
		panelMenu.add(btnAjoutRapport);
		
		JLabel lblConsulter = new JLabel("Consulter");
		lblConsulter.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblConsulter.setBounds(10, 215, 178, 14);
		panelMenu.add(lblConsulter);
		btnMedicaments.setBounds(10, 240, 178, 37);
		panelMenu.add(btnMedicaments);
		
		JButton btnPraticiens = new JButton("Praticiens");
		btnPraticiens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPraticiens();
			}
		});
		btnPraticiens.setBounds(10, 288, 178, 37);
		panelMenu.add(btnPraticiens);
		
		JButton btnAutresVisiteurs = new JButton("Autres Visiteurs");
		btnAutresVisiteurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAutresVisiteurs();
			}
		});
		btnAutresVisiteurs.setBounds(10, 336, 178, 37);
		panelMenu.add(btnAutresVisiteurs);
		
		JButton btnAjoutMedic = new JButton("Nouveau M\u00E9dicament");
		btnAjoutMedic.setBounds(10, 412, 178, 37);
		panelMenu.add(btnAjoutMedic);
		
		JButton btnAjoutPrat = new JButton("Nouveau Praticien");
		btnAjoutPrat.setBounds(10, 460, 178, 37);
		panelMenu.add(btnAjoutPrat);
		
		JButton btnLogOut = new JButton("D\u00E9connexion");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Clean Frame
				cleanFrame();
				// Modifications des Input de Log
				txtIdentifiant.setText("");
				txtMotDePasse.setText("");
				panelLog();
			}
		});
		btnLogOut.setBounds(10, 521, 178, 37);
		panelMenu.add(btnLogOut);
	}
	
	public void panelAccueil(){
		panelAccueil.setVisible(true);
		panelRapport.setVisible(false);
		panelMedicaments.setVisible(false);
		panelPraticiens.setVisible(false);
		panelAutresVisiteurs.setVisible(false);
		panelNewRapport.setVisible(false);
		
		panelAccueil.setBounds(214, 11, 710, 569);
		contentPane.add(panelAccueil);
		panelAccueil.setLayout(null);
		
		JLabel lblTitleHome = new JLabel("Bienvenue dans l'application de Compte rendu de GSB");
		lblTitleHome.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitleHome.setBounds(10, 11, 690, 24);
		panelAccueil.add(lblTitleHome);
		
		JLabel lblClientTitle = new JLabel("Bonjour, " + Nom + " " + Prenom + ".");
		lblClientTitle.setBounds(10, 70, 138, 14);
		panelAccueil.add(lblClientTitle);
		JLabel lblClientStatut = new JLabel("Vous \u00EAtes : " + ClientType + ".");
		lblClientStatut.setBounds(10, 95, 138, 14);
		panelAccueil.add(lblClientStatut);
	}
	
	public void panelRapport(){
		panelAccueil.setVisible(false);
		panelMedicaments.setVisible(false);
		panelPraticiens.setVisible(false);
		panelAutresVisiteurs.setVisible(false);
		panelNewRapport.setVisible(false);
		
		panelRapport.setVisible(true);
		
		panelRapport.setBounds(214, 11, 710, 569);
		contentPane.add(panelRapport);
		panelRapport.setLayout(null);
		
		JLabel lblTitleRapport = new JLabel("Rapports");
		lblTitleRapport.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitleRapport.setBounds(321, 5, 68, 19);
		panelRapport.add(lblTitleRapport);
		
		JComboBox listRapport = new JComboBox();
		listRapport.setModel(new DefaultComboBoxModel(new String[] {"Choisir la date du rendez-vous", "Rapport n�x du 00/00/0000"}));
		listRapport.setBounds(27, 67, 481, 29);
		panelRapport.add(listRapport);
		
		JButton btnRapportValider = new JButton("Valider");
		btnRapportValider.setBounds(532, 66, 106, 30);
		panelRapport.add(btnRapportValider);
		
		JLabel lblRapportNumber = new JLabel("Num\u00E9ro : ");
		lblRapportNumber.setBounds(27, 128, 68, 14);
		panelRapport.add(lblRapportNumber);
		
		txtRapport = new JTextField();
		txtRapport.setBounds(105, 125, 175, 20);
		panelRapport.add(txtRapport);
		txtRapport.setColumns(10);
		
		JLabel lblRapportPraticien = new JLabel("Praticien :");
		lblRapportPraticien.setBounds(27, 160, 68, 14);
		panelRapport.add(lblRapportPraticien);
		
		txtRapportPraticien = new JTextField();
		txtRapportPraticien.setBounds(105, 157, 175, 20);
		panelRapport.add(txtRapportPraticien);
		txtRapportPraticien.setColumns(10);
		
		JLabel lblRapportBilan = new JLabel("Bilan : ");
		lblRapportBilan.setBounds(27, 199, 46, 14);
		panelRapport.add(lblRapportBilan);
		
		JTextArea textRapportBilan = new JTextArea();
		textRapportBilan.setBounds(105, 194, 533, 133);
		panelRapport.add(textRapportBilan);
		
		JLabel lblRapportMotif = new JLabel("Motif : ");
		lblRapportMotif.setBounds(27, 356, 46, 14);
		panelRapport.add(lblRapportMotif);
		
		JTextArea txtRapportMotif = new JTextArea();
		txtRapportMotif.setBounds(105, 351, 533, 133);
		panelRapport.add(txtRapportMotif);
	}
	
	public void panelMedicaments(){
		panelAccueil.setVisible(false);
		panelRapport.setVisible(false);
		panelPraticiens.setVisible(false);
		panelAutresVisiteurs.setVisible(false);
		panelNewRapport.setVisible(false);
		
		panelMedicaments.removeAll();
		panelMedicaments.setVisible(true);
		
		// Donn�es tmp
        String tmpDepotLegal = null;
        String tmpNomCommercial = null;
        String tmpFamCode = null;
        String tmpComposition = null;
        String tmpEffets = null;
        String tmpContreIndic = null;
        float tmpPrixEchan = 0;
        
        // M�thode de r�cup�ration des information de connexion � la BDD
		String[] infosConnexionBDD = InfosConnexionBDD.InfosConnexionBDD();
		String BDD = infosConnexionBDD[0];
        String url = infosConnexionBDD[1];
        String user = infosConnexionBDD[2];
        String passwd = infosConnexionBDD[3];
		
     	// Donn�es de navigation da sl a liste des m�dicaments
     	String tmpIDDepotLegal = null;
     	
     	try {
     		JTextField lblPages = new JTextField();
     		
            Class.forName("com.mysql.jdbc.Driver");
            // Connexion � la BDD
            Connection con = DriverManager.getConnection(url, user, passwd);
            Statement stmt = con.createStatement();
            
            // Cr�ation d'un maximum de lecture pour la liste des m�dicaments
            ResultSet resultat = null;
            resultat = stmt.executeQuery("SELECT count(MED_DEPOTLEGAL) AS result FROM medicament");
            while (resultat.next()) {
            	MedListeMax = resultat.getInt("result");
            }
            
            if(MedMoveList<1){ MedMoveList++; }
            if(MedMoveList>MedListeMax){ MedMoveList--; }
            
            String i = Integer.toString(MedMoveList);
            int x = 0;
            String InsertDepotLegal = null;
            String h = null;
            String f = null;
            
            resultat = null;
            resultat = stmt.executeQuery("SELECT MED_DEPOTLEGAL FROM medicament ORDER BY MED_DEPOTLEGAL");
            while (resultat.next()) {
            	x++;
            	// - - 
            	InsertDepotLegal = "" + x + '-' + resultat.getString("MED_DEPOTLEGAL");
            	// D�coupage de l'insertion
            	String tmpCut[] = InsertDepotLegal.split("-");
            	// R�cup�ration de la 1er occurence
            	h = tmpCut[0];
            	f = tmpCut[1];
            	// V�rification du d�placement dans la liste des m�dicaments vers le m�dicaments souhait�
            	if(x == MedMoveList){
            		// r�cup�ration de l'ID du m�dicaments chercher
            		tmpIDDepotLegal = f;
            	}
            }
            nbresPages = "" + MedMoveList + "/" + MedListeMax + "";
			
            // TEST
            System.out.println("# = = = = #");
            System.out.println("f : " + f);
            System.out.println("i : " + i + " <==> h : " + h + "");
            System.out.println("MedMoveList : " + MedMoveList);
            System.out.println("nbresPages : " + nbresPages);
            System.out.println("tmpIDDepotLegal : " + tmpIDDepotLegal);
            System.out.println("MedListeMax : " + MedListeMax + " <==> x : " + x );
            
            // V�rification des donn�es de l'utilisateur connect�
			// JOptionPane.showMessageDialog(null, MedListeMax + "\n" + tmpIDDepotLegal + "\n" + MedMoveList + "\n" + i + "\n" + x + "\n" + InsertDepotLegal + "\n" + h, DEBUGG_MODE + " Test Donn�es ", JOptionPane.INFORMATION_MESSAGE);
			
            // R�cup�ration des informations du m�dicaments trouv� pour affichage
            resultat = null;
            resultat = stmt.executeQuery("SELECT * FROM medicament WHERE MED_DEPOTLEGAL = '" + tmpIDDepotLegal + "'");
			if (resultat.next()) {
				tmpDepotLegal = resultat.getString("MED_DEPOTLEGAL");
				tmpNomCommercial = resultat.getString("MED_NOMCOMMERCIAL");
				tmpFamCode = resultat.getString("FAM_CODE");
				tmpComposition = resultat.getString("MED_COMPOSITION");
				tmpEffets = resultat.getString("MED_EFFETS");
				tmpContreIndic = resultat.getString("MED_CONTREINDIC");
				tmpPrixEchan = resultat.getFloat("MED_PRIXECHANTILLON");
			}
			
			// V�rification des donn�es de l'utilisateur connect�
	     	// JOptionPane.showMessageDialog(null, "D�po L�gal : " + tmpDepotLegal + "\nNom commercial : " + tmpNomCommercial + "\nFamille Code : " + tmpFamCode + "\nComposition : " + tmpComposition + "\nEffets : " + tmpEffets + "\nContre Indic : " + tmpContreIndic + "\nPrix �chantillon : " + tmpPrixEchan, DEBUGG_MODE + " Donn�es M�dicaments", JOptionPane.INFORMATION_MESSAGE);
	     	
	     	panelMedicaments.setBounds(214, 11, 710, 569);
			contentPane.add(panelMedicaments);
			panelMedicaments.setLayout(null);
			
			JLabel lblTitleMedicaments = new JLabel("M�dicaments");
			lblTitleMedicaments.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblTitleMedicaments.setBounds(306, 5, 127, 19);
			panelMedicaments.add(lblTitleMedicaments);
			
			JLabel lblDepotLegal = new JLabel("DEPOT LEGAL :");
			lblDepotLegal.setBounds(10, 52, 159, 14);
			panelMedicaments.add(lblDepotLegal);
			
			txtDepotLegal = new JTextField("" + tmpDepotLegal + "");
			txtDepotLegal.setBounds(179, 49, 178, 20);
			panelMedicaments.add(txtDepotLegal);
			txtDepotLegal.setColumns(10);
			
			JLabel lblNomCommercial = new JLabel("NOM COMMERCIAL :");
			lblNomCommercial.setBounds(10, 87, 159, 14);
			panelMedicaments.add(lblNomCommercial);
			
			txtNomCommercial = new JTextField("" + tmpNomCommercial + "");
			txtNomCommercial.setBounds(179, 84, 178, 20);
			panelMedicaments.add(txtNomCommercial);
			txtNomCommercial.setColumns(10);
			
			JLabel lblFamille = new JLabel("FAMILLE :");
			lblFamille.setBounds(10, 118, 159, 14);
			panelMedicaments.add(lblFamille);
			
			txtFamCode = new JTextField("" + tmpFamCode + "");
			txtFamCode.setBounds(179, 115, 178, 20);
			panelMedicaments.add(txtFamCode);
			txtFamCode.setColumns(10);
			
			JLabel lblComposition = new JLabel("COMPOSITION :");
			lblComposition.setBounds(10, 143, 159, 14);
			panelMedicaments.add(lblComposition);
			
			JTextPane scrollPaneComposition = new JTextPane();
			scrollPaneComposition.setText("" + tmpComposition + "");
			scrollPaneComposition.setBounds(10, 168, 690, 83);
			panelMedicaments.add(scrollPaneComposition);
			
			JLabel lblEffets = new JLabel("EFFETS :");
			lblEffets.setBounds(10, 262, 76, 14);
			panelMedicaments.add(lblEffets);
			
			JTextPane scrollPaneEffets = new JTextPane();
			scrollPaneEffets.setText("" + tmpEffets + "");
			scrollPaneEffets.setBounds(10, 287, 690, 83);
			panelMedicaments.add(scrollPaneEffets);
			
			JLabel lblContreIndic = new JLabel("CONTRE INDIC. :");
			lblContreIndic.setBounds(10, 381, 127, 14);
			panelMedicaments.add(lblContreIndic);
			
			JTextPane scrollPaneContreIndic = new JTextPane();
			scrollPaneContreIndic.setText("" + tmpContreIndic + "");
			scrollPaneContreIndic.setBounds(10, 406, 690, 83);
			panelMedicaments.add(scrollPaneContreIndic);
			
			JLabel lblPrixEchantillion = new JLabel("PRIX ECHANTILLON : ");
			lblPrixEchantillion.setBounds(10, 504, 159, 14);
			panelMedicaments.add(lblPrixEchantillion);
			
			txtPrixEchantillon = new JTextField("" + tmpPrixEchan + "");
			txtPrixEchantillon.setBounds(179, 501, 58, 20);
			panelMedicaments.add(txtPrixEchantillon);
			txtPrixEchantillon.setColumns(10);
			
			JLabel lblSigle = new JLabel("�");
			lblSigle.setBounds(247, 504, 47, 14);
			panelMedicaments.add(lblSigle);
			
			lblPages.setText("" + nbresPages + "");
			lblPages.setBounds(61, 537, 42, 20);
			panelMedicaments.add(lblPages);
			
		} catch (Exception e){
            // e.printStackTrace();
			System.err.println("Oups ! Il y a une erreur SQL !");
        }
		
		JButton buttonPrecedent = new JButton("<");
		buttonPrecedent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MedMoveList--;
				panelMedicaments();
			}
		});
		buttonPrecedent.setBounds(10, 535, 41, 25);
		panelMedicaments.add(buttonPrecedent);
		
		JButton buttonSuivant = new JButton(">");
		buttonSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedMoveList++;
				panelMedicaments();
			}
		});
		buttonSuivant.setBounds(113, 535, 41, 25);
		panelMedicaments.add(buttonSuivant);
	}
	
	public void panelPraticiens(){
		panelAccueil.setVisible(false);
		panelRapport.setVisible(false);
		panelMedicaments.setVisible(false);
		panelAutresVisiteurs.setVisible(false);
		panelNewRapport.setVisible(false);
		
		panelPraticiens.setVisible(true);
		
		panelPraticiens.setBounds(214, 11, 710, 569);
		contentPane.add(panelPraticiens);
		panelPraticiens.setLayout(null);
		
		JLabel lblTitlePraticiens = new JLabel("Praticiens");
		lblTitlePraticiens.setBounds(317, 5, 97, 19);
		lblTitlePraticiens.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelPraticiens.add(lblTitlePraticiens);
		
		JLabel lblPraticienNumero = new JLabel("Num\u00E9ro : ");
		lblPraticienNumero.setBounds(28, 55, 127, 14);
		panelPraticiens.add(lblPraticienNumero);
		
		txtPraticienNumero = new JTextField();
		txtPraticienNumero.setBounds(176, 52, 200, 20);
		panelPraticiens.add(txtPraticienNumero);
		txtPraticienNumero.setColumns(10);
		
		JLabel lblPraticienNom = new JLabel("Nom : ");
		lblPraticienNom.setBounds(28, 86, 127, 14);
		panelPraticiens.add(lblPraticienNom);
		
		txtPraticienNom = new JTextField();
		txtPraticienNom.setBounds(176, 83, 200, 20);
		panelPraticiens.add(txtPraticienNom);
		txtPraticienNom.setColumns(10);
		
		JLabel lblPraticienPrenom = new JLabel("Pr\u00E9nom : ");
		lblPraticienPrenom.setBounds(28, 117, 127, 14);
		panelPraticiens.add(lblPraticienPrenom);
		
		txtPraticienPrenom = new JTextField();
		txtPraticienPrenom.setBounds(176, 114, 200, 20);
		panelPraticiens.add(txtPraticienPrenom);
		txtPraticienPrenom.setColumns(10);
		
		JLabel lblPraticienAdresse = new JLabel("Adresse :");
		lblPraticienAdresse.setBounds(28, 148, 127, 14);
		panelPraticiens.add(lblPraticienAdresse);
		
		txtPraticienAdresse = new JTextField();
		txtPraticienAdresse.setBounds(176, 145, 200, 20);
		panelPraticiens.add(txtPraticienAdresse);
		txtPraticienAdresse.setColumns(10);
		
		JLabel lblPraticienVille = new JLabel("Ville :");
		lblPraticienVille.setBounds(28, 179, 127, 14);
		panelPraticiens.add(lblPraticienVille);
		
		txtPraticienVille = new JTextField();
		txtPraticienVille.setBounds(176, 176, 200, 20);
		panelPraticiens.add(txtPraticienVille);
		txtPraticienVille.setColumns(10);
		
		txtPraticienCP = new JTextField();
		txtPraticienCP.setBounds(386, 176, 200, 20);
		panelPraticiens.add(txtPraticienCP);
		txtPraticienCP.setColumns(10);
		
		JLabel lblPraticienCoef = new JLabel("Coef. Notori\u00E9t\u00E9");
		lblPraticienCoef.setBounds(28, 210, 127, 14);
		panelPraticiens.add(lblPraticienCoef);
		
		txtPraticienCoef = new JTextField();
		txtPraticienCoef.setBounds(176, 207, 200, 20);
		panelPraticiens.add(txtPraticienCoef);
		txtPraticienCoef.setColumns(10);
		
		JLabel lblPraticienTypeCode = new JLabel("Type code");
		lblPraticienTypeCode.setBounds(28, 241, 127, 14);
		panelPraticiens.add(lblPraticienTypeCode);
		
		txtPraticienTypeCode = new JTextField();
		txtPraticienTypeCode.setBounds(176, 238, 200, 20);
		panelPraticiens.add(txtPraticienTypeCode);
		txtPraticienTypeCode.setColumns(10);
		
		JButton btnPraticienPrecedent = new JButton("<");
		btnPraticienPrecedent.setBounds(28, 305, 51, 25);
		panelPraticiens.add(btnPraticienPrecedent);
		
		JLabel lblPraticienTtl = new JLabel("00/00");
		lblPraticienTtl.setBounds(99, 310, 46, 14);
		panelPraticiens.add(lblPraticienTtl);
		
		JButton btnPraticienSuivant = new JButton(">");
		btnPraticienSuivant.setBounds(155, 302, 51, 30);
		panelPraticiens.add(btnPraticienSuivant);
	}
	
	public void panelAutresVisiteurs(){
		panelAccueil.setVisible(false);
		panelRapport.setVisible(false);
		panelMedicaments.setVisible(false);
		panelPraticiens.setVisible(false);
		panelNewRapport.setVisible(false);
		
		panelAutresVisiteurs.setVisible(true);
		
		panelAutresVisiteurs.setBounds(214, 11, 710, 569);
		contentPane.add(panelAutresVisiteurs);
		panelAutresVisiteurs.setLayout(null);
		
		final JLabel lblTitleAutresVisiteurs = new JLabel("Autres Visiteurs");
		lblTitleAutresVisiteurs.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitleAutresVisiteurs.setBounds(295, 5, 152, 19);
		panelAutresVisiteurs.add(lblTitleAutresVisiteurs);
		
		JComboBox AutresVisiteursDeptListe = new JComboBox();
		AutresVisiteursDeptListe.setModel(new DefaultComboBoxModel (new String[] {"Choisir un visiteur", "Exemple"}));
		AutresVisiteursDeptListe.setBounds(43, 73, 308, 25);
		panelAutresVisiteurs.add(AutresVisiteursDeptListe);
		
		JButton btnAutresVisiteursValider = new JButton("Valider");
		btnAutresVisiteursValider.setBounds(361, 72, 110, 26);
		panelAutresVisiteurs.add(btnAutresVisiteursValider);
		
		JLabel lblAutresVisiteursNom = new JLabel("Nom :");
		lblAutresVisiteursNom.setBounds(43, 131, 144, 14);
		panelAutresVisiteurs.add(lblAutresVisiteursNom);
		
		txtAutresVisiteursNom = new JTextField();
		txtAutresVisiteursNom.setBounds(197, 128, 274, 20);
		panelAutresVisiteurs.add(txtAutresVisiteursNom);
		txtAutresVisiteursNom.setColumns(10);
		
		JLabel lblAutresVisiteursPrenom = new JLabel("Pr\u00E9nom :");
		lblAutresVisiteursPrenom.setBounds(43, 159, 144, 14);
		panelAutresVisiteurs.add(lblAutresVisiteursPrenom);
		
		txtAutresVisiteursPrenom = new JTextField();
		txtAutresVisiteursPrenom.setColumns(10);
		txtAutresVisiteursPrenom.setBounds(197, 156, 274, 20);
		panelAutresVisiteurs.add(txtAutresVisiteursPrenom);
		
		JLabel lblAutresVisiteursAdresse = new JLabel("Adresse : ");
		lblAutresVisiteursAdresse.setBounds(43, 187, 144, 14);
		panelAutresVisiteurs.add(lblAutresVisiteursAdresse);
		
		txtAutresVisiteursAdresse = new JTextField();
		txtAutresVisiteursAdresse.setColumns(10);
		txtAutresVisiteursAdresse.setBounds(197, 184, 274, 20);
		panelAutresVisiteurs.add(txtAutresVisiteursAdresse);
		
		JLabel lblAutresVisiteursCP = new JLabel("CP : ");
		lblAutresVisiteursCP.setBounds(43, 215, 144, 14);
		panelAutresVisiteurs.add(lblAutresVisiteursCP);
		
		txtAutresVisiteursCP = new JTextField();
		txtAutresVisiteursCP.setColumns(10);
		txtAutresVisiteursCP.setBounds(197, 212, 274, 20);
		panelAutresVisiteurs.add(txtAutresVisiteursCP);
		
		JLabel lblAutresVisiteursVille = new JLabel("Ville : ");
		lblAutresVisiteursVille.setBounds(43, 243, 144, 14);
		panelAutresVisiteurs.add(lblAutresVisiteursVille);
		
		txtAutresVisiteursVille = new JTextField();
		txtAutresVisiteursVille.setColumns(10);
		txtAutresVisiteursVille.setBounds(197, 240, 274, 20);
		panelAutresVisiteurs.add(txtAutresVisiteursVille);
		
		JLabel lblAutresVisiteursSecteur = new JLabel("Secteur : ");
		lblAutresVisiteursSecteur.setBounds(43, 271, 144, 14);
		panelAutresVisiteurs.add(lblAutresVisiteursSecteur);
		
		txtAutresVisiteursSecteur = new JTextField();
		txtAutresVisiteursSecteur.setColumns(10);
		txtAutresVisiteursSecteur.setBounds(197, 268, 274, 20);
		panelAutresVisiteurs.add(txtAutresVisiteursSecteur);
		
		JButton btnAutresVisiteursUp = new JButton("<");
		btnAutresVisiteursUp.setBounds(43, 299, 41, 25);
		panelAutresVisiteurs.add(btnAutresVisiteursUp);
		
		JLabel lblAutresVisiteursPages = new JLabel("00 / 00");
		lblAutresVisiteursPages.setBounds(94, 304, 61, 14);
		panelAutresVisiteurs.add(lblAutresVisiteursPages);
		
		JButton btnAutresVisiteursDown = new JButton(">");
		btnAutresVisiteursDown.setBounds(169, 299, 41, 25);
		panelAutresVisiteurs.add(btnAutresVisiteursDown);
	}
	
	public void panelNewRapport(){
		panelAccueil.setVisible(false);
		panelRapport.setVisible(false);
		panelMedicaments.setVisible(false);
		panelPraticiens.setVisible(false);
		panelAutresVisiteurs.setVisible(false);
		panelNewRapport.setVisible(true);
		
		panelNewRapport.setBounds(10, 11, 914, 569);
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
		btnNewRapportEffacer.setBounds(344, 413, 89, 25);
		panelNewRapport.add(btnNewRapportEffacer);
		
		JButton btnNewRapportValider = new JButton("VALIDER");
		btnNewRapportValider.setBounds(443, 413, 89, 25);
		panelNewRapport.add(btnNewRapportValider);
		
		JLabel lblNouveauRapportDe = new JLabel("Nouveau rapport de visiteur");
		lblNouveauRapportDe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNouveauRapportDe.setBounds(502, 27, 256, 14);
		panelNewRapport.add(lblNouveauRapportDe);
	}
	
	// Others Classes
	public void recupDonneesClient(){
		// R�cup�ration et enregistrement des donn�es du client connect�
		ClientType = DonneesClient.ClientType;
	    // Instanciation des donn�es Communes
		Nom = DonneesClient.Nom;
		Prenom = DonneesClient.Prenom;
		Adresse = DonneesClient.Adresse;
		CP = DonneesClient.CP;
		Ville = DonneesClient.Ville;
		// Instanciation des donn�es Visiteur
		Matricule = DonneesClient.Matricule;
		Login = DonneesClient.Login;
		DateEmbauche = DonneesClient.DateEmbauche;
		CodeSEC = DonneesClient.CodeSEC;
		CodeLAB = DonneesClient.CodeLAB;
	}
	
 	public void cleanFrame(){
		panelMenu.setVisible(false);
		panelAccueil.setVisible(false);
		panelRapport.setVisible(false);
		panelMedicaments.setVisible(false);
		panelPraticiens.setVisible(false);
		panelAutresVisiteurs.setVisible(false);
		panelNewRapport.setVisible(false);
	}
}
