<script type="text/javascript">
var arrayConteudoTabelaMensagem = function(){
	return $(conteudoTabelaMensagem).get().reverse();
}
</script>
<script type="text/javascript">
	var mensagens;
	var paginaAtual = 1;
	var paginas = 1;
	var numeroElementos = 10;
	var coletarMensagens = function() {
		$.when(receberMensagens()).done(function() {
			$("#corpoTabelaMensagens").find('tr').remove().end();
			conteudoTabelaMensagem = [];
			$(mensagens).each(function(i, mensagem) {
				conteudoTabelaMensagem.push(mensagem);

			});
			paginas = Math.ceil(conteudoTabelaMensagem.length/ numeroElementos);
			$("#rodapeTabelaPaginacao").find("ul").remove().end();
			$("#rodapeTabelaPaginacao").append("<ul class='pagination'"+
			" id='paginacaoTabelaNumero'></ul>");
			criarPaginas();
			mudarPagina(1);
		});
	}
	var criarPaginas = function() {
		for (var j = 1; j < paginas + 1; j++) {
			$("#paginacaoTabelaNumero").append(
					"<li><a href=''" + " onclick='return mudarPagina(\"" + j
							+ "\")'>" + j + "</a></li>");
		}
	}
	var mudarPagina = function(k) {
		paginaAtual = k;
		$("#corpoTabelaMensagens").find("tr").remove().end();
		$(arrayConteudoTabelaMensagem()).each(function(j, e) {
			var antes = (paginaAtual - 1) * numeroElementos;
			if (antes <= j) {
				if (j <= paginaAtual * numeroElementos - 1) {
					cadastrarMensagem(j, e);
				}
			}
		});
		return false;
	}
	$( document ).ready(function() {
		coletarMensagens();
	});
</script>
<script type="text/javascript">
	var cadastrarMensagem = function(i, mensagem) {
		dia = mensagem.data.split("T")[0];
		hora = mensagem.data.split("T")[1].split("-")[0];
		var dataMensagem = dia + " - " + hora;
		var nova = "";
		if (!mensagem.visualizado) {
			nova = " <small style='color:red'>[NOVA]</small>";
		}
		$("#corpoTabelaMensagens")
				.append(
						"<tr>"
								+ "<td class='my-table-label-body'>"
								+ dataMensagem
								+ "</td>"
								+ "<td class='my-table-label-body'>"
								+ "<a href='' onclick='return visualizarMensagem(\""
								+ i
								+ "\")'>"
								+ mensagem.titulo
								+ nova
								+ "</a>"
								+ "</td>"
								+ "<td class='my-table-label-body'>"
								+ mensagem.usuarioRemetente
								+ "</td>"
								+ "<td class='my-table-label-body'>"
								+ "<a href='' onclick='return visualizarMensagem(\""
								+ i
								+ "\")'><i class='fa fa-search' aria-hidden='true'></i></a>"
								+ "<a href='' onclick='return removerMensagem(\""
								+ i
								+ "\")'><i class='fa fa-trash' aria-hidden='true'></i></a>"
								+ "</td>" + +"</tr>");
	}
</script>
<script type="text/javascript">
	var visualizarMensagem = function(indice) {
		var mensagem = arrayConteudoTabelaMensagem()[ indice ];
		$("#idRecEnviar").val(mensagem.idRec);
		$("#verMensagem").submit();
		return false;
	};
</script>

