
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
	static int N,M,ans;
	static int[][] map;
	static int[] dy = {-1,-1,0,1,1,1,0,-1};
	static int[] dx = {0,1,1,1,0,-1,-1,-1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			if(N == 0 && M == 0) break;
			
			map = new int[N][M];
			visited = new boolean[N][M];
			ans = 0;
			
			for(int i=0;i<N;++i) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0;i<N;++i) {
				for(int j=0;j<M;++j) {
					if(map[i][j] == 1 && visited[i][j] == false) {
						ans++;
						BFS(i,j); // 요소 탐색
					}
				}
			}
			
			sb.append(ans+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static void BFS(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r,c));
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			int qSize = q.size();
			
			while(qSize-- > 0) {
				Point cur = q.peek();
				
				q.poll();
				for(int i=0;i<8;++i) {
					int ny = cur.y + dy[i];
					int nx = cur.x + dx[i];
					
					if(isIn(ny,nx) == false) continue;
					if(visited[ny][nx] == true || map[ny][nx] == 0) continue; 
					visited[ny][nx] = true;
					
					q.offer(new Point(ny,nx));
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return r >=0 && r< N && c >=0 && c< M;
	}
	
	static class Point {
		int y;
		int x;
		
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}

