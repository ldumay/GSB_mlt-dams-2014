<?php
	// Vérification Visiteur : 
  $TmpTypeUser1 = $bdd->query("SELECT VIS_NOM FROM visiteur");
  
  while($TypeUser1 = $TmpTypeUser1-> fetch()){
    
    $tmpLoginType1 = $TypeUser1['VIS_NOM'];
    
    if( ($login==$tmpLoginType1) && ($TypeUser1['SEC_CODE']=='NULL') ){
      $UserType = 'Visiteur';
    }

    if( ($login==$tmpLoginType1) && ($TypeUser1['SEC_CODE']!='NULL') ){
      $UserType = 'Délégué';
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