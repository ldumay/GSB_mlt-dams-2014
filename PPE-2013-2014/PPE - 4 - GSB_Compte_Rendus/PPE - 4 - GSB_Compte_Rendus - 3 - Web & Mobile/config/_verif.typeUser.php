<?php
	// Vérification Visiteur : 
  $TmpTypeUser1 = $bdd->query("SELECT VIS_NOM,SEC_CODE FROM visiteur WHERE VIS_NOM='".$login."'");
  
  while($TypeUser1 = $TmpTypeUser1-> fetch()){
    
    $tmpLoginType1 = $TypeUser1['VIS_NOM'];

    if(!empty($TypeUser1['SEC_CODE'])){
      $UserType = 'Délégué';
    }
    else{
      $UserType = 'Visiteur';
    }
  }

  // Vérification Praticien :
  $TmpTypeUser2 = $bdd->query("SELECT PRA_NOM FROM praticien");
  while($TypeUser2 = $TmpTypeUser2-> fetch()){
    $tmpLoginType2 = $TypeUser2['PRA_NOM'];
    if($login==$tmpLoginType2){
      $UserType = 'Praticien';
    }
  }

?>