package phonebook

class Phonebook {

    static constraints = {
        name blank: false
        surname blank: false
        birthday nullable: false
        eMail email: true
    }

    String name
    String surname
    String patronymic
    Date birthday
    String telephone
    String eMail

}
