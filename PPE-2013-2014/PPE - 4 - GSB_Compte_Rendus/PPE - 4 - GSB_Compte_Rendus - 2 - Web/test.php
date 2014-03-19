<?php

try

{
    $bdd = new PDO('mysql:host=localhost;dbname=gsb', 'root', '');
}
catch(Exception $e)
{
        die('Erreur : '.$e->getMessage());
}

?>

<!DOCTYPE html>
<html>
	<head>
		<titla>GSB</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	</head>
	<body>
		<?php 
			$maxTmp = $bdd->query("SELECT * FROM medicament");
			while($max = $maxTmp-> fetch()){
				$MED_DEPOTLEGAL = utf8_encode ($max['MED_DEPOTLEGAL']);
				$MED_NOMCOMMERCIAL = utf8_encode ($max['MED_NOMCOMMERCIAL']);
				$MED_EFFETS = utf8_encode ($max['MED_EFFETS']);
				echo $MED_DEPOTLEGAL.' - '.$MED_NOMCOMMERCIAL.' - '.$MED_EFFETS.'<br />';
			}
		?>
	</body>
</html>