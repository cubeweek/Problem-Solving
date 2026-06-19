package problem.leetcode.easy

class FindTheHighestAltitude {
    fun largestAltitude(gain: IntArray): Int {
        var currentAltitude = 0
        var maxAltitude = 0

        for (g in gain) {
            currentAltitude += g
            if (currentAltitude > maxAltitude) {
                maxAltitude = currentAltitude
            }
        }

        return maxAltitude
    }
}