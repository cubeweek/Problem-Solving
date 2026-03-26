package problem.baekjoon.silver.s5

fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val (n, h, w) = br.readLine().split(" ").map { it.toInt() }
    val paper = Array(h) { CharArray(w) }
    val result = CharArray(n) { '?' }

    for (i in 0 until h) paper[i] = br.readLine().toCharArray()

    for (i in 0 until n) {
        for (y in 0 until h) {
            for (x in i*w until i*w+w) {
                if (paper[y][x] != '?') {
                    result[i] = paper[y][x]
                    break
                }
            }
            if (result[i] != '?') break
        }
    }
    println(result)
}