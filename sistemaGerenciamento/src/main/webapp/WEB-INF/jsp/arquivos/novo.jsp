<!-- Importa��o JSTLs -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Importa��o das Tags -->
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!-- Importa��o do Cabecalho -->
<t:cabecalho>
	<!-- Estiliza��o da TAG select -->
</t:cabecalho>
<!-- Importa��o do Menu Lateral e Superior -->
<c:import url="../menuSuperior.jsp"></c:import>

<!-- Se��o Principal -->
<div class="col-lg-12 container-fluid">
	<div class="row" role="main">
		<div class="container">
			<h1 class="page-header" style="margin-top: 0px;">
				Cadastro <small>Arquivos</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="${linkTo[HomeController].index()}">P�gina
						Inicial</a></li>
				<li><a href="${linkTo[ArquivosController].index()}">Arquivos</a></li>
				<li class="active">Novo</a>
				</li>
			</ol>
			<div id="dropzone" class="col-xs-12">
				<form id="formCadastro">
					<t:input divCol="col-xs-12" id="titulo" nome="" nomeLabel="T�tulo"
						valorPadrao="${tituloArq}" tipo="text"></t:input>
					<div class="col-xs-12">
						<label for="descricao">Descri��o</label>
						<textarea rows="5" cols="5" class="form-control" id="descricao" 
						>${descricaoArq }</textarea>
						<label>Arquivo</label>
					</div>
				</form>
				<div class="col-xs-12"
					style="margin-top: 15px; margin-bottom: 15px;">
					<form id="my-awesome-dropzone" class="dropzone"
						action="${linkTo[ArquivosController].enviar(null,null) }">
						<div class="dropzone-previews"></div>
						<div class="fallback">
							<!-- this is the fallback if JS isn't working -->
							<input name="file" type="file" />
						</div>
						<input type="hidden" name="arquivos.id" id="arqId" value="${idArq }" /> 
						<input type="hidden" name="arquivos.titulo" id="arqTitulo" /> 
						<input type="hidden" name="arquivos.descricao" id="arqDescricao" />
						<button type="submit" style="display: none" id="enviar"></button>
					</form>
				</div>
				<div class="col-xs-12">
					<button type="submit" class="btn btn-primary" id="enviarForm" onclick="enviando();">
						Enviar</button>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Scripts Espec�ficos e Importa��o do Rodap� -->
<t:rodape>
	<script type="text/javascript">
		$("#formCadastro").submit(function(e){
		    return false;
		});
		function dormir(ms) {
			  return new Promise(resolve => setTimeout(resolve, ms));
		}
		//VALIDA��O COM JQUERY
		$(document).ready(function(){
			$("#formCadastro").validate(function(){});
			$("#descricao").rules("add",{
				required: true,
				  minlength: 5,
				  maxlength: 1000,
				  messages: {
				    required: iniMensagemAlerta+"Campo Obrigat�rio"+fimMensagemAlerta,
				    minlength: jQuery.validator.format
				    (iniMensagemAlerta+"Por Favor insira ao menos {0} caracteres."+fimMensagemAlerta),
				    maxlength: jQuery.validator.format
				    (iniMensagemAlerta+"Por Favor insira at� {0} caracteres."+fimMensagemAlerta)
				  }}
				);
			$("#titulo").rules("add",{
				required: true,
				  minlength: 5,
				  maxlength: 255,
				  messages: {
				    required: iniMensagemAlerta+"Campo Obrigat�rio"+fimMensagemAlerta,
				    minlength: jQuery.validator.format
				    (iniMensagemAlerta+"Por Favor insira ao menos {0} caracteres."+fimMensagemAlerta),
				    maxlength: jQuery.validator.format
				    (iniMensagemAlerta+"Por Favor insira at� {0} caracteres."+fimMensagemAlerta)
				  }}
				);
			});
	</script>
	<!-- Script para o DropZone.js -->
	<script type="text/javascript">
		var enviando = function() {
			var esseForm = $('#formCadastro');
		    if (esseForm.valid()) {
				$("#arqTitulo").val($("#titulo").val());
				$("#arqDescricao").val($("#descricao").val());
				$("#enviar").submit();
		    }else{
		    	esseForm.validate();
		    	formularErro("Formul�rio Inv�lido!");
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
					myDropzone.processQueue();
					
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
				console.log(resposta);
				myDropzone.removeAllFiles(true);
				},3000);
			
		}
	</script>
</t:rodape>