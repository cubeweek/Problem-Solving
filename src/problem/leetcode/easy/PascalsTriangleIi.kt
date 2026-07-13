package problem.leetcode.easy

class PascalsTriangleIi {
    fun getRow(rowIndex: Int): List<Int> {
        val row = MutableList(rowIndex + 1) { 1 }

        for (i in 2..rowIndex) {
            for (j in i - 1 downTo 1) {
                row[j] = row[j] + row[j - 1]
            }
        }

        return row
    }
}