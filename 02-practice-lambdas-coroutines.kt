import kotlinx.coroutines.*
import java.util.*

//Coding Questions on Coroutines in Kotlin:
//1. Basic Coroutine Example:
// Write a simple coroutine in Kotlin that prints "Hello" and then "World" after a 1-second delay.

//fun main() = runBlocking {
//    launch {
//        delay(5000) // 5-second delay
//        println("World")
//    }
//    println("Hello")
//}

//2. Coroutine with Exception Handling:
//Write a coroutine in Kotlin that handles exceptions during the execution of tasks.

//fun main() = runBlocking {
//    val job = launch {
//        try {
//            delay(1000)
//            println("Task completed successfully")
//
//            throw IllegalStateException("Something went wrong")
//        } catch (e: Exception) {
//            println("Exception caught: ${e.message}")
//        }
//    }
//
//    job.join() //ensures that the coroutine completes before the program exits
//}

//Basic try-catch Example:

//a. Write a Kotlin function that takes two integers and divides the first by the second.
//Use a try-catch block to handle any potential division by zero exceptions and return a meaningful error message.
//fun main() {
//    try {
//        val x=10
//        val y=0
//        val res = x/y
//        println(res)
//    }
//    catch(e: Exception) {
////        println(e)
//        println("Caught exception: ${e.message}")
//    }
//    finally {
//        println("Finally program is over")
//    }
//}

//b. Create a custom exception class in Kotlin called InvalidAgeException.
//Write a function that checks a person's age and throws InvalidAgeException if the age is less than 18.
class InvalidAgeException(message: String) : Exception(message)
fun checkAge(age: Int) {
    if (age < 18) {
        throw InvalidAgeException("Age is less than 18.")
    }
}
//fun main() {
//    val age1 = 15
//    val age2 = 20 // Replace with the actual age
//    try {
//        checkAge(age2)
//        println("Age is valid.")
//        checkAge(age1)
//    }
//    catch(e: Exception) {
//        println("Caught exception: ${e.message}")
//    }
//}

//c. Write a Kotlin function that accesses an array element by index.
//Use a finally block to print a message indicating the end of the operation, regardless of whether an exception was thrown.
fun accessArrayElement(array: Array<String>, index: Int) {
    try {
        val element = array[index]
        println("Element at index $index: $element")
    } catch (e: ArrayIndexOutOfBoundsException) {
        println("Index out of bounds: ${e.message}")
    } finally {
        println("Array access operation completed.")
    }
}
//fun main() {
//    val myArray = arrayOf("Biswesh", "Kumar", "Padhy")
//    accessArrayElement(myArray, 1)
//    println()
//    accessArrayElement(myArray, 5)
//}

//3. Coroutine with Timeout:
//Write a Kotlin program using coroutines that performs a long-running task but cancels it if it takes more than 2 seconds to complete.
//Use the withTimeout function to achieve this.

//fun main() = runBlocking {
//    try {
//        withTimeout(2000) {// performs a long-running task
//            delay(3000)
//            println("Task completed successfully")
//        }
//    } catch (e: TimeoutCancellationException) {
//        println("Task timed out: ${e.message}")
//    }
//}


//Advanced Coding Questions on Lambdas in Kotlin:
//1. Lambda for Sorting:
//Write a Kotlin function that takes a list of pairs containing a name and age, and returns the list sorted by age using a lambda function.
//fun main() {
//    val people = listOf(
//        "Biswesh" to 19,
//        "Bibhuti" to 21,
//        "Rahul" to 22,
//        "Harsh" to 25
//    )
//    val sort = people.sortedBy { it.second }
//    sort
//        .forEach { (name, age) ->
//        println("$name age is $age ")
//    }
//}


//2. in Functional Programming:
//Write a Kotlin program that uses map, filter, and reduce functions with lambdas to process a list of numbers.
//First, square all the numbers, then filter out the even numbers, and finally sum the remaining numbers.
//fun main() {
//    val numbers = listOf(1,2,3,4,5,6,7,8)
//    val newList = numbers.map {
//        it*it
//    }.filter {
//        it % 2 ==0
//    }.reduce { acc, i -> acc + i }
//    println("Sum the remaining numbers is $newList")
//}

//3. Lambda with Closures:
//Write a Kotlin function that uses a lambda to create a closure.
//The function should return a lambda that adds a given number to its input.
fun add(num : Int): (Int) -> Int {
    return { input -> input + num }
}

//fun main() {
//    val addFive = add(5)
//    println("Result is : ${addFive(6)}")
//}


//4. Lambda with Higher-Order Functions:
//Write a Kotlin function that takes another function as a parameter and uses it to transform a list of strings.
fun transformListStrings(strings: List<String>, transform: (String) -> Int): List<Int> {
    return strings.map(transform)
}

//fun main() {
//    val list = listOf("Biswesh", "Kumar", "Padhy", "Shubham", "Patel")
//
//    val listLength = transformListStrings(list) { it.length}
//    println("Length of element in string: $listLength")
//}

//5. Lambdas with Collections:
//Write a Kotlin function that uses lambdas to perform a sequence of operations on a list of strings:
//filter the strings to include only those starting with a specific letter, convert them to uppercase, and then sort them alphabetically.
fun processStrings(strings: List<String>, start: Char): List<String> {
    return strings
        .filter { it.startsWith(start) }
        .map { it.uppercase(Locale.getDefault()) }
        .sorted()
}

//fun main() {
//    val str = listOf("Biswesh", "Bibhuti", "Padhy", "Shubham", "Patel")
//    val start = 'B'
//
//    val returnedStr = processStrings(str, start)
//    println("Processed strings: $returnedStr")
//}


//6. Lambda with Return Type Inference:
//Write a Kotlin function that uses a lambda to calculate the factorial of a given number.
//The lambda should be assigned to a variable, and the function should use this lambda to compute the factorial.
fun calcFactorial(n: Int): Long {
    val factorial: (Int) -> Long = {
        var result: Long = 1
        for (i in 1..it) {
            result *= i
        }
        result
    }
    return factorial(n)
}

//fun main() {
//    var number = 0
//    var factorial = calcFactorial(number)
//    println("Factorial of $number is: $factorial")
//    number = 6
//    factorial = calcFactorial(number)
//    println("Factorial of $number is: $factorial")
//}