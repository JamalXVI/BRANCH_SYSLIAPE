<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/jsp/comum/modal/modal_erro.jsp" />
<c:import url="/WEB-INF/jsp/comum/modal/modal_sucesso.jsp" />
<form action="${linkTo[MensagemController].visualizar()}" method="POST" id="verMensagem">
	<input type='hidden' name='idRec' id='idRecEnviar' />
</form>
<!-- footer content -->
<div class="col-md-12">
	<hr>
	<!-- Footer -->
	<footer style="margin-top:0px;">
		<div class="row">
			<div class="col-lg-12 text-center">
				<p class="text-center">Copyright &copy; SYSLIAPE 2017</p>
			</div>
		</div>
	</footer>
</div>
</div>
</body>
</html>
<script type="text/javascript">
<!--
	//-->
	
	iniMensagemAlerta = "<p style='color: #EF5350;'>"//'<div class="alert alert-danger" role="alert">';
	fimMensagemAlerta = "</p>"//'</div>';
	var formularErro = function(mensagem)
	{
		mensagem = mensagem.replace("Erro:","").replace("Erro!","").replace("Erro","");
		$("#mensagemErroModal").html("<div class='col-xs-2'><i class='fa fa-exclamation-circle fa-3x'"+
		"aria-hidden='true' style='color: #f44336'></i></div><div class='col-xs-10'><p>"+mensagem+"</p></div>");
		$("#novaMensagemErro").modal('show');
		
	};
</script>
<!-- Watch Js -->
<script src="<c:url value='/assets/js/object-watch.js' />"></script>
<!-- jQuery -->
<script src="<c:url value='/assets/js/jquery-1.9.1.min.js' />"></script>
<!-- jQuery -->
<script src="<c:url value='/assets/js/dropzone.js' />"></script>
<!-- Bootstrap -->
<script src="<c:url value='/assets/js/bootstrap.min.js' />"></script>
<!-- FastClick -->
<script src="<c:url value='/assets/js/fastclick.js' />"></script>
<!-- Chart.js -->
<script src="<c:url value='/assets/js/Chart.min.js' />"></script>
<!-- gauge.js -->
<script src="<c:url value='/assets/js/gauge.min.js' />"></script>
<!-- gauge.js -->
<!-- NProgress -->
<script src="<c:url value='/assets/js/nprogress.js' />"></script>
<!-- Jquery Validate -->
<script src="<c:url value='/assets/js/jquery.validate.min.js' />"></script>
<!-- File Input Validate -->
<script src="<c:url value='/assets/js/fileinput.min.js' />"></script>
<script src="<c:url value="/assets/js/jquery.mask.min.js"/>"></script>
	<!-- jQuery UI-->
	<script src="<c:url value='/assets/js/jquery-ui.js' />"></script>
	<!-- jQuery Date Time Picker-->
	<script src="<c:url value='/assets/js/jquery.datetimepicker.full.min.js' />"></script>
<!-- Renderizar Image -->
<script type="text/javascript">
	var urlFoto = "${linkTo[FotoPerfilController].imagem() }";
	var idCaboclo = "${usuarioLogado.usuario.pessoa.id}";
	var posicionarFotoPerfil = function(objetoPosicionar, idPessoaFoto){
		$.ajax({
			type : "post",
			url : urlFoto + idPessoaFoto,
			processData : false
		}).always(function(b64data) {
			$(objetoPosicionar).attr("src", "data:image/png;base64," + b64data);
		});
	}
	posicionarFotoPerfil(".imagem-perfil", idCaboclo);
