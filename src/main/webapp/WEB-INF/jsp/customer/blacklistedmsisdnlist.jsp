<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1><fmt:message key="blacklistedmsisdn.form.list"/></h1>
<c:url var="newBlacklistMSISDNUrl" value="/customer/blacklistedmsisdnform.html">
	<c:param name="id" value="${customer.id}" />
</c:url>
<a href='<c:out value="${newBlacklistMSISDNUrl}"/>'><fmt:message key="blacklistedmsisdn.form.newmsisdn"/></a>
<table class="search">
    <tr>
        <th><fmt:message key="blacklistedmsisdn.form.msisdn"/></th>
        <th><fmt:message key="blacklistedmsisdn.form.action"/></th>
    </tr>
<c:forEach items="${customer.blacklistedMsisdns}" var="list" varStatus="status">
	<c:set var="delBlacklistMSISDNFormId" value="delBlacklistMSISDN${status.index}"/>
	<c:url var="delBlacklistUrl" value="/customer/deleteblacklistedmsisdn.html"/>
	<form id="${delBlacklistMSISDNFormId}" action="${delBlacklistUrl}" method="POST">
	   <input id="customerId" name="customerId" type="hidden" value="${customer.id}"/>
	   <input id="blacklistedMSISDNId" name="blacklistedMSISDNId" type="hidden" value="${list.id}"/>
	</form>	
    <tr><td align="left" >
      <c:out value="${list.msisdn}" />
    </td>
    <td>
			<sec:authorize ifAllGranted="ROLE_ADMIN">            
	            <a href="javascript:document.forms['${delBlacklistMSISDNFormId}'].submit();"><fmt:message key="button.delete"/></a>
            </sec:authorize>  	
	</td>
    </tr>
</c:forEach>
</table>