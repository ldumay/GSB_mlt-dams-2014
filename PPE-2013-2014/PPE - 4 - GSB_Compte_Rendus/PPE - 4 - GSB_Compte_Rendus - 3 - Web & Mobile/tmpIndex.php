<?php
	require('classes/Mobile_Detect.php');

	$detect = new Mobile_Detect();
	// $tablet_detect = new Tablet_Detect();
	
	
	if($detect->isMobile()){
		header('location: mobile/home.php');
	}
	/*
	if($tablet_detect = $isMobile()){
		header('location: tablet/home.php');
	}
	*/

	/*
	$isPhone = $detect->isMobile && !$detect->isTablet;

	if($isPhone){
		header('location: mobile/index.php');
	}
	*/

	header('location: desktop/index.php');
?>