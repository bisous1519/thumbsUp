import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj11559 {
		
	public static final int R = 12;
	public static final int C = 6;
	
	public static int[] dxs= {1,0,0,-1};
	public static int[] dys= {0,1,-1,0};
	
	public static char[][] map;
	public static boolean[][] visited;
	
	public static boolean isRange(int x, int y) {
		return 0<=x && x<R && 0<=y && y<C;
	}
	
	public static int Puyo(int x, int y, char type) {
		Queue<Point> q = new LinkedList<>();
		Queue<Point> results = new LinkedList<>();
		q.offer(new Point(x,y,type));
		results.offer(new Point(x,y,type));
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int d=0; d<dxs.length; d++) {
				int nx = cur.x + dxs[d];
				int ny = cur.y + dys[d];
				
				if(isRange(nx,ny) && !visited[nx][ny] && map[nx][ny] == type) {
					visited[nx][ny] = true;
					Point np = new Point(nx,ny, type);
					q.offer(np);
					results.offer(np);
				}
			}
		}
		
		if(results.size()>=4) {
			startPuyo(results);
			return 1;
		}
		
		
		return 0;
	}
	
	public static void startPuyo(Queue<Point> q) {
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			map[cur.x][cur.y] = '.';
		}
		
	}
	
	public static void swap(int x, int y, int nx, int ny) {
		char temp = map[x][y];
		map[x][y] = map[nx][ny];
		map[nx][ny] = temp;
	}
	
	public static void resizingMap() {
		// 밑이 .이고 나는 알파벳인경우다.
		for(int r=R-1; r>=0; r--) {
			for(int c=C-1; c>=0; c--) {
				int nr = r;
				while(isRange(++nr,c) && map[nr-1][c] !='.' && map[nr][c]=='.') {
					swap(nr-1,c, nr,c);
				}
			}
		}
	}
	
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[R][];
		
		for(int r=0; r<R; r++) {
			map[r] = br.readLine().toCharArray();
		}
		
		boolean flag = true;
		int answer = 0;
		while(flag) {
			visited = new boolean[R][C];
			int temp = 0;
			
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					if(!visited[r][c] && map[r][c] != '.') {
						visited[r][c] = true;
						temp += Puyo(r,c, map[r][c]);
					}
				}
			}
			
			resizingMap();

			if(temp == 0) flag = false;
			else answer +=1;
		}
		
		System.out.println(answer);

	}
	
	static class Point{
		int x;
		int y;
		char type;
		
		public Point(int x, int y, char type) {
			this.x =x; this.y= y; this.type = type;
		}
	}

}
