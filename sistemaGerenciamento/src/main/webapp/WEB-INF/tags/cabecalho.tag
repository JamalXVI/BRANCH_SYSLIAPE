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
<link href="<c:url value='/assets/css/bootstrap.min.css' />"
	rel="stylesheet">
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

@media ( min-width : 768px) {
	.texto_perfil {
		display: none;
	}
	#perfil_pequeno {
		display: none;
	}
	.sumir_pequeno {
		display: block;
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