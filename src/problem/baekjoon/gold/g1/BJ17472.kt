package problem.baekjoon.gold.g1

import java.util.*
import kotlin.math.abs

fun main(args: Array<String>) {
    data class Coodi(val y:Int, val x:Int)
    data class Bridge(val src:Int, val dst:Int, val length:Int)

    val fs = object {
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

    val seaH = fs.nextInt()
    val seaW = fs.nextInt()
    val map = Array(seaH) { IntArray(seaW) }
    val visited = Array(seaH) { BooleanArray(seaW) }

    for (i in 0 until seaH) {
        for (j in 0 until seaW) {
            map[i][j] = fs.nextInt()
        }
    }

    val mover = arrayOf(arrayOf(-1, 0), arrayOf(0, -1), arrayOf(1, 0), arrayOf(0, 1))

    fun isInArea(y: Int, x: Int): Boolean = y in 0 until seaH && x in 0 until seaW

    var islandSrl = 1

    // 섬 구분하기
    val q = ArrayDeque<Coodi>()
    for (i in 0 until seaH) {
        for (j in 0 until seaW) {
            if (!visited[i][j]) {
                visited[i][j] = true
                if (map[i][j] == 1) q.add(Coodi(i, j))
                while (q.isNotEmpty()) {
                    val now = q.pop()
                    map[now.y][now.x] = islandSrl

                    for (i in 0..3) {
                        val ny = now.y + mover[i][0]
                        val nx = now.x + mover[i][1]

                        if (isInArea(ny, nx) && !visited[ny][nx]) {
                            if (map[ny][nx] == 1) {
                                map[ny][nx] = islandSrl
                                q.add(Coodi(ny, nx))
                            }
                            visited[ny][nx] = true
                        }
                    }
                }
                if (map[i][j] == islandSrl) ++islandSrl
            }
        }
    }

    var srl = -1
    val pq = PriorityQueue<Bridge>(compareBy { it.length })
    fun findBridge(range: IntProgression, action: (Int) -> Int) {
        var waterCnt = 0
        for (i in range) {
            val now = action(i)
            if (now > 0) {
                if (now != srl && waterCnt >= 2) pq.add(Bridge(srl, now, waterCnt))
                break
            } else {
                waterCnt++
            }
        }
    }

    for (y in 0 until seaH) {
        for (x in 0 until seaW) {
            srl = map[y][x]
            if (srl > 0) {
                findBridge((y + 1) until seaH) { map[it][x] }
                findBridge((y - 1) downTo 0) { map[it][x] }
                findBridge((x + 1) until seaW) { map[y][it] }
                findBridge((x - 1) downTo 0) { map[y][it] }
            }
        }
    }

    val islandCnt = islandSrl - 1
    val parent = IntArray (islandCnt + 1) { it }
    fun find(x: Int):Int {
        if (x != parent[x]) parent[x] = find(parent[x])
        return parent[x]
    }
    fun union(new: Int, old: Int) {
        val n = find(new)
        val o = find(old)
        if (n != o) parent[o] = n
    }

    if (pq.size >= islandCnt - 1) {
        var buildCnt = 0
        var buildLength = 0
        while (buildCnt < islandCnt - 1 && pq.isNotEmpty()) {
            val now = pq.poll()
            if (find(now.src) != find(now.dst)) {
                union(now.src, now.dst)
                buildCnt++
                buildLength += now.length
            }
        }
        var isCan = true
        val std = find(1)
        for (p in 2..islandCnt) {
            if (find(p) != std) {
                isCan = false
                break
            }
        }
        println(if (isCan) buildLength else -1)
    } else {
        println(-1)
    }
}