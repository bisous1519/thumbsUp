
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
	static int R,C,ans;
	static char[][] map;
	static boolean possible,end;
	static Queue<Point> f,f_nxt;
	static Queue<Point> jihoon,j_nxt;
	static boolean[][] visited;
	static int[] dx= {0,0,1,-1};
	static int[] dy= {1,-1,0,0};
	
	public static void main(String[] args) throws Exception {
		
		st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		visited = new boolean[R][C];
		map = new char[R][C];
		jihoon = new LinkedList<>();
		j_nxt = new LinkedList<>();
		f = new LinkedList<>();
		f_nxt = new LinkedList<>();
		
		for(int i=0;i<R;++i) {
			String row=br.readLine();
			
			for(int j=0;j<C;++j) {
				map[i][j] = row.charAt(j);
				if(map[i][j] == 'J') {
					map[i][j] = '.';
					visited[i][j] = true;
					jihoon.add(new Point(i,j));
				} else if(map[i][j] == 'F') {
					f.add(new Point(i,j));
					visited[i][j] = true;
				}
			}
		}
		
		for(int i=1;;++i) {
			move(); // 지훈이 이동
			fire(); // 불이동
			
			if(end == true) {
				ans = i;
				break;
			}
		}
		
		if(possible == false) {
			System.out.println("IMPOSSIBLE");
		} else {
			System.out.println(ans);
		}
		
	}
	
	static void move() {
		
		while(!jihoon.isEmpty()) {
			int qSize = jihoon.size();
			
			while(qSize-- > 0) {
				Point cur = jihoon.peek();
				
				jihoon.poll();
				//불이 난곳
				if(map[cur.y][cur.x] == 'F') continue;
				
				for(int i=0;i<4;++i) {
					int ny = cur.y + dy[i];
					int nx = cur.x + dx[i];
					
					//게임 종료 조건
					if(isIn(ny,nx) == false) {
						end = true;
						possible = true;
						return;
					}
					
					if(visited[ny][nx] == true) continue;
					if(map[ny][nx] != '.') continue;
					
					visited[ny][nx] = true;					
					j_nxt.offer(new Point(ny,nx));
				}
			}
		}
		//다음 움직일 공간이 없었음
		if(j_nxt.isEmpty()) {
			end = true;
		}
		
		while(!j_nxt.isEmpty()) {
			jihoon.offer(j_nxt.peek());
			j_nxt.poll();
		}
	}
	
	static void fire() {
		
		while(!f.isEmpty()) {
			int qSize = f.size();
			
			while(qSize-- > 0) {
				Point cur = f.peek();
				
				f.poll();
				
				for(int i=0;i<4;++i) {
					int ny = cur.y + dy[i];
					int nx = cur.x + dx[i];
					
					if(isIn(ny,nx) == false) continue;
					if(map[ny][nx] != '.') continue;
					
					visited[ny][nx] = true;
					map[ny][nx] = 'F'; // 불 전염
					
					f_nxt.offer(new Point(ny,nx));
				}
			}
		}
		
		while(!f_nxt.isEmpty()) {
			f.offer(f_nxt.peek());
			f_nxt.poll();
		}
	}
	
	static boolean isIn(int r, int c) {
		return r>=0 && r < R && c>=0 && c < C;
	}
}

class Point {
	int y;
	int x;
	
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}