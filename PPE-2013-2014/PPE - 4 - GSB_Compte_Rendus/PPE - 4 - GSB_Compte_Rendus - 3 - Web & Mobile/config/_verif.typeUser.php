<?php

  // Récupération du l'id de l'utilisateur
  $TmpIDUser = $bdd->query("SELECT VIS_MATRICULE,VIS_NOM FROM visiteur WHERE VIS_NOM='".$login."'");
  while($IDUser = $TmpIDUser-> fetch()){
    $IDTmpUser = $IDUser['VIS_MATRICULE'];
  }
  // Récupération du type de l'utilisateur
  $TmpTypeUser = $bdd->query("SELECT VIS_MATRICULE,JJMMAA,TRA_ROLE FROM travailler WHERE JJMMAA=(SELECT max(JJMMAA) FROM travailler WHERE VIS_MATRICULE='".$IDTmpUser."') AND VIS_MATRICULE='".$IDTmpUser."'");
  while($TypeUser = $TmpTypeUser-> fetch()){
    $UserType = utf8_encode($TypeUser['TRA_ROLE']);
  }

?>