<?php  
/** 
 * Script de contrôle et d'affichage du cas d'utilisation "Se connecter"
 * @package default
 * @todo  RAS
 */
  $repInclude = './include/';
  require($repInclude . "_init.inc.php");
  require($repInclude . "_entete.inc.html");
?>
<!-- Division pour le contenu principal -->
    <div id="contenu">
      <h2>Identification</h2>
      <p class="erreur"><?php if(isset($_SESSION['Erreur_Log'])){ echo $_SESSION['Erreur_Log'];} ?></p>
      <form id="frmConnexion" action="cSeConnecter-post.php" method="post">
        <div class="corpsForm">
          <input type="hidden" name="etape" id="etape" value="validerConnexion" />
          <p>
            <label for="txtLogin" accesskey="n">Login : </label>
            <input type="text" id="txtLogin" name="txtLogin" maxlength="20" size="15" value="" title="Entrez votre login" placeholder="ex: login"/>
          </p>
          <p>
            <label for="txtMdp" accesskey="m">Mot de passe : </label>
            <input type="password" id="txtMdp" name="txtMdp" maxlength="11" size="15" value=""  title="Entrez votre mot de passe" placeholder="ex: jj-mm-aaaa"/>
          </p>
          </div>
          <div class="piedForm">
          <p>
          <input type="submit" id="ok" value="Valider" />
          <input type="reset" id="annuler" value="Effacer" />
        </p> 
        </div>
      </form>
    </div>
    <?php session_destroy(); ?>
<?php
    require($repInclude . "_pied.inc.html");
?>