package problem.leetcode.hard

import java.util.ArrayDeque

class JumpGameIv {
    fun minJumps(arr: IntArray): Int {
        val len = arr.size

        val grp = HashMap<Int, MutableList<Int>>()
        for (i in 0 until len) grp.getOrPut(arr[i], { ArrayList() }).add(i)

        val visited = BooleanArray(len)
        val q = ArrayDeque<Pair<Int, Int>>()
        q.add(0 to 0)

        var cur = 0 to 0
        while (q.isNotEmpty() && cur.first != len-1) {
            cur = q.poll()
            visited[cur.first] = true
            val nextJump = cur.second + 1
            val back = cur.first - 1
            val forward = cur.first + 1
            if (back >= 0 && !visited[back]) q.add(back to nextJump)
            if (forward < len && !visited[forward]) q.add(forward to nextJump)
            for (g in grp[arr[cur.first]] ?: continue) if (!visited[g]) q.add(g to nextJump)
            grp.remove(arr[cur.first])
        }
        return cur.second
    }
}