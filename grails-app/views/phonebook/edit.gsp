<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'phonebook.label', default: 'Phonebook')}" />
        <title><g:message code="default.edit.contact" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-phonebook" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.phonebook" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.contact" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-phonebook" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.contact" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.phonebook}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.phonebook}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.phonebook}" method="PUT">
                <g:hiddenField name="version" value="${this.phonebook?.version}" />
                <fieldset class="form">
                    <fieldset class="form">
                        <div class="fieldcontain">
                            <div class="form-group">
                                <label for="surname">Фамилия</label>
                                <g:textField name="surname" value="${this.phonebook.surname}"/>
                            </div>

                            <div class="form-group">
                                <label for="name">Имя</label>
                                <g:textField name="name" value="${this.phonebook.name}"/>
                            </div>

                            <div class="form-group">
                                <label for="patronymic">Отчество</label>
                                <g:textField name="patronymic" value="${this.phonebook.patronymic}"/>
                            </div>

                            <div class="form-group">
                                <label for="birthday">Дата рождения</label>
                                <g:datePicker name="birthday" precision="day" value="${this.phonebook.birthday}"/>
                            </div>

                            <div class="form-group">
                                <label for="telephone">Телефонный номер</label>
                                <g:textField name="telephone" value="${this.phonebook.telephone}"/>
                            </div>
                            <div class="form-group">
                                <label for="eMail">Адрес электронной почты</label>
                                <g:textField name="eMail" value="${this.phonebook.eMail}"/>
                            </div>
                        </div>
                    </fieldset>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
