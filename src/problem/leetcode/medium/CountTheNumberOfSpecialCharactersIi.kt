package problem.leetcode.medium

class CountTheNumberOfSpecialCharactersIi {
    fun numberOfSpecialChars(word: String): Int {
        val checker = IntArray(26)

        for (w in word) {
            if (w in 'A'..'Z') {
                if (checker[w - 'A'] == 1) checker[w - 'A'] = 2
                else if (checker[w - 'A'] == 0)checker[w - 'A'] = -1
            } else {
                 when (checker[w - 'a']) {
                    0 -> checker[w - 'a'] = 1
                    2 -> checker[w - 'a'] = -1
                }
            }
        }

        var answer = 0
        for (i in 0..25) if (checker[i] == 2) answer++

        return answer
    }
}