import sys, heapq
input = sys.stdin.readline

N,K = map(int, input().rstrip('\n').split())
ary = [list(map(int, input().rstrip('\n').split())) for _ in range(N)]
S,X,Y = map(int, input().rstrip('\n').split())
dxs, dys, = [1,0,0,-1], [0,1,-1,0]
heap = []
[heapq.heappush(heap, (ary[r][c],r,c)) for r in range(N) for c in range(N) if ary[r][c] != 0]

def is_range(x:int, y:int)->bool:
    return 0<=x<N and 0<=y<N

def infection(coord:heapq):
    new_heap = []
    while(len(coord)>0):
        n,x,y = heapq.heappop(coord)

        for dx,dy in zip(dxs,dys):
            nx = x + dx
            ny = y + dy

            if is_range(nx,ny) and ary[nx][ny] == 0:
                ary[nx][ny] = n
                heapq.heappush(new_heap, (n, nx, ny))

    return new_heap

for _ in range(S):
    heap = infection(heap)

print(ary[X-1][Y-1])