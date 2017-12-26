package phonebook

import grails.validation.ValidationException
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

        String birthdayField

        //!! вот оно
        request.getFile('filecsv').inputStream.splitEachLine(';') { line ->
            if(line.contains('#')){
            } else {

                birthdayField = line[4] //!!

                def phonebook = new Phonebook(name: fields[1].trim(),
                    surname: fields[2].trim(), patronymic: fields[3].trim(),
                        birthday: birthdayField, telephone: fields[5].trim(),
                    eMail: fields[6].trim())

                if (phonebook.hasErrors() || phonebook.save(flush: true) == null) {
                log.error("Could not import domainObject  ${phonebook.errors}")
            }

            log.debug("Importing domainObject  ${phonebook.toString()}")

            }
        }

//        def list = request.getFile('filecsv').inputStream.splitEachLine(';').collect {it}
//        println(list)


//        def file = request.getFile('filecsv')
//        StringBuilder sb = new StringBuilder()
//        def fs = file.getInputStream()
//
//        int ch
//        while((ch = fs.read()) != -1){
//            sb.append((char)ch)
//        }
//
//        String fileFullContent = sb.toString()
//        String[] wordArray = fileFullContent.split('$')
//
//
//        println(wordArray.toString())
//        println(wordArray.length)

//        println(wordArray[0])
//        println(wordArray[6])
//        println(wordArray[6])



//        for(int i = 0; i <= wordArray.length; i=i+7){
//            idField = wordArray[i]
//            nameField = wordArray[i+1]
//            surnameField = wordArray[i+2]
//            patronymicField = wordArray[i+3]
//            birthdayField = wordArray[i+4]
//            telephoneField = wordArray[i+5]
//            eMailField = wordArray[i+6]
//
//            println("\nСледущая сторока " + idField +" "+ nameField +" "
//            + surnameField +" " + patronymicField +" " + birthdayField +" " + telephoneField +" "+ eMailField)
//
//        }

    }




//        request.getFile('filecsv')
//                .inputStream
//                .splitEachLine(';') { fields ->
//            def phonebook = new Phonebook(name: fields[1].trim(),
//                    surname: fields[2].trim(), patronymic: fields[3].trim(),
//                    telephone: fields[5].trim(),
//                    eMail: fields[6].trim())

//birthday: fields[4].trim().toDate(),
//            if (phonebook.hasErrors() || phonebook.save(flush: true) == null) {
//                log.error("Could not import domainObject  ${phonebook.errors}")
//            }
//
//            log.debug("Importing domainObject  ${phonebook.toString()}")
//        }

//
//    }

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
