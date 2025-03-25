'''
문제 설명
XX 회사는 트럭을 이용해 상품을 운반합니다. 트럭은 최대 무게가 한정되어있습니다. 직원은 트럭에 상품을 순서대로 실으며, 상품을 실을 수 없는 트럭은 바로 목적지로 출발합니다. 이때 우리는 모든 상품을 운반하는데 필요한 트럭은 최소 몇 대인지 구하려 합니다.

예를 들어, 각 상품의 스펙이 다음과 같고, 트럭의 허용 무게가 300, 실어야 할 상품이 ["toy", "snack", "snack"]라고 합니다.

상품 이름	무게
toy	70
snack	200
이 경우 첫째 상품과 둘째 상품은 같은 트럭에 들어가지만, 셋째 상품은 다른 트럭에 넣어야 합니다. 따라서 필요한 트럭 수는 두 대 입니다.

상품	누적 무게	새 트럭
toy	70	불필요
snack	270	불필요
snack	200	필요
트럭의 허용 무게 max_weight와 상품의 스펙을 담은 배열 specs, 운반할 상품의 이름이 순서대로 들은 배열 names가 주어집니다. 이때, 상품을 순서대로 운반하기 위해 필요한 트럭 수를 리턴하는 함수, soution을 완성하세요.

제한 조건
max_weight는 1 이상 100,000 이하입니다.
specs의 길이는 1 이상 100,000 이하입니다.
specs의 원소는 [상품 이름, 상품 무게]를 나타냅니다.
상품 이름은 길이가 1 이상 10,000 이하인 문자열입니다.
상품 무게는 1 이상 max_weight 이하인 자연수를 나타내는 문자열입니다.
이름이 같은 상품은 없습니다.
names는 길이가 1 이상 10,000 이하인 배열입니다.
names의 원소는 모두 specs에 있는 상품입니다.
'''

def solution(max_weight, specs, names):
    answer = 1
    weight = max_weight
    spec_names = dict(specs)
    for name in names:
        if int(spec_names[name]) <= weight:
            weight -= int(spec_names[name])
        else:
            weight = max_weight - int(spec_names[name])
            answer += 1
    return answer

if __name__ == '__main__':
    solution(300, [["toy","70"], ["snack", "200"]], ["toy", "snack", "snack"])