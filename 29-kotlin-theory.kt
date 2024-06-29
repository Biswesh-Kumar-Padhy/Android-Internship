fun main(){
//    greetStudents(firstName = "Biswesh", lastName = "Padhy")
//    printNumbers(1,2,3,4,5,6)
//    val person = Person("Biswesh")
//    val person2 = Person("amit", 15)
//    person.name = "Nitesh"
//    println("Name: ${person.name}, Age: ${person.age}")
//    println("Name: ${person2.name}, Age: ${person2.age}")
//    val square = Square(6.0)
//    println("Area = ${square.area()}")
//    square.draw()
    val instance = MyClass.create()
}
class MyClass{
    companion object {
        fun create():MyClass = MyClass()
    }
}
data class User(val name: String, val age: Int){

}
//fun greetStudents(firstName: String, middleName: String = "Kumar", lastName: String){
//    println("Hello, $firstName $middleName $lastName")
//}
//
//fun printNumbers(vararg number: Int) {
//    for (n in number){
//        println(n)
//    }
//}
//class Person{
//    init {
//        println("Person object is Created")
//    }
//    var name: String = ""
//    var age = 0
//}
// //Primary & Secondary Constructor
//class Person(var name:String, var age:Int)
//class Person(var name:String){
//    var age:Int = 0
//    constructor(name:String, age:Int): this(name) {
//        println("I am secondary constructor")
//        greet()
//        this.age = age
//        greet()
//    }
//    init {
//        println("My Object is Created")
//        greet()
//    }
//    fun greet(){
//        println("hello ${name} ${age}")
//    }
//}
//abstract class Shape{
//    abstract fun area(): Double
//}
//interface Drawable{
//    fun draw()
//}
//class Square(var side: Double):Shape(),Drawable {
//    override fun area(): Double {
//        return side * side
//    }
//    override fun draw(){
//        println("I am a Rectangle")
//    }
//}