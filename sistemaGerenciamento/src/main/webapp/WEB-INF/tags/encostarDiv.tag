<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="divCol" required="true"%>
<%@ attribute name="divColmd" required="false"%>


<div class="col-xs-offset-<fmt:formatNumber type='number' 
            minFractionDigits='0' value='${(12 - divCol)}' /> col-xs-${divCol} <c:if test="${divColmd != 0 && divColmd != null}">
col-md-offset-<fmt:formatNumber type='number' 
            minFractionDigits='0' value='${(12 - divColmd)}' /> col-md-${divColmd}
</c:if>" >
<jsp:doBody></jsp:doBody>
</div>