<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Sistema de Gerenciamento - SISGER</title>

<!-- Bootstrap Core CSS -->
<!-- Bootstrap -->
<link href="<c:url value='/assets/css/bootstrap.min.css' />" rel="stylesheet" />
<!-- Font Awesome -->
<link href="<c:url value='/assets/css/font-awesome.min.css' />"
	rel="stylesheet">
<!-- Estilo Próprio da Página -->
<link href="<c:url value='/assets/css/estilo.css' />" rel="stylesheet">
<!-- Custom CSS -->
<link href="<c:url value="/assets/css/modern-business.css"/>"
	rel="stylesheet">
<link href="<c:url value="/assets/css/business-frontpage.css"/>"
	rel="stylesheet">
<!-- Custom Fonts -->
<link href="<c:url value="/assets/css/site.css"/>" rel="stylesheet">
<!-- Custom Fonts -->
<link href="<c:url value="/assets/css/dropzone.css"/>" rel="stylesheet">


<link href="<c:url value="/assets/css/sb-admin.css"/>" rel="stylesheet">
<!-- jQuery UI-->
<link href="<c:url value="/assets/css/jquery-ui.css"/>" rel="stylesheet">
<!-- jQuery DateTime Picker -->
<link href="<c:url value="/assets/css/jquery.datetimepicker.min.css"/>"
	rel="stylesheet">
<style>
#page-wrapper {
	padding-top: 0px;
}

.image-upload>input {
	display: none;
}

.perfil_imagem {
	background-color: rgba(255, 255, 255, 0);
	border: 0px;
	height: 52px;
	width: auto;
}

.col-center {
	margin: 0 auto;
}
.btn-enviar{
	margin-left: 15px !important; 
	width: 160px !important; 
	margin-top: 6px;
}
<!--
-->
body.modal-open-noscroll {
	margin-right: 0 !important;
	overflow: hidden;
}

.modal-open-noscroll .navbar-fixed-top, .modal-open .navbar-fixed-bottom
	{
	margin-right: 0 !important;
}

.tamanho_logo {
	width: 90px;
	/* 	display: inline; */
}

.form_imagem_perfil {
	display: inline-block;
	height: 50px;
}

.alinhar_imagem_perfil {
	display: inline-block;
}

.sumir_pequeno {
	display: none;
}

#perfil_pequeno {
	display: block;
}

.remover_padding {
	padding-top: 0px;
	padding-bottom: 0px;
}

@media ( min-width : 640px) {
	.tamanho_logo {
		width: 135px;
		/* 	display: inline; */
	}
}

.texto_perfil {
	display: inline;
}
.logo_liape{
		width: 30px;
		height: auto;
}
.loader {
    border: 16px solid #f3f3f3; /* Light grey */
    border-top: 16px solid #3498db; /* Blue */
    border-radius: 50%;
    width: 60px;
    height: 60px;
    animation: spin 2s linear infinite;
}
.panel-footer{
	padding: 0px 15px;
}
@keyframes spin {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
}
@media ( min-width : 768px) {
	.logo_liape{
		width: 40px;
		height: auto;
	}
	.texto_perfil {
		display: none;
	}
	#perfil_pequeno {
		display: none;
	}
	.sumir_pequeno {
		display: block;
	}
	#logoLiape{
		padding-top : 0px;
		padding-bottom : 0px;
		color: black !important; /* white */
	}
	#linkSysliape:hover, #linkSysliape:visited, #linkSysliape{
	color: inherit; /* blue colors for links too */
  text-decoration: inherit; /* no underline */
	}
}

@media ( min-width : 940px) {
	.tamanho_logo {
		width: 150px;
		/* 	display: inline; */
	}
}
</style>
<jsp:doBody></jsp:doBody>
</head>