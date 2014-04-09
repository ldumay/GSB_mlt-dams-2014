<?php
/** 
 * Page d'accueil de l'application web AppliFrais
 * @package default
 * @todo  RAS
 */
  $repInclude = './include/';
  require($repInclude . "_init.inc.php");

  verifConnexion();

  // Début de Page
  require($repInclude . "_entete.inc.html");
  // Menu
	require($repInclude . "_sommaire.inc.php");
?>

<!-- Contenu de Page -->

<div id="contenu">
  <h2>Nouveau Practicien</h2>
    <?php 
      if($_SESSION['User_Ajout']!=''){
        echo '<i style="color:green;">'.$_SESSION['User_Ajout'].'</i>';
        session_destroy();
      }
    ?>
    <form method="post" action="formNOUVEAUX-post.php">

            <table>
              <?php
              /*
              <tr>
                <td>NUMERO</td>
                <td>:</td>
                <td><input type="text" name="PRA_NUM" value=""></td>
              </tr>
              */
              ?>
              <tr>
                <td>NOM</td>
                <td>:</td>
                <td><input type="text" name="PRA_NOM" value="" placeholder="Votre nom"></td>
              </tr>
              <tr>
                <td>PRENOM</td>
                <td>:</td>
                <td><input type="text" name="PRA_PRENOM" value="" placeholder="Votre prénom"></td>
              </tr>
              <tr>
                <td>ADRESSE</td>
                <td>:</td>
                <td><input type="text" name="PRA_ADRESSE" value="" placeholder="Votre adresse"></td>
              </tr>
              <tr>
                <td>VILLE</td>
                <td>:</td>
                <td><input type="text" name="PRA_CP" value="" placeholder="Votre CP"></td>
                <td><input type="text" name="PRA_VILLE" value="" placeholder="Votre Ville"></td>
              </tr>
              <tr>
                <td>COEF. NOTORIETE</td>
                <td>:</td>
                <td><input type="text" name="PRA_COEFNOTORIETE" value="" placeholder="Votre Coef."></td>
              </tr>
              <tr>
                <td>TYPE CODE</td>
                <td>:</td>
                <td>
                <select name="TYP_CODE">
                <?php 
                  $TYPE = $bdd->query("SELECT TYP_CODE FROM praticien GROUP BY TYP_CODE");
                  while($donTYPE = $TYPE -> fetch()){ 
                    echo '<option value="'.$donTYPE['TYP_CODE'].'">'.$donTYPE['TYP_CODE'].'</option>';
                  };
                ?>
                </select>
              </td>
              </tr>
            </table>
      <input type="reset" value="Annuler"/>
      <input type="submit" value="valider"/>
    </form>
</div>

<!-- Fin de Page -->
<?php        
  require($repInclude . "_pied.inc.html");
?>