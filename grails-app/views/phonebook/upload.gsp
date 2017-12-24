<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'phonebook.label', default: 'Phonebook')}"/>
    <title><g:message code="default.create.contact" args="[entityName]"/></title>
</head>

<body>
<g:uploadForm action="upload">
    <input type="file" name="file">
    <g:submitButton name="file" value="Upload"/>
</g:uploadForm>
</body>
</html>

