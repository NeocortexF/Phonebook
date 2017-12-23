package phonebook

class Phonebook {

    static constraints = {
        name blank: false
        surname blank: false
        birthday nullable: false
        eMail email: true, blank: true
        patronymic blank: true
        telephone blank: true
    }

    String name
    String surname
    String patronymic
    Date birthday
    String telephone
    String eMail
}
