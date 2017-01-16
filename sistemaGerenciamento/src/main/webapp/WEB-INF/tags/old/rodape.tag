<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- footer content -->

<footer>
	<div class="pull-right">
		Gentelella - Bootstrap Admin Template by <a
			href="https://colorlib.com">Colorlib</a>
	</div>
	<div class="clearfix"></div>
</footer>
<!-- /footer content -->
</div>
</div>
<script type="text/javascript">
<!--
	//-->
	iniMensagemAlerta = "<p style='color: #EF5350;'>"//'<div class="alert alert-danger" role="alert">';
	fimMensagemAlerta = "</p>"//'</div>';
</script>

<!-- jQuery -->
<script src="<c:url value='/assets/js/jquery-1.9.1.min.js' />"></script>
<!-- Bootstrap -->
<script src="<c:url value='/assets/js/bootstrap.min.js' />"></script>
<!-- FastClick -->
<script src="<c:url value='/assets/js/fastclick.js' />"></script>
<!-- Chart.js -->
<script src="<c:url value='/assets/js/Chart.min.js' />"></script>
<!-- gauge.js -->
<script src="<c:url value='/assets/js/gauge.min.js' />"></script>
<!-- gauge.js -->
<script src="<c:url value='/assets/js/custom.min.js' />"></script>
<!-- NProgress -->
<script src="<c:url value='/assets/js/nprogress.js' />"></script>
<!-- Jquery Validate -->
<script src="<c:url value='/assets/js/jquery.validate.min.js' />"></script>
<!-- File Input Validate -->
<script src="<c:url value='/assets/js/fileinput.min.js' />"></script>
<script src="<c:url value="/assets/js/jquery.mask.min.js"/>"></script>
<!-- Renderizar Image -->
<script type="text/javascript">
	var urlFoto = "${linkTo[FotoPerfilController].imagem() }";
	var idCaboclo = "${usuarioLogado.usuario.pessoa.id}";
	$.ajax({
		type : "post",
		url : urlFoto + idCaboclo,
		processData : false
	}).always(function(b64data) {
		$(".imagem-perfil").attr("src", "data:image/png;base64," + b64data);
	});
</script>
<!-- Enviar Foto Perfil -->
<script type="text/javascript">
	$('#file').change(function() {
		$('#fotoPerfilForm').submit();
	});
</script>
<!-- Correção Bug Modal Bootstrap -->
<script type="text/javascript">
$(document).ready(function () {
    $('.modal').on('show.bs.modal', function () {
        if ($(document).height() > $(window).height()) {
            // no-scroll
            $('body').addClass("modal-open-noscroll");
        }
        else {
            $('body').removeClass("modal-open-noscroll");
        }
    });
    $('.modal').on('hide.bs.modal', function () {
        $('body').removeClass("modal-open-noscroll");
    });
})
</script>
<!-- Rendeizar Scripts Personalizados -->
<jsp:doBody></jsp:doBody>

</body>
</html>