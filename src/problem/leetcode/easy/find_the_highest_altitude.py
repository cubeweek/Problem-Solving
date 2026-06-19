package problem.leetcode.easy

class find_the_highest_altitude:
   def largestAltitude(self, gain: list[int]) -> int:
       current_altitude = 0
       max_altitude = 0

       for g in gain:
           current_altitude += g
           if current_altitude > max_altitude:
               max_altitude = current_altitude

       return max_altitude