'''
문제 설명
정수 n을 입력받아 n의 약수를 모두 더한 값을 리턴하는 함수, solution을 완성해주세요.

제한 사항
n은 0 이상 3000이하인 정수입니다.
'''

def solution(n):
    return sum([nn for nn in range(1, n + 1) if n % nn == 0])

if __name__ == '__main__':
    solution(28)