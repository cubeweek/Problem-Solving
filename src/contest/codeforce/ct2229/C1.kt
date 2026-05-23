package contest.codeforce.ct2229

fun main() {
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
            return if (isNeg) -res else res
        }
    }
    val sb = StringBuilder()
    val testCnt = fs.nextInt()

    repeat(testCnt) {
        val n = fs.nextInt()
        val arr = IntArray(n) { fs.nextInt() }

        var oprCnt = 0
        val oprArr = IntArray(n)
        for (i in n-1 downTo 0) {
            if ((arr[i] * if (oprCnt % 2 == 0) 1 else -1) > 0) {
                oprArr[oprCnt++] = i + 1
            }
        }
        sb.appendLine(oprCnt).appendLine(oprArr.sliceArray(0 until oprCnt).joinToString(" "))
    }
    println(sb)
}