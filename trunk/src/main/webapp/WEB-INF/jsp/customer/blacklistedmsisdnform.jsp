<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1><fmt:message key="blacklistedmsisdn.form.title"/></h1>

<c:if test="${not empty statusMessageKey}">
    <p><fmt:message key="${statusMessageKey}"/></p>
</c:if>
<!-- <c:out value="${customer.id}"/> -->
<c:url var="url" value="/customer/newblacklistedmsisdn.html" /> 
<form action="${url}" method="post">
	<input type="hidden" name="id" value="${customer.id}"/>
	MSISDN : <input type="text" name="msisdn" />
	
	<input type="submit" value="Submit" />

</form>