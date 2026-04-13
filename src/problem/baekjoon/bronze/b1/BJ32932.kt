package problem.baekjoon.bronze.b1

fun main(args: Array<String>) {
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

        fun nextChar(): Char? {
            var c = readByte()
            while (c in 0..32) c = readByte()
            if (c == -1) return null
            return c.toChar()
        }
    }

    val map = Array(1001) { BooleanArray(1001) }
    val obsCnt = fs.nextInt()
    val commCnt = fs.nextInt()

    repeat(obsCnt) {
        val x = fs.nextInt()
        val y = fs.nextInt()
        map[y + 500][x + 500] = true
    }

    var nowY = 500
    var nowX = 500
    fun moveChecker(a: Int): Boolean = a in 0..1000

    repeat(commCnt) {
        when (fs.nextChar()) {
            'L' -> if (moveChecker(nowX - 1) && !map[nowY][nowX - 1]) nowX--
            'R' -> if (moveChecker(nowX + 1) && !map[nowY][nowX + 1]) nowX++
            'D' -> if (moveChecker(nowY - 1) && !map[nowY - 1][nowX]) nowY--
            'U' -> if (moveChecker(nowY + 1) && !map[nowY + 1][nowX]) nowY++
        }
    }

    println("${nowX-500} ${nowY-500}")
}