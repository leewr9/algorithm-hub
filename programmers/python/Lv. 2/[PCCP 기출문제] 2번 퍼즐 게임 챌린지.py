"""
문제 설명
당신은 순서대로 n개의 퍼즐을 제한 시간 내에 풀어야 하는 퍼즐 게임을 하고 있습니다. 각 퍼즐은 난이도와 소요 시간이 정해져 있습니다. 당신의 숙련도에 따라 퍼즐을 풀 때 틀리는 횟수가 바뀌게 됩니다. 현재 퍼즐의 난이도를 diff, 현재 퍼즐의 소요 시간을 time_cur, 이전 퍼즐의 소요 시간을 time_prev, 당신의 숙련도를 level이라 하면, 게임은 다음과 같이 진행됩니다.

diff ≤ level이면 퍼즐을 틀리지 않고 time_cur만큼의 시간을 사용하여 해결합니다.
diff > level이면, 퍼즐을 총 diff - level번 틀립니다. 퍼즐을 틀릴 때마다, time_cur만큼의 시간을 사용하며, 추가로 time_prev만큼의 시간을 사용해 이전 퍼즐을 다시 풀고 와야 합니다. 이전 퍼즐을 다시 풀 때는 이전 퍼즐의 난이도에 상관없이 틀리지 않습니다. diff - level번 틀린 이후에 다시 퍼즐을 풀면 time_cur만큼의 시간을 사용하여 퍼즐을 해결합니다.
예를 들어 diff = 3, time_cur = 2, time_prev = 4인 경우, level에 따라 퍼즐을 푸는데 걸리는 시간은 다음과 같습니다.

level = 1이면, 퍼즐을 3 - 1 = 2번 틀립니다. 한 번 틀릴 때마다 2 + 4 = 6의 시간을 사용하고, 다시 퍼즐을 푸는 데 2의 시간을 사용하므로 총 6 × 2 + 2 = 14의 시간을 사용하게 됩니다.
level = 2이면, 퍼즐을 3 - 2 = 1번 틀리므로, 6 + 2 = 8의 시간을 사용하게 됩니다.
level ≥ 3이면 퍼즐을 틀리지 않으며, 2의 시간을 사용하게 됩니다.
퍼즐 게임에는 전체 제한 시간 limit가 정해져 있습니다. 제한 시간 내에 퍼즐을 모두 해결하기 위한 숙련도의 최솟값을 구하려고 합니다. 난이도, 소요 시간은 모두 양의 정수며, 숙련도도 양의 정수여야 합니다.

퍼즐의 난이도를 순서대로 담은 1차원 정수 배열 diffs, 퍼즐의 소요 시간을 순서대로 담은 1차원 정수 배열 times, 전체 제한 시간 limit이 매개변수로 주어집니다. 제한 시간 내에 퍼즐을 모두 해결하기 위한 숙련도의 최솟값을 정수로 return 하도록 solution 함수를 완성해 주세요.

제한사항
1 ≤ diffs의 길이 = times의 길이 = n ≤ 300,000
diffs[i]는 i번째 퍼즐의 난이도, times[i]는 i번째 퍼즐의 소요 시간입니다.
diffs[0] = 1
1 ≤ diffs[i] ≤ 100,000
1 ≤ times[i] ≤ 10,000
1 ≤ limit ≤ 1015
제한 시간 내에 퍼즐을 모두 해결할 수 있는 경우만 입력으로 주어집니다.
"""


def solution(diffs, times, limit):
    answer = max(diffs)

    diff_times = [0] * (max(diffs) + 1)
    for i in range(len(diffs)):
        diff_times[diffs[i]] += times[i] + (times[i - 1] if i > 0 else 0)

    now = 0
    usage = sum(times)
    while usage <= limit and answer > 1:
        if diff_times[answer] > 0:
            now += diff_times[answer]
        usage += now
        if usage > limit:
            break
        answer -= 1

    return answer


def solution(diffs, times, limit):
    answer = limit

    def calculate_usage(diff):
        usage = 0
        for j in range(len(diffs)):
            if diffs[j] <= diff:
                usage += times[j]
            else:
                time = times[j] + (times[j - 1] if j > 0 else 0)
                usage += (time * (diffs[j] - diff)) + times[j]
        return usage

    start, end = 1, max(diffs)
    while start <= end:
        mid = (start + end) // 2
        usage = calculate_usage(mid)
        if usage <= limit:
            answer = min(answer, mid)
            end = mid - 1
        else:
            start = mid + 1

    return answer


if __name__ == "__main__":
    solution([1, 5, 3], [2, 4, 7], 30)
    solution([1, 4, 4, 2], [6, 3, 8, 2], 59)
    solution([1, 328, 467, 209, 54], [2, 7, 1, 4, 3], 1723)
    solution([1, 99999, 100000, 99995], [9999, 9001, 9999, 9001], 3456789012)
