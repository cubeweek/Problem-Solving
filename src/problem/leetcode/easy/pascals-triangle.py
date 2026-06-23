class PascalsTriangle:
    def generate(self, numRows: int) -> list[list[int]]:
        if numRows == 0:
            return []

        res = [[1]]

        for i in range(1, numRows):
            prev_row = res[-1]

            new_row = [1] + [prev_row[j] + prev_row[j+1] for j in range(len(prev_row) - 1)] + [1]

            res.append(new_row)

        return res