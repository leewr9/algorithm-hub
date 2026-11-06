"""
m 그램(gram)을 담을 수 있는 가방에 사탕을 가득 채우는 경우의 수를 구하려 합니다. 단, 같은 사탕은 또 넣을 수 없습니다.


가방이 감당할 수 있는 무게 m, 사탕별 무게가 담긴 배열 weights가 매개변수로 주어질 때,
가방을 정확히 m 그램으로 채우는 경우의 수를 return 하는 solution 함수를 작성해주세요.


제한 조건

m은 1,000 이상 100,000 이하인 자연수입니다.

모든 사탕의 무게는 10 이상 100,000 이하인 자연수입니다.

weights의 길이는 3 이상 15 이하입니다.
"""


def solution(m, weights):
    def add(index, weight):
        if weight > m:
            return 0
        if index == len(weights):
            return 1 if weight == m else 0
        return add(index + 1, weight + weights[index]) + add(index + 1, weight)

    answer = add(0, 0)
    return answer


if __name__ == "__main__":
    solution(3000, [250, 500, 1000, 1250, 1500, 2000, 2250, 2500])
