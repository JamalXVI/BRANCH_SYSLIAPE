<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="nome" required="true" %>
<%@ attribute name="genero" required="true" %>
<%@ attribute name="nomeLabel" required="true" %>
<%@ attribute name="semSalvar" required="false"   %>
<%@ attribute name="fecharSalvar" required="false" type="java.lang.String" %>
<div class="modal fade" id="nov${genero }${nome}" role="dialog" aria-labelledby="nov${genero }${nome}Label" data-focus-on="input:first">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="nov${genero }${nome}Label">${nomeLabel }:</h4>
      </div>
      <div class="modal-body">
      	<div class='row'>
        	<jsp:doBody></jsp:doBody>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
        <c:if test="${semSalvar == null || semSalvar != 0  }">
        	<button type="button" class="btn btn-primary"
        	<c:if test="${empty fecharSalvar}" >
        	 data-dismiss="modal"
        	 </c:if>
        	 onclick="cadastrar${nome}();">Salvar</button>
        	
		</c:if>      
      </div>
    </div>
  </div>
</div>