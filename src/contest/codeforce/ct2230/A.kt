package contest.codeforce.ct2230

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
            return if (isNeg) -res else res
        }
    }
    val sb = StringBuilder()

    val testCnt = fs.nextInt()

    repeat(testCnt) {
        val n = fs.nextInt()
        val oneCost = fs.nextInt().toLong()
        val grpCost = fs.nextInt().toLong()
        val onlyOne = n * oneCost
        val onlyGrp = (n / 3 * grpCost) + if (n % 3 != 0) grpCost else 0
        val mixed = (n / 3 * grpCost) + if (n % 3 != 0) oneCost * (n % 3) else 0
        sb.append(if (onlyOne < onlyGrp && onlyOne < mixed) onlyOne else if (onlyGrp < mixed) onlyGrp else mixed).append("\n")
    }
    print(sb)
}