<?php
	// Coordonnée d'un compte en SESSION
		// Vérification d'une activité SESSION
			if(!isset($_SESSION['User_actif'])){
				$_SESSION['User_actif']=false;
			}
		// Vérification des identifiants
			if(!isset($_SESSION['User_id'])){
				$_SESSION['User_id']='';
			}
			if(!isset($_SESSION['User_login'])){
				$_SESSION['User_login']='';
			}
			if(!isset($_SESSION['User_mdp'])){
				$_SESSION['User_mdp']='';
			}
			if(!isset($_SESSION['User_type'])){
				$_SESSION['User_type']='';
			}
			if(!isset($_SESSION['User_grade'])){
				$_SESSION['User_grade']='';
			}

		// = Pour PPE = Start
			if(!isset($_SESSION['User_idVisiteur'])){
				$_SESSION['User_idVisiteur'] = '';
			}
			if(!isset($_SESSION['User_idPracticien'])){
				$_SESSION['User_idPracticien'] = '';
			}
			if(!isset($_SESSION['User_MedList'])){
				$_SESSION['User_MedList'] = 1;
			}
			if(!isset($_SESSION['User_MedListMax'])){
				$_SESSION['User_MedListMax'] = 0;
			}
			if(!isset($_SESSION['User_PratList'])){
				$_SESSION['User_PratList'] = 1;
			}
			if(!isset($_SESSION['User_PratListMax'])){
				$_SESSION['User_PratListMax'] = 0;
			}
			if(!isset($_SESSION['User_VisList'])){
				$_SESSION['User_VisList'] = 1;
			}
			if(!isset($_SESSION['User_VisListMax'])){
				$_SESSION['User_VisListMax'] = 0;
			}
			if(!isset($_SESSION['User_VisDept'])){
				$_SESSION['User_VisDept'] = '';
			}
			if(!isset($_SESSION['User_Ajout'])){
				$_SESSION['User_Ajout'] = '';
			}
		// = Pour PPE = End

		// Vérification des coordonnées d'un utilisateurs connecté
			if(!isset($_SESSION["User_Nom"])){
				$_SESSION["User_Nom"]='';
			}
			if(!isset($_SESSION["User_Prenom"])){
				$_SESSION["User_Prenom"]='';
			}
			if(!isset($_SESSION["User_Age"])){
				$_SESSION["User_Age"]='';
			}
			if(!isset($_SESSION["User_Email"])){
				$_SESSION["User_Email"]='';
			}
			if(!isset($_SESSION["User_Adresse"])){
				$_SESSION["User_Adresse"]='';
			}
			if(!isset($_SESSION["User_Ville"])){
				$_SESSION["User_Ville"]='';
			}
			if(!isset($_SESSION["User_CP"])){
				$_SESSION["User_CP"]='';
			}
			if(!isset($_SESSION["User_Pays"])){
				$_SESSION["User_Pays"]='';
			}
			if(!isset($_SESSION["User_Date"])){
				$_SESSION["User_Date"]='';
			}
			if(!isset($_SESSION["User_Time"])){
				$_SESSION["User_Time"]='';
			}
			if(!isset($_SESSION["User_CraftPoint"])){
				$_SESSION["User_CraftPoint"]='';
			}
			if(!isset($_SESSION["User_TtlConnexion"])){
				$_SESSION["User_TtlConnexion"]='';
			}
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