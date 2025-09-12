"""
문제 설명
리코쳇 로봇이라는 보드게임이 있습니다.

이 보드게임은 격자모양 게임판 위에서 말을 움직이는 게임으로, 시작 위치에서 출발한 뒤 목표 위치에 정확하게 멈추기 위해 최소 몇 번의 이동이 필요한지 말하는 게임입니다.

이 게임에서 말의 이동은 현재 위치에서 상, 하, 좌, 우 중 한 방향으로 게임판 위의 장애물이나 게임판 가장자리까지 부딪힐 때까지 미끄러져 움직이는 것을 한 번의 이동으로 정의합니다.

다음은 보드게임판을 나타낸 예시입니다. ("."은 빈 공간을, "R"은 로봇의 처음 위치를, "D"는 장애물의 위치를, "G"는 목표지점을 나타냅니다.)

...D..R
.D.G...
....D.D
D....D.
..D....
이때 최소 움직임은 7번이며 "R" 위치에서 아래, 왼쪽, 위, 왼쪽, 아래, 오른쪽, 위 순서로 움직이면 "G" 위치에 멈춰 설 수 있습니다.

리코챗로봇.jpg

게임판의 상태를 나타내는 문자열 배열 board가 주어졌을 때, 말이 목표위치에 도달하는데 최소 몇 번 이동해야 하는지 return 하는 solution함수를 완성해주세요. 만약 목표위치에 도달할 수 없다면 -1을 return 해주세요.

제한 사항
3 ≤ board의 길이 ≤ 100
3 ≤ board의 원소의 길이 ≤ 100
board의 원소의 길이는 모두 동일합니다.
문자열은 ".", "D", "R", "G"로만 구성되어 있으며 각각 빈 공간, 장애물, 로봇의 처음 위치, 목표 지점을 나타냅니다.
"R"과 "G"는 한 번씩 등장합니다.
"""


def solution(board):
    answer = -1

    n, m = len(board), len(board[0])

    visit = [[False] * m for _ in range(n)]
    queue = []

    for i in range(n):
        for j in range(m):
            if board[i][j] == "R":
                queue.append((i, j, 0))
                visit[i][j] = True
                break

    while queue:
        x, y, cnt = queue.pop(0)
        if board[x][y] == "G":
            answer = cnt
            break

        if x > 0:
            for i in range(x, -1, -1):
                if i == 0 or board[i - 1][y] == "D":
                    if not visit[i][y]:
                        queue.append((i, y, cnt + 1))
                        visit[i][y] = True
                    break
        if x < n - 1:
            for i in range(x, n):
                if i == n - 1 or board[i + 1][y] == "D":
                    if not visit[i][y]:
                        queue.append((i, y, cnt + 1))
                        visit[i][y] = True
                    break
        if y > 0:
            for j in range(y, -1, -1):
                if j == 0 or board[x][j - 1] == "D":
                    if not visit[x][j]:
                        queue.append((x, j, cnt + 1))
                        visit[x][j] = True
                    break
        if y < m - 1:
            for j in range(y, m):
                if j == m - 1 or board[x][j + 1] == "D":
                    if not visit[x][j]:
                        queue.append((x, j, cnt + 1))
                        visit[x][j] = True
                    break

    return answer


if __name__ == "__main__":
    solution(["...D..R", ".D.G...", "....D.D", "D....D.", "..D...."])
    solution([".D.R", "....", ".G..", "...D"])
    solution(["DGR", "...", "..."])
    solution(
        [
            "........D..",
            "...........",
            "...........",
            "...........",
            "...........",
            "...........",
            "R.D........",
            "...D.......",
            ".D..D......",
            "..D..D.....",
            "...D..D....",
            "....D.....D",
            ".....D.D...",
            ".........G.",
        ]
    )
