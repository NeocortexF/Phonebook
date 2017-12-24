package phonebook

class AgeTagLib {
//    static defaultEncodeAs = [taglib:'html']

    static namespace = "age"

    def daysBetween = { attr ->
        out << AgeCalculator.daysBetween()
    }
}
