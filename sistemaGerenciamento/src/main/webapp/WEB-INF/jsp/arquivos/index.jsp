<!-- Importação JSTLs -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Importação das Tags -->
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!-- Importação do Cabecalho -->
<t:cabecalho>
	<!-- Estilização da TAG select -->
</t:cabecalho>
<!-- Importação do Menu Lateral e Superior -->
<c:import url="../menuSuperior.jsp"></c:import>

<!-- Seção Principal -->
<div class="row col-xs-12" role="main">
	<h1 class="page-header" style="margin-top: 0px;">
		Principal <small>Arquivos</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="${linkTo[HomeController].index()}">Página
				Inicial</a></li>
		<li class="active">Arquivos</a>
		</li>
	</ol>
	<div class="col-xs-12">
		<!-- 		<label for="pesquisarProf">Pesquisar Usuário</label> -->
		<div class="col-xs-6 col-md-4">
			<input class="form-control" placeholder="Filtrar" id="pesquisarArq"
				name="pesquisarArq" />
		</div>

		<div class="col-xs-3 col-md-2">
			<a href="${linkTo[ArquivosController].novo() }"
				id="btnInserirArquivo"
				class="fa fa-plus-square fa-2x botao_confirmar cor_verde permissao"></a>
		</div>
	</div>
	<div id="listaArquvivos" class="col-xs-12"></div>
</div>
<!-- Scripts Específicos e Importação do Rodapé -->
<t:rodape>
	<script type="text/javascript">
		var filtroArq = "";
		$("#pesquisarArq").on("change", function() {
			filtrar();
		});
		$("#pesquisarArq").on("type", function() {
			filtrar();
		});
		$("#pesquisarArq").on("tap", function() {
			filtrar();
		});
		$("#pesquisarArq").on("keyup", function() {
			filtrar();
		});
		$("#pesquisarArq").on("keydown", function() {
			filtrar();
		});
		var filtrar = function() {

			filtroArq = $("#pesquisarArq").val().toLowerCase();
			fazerListaArquivo();
		}
	</script>
	<script type="text/javascript">
		var arquivos;
		var paginaAtual = 1;
		var paginas = 1;
		var conteudoTabelaArq = [];
		var numeroElementos = 10;
		var listarArquivos = function() {
			return $.ajax({
				type : "post",
				url : "${linkTo[ArquivosController].listar() }",
				dataType : "json", // Isso diz que você espera um JSON do servidor
				beforeSend : function(xhr, settings) {
				},
				success : function(data, textStatus, xhr) {
					arquivos = data;
					//criarPermissoes();
				}, // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
				error : function(xhr, textStatus, errorThrown) {
					tratarErroAjax(xhr, textStatus, errorThrown);
				}
			});
		};
		var fazerListaArquivo = function() {
			$("#listaArquvivos").find("#panel_paginacao_tabela").remove().end();
			//Adicionar Cabeçalho na Tabela
			$("#listaArquvivos")
					.append(
							"<div class='' id='panel_paginacao_tabela'>"
									+ "<div class='panel-body'>"
									+ "<div class='table-responsive espaco_cima'>"
									+ "<table id='tabelaListaProfessores' "+
					"class='table table-striped table-hover table-bordered'><thead class='thead-inverse'"+
					"style='background: #1e282c;'>"
									+ "<tr><th class='my-table-label'>Arquivo</th>"
									+ "<th class='my-table-label'>Descrição</th>"
									+ "<th class='my-table-label'>Ação</th>"
									+"</thead><tbody id='corpotabelaListaArquivos'><tbody></table></div>"
									+ "<div class='' id='rodapeTabelaPaginacao'></div></div></div>");
			conteudoTabelaArq = [];
			$(arquivos)
					.each(
							function(i, arquivo) {
								//Verificar se o Professor está no Filtro
								if (arquivo.descricao.toLowerCase().indexOf(
										filtroArq) >= 0
										|| arquivo.titulo.toLowerCase()
												.indexOf(filtroArq) >= 0) {
									conteudoTabelaArq.push(arquivo);
								}

							});
			paginas = Math.ceil(conteudoTabelaArq.length / numeroElementos);
			//"<li><a href=''>1</a></li>"+
			$("#rodapeTabelaPaginacao").append(
					"<ul class='pagination' id='paginacaoTabelaNumero'>"
							+ "</ul>");
			criarPaginas();
			mudarPagina(1);
		}
		var criarPaginas = function() {
			for (var j = 1; j < paginas + 1; j++) {
				$("#paginacaoTabelaNumero").append(
						"<li><a href='' onclick='return mudarPagina(\"" + j
								+ "\")'>" + j + "</a></li>");
			}
		}
		var mudarPagina = function(k) {
			paginaAtual = k;
			$("#corpotabelaListaArquivos").find("tr").remove().end();
			$(conteudoTabelaArq).each(function(j, e) {
				var antes = (paginaAtual - 1) * numeroElementos;
				if (antes <= j) {
					if (j <= paginaAtual * numeroElementos - 1) {
						adicionarArquivoTabela(e, j);
					}
				}
			});
			return false;
		}
		var adicionarArquivoTabela = function(arquivo, i) {
			$("#corpotabelaListaArquivos").append(
					"<tr><td><a href='${linkTo[ArquivosController].download()}"
							+ arquivo.id
							+ "' rel='nofollow' download target='_blank'>"
							+ arquivo.titulo + "</a></td><td>"
							+ arquivo.descricao + "</td><td>"
							+criarTextoAcao("excluirArquivo", "fa-trash", arquivo.id)
							+"<a href='${linkTo[ArquivosController].editar()}"+arquivo.id
							+"' style='margin-left:10px;'"+
							"class='fa fa-2x fa-pencil' ></a>"
							+"<a href='${linkTo[ArquivosController].historico()}"+arquivo.id
							+"' style='margin-left:10px;'"+
							"class='fa fa-2x fa-history' ></a>");
		}
		var excluirArquivo = function(id){
			$.when(requisicaoAjax(id)).done(function(){
				atualizarLista();
			});
			return false;
		};
		var requisicaoAjax = function(id){
			return $.ajax({
				type : "post",
				url : "${linkTo[ArquivosController].remover() }",
				data: {"id" : id},
				dataType : "json", // Isso diz que você espera um JSON do servidor
				beforeSend : function(xhr, settings) {
				},
				success : function(data, textStatus, xhr) {
					if (data.mensagem.indexOf("Erro") !== -1) {
			    		formularErro(data.mensagem);
			    		reservaErro = true;
					}else{
						mensagemSucesso();
					}
					//criarPermissoes();
				}, // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
				error : function(xhr, textStatus, errorThrown) {
					tratarErroAjax(xhr, textStatus, errorThrown);
				}
			});
		}
		var criarTextoAcao = function(funcao, icone, id) {
			return "<a href='' style='margin-left:10px;' onclick='return "
					+ funcao + "(\"" + id
					+ "\")'><i class='fa fa-2x "+icone+"'></i></a>";
		}
		var inciarDownloadArquivo = function(i) {
			caminho = "" + arquivos[i].id;
			window.open(caminho, 'Download');
			return false;
		}
		var atualizarLista = function(){
			$.when(listarArquivos()).done(function() {
				fazerListaArquivo();
			});
		}
		$(document).ready(function() {
			atualizarLista();
		});
	</script>
</t:rodape>