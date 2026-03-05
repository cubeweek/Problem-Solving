package problem.baekjoon.bronze.b2

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
    val sb = StringBuilder()

    val testCnt = fs.nextInt()
    repeat (testCnt) {
        val a = fs.nextInt()
        val b = fs.nextInt()

        var res = 1
        val power = if (b % 4 == 0) 4 else b % 4

        repeat (power) {
            res = (res * a) % 10
        }

        sb.append(if (res == 0) 10 else res).append("\n")
    }
    println(sb)
}