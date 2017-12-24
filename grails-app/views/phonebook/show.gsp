<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'phonebook.label', default: 'Phonebook')}" />
        <title><g:message code="default.show.details" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-phonebook" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.phonebook" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.contact" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-phonebook" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.contact" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            %{--<f:display bean="phonebook" />--}%
            <g:form resource="${this.phonebook}" method="DELETE">
                <fieldset class="form">
                    <div class="fieldcontain">
                        <div class="form-group">
                            <label for="surname">Фамилия</label>
                            <g:textField name="surname" readonly="readonly" value="${this.phonebook.surname}"/>
                        </div>

                        <div class="form-group">
                            <label for="name">Имя</label>
                            <g:textField name="name" readonly="readonly" value="${this.phonebook.name}"/>
                        </div>

                        <div class="form-group">
                            <label for="patronymic">Отчество</label>
                            <g:textField name="patronymic" readonly="readonly" value="${this.phonebook.patronymic}"/>
                        </div>

                        <div class="form-group">
                            <label for="birthday">Дата рождения</label>
                            <g:datePicker name="birthday" precision="day" disabled="disabled" value="${this.phonebook.birthday}"/>
                        </div>

                        <div class="form-group">
                            <label for="telephone">Телефонный номер</label>
                            <g:textField name="telephone" readonly="readonly" value="${this.phonebook.telephone}"/>
                        </div>
                        <div class="form-group">
                            <label for="eMail">Адрес электронной почты</label>
                            <g:textField name="eMail" readonly="readonly" value="${this.phonebook.eMail}"/>
                        </div>
                    </div>
                </fieldset>

                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.phonebook}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
