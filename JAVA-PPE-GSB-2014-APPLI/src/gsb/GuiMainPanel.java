package gsb;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class GuiMainPanel extends JFrame {
  private static String version = "v1.65.33 - 2020/10/26";
  
  private JPanel contentPane;
  
  private JTextField txtIdentifiant;
  
  public static String Identifiant;
  
  public String MotDePasse;
  
  public static String TypeUser;
  
  static final JButton btnRafraichir = new JButton("Rafraichir");
  
  public static String EtatAff = null;
  
  static boolean EtatConnexion = InfosConnexionBDD.EtatConnexion;
  
  final JComboBox serveurListe = new JComboBox();
  
  private Object serveurchoix;
  
  public static String serveur = null;
  
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
  
  public String ErrorLog = "Erreur de connexion";
  
  public String DEBUGG_MODE = "DEBUGG MODE : ";
  
  public String ClientType = null;
  
  public String Nom = null;
  
  public String Prenom = null;
  
  public String Adresse = null;
  
  public String CP = null;
  
  public String Ville = null;
  
  public String Matricule = null;
  
  public String Login = null;
  
  public String DateEmbauche = null;
  
  public String CodeSEC = null;
  
  public String CodeLAB = null;
  
  public String choixListe = null;
  
  private Object choixListeRap;
  
  public String ID_RAP = null;
  
  public int MedMoveList = 1;
  
  public int MedListeMax = 0;
  
  public String MednbresPages = null;
  
  public int PratMoveList = 1;
  
  public int PratListeMax = 0;
  
  public String PratnbresPages = null;
  
  public static String tmpVIS_MATRICULE = null;
  
  public static String tmpPRA_NUM = null;
  
  public static String tmpRAP_BILAN = null;
  
  public static String tmpRAP_MOTIF = null;
  
  public static String tmpMED_DEPOTLEGAL_1 = null;
  
  public static String tmpMED_DEPOTLEGAL_2 = null;
  
  public static String tmpQteMed_1 = null;
  
  public static String tmpQteMed_2 = null;
  
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
          public void run() {
            try {
              System.out.println("# - - - - - - - - - - - - - - - - - - - - - - - - - - - #");
              GuiMainPanel.EtatAff = "OFF";
              GuiMainPanel frame = new GuiMainPanel();
              frame.setLocationRelativeTo(null);
              frame.setVisible(true);
              JLabel lblEtat = new JLabel(GuiMainPanel.EtatAff);
              if (GuiMainPanel.EtatAff == "ON") {
                lblEtat.setText(GuiMainPanel.EtatAff);
                GuiMainPanel.btnRafraichir.setVisible(false);
              } 
              if (GuiMainPanel.EtatAff == "OFF") {
                lblEtat.setText(GuiMainPanel.EtatAff);
                GuiMainPanel.btnRafraichir.setVisible(true);
              } 
              JOptionPane.showMessageDialog(null, "Merci de sun serveur actif.\n>>>>>>>>>>>> En cours <<<<<<<<<<<\n\nDSeul le serveur Personnel Dumay Loic\n ou un serveur installen local sont fonctionnel.", "Version Beta - Serveur Multiples", 2);
            } catch (Exception e) {
              e.printStackTrace();
            } 
          }
        });
  }
  
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
  
  private JTextField QteMed_1;
  
  private JTextField QteMed_2;
  
  public GuiMainPanel() {
    setTitle("GSB - Compte Rendu - " + version);
    setDefaultCloseOperation(3);
    setBounds(100, 100, 950, 630);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout((LayoutManager)null);
    panelLog();
  }
  
  public void panelLog() {
    final JLabel lblEtat = new JLabel(EtatAff);
    this.panelLog.setVisible(true);
    this.panelLog.setBounds(10, 11, 914, 569);
    this.contentPane.add(this.panelLog);
    this.panelLog.setLayout((LayoutManager)null);
    this.serveurListe.setModel(new DefaultComboBoxModel<>(new String[] { "Veulliez choisir un serveur", "Serveur Local", "Serveur Local Mac", "Serveur Personnel [DUMAY Loic]"}));
    this.serveurListe.setBounds(10, 533, 318, 25);
    this.panelLog.add(this.serveurListe);
    JLabel lblConnexion = new JLabel("Connexion");
    lblConnexion.setFont(new Font("Tahoma", 1, 13));
    lblConnexion.setBounds(750, 432, 106, 23);
    this.panelLog.add(lblConnexion);
    JLabel lblIdentifiant = new JLabel("Identifiant :");
    lblIdentifiant.setBounds(716, 466, 76, 14);
    this.panelLog.add(lblIdentifiant);
    JLabel lblMotDePasse = new JLabel("  Mot de passe :");
    lblMotDePasse.setBounds(699, 502, 106, 14);
    this.panelLog.add(lblMotDePasse);
    this.txtIdentifiant = new JTextField();
    this.txtIdentifiant.setBounds(798, 461, 106, 25);
    this.panelLog.add(this.txtIdentifiant);
    this.txtIdentifiant.setColumns(10);
    JButton btnValider = new JButton("Valider");
    btnValider.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent arg0) {
            GuiMainPanel.Identifiant = GuiMainPanel.this.txtIdentifiant.getText();
            GuiMainPanel.this.MotDePasse = GuiMainPanel.this.txtMotDePasse.getText();
            InfosConnexionBDD connexionTest2 = new InfosConnexionBDD();
            GuiMainPanel.EtatConnexion = InfosConnexionBDD.EtatConnexion;
            GuiMainPanel.EtatAff = InfosConnexionBDD.EtatAff;
            if (GuiMainPanel.EtatAff == "OFF") {
              lblEtat.setText(GuiMainPanel.EtatAff);
              GuiMainPanel.btnRafraichir.setVisible(true);
              JOptionPane.showMessageDialog(null, "Dsl, le serveur ne semble pas accessible !", GuiMainPanel.this.ErrorLog, 2);
            } else if (GuiMainPanel.TypeUser == "Choisir un type") {
              JOptionPane.showMessageDialog(null, "Veuillez choisir un type SVP", GuiMainPanel.this.ErrorLog, 2);
            } else if (GuiMainPanel.Identifiant.isEmpty() && GuiMainPanel.this.MotDePasse.isEmpty()) {
              JOptionPane.showMessageDialog(null, "L'identifiant et le mot de passe n'ont pas ete saisi !", GuiMainPanel.this.ErrorLog, 2);
            } else if (GuiMainPanel.Identifiant.isEmpty()) {
              JOptionPane.showMessageDialog(null, "L'identifiant n'a pas ete saisi !", GuiMainPanel.this.ErrorLog, 2);
            } else if (GuiMainPanel.this.MotDePasse.isEmpty()) {
              JOptionPane.showMessageDialog(null, "Le mot de passe n'a pas ete saisi !", GuiMainPanel.this.ErrorLog, 2);
            } else {
              String pilote = "com.mysql.jdbc.Driver";
              try {
                Class.forName(pilote);
                String[] infosConnexionBDD = InfosConnexionBDD.InfosConnexionBDD();
                String BDD = infosConnexionBDD[0];
                String url = infosConnexionBDD[1];
                String user = infosConnexionBDD[2];
                String passwd = infosConnexionBDD[3];
                Connection con = DriverManager.getConnection(url, user, passwd);
                Statement stmt = con.createStatement();
                ResultSet resultat = null;
                String idUser = null;
                resultat = stmt.executeQuery("SELECT * FROM visiteur WHERE VIS_NOM='" + GuiMainPanel.this.txtIdentifiant.getText() + "'");
                if (resultat.next()) {
                  String idClient = resultat.getString("VIS_MATRICULE");
                  String date = resultat.getString("VIS_DATEEMBAUCHE");
                  String[] dateSplit2 = date.split(" ");
                  String[] dateSplit = dateSplit2[0].split("-");
                  String jour = dateSplit[2];
                  String mois = dateSplit[1];
                  String annee = dateSplit[0];
                  String str1;
                  switch ((str1 = mois).hashCode()) {
                    case 1537:
                      if (!str1.equals("01"))
                        break; 
                      mois = "jan";
                      break;
                    case 1538:
                      if (!str1.equals("02"))
                        break; 
                      mois = "feb";
                      break;
                    case 1539:
                      if (!str1.equals("03"))
                        break; 
                      mois = "mar";
                      break;
                    case 1540:
                      if (!str1.equals("04"))
                        break; 
                      mois = "apr";
                      break;
                    case 1541:
                      if (!str1.equals("05"))
                        break; 
                      mois = "may";
                      break;
                    case 1542:
                      if (!str1.equals("06"))
                        break; 
                      mois = "jun";
                      break;
                    case 1543:
                      if (!str1.equals("07"))
                        break; 
                      mois = "jul";
                      break;
                    case 1544:
                      if (!str1.equals("08"))
                        break; 
                      mois = "aug";
                      break;
                    case 1545:
                      if (!str1.equals("09"))
                        break; 
                      mois = "sep";
                      break;
                    case 1567:
                      if (!str1.equals("10"))
                        break; 
                      mois = "oct";
                      break;
                    case 1568:
                      if (!str1.equals("11"))
                        break; 
                      mois = "nov";
                      break;
                    case 1569:
                      if (!str1.equals("12"))
                        break; 
                      mois = "dec";
                      break;
                  } 
                  annee = annee.substring(2, 4);
                  String date_emb = String.valueOf(jour) + "-" + mois + "-" + annee;
                  String mdp = GuiMainPanel.this.txtMotDePasse.getText();
                  if (mdp.equals(date_emb)) {
                    DonneesClient Client = new DonneesClient();
                    GuiMainPanel.this.recupDonneesClient();
                    JOptionPane.showMessageDialog(null, "\nMatricule / ID : " + GuiMainPanel.this.Matricule + "\nClientType : " + GuiMainPanel.this.ClientType + "\nNom : " + GuiMainPanel.this.Nom + "\n Prenom : " + GuiMainPanel.this.Prenom + "\nAdresse : " + GuiMainPanel.this.Adresse + "\nCP : " + GuiMainPanel.this.CP + "\nVille : " + GuiMainPanel.this.Ville + "\nLogin : " + GuiMainPanel.this.Login + "\nDateEmbauche : " + GuiMainPanel.this.DateEmbauche + "\nCodeSEC : " + GuiMainPanel.this.CodeSEC + "\nCodeLAB : " + GuiMainPanel.this.CodeLAB + "\n", String.valueOf(GuiMainPanel.this.DEBUGG_MODE) + " Donnees Clients", 1);
                    GuiMainPanel.this.panelLog.setVisible(false);
                    GuiMainPanel.this.panelMenu();
                    GuiMainPanel.this.panelAccueil();
                  } else {
                    JOptionPane.showMessageDialog(null, "Oups, le mot de passe n'est pas correcte ! \n\n Assurez-vous d'entrer les 3 premieres lettres du mois \n dans le mot de passe, tel que : XX-XXX-XX", GuiMainPanel.this.ErrorLog, 2);
                  } 
                } else {
                  JOptionPane.showMessageDialog(null, "L'identifiant saisie ne fais pas parti des " + GuiMainPanel.TypeUser + "s !");
                } 
              } catch (SQLException e) {
                e.printStackTrace();
              } catch (ClassNotFoundException e) {
                e.printStackTrace();
              } 
            } 
          }
        });
    this.txtMotDePasse = new JPasswordField();
    this.txtMotDePasse.setToolTipText("");
    this.txtMotDePasse.setBounds(798, 497, 106, 25);
    this.panelLog.add(this.txtMotDePasse);
    btnValider.setBounds(716, 533, 89, 25);
    this.panelLog.add(btnValider);
    JButton btnAnnuler = new JButton("Annuler");
    btnAnnuler.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent arg0) {
            GuiMainPanel.this.txtIdentifiant.setText("");
            GuiMainPanel.this.txtMotDePasse.setText("");
          }
        });
    btnAnnuler.setBounds(815, 533, 89, 25);
    this.panelLog.add(btnAnnuler);
    JLabel lblEtatDuServeur = new JLabel("Etat du serveur : ");
    lblEtatDuServeur.setBounds(338, 533, 117, 25);
    this.panelLog.add(lblEtatDuServeur);
    lblEtat.setBounds(448, 533, 44, 25);
    this.panelLog.add(lblEtat);
    JButton btnAbout = new JButton("A propos");
    btnAbout.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent arg0) {
            Infos infos = new Infos();
            infos.setLocationRelativeTo(null);
            infos.setVisible(true);
          }
        });
    btnAbout.setBounds(10, 497, 89, 25);
    this.panelLog.add(btnAbout);
    btnRafraichir.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent arg0) {
            GuiMainPanel.this.serveurchoix = GuiMainPanel.this.serveurListe.getSelectedItem();
            if (GuiMainPanel.this.serveurchoix != "Veulliez choisir un serveur") {
              if (GuiMainPanel.this.serveurchoix == "Serveur Local")
                GuiMainPanel.serveur = "localhost"; 
              if (GuiMainPanel.this.serveurchoix == "Serveur Local Mac") {
                GuiMainPanel.serveur = "localhostMac";
              } else if (GuiMainPanel.this.serveurchoix == "Serveur Hitema [DUMAY Loic]") {
                GuiMainPanel.serveur = "hitemaLoic";
              } else if (GuiMainPanel.this.serveurchoix == "Serveur Hitema [COUTEILLON Damien]") {
                GuiMainPanel.serveur = "hitemaDamien";
              } else if (GuiMainPanel.this.serveurchoix == "Serveur Personnel [DUMAY Loic]") {
                GuiMainPanel.serveur = "personnelLoic";
              } else if (GuiMainPanel.this.serveurchoix == "Serveur Personnel [COUTEILLON Damien]") {
                GuiMainPanel.serveur = "personnelDamien";
              } 
              InfosConnexionBDD connexionTest2 = new InfosConnexionBDD();
              GuiMainPanel.EtatConnexion = InfosConnexionBDD.EtatConnexion;
              GuiMainPanel.EtatAff = InfosConnexionBDD.EtatAff;
              if (GuiMainPanel.EtatAff == "ON")
                lblEtat.setText(GuiMainPanel.EtatAff); 
              if (GuiMainPanel.EtatAff == "OFF")
                lblEtat.setText(GuiMainPanel.EtatAff); 
            } else {
              JOptionPane.showMessageDialog(null, "Oups ! " + GuiMainPanel.this.serveurchoix + ", SVP !", GuiMainPanel.this.ErrorLog, 1);
            } 
          }
        });
    btnRafraichir.setBounds(502, 533, 95, 25);
    this.panelLog.add(btnRafraichir);
  }
  
  public void panelMenu() {
    this.panelLog.setVisible(false);
    this.panelMenu.setVisible(true);
    this.panelMenu.setBounds(10, 11, 198, 569);
    this.contentPane.add(this.panelMenu);
    this.panelMenu.setLayout((LayoutManager)null);
    JLabel lblTitleMenu = new JLabel("Menu");
    lblTitleMenu.setFont(new Font("Tahoma", 1, 15));
    lblTitleMenu.setBounds(10, 11, 178, 24);
    this.panelMenu.add(lblTitleMenu);
    JButton btnRapport = new JButton("Rapports de visite");
    btnRapport.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            GuiMainPanel.this.panelRapport();
          }
        });
    JButton btnAccueil = new JButton("Accueil");
    btnAccueil.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            GuiMainPanel.this.panelAccueil();
          }
        });
    btnAccueil.setBounds(10, 46, 178, 37);
    this.panelMenu.add(btnAccueil);
    JLabel lblComptesrendus = new JLabel("Comptes-rendus");
    lblComptesrendus.setFont(new Font("Tahoma", 3, 14));
    lblComptesrendus.setBounds(10, 94, 178, 14);
    this.panelMenu.add(lblComptesrendus);
    btnRapport.setBounds(10, 119, 178, 37);
    this.panelMenu.add(btnRapport);
    JButton btnMedicaments = new JButton("Médicaments");
    btnMedicaments.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            GuiMainPanel.this.panelMedicaments();
          }
        });
    JButton btnAjoutRapport = new JButton("Nouveau Rapport");
    btnAjoutRapport.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent arg0) {
            GuiMainPanel.this.panelNewRapport();
          }
        });
    btnAjoutRapport.setBounds(10, 167, 178, 37);
    this.panelMenu.add(btnAjoutRapport);
    JLabel lblConsulter = new JLabel("Consulter");
    lblConsulter.setFont(new Font("Tahoma", 3, 14));
    lblConsulter.setBounds(10, 215, 178, 14);
    this.panelMenu.add(lblConsulter);
    btnMedicaments.setBounds(10, 240, 178, 37);
    this.panelMenu.add(btnMedicaments);
    JButton btnPraticiens = new JButton("Praticiens");
    btnPraticiens.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            GuiMainPanel.this.panelPraticiens();
          }
        });
    btnPraticiens.setBounds(10, 288, 178, 37);
    this.panelMenu.add(btnPraticiens);
    JButton btnAutresVisiteurs = new JButton("Autres Visiteurs");
    btnAutresVisiteurs.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            GuiMainPanel.this.panelAutresVisiteurs();
          }
        });
    btnAutresVisiteurs.setBounds(10, 336, 178, 37);
    this.panelMenu.add(btnAutresVisiteurs);
    JButton btnAjoutMedic = new JButton("Nouveau M");
    btnAjoutMedic.setBounds(10, 412, 178, 37);
    this.panelMenu.add(btnAjoutMedic);
    JButton btnAjoutPrat = new JButton("Nouveau Praticien");
    btnAjoutPrat.setBounds(10, 460, 178, 37);
    this.panelMenu.add(btnAjoutPrat);
    JButton btnLogOut = new JButton("Déconnexion");
    btnLogOut.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            GuiMainPanel.this.cleanFrame();
            GuiMainPanel.this.txtIdentifiant.setText("");
            GuiMainPanel.this.txtMotDePasse.setText("");
            GuiMainPanel.this.panelLog();
          }
        });
    btnLogOut.setBounds(10, 521, 178, 37);
    this.panelMenu.add(btnLogOut);
  }
  
  public void panelAccueil() {
    this.panelAccueil.setVisible(true);
    this.panelRapport.setVisible(false);
    this.panelMedicaments.setVisible(false);
    this.panelPraticiens.setVisible(false);
    this.panelAutresVisiteurs.setVisible(false);
    this.panelNewRapport.setVisible(false);
    this.panelAccueil.setBounds(214, 11, 710, 569);
    this.contentPane.add(this.panelAccueil);
    this.panelAccueil.setLayout((LayoutManager)null);
    JLabel lblTitleHome = new JLabel("Bienvenue dans l'application de Compte rendu de GSB");
    lblTitleHome.setFont(new Font("Tahoma", 1, 15));
    lblTitleHome.setBounds(10, 11, 690, 24);
    this.panelAccueil.add(lblTitleHome);
    JLabel lblClientTitle = new JLabel("Bonjour, " + this.Nom + " " + this.Prenom + ".");
    lblClientTitle.setBounds(10, 70, 138, 14);
    this.panelAccueil.add(lblClientTitle);
    JLabel lblClientStatut = new JLabel("Vous : " + this.ClientType + ".");
    lblClientStatut.setBounds(10, 95, 138, 14);
    this.panelAccueil.add(lblClientStatut);
  }
  
  public void panelRapport() {
    this.panelAccueil.setVisible(false);
    this.panelMedicaments.setVisible(false);
    this.panelPraticiens.setVisible(false);
    this.panelAutresVisiteurs.setVisible(false);
    this.panelNewRapport.setVisible(false);
    this.panelRapport.removeAll();
    String[] infosConnexionBDD = InfosConnexionBDD.InfosConnexionBDD();
    String BDD = infosConnexionBDD[0];
    String url = infosConnexionBDD[1];
    String user = infosConnexionBDD[2];
    String passwd = infosConnexionBDD[3];
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection(url, user, passwd);
      Statement stmt = con.createStatement();
      int totalRap = 0;
      ResultSet resultat = null;
      resultat = stmt.executeQuery("SELECT count(SUBSTRING(RAP_DATE,1,10)) AS result FROM rapport_visite WHERE VIS_MATRICULE='" + this.Matricule + "'");
      while (resultat.next())
        totalRap = resultat.getInt("result"); 
      totalRap++;
      String[] tmpListe = new String[totalRap];
      tmpListe[0] = "Choisir la date du rendez-vous";
      final String[] tmpListeRapNum = new String[totalRap];
      tmpListeRapNum[0] = "0";
      int x = 1;
      resultat = null;
      resultat = stmt.executeQuery("SELECT DISTINCT  VIS_MATRICULE, RAP_NUM, SUBSTRING(RAP_DATE,1,10) AS dateRapport FROM rapport_visite WHERE VIS_MATRICULE='" + this.Matricule + "'");
      while (resultat.next()) {
        String idRap = resultat.getString("RAP_NUM");
        tmpListeRapNum[x] = idRap;
        Date date = null;
        String tmpDate = null;
        date = resultat.getDate("dateRapport");
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
        tmpDate = simpleFormat.format(date);
        tmpListe[x] = "Rapport num" + x + " du " + tmpDate;
        x++;
      } 
      this.choixListe = (String)this.choixListeRap;
      this.panelRapport.setVisible(true);
      this.panelRapport.setBounds(190, 11, 710, 569);
      this.contentPane.add(this.panelRapport);
      this.panelRapport.setLayout((LayoutManager)null);
      JLabel lblTitleRapport = new JLabel("Rapports de visite");
      lblTitleRapport.setFont(new Font("Tahoma", 1, 15));
      lblTitleRapport.setBounds(311, 5, 150, 19);
      this.panelRapport.add(lblTitleRapport);
      if (this.ID_RAP != null) {
        this.panelRapport.removeAll();
        this.panelRapport.setBounds(190, 11, 710, 569);
        this.contentPane.add(this.panelRapport);
        this.panelRapport.setLayout((LayoutManager)null);
        lblTitleRapport = new JLabel("Rapports de visite");
        lblTitleRapport.setFont(new Font("Tahoma", 1, 15));
        lblTitleRapport.setBounds(311, 5, 150, 19);
        this.panelRapport.add(lblTitleRapport);
        resultat = null;
        resultat = stmt.executeQuery("SELECT * FROM rapport_visite WHERE VIS_MATRICULE='" + this.Matricule + "' AND RAP_NUM= '" + this.ID_RAP + "'");
        if (resultat.next()) {
          String idRap = resultat.getString("RAP_NUM");
          String idPrat = resultat.getString("PRA_NUM");
          String RapBilan = resultat.getString("RAP_BILAN");
          String RapMotif = resultat.getString("RAP_MOTIF");
          JLabel lblRapportNumber = new JLabel("Num: ");
          lblRapportNumber.setBounds(27, 128, 68, 14);
          this.txtRapport = new JTextField();
          this.txtRapport.setBounds(105, 125, 175, 20);
          this.txtRapport.setText(idRap);
          this.txtRapport.setColumns(10);
          JLabel lblRapportPraticien = new JLabel("Praticien :");
          lblRapportPraticien.setBounds(27, 160, 68, 14);
          this.txtRapportPraticien = new JTextField();
          this.txtRapportPraticien.setBounds(105, 157, 175, 20);
          this.txtRapportPraticien.setText(idPrat);
          this.txtRapportPraticien.setColumns(10);
          JLabel lblRapportBilan = new JLabel("Bilan : ");
          lblRapportBilan.setBounds(27, 199, 46, 14);
          JTextPane textRapportBilan = new JTextPane();
          textRapportBilan.setBounds(105, 194, 533, 133);
          textRapportBilan.setText(RapBilan);
          JLabel lblRapportMotif = new JLabel("Motif : ");
          lblRapportMotif.setBounds(27, 356, 46, 14);
          JTextPane txtRapportMotif = new JTextPane();
          txtRapportMotif.setBounds(105, 351, 533, 133);
          txtRapportMotif.setText(RapMotif);
          this.panelRapport.add(lblRapportNumber);
          this.panelRapport.add(lblRapportPraticien);
          this.panelRapport.add(lblRapportBilan);
          this.panelRapport.add(lblRapportMotif);
          this.panelRapport.add(this.txtRapport);
          this.panelRapport.add(this.txtRapportPraticien);
          this.panelRapport.add(textRapportBilan);
          this.panelRapport.add(txtRapportMotif);
        } 
        this.panelRapport.setVisible(true);
      } 
      final JComboBox listRapport = new JComboBox();
      listRapport.setModel(new DefaultComboBoxModel<>(tmpListe));
      listRapport.setBounds(27, 67, 481, 29);
      this.panelRapport.add(listRapport);
      JButton btnRapportValider = new JButton("Valider");
      btnRapportValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
              GuiMainPanel.this.choixListeRap = listRapport.getSelectedItem();
              GuiMainPanel.this.choixListe = (String)GuiMainPanel.this.choixListeRap;
              if (GuiMainPanel.this.choixListe != null && GuiMainPanel.this.choixListe != "Choisir la date du rendez-vous") {
                String[] tmpchoix = GuiMainPanel.this.choixListe.split("num");
                String tmpNumChoix = tmpchoix[1];
                String[] tmpNum = tmpNumChoix.split("du");
                String tmpChoixResult = tmpNum[0];
                tmpChoixResult = tmpChoixResult.replaceAll(" ", "");
                int x = Integer.parseInt(tmpChoixResult);
                GuiMainPanel.this.ID_RAP = tmpListeRapNum[x];
                GuiMainPanel.this.panelRapport();
              } 
            }
          });
      btnRapportValider.setBounds(532, 66, 106, 30);
      this.panelRapport.add(btnRapportValider);
      System.out.println("-> Page Rapport Visite charge");
      System.out.println("# - - - - - - - - - - - - - - - - - - - - - - - - - - - #");
    } catch (Exception e) {
      System.err.println("Oups ! Il y a une erreur SQL !");
    } 
  }
  
  public void panelMedicaments() {
    this.panelAccueil.setVisible(false);
    this.panelRapport.setVisible(false);
    this.panelPraticiens.setVisible(false);
    this.panelAutresVisiteurs.setVisible(false);
    this.panelNewRapport.setVisible(false);
    this.panelMedicaments.removeAll();
    this.panelMedicaments.setVisible(true);
    String tmpDepotLegal = null;
    String tmpNomCommercial = null;
    String tmpFamCode = null;
    String tmpComposition = null;
    String tmpEffets = null;
    String tmpContreIndic = null;
    float tmpPrixEchan = 0.0F;
    String[] infosConnexionBDD = InfosConnexionBDD.InfosConnexionBDD();
    String BDD = infosConnexionBDD[0];
    String url = infosConnexionBDD[1];
    String user = infosConnexionBDD[2];
    String passwd = infosConnexionBDD[3];
    String tmpIDDepotLegal = null;
    try {
      JTextField lblPages = new JTextField();
      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection(url, user, passwd);
      Statement stmt = con.createStatement();
      ResultSet resultat = null;
      resultat = stmt.executeQuery("SELECT count(MED_DEPOTLEGAL) AS result FROM medicament");
      while (resultat.next())
        this.MedListeMax = resultat.getInt("result"); 
      if (this.MedMoveList < 1)
        this.MedMoveList++; 
      if (this.MedMoveList > this.MedListeMax)
        this.MedMoveList--; 
      String i = Integer.toString(this.MedMoveList);
      int x = 0;
      String InsertDepotLegal = null;
      String h = null;
      String f = null;
      resultat = null;
      resultat = stmt.executeQuery("SELECT MED_DEPOTLEGAL FROM medicament ORDER BY MED_DEPOTLEGAL");
      while (resultat.next()) {
        x++;
        InsertDepotLegal = x + '-' + resultat.getString("MED_DEPOTLEGAL");
        String[] tmpCut = InsertDepotLegal.split("-");
        h = tmpCut[0];
        f = tmpCut[1];
        if (x == this.MedMoveList)
          tmpIDDepotLegal = f; 
      } 
      this.MednbresPages = this.MedMoveList + "/" + this.MedListeMax;
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
      this.panelMedicaments.setBounds(214, 11, 710, 569);
      this.contentPane.add(this.panelMedicaments);
      this.panelMedicaments.setLayout((LayoutManager)null);
      JLabel lblTitleMedicaments = new JLabel("Medicaments");
      lblTitleMedicaments.setFont(new Font("Tahoma", 1, 15));
      lblTitleMedicaments.setBounds(306, 5, 127, 19);
      this.panelMedicaments.add(lblTitleMedicaments);
      JLabel lblDepotLegal = new JLabel("DEPOT LEGAL :");
      lblDepotLegal.setBounds(10, 52, 159, 14);
      this.panelMedicaments.add(lblDepotLegal);
      this.txtDepotLegal = new JTextField(tmpDepotLegal);
      this.txtDepotLegal.setBounds(179, 49, 178, 20);
      this.panelMedicaments.add(this.txtDepotLegal);
      this.txtDepotLegal.setColumns(10);
      JLabel lblNomCommercial = new JLabel("NOM COMMERCIAL :");
      lblNomCommercial.setBounds(10, 87, 159, 14);
      this.panelMedicaments.add(lblNomCommercial);
      this.txtNomCommercial = new JTextField(tmpNomCommercial);
      this.txtNomCommercial.setBounds(179, 84, 178, 20);
      this.panelMedicaments.add(this.txtNomCommercial);
      this.txtNomCommercial.setColumns(10);
      JLabel lblFamille = new JLabel("FAMILLE :");
      lblFamille.setBounds(10, 118, 159, 14);
      this.panelMedicaments.add(lblFamille);
      this.txtFamCode = new JTextField(tmpFamCode);
      this.txtFamCode.setBounds(179, 115, 178, 20);
      this.panelMedicaments.add(this.txtFamCode);
      this.txtFamCode.setColumns(10);
      JLabel lblComposition = new JLabel("COMPOSITION :");
      lblComposition.setBounds(10, 143, 159, 14);
      this.panelMedicaments.add(lblComposition);
      JTextPane scrollPaneComposition = new JTextPane();
      scrollPaneComposition.setText(tmpComposition);
      scrollPaneComposition.setBounds(10, 168, 690, 83);
      this.panelMedicaments.add(scrollPaneComposition);
      JLabel lblEffets = new JLabel("EFFETS :");
      lblEffets.setBounds(10, 262, 76, 14);
      this.panelMedicaments.add(lblEffets);
      JTextPane scrollPaneEffets = new JTextPane();
      scrollPaneEffets.setText(tmpEffets);
      scrollPaneEffets.setBounds(10, 287, 690, 83);
      this.panelMedicaments.add(scrollPaneEffets);
      JLabel lblContreIndic = new JLabel("CONTRE INDIC. :");
      lblContreIndic.setBounds(10, 381, 127, 14);
      this.panelMedicaments.add(lblContreIndic);
      JTextPane scrollPaneContreIndic = new JTextPane();
      scrollPaneContreIndic.setText(tmpContreIndic);
      scrollPaneContreIndic.setBounds(10, 406, 690, 83);
      this.panelMedicaments.add(scrollPaneContreIndic);
      JLabel lblPrixEchantillion = new JLabel("PRIX ECHANTILLON : ");
      lblPrixEchantillion.setBounds(10, 504, 159, 14);
      this.panelMedicaments.add(lblPrixEchantillion);
      this.txtPrixEchantillon = new JTextField(""+tmpPrixEchan+"");
      this.txtPrixEchantillon.setBounds(179, 501, 58, 20);
      this.panelMedicaments.add(this.txtPrixEchantillon);
      this.txtPrixEchantillon.setColumns(10);
      JLabel lblSigle = new JLabel("Euro");
      lblSigle.setBounds(247, 504, 47, 14);
      this.panelMedicaments.add(lblSigle);
      lblPages.setText(this.MednbresPages);
      lblPages.setBounds(61, 537, 76, 20);
      this.panelMedicaments.add(lblPages);
      JButton buttonPrecedent = new JButton("<");
      buttonPrecedent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
              GuiMainPanel.this.MedMoveList--;
              GuiMainPanel.this.panelMedicaments();
            }
          });
      buttonPrecedent.setBounds(10, 535, 41, 25);
      this.panelMedicaments.add(buttonPrecedent);
      JButton buttonSuivant = new JButton(">");
      buttonSuivant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              GuiMainPanel.this.MedMoveList++;
              GuiMainPanel.this.panelMedicaments();
            }
          });
      buttonSuivant.setBounds(148, 535, 41, 25);
      this.panelMedicaments.add(buttonSuivant);
      System.out.println("-> Page Medicament charge");
      System.out.println("# - - - - - - - - - - - - - - - - - - - - - - - - - - - #");
    } catch (Exception e) {
      System.err.println("Oups ! Il y a une erreur SQL !");
    } 
  }
  
  public void panelPraticiens() {
    this.panelAccueil.setVisible(false);
    this.panelRapport.setVisible(false);
    this.panelMedicaments.setVisible(false);
    this.panelAutresVisiteurs.setVisible(false);
    this.panelNewRapport.setVisible(false);
    this.panelPraticiens.removeAll();
    int tmpPraNum = 0;
    String tmpPraNom = null;
    String tmpPraPrenom = null;
    String tmpPraAdresse = null;
    String tmpPraCP = null;
    String tmpPraVille = null;
    float tmpPraCoef = 0.0F;
    String tmpTypeCode = null;
    String[] infosConnexionBDD = InfosConnexionBDD.InfosConnexionBDD();
    String BDD = infosConnexionBDD[0];
    String url = infosConnexionBDD[1];
    String user = infosConnexionBDD[2];
    String passwd = infosConnexionBDD[3];
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection(url, user, passwd);
      Statement stmt = con.createStatement();
      ResultSet resultat = null;
      resultat = stmt.executeQuery("SELECT count(PRA_NUM) AS result FROM praticien");
      while (resultat.next())
        this.PratListeMax = resultat.getInt("result"); 
      if (this.PratMoveList < 1)
        this.PratMoveList++; 
      if (this.PratMoveList > this.PratListeMax)
        this.PratMoveList--; 
      int totalRap = this.PratListeMax++;
      final String[] tmpListePraNum = new String[totalRap];
      tmpListePraNum[0] = "0";
      int x = 1;
      resultat = null;
      resultat = stmt.executeQuery("SELECT * FROM praticien ORDER BY PRA_NOM");
      while (resultat.next()) {
        int PraNum = resultat.getInt("PRA_NUM");
        String PraNom = resultat.getString("PRA_NOM");
        String PraPrenom = resultat.getString("PRA_PRENOM");
        System.out.println(x + " - " + PraNum + " - " + PraNom + " " + PraPrenom);
        x++;
      } 
      System.err.println("A finir");
      this.PratnbresPages = this.PratMoveList + "/" + this.PratListeMax;
      resultat = null;
      resultat = stmt.executeQuery("SELECT * FROM praticien WHERE PRA_NUM='" + this.PratMoveList + "'");
      if (resultat.next()) {
        tmpPraNum = resultat.getInt("PRA_NUM");
        tmpPraNom = resultat.getString("PRA_NOM");
        tmpPraPrenom = resultat.getString("PRA_PRENOM");
        tmpPraAdresse = resultat.getString("PRA_ADRESSE");
        tmpPraCP = resultat.getString("PRA_CP");
        tmpPraVille = resultat.getString("PRA_VILLE");
        tmpPraCoef = resultat.getFloat("PRA_COEFNOTORIETE");
        tmpTypeCode = resultat.getString("TYP_CODE");
      } 
      this.panelPraticiens.setBounds(214, 11, 710, 569);
      this.contentPane.add(this.panelPraticiens);
      this.panelPraticiens.setLayout((LayoutManager)null);
      JComboBox listPrat = new JComboBox();
      listPrat.setModel(new DefaultComboBoxModel<>(tmpListePraNum));
      listPrat.setBounds(26, 40, 446, 25);
      this.panelPraticiens.add(listPrat);
      JButton btnValider_1 = new JButton("Valider");
      btnValider_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
              int x = Integer.parseInt(GuiMainPanel.this.PratnbresPages);
              int y = GuiMainPanel.this.PratListeMax;
              for (int i = 0; i < y; i++) {
                if (i == x) {
                  GuiMainPanel.this.PratMoveList = Integer.parseInt(tmpListePraNum[x]);
                  GuiMainPanel.this.panelPraticiens();
                } 
              } 
            }
          });
      btnValider_1.setBounds(482, 40, 102, 25);
      this.panelPraticiens.add(btnValider_1);
      JLabel lblTitlePraticiens = new JLabel("Praticiens");
      lblTitlePraticiens.setBounds(317, 5, 97, 19);
      lblTitlePraticiens.setFont(new Font("Tahoma", 1, 15));
      this.panelPraticiens.add(lblTitlePraticiens);
      JLabel lblPraticienNumero = new JLabel("Num: ");
      lblPraticienNumero.setBounds(26, 79, 127, 14);
      this.panelPraticiens.add(lblPraticienNumero);
      this.txtPraticienNumero = new JTextField();
      this.txtPraticienNumero.setBounds(174, 76, 200, 20);
      this.txtPraticienNumero.setText(""+tmpPraNum+"");
      this.panelPraticiens.add(this.txtPraticienNumero);
      this.txtPraticienNumero.setColumns(10);
      JLabel lblPraticienNom = new JLabel("Nom : ");
      lblPraticienNom.setBounds(26, 110, 127, 14);
      this.panelPraticiens.add(lblPraticienNom);
      this.txtPraticienNom = new JTextField();
      this.txtPraticienNom.setBounds(174, 107, 200, 20);
      this.txtPraticienNom.setText(tmpPraNom);
      this.panelPraticiens.add(this.txtPraticienNom);
      this.txtPraticienNom.setColumns(10);
      JLabel lblPraticienPrenom = new JLabel("Pr: ");
      lblPraticienPrenom.setBounds(26, 141, 127, 14);
      this.panelPraticiens.add(lblPraticienPrenom);
      this.txtPraticienPrenom = new JTextField();
      this.txtPraticienPrenom.setBounds(174, 138, 200, 20);
      this.txtPraticienPrenom.setText(tmpPraPrenom);
      this.panelPraticiens.add(this.txtPraticienPrenom);
      this.txtPraticienPrenom.setColumns(10);
      JLabel lblPraticienAdresse = new JLabel("Adresse :");
      lblPraticienAdresse.setBounds(26, 172, 127, 14);
      this.panelPraticiens.add(lblPraticienAdresse);
      this.txtPraticienAdresse = new JTextField();
      this.txtPraticienAdresse.setBounds(174, 169, 200, 20);
      this.txtPraticienAdresse.setText(tmpPraAdresse);
      this.panelPraticiens.add(this.txtPraticienAdresse);
      this.txtPraticienAdresse.setColumns(10);
      JLabel lblPraticienVille = new JLabel("Ville :");
      lblPraticienVille.setBounds(26, 203, 127, 14);
      this.panelPraticiens.add(lblPraticienVille);
      this.txtPraticienVille = new JTextField();
      this.txtPraticienVille.setBounds(174, 200, 200, 20);
      this.txtPraticienVille.setText(tmpPraVille);
      this.panelPraticiens.add(this.txtPraticienVille);
      this.txtPraticienVille.setColumns(10);
      this.txtPraticienCP = new JTextField();
      this.txtPraticienCP.setBounds(384, 200, 200, 20);
      this.txtPraticienCP.setText(tmpPraCP);
      this.panelPraticiens.add(this.txtPraticienCP);
      this.txtPraticienCP.setColumns(10);
      JLabel lblPraticienCoef = new JLabel("Coef. Notori");
      lblPraticienCoef.setBounds(26, 234, 127, 14);
      this.panelPraticiens.add(lblPraticienCoef);
      this.txtPraticienCoef = new JTextField();
      this.txtPraticienCoef.setBounds(174, 231, 200, 20);
      this.txtPraticienCoef.setText(""+tmpPraCoef+"");
      this.panelPraticiens.add(this.txtPraticienCoef);
      this.txtPraticienCoef.setColumns(10);
      JLabel lblPraticienTypeCode = new JLabel("Type code");
      lblPraticienTypeCode.setBounds(26, 265, 127, 14);
      this.panelPraticiens.add(lblPraticienTypeCode);
      this.txtPraticienTypeCode = new JTextField();
      this.txtPraticienTypeCode.setBounds(174, 262, 200, 20);
      this.txtPraticienTypeCode.setText(tmpTypeCode);
      this.panelPraticiens.add(this.txtPraticienTypeCode);
      this.txtPraticienTypeCode.setColumns(10);
      JButton btnPraticienPrecedent = new JButton("<");
      btnPraticienPrecedent.setBounds(26, 329, 51, 25);
      btnPraticienPrecedent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              GuiMainPanel.this.PratMoveList--;
              GuiMainPanel.this.panelPraticiens();
            }
          });
      this.panelPraticiens.add(btnPraticienPrecedent);
      JTextField lblPraticienTtl = new JTextField(this.PratnbresPages);
      lblPraticienTtl.setBounds(97, 334, 46, 14);
      this.panelPraticiens.add(lblPraticienTtl);
      JButton btnPraticienSuivant = new JButton(">");
      btnPraticienSuivant.setBounds(153, 326, 51, 30);
      btnPraticienSuivant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              GuiMainPanel.this.PratMoveList++;
              GuiMainPanel.this.panelPraticiens();
            }
          });
      this.panelPraticiens.add(btnPraticienSuivant);
      System.out.println("-> Page Praticiens charge");
      System.out.println("# - - - - - - - - - - - - - - - - - - - - - - - - - - - #");
    } catch (Exception e) {
      System.err.println("Oups ! Il y a une erreur SQL !");
    } 
    this.panelPraticiens.setVisible(true);
  }
  
  public void panelAutresVisiteurs() {
    this.panelAccueil.setVisible(false);
    this.panelRapport.setVisible(false);
    this.panelMedicaments.setVisible(false);
    this.panelPraticiens.setVisible(false);
    this.panelNewRapport.setVisible(false);
    this.panelAutresVisiteurs.setVisible(true);
    this.panelAutresVisiteurs.setBounds(214, 11, 710, 569);
    this.contentPane.add(this.panelAutresVisiteurs);
    this.panelAutresVisiteurs.setLayout((LayoutManager)null);
    JLabel lblTitleAutresVisiteurs = new JLabel("Autres Visiteurs");
    lblTitleAutresVisiteurs.setFont(new Font("Tahoma", 1, 15));
    lblTitleAutresVisiteurs.setBounds(295, 5, 152, 19);
    this.panelAutresVisiteurs.add(lblTitleAutresVisiteurs);
    JComboBox AutresVisiteursDeptListe = new JComboBox();
    AutresVisiteursDeptListe.setModel(new DefaultComboBoxModel<>(new String[] { "Choisir un visiteur", "Exemple" }));
    AutresVisiteursDeptListe.setBounds(43, 73, 308, 25);
    this.panelAutresVisiteurs.add(AutresVisiteursDeptListe);
    JButton btnAutresVisiteursValider = new JButton("Valider");
    btnAutresVisiteursValider.setBounds(361, 72, 110, 26);
    this.panelAutresVisiteurs.add(btnAutresVisiteursValider);
    JLabel lblAutresVisiteursNom = new JLabel("Nom :");
    lblAutresVisiteursNom.setBounds(43, 131, 144, 14);
    this.panelAutresVisiteurs.add(lblAutresVisiteursNom);
    this.txtAutresVisiteursNom = new JTextField();
    this.txtAutresVisiteursNom.setBounds(197, 128, 274, 20);
    this.panelAutresVisiteurs.add(this.txtAutresVisiteursNom);
    this.txtAutresVisiteursNom.setColumns(10);
    JLabel lblAutresVisiteursPrenom = new JLabel("Pr:");
    lblAutresVisiteursPrenom.setBounds(43, 159, 144, 14);
    this.panelAutresVisiteurs.add(lblAutresVisiteursPrenom);
    this.txtAutresVisiteursPrenom = new JTextField();
    this.txtAutresVisiteursPrenom.setColumns(10);
    this.txtAutresVisiteursPrenom.setBounds(197, 156, 274, 20);
    this.panelAutresVisiteurs.add(this.txtAutresVisiteursPrenom);
    JLabel lblAutresVisiteursAdresse = new JLabel("Adresse : ");
    lblAutresVisiteursAdresse.setBounds(43, 187, 144, 14);
    this.panelAutresVisiteurs.add(lblAutresVisiteursAdresse);
    this.txtAutresVisiteursAdresse = new JTextField();
    this.txtAutresVisiteursAdresse.setColumns(10);
    this.txtAutresVisiteursAdresse.setBounds(197, 184, 274, 20);
    this.panelAutresVisiteurs.add(this.txtAutresVisiteursAdresse);
    JLabel lblAutresVisiteursCP = new JLabel("CP : ");
    lblAutresVisiteursCP.setBounds(43, 215, 144, 14);
    this.panelAutresVisiteurs.add(lblAutresVisiteursCP);
    this.txtAutresVisiteursCP = new JTextField();
    this.txtAutresVisiteursCP.setColumns(10);
    this.txtAutresVisiteursCP.setBounds(197, 212, 274, 20);
    this.panelAutresVisiteurs.add(this.txtAutresVisiteursCP);
    JLabel lblAutresVisiteursVille = new JLabel("Ville : ");
    lblAutresVisiteursVille.setBounds(43, 243, 144, 14);
    this.panelAutresVisiteurs.add(lblAutresVisiteursVille);
    this.txtAutresVisiteursVille = new JTextField();
    this.txtAutresVisiteursVille.setColumns(10);
    this.txtAutresVisiteursVille.setBounds(197, 240, 274, 20);
    this.panelAutresVisiteurs.add(this.txtAutresVisiteursVille);
    JLabel lblAutresVisiteursSecteur = new JLabel("Secteur : ");
    lblAutresVisiteursSecteur.setBounds(43, 271, 144, 14);
    this.panelAutresVisiteurs.add(lblAutresVisiteursSecteur);
    this.txtAutresVisiteursSecteur = new JTextField();
    this.txtAutresVisiteursSecteur.setColumns(10);
    this.txtAutresVisiteursSecteur.setBounds(197, 268, 274, 20);
    this.panelAutresVisiteurs.add(this.txtAutresVisiteursSecteur);
    JButton btnAutresVisiteursUp = new JButton("<");
    btnAutresVisiteursUp.setBounds(43, 299, 41, 25);
    this.panelAutresVisiteurs.add(btnAutresVisiteursUp);
    JLabel lblAutresVisiteursPages = new JLabel("00 / 00");
    lblAutresVisiteursPages.setBounds(94, 304, 61, 14);
    this.panelAutresVisiteurs.add(lblAutresVisiteursPages);
    JButton btnAutresVisiteursDown = new JButton(">");
    btnAutresVisiteursDown.setBounds(169, 299, 41, 25);
    this.panelAutresVisiteurs.add(btnAutresVisiteursDown);
  }
  
  public void panelNewRapport() {
    this.panelAccueil.setVisible(false);
    this.panelRapport.setVisible(false);
    this.panelMedicaments.setVisible(false);
    this.panelPraticiens.setVisible(false);
    this.panelAutresVisiteurs.setVisible(false);
    this.panelNewRapport.setVisible(true);
    String[] infosConnexionBDD = InfosConnexionBDD.InfosConnexionBDD();
    String BDD = infosConnexionBDD[0];
    String url = infosConnexionBDD[1];
    String user = infosConnexionBDD[2];
    String passwd = infosConnexionBDD[3];
    try {
      JTextField lblPages = new JTextField();
      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection(url, user, passwd);
      Statement stmt = con.createStatement();
      int totalMed = 0;
      ResultSet resultat = null;
      resultat = stmt.executeQuery("SELECT count(MED_DEPOTLEGAL) AS result FROM medicament ORDER BY MED_DEPOTLEGAL");
      while (resultat.next())
        totalMed = resultat.getInt("result"); 
      totalMed++;
      int totalPrat = 0;
      resultat = null;
      resultat = stmt.executeQuery("SELECT count(PRA_NUM) AS result FROM praticien ORDER BY PRA_NUM");
      while (resultat.next())
        totalPrat = resultat.getInt("result"); 
      totalPrat++;
      int x = 0;
      String Medicament_ID = "";
      String Medicament_NOM = "";
      int y = 0;
      String Praticien_Nom = "";
      String Praticien_Prenom = "";
      String[] Med_List = new String[totalMed];
      Med_List[0] = "Choisir un medicament";
      resultat = null;
      resultat = stmt.executeQuery("SELECT MED_DEPOTLEGAL,MED_NOMCOMMERCIAL FROM medicament ORDER BY MED_DEPOTLEGAL");
      while (resultat.next()) {
        x++;
        Medicament_ID = resultat.getString("MED_DEPOTLEGAL");
        Medicament_NOM = resultat.getString("MED_NOMCOMMERCIAL");
        Med_List[x] = Medicament_NOM;
      } 
      String[] Prat_List = new String[totalPrat];
      Prat_List[0] = "Choisir un praticien";
      resultat = null;
      resultat = stmt.executeQuery("SELECT PRA_NUM, PRA_NOM, PRA_PRENOM FROM praticien ORDER BY PRA_NUM");
      while (resultat.next()) {
        y++;
        Praticien_Nom = resultat.getString("PRA_NOM");
        Praticien_Prenom = resultat.getString("PRA_PRENOM");
        Prat_List[y] = String.valueOf(Praticien_Nom) + " " + Praticien_Prenom;
      } 
      this.panelNewRapport.setBounds(10, 11, 914, 569);
      this.contentPane.add(this.panelNewRapport);
      this.panelNewRapport.setLayout((LayoutManager)null);
      JLabel lblChoixPrat = new JLabel("Nouveau rapport de visiteur");
      lblChoixPrat.setFont(new Font("Tahoma", 1, 15));
      lblChoixPrat.setBounds(502, 27, 256, 14);
      this.panelNewRapport.add(lblChoixPrat);
      final JComboBox<String> ChoixPrat = new JComboBox<>(Prat_List);
      ChoixPrat.setBounds(443, 90, 222, 25);
      this.panelNewRapport.add(ChoixPrat);
      JLabel lblNewRapportBilan = new JLabel("Bilan : ");
      lblNewRapportBilan.setBounds(344, 203, 63, 14);
      this.panelNewRapport.add(lblNewRapportBilan);
      final JTextPane textNewRapportBilan = new JTextPane();
      textNewRapportBilan.setBounds(344, 228, 485, 167);
      this.panelNewRapport.add(textNewRapportBilan);
      JLabel lblNewRapportMotif = new JLabel("Motif : ");
      lblNewRapportMotif.setBounds(344, 120, 63, 14);
      this.panelNewRapport.add(lblNewRapportMotif);
      final JTextPane textNewRapportMotif = new JTextPane();
      textNewRapportMotif.setBounds(344, 145, 485, 47);
      this.panelNewRapport.add(textNewRapportMotif);
      JLabel lblChoixMed = new JLabel("Choix du M:");
      lblChoixMed.setBounds(344, 406, 188, 23);
      this.panelNewRapport.add(lblChoixMed);
      final JComboBox ChoixMed_1 = new JComboBox();
      ChoixMed_1.setModel(new DefaultComboBoxModel<>(Med_List));
      ChoixMed_1.setBounds(344, 440, 188, 25);
      this.panelNewRapport.add(ChoixMed_1);
      final JComboBox ChoixMed_2 = new JComboBox();
      ChoixMed_2.setModel(new DefaultComboBoxModel<>(Med_List));
      ChoixMed_2.setBounds(344, 476, 188, 25);
      this.panelNewRapport.add(ChoixMed_2);
      JLabel lblQteMed = new JLabel("Quantitde M: ");
      lblQteMed.setBounds(542, 406, 202, 23);
      this.panelNewRapport.add(lblQteMed);
      this.QteMed_1 = new JTextField();
      this.QteMed_1.setBounds(542, 476, 123, 25);
      this.panelNewRapport.add(this.QteMed_1);
      this.QteMed_1.setColumns(10);
      this.QteMed_2 = new JTextField();
      this.QteMed_2.setBounds(542, 440, 123, 25);
      this.panelNewRapport.add(this.QteMed_2);
      this.QteMed_2.setColumns(10);
      JButton btnNewRapportEffacer = new JButton("EFFACER");
      btnNewRapportEffacer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
              textNewRapportBilan.setText("");
              textNewRapportMotif.setText("");
              GuiMainPanel.this.QteMed_1.setText("");
              GuiMainPanel.this.QteMed_2.setText("");
            }
          });
      btnNewRapportEffacer.setBounds(344, 512, 89, 25);
      this.panelNewRapport.add(btnNewRapportEffacer);
      JButton btnNewRapportValider = new JButton("VALIDER");
      btnNewRapportValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
              Object tmpChoixPrat = ChoixPrat.getSelectedItem();
              GuiMainPanel.tmpVIS_MATRICULE = GuiMainPanel.this.Matricule;
              GuiMainPanel.tmpPRA_NUM = (String)tmpChoixPrat;
              GuiMainPanel.tmpRAP_BILAN = textNewRapportBilan.getText();
              GuiMainPanel.tmpRAP_MOTIF = textNewRapportMotif.getText();
              Object tmpChoixMed_1 = ChoixMed_1.getSelectedItem();
              GuiMainPanel.tmpMED_DEPOTLEGAL_1 = (String)tmpChoixMed_1;
              Object tmpChoixMed_2 = ChoixMed_2.getSelectedItem();
              GuiMainPanel.tmpMED_DEPOTLEGAL_2 = (String)tmpChoixMed_2;
              GuiMainPanel.tmpQteMed_1 = GuiMainPanel.this.QteMed_1.getText();
              GuiMainPanel.tmpQteMed_2 = GuiMainPanel.this.QteMed_2.getText();
              NewRapport NewRapport = new NewRapport();
              textNewRapportBilan.setText("");
              textNewRapportMotif.setText("");
              GuiMainPanel.this.QteMed_1.setText("");
              GuiMainPanel.this.QteMed_2.setText("");
            }
          });
      btnNewRapportValider.setBounds(443, 512, 89, 25);
      this.panelNewRapport.add(btnNewRapportValider);
      JLabel lblPraticien = new JLabel("Praticien :");
      lblPraticien.setBounds(344, 95, 82, 14);
      this.panelNewRapport.add(lblPraticien);
      System.out.println("-> Page New Rapport charge");
      System.out.println("# - - - - - - - - - - - - - - - - - - - - - - - - - - - #");
    } catch (Exception e) {
      System.err.println("Oups ! Il y a une erreur SQL !");
    } 
  }
  
  public void recupDonneesClient() {
    this.ClientType = DonneesClient.ClientType;
    this.Nom = DonneesClient.Nom;
    this.Prenom = DonneesClient.Prenom;
    this.Adresse = DonneesClient.Adresse;
    this.CP = DonneesClient.CP;
    this.Ville = DonneesClient.Ville;
    this.Matricule = DonneesClient.Matricule;
    this.Login = DonneesClient.Login;
    this.DateEmbauche = DonneesClient.DateEmbauche;
    this.CodeSEC = DonneesClient.CodeSEC;
    this.CodeLAB = DonneesClient.CodeLAB;
  }
  
  public void cleanFrame() {
    this.panelMenu.setVisible(false);
    this.panelAccueil.setVisible(false);
    this.panelRapport.setVisible(false);
    this.panelMedicaments.setVisible(false);
    this.panelPraticiens.setVisible(false);
    this.panelAutresVisiteurs.setVisible(false);
    this.panelNewRapport.setVisible(false);
  }
}
