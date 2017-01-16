<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_sem_save nomeLabel="Visualizar Professor" nome="VerProfessor" genero="o">
	<div class='row'>
		<t:input divCol="col-xs-12 col-md-6" 
		id="nomePessoaVer" nome="" nomeLabel="Nome:" valorPadrao="" tipo="text" editavel="1">
		</t:input>
		<t:input divCol="col-xs-12 col-md-6" 
		id="sobrenomePessoaVer" nome="" nomeLabel="Sobrenome:" valorPadrao="" tipo="text"
		 editavel="1"></t:input>
		 <t:input divCol="col-xs-12 col-md-6" 
		id="emailPessoaVer" nome="" nomeLabel="Email:" valorPadrao="" tipo="text"
		 editavel="1"></t:input>
		 <t:input divCol="col-xs-12 col-md-6" 
		id="dataNascimentoVer" nome="" nomeLabel="Data de Nascimento:" valorPadrao="" tipo="text"
		 editavel="1"></t:input>
		 <t:input divCol="col-xs-12 col-md-6" 
		id="codigoVer" nome="" nomeLabel="Código:" valorPadrao="" tipo="text"
		 editavel="1"></t:input>
		 <div class="col-xs-12" id="verTelefones"></div>
	</div>
</t:modal_sem_save>