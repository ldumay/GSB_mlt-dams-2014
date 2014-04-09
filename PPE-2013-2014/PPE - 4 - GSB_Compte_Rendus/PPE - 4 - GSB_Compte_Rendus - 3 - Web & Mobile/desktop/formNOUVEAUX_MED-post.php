<?php
/** 
 * Page d'accueil de l'application web AppliFrais
 * @package default
 * @todo  RAS
 */
  $repInclude = './include/';
  require($repInclude . "_init.inc.php");

  $refeMed = $_POST['MED_DEPOTLEGAL'];
  $commMed = $_POST['MED_NOMCOMMERCIAL'];
  $codeMed = $_POST['FAM_CODE'];
  $compMed = $_POST['MED_COMPOSITION'];
  $effeMed = $_POST['MED_EFFETS'];
  $contMed = $_POST['MED_CONTREINDIC'];
  $prixMed = $_POST['MED_PRIXECHANTILLON'];

  /*  
  echo $refeMed;
  echo '<br />';
  echo $commMed;
  echo '<br />';
  echo $codeMed;
  echo '<br />';
  echo $compMed;
  echo '<br />';
  echo $effeMed;
  echo '<br />';
  echo $contMed;
  echo '<br />';
  echo $prixMed;
  */
  
  

  $req = $bdd->prepare('INSERT INTO medicament (MED_DEPOTLEGAL,MED_NOMCOMMERCIAL,FAM_CODE,MED_COMPOSITION,MED_EFFETS,MED_CONTREINDIC,MED_PRIXECHANTILLON) VALUES (:MED_DEPOTLEGAL,:MED_NOMCOMMERCIAL,:FAM_CODE,:MED_COMPOSITION,:MED_EFFETS,:MED_CONTREINDIC,:MED_PRIXECHANTILLON)');

  // echo '<pre>'.var_dump($req).'</pre>';

  $req->execute(array('MED_DEPOTLEGAL' => $refeMed,'MED_NOMCOMMERCIAL' => $commMed,'FAM_CODE' => $codeMed,'MED_COMPOSITION' => $compMed,'MED_EFFETS' => $effeMed,'MED_CONTREINDIC' => $contMed,'MED_PRIXECHANTILLON' => $prixMed));      

  // echo '<pre>'.var_dump($req).'</pre>';
  $_SESSION['User_Ajout'] = 'Ajout Réussit';
  header('location: formNOUVEAUX_MED.php');
?>