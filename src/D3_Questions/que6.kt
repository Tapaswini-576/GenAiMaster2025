package D3_Questions

fun main() {
    print("Enter a day number (1-7): ")
    val input = readLine()
    val day = input?.toIntOrNull()

    when(day) {
        1 -> println("Try our Espresso!")
        2 -> println("How about a Latte?")
        3 -> println("Cappuccino time!")
        4 -> println("Macchiato magic!")
        5 -> println("Flat White Friday!")
        6 -> println("Cold Brew Saturday!")
        7 -> println("Lazy Sunday with Mocha!")
        else -> println("Invalid day!")
    }
}
