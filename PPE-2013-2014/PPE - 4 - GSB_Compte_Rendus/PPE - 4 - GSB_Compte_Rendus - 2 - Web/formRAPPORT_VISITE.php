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

  // vérifiation de compte connecter
  $vist = $_SESSION['User_idVisiteur'];
  $pract = $_SESSION['User_idPracticien'];
?>

<!-- Contenu de Page -->

<div id="contenu">
  <h2>Rapports de visite</h2>
    <form name="formRAPPORT_VISITE" method="post" action="formRAPPORT_VISITE.php">
      <?php
        // =====
        $req3 = $bdd->query('SELECT DISTINCT  VIS_MATRICULE, RAP_NUM, SUBSTRING(RAP_DATE,1,10) AS dateRapport FROM rapport_visite WHERE PRA_NUM="'.$pract.'" AND VIS_MATRICULE="'.$vist.'" ');
      ?>
        <tr> 
          <td><select name="lstmnt" class="titre">
            <option selected="selected">Choisir la date du rendez-vous</option>
            <?php
                while ($donnees3 = $req3-> fetch()){
                  $tpDate = $donnees3['dateRapport'];
                  $tpDateFormat = date_create($tpDate);
                  $date = date_format($tpDateFormat, "d/m/Y");
                  echo '<option value="'.$donnees3['RAP_NUM'].'">Rapport n°'.$donnees3['RAP_NUM'].' du '.$date.'</option>';
                };
            ?>
            </select> 
          </td>
          <td><input type='submit' value='Valider' ></td>
        </tr><br><br>
        
        <?php 
        if(isset($_POST['lstmnt'])) {

        $NUM =$_POST['lstmnt'];

        $req = $bdd->query("SELECT * FROM rapport_visite WHERE VIS_MATRICULE = '".$vist."' AND RAP_NUM= '".$NUM."'");
        
        $donnees = $req-> fetch();
        $rapport = $donnees['RAP_NUM'];
      ?>
      <label class="titre">NUMERO :</label>
      <input type="text" size="10" name="RAP_NUM" value="<?php echo $rapport; ?>" class="zone" readonly/>
      <br />
      <?php
        $req = $bdd->query("SELECT * FROM rapport_visite WHERE VIS_MATRICULE = '".$vist."' AND RAP_NUM= '".$NUM."'");
        $donnees = $req-> fetch();
        $rapport = $donnees['RAP_NUM'];
        $praticien = $donnees['PRA_NUM'];

        $req2 = $bdd->query("SELECT * FROM praticien WHERE PRA_NUM = '".$praticien."'");
        $donnees2 = $req2-> fetch();
        $nomPraticien = utf8_encode($donnees2['PRA_NOM']." ".$donnees2['PRA_PRENOM']);
       ?>
       <br />
      <label class="titre">PRATICIEN :</label>
      <br /><input type="text" size="25" name="RAP_NUM" value="<?php echo $nomPraticien; ?>" class="zone" />
      <br />
      <label class="titre">BILAN :</label>
      <br /><textarea rows="5" cols="50" name="MED_EFFETS" class="zone" readonly ><?php echo $bilan = utf8_encode($donnees['RAP_BILAN']); ?></textarea>
      <br />
      <label class="titre">MOTIF :</label>
      <br /><textarea rows="5" cols="50" name="MED_EFFETS" class="zone" readonly ><?php echo $motif = utf8_encode($donnees['RAP_MOTIF']); ?></textarea>
    <?php } ?>
    </form>
</div>

<!-- Fin de Page -->
<?php        
  require($repInclude . "_pied.inc.html");
?>