<!-- Javascript de Telefone -->
<script type="text/javascript">
var remover_tel = function(i){
	$("#div_tel_"+i).remove();	
};
var n_telefones = 0;
$(".div_telefone").each(function(i,e)
		{
			n_telefones += 1;
		});
var adicionar_telefone = function(){
	return adicionar_telefones("", "", "");
}
var removerTelefones = function(){
	$("#telefones").find(".div_telefone").remove().end();
}
var adicionar_telefones = function(ddd_esc, num_esc, operadora_esc){
	$("#telefones").append("<div id='div_tel_"+n_telefones+"' class='div_telefone col-xs-offset-2 col-xs-8'>"+
			"<div class='col-sm-2'><label for='telefone_ddd"+n_telefones+"'>DDD:</label>"+
		     "<input type='text' name='telefones["+n_telefones+"].ddd' id='telefone_ddd_"+n_telefones+"'"+
		      "class='form-control ddd_telefone'/></div>"+
			"<div class='col-sm-4'>"+
            "<label for='telefone"+n_telefones+"'>Telefone:</label>"+
    "<input type='text' name='telefones["+n_telefones+"].numero' id='telefone"+n_telefones+"'"+
     "class='form-control sp_celphones numero_telefone'/>"+
     "</div>"+
	"<div class='col-sm-3'><label>Operadora:</label>"+  
    "<select class='input-large form-control' id='slct_telefone_op_"+n_telefones+"' "+
    "name='telefones["+n_telefones+"].operadora'>"+
     "<option id='telefone_op_"+n_telefones+"' value=''>Selecione Uma Operadora</option>"+
          "<option id='Tim_"+n_telefones+"' value='Tim'>Tim</option>"+
         "<option id='Claro_"+n_telefones+"' value='Claro'>Claro</option>"+
          "<option id='Vivo_"+n_telefones+"' value='Vivo'>Vivo</option>"+
          "<option id='Nextel_"+n_telefones+"' value='Nextel'>Nextel</option>"+
          "<option id='CTBC_"+n_telefones+"' value='CTBC'>CTBC</option>"+
          "<option id='Outras_"+n_telefones+"' value='Outras'>Outras</option>"+
    "</select>"+
"</div><div class='col-sm-3'><label>Apagar Telefone</label><button type='button' class='btn btn-danger'"+
	" onclick=remover_tel("+n_telefones+")>"+
 "<span class=' glyphicon glyphicon-remove-circle'></span>Apagar</button></div></div>");
$("#telefone_ddd_"+n_telefones).val(ddd_esc);
$("#telefone"+n_telefones).val(num_esc);
$("#slct_telefone_op_"+n_telefones).val(operadora_esc);
n_telefones++;
return false;
}
</script>
<!-- Máscaras de Telefones -->

<script type="text/javascript">
var SPMaskBehavior = function (val) {
    return val.replace(/\D/g, '').length === 11 ? '00000-0000' : '0000-00009';
  },
  spOptions = {
    onKeyPress: function(val, e, field, options) {
        field.mask(SPMaskBehavior.apply({}, arguments), options);
      }
  };

  $('.sp_celphones').mask(SPMaskBehavior, spOptions);
  $('.ddd_telefone').mask('00');
  
</script>