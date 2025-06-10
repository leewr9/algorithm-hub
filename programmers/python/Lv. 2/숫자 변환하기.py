'''
문제 설명
자연수 x를 y로 변환하려고 합니다. 사용할 수 있는 연산은 다음과 같습니다.

x에 n을 더합니다
x에 2를 곱합니다.
x에 3을 곱합니다.
자연수 x, y, n이 매개변수로 주어질 때, x를 y로 변환하기 위해 필요한 최소 연산 횟수를 return하도록 solution 함수를 완성해주세요. 이때 x를 y로 만들 수 없다면 -1을 return 해주세요.

제한사항
1 ≤ x ≤ y ≤ 1,000,000
1 ≤ n < y
'''

def solution(x, y, n):
    queue, check = [(y, 0)], []
    while queue:
        y, cnt = queue.pop(0)
        if y in check:
            continue
        if y == x:
            return cnt
        check.append(y)

        if y > x:
            queue.append((y - n, cnt + 1))
        if y % 2 == 0:
            queue.append((y // 2, cnt + 1))
        if y % 3 == 0:
            queue.append((y // 3, cnt + 1))

    return -1

if __name__ == '__main__':
    solution(2, 5, 4)