package D3_Questions

fun main(){
    val caffeine = 75
    val strength = when {
        caffeine < 30 -> "Mild"
        caffeine < 70 -> "Medium"
        else -> "Strong"
    }
    println("Your coffee strength is: $strength")

}