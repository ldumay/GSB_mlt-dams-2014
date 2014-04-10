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

<style type="text/css">
  /* Active le masquage
  #PRA_NUM{
    display:none;
  }
  */
</style>

<!-- Contenu de Page -->

<div id="contenu">
  <h2>Nouveau rapport de visite</h2>
  <p>Création d'un nouveau rapport de visite pour le : <strong><?php $date = date("d-m-Y à H:i:s"); echo $date; ?></strong>.
  <?php
    // Liste des Visiteurs
    $PractInfos = $bdd->query("SELECT PRA_NOM,PRA_PRENOM FROM praticien WHERE PRA_NUM='".$_SESSION["User_idPracticien"]."'");
    while($PractInfo = $PractInfos -> fetch())
    {
      $Auteur = utf8_encode ($PractInfo['PRA_NOM'].' '.$PractInfo['PRA_PRENOM']);
    }
  ?>
  <br />Par : <strong><?php echo $Auteur; ?></strong>.</p>
    <?php 
      if($_SESSION['User_Ajout']!=''){
        echo '<i style="color:green;">'.$_SESSION['User_Ajout'].'</i>';
        session_destroy();
      }
    ?>
    <form name="formRAPPORT_VISITE" method="post" action="formNEW-RAPPORT-post.php">
      <hr />
      <label>Liste des visiteurs : </label>
      <select id="VIS_MATRICULE" name="VIS_MATRICULE" class="zone">
      <?php
        // Liste des Visiteurs
        $visiteur = $bdd->query("SELECT VIS_MATRICULE,VIS_NOM FROM visiteur ORDER BY VIS_NOM");
        echo '<option value="0" selected>Visiteurs</option>';
        while($resultVis = $visiteur -> fetch())
        {
          if(!isset($resultVis['VIS_VILLE'])){$resultVis['VIS_VILLE']='Sans Nom';};
          $matricule = utf8_encode ($resultVis['VIS_MATRICULE']);
          $nom = utf8_encode ($resultVis['VIS_NOM']);
          echo '<option value="'.$matricule.'">'.$nom.'</option>';
        }
      ?>
      </select>
      <hr />
      <label class="titre">AUTEUR :</label>
      <i style="color:red;">A masquer</i>
      <input type="text" id="PRA_NUM" name="PRA_NUM" value="<?php echo $_SESSION['User_idPracticien']; ?>"/>
      <hr />
      <label class="titre">BILAN :</label>
      <br /><textarea rows="5" cols="50" name="RAP_BILAN" class="zone"></textarea>
      <br />
      <label class="titre">MOTIF :</label>
      <br /><textarea rows="5" cols="50" name="RAP_MOTIF" class="zone"></textarea>
      <br />
      <input type="reset" value="Annuler"/>
      <input type="submit" value="valider"/>
    </form>
</div>

<!-- Fin de Page -->
<?php        
  require($repInclude . "_pied.inc.html");
?>