'''
문제 설명
아래와 같이 5와 사칙연산만으로 12를 표현할 수 있습니다.

12 = 5 + 5 + (5 / 5) + (5 / 5)
12 = 55 / 5 + 5 / 5
12 = (55 + 5) / 5

5를 사용한 횟수는 각각 6,5,4 입니다. 그리고 이중 가장 작은 경우는 4입니다.
이처럼 숫자 N과 number가 주어질 때, N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.

제한사항
N은 1 이상 9 이하입니다.
number는 1 이상 32,000 이하입니다.
수식에는 괄호와 사칙연산만 가능하며 나누기 연산에서 나머지는 무시합니다.
최솟값이 8보다 크면 -1을 return 합니다.
'''

def solution(N, number):
    answer = 0
    check = [[]]
    for i in range(1, 10):
        check.append([int(str(N) * i)])
    for i in range(1, len(check)):
        for j in range(i, 9 - i):
            check[i + j] += calc(check[i], check[j])
        if number in check[i]:
            answer = i
            break
    if answer == 0 or answer > 8:
        return -1
    return answer

def calc(nn, NN):
    all_calc = []
    for n in nn:
        for N in NN:
            all_calc += [n + N, n - N, N - n, n * N]
            if n != 0:
                all_calc.append(N // n)
            if N != 0:
                all_calc.append(n // N)    
    return all_calc

if __name__ == '__main__':
    solution(1, 121)