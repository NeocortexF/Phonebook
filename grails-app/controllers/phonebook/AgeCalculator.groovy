package phonebook

class AgeCalculator {

        static daysBetween(String date) {

            def birthday = date
            println(birthday)
            def today = Calendar.instance

            return today.timeInMillis.toBigInteger()
        }
}
