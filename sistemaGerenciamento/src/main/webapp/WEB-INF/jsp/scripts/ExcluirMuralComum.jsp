<script type="text/javascript">
var removerMensagem = function(indice){
	var idMur = arrayConteudoTabelaMensagem()[indice].idMur;
	$("#excluirMensagemId").val(idMur);
	$("#novoExcluirMensagem").modal('show');
	return false;
}
$("#excluirMensagemSim").on("click", function(){
	$.when(apagarMensagens($("#excluirMensagemId").val())).done(function(){
		coletarMensagens();
	});
});

</script>
