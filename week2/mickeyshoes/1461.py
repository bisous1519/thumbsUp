import sys
input = sys.stdin.readline

N,M = map(int, input().rstrip('\n').split())
minus, plus = list(), list()
answer = 0

def padding(target:list)->list:
    for _ in range(M-len(target)%M):
        target.append(0)
    return sorted(target)

def pop_list_for_answer(target:list)->int:
    prev, answer = 0, 0
    while target:
        for _ in range(M):
            prev = max(prev, target.pop())
        answer += prev*2
        prev=0
    return answer

max_value = 0
for i in map(int, input().rstrip('\n').split()):
    max_value = max(max_value, abs(i))
    if i>0:
        plus.append(i)
    else:
        minus.append(-i)

minus = padding(minus)
plus = padding(plus)

answer += pop_list_for_answer(plus) + pop_list_for_answer(minus)

print(answer-max_value)