fun main() {
    var intExample : Int = 0
//     var a = 10.5;
//     a = 20.5
//     val b = 10;
//     val sum = a + b;
//     println(a);
//     println(sum);

    var name = "Andriod"
    println("Welcome to ${name +" Jetpack"}")
    
    val max = if(5>3) 5 else 3
    println(max)

    //Switch
    val day = 5 
    val dayName = when(day){
        1 -> "Monday"
        2 -> "Tueday"
        3 -> "Wednesday"
        4 -> "Thurday"
        5 -> "Friday"
        6 -> "Saturday"
        7 -> "Sunday"
        else -> "Invalid day"
    }
    println(dayName)
    
    //loop
    for (i in 1 until 5) {
// 	for (j in 1 ... 5){
//                 println(${i+j})}
    }
    
    //
    val numbers = listOf(1,2,3,4)
    for(n in numbers){
        println(n)
    }
    
    //
    val num = 123421
    var a = num
    var b = 0
    while(a!=0){
        val c = a % 10
        b = b*10 + c
        a /= 10
    }
        if(num==b){
            println("palindrome")
        }
}