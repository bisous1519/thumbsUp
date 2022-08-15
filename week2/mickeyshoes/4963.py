import sys
from collections import deque
input = sys.stdin.readline
ary = []
visited = []
w,h, = 0,0
coord = [(1,0), (-1,0), (0,1), (0,-1),
        (-1,-1), (-1,1), (1,-1), (1,1)]

def is_range(x:int, y:int)->bool:
    return 0<=x<h and 0<=y<w

def BFS(x:int, y:int)->None:
    q= deque()
    q.append((x,y))

    while q:
        x,y = q.popleft()

        for dx,dy in coord:
            nx = x+dx
            ny = y+dy

            if is_range(nx,ny) and not visited[nx][ny] and ary[nx][ny] =="1":
                visited[nx][ny] = True
                q.append((nx,ny))

while True:
    w,h = map(int, input().rstrip('\n').split())
    if(w==0 and h==0): break

    ary = [input().rstrip('\n').split() for _ in range(h)]
    visited = [[False]*w for _ in range(h)]
    answer = 0

    for i in range(h):
        for j in range(w):
            if ary[i][j] == "1" and not visited[i][j]:
                visited[i][j] = True
                answer+=1
                BFS(i,j)

    print(answer)