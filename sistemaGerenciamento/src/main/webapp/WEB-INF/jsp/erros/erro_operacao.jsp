<!-- Importa��o JSTLs -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Importa��o das Tags -->
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<!-- Importa��o do Cabecalho -->
<t:cabecalho>
<!-- Estiliza��o da TAG select -->
</t:cabecalho>
<!-- Importa��o do Menu Lateral e Superior -->
<c:import url="../menuSuperior.jsp"></c:import>

<!-- Se��o Principal -->
<div class="row" role="main">
   <div class="alert alert-danger" role="alert">
      <strong>Ah n�o!</strong> A opera��o que voc� realizou est� invalidada ou n�o foi autorizada corretamente.
    </div>

</div>

<!-- Scripts Espec�ficos e Importa��o do Rodap� -->
<t:rodape>
</t:rodape>