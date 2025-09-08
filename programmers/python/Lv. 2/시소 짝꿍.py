"""
문제 설명
어느 공원 놀이터에는 시소가 하나 설치되어 있습니다. 이 시소는 중심으로부터 2(m), 3(m), 4(m) 거리의 지점에 좌석이 하나씩 있습니다.
이 시소를 두 명이 마주 보고 탄다고 할 때, 시소가 평형인 상태에서 각각에 의해 시소에 걸리는 토크의 크기가 서로 상쇄되어 완전한 균형을 이룰 수 있다면 그 두 사람을 시소 짝꿍이라고 합니다. 즉, 탑승한 사람의 무게와 시소 축과 좌석 간의 거리의 곱이 양쪽 다 같다면 시소 짝꿍이라고 할 수 있습니다.
사람들의 몸무게 목록 weights이 주어질 때, 시소 짝꿍이 몇 쌍 존재하는지 구하여 return 하도록 solution 함수를 완성해주세요.

제한 사항
2 ≤ weights의 길이 ≤ 100,000
100 ≤ weights[i] ≤ 1,000
몸무게 단위는 N(뉴턴)으로 주어집니다.
몸무게는 모두 정수입니다.
"""


def solution(weights):
    answer = 0

    weights.sort()
    counts = [0] * 1001
    for weight in weights:
        answer += counts[weight]
        if weight % 2 == 0:
            answer += counts[weight // 2]
        if weight * 2 % 3 == 0:
            answer += counts[weight * 2 // 3]
        if weight * 3 % 4 == 0:
            answer += counts[weight * 3 // 4]
        counts[weight] += 1

    return answer


if __name__ == "__main__":
    solution([100, 180, 360, 100, 270])
    solution([200, 100, 100, 200, 100])
    solution([100, 400])
    solution([100, 50, 100, 150])
    solution([100, 100, 100])
