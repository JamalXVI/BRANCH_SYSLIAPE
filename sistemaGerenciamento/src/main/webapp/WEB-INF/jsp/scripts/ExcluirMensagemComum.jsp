<script type="text/javascript">
var removerMensagem = function(indice){
	var idRec = arrayConteudoTabelaMensagem()[indice].idRec;
	$("#excluirMensagemId").val(idRec);
	$("#novoExcluirMensagem").modal('show');
	return false;
}
$("#excluirMensagemSim").on("click", function(){
	$.when(apagarMensagens($("#excluirMensagemId").val())).done(function(){
		coletarMensagens();
	});
});

</script>
