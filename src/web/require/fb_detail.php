<?php

	require_once('facebook-sdk-v5/src/Facebook/autoload.php');

	$fb = new Facebook\Facebook([
	  'app_id' => '1749626411963598',//app_id 要改埋login-callback.php個app id
	  'app_secret' => 'a98680d72521a1e94d3741699f5c7c08',//app_安全碼
	  'default_graph_version' => 'v2.7',//預設版本
	  ]);

	$helper = $fb->getRedirectLoginHelper();

?>