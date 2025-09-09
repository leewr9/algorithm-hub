"""
문제 설명
1 x 1 크기의 칸들로 이루어진 직사각형 격자 형태의 미로에서 탈출하려고 합니다. 각 칸은 통로 또는 벽으로 구성되어 있으며, 벽으로 된 칸은 지나갈 수 없고 통로로 된 칸으로만 이동할 수 있습니다. 통로들 중 한 칸에는 미로를 빠져나가는 문이 있는데, 이 문은 레버를 당겨서만 열 수 있습니다. 레버 또한 통로들 중 한 칸에 있습니다. 따라서, 출발 지점에서 먼저 레버가 있는 칸으로 이동하여 레버를 당긴 후 미로를 빠져나가는 문이 있는 칸으로 이동하면 됩니다. 이때 아직 레버를 당기지 않았더라도 출구가 있는 칸을 지나갈 수 있습니다. 미로에서 한 칸을 이동하는데 1초가 걸린다고 할 때, 최대한 빠르게 미로를 빠져나가는데 걸리는 시간을 구하려 합니다.

미로를 나타낸 문자열 배열 maps가 매개변수로 주어질 때, 미로를 탈출하는데 필요한 최소 시간을 return 하는 solution 함수를 완성해주세요. 만약, 탈출할 수 없다면 -1을 return 해주세요.

제한사항
5 ≤ maps의 길이 ≤ 100
5 ≤ maps[i]의 길이 ≤ 100
maps[i]는 다음 5개의 문자들로만 이루어져 있습니다.
S : 시작 지점
E : 출구
L : 레버
O : 통로
X : 벽
시작 지점과 출구, 레버는 항상 다른 곳에 존재하며 한 개씩만 존재합니다.
출구는 레버가 당겨지지 않아도 지나갈 수 있으며, 모든 통로, 출구, 레버, 시작점은 여러 번 지나갈 수 있습니다.
"""


def escape(maps, start, target):
    queue = [start]
    visit = [[False] * len(m) for m in maps]

    while queue:
        x, y, cnt = queue.pop(0)
        if "X" == maps[x][y] or visit[x][y]:
            continue
        visit[x][y] = True
        if target == maps[x][y]:
            return cnt
        if y > 0:
            queue.append((x, y - 1, cnt + 1))
        if y < len(maps[x]) - 1:
            queue.append((x, y + 1, cnt + 1))
        if x > 0:
            queue.append((x - 1, y, cnt + 1))
        if x < len(maps) - 1:
            queue.append((x + 1, y, cnt + 1))
    return -1


def solution(maps):
    answer = 0

    start, lever = (0, 0), (0, 0)
    for i in range(len(maps)):
        for j in range(len(maps[i])):
            if maps[i][j] == "S":
                start = (i, j)
            if maps[i][j] == "L":
                lever = (i, j)

    answer = escape(maps, (*start, answer), "L")
    if answer > -1:
        answer = escape(maps, (*lever, answer), "E")
    return answer


if __name__ == "__main__":
    solution(["SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"])
    solution(["LOOXS", "OOOOX", "OOOOO", "OOOOO", "EOOOO"])
    solution(["SOOOO", "OOOLO", "OOOOE", "OOOOO", "OOOOO"])
    solution(["SOOOL", "XXXXX", "OOOOO", "OXXXX", "OOOOE"])
