//Functions
//Q1. Write a function that takes two integers and returns the larger of the two.
fun max(a: Int, b: Int) {
   if (a > b) {
       println("$a is larger")
   } else {
       println("$b is larger")
   }
}
//fun main(){
//    max(2,3)
//}
//Q2. Write a function sumOfList that takes a list of integers and returns the sum of all elements.
fun sumOfList(vararg number: Int) {
    var sum  = 0
    for (n in number){
        sum +=  n
    }
    println(sum)
}
//fun main(){
//    sumOfList(1,2,3,4,5)
//}
//Q3. Implement a function named isPalindrome that checks whether a given string is a palindrome
fun isPalindrome(input: String): Boolean {
    val str = input.lowercase()
    var start = 0
    var end = str.length - 1
    while (start < end) {
        if (str[start] != str[end]) {
            return false
        }
        start++
        end--
    }
    return true
}
//fun main() {
//    val test1 = isPalindrome("Madam")
//    val test2 = isPalindrome("hello")
//    println("Madam is a palindrome: $test1")
//    println("hello is a palindrome: $test2")
//}

//Q4. Create a function factorial that takes an integer n and returns the factorial of n using recursion.
fun factorial(n: Int): Int {
    return if (n == 0) {
        1
    } else {
        n * factorial(n - 1)
    }
}
//fun main() {
//    println( factorial(4))
//}

//Class
//1. Define a BankAccount class with properties accountNumber and balance.
// Implement methods deposit and withdraw to modify the balance, and getBalance to return the current balance.
class BankAccount(private val accountNumber: String = "xyz000", private var balance: Double) {
    fun deposit(amount: Double) {
        balance += amount
    }
    fun withdraw(amount: Double) {
        if (amount <= balance) {
            balance -= amount
        } else {
            println("Insufficient balance.")
        }
    }
    fun getBalance() {
        println("The balance in AccNO: $accountNumber is $balance")
    }
}
//fun main() {
//    val biswesh = BankAccount("bis123", 100.0 )
//    biswesh.deposit(75.0)
//    biswesh.getBalance();
//    biswesh.withdraw(100.0)
//    biswesh.getBalance();
//    biswesh.withdraw(80.0)
//    biswesh.getBalance();
//}
//2. Inheritance and Polymorphism: Create an abstract class Shape with an abstract method area.
// Create two subclasses Rectangle and Circle that implement the area method.
// Add another method perimeter in both subclasses and demonstrate polymorphism.
abstract class Shape {
    abstract fun area(): Double
}
class Rectangle(private val length: Double, private val breadth: Double) : Shape() {
    override fun area(): Double = length * breadth
    fun perimeter(): Double = 2 * (length + breadth)
}
class Circle(private val r: Double) : Shape() {
    override fun area(): Double = Math.PI * r * r
    fun perimeter(): Double = 2 * Math.PI * r
}
//fun main() {
//    val cuboid = Rectangle(5.0, 9.0)
//    val sphere = Circle(7.0)
//    println("The area and perimeter of Rectangle are ${cuboid.area()} and ${cuboid.perimeter()}")
//    println("The area and perimeter of Circle are ${sphere.area()} and ${sphere.perimeter()}")
//}

//3. Interface Implementation: Create an interface Drawable with a method drawInfo.
// Implement this interface in two classes Square and Triangle and print the info about the shapes.
interface Drawable {
    fun drawInfo(): String
}
class Square(private val side: Double) : Drawable {
    override fun drawInfo(): String {
        return "Square of side: $side"
    }
}
class Triangle(private val base: Double, private val height: Double) : Drawable {
    override fun drawInfo(): String {
        return "Triangle of base: $base and height: $height"
    }
}
//fun main() {
//    val cube = Square(5.0)
//    val cone = Triangle(2.0, 6.0)
//    println(cube.drawInfo())
//    println(cone.drawInfo())
//
//}