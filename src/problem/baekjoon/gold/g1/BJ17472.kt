package problem.baekjoon.gold.g1

import java.util.PriorityQueue

fun main(args: Array<String>) {
    data class Island(val stY:Int, val stX:Int, val edY:Int, val edX:Int)
    data class Bridge(val src:Int, val dst:Int, val size:Int)

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
    val seaH = fs.nextInt()
    val seaW = fs.nextInt()
    val map = Array(seaH) { IntArray(seaW) }
    val islands = ArrayList<Island>()

    for (i in 0 until seaH) {
        for (j in 0 until seaW) {
            map[i][j] = fs.nextInt()
        }
    }

    for (i in 0 until seaH) {
        var j = 0
        while (j < seaW) {
            when (map[i][j]) {
                1 -> {
                    var edY = i
                    var edX = j
                    map[i][j] = 2
                    while (++edX < seaW && map[i][edX] == 1) map[i][edX] = 2
                    while (++edY < seaH && map[edY][j] == 1) map[edY][j] = 2

                    islands.add(Island(i, j, --edY, --edX))
                    j = edX
                }
                2 -> while (j < seaW && map[i][j] != 0) ++j
                else -> j++
            }
        }
    }
    println(map.joinToString("\n") { it.joinToString(" ") })
    print(islands.joinToString("\n"))

    val pq = PriorityQueue<Bridge>(compareBy { it.size })
    for (i in islands) {
        for (j in islands) {
            if (i == j) continue
            print("${i.stY - j.stY} ")
            print("${i.stX - j.stX} ")
            print("${i.stX - j.stX} ")
            print("${i.stX - j.stX} ")
        }
    }
}