<!-- Importa��o JSTLs -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Importa��o das Tags -->
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<!-- Importa��o do Cabecalho -->
<t:cabecalho>
<!-- jQuery UI-->
<link href="<c:url value="/assets/css/jquery-ui.css"/>" rel="stylesheet">
<!-- Estiliza��o da TAG select -->
<style>
</style>
</t:cabecalho>
<!-- Importa��o do Menu Lateral e Superior -->
<c:import url="../menuSuperior.jsp"></c:import>
<div class="col-lg-12">

<div class="row" role="main">
		<h1 class="page-header" style="margin-top: 0px;">
			Nova <small>Mensagem</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="${linkTo[HomeController].index()}">P�gina Inicial</a></li>
			<li class="active">Mensagens</li>
		</ol>
		<form action="${linkTo[MensagemController].enviar(null) }" method="post"
		id="formReserva">
			<t:input divCol="col-xs-12"
			 id="mensagemPara" nome="mensagem.para" nomeLabel="Para:" valorPadrao=""
			  tipo="text" classes=""></t:input>
			  <t:input divCol="col-xs-12"
			 id="mensagemTitulo" nome="mensagem.titulo" nomeLabel="T�tulo:" valorPadrao=""
			  tipo="text" classes=""></t:input>
			 <div class="col-xs-12">
			 <label for="mensagem.texto">Texto:</label>
			 <textarea rows="5" cols="" name="mensagem.mensagem" class="form-control"></textarea>
			 </div>
			 <button type="submit" class="btn btn-primary btn-enviar" id="enviarMensagem">Enviar</button>
		</form>
</div>
	
</div>
<!-- Scripts Espec�ficos e Importa��o do Rodap� -->
<t:rodape>
<!-- jQuery UI-->
<script src="<c:url value='/assets/js/jquery-ui.js' />"></script>
<!-- Listar Usu�rios para Mensagens -->
<script type="text/javascript">
var usuarios;
//Listar Todos os Usu�rios e fazer Requisi��o Ajax da Lista
var listarUsuarios = function(){
	return $.ajax({  
	    type:"post",  
	    url: "${linkTo[UsuarioController].listar() }", 
	    dataType: "json",  // Isso diz que voc� espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	usuarios = data;
	    	//criarPermissoes();
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso � um JSON
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

</script>
</t:rodape>