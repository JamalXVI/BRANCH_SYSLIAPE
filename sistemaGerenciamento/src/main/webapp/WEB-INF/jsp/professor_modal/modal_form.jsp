<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_geralLg nomeLabel="Professor" nome="Professor" genero="o">
	<div class='row'>
		<form action="${linkTo[ProfessorController].postar(null, null, null) }"
			method="post" id="form">
	
			<!-- Seção de Pessoa -->
			<!-- Nome e Sobrenome -->
			<input type='hidden' id="formIdPessoa" name="professor.idPes"/>
				<t:input divCol="col-xs-12 col-sm-6" nomeLabel="Nome:"
					nome="professor.pessoa.nome" tipo="text"
					valorPadrao="${professor.pessoa.nome }" id="nome"></t:input>
	
				<t:input divCol="col-xs-12 col-sm-6" nomeLabel="Sobrenome:"
					nome="professor.pessoa.sobrenome" tipo="text"
					valorPadrao="${professor.pessoa.sobrenome }" id="sobrenome"></t:input>
			<!-- Email e Data de Nascimento -->
				<t:input divCol="col-xs-12 col-sm-6" nomeLabel="Email:"
					nome="professor.pessoa.email" tipo="email"
					valorPadrao="${professor.pessoa.email }" id="email"></t:input>
				<t:input divCol="col-xs-12 col-sm-6" nomeLabel="Data de Nascimento:"
					nome="datanascimento" tipo="date"
					valorPadrao="${professor.pessoa.datanascimento}" id="dataNascimento"></t:input>
			<!-- Sessão de Professor -->
	   	<t:input valorPadrao="${professor.codigo }" divCol="col-xs-12 col-sm-6" 
	   	nomeLabel="Código do Professor" nome="professor.codigo"
	   	 tipo="text" id="codigoProfessor" maxLenght="5"></t:input>
			
	 <div class="col-xs-12 col-sm-4">
	 <a href=""
	       href="#" id="btn_telefone"
	       class="fa fa-phone-square fa-3x botao_confirmar" 
	       style="color: green;" onclick="return adicionar_telefone();" >
	</a>
	 </div>
			<div class="col-sm-12" id="telefones"></div>
		</form>
	</div>
</t:modal_geralLg>