'''
문제 설명
N개의 마을로 이루어진 나라가 있습니다. 이 나라의 각 마을에는 1부터 N까지의 번호가 각각 하나씩 부여되어 있습니다. 각 마을은 양방향으로 통행할 수 있는 도로로 연결되어 있는데, 서로 다른 마을 간에 이동할 때는 이 도로를 지나야 합니다. 도로를 지날 때 걸리는 시간은 도로별로 다릅니다. 현재 1번 마을에 있는 음식점에서 각 마을로 음식 배달을 하려고 합니다. 각 마을로부터 음식 주문을 받으려고 하는데, N개의 마을 중에서 K 시간 이하로 배달이 가능한 마을에서만 주문을 받으려고 합니다. 다음은 N = 5, K = 3인 경우의 예시입니다.

배달_1_uxun8t.png

위 그림에서 1번 마을에 있는 음식점은 [1, 2, 4, 5] 번 마을까지는 3 이하의 시간에 배달할 수 있습니다. 그러나 3번 마을까지는 3시간 이내로 배달할 수 있는 경로가 없으므로 3번 마을에서는 주문을 받지 않습니다. 따라서 1번 마을에 있는 음식점이 배달 주문을 받을 수 있는 마을은 4개가 됩니다.
마을의 개수 N, 각 마을을 연결하는 도로의 정보 road, 음식 배달이 가능한 시간 K가 매개변수로 주어질 때, 음식 주문을 받을 수 있는 마을의 개수를 return 하도록 solution 함수를 완성해주세요.
'''

import heapq

def solution(N, road, K):
    answer = 0
    distances = [float('inf')] * (N + 1)
    distances[1] = 0

    check = []
    heapq.heapify(check)
    heapq.heappush(check, (0, 1))
    while check:
        d, s = heapq.heappop(check)
        for start, end, distance in road:
            d_distance = d + distance
            if s == start and distances[end] > d_distance:
                heapq.heappush(check, (d_distance, end))
                distances[end] = d_distance
            elif s == end and distances[start] > d_distance:
                heapq.heappush(check, (d_distance, start))
                distances[start] = d_distance
    answer = len([d for d in distances if d <= K])
    return answer

if __name__ == '__main__':
    solution(6, [[1,2,7],[1,6,14],[1,3,9],[2,3,10],[3,6,2],[3,4,11],[2,4,15],[6,5,9],[4,5,6]], 4)
