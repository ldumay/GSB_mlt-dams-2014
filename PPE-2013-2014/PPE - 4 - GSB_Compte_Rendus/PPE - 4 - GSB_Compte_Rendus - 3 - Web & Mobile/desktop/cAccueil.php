<?php
/** 
 * Page d'accueil de l'application web AppliFrais
 * @package default
 * @todo  RAS
 */
  $repInclude = './include/';
  require($repInclude . "_init.inc.php");

  verifConnexion();

  // Début de Page
  require($repInclude . "_entete.inc.html");
  // Menu
	require($repInclude . "_sommaire.inc.php");
?>

<!-- Script -->
<script text="text/javascript">
  /*
  $(document).ready(function(){
    alert("Bienvenue sur GSB, espace Compte Rendu.\n(Merci de vérifié l'entête avant de valider la fin du projet.)\n\n==> idPracticien");
  });
*/
</script>

<?php
  $nom = $_SESSION['User_nom'];
  $prenom = $_SESSION['User_prenom'];
  /*
  $type = $_SESSION['User_type'];
  $adresse = $_SESSION['User_adresse'];
  $cp = $_SESSION['User_cp'];
  $ville = $_SESSION['User_ville'];
  $dateEmbauche = $_SESSION['User_dateEmbauche'];
  $secCode = $_SESSION['User_Sec-Code'];
  $labCode = $_SESSION['User_Lab-Code'];
  */
?>

<!-- Contenu de Page -->
<div id="contenu">
  <h2>Bienvenue <?php echo $nom.' '.$prenom.''; ?></h2>
  <p>
    <h3>Zone de notification</h3>
    <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    <br /><br /><br /><br /><br /><br /><br /><br /><br />
  </p>
</div>

<!-- Fin de Page -->
<?php        
  require($repInclude . "_pied.inc.html");
?>
