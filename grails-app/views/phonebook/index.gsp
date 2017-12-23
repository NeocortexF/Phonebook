<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'phonebook.label', default: 'Phonebook')}" />
        <title><g:message code="default.list.phonebook" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-phonebook" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.contact" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-phonebook" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.phonebook" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${phonebookList}" properties="['name', 'surname']" />

            <div class="pagination">
                <g:paginate total="${phonebookCount ?: 0}" />
            </div>
        </div>
    </body>
</html>