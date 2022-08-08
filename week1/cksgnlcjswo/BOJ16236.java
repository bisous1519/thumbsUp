import java.util.*;
import java.io.*;
/*
    observation: 상어가 특정 시간에서 최소의 물고기를 찾는데는 BFS를 써서 요소를 탐색해야함

    0 0 0 0 0 9
    0 3 3 0 0 0
    3 2 3 0 0 0
    3 3 3 0 0 0

    이런케이스에서 상어는 더이상 먹을 물고기가 없다!
 */
class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static int N;
    static int[][] map;
    static int dx[] = {0,0,1,-1};
    static int dy[] = {1,-1,0,0};
    static Shark s;
    static int ans;

    public static void main(String[] args) throws Exception {
        
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0;i<N;++i) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;++j) {
                map[i][j]  = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    s = new Shark(i,j);                
                    map[i][j] = 0;
                }
            }
        }

         while(true) {
            Point nxt = findFish();
           
            
            if(nxt.y == -1) break; // 먹을 고기가 없음
            
            move(nxt); // 아기상어 이동
            
        }
        System.out.println(ans);
    }
    static void move(Point nxt) {
        ans += nxt.dist; //움직여야 하는 최소 경로

        map[nxt.y][nxt.x] = 0;

        s.cnt += 1; //물고기 먹음
        s.y = nxt.y;
        s.x = nxt.x;

        if(s.cnt == s.size) {
            s.size++;
            s.cnt = 0;
        }

    }

    static Point findFish() {
        
        int x=-1,y=-1,candDist=Integer.MAX_VALUE;
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        q.add(new Point(s.y,s.x));
        visited[s.y][s.x] = true;

        while(!q.isEmpty()) {

            Point cur = q.peek();
            //물고기 중에서 거리가 최소인 애들 저장 
            q.poll();

            for(int i=0;i<4;++i) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                //상어 크기가 더 작거나 범위를 벗어나면 못감.
                if(!isIn(ny,nx) || s.size < map[ny][nx] || visited[ny][nx] == true) continue;
                
                Point nxt = new Point(ny,nx,cur.dist+1);                
                visited[ny][nx] = true;

                //현재 상어보다 작은 물고기일 때 검사
                if(map[ny][nx] > 0 && s.size > map[ny][nx]) {
                    if(nxt.dist < candDist) {
                        candDist = nxt.dist;
                        y = ny;
                        x = nx;
                    } else if(nxt.dist == candDist) {
                        //거리가 같다면 y값이 작은 것
                        if(ny < y) {
                            y = ny;
                            x = nx;
                        //y값이 같다면 x가 작은 순
                        } else if(ny == y) {
                            if(nx < x) {
                                x = nx;
                            }
                        }
                    }
                }

                q.offer(nxt);
            }
        }

        return new Point(y,x,candDist);
    }

    static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}

class Shark {
    int y;
    int x;
    int size;
    int cnt; //물고기 먹은 개수

    Shark(int y, int x) {
        this.y = y;
        this.x = x;
        size = 2;
        cnt = 0;
    }
}

class Point {
    int y;
    int x;
    int dist;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
        dist = 0;
    }

    public Point(int y, int x,int dist) {
        this.y = y;
        this.x = x;
        this.dist = dist;
    }
}