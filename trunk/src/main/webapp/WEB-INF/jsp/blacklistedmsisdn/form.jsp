<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1><fmt:message key="blacklistedmsisdn.form.title"/></h1>

<c:if test="${not empty statusMessageKey}">
    <p><fmt:message key="${statusMessageKey}"/></p>
</c:if>

<c:url var="url" value="/blacklistedmsisdn/form.html" /> 

<c:out value="${customer_id}"/>

<c:url var="url" value="/blacklistedmsisdn/new.html" /> 

<form:form action="${url}">

</form:form>
<form:form action="${url}">	
    <fieldset>
    	<input name="customer_id" type="hidden" value="${customer_id}"/>
        <div class="form-row">
            <label for="msisdn"><fmt:message key="blacklistedmsisdn.form.msisdn"/>:</label>
            <span class="input"><form:input path="msisdn" /></span>
        </div>     
        <div class="form-buttons">
            <div class="button"><input name="submit" type="submit" value="<fmt:message key="button.save"/>" /></div>
        </div>
    </fieldset>
</form:form>