//Collections
//1.Write a function that takes a list of integers and returns a new list with the elements in reverse order.
fun reverseList(input: List<Int>): List<Int> {
    val reversedList = mutableListOf<Int>()
    val start = 0
    val end = input.size - 1
    for (i in end downTo start) {
        reversedList.add(input[i])
    }
    return reversedList
}
//fun main() {
//    val oriList = listOf(1, 2, 3, 4, 5)
//    val revList  = reverseList(oriList)
//    println("Original list: $oriList")
//    println("Reversed list: $revList")
//}

//2.Write a function that takes a list of integers and an integer n, and returns a new list with all elements less than n removed.
fun greaterThanN(input: List<Int>, n: Int): List<Int> {
    return input.filter { it >= n }
}
//fun main() {
//    val oriList: List<Int> = listOf(1,2,3,4,5,6,7,8,9,10)
//    val filteredList = greaterThanN(oriList,5)
//    println(filteredList)
//}
//3.Write a function that takes a list of strings and returns a map where the keys are the strings and the values are their lengths.
fun stringLengthMap(input: List<String>): Map<String, Int> {
    val newList = mutableMapOf<String, Int>()
    for (i in input) {
        newList[i] = i.length
    }
    return newList
}
//fun main() {
//    val oriList = listOf("Biswesh", "Kumar","Padhy","silicon","sit")
//    println(stringLengthMap(oriList))
//}

//4.Write a function that takes two lists of integers and returns true if the first list is a subset of the second list.
fun subset(first: List<Int>, second: List<Int>): Boolean {
    for (i in first) {
        if (i !in second) {
            return false
        }
    }
    return true
}
//fun main() {
//    val l1 = listOf(3, 2, 1, 4, 5)
//    val l2 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
//    val sub = subset(l1, l2)
//    if (sub) {
//        println("List1 is a subset of List2.")
//    } else {
//        println("List1 is not a subset of List2.")
//    }
//}

//5.Given a list of Person objects (each with name and age properties), sort the list first by age in ascending order
//and then by name in alphabetical order if ages are equal.
data class Person(val name: String, val age: Int)

fun sortPeople(persons: List<Person>): List<Person> {
    return persons.sortedWith(compareBy(Person::age, Person::name))
}
//fun main() {
//    val persons = listOf(
//        Person("Alice", 30),
//        Person("Bob", 25),
//        Person("Charlie", 25),
//        Person("David", 28),
//        Person("Eve", 30)
//    )
//    val sort = sortPeople(persons)
//    sort.forEach {
//        println("${it.name}, ${it.age} years old")
//    }
//}

//Null Safety
//1. Safe Calls and Elvis Operator
//Write a function that takes a nullable string and returns its length if it is not null, or -1 if it is null.
fun nullableString(s: String?): Int {
    return s?.length ?: -1
}
//fun main() {
//    var str: String? = "abed"
//    println("Value of nullable string is: ${nullableString(str)}")
//    str = null
//    println("Value of nullable string is: ${nullableString(str)}")
//}

//2. Nullability and Collections
//Given a list of nullable integers, write a function that returns a list of non-null integers. Use the filterNotNull function.
fun nonNullFilter(input: List<Int?>): List<Int?> {
//    return input.filter { it != null }
        return input.filterNotNull()
}
//fun main() {
//    val l1 : List<Int?> = listOf(3, null, 2, null, 1, null, 4, 5)
//    println( nonNullFilter(l1))
//}

//3. Handling Null with let
//Write a function that takes a nullable string and prints its uppercase version if it is not null, otherwise prints “String is null”.
fun upperOrNull(s: String?) {
    s?.let { println(it.uppercase()) } ?: println("String is null")
}
//fun main() {
//    var str: String? ="Biswesh"
//    upperOrNull(str)
//    str = null
//    upperOrNull(str)
//}

//4. Null Safety in Data Classes
//Define a data class User with nullable properties name and email. Write a function that prints “Incomplete User” if any property is null, otherwise prints the user’s details.
data class User(val name: String?, val email: String?)

fun printUser(user: User) {
    if (user.name != null && user.email != null) {
        println("Complete User:")
        println("Name: ${user.name}, Email: ${user.email}")
    } else {
        println("Incomplete User :(")
    }
}
//fun main() {
//    val user1 = User(name = "Biswesh", email = "padhy@gmail.com")
//    val user2 = User(name = null, email = "google@gmail.com")
//    printUser(user1)
//    println()
//    printUser(user2)
//}
//5. Safe Casting with as?
//Given a list of Any type, write a function that filters out all elements that are not strings and returns a list of strings.
fun stringsFilters(anyList: List<Any>): List<String> {
    return anyList.filterIsInstance<String>()
}
//fun main() {
//    val anyList: List<Any> = listOf("Biswesh", 94, "Kumar", 3.14, "Padhy", true)
//    val strList = stringsFilters(anyList)
//    strList.forEach { println(it) }
//}