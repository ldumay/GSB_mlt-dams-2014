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

  // vérifiation de compte connecter
  $vist = $_SESSION['User_id'];
  $pract = $_SESSION['User_idPracticien'];
?>

<style type="text/css">
  /* Active l'invisibilité de l'utilisateur connecté pour l'insertion */
  #VIS_MATRICULE, .titre{
    display:none;
  }
</style>

<!-- Contenu de Page -->

<div id="contenu">
  <h2>Nouveau rapport de visite</h2>
  <p>Création d'un nouveau rapport de visite pour le : <strong><?php $date = date("d-m-Y à H:i:s"); echo $date; ?></strong>.
  <?php
    $PractInfos = $bdd->query("SELECT VIS_NOM,VIS_PRENOM FROM visiteur WHERE VIS_MATRICULE='".$vist."'");
    while($PractInfo = $PractInfos -> fetch())
    {
      $Auteur = utf8_encode ($PractInfo['VIS_NOM'].' '.$PractInfo['VIS_PRENOM']);
    }
  ?>
  <br />Par : <strong><?php echo $Auteur; ?></strong>.</p>
    <?php 
      if($_SESSION['User_Ajout']!=''){
        echo '<i style="color:green;">'.$_SESSION['User_Ajout'].'</i>';
        $_SESSION['User_Ajout']='';
      }
    ?>
    <form name="formRAPPORT_VISITE" method="post" action="formNEW-RAPPORT-post.php">
      <hr />
      <label>Liste des visiteurs : </label>
      <select id="PRA_NUM" name="PRA_NUM" class="zone">
      <?php
        // Liste des Visiteurs
        $visiteur = $bdd->query("SELECT PRA_NUM,PRA_NOM,PRA_PRENOM,PRA_VILLE FROM praticien ORDER BY PRA_NOM");
        echo '<option value="0" selected>Praticiens</option>';
        while($resultVis = $visiteur -> fetch())
        {
          if(!isset($resultVis['PRA_VILLE'])){$resultVis['PRA_VILLE']='Sans Nom';};
          $matriculePrat = utf8_encode ($resultVis['PRA_NUM']);
          $nom = utf8_encode ($resultVis['PRA_NOM']);
          echo '<option value="'.$matriculePrat.'">'.$nom.'</option>';
        }
      ?>
      </select>
      <hr />
      <label class="titre">AUTEUR :</label>
      <input type="text" id="VIS_MATRICULE" name="VIS_MATRICULE" value="<?php echo $vist; ?>"/>
      
      <hr class="titre"/>
      <label class="titre">BILAN :</label>
      <br  class="titre"/><textarea rows="5" cols="50" name="RAP_BILAN" class="zone"></textarea>
      
      <br />
      <label class="titre">MOTIF :</label>
      <br /><textarea rows="5" cols="50" name="RAP_MOTIF" class="zone"></textarea>
      <br />
      <br />
      <input type="reset" value="Annuler"/>
      <input type="submit" value="valider"/>
    </form>
</div>

<!-- Fin de Page -->
<?php        
  require($repInclude . "_pied.inc.html");
?>