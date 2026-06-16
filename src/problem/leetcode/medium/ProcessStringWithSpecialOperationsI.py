 class Solution:
     def processStr(self, s: str) -> str:
         result = []

         for char in s:
             if 'a' <= char <= 'z':
                 result.append(char)
             elif char == '*':
                 if result:
                     result.pop()
             elif char == '#':
                 result.extend(result)
             elif char == '%':
                 result.reverse()

         return "".join(result)