</script>
<!-- Enviar Foto Perfil -->
<script type="text/javascript">
	$('#file').change(function() {
		$('#fotoPerfilForm').submit();
	});
	$(".modal").draggable({
	    handle: ".modal-header"
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
<!-- Tratamento de Erros - Ajax -->
<script type="text/javascript">
var tratarErroAjax = function(xhr, textStatus, errorThrown){
	if (xhr.status == 404 || xhr.status == 500) {
		window.location.replace("${linkTo[ErrosController].erro_operacao()}");
	}else{
	}
}
</script>

<!-- Listar Permissões do Usuário -->
<script type="text/javascript">
	var permissoesUsuario = [];
</script>

<c:forEach items="${usuarioLogado.pegarAutorizacao() }" var="permissao">
 <script type="text/javascript">
 	permissoesUsuario.push("${permissao.id}");
// 	console.log("${permissao}");
 </script>

</c:forEach>

 <!-- Desativar Enter enviar Formulário -->
<script type="text/javascript" >
$('form').on('keyup keypress', function(e) {
	  var keyCode = e.keyCode || e.which;
	  if (keyCode === 13) { 
	    e.preventDefault();
	    return false;
	  }
	});
</script>
<!-- Formulário de Date e Hora -->
<script type="text/javascript">
$('.formHora').datetimepicker({
	  datepicker:false,
	  format:'H:i',
	  mask:true
	});
$('.formData').datetimepicker({
	 timepicker:false,
	 mask:true, // '9999/19/39 29:59' - digit is the maximum possible for a cell
	});

</script>
<!-- Script de Carregando e Carregar -->
<script type="text/javascript">
var cancelouCarregar = function(valor){
	
}
var carregando = function(){
	
	carregar(false);
	waitingDialog.show();
	//$("#carregar").append("<div><div class='loader'></div><p>Carregando</p></div>");
};
var carregar = function(terminou){
	terminou = (terminou == undefined ? true : terminou);
	//$("#carregar").find("div").remove().end();
	waitingDialog.hide();	
	cancelouCarregar(terminou);
};
var mensagemSucesso =  function(){
	$("#mensagemSucessoModal").html("<div class='col-lg-4'><i class='fa fa-2x fa-check-circle-o'></i>"
	+"</div><div class='col-lg-8'<p class='bg-success'>Ação Efetivada com Sucesso!</p></div>");
	$("#novaMensagemSucesso").modal("show");
}
$("#novaMensagemSucesso").on("hide.bs.modal", function(){
	$("body").removeClass("body.modal-open-noscroll");
});
var waitingDialog = waitingDialog || (function ($) {
    'use strict';

	// Creating modal dialog's DOM
	var $dialog = $(
		'<div class="modal " id="carrengadoModal" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true" style="padding-top:15%; overflow-y:visible;">' +
		'<div class="modal-dialog modal-m">' +
		'<div class="modal-content">' +
			'<div class="modal-header"><h3 style="margin:0;"></h3></div>' +
			'<div class="modal-body">' +
				'<div class="progress progress-striped active" style="margin-bottom:0;"><div class="progress-bar" style="width: 100%"></div></div>' +
			'</div>' +
		'</div></div></div>');

	return {
		/**
		 * Opens our dialog
		 * @param message Custom message
		 * @param options Custom options:
		 * 				  options.dialogSize - bootstrap postfix for dialog size, e.g. "sm", "m";
		 * 				  options.progressType - bootstrap postfix for progress bar type, e.g. "success", "warning".
		 */
		show: function (message, options) {
			// Assigning defaults
			if (typeof options === 'undefined') {
				options = {};
			}
			if (typeof message === 'undefined') {
				message = 'Carregando';
			}
			var settings = $.extend({
				dialogSize: 'm',
				progressType: '',
				onHide: null // This callback runs after the dialog was hidden
			}, options);

			// Configuring dialog
			$dialog.find('.modal-dialog').attr('class', 'modal-dialog').addClass('modal-' + settings.dialogSize);
			$dialog.find('.progress-bar').attr('class', 'progress-bar');
			if (settings.progressType) {
				$dialog.find('.progress-bar').addClass('progress-bar-' + settings.progressType);
			}
			$dialog.find('h3').text(message);
			// Adding callbacks
			if (typeof settings.onHide === 'function') {
				$dialog.off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
					settings.onHide.call($dialog);
				});
			}
			// Opening dialog
			$dialog.modal();
		},
		/**
		 * Closes dialog
		 */
		hide: function () {
			$dialog.modal('hide');
			$('body').removeClass('modal-open');
			$('.modal-backdrop').remove();
		}
	};

})(jQuery);

</script>
<!-- Validações Customizadas -->
<script type="text/javascript">
jQuery.validator.addMethod("hora", function (value, element) {
    return this.optional(element) || /^\d{2}:\d{2}$/.test(value);
}, "Entre com uma Hora Válida");
jQuery.validator.addMethod("horaMaiorQue", 
		function(value, element, params) {
			var x = new Date();
			var dia = x.toDateString();
			var valor1 = dia+" "+value;
			var valor2 = (dia+" "+$(params).val());
		    if (!/Invalid|NaN/.test(new Date(valor1))) {
		        return new Date(valor1) > new Date(valor2);
		    }

		    return isNaN(valor1) && isNaN(valor2) 
		        || (Number(valor1) > Number(valor2)); 
},'Tem que ser maior que a entrada!');
</script>
<!-- Rendeizar Scripts Personalizados -->
<jsp:doBody></jsp:doBody>

</body>
</html>