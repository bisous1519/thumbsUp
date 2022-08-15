import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj4963 {
	
	public static int W;
	public static int H;
	public static int[] dxs = {1,0,0,-1,-1,-1,1,1};
	public static int[] dys = {0,1,-1,0,-1,1,-1,1};
	public static int[][] map;
	public static boolean[][] visited;
	
	public static boolean isRange(int x, int y) {
		return 0<=x && x<H && 0<=y && y<W;
	}
	
	public static void BFS(int x, int y) {
		Queue<Coord> q = new LinkedList<>();
		q.offer(new Coord(x,y));
		
		while(!q.isEmpty()) {
			Coord c = q.poll();
			
			for(int i=0; i<dxs.length; i++) {
				int nx = c.x + dxs[i];
				int ny = c.y + dys[i];
				
				if(isRange(nx,ny) && !visited[nx][ny]&& map[nx][ny]==1) {
					visited[nx][ny] = true;
					q.offer(new Coord(nx,ny));
				}
			}
			
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String[] temp = br.readLine().split(" ");
			W = Integer.parseInt(temp[0]);
			H = Integer.parseInt(temp[1]);
			
			if(W==H && W==0) break;
			
			map = new int[H][W];
			visited = new boolean[H][W];
			for(int r=0; r<H; r++) {
				temp = br.readLine().split(" ");
				for(int c=0; c<W; c++) {
					map[r][c] = Integer.parseInt(temp[c]);
				}
			}
			int answer = 0;
			for(int r=0; r<H; r++) {
				for(int c=0; c<W; c++) {
					if(!visited[r][c] && map[r][c]==1) {
						visited[r][c] = true;
						answer +=1;
						BFS(r,c);
					}
				}
			}
			
			System.out.println(answer);
		}
	}
	
	static class Coord{
		int x;
		int y;
		public Coord(int x, int y) {
			this.x =x; this.y = y;
		}
	}

}
