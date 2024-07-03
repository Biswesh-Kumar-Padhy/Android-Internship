//import kotlinx.coroutines.*
//Lambdas concise functions treated as a value, pass as argument and can be returned
//val sum = { x: Int, y: Int -> x+y }
//Lambdas with receivers: call on an object w/o actually referencing it
//main default io unconfined
fun main() { //= runBlocking
    val sum = {
        x: Int, y: Int -> x+y
    }
    val mul = {
            x: Int, y: Int -> x*y
    }

    println(sum(2, 5))
    println(mul(2, 5))
    println(operation(2, 7){
        x, y -> x+y
    })
    println(operation(2, 7, mul))

    val list = listOf(1,2,3,5)
    println(list.map { it*it })

    val string1 = StringBuilder().apply{
        append("Silicon ")
        append("Institute")
    }
    println(string1)

    //TypeAlias
    val sumT: operation = {x, y -> x+y}
    val mulT: operation = {x, y -> x*y}
    println(sumT(3, 5))
    println(mulT(3, 5))

    //Unit
    printSomething()

    //Callback
    val callback = {}
    println( doSomething(2){
        println("I am inside callback")
    })

    var i=0
    val increment = {i++}
    increment()
    increment()
    increment()
    increment()
    println(i)

    //Error Handling
    try {
        val x=10
        val y=0
        val res = x/y
        println(res)
    }
    catch(e: Exception) {
//        println(e)
        println("Caught exception: ${e.message}")
    }
    finally {
        println("Finally program is over")
    }

    //Concurrency
//    println("Inside main")
//    launch{
//        delay(1000L)
//        println("Inside coroutine")
//    }
//    launch{
//        delay(500L)
//        println("Inside coroutine2")
//    }
//    println("Also inside main")
//
//    //Dispatchers
//    launch(Dispatcher.Default){
//        println("Inside Main")
//    }
//    launch(Dispatcher.Default){
//        println("Inside Default")
//    }
//    launch(Dispatcher.Default){
//        println("Inside IO")
//    }
//    launch(Dispatcher.Default){
//        println("Inside Unconfined")
//    }

}
//suspend fun printSomething2(){
//    delay(500L)
//    println("Inside suspend function")
//}
fun doSomething(x:Int, callback: ()->Unit): Int{
    println("I am a doSomething") //post the values
    callback()
    return 5*x
}

fun printSomething(){
    println("something")
}

typealias operation = (Int, Int) -> Int
fun operation(x: Int, y: Int, operate: (Int, Int)-> Int): Int{
    return operate(x,y)
}