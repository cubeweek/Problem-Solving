package problem.baekjoon.bronze.b1

fun main(args: Array<String>) {
    val n = readln().toInt()
    val arr = readln().split(" ").map { it.toInt() }

    var isMirror = "no"
    if (n > 3) {
        for (i in 2..(n/2 + n%2)) {
            if (arr.subList(0, i) == arr.subList(n - i, n)) {
                isMirror = "yes"
                break
            }
        }
    }

    println(isMirror)
}