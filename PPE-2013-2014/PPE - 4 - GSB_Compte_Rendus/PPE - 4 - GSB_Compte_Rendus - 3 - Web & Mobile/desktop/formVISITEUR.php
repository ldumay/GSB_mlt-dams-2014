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

  // Création d'un maximum de lecture pour la liste des médicaments
  $maxTmp = $bdd->query("SELECT count(VIS_MATRICULE) as result FROM visiteur");
  $max = $maxTmp-> fetch();
  $_SESSION['User_VisListMax'] = $max['result'];
  // Vérfification des bouton suivant ou retour qui permettes de déclencher un avancement 
  // ou un recule dans la liste des médicaments
  // retour
  if(isset($_POST['supp'])){
    if($_SESSION['User_VisList'] > 1){
      $_SESSION['User_VisList']--;
    }
  }
  // suivant
  if(isset($_POST['add'])){
    if($_SESSION['User_VisList'] < $_SESSION['User_VisListMax']){
      $_SESSION['User_VisList']++;
    }
  }
  // ===
  if(isset($_POST['lstDept'])){
    $_SESSION['User_VisDept'] = $_POST['lstDept'];
  }
  if(isset($_POST['lstVisiteur'])){
    $_SESSION['User_VisList'] = $_POST['lstVisiteur'];
  }
  // Affectation de ce déplacement de liste
  $i = $_SESSION['User_VisList'];
  // echo 'i : '.$i.'<br />';

  // Création de la liste de navigation des médicaments
  $x = 0;
  $j = 0;
  // echo $_SESSION['User_VisList'].'<br />';
  $VisNum = $bdd->query("SELECT VIS_MATRICULE FROM visiteur ORDER BY VIS_MATRICULE");
  while($AffVisNum = $VisNum -> fetch()){
    $x++;
    $InsertVisNum = $x.'-'.$AffVisNum['VIS_MATRICULE'];
    // echo $InsertDepotLegal.'<br />';
    $h = strstr($InsertVisNum, '-', true);
    // Vérification du déplacement dans la liste des médicaments vers le médicaments souhaité
    if($h == $i){
      // récupération de l'ID du médicaments chercher
      $tempDpt = strstr($InsertVisNum, '-');
      // Nettoyage
      $Vis_Matricule = str_replace('-', '', $tempDpt);
    }
    if($i == $AffVisNum['VIS_MATRICULE']){
      $Vis_Matricule = $i;
      $j = $h;
    }
    // echo 'i ==> '.$i.' - h ==> '.$h.' - j ==> '.$j.' - Vis ==>'.$Vis_Matricule.' - Num ==>'.$AffVisNum['VIS_MATRICULE'];
    // echo '<br />';
  }

  if(empty($Vis_Matricule)){
    $Vis_Matricule = $_SESSION['User_VisList'];
  }

  if( ($i >= 1) && ($i <= $_SESSION['User_VisListMax']) ){
    $num = $i;
  }
  if( ($j >= 1) && ($j <= $_SESSION['User_VisListMax']) ){
    $num = $j;
  }

  if(!empty($num)){
    $_SESSION['User_VisList'] = $num;
  }
  else{
    $_SESSION['User_VisList'] = $i;
  }
?>

<!-- Contenu de Page -->

<div id="contenu">
  <h2>Autres Visiteur</h2>
  <form name="formVISITEUR" method="post" action="formVISITEUR.php">
    <select name="lstDept" class="titre">
      <?php
        // Liste des Département
        $departement = $bdd->query("SELECT VIS_VILLE FROM visiteur GROUP BY VIS_VILLE ORDER BY VIS_VILLE");
        echo '<option value="">Département</option>';
        while($resultDept = $departement -> fetch())
        {
          echo '<option value="'.$resultDept['VIS_VILLE'].'">'.$resultDept['VIS_VILLE'].'</option>';
        }
      ?>
    </select>
    <?php
      if($_SESSION['User_VisDept'] != ''){
        echo '<select name="lstVisiteur" class="zone">';
            // Liste des Visiteurs
            $visiteur = $bdd->query("SELECT VIS_MATRICULE,VIS_NOM FROM visiteur WHERE VIS_VILLE='".$_SESSION['User_VisDept']."' ORDER BY VIS_NOM");
            echo '<option value="">Visiteurs</option>';
            while($resultVis = $visiteur -> fetch())
            {
              echo '<option value="'.$resultVis['VIS_MATRICULE'].'">'.$resultVis['VIS_NOM'].'</option>';
            }
        echo '</select>';
      }
    ?>
    <input type='submit' name="search" value='Valider' />
    <br />

    <?php
      $donneesVis = $bdd->query("SELECT * FROM visiteur WHERE VIS_MATRICULE = '".$Vis_Matricule."'");
      $VisDonnees = $donneesVis -> fetch();

      $nom = utf8_encode($VisDonnees['VIS_NOM']);
      $prenom = utf8_encode($VisDonnees['VIS_PRENOM']);
      $adresse = utf8_encode($VisDonnees['VIS_ADRESSE']);
      $cp = utf8_encode($VisDonnees['VIS_CP']);
      $ville = utf8_encode($VisDonnees['VIS_VILLE']);
      $code = utf8_encode($VisDonnees['SEC_CODE']);
    ?>

    <br />
    <label class="titre">NOM :</label>
      <input type="text"class="zone"  size="25" name="VIS_NOM" value="<?php echo $nom; ?>"/>
    <br />
    <label class="titre">PRENOM :</label>
      <input type="text"class="zone"  size="50" name="Vis_PRENOM" value="<?php echo $prenom; ?>"/>
    <br />
    <label class="titre">ADRESSE :</label>
     <input type="text"class="zone"  size="50" name="VIS_ADRESSE" value="<?php echo $adresse; ?>"/>
    <br />
    <label class="titre">CP :</label>
      <input type="text"class="zone"  size="5" name="VIS_CP" value="<?php echo $cp; ?>"/>
    <br />
    <label class="titre">VILLE :</label>
      <input type="text"class="zone"  size="30" name="VIS_VILLE" value="<?php echo $ville; ?>"/>
    <br />
    <label class="titre">SECTEUR :</label>
      <input type="text"class="zone"  size="1" name="SEC_CODE" value="<?php echo $code; ?>"/>
    <br />
    <br />
    <label class="titre"><!-- &nbsp; --></label>
      <input class="zone" type="submit" name="supp" value="<"></input>
      <?php echo $_SESSION['User_VisList'].'/'.$_SESSION['User_VisListMax']; ?>
      <input class="zone" type="submit" name="add" value=">"></input>
  </form>
</div>

<!-- Fin de Page -->
<?php        
  require($repInclude . "_pied.inc.html");
?>