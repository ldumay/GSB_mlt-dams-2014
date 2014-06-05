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
  $vist = $_SESSION['User_id'];
  $pract = $_SESSION['User_idPracticien'];

  if(isset($_POST['search'])){
    $_SESSION['User_PratSearch'] = $_POST['lstPrat'];
  }
?>

<!-- Contenu de Page -->

<div id="contenu">
  <h2>Rapports de visite</h2>
    <form name="formRAPPORT_VISITE" method="post" action="formRAPPORT_VISITE.php">
      <?php
      // =====
      $selectPraticien = $bdd->query('SELECT PRA_NUM,PRA_NOM,PRA_PRENOM FROM praticien ORDER BY PRA_NOM');
      // =====
      ?>

      <tr> 
        <td><select name="lstPrat" class="titre">
          <option selected="selected">Choisir le praticien</option>
          <?php
              while ($praticienAff = $selectPraticien-> fetch()){
                $numPrat = utf8_encode($praticienAff['PRA_NUM']);
                $nomPrat = utf8_encode($praticienAff['PRA_NOM']);
                $prenomPrat = utf8_encode($praticienAff['PRA_PRENOM']);
                echo '<option value="'.$numPrat.'">'.$nomPrat.' '.$prenomPrat.'</option>';
              };
          ?>
          </select> 
        </td>
        <td><input name="search" type='submit' value='Rechercher' ></td>
      </tr><br><br>

      <?php // echo $_SESSION['User_PratSearch'].'<br /><br />';?>

      <?php
        if($_SESSION['User_PratSearch']==0){
          // =====
          $req3 = $bdd->query('SELECT DISTINCT  VIS_MATRICULE, RAP_NUM, SUBSTRING(RAP_DATE,1,10) AS dateRapport FROM rapport_visite WHERE VIS_MATRICULE="'.$vist.'" ');
          // =====
          ?>
            <tr>
              <td><h3>Praticien non choisit / Liste des rapports de visites en fontion du visiteur connecté.</h3></td>
              <td><select name="lstmnt" class="titre">
                <option value="0" selected="selected">Choisir la date du rendez-vous</option>
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
        }
        if($_SESSION['User_PratSearch']!=0){
          // =====
          $req4 = $bdd->query('SELECT VIS_MATRICULE, RAP_NUM, RAP_DATE FROM rapport_visite WHERE PRA_NUM="'.$_SESSION['User_PratSearch'].'"');
          // =====
          ?>
          <tr>
            <td><h3>Praticien choisit / Liste des rapports de visites en fontion du praticien choisit.</h3></td>
            <td><select name="lstPrat" class="titre">
              <option value="0" selected="selected">Choisir la date du rendez-vous</option>
              <?php
                  while ($donnees4 = $req4-> fetch()){
                    $tpDate = $donnees4['dateRapport'];
                    $tpDateFormat = date_create($tpDate);
                    $date = date_format($tpDateFormat, "d/m/Y");
                    echo '<option value="'.$donnees4['RAP_NUM'].'">Rapport n°'.$donnees4['RAP_NUM'].' du '.$date.'</option>';
                  };
              ?>
              </select> 
            </td>
            <td><input type='submit' value='Valider' ></td>
          </tr><br><br>
          <?php
        }
      ?>
      <?php
        // Recherche du rapport en fonction du client connecté
        if(isset($_POST['lstmnt'])){
          $NUM = $_POST['lstmnt'];
          $req = $bdd->query("SELECT * FROM rapport_visite WHERE VIS_MATRICULE = '".$vist."' AND RAP_NUM= '".$NUM."'");
          $donnees = $req-> fetch();
          $rapport = $donnees['RAP_NUM'];
        }
        // Recherche du rapport en fonction du praticien
        if(isset($_POST['lstPrat'])){
          $NUM = $_POST['lstPrat'];
          $req = $bdd->query("SELECT * FROM rapport_visite WHERE PRA_NUM= '".$NUM."'");
          $donnees = $req-> fetch();
          $rapport = $donnees['RAP_NUM'];
        }
        // = = = Affiche = = = =
        echo $rapport;
        if(!empty($rapport)){
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
          <?php
        }
      ?>
    </form>
</div>

<!-- Fin de Page -->
<?php        
  require($repInclude . "_pied.inc.html");
?>