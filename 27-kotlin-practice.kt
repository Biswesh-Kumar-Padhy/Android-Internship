
//1.	Print the pattern
 fun main() {
    val rows = 5
    var space = rows - 1
    for (i in 1..rows) {
        for (j in 1..space) {
            print("  ")
        }
        space--
        for (j in 1..2 * i - 1) {
            print("* ")
        }
        println()
    }
    space = 1
    for (i in 1..rows - 1) {
        for (j in 1..space) {
            print("  ")
        }
        space++
        for (j in 1..2 * (rows - i) - 1) {
            print("* ")
        }
        println()
    }
}

//2.	Check if the number is Armstrong number or not
fun main() {
    val num = 371
    var a = num
    var res = 0

    while (a != 0) {
        val rem = a % 10
        println(rem)
        res += Math.pow(rem.toDouble(), 3.0).toInt()
        a /= 10
    }

    if (res == num) {
        println("${num} is an Armstrong number.")
    } else {
        println("${num} is not an Armstrong number.")
    }
}

//3.	Find the GCD of two numbers using Euclidean method
fun main(){
fun calculateGCD(a: Int, b: Int): Int {
    var num1 = a
    var num2 = b
    while (num2 != 0) {
        val temp = num2
        num2 = num1 % num2
        num1 = temp
    }
    
    return num1
}

val a = 40
val b = 18
val gcd = calculateGCD(a, b)
println("GCD of ${a} and ${b} is: ${gcd}")
}

//4.	Find the frequency of letters in a string






//5.	Check if a number is duck number or not
fun main() {
    fun isDuck(n: Int): Boolean {
    val s = n.toString()
    return s.any { it == '0' } && s[0] != '0'
}

val duck = 707967
if (isDuck(duck)) {
    println("${duck} is a Duck number.")
} else {
    println("${duck} is not a Duck number.")
}
}


