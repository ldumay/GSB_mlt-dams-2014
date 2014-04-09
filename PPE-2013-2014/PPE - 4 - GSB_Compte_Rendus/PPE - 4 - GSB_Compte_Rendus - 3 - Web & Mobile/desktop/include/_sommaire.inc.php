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

      
    /*    
    if (estConnecte() ) //si la personne est connecté 
      {
        //recuperation des divers info correspondant à l'utilisateur
          $obtenirDetail= "obtenirDetail".$_SESSION["typeUser"];
          $idUser = obtenirIdUserConnecte() ;
          $lgUser = $obtenirDetail($idConnexion, $idUser);
          $nom = $lgUser['nom'];
          $prenom = $lgUser['prenom'];
  
        //affiche le nom et prénom de la personne ainsi que sa fonction
        echo "<h2>".$nom . " " . $prenom . "</h2><h3>".$_SESSION["typeUser"]." médical</h3>";     
  
       }
    */
    ?>  
  </div>
  <br />
  <ul id="menuList">
    <li class="smenu">
      <a href="cAccueil.php" title="Page d'accueil">Accueil</a>
    </li>
    <br />

    <h3>Côter Visiteur</h3>
    <hr />
    <h3>Comptes-Rendus</h3>
    <li class="smenu">
      <a href="formRAPPORT_VISITE.php" >Rapports de visite</a>
    </li>
    <h3>Consulter</h3>
    <li class="smenu">
      <a href="formMEDICAMENT.php" >Médicaments</a>
    </li>
    <li class="smenu">
      <a href="formPRATICIEN.php" >Praticiens</a>
    </li>
    <li class="smenu">
      <a href="formVISITEUR.php" >Autres visiteurs</a>
    </li>
    <br />

    <h3>Côter Practicien</h3>
    <hr />
    <li class="smenu">
      <a href="formNEW-RAPPORT.php" >Ajout de rapport</a>
    </li>
    <li class="smenu">
      <a href="formNOUVEAUX.php" >Ajouter un practicien</a>
    </li>
    <li class="smenu">
      <a href="formNOUVEAUX_MED.php" >Ajouter un médicament</a>
    </li>

    <li class="smenu">
      <h3><a href="cDeconnexion.php" >Déconnexion</a></h3>
    </li>

  </ul>
</div>