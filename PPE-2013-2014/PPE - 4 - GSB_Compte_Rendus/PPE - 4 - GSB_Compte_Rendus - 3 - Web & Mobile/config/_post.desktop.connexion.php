<?php 
  require("_init.inc.php");

  # ========================================================================== #
  # [ Adresses de redirections : ] #
  # ========================================================================== #
    // Redirection à la page de connexion :
    $ReturnConnexion = '../'.$dirDesktop.'/cConnexion';

    // Redirection à la page d'accueil :
    $ReturnHome = '../'.$dirDesktop.'/cAccueil';

  require("_verif.connexion.php");
?>