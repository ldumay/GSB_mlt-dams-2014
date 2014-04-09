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

  // Création d'un maximum de lecture pour la liste des médicaments
  $maxTmp = $bdd->query("SELECT count(PRA_NUM) as result FROM praticien");
  $max = $maxTmp-> fetch();
  $_SESSION['User_PratListMax'] = $max['result'];
  // Vérfification des bouton suivant ou retour qui permettes de déclencher un avancement 
  // ou un recule dans la liste des médicaments
  // retour
  if(isset($_POST['supp'])){
    if($_SESSION['User_PratList'] > 1){
      $_SESSION['User_PratList']--;
    }
  }
  // suivant
  if(isset($_POST['add'])){
    if($_SESSION['User_PratList'] < $_SESSION['User_PratListMax']){
      $_SESSION['User_PratList']++;
    }
  }
  // search
  if(isset($_POST['search'])){
    $_SESSION['User_PratList'] = $_POST['id_search'];
  }
  // Affectation de ce déplacement de liste
  $PratNum = $_SESSION['User_PratList'];
  
  // echo 'PratNum : '.$PratNum.'<br />';
?>

<!-- Contenu de Page -->
<div id="contenu">
  <h2> Praticiens </h2>
      <form name="formListeRecherche"  method="post" action="formPRATICIEN.php" >
        <tr>
          <td>
          <select name="id_search" class="titre" onSubmit="document.forms.formListeRecherche.submit()">
            <option>Choisissez un praticien</option>
            <?php 
              $req = $bdd->query("SELECT * FROM praticien ORDER BY PRA_NOM");
              while ($donnees = $req-> fetch()){
                ?>
                <option value="<?php echo $list = utf8_encode($donnees['PRA_NUM']); ?>"><?php echo $list = utf8_encode($donnees['PRA_NOM']);?></option>
                <?php 
              };
            ?>
          </select>
          </td>
          <td><input type='submit' name="search" value='Valider' /></td>
          </tr>
          <br /><br />
        <?php
        // Récupération des informations du praticien trouvé pour affichage
        $donnees = $bdd->query("SELECT * FROM praticien WHERE PRA_NUM = '".$PratNum."'");

        if(($_SESSION['User_PratListMax'] >= 1) && ($_SESSION['User_PratListMax'] <= $_SESSION['User_PratListMax'])){
          $afficherPraticien = $bdd->query("SELECT * FROM praticien WHERE PRA_NUM = '".$PratNum."'");
          $donneesPra = $afficherPraticien -> fetch();

          $num = utf8_encode($donneesPra['PRA_NUM']);
          $nom = utf8_encode($donneesPra['PRA_NOM']);
          $prenom = utf8_encode($donneesPra['PRA_PRENOM']);
          $adresse = utf8_encode($donneesPra['PRA_ADRESSE']);
          $cp = utf8_encode($donneesPra['PRA_CP']);
          $ville = utf8_encode($donneesPra['PRA_VILLE']);
          $coef = utf8_encode($donneesPra['PRA_COEFNOTORIETE']);
          $code = utf8_encode($donneesPra['TYP_CODE']);
          ?>
          <table>
            <tr>
              <td>NUMERO</td>
              <td>:</td>
              <td><input type="text" value="<?php echo $num; ?>" readonly></td>
            </tr>
            <tr>
              <td>NOM</td>
              <td>:</td>
              <td><input type="text" value="<?php echo $nom; ?>" readonly></td>
            </tr>
            <tr>
              <td>PRENOM</td>
              <td>:</td>
              <td><input type="text" value="<?php echo $prenom; ?>" readonly></td>
            </tr>
            <tr>
              <td>ADRESSE</td>
              <td>:</td>
              <td><input type="text" value="<?php echo $adresse; ?>" readonly></td>
            </tr>
            <tr>
              <td>VILLE</td>
              <td>:</td>
              <td><input type="text" value="<?php echo $cp;?>" readonly></td>
              <td><input type="text" value="<?php echo $ville;  ?>" readonly></td>
            </tr>
              <tr>
              <td>COEF. NOTORIETE</td>
              <td>:</td>
              <td><input type="text" value="<?php echo $coef; ?>" readonly></td>
            </tr>
            <tr>
              <td>TYPE CODE</td>
              <td>:</td>
              <td><input type="text" value="<?php echo $code; ?>" readonly></td>
            </tr>
          </table>  
          <?php
        }
        ?>
        <input type='submit' name="supp" value='Precedent' />
        <?php echo $PratNum.'/'.$_SESSION['User_PratListMax']; ?>
        <input type='submit' name="add" value='Suivant' />
      </form>

</div>

<!-- Fin de Page -->
<?php        
  require($repInclude . "_pied.inc.html");
?>