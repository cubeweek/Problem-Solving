package problem.baekjoon.bronze.b5

fun main(args: Array<String>) {
    val subject = readln()

    println(
        when (subject[0]) {
            'F' -> "Foundation"
            'C' -> "Claves"
            'V' -> "Veritas"
            'E' -> "Exploration"
            else -> ""
        }
    )
}