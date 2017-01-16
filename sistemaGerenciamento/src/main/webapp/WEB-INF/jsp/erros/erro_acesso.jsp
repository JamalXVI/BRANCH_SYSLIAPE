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
<div class="right_col" role="main">

   <div class="alert alert-danger" role="alert">
      <strong>Ah não!</strong> Você não tem direitos para acessar essa página.
    </div>
</div>
<t:rodape>
<!-- Importação de Scripts da Página -->
</t:rodape>