<!-- Importação JSTLs -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Importação das Tags -->
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<!-- Importação do Cabecalho -->
<t:cabecalho>
<!-- jQuery UI-->
<link href="<c:url value="/assets/css/jquery-ui.css"/>" rel="stylesheet">
<!-- Estilização da TAG select -->
<style>
</style>
</t:cabecalho>
<!-- Importação do Menu Lateral e Superior -->
<c:import url="../menuSuperior.jsp"></c:import>
<div class="col-lg-12">

<div class="row" role="main">
		<h1 class="page-header" style="margin-top: 0px;">
			Nova <small>Mensagem</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="${linkTo[HomeController].index()}">Página Inicial</a></li>
			<li class="active">Nova</li>
		</ol>
		<form action="${linkTo[MensagemController].enviar(null) }" method="post"
		id="formMensagem">
			<t:input divCol="col-xs-12"
			 id="mensagemPara" nome="mensagem.para" nomeLabel="Para:" valorPadrao=""
			  tipo="text" classes=""></t:input>
			  <t:input divCol="col-xs-12"
			 id="mensagemTitulo" nome="mensagem.titulo" nomeLabel="Título:" valorPadrao=""
			  tipo="text" classes=""></t:input>
			 <div class="col-xs-12">
			 <label for="mensagem.texto">Texto:</label>
			 <textarea rows="5" cols="" name="mensagem.mensagem" class="form-control" id="mensagemTexto"></textarea>
			 </div>
			 
		</form>
		<div class="col-xs-12"
			style="margin-top: 15px; margin-bottom: 15px;">
			<form id="my-awesome-dropzone" class="dropzone"
				action="${linkTo[MensagemController].enviar(null, null) }" method="POST">
				<div class="dropzone-previews"></div>
				<div class="fallback">
				<!-- this is the fallback if JS isn't working -->
					<input name="file" type="file" />
				</div>
				<input type="hidden" name="mensagem.para" id="paraMensagem" value="${idArq }" /> 
				<input type="hidden" name="mensagem.titulo" id="tituloMensagem" /> 
				<input type="hidden" name="mensagem.mensagem" id="mensagemMensagem" />
				<button type="submit" style="display: none" id="enviar"></button>
			</form>
		</div>
		<button type="submit" class="btn btn-primary btn-enviar" id="enviarMensagem" onclick="enviando();">Enviar</button>
</div>
	
</div>
<!-- Scripts Específicos e Importação do Rodapé -->
<t:rodape>
<!-- jQuery UI-->
<script src="<c:url value='/assets/js/jquery-ui.js' />"></script>
<!-- Listar Usuários para Mensagens -->
<script type="text/javascript">
var usuarios;
//Listar Todos os Usuários e fazer Requisição Ajax da Lista
var listarUsuarios = function(){
	return $.ajax({  
	    type:"post",  
	    url: "${linkTo[UsuarioController].listar() }", 
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	usuarios = data;
	    	//criarPermissoes();
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){
	    	tratarErroAjax(xhr, textStatus, errorThrown);
	    }
	});
};
var valoresPossiveis = [];
function split( val ) {
    return val.split( /,\s*/ );
  }
  function extractLast( term ) {
    return split( term ).pop();
  }
