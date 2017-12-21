package phonebook

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PhonebookServiceSpec extends Specification {

    PhonebookService phonebookService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Phonebook(...).save(flush: true, failOnError: true)
        //new Phonebook(...).save(flush: true, failOnError: true)
        //Phonebook phonebook = new Phonebook(...).save(flush: true, failOnError: true)
        //new Phonebook(...).save(flush: true, failOnError: true)
        //new Phonebook(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //phonebook.id
    }

    void "test get"() {
        setupData()

        expect:
        phonebookService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Phonebook> phonebookList = phonebookService.list(max: 2, offset: 2)

        then:
        phonebookList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        phonebookService.count() == 5
    }

    void "test delete"() {
        Long phonebookId = setupData()

        expect:
        phonebookService.count() == 5

        when:
        phonebookService.delete(phonebookId)
        sessionFactory.currentSession.flush()

        then:
        phonebookService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Phonebook phonebook = new Phonebook()
        phonebookService.save(phonebook)

        then:
        phonebook.id != null
    }
}
