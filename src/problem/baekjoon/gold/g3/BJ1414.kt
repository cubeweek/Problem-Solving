package problem.baekjoon.gold.g3

import java.util.PriorityQueue

fun main(args: Array<String>) {
    data class Cable(val src: Int, val dst: Int, val len: Int)
    fun cvtLen(c: Char): Int = c.code - if (c in 'a'..'z') 96 else 38

    val cptCnt = readln().toInt()
    val pq = PriorityQueue<Cable>(cptCnt * cptCnt, compareBy { it.len })

    for (i in 0 until cptCnt) {
        val str = readln()
        for (j in 0 until cptCnt) {
            if (str[j] == '0') continue
            pq.add(Cable(i, j, cvtLen(str[j])))
        }
    }

    val parent = IntArray(cptCnt) { it }
    fun find(x: Int): Int {
        if (x != parent[x]) parent[x] = find(parent[x])
        return parent[x]
    }
    fun union(new: Int, old: Int) {
        val n = find(new)
        val o = find(old)
        if (n != o) parent[o] = n
    }

    var connCnt = 0
    var connLen = 0
    var totLen = 0
    while (connCnt < cptCnt - 1 && pq.isNotEmpty()) {
        val now = pq.poll()
        if (find(now.src) != find(now.dst)) {
            union(now.src, now.dst)
            connCnt++
            connLen += now.len
        }
        totLen += now.len
    }

    var isConnected = connCnt == cptCnt-1
    val std = find(0)
    for (i in 1 until cptCnt) {
        if (find(i) != std) {
            isConnected = false
            break
        }
    }

    while (pq.isNotEmpty()) totLen += pq.poll().len
    println(if (!isConnected) -1 else totLen-connLen)
}