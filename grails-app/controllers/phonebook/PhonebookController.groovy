package phonebook

import grails.validation.ValidationException

import java.sql.Timestamp
import java.text.DateFormat
import java.text.SimpleDateFormat

import static org.springframework.http.HttpStatus.*

class PhonebookController {

    PhonebookService phonebookService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond phonebookService.list(params), model: [phonebookCount: phonebookService.count()]
    }

    def show(Long id) {
        respond phonebookService.get(id)
    }

    def create() {
        respond new Phonebook(params)
    }

    def save(Phonebook phonebook) {
        if (phonebook == null) {
            notFound()
            return
        }

        try {
            phonebookService.save(phonebook)
        } catch (ValidationException e) {
            respond phonebook.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.messageOfContact', args: [message(code: 'phonebook.label', default: 'Phonebook'), phonebook.id])
                redirect phonebook
            }
            '*' { respond phonebook, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond phonebookService.get(id)
    }

    def update(Phonebook phonebook) {
        if (phonebook == null) {
            notFound()
            return
        }

        try {
            phonebookService.save(phonebook)
        } catch (ValidationException e) {
            respond phonebook.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.messageOfContact', args: [message(code: 'phonebook.label', default: 'Phonebook'), phonebook.id])
                redirect phonebook
            }
            '*' { respond phonebook, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        phonebookService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.messageOfContact', args: [message(code: 'phonebook.label', default: 'Phonebook'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    def upload = {
        request.getFile('filecsv').inputStream.splitEachLine(';') { line ->
            if (line.contains('#')) {

            } else {
                String birthdayField = line[4]
                DateFormat formatter
                formatter = new SimpleDateFormat("dd.MM.yyyy")
                Date date = (Date) formatter.parse(birthdayField.trim())
                java.sql.Timestamp timestamp = new Timestamp(date.getTime())

                def phonebook = new Phonebook(name: line[1].trim(),
                        surname: line[2].trim(), patronymic: line[3].trim(),
                        birthday: timestamp, telephone: line[5].trim(),
                        eMail: line[6].trim())

                if (phonebook.hasErrors() || phonebook.save(flush: true) == null) {
                    log.error("Could not import domainObject  ${phonebook.errors}")
                }
                log.debug("Importing domainObject  ${phonebook.toString()}")
            }
        }
        redirect(uri:'/')
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'phonebook.label', default: 'Phonebook'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
