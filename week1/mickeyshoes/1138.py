import sys
input = sys.stdin.readline
N= int(input().rstrip('\n'))

visited = [True] * N
cnt = list(map(int, input().rstrip('\n').split()))
answer = [0] * N

# 각 키마다
for i in range(1, N+1):
    # 자리를 찾는데 이미 자리가 찬 경우나 여러 자리가 있는 경우, 비어있는 원소의 갯수로 누가 들어올지 체크
    if cnt[i-1] == 0:
        for j in range(N):
            if visited[j]:
                answer[j] = i
                visited[j] = False
                break
    else:
        for j in range(0, N):
            # 방문 한 적 없고 false 의 갯수가 cnt 갯수와 같으면
            if visited[j] and sum(visited[:j])== cnt[i-1]:
                answer[j] = i
                visited[j] = False
                break

print(*answer)