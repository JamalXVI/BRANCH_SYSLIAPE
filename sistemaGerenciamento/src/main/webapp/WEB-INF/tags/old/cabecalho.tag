<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Titulo da Página -->
    <title>Sistema de Gerenciamento - SISGER </title>
    <!-- Bootstrap -->
    <link href="<c:url value='/assets/css/bootstrap.min.css' />" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="<c:url value='/assets/css/font-awesome.min.css' />" rel="stylesheet">
    <!-- Estilo Próprio da Página -->
    <link href="<c:url value='/assets/css/estilo.css' />" rel="stylesheet">
    <!-- iCheck -->
    <link href="<c:url value='/assets/css/green.css' />" rel="stylesheet">
    <!-- File Input -->
    <link href="<c:url value='/assets/css/fileinput.min.css' />" rel="stylesheet">
<!-- bootstrap-progressbar -->
<script src="<c:url value='/assets/js/bootstrap-progressbar.min.js' />" ></script>
<!-- iCheck -->
<script src="<c:url value='/assets/js/icheck.min.js' />" ></script>
    <!-- NProgress -->
    <link href="<c:url value='/assets/css/nprogress.css' />" rel="stylesheet">
    <!-- bootstrap-progressbar -->
    <link href="<c:url value='/assets/css/bootstrap-progressbar-3.3.4.min.css' />" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="<c:url value='/assets/css/custom.min.css' />" rel="stylesheet">
    <style>
    .image-upload > input
		{
		    display: none;
		}
		.col-center{
		  margin:0 auto;
		}
	<!-- -->
	body.modal-open-noscroll
	{
	    margin-right: 0!important;
	    overflow: hidden;
	}
	.modal-open-noscroll .navbar-fixed-top, .modal-open .navbar-fixed-bottom
	{
	    margin-right: 0!important;
	}
    </style>
    <jsp:doBody></jsp:doBody>
</head>
