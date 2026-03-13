package problem.baekjoon.bronze.b5

fun main(args: Array<String>) {
    val n = readln().toInt()
    val sb = StringBuilder()
    for (i in 1..(n/4)) sb.append("long ")
    print(sb.append("int"))
}