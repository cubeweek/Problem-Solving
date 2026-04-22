package contest.codeforce.ct2225

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

        fun nextLong(): Long {
            var c = readByte()
            while (c in 0..32) c = readByte()
            if (c == -1) return 0

            var isNeg = false
            if (c == 45) {
                isNeg = true
                c = readByte()
            }

            var res = 0L
            while (c in 48..57) {
                res = (res shl 3) + (res shl 1) + (c.toLong() and 15L)
                c = readByte()
            }
            return if (isNeg) -res else res
        }
    }
    val sb = StringBuilder()

    val testCnt = fs.nextLong().toInt()
    repeat(testCnt) {
        val x = fs.nextLong()
        val y = fs.nextLong()
        var isExistZ = false
        for (z in x * 2 until y step x) {
            if (y % z != 0L) {
                isExistZ = true
                break
            }
        }
        sb.append(if (isExistZ) "YES\n" else "NO\n")
    }
    print(sb)
}