<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1><fmt:message key="contact.search.title"/></h1>

<table class="search">
    <tr>
        <th><fmt:message key="contact.form.firstName"/></th>
        <th><fmt:message key="contact.form.lastName"/></th>
        <th><fmt:message key="contact.form.telephone"/></th>
        <th><fmt:message key="contact.form.email"/></th>
        <th><fmt:message key="contact.form.action"/></th>
    </tr>
<c:forEach var="contact" items="${contacts}" varStatus="status">
    <tr>
        <c:set var="contactFormId" value="contact${status.index}"/>

        <c:url var="editUrl" value="/contact/form.html">
            <c:param name="id" value="${contact.id}" />
        </c:url>
        <sec:authorize ifAllGranted="ROLE_ADMIN">
	        <c:url var="deleteUrl" value="/contact/delete.html"/>
	        <form id="${contactFormId}" action="${deleteUrl}" method="POST">
	            <input id="id" name="id" type="hidden" value="${contact.id}"/>
	        </form>
		</sec:authorize>
    	<td>${contact.firstName}</td>
        <td>${contact.lastName}</td> 
        <td>${contact.telephone}</td>
        <td>${contact.email}</td> 
    	<td>
            <a href='<c:out value="${editUrl}"/>'><fmt:message key="button.edit"/></a>
            <sec:authorize ifAllGranted="ROLE_ADMIN">
	            <a href="javascript:document.forms['${contactFormId}'].submit();"><fmt:message key="button.delete"/></a>
            </sec:authorize> 
        </td>
    </tr>
</c:forEach>
</table>