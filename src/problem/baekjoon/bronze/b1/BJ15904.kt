package problem.baekjoon.bronze.b1

fun main(args: Array<String>) {
    val str = readln()

    val target = "UCPC"
    var idx = 0
    for (s in str) {
        if (s == target[idx]) {
            if (++idx > 3) break
        }
    }
    println("I ${if (idx > 3) "love" else "hate"} UCPC")
}