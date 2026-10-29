"""
문제 설명
어떤 도로에 차량 신호등이 n개 있습니다. 모든 신호등은 항상 초록불 → 노란불 → 빨간불 순서로 반복되며, 각 신호의 지속 시간은 신호등마다 다릅니다. 시간은 1초부터 시작하며, 각 신호등은 처음에는 초록불 상태로 시작합니다.

이 도로에서는 가끔 정전이 일어나는데, 모든 신호등이 모두 노란불이 되면 정전이 발생한다는 사실이 밝혀졌습니다.

예를 들어 신호등이 2개이고, 각 신호등의 주기가 다음과 같다고 가정해 보겠습니다.

신호등	초록불	노란불	빨간불
1번	2초	1초	2초
2번	5초	1초	1초
신호등-1.drawio.png

위 그림과 같이 13초에 처음으로 두 신호등이 모두 노란불이 됩니다.

신호등 n개의 신호 주기를 담은 2차원 정수 배열 signals가 매개변수로 주어집니다. 모든 신호등이 노란불이 되는 가장 빠른 시각(초)을 return 하도록 solution 함수를 완성해 주세요. 만약 모든 신호등이 노란불이 되는 경우가 존재하지 않는다면 -1을 return 해주세요.

제한사항
2 ≤ signals의 길이 = n ≤ 5
signals의 원소는 [G, Y, R] 형태의 길이가 3인 정수 배열입니다. 순서대로 초록불, 노란불, 빨간불의 지속 시간을 의미합니다.
1 ≤ G, Y, R ≤ 18
3 ≤ G + Y + R ≤ 20
"""


def solution(signals):
    answer = 1

    max = 1
    for green, yellow, red in signals:
        cycle = green + yellow + red
        max *= cycle

    lights = [0] * len(signals)
    while answer < max:
        for i in range(len(signals)):
            green, yellow, red = signals[i]
            cycle = green + yellow + red
            if answer % cycle > green + yellow or answer % cycle == 0:
                lights[i] = 2
            elif answer % cycle > green:
                lights[i] = 1
            else:
                lights[i] = 0
        if all(light == 1 for light in lights):
            return answer
        answer += 1

    return -1


if __name__ == "__main__":
    solution([[2, 1, 2], [5, 1, 1]])
