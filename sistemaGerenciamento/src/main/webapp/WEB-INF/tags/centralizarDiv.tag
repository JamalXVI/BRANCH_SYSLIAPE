<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="divCol" required="true"%>
<%@ attribute name="divColmd" required="false"%>
<%@ attribute name="classes" required="false"%>


<div class="col-xs-offset-<fmt:formatNumber type='number' 
            minFractionDigits='0' value='${((12 - divCol)/2)}' /> col-xs-${divCol} <c:if test="${divColmd != 0 && divColmd != null}">
col-md-offset-<fmt:formatNumber type='number' 
            minFractionDigits='0' value='${((12 - divColmd)/2)}' /> col-md-${divColmd}
</c:if> ${classes }">
<jsp:doBody></jsp:doBody>
</div>