//Jquery AutoComplete
var adicionarInputUsuarios = function(){
	$.when(listarUsuarios()).done(function(){
		$(usuarios).each(function(i, usuario){
			//Adicionar Todos Valores
			valoresPossiveis.push({login: usuario.login, 
				nome: usuario.pessoa.nome+" "+usuario.pessoa.sobrenome});
		});
		valoresPossiveis.push({login: "Todos", 
			nome: "Todos"});	
	});
	$("#mensagemPara").on( "keydown", function( event ) {
        if ( event.keyCode === $.ui.keyCode.TAB &&
                $( this ).autocomplete( "instance" ).menu.active ) {
              event.preventDefault();
            }
          }).autocomplete({
        	  minLength: 0,
              source: function(request, response) {
            	  
            	  var termos = request.term.split(new RegExp(", |,"));
            	  var termo = termos[termos.length-1]
            	  var matcher = new RegExp($.ui.autocomplete.escapeRegex(termo), "i");
                  response($.grep(valoresPossiveis, function(value) {
                      return matcher.test(value.nome);
                  }));
              },
              focus: function(event, ui) {
//             	$("#mensagemPara").val( ui.item.nome );
                // prevent value inserted on focus
                return false;
              },
              select: function( event, ui ) {
                var terms = split( this.value );
                // remove the current input
                terms.pop();
                // add the selected item
                terms.push( ui.item.login );
                // add placeholder to get the comma-and-space at the end
                terms.push( "" );
                this.value = terms.join( ", " );
                return false;
              }
             }).autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<li>" )
	        .append( "<div>" + item.nome+"</div>" )
	        .appendTo( ul );
	    };
	
}
adicionarInputUsuarios();
</script>
<script type="text/javascript">
$("#formMensagem").submit(function(e){
    return false;
});
var validarFormulario = function(){
	$("#formMensagem").validate(function(){});
	$("#mensagemTexto").rules("add",{
		required: true,
		  minlength: 5,
		  maxlength: 1000,
		  messages: {
		    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
		    minlength: jQuery.validator.format
		    (iniMensagemAlerta+"Por Favor insira ao menos {0} caracteres."+fimMensagemAlerta),
		    maxlength: jQuery.validator.format
		    (iniMensagemAlerta+"Por Favor insira até {0} caracteres."+fimMensagemAlerta)
		  }}
		);
	$("#mensagemTitulo").rules("add",{
		required: true,
		  minlength: 5,
		  maxlength: 255,
		  messages: {
		    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
		    minlength: jQuery.validator.format
		    (iniMensagemAlerta+"Por Favor insira ao menos {0} caracteres."+fimMensagemAlerta),
		    maxlength: jQuery.validator.format
		    (iniMensagemAlerta+"Por Favor insira até {0} caracteres."+fimMensagemAlerta)
		  }}
		);
	$("#mensagemPara").rules("add",{
		required: true,
		  minlength: 5,
		  maxlength: 255,
		  messages: {
		    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
		    minlength: jQuery.validator.format
		    (iniMensagemAlerta+"Por Favor insira ao menos {0} caracteres."+fimMensagemAlerta),
		    maxlength: jQuery.validator.format
		    (iniMensagemAlerta+"Por Favor insira até {0} caracteres."+fimMensagemAlerta)
		  }}
		);
}
var enviando = function() {
	var esseForm = $('#formMensagem');
    if (esseForm.valid()) {
    	debugger;
		$("#paraMensagem").val($("#mensagemPara").val());
		$("#tituloMensagem").val($("#mensagemTitulo").val());
		$("#mensagemMensagem").val($("#mensagemTexto").val());
		 
		$("#enviar").submit();
    }else{
    	esseForm.validate();
    	formularErro("Formulário Inválido!");
    }
}
Dropzone.options.myAwesomeDropzone = { // The camelized version of the ID of the form element

		// The configuration we've talked about above
		autoProcessQueue : false,
		uploadMultiple : false,
		addRemoveLinks : true,
		clickable : true,
		// The setting up of the dropzone
		init : function() {
			var myDropzone = this;

			// First change the button to actually tell Dropzone to process the queue.
			$("button[type=submit]").on("submit", function(e) {
				// Make sure that the form isn't actually being sent.
				e.preventDefault();
				e.stopPropagation();
				if (myDropzone.getQueuedFiles().length > 0) {                        
		            myDropzone.processQueue();  
		        } else {                       
		            $("#my-awesome-dropzone").submit(); //send empty 
		        } 
				
			});

			myDropzone.on("addedfile", function(file) {
				/* Maybe display some more file information on your page */
				console.log(file);
			});
			myDropzone.on("complete", function(file, resposta){
				sucessoEnvio(file, resposta, myDropzone);
				
			});
		}

	}
async function sucessoEnvio(file, resposta, myDropzone){
	setTimeout(function(){
		myDropzone.removeAllFiles(true);
		window.location.replace("${linkTo[MensagemController].entrada()}");
		},3000);
	
}
</script>
<script type="text/javascript">
	$(document).on("ready", function(){
		validarFormulario();
		
	})
</script>
</t:rodape>