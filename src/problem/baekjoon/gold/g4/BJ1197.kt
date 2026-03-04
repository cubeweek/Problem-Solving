package problem.baekjoon.gold.g4

fun main(args: Array<String>) {
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
    val vCnt = fs.nextInt()
    val eCnt = fs.nextInt()
    val edgeArr = Array(eCnt) { IntArray(3) }
    for (i in 0 until eCnt) {
        edgeArr[i][0] = fs.nextInt()
        edgeArr[i][1] = fs.nextInt()
        edgeArr[i][2] = fs.nextInt()
    }
    edgeArr.sortBy { it[2] }

    val grpArr = IntArray(vCnt + 1) { it }

    fun find(x: Int): Int {
        if (grpArr[x] != x) grpArr[x] = find(grpArr[x])
        return grpArr[x]
    }

    fun union(a: Int, b: Int) {
        val rep = find(a)
        val chg = find(b)
        if (rep == chg) return
        grpArr[chg] = rep
    }

    var answer = 0
    for (e in edgeArr) {
        if (find(e[0]) != find(e[1])) {
            union(e[0], e[1])
            answer += e[2]
        }
    }

    println(answer)
}