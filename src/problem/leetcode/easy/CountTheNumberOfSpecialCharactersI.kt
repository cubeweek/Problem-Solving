package problem.leetcode.easy

class CountTheNumberOfSpecialCharactersI {
    fun numberOfSpecialChars(word: String): Int {
        val lowerChecker = BooleanArray(26)
        val upperChecker = BooleanArray(26)
        for (w in word) {
            if (w in 'A'..'Z') upperChecker[w - 'A'] = true
            else lowerChecker[w - 'a'] = true
        }

        var result = 0
        for (i in 0 until word.length) {
            if (lowerChecker[i] && upperChecker[i]) result++
        }

        return result
    }
}