package problem.baekjoon.bronze.b1

import kotlin.math.min

fun main(args: Array<String>) {
    val (ay, am, ad) = readln().split(" ").map { it.toInt() }
    val (by, bm, bd) = readln().split(" ").map { it.toInt() }

    val aDay = (ay * 360) + (am * 30) + ad
    val bDay = (by * 360) + (bm * 30) + bd

    val workDay = bDay - aDay

    val monthLeave = if (workDay < 30) 0 else min(workDay / 30, 36) // 월차
    var yearLeave = 0

    if (workDay >= 360) {
        for (i in 0 until (workDay / 360)) {
            yearLeave += (i/2) + 15
        }
    }

    println("$yearLeave $monthLeave")
    println("${workDay}days")
}