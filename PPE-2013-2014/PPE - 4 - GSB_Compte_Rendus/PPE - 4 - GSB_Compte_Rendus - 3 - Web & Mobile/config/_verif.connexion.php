<?php
# ========================================================================== #
  # [ Vérification de connexion ] #
  # ========================================================================== #

    // récupération des information de connexion
    $login = $_POST['txtLogin'];
    $mdp = $_POST['txtMdp'];

    // echo $login.' <br /> '.$mdp;

    $erreur = "";
    // Vérification des erreurs de saisie 
    if( (empty($login)) && (empty($mdp)) ){
      $_SESSION['Erreur_Log'] = "Le login et le mot de passe n'a passe été saisie.";
      header('location: '.$ReturnConnexion.'.php');
    }
    else if(empty($login)){
      $_SESSION['Erreur_Log'] = "Le login n'a pas été saisie.";
      header('location: '.$ReturnConnexion.'.php');
    }
    else if(empty($mdp)){
      $_SESSION['Erreur_Log'] = "Le mot de passe n'a pas été saisie.";
      header('location: '.$ReturnConnexion.'.php');
    }
    else{
      // Vérification du type d'utlisateur
        require("_verif.typeUser.php");
        // Vérification de la création de donnée type : 
        if(empty($UserType)){
          $_SESSION['Erreur_Log'] = "Impossible de trouvé votre niveau hiérarchique.";
          header('location: '.$ReturnConnexion.'.php');
        }
      // echo '<br />Type d\'utlisateur : '.$UserType;

      // récupération des données du client dans la BDD
      $TmpDonnees = $bdd->query("SELECT * FROM visiteur WHERE VIS_NOM='".$login."'");
      while($Donnees = $TmpDonnees-> fetch()){
        // mémorisation des données de l'utilisateur en session

        // cryptage mot de passe
        $date = $Donnees["VIS_DATEEMBAUCHE"];
        
        // echo '<br />'.$date.'<br />';

        // Formatage de la date
        $annee = substr($date, 0, 4);
        $mois = substr($date, 5, 2);
        $jour = substr($date, 8, 2);
        // echo $annee.' - ';
        // echo $mois.' - ';
        // echo $jour.'<br />';


        // Vérification du mois
        switch($mois){
          case "01" :   $mois = "jan";  break;
          case "02" :   $mois = "feb";  break;
          case "03" :   $mois = "mar";  break;
          case "04" :   $mois = "apr";  break;
          case "05" :   $mois = "may";  break;
          case "06" :   $mois = "jun";  break;
          case "07" :   $mois = "jul";  break;
          case "08" :   $mois = "aug";  break;
          case "09" :   $mois = "sep";  break;
          case "10" :   $mois = "oct";  break;
          case "11" :   $mois = "nov";  break;
          case "12" :   $mois = "dec";  break;
          default : $mois = "erreur";  break;
        }
        $annee = substr($annee, 2, 2);
        $date = $jour.'-'.$mois.'-'.$annee;
        // echo 'Date crypte : '.$date;
        // echo '<br />Mdp : '.$mdp;
      }
      if(isset($date)){
        if($date==$mdp){
          $_SESSION['User_actif']=true;
          $_SESSION['User_Connexion'] = true;
          header('location: '.$ReturnHome.'.php');
        }
        else{
          $_SESSION['Erreur_Log'] = "Le mot de passe est incorrect";
          header('location: '.$ReturnConnexion.'.php');
        }
      }
      else{
        $_SESSION['Erreur_Log'] = "Le mot de passe est incorrect ou erreur dans la BDD.";
        header('location: '.$ReturnConnexion.'.php');
      }
    }

    // echo '<br /> SESSION : '.$_SESSION['Erreur_Log'];
?>