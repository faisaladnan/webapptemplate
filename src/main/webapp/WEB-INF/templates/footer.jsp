<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div align="right">
    <div>
        <fmt:message key="button.locale"/>:
            <c:url var="englishLocaleUrl" value="${requestScope.requestURL}">
                <c:param name="locale" value="en" />
            </c:url>
            <c:url var="deutschLocaleUrl" value="${requestScope.requestURL}">
                <c:param name="locale" value="de" />
            </c:url>
        
            <a href='<c:out value="${englishLocaleUrl}"/>'><fmt:message key="locale.english"/></a>
            <a href='<c:out value="${deutschLocaleUrl}"/>'><fmt:message key="locale.deutsch"/></a>
    </div>
    
    <div>&nbsp;</div>
    
    <div><fmt:message key="site.footer"/></div>
</div>