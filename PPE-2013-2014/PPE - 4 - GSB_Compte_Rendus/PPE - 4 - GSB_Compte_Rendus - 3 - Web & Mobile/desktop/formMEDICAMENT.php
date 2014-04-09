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
  $maxTmp = $bdd->query("SELECT count(MED_DEPOTLEGAL) as result FROM medicament");
  $max = $maxTmp-> fetch();
  $_SESSION['User_MedListMax'] = $max['result'];
  // Vérfification des bouton suivant ou retour qui permettes de déclencher un avancement 
  // ou un recule dans la liste des médicaments
  // retour
  if(isset($_POST['supp'])){
    if($_SESSION['User_MedList'] > 1){
      $_SESSION['User_MedList']--;
    }
  }
  // suivant
  if(isset($_POST['add'])){
    if($_SESSION['User_MedList'] < $_SESSION['User_MedListMax']){
      $_SESSION['User_MedList']++;
    }
  }
  // Affectation de ce déplacement de liste
  $i = $_SESSION['User_MedList'];
  // echo 'i : '.$i.'<br />';

  // Création de la liste de navigation des médicaments
  $x = 0;
  $DepotLegal = $bdd->query("SELECT MED_DEPOTLEGAL FROM medicament ORDER BY MED_DEPOTLEGAL");
  while($AffDepotLegal = $DepotLegal -> fetch()){
    $x++;
    $InsertDepotLegal = $x.'-'.$AffDepotLegal['MED_DEPOTLEGAL'];
    // echo $InsertDepotLegal.'<br />';
    $h = strstr($InsertDepotLegal, '-', true);
    // Vérification du déplacement dans la liste des médicaments vers le médicaments souhaité
    if($h == $i){
      // récupération de l'ID du médicaments chercher
      $tempDpt = strstr($InsertDepotLegal, '-');
      // Nettoyage
      $resultDepotLegal = str_replace('-', '', $tempDpt);
      // echo 'i ==> '.$resultDepotLegal;
      // echo '<br />';
    }
  }
  // Affecte le résultat de la recherche du dépot en temps d'ID du dépot qu'on cherche
  $MedDepotLegal = $resultDepotLegal;
  // Récupération des informations du médicaments trouvé pour affichage
  $medicament = $bdd->query("SELECT * FROM medicament WHERE MED_DEPOTLEGAL = '".$MedDepotLegal."' ORDER BY MED_DEPOTLEGAL");
  $resultMedi = $medicament -> fetch();

  
  $depot = utf8_encode($resultMedi['MED_DEPOTLEGAL']);
  $num = utf8_encode($resultMedi['MED_NOMCOMMERCIAL']);
  $code = utf8_encode($resultMedi['FAM_CODE']);
  $compo = utf8_encode($resultMedi['MED_COMPOSITION']);
  $effets = utf8_encode($resultMedi['MED_EFFETS']);
  $indic = utf8_encode($resultMedi['MED_CONTREINDIC']);
?>

<!-- Contenu de Page -->

<div id="contenu">
  <h2>Medicaments - Pharmacope</h2>
  <form name="formMEDICAMENT" method="post" action="formMEDICAMENT.php">
    <label class="titre">DEPOT LEGAL :</label>
      <input type="text" class="zone" size="16" name="MED_DEPOTLEGAL" value="<?php echo $depot; ?>"/>
    <br />
    <label class="titre">NOM COMMERCIAL :</label>
      <input type="text" class="zone" size="25" name="MED_NOMCOMMERCIAL" value="<?php echo $num; ?>"/>
    <br />
    <label class="titre">FAMILLE :</label>
      <input type="text" class="zone" size="3" name="FAM_CODE" value="<?php echo $code; ?>"/>
    <br />
    <label class="titre">COMPOSITION :</label><br />
      <textarea rows="5" class="zone" cols="70" name="MED_COMPOSITION" ><?php echo $compo; ?></textarea>
    <br />
    <label class="titre">EFFETS :</label><br />
      <textarea rows="5" class="zone" cols="70" name="MED_EFFETS" ><?php echo $effets; ?></textarea>
    <br />
    <label class="titre">CONTRE INDIC. :</label><br />
      <textarea rows="5" class="zone" cols="70" name="MED_CONTREINDIC" ><?php echo $indic; ?></textarea>
    <br />
    <label class="titre">PRIX ECHANTILLON :</label>
    <?php 
      if(!isset($resultMedi['MED_PRIXECHANTILLON']))
        {$resultMedi['MED_PRIXECHANTILLON']=='0.00';}
      $Price = number_format($resultMedi['MED_PRIXECHANTILLON'], 2, ',', ' ');
      $Price = $Price.' €';
    ?>
      <input type="text" class="zone" size="12" name="MED_PRIXECHANTILLON" value="<?php echo $Price; ?>"/>
    <br />
    <label class="titre"><!-- &nbsp; --></label>
      <input class="zone" type="submit" name="supp" value="<"></input>
      <?php echo $i.'/'.$_SESSION['User_MedListMax']; ?>
      <input class="zone" type="submit" name="add" value=">"></input>
  </form>
</div>

<!-- Fin de Page -->
<?php        
  require($repInclude . "_pied.inc.html");
?>
