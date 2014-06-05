<?php
/** 
 * Contient la division pour le sommaire, sujet à des variations suivant la 
 * connexion ou non d'un utilisateur, et dans l'avenir, suivant le type de cet utilisateur 
 * @todo  RAS
 */

?>
<!-- Division pour le sommaire -->
<div id="menuGauche">
  <div id="infosUtil">
    <?php 
      if ($_SESSION['User_actif']==true){
      //recuperation des divers info correspondant à l'utilisateur
        $id = $_SESSION['User_id'];
        $login = $_SESSION['User_login'];
        $type = $_SESSION['User_type'];

        //affiche le nom et prénom de la personne ainsi que sa fonction
        echo "<h2>Bonjour, " . $login . "</h2><h3>".$type." médical</h3>";     
      }
    ?>  
  </div>

  <br /></br />

  <ul id="menuList">
    <li class="smenu">
      <a href="cAccueil.php" title="Page d'accueil">Accueil</a>
    </li>

    <h3>Comptes-Rendus</h3>
    <li class="smenu">
      <a href="formRAPPORT_VISITE.php">Rapports de visite</a>
    </li>
    <li class="smenu">
      <a href="formNEW-RAPPORT.php">Nouveau rapport</a>
    </li>
    <h3>Consulter</h3>
    <li class="smenu">
      <a href="formMEDICAMENT.php">Médicaments</a>
    </li>
    <li class="smenu">
      <a href="formSearchMEDICAMENT.php">Recherche de Médicaments</a>
    </li>
    <li class="smenu">
      <a href="formPRATICIEN.php">Praticiens</a>
    </li>
    <li class="smenu">
      <a href="formVISITEUR.php">Autres visiteurs</a>
    </li>
    <br />

    <?php
      if( ($type=='Délégué') || ($type=='Responsable') ){
        echo'
        <li class="smenu">
          <a href="formNOUVEAUX_MED.php">Nouveau médicament</a>
        </li>
        <li class="smenu">
          <a href="formNOUVEAUX.php">Nouveau practicien</a>
        </li>
        ';
      }
    ?>

    <h3> 
      <a href="cDeconnexion.php">Déconnexion</a>
    </h3>
  </ul>
</div>