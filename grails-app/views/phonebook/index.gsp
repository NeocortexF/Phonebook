<!DOCTYPE html>
<%@ page import="java.sql.Date" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'phonebook.label', default: 'Phonebook')}"/>
    <title><g:message code="default.list.phonebook" args="[entityName]"/></title>
</head>

<body>
<a href="#list-phonebook" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.contact"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-phonebook" class="panel-info" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:form resource="${phonebookList}" method="GET">
        <div class="panel-body col-lg-12 col-md-12">
            <div class="table-responsive col-lg-6 col-md-6 col-sm-12 col-xs-12">
                <h1 class="h3"><g:message code="default.list.phonebook" args="[entityName]"/></h1>
        <table class="table panel-info table-bordered table-hover" id="dataTable">
            <thead>
            <tr>
                <th>ФИО</th>
                <th>Возраст</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${phonebookList}">
                <tr>
                    <td><a href="/phonebook/show/${it.id}">${it.surname} ${it.name} ${it.patronymic}</a></td>
                    <g:set var="birth" value="${it.birthday}"/>
                    <% duration = new java.util.Date().toTimestamp().minus(birth) %>
                    <% years = Math.round(duration / 365) %>
                    <td><a href="/phonebook/show/${it.id}"><%=years%></a></td>
                </tr>
            </g:each>
            </tbody>
        </table>
        </div>
    </g:form>
    <div class="panel-info col-lg-6 col-md-6 col-sm-12 col-xs-12">
        <label class="h3">Импортировать из CSV</label>
        <div class="panel-heading">
            <g:uploadForm action="upload">
                <input class="panel-info" type="file" name="filecsv"/>
                <input class="btn-info" type="submit"/>
            </g:uploadForm>
        </div>
    </div>
    <div class="pagination">
        <g:paginate total="${phonebookCount ?: 0}"/>
    </div>
</div>

</body>
</html>