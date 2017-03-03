<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="nome" required="true" %>
<%@ attribute name="classes" required="false" %>
<%@ attribute name="nomeLabel" required="true" %>
<%@ attribute name="divCol" required="true" %>
<%@ attribute name="tipo" required="true" %>
<%@ attribute name="valorPadrao" required="true" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="maxLenght" required="false" %>
<%@ attribute name="editavel" required="false"  %>
<%@ attribute name="placeholder" required="false"  %>

<div class="${divCol }">
	<label for="${nome }" id="label${id}">${ nomeLabel }</label>
	<input type="${tipo}" class="form-control ${classes }" id="${id }"
	 value="${valorPadrao }" name="${nome }" <c:if test="${maxLenght != null && maxLenght != 0 }">
 		maxlength="${maxLenght }"
	 </c:if> 
	 	placeholder="${placeholder }"
	   <c:if test="${editavel != null && editavel != 0 }">
 		readonly="readonly"
	 </c:if> />
</div>

