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
  <h2>Nouveau Médicament</h2>
    <?php 
      if($_SESSION['User_Ajout']!=''){
        echo '<i style="color:green;">'.$_SESSION['User_Ajout'].'</i>';
        session_destroy();
      }
    ?>
    <form method="post" action="formNOUVEAUX_MED-post.php">

            <table>
              
              <label class="titre">DEPOT LEGAL :</label>
                <input type="text" class="zone" size="10" name="MED_DEPOTLEGAL" value=""/>
              <br />
              <label class="titre">NOM COMMERCIAL :</label>
                <input type="text" class="zone" size="25" name="MED_NOMCOMMERCIAL" value=""/>
              <br />
              <label class="titre">FAMILLE :</label>
                <select name="FAM_CODE">
                <?php 
                  $TYPE = $bdd->query("SELECT FAM_CODE FROM medicament GROUP BY FAM_CODE");
                  while($donTYPE = $TYPE -> fetch()){ 
                    echo '<option value="'.$donTYPE['FAM_CODE'].'">'.$donTYPE['FAM_CODE'].'</option>';
                  };
                ?>
                </select>
              <br />
              <label class="titre">COMPOSITION :</label><br />
                <textarea rows="5" class="zone" cols="70" name="MED_COMPOSITION" ></textarea>
              <br />
              <label class="titre">EFFETS :</label><br />
                <textarea rows="5" class="zone" cols="70" name="MED_EFFETS" ></textarea>
              <br />
              <label class="titre">CONTRE INDIC. :</label><br />
                <textarea rows="5" class="zone" cols="70" name="MED_CONTREINDIC" ></textarea>
              <br />
              <label class="titre">PRIX ECHANTILLON :</label>
                <input type="text" class="zone" size="7" name="MED_PRIXECHANTILLON" value=""/>€
            </table>
      <input type="reset" value="Annuler"/>
      <input type="submit" value="valider"/>
    </form>
</div>

<!-- Fin de Page -->
<?php        
  require($repInclude . "_pied.inc.html");
?>