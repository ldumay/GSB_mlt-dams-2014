<?php
/** 
 * Page d'accueil de l'application web AppliFrais
 * @package default
 * @todo  RAS
 */
  $repInclude = './include/';
  require($repInclude . "_init.inc.php");

  $vist = $_POST['VIS_MATRICULE'];
  $pract = $_POST['PRA_NUM'];
  $date = date("Y-m-d H:i:s");
  $bilan = $_POST['RAP_BILAN'];
  $motif = $_POST['RAP_MOTIF'];

  $req = $bdd->prepare('INSERT INTO rapport_visite (PRA_NUM,VIS_MATRICULE,RAP_DATE,RAP_BILAN,RAP_MOTIF) VALUES (:PRA_NUM,:VIS_MATRICULE,:RAP_DATE,:RAP_BILAN,:RAP_MOTIF)');
  $req->execute(array('PRA_NUM' => $pract,'VIS_MATRICULE' => $vist,'RAP_DATE' => $date,'RAP_BILAN' => $bilan,'RAP_MOTIF' => $motif));

  $_SESSION['User_Ajout'] = 'Ajout Réussit';
  header('location: formNEW-RAPPORT.php');
?>