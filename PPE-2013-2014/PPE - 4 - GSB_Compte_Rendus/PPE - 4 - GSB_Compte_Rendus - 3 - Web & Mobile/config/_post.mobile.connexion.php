<?php 
  require("_init.inc.php");

  # ========================================================================== #
  # [ Adresses de redirections : ] #
  # ========================================================================== #
    // Redirection à la page de connexion :
    $ReturnConnexion = '../'.$dirMobile.'/cConnexion';

    // Redirection à la page d'accueil :
    $ReturnHome = '../'.$dirMobile.'/cAccueil';

  require("_verif.connexion.php");
?>