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
			if(!isset($_SESSION['User_type'])){
				$_SESSION['User_type']='';
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
?>