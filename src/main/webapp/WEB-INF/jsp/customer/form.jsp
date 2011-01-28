<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1><fmt:message key="customer.form.title"/></h1>

<c:if test="${not empty statusMessageKey}">
    <p><fmt:message key="${statusMessageKey}"/></p>
</c:if>

<c:url var="url" value="/customer/form.html" /> 
<form:form action="${url}" commandName="customer">
    <form:hidden path="id" />

    <fieldset>
        <div class="form-row">
            <label for="customerName"><fmt:message key="customer.form.customerName"/>:</label>
            <span class="input"><form:input path="customerName" /></span>
        </div>       
        <div class="form-row">
            <label for="address"><fmt:message key="customer.form.address"/>:</label>
            <span class="input"><form:input path="address" /></span>
        </div>      
        <div class="form-buttons">
            <div class="button"><input name="submit" type="submit" value="<fmt:message key="button.save"/>" /></div>
        </div>
    </fieldset>
</form:form>