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
  $(document).ready(function(){
    alert("Bienvenue sur GSB, espace Compte Rendu.\n(Merci de vérifié l'entête avant de valider la fin du projet.)\n\n==> idPracticien");
  });
</script>

<!-- Contenu de Page -->

<div id="contenu">
  <h2>Bienvenue</h2>
  <br />
  <h3>A faire :</h3>
  <p>
  <br />- Vérifier la notion de visiteur ==> "Visteur interne ou externe ?"
  <br />
  <br />- Optimisation des pages practicien et médicaments : créé une page type qui fait appelle une page php pour charger les informations voulus.
  <br />
  <br />- Après la création d'un practicien, afficher ses informations dans la page "practiciens" en utilisant son ID (IDEM : pour l'ajout d'un médicament).
  <br />
  <br />- Premettre la modification d'un practicien dans la page "Practicien".
  <br />
  <br />- <strong style="color:red;">Vérifié si la BDD => médicaments pour le prix est bien en : 'float' & 'NOT NULL' ; sur la VM pour une nouvelle sauvegarde.</strong>
  <br />
  <br />- Ajouter des messages d'erreurs sur chaque page.
  <br />
  <br />A la fin => <strong style="color:red;">Vérification, Nettoyage et Optimisation du code.</strong>
  </p>
</div>

<!-- Fin de Page -->
<?php        
  require($repInclude . "_pied.inc.html");
?>
