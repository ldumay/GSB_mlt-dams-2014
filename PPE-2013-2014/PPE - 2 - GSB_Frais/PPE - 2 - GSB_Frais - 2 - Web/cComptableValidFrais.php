<?php
/** 
	* Page d'accueil de l'application web AppliFrais
	* @package default
	* @todo  RAS
*/

	$repInclude = './include/';
	require($repInclude . "_init.inc.php");

	// page inaccessible si visiteur non connecté
	if ( ! estVisiteurConnecte() ) {
		header("Location: cSeConnecter.php");  
	}
	require($repInclude . "_entete.inc.html");
	require($repInclude . "_sommaire.compt.inc.php");
	
	// acquisition des données entrées, ici le numéro de mois et l'étape du traitement
		$moisSaisi=lireDonneePost("lstMois", "");
		$etape=lireDonneePost("etape","");
		$idVisiteurSaisi=lireDonneePost("lstVisiteur","");
		$idLigneHF = lireDonnee("idLigneHF", "");

	if ($etape != "demanderConsult" && $etape != "validerConsult") {
		// si autre valeur, on considère que c'est le début du traitement
		$etape = "demanderConsult";        
	} 
	if ($etape == "validerConsult") { // l'utilisateur valide ses nouvelles données
		// vérification de l'existence de la fiche de frais pour le mois demandé
		$existeFicheFrais = existeFicheFrais($idConnexion, $moisSaisi, $idVisiteurSaisi);
		// si elle n'existe pas, on la crée avec les élets frais forfaitisés à 0
		if ( !$existeFicheFrais ) {
			ajouterErreur($tabErreurs, "Le mois demandé est invalide");
		}
		else {
			// récupération des données sur la fiche de frais demandée
			$tabFicheFrais = obtenirDetailFicheFrais($idConnexion, $moisSaisi, $idVisiteurSaisi);
		}
	} 
	elseif ($etape == "validerSuppressionLigneHF") {
		supprimerLigneHF($idConnexion, $idLigneHF);
	}
	  else { // on ne fait rien, étape non prévue 
  
  }                                  


 ?>
 
	<!-- Division principale -->
	<div id="contenu">
	
		<h2>Validation des frais</h2>
		<h3>Validation des frais par visiteur</h3>
		
		<form name="choix" action="" method="post">
			<div class="corpsForm">
				<input type="hidden" name="etape" value="validerConsult" />
				
					<label class="titre" for="lstVIsiteur">Choisir le visiteur :</label>
					
					<select name="lstVisiteur" class="zone" onChange="document.forms.choix.submit()">
					<?php
						// on propose tous les mois pour lesquels le visiteur a une fiche de frais
						$idJeuVisiteur = mysql_query("select id, nom from visiteur", $idConnexion);
						$lgVisiteur = mysql_fetch_assoc($idJeuVisiteur);
						while ( is_array($lgVisiteur) ) {
						$idVisiteur = $lgVisiteur["id"];
						$visiteur = $lgVisiteur["nom"];
					?>				
						<option value="<?php echo $idVisiteur; ?>" <?php echo ($idVisiteurSaisi == $idVisiteur ? 'selected="selected"' : ''); ?>><?php echo $visiteur; ?></option>
					<?php
						$lgVisiteur = mysql_fetch_assoc($idJeuVisiteur);        
						}
						mysql_free_result($idJeuVisiteur);
					?>
					</select>	
				<p>	<label for="lstMois">Mois : </label>
					<select id="lstMois" name="lstMois" title="Sélectionnez le mois souhaité pour la fiche de frais" onChange="document.forms.choix.submit()" >
						<?php
							// on propose tous les mois pour lesquels le visiteur a une fiche de frais
							$req = obtenirReqMoisFicheFraisNonValide($idVisiteurSaisi);
							$idJeuMois = mysql_query($req, $idConnexion);
							while ( $lgMois = mysql_fetch_assoc($idJeuMois)) {
								$mois = $lgMois["mois"];
								$noMois = intval(substr($mois, 4, 2));
								$annee = intval(substr($mois, 0, 4));
						?>
							<option value="<?php echo $mois; ?>" <?php if ($moisSaisi == $mois) { ?> selected="selected"<?php } ?>><?php echo obtenirLibelleMois($noMois) . " " . $annee; ?></option>
						<?php       
							}
							mysql_free_result($idJeuMois);
						?>
					</select>
				</p>
			</div>
			<?php 
			/*
			* Fonctionnel, mais pas nécesaire.
			<div class="piedForm">
				<p>
					<input id="ok" type="submit" value="Valider" size="20"
					title="Demandez à consulter cette fiche de frais" />
					<input id="annuler" type="reset" value="Effacer" size="20" />
				</p> 
			</div>
			*/
			?>
		</form>
		<?php      

