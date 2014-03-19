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
    ?>  
       </div>  
<?php    
//si la personne est connecté  
if (estConnecte()){

// si la fonction du connecter est visiteur 
  if ($_SESSION["typeUser"]== "visiteur")  
  {
//afficher le menu
?>
        <ul id="menuList">
           <li class="smenu">
              <a href="cAccueil.php" title="Page d'accueil">Accueil</a>
           </li><br />
           <li class="smenu">
              <a href="cSaisieFicheFrais.php" title="Saisie fiche de frais du mois courant">Saisie fiche de frais</a>
           </li>
           <li class="smenu">
              <a href="cConsultFichesFrais.php" title="Consultation de mes fiches de frais">Mes fiches de frais</a>
           </li><br />
           <li class="smenu">
              <a href="cSeDeconnecter.php" title="Se déconnecter">Se déconnecter</a>
           </li>
         </ul>
        <?php
          
  }
  else{
    //sinon si la fonction du connecter est comptable 
    if ((estConnecte()) & ($_SESSION["typeUser"]== "comptable") ){
        //afficher le menu
        ?>
          <ul id="menuList">
           <li class="smenu">
              <a href="cAccueil.php" title="Page d'accueil">Accueil</a>
           </li>
           <li class="smenu">
              <a href="ConsultFrais.php" title="Saisie fiche de frais du mois courant">Consultation de frais</a>
           </li>
           <li class="smenu">
              <a href="ValidFrais.php" title="Consultation de mes fiches de frais">Validtion fiches de frais</a>
           </li><br/>
           <li class="smenu">
              <a href="cSeDeconnecter.php" title="Se déconnecter">Se déconnecter</a>
           </li>
         </ul>

<?php  
      }}}
?>    </div>