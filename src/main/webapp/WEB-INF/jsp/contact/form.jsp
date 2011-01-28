<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1><fmt:message key="contact.form.title"/></h1>

<c:if test="${not empty statusMessageKey}">
    <p><fmt:message key="${statusMessageKey}"/></p>
</c:if>

<c:url var="url" value="/contact/form.html" /> 
<form:form action="${url}" commandName="contact">
    <form:hidden path="id" />

    <fieldset>
        <div class="form-row">
            <label for="firstName"><fmt:message key="contact.form.firstName"/>:</label>
            <span class="input"><form:input path="firstName" /></span>
        </div>       
        <div class="form-row">
            <label for="lastName"><fmt:message key="contact.form.lastName"/>:</label>
            <span class="input"><form:input path="lastName" /></span>
        </div>
        <div class="form-row">
            <label for="telephone"><fmt:message key="contact.form.telephone"/>:</label>
            <span class="input"><form:input path="telephone" /></span>
        </div>        
        <div class="form-row">
            <label for="email"><fmt:message key="contact.form.email"/>:</label>
            <span class="input"><form:input path="email" /></span>
        </div>          
        <div class="form-buttons">
            <div class="button"><input name="submit" type="submit" value="<fmt:message key="button.save"/>" /></div>
        </div>
    </fieldset>
</form:form>