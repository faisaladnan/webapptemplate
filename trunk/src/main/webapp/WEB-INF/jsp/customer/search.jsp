<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1><fmt:message key="customer.search.title"/></h1>

<table class="search">
    <tr>
        <th><fmt:message key="customer.form.customerName"/></th>
        <th><fmt:message key="customer.form.address"/></th>
        <th><fmt:message key="customer.form.action"/></th>
    </tr>
<c:forEach var="customer" items="${customers}" varStatus="status">
    <tr>
        <c:set var="deleteCustomerFormId" value="deleteCustomer${status.index}"/>
		<c:set var="addBlacklistMSISDNFormId" value="addBlacklistMSISDN${status.index}"/>
		
        <c:url var="editUrl" value="/customer/form.html">
            <c:param name="id" value="${customer.id}" />
        </c:url>
       	<c:url var="newBlacklistMSISDNUrl" value="/customer/blacklistedmsisdnform.html">
            <c:param name="id" value="${customer.id}" />
        </c:url>
		<c:url var="viewBlacklistMSISDNUrl" value="/customer/blacklistedmsisdnlist.html">
            <c:param name="id" value="${customer.id}" />
        </c:url>        
        <sec:authorize ifAllGranted="ROLE_ADMIN">        
	        <c:url var="deleteUrl" value="/customer/delete.html"/>
	        <form id="${deleteCustomerFormId}" action="${deleteUrl}" method="POST">
	            <input id="id" name="id" type="hidden" value="${customer.id}"/>
	        </form>
	        <c:url var="newBlacklistUrl" value="/blacklistedmsisdn/new.html"/>
	        <form id="${addBlacklistMSISDNFormId}" action="${newBlacklistUrl}" method="POST">
	            <input id="id" name="id" type="hidden" value="${customer.id}"/>
	        </form>	 	        
		</sec:authorize>
    	<td>${customer.customerName}</td>
        <td>${customer.address}</td> 
    	<td>
            <a href='<c:out value="${editUrl}"/>'><fmt:message key="button.edit"/></a>
            <a href='<c:out value="${viewBlacklistMSISDNUrl}"/>'><fmt:message key="blacklistedmsisdn.form.view"/></a>
            	        
       		<a href='<c:out value="${newBlacklistMSISDNUrl}"/>'><fmt:message key="blacklistedmsisdn.form.newmsisdn"/></a>
			<sec:authorize ifAllGranted="ROLE_ADMIN">            
	            <a href="javascript:document.forms['${deleteCustomerFormId}'].submit();"><fmt:message key="button.delete"/></a>
            </sec:authorize>        		
        </td>
    </tr>
</c:forEach>
</table>