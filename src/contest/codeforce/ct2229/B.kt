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
        val a = IntArray(n) { fs.nextInt() }
        val b = IntArray(n) { fs.nextInt() }

        var maxOfMin = 0
        var maxSum = 0L
        for (i in 0 until n) {
            var mx = a[i]
            var mn = b[i]
            if (a[i] < b[i]) {
                mx = b[i]
                mn = a[i]
            }
            maxOfMin = maxOf(maxOfMin, mn)
            maxSum += mx
        }
        sb.appendLine(maxOfMin + maxSum)
    }

    println(sb)
}