package contest.codeforce.ct2225

fun main(args: Array<String>) {
    val br = System.`in`.bufferedReader()
    val testCnt = br.readLine().toInt()

    fun chkValid(now: String, target: Int): Boolean {
        val diffNum = ArrayList<Int>()
        for (i in 0 until now.length) if (now[i] != (if (i % 2 == target) 'a' else 'b')) diffNum.add(i)
        var isCont = true
        for (d in 1 until diffNum.size) {
            if (diffNum[d] - diffNum[d-1] != 1) {
                isCont = false
                break
            }
        }
        return diffNum.isEmpty() || isCont
    }

    val sb = StringBuilder()
    repeat(testCnt) {
        val now = br.readLine()
        sb.append(
            if (chkValid(now, 0) || chkValid(now, 1)) "YES\n" else "NO\n")
    }

    print(sb)
}