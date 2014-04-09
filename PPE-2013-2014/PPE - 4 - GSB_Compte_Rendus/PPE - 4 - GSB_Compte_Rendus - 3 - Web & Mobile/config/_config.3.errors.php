<?php
	// Vérificateur d'erreur
	if(!isset($_SESSION['User_BDD-error'])){
		$_SESSION['User_BDD-error']=false;
	}
	if(!isset($_SESSION['User_BDD-error-actif'])){
		$_SESSION['User_BDD-error-actif']=false;
	}
	if(!isset($_SESSION['User_BDD-error-message'])){
		$_SESSION['User_BDD-error-message']='';
	}
	if(!isset($_SESSION['User_identification'])){
		$_SESSION['User_identification']='';
	}
	if(!isset($_SESSION['User_register'])){
		$_SESSION['User_register']='';
	}
	if(!isset($_SESSION['error'])){
		$_SESSION['User_Mail_Error'] = '';
	}
	if(!isset($_SESSION['good'])){
		$_SESSION['User_Mail_OK'] = '';
	}
?>