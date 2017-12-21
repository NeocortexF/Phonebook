package phonebook

import grails.gorm.services.Service

@Service(Phonebook)
interface PhonebookService {

    Phonebook get(Serializable id)

    List<Phonebook> list(Map args)

    Long count()

    void delete(Serializable id)

    Phonebook save(Phonebook phonebook)

}