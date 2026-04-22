package problem.leetcode.medium

class WordsWithinTwoEditsOfDictionary {
    fun twoEditWords(queries: Array<String>, dictionary: Array<String>): List<String> {
        val list = mutableListOf<String>()
        val wordLen = queries[0].length

        for (q in queries) {
            for (d in dictionary) {
                var diffCnt = 0
                for (i in 0 until wordLen) {
                    if (q[i] != d[i] && ++diffCnt > 2) break
                }
                if (diffCnt <= 2) {
                    list.add(q)
                    break
                }
            }
        }

        return list
    }
}