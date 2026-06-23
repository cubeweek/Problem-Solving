package problem.leetcode.easy

class PascalsTriangle {
    fun generate(numRows: Int): List<List<Int>> {
        val arr = Array(numRows) { i -> IntArray(i + 1) }
        arr[0][0] = 1

        for (i in 0 until numRows - 1) {
            for (j in 0..i) {
                arr[i+1][j] += arr[i][j]
                arr[i+1][j+1] += arr[i][j]
            }
        }

        return arr.map { it.toList() }
    }
}