<?php
/** 
 * Page d'accueil de l'application web AppliFrais
 * @package default
 * @todo  RAS
 */
  $repInclude = './include/';
  require($repInclude . "_init.inc.php");

  $nom = $_POST['PRA_NOM'];
  $prenom = $_POST['PRA_PRENOM'];
  $adresse = $_POST['PRA_ADRESSE'];
  $cp = $_POST['PRA_CP'];
  $ville = $_POST['PRA_VILLE'];
  $coef = $_POST['PRA_COEFNOTORIETE'];
  $type = $_POST['TYP_CODE'];

  /*
  if(empty()){
  }
  else{
  }

  echo $nom;
  echo $prenom;
  echo $adresse;
  echo $cp;
  echo $ville;
  echo $coef;
  echo $type;
  */

  $req = $bdd->prepare('INSERT INTO praticien (PRA_NOM,PRA_PRENOM,PRA_ADRESSE,PRA_CP,PRA_VILLE,PRA_COEFNOTORIETE,TYP_CODE) VALUES (:PRA_NOM,:PRA_PRENOM,:PRA_ADRESSE,:PRA_CP,:PRA_VILLE,:PRA_COEFNOTORIETE,:TYP_CODE)');
  $req->execute(array('PRA_NOM' => $nom,'PRA_PRENOM' => $prenom,'PRA_ADRESSE' => $adresse,'PRA_CP' => $cp,'PRA_VILLE' => $ville,'PRA_COEFNOTORIETE' => $coef,'TYP_CODE' => $type));      

  $_SESSION['User_Ajout'] = 'Ajout Réussit';
  header('location: formNOUVEAUX.php');
?>