<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="side-bar">
    <a href="<c:url value="/"/>"><fmt:message key="index.title"/></a>

    <sec:authorize ifAllGranted="ROLE_USER">        
	    <!-- <p><fmt:message key="person.form.title"/></p>
	        <a href="<c:url value="/person/form.html"/>"><fmt:message key="button.create"/></a> 
	        <a href="<c:url value="/person/search.html"/>"><fmt:message key="button.search"/></a>
	    <p><fmt:message key="contact.form.title"/></p>
	        <a href="<c:url value="/contact/form.html"/>"><fmt:message key="button.create"/></a> 
	        <a href="<c:url value="/contact/search.html"/>"><fmt:message key="button.search"/></a>   -->   
	    <p><fmt:message key="customer.form.title"/></p>
	        <a href="<c:url value="/customer/form.html"/>"><fmt:message key="button.create"/></a> 
	        <a href="<c:url value="/customer/search.html"/>"><fmt:message key="button.list"/></a> 	          
	    <br><br><br><br>
    </sec:authorize>
</div>
