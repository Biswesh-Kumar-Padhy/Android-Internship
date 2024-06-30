
fun main() {
    //higher order functions
    val numberList = listOf(1,2,3,4)
    val newList = numberList.map {
        println("I am a member of this list: $it") //transform each element in the list
        it*it
    }
    println(newList)
    val newList2 = numberList.filter { it % 2 != 0 }//filters aot according to the criteria
    println(newList2)
    numberList.forEach{ print(it) }
    println()
    val showEven = numberList.find { it % 2 == 0 } //finds or
    println(showEven)
    //Null-Safe ?.
    var b: Int? = 1 //can be set to null
    b = null //ok
    if (b == null) {
        println("Error: Using null")
    }
    val l = if (b != null) "Not null" else b
    println(l)
    //Null assertion !!.
    val a: String? = "abc"
    println(a!!.length)


}