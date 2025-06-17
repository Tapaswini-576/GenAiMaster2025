package D3_Questions

fun main(){
    var cupSize1 = "Small"
    var cupSize2 = "Large"
    val temp = cupSize1
    cupSize1 = cupSize2
    cupSize2 = temp
    println("Cup sizes swapped: $cupSize1, $cupSize2")

}