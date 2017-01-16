<!-- Importação JSTLs -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Importação das Tags -->
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<!-- Importação do Cabecalho -->
<t:cabecalho>
<!-- Estilização da TAG select -->
</t:cabecalho>
<!-- Importação do Menu Lateral e Superior -->
<c:import url="../menuSuperior.jsp"></c:import>

<!-- Seção Principal -->
<div class="row" role="main">
   <div class="alert alert-danger" role="alert">
      <strong>Ah não!</strong> A operação que você realizou está invalidada ou não foi autorizada corretamente.
    </div>

</div>

<!-- Scripts Específicos e Importação do Rodapé -->
<t:rodape>
</t:rodape>