// demande et affichage des différents éléments (forfaitisés et non forfaitisés)
// de la fiche de frais demandée, uniquement si pas d'erreur détecté au contrôle
    if ( $etape == "validerConsult" ) {
        if ( nbErreurs($tabErreurs) > 0 ) {
            echo toStringErreurs($tabErreurs) ;
        }
        else {
?>
    <h3>Validation des frais</h3>
<?php          
            // demande de la requête pour obtenir la liste des éléments 
            // forfaitisés du visiteur connecté pour le mois demandé
            $req = obtenirReqEltsForfaitFicheFrais($moisSaisi, $idVisiteurSaisi);
            $idJeuEltsFraisForfait = mysql_query($req, $idConnexion);
            echo mysql_error($idConnexion);
            $lgEltForfait = mysql_fetch_assoc($idJeuEltsFraisForfait);
            // parcours des frais forfaitisés du visiteur connecté
            // le stockage intermédiaire dans un tableau est nécessaire
            // car chacune des lignes du jeu d'enregistrements doit être doit être
            // affichée au sein d'une colonne du tableau HTML
            $tabEltsFraisForfait = array();
            while ( is_array($lgEltForfait) ) {
                $tabEltsFraisForfait[$lgEltForfait["libelle"]] = $lgEltForfait["quantite"];
                $lgEltForfait = mysql_fetch_assoc($idJeuEltsFraisForfait);
            }
            mysql_free_result($idJeuEltsFraisForfait);
            ?>
  	<table class="listeLegere">
  	   <caption>Frais au forfait</caption>
        <tr>
            <?php
            // premier parcours du tableau des frais forfaitisés du visiteur connecté
            // pour afficher la ligne des libellés des frais forfaitisés
            foreach ( $tabEltsFraisForfait as $unLibelle => $uneQuantite ) {
            ?>
                <th><?php echo $unLibelle ; ?></th>
            <?php
            }
            ?>
        </tr>
        <tr>
            <?php
            // second parcours du tableau des frais forfaitisés du visiteur connecté
            // pour afficher la ligne des quantités des frais forfaitisés
            foreach ( $tabEltsFraisForfait as $unLibelle => $uneQuantite ) {
            ?>
                <td class="qteForfait"><input type="text" value="<?php echo $uneQuantite ; ?>"/></td>
            <?php
            }
            ?>
			

        </tr>
    </table>
  	<table class="listeLegere">
  	   <caption>Hors Forfait - <?php echo $tabFicheFrais["nbJustificatifs"]; ?> justificatifs reçus -
       </caption>
             <tr>
                <th class="date">Date</th>
                <th class="libelle">Libellé</th>
                <th class="montant">Montant</th>  
				<th>Situation</th>				
             </tr>
<?php          
            // demande de la requête pour obtenir la liste des éléments hors
            // forfait du visiteur connecté pour le mois demandé
            $req = obtenirReqEltsHorsForfaitFicheFrais($moisSaisi, $idVisiteurSaisi);
            $idJeuEltsHorsForfait = mysql_query($req, $idConnexion);
            $lgEltHorsForfait = mysql_fetch_assoc($idJeuEltsHorsForfait);
            
            // parcours des éléments hors forfait 
            while ( is_array($lgEltHorsForfait) ) {
            ?>
                <tr>
                   <td><?php echo $lgEltHorsForfait["date"] ; ?></td>
                   <td><?php echo filtrerChainePourNavig($lgEltHorsForfait["libelle"]) ; ?></td>
                   <td><input type="text" value="<?php echo $lgEltHorsForfait["montant"] ; ?>"/></td>
               
				<td width="80"> 
					<select size="3" name="hfSitu1">
						<option value="S">Supprimer</option>
						<option value="V">Validé</option>
					</select>
				</tr>

            <?php
                $lgEltHorsForfait = mysql_fetch_assoc($idJeuEltsHorsForfait);
            }
            mysql_free_result($idJeuEltsHorsForfait);
  ?>
    </table>
  </div>
<?php
        }
    }
?> 

	</div>
	
	
<?php        
  require($repInclude . "_pied.inc.html");
  require($repInclude . "_fin.inc.php");
?>