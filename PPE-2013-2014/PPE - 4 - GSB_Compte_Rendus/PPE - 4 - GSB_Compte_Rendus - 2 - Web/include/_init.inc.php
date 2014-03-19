<?php
# ======================================================== #
session_start();
# ======================================================== #
require("_bdGestionDonnees.lib.php");
require("_GestionBDD-verifBDD.inc.php");
# ======================================================== #
if(empty($_SESSION['id_Prat'])){
	$_SESSION['id_Prat'] = '';
}
if(isset($_POST['PratAction'])){
	$id_Prat = $_POST['id_Prat'];
	$_SESSION['id_Prat'] = $id_Prat;
}
# ======================================================== #
function verifConnexion(){
	// page inaccessible si visiteur non connecté
	if (empty($_SESSION['User_idVisiteur'])){
		header("Location: cSeConnecter.php");  
	}
}
?>