package problem.baekjoon.gold.g4

import java.util.PriorityQueue

fun main(args: Array<String>) {
    data class Line(val src: Int, val dst: Int, val cost: Int)

    class FastReader {
        val buffer = ByteArray(1 shl 16)
        var lim = 0
        var cur = 0

        @Suppress("NOTHING_TO_INLINE")
        private inline fun readByte(): Int {
            if (cur >= lim) {
                lim = System.`in`.read(buffer)
                if (lim <= 0) return -1
                cur = 0
            }
            return buffer[cur++].toInt()
        }

        fun nextInt(): Int {
            var c = readByte()
            while (c in 0..32) c = readByte()
            if (c == -1) return 0

            var isNeg = false
            if (c == 45) {
                isNeg = true
                c = readByte()
            }

            var res = 0
            while (c in 48..57) {
                res = (res shl 3) + (res shl 1) + (c and 15)
                c = readByte()
            }
            return if(isNeg) -res else res
        }
    }

    val fs = FastReader()
    val cptCnt = fs.nextInt()
    val lineCnt = fs.nextInt()

    val pq = PriorityQueue<Line>(lineCnt, compareBy { it.cost })

    repeat(lineCnt) {
        pq.add(Line(fs.nextInt(), fs.nextInt(), fs.nextInt()))
    }

    val parent = IntArray(cptCnt + 1) { it }

    fun find(x: Int): Int {
        if (x != parent[x]) parent[x] = find(parent[x])
        return parent[x]
    }

    fun union(new: Int, old: Int) {
        val new = find(new)
        val old = find(old)
        if (new != old) parent[old] = new
    }

    var connectCnt = 0
    var minCost = 0

    while (connectCnt < cptCnt-1) {
        val now = pq.poll()
        if (find(now.src) != find(now.dst)) {
            union(now.src, now.dst)
            minCost += now.cost
            ++connectCnt
        }
    }

    println(minCost)
}