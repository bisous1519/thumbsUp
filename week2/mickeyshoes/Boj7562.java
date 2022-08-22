import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj7562 {

	public static int M;
	public static int AR;
	public static int AC;
	public static int answer;
	
	public static int[] dxs = {-1, -2, -2, -1, 1, 2, 2, 1};
	public static int[] dys = {-2, -1, 1, 2, -2, -1, 1, 2};
	
	public static int [][] map;
	public static boolean[][] visited;
	
	public static int convert2Int(String s) {
		return Integer.parseInt(s);
	}
	
	public static boolean isRange(int x, int y) {
		return 0<=x && x<M && 0<=y && y<M;
	}
	
	public static void BFS(int x, int y) {
		Queue<Coord> q = new LinkedList<>();
		q.offer(new Coord(x,y,0));
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			Coord c = q.poll();
			
			for(int i=0; i<dxs.length; i++) {
				int nx = c.x + dxs[i];
				int ny = c.y + dys[i];
				
				if(isRange(nx,ny) && !visited[nx][ny]) {
					if (nx == AR && ny == AC) {
						answer = c.depth+1;
						return;
					}
					visited[nx][ny] = true;
					q.offer(new Coord(nx,ny, c.depth+1));
				}
			}
			
			
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = convert2Int(br.readLine());
		for(int t=0; t<T; t++) {
			answer = 0;
			M = convert2Int(br.readLine());
			map = new int[M][M];
			visited = new boolean[M][M];
			String[] temp = br.readLine().split(" ");
			int x = convert2Int(temp[0]);
			int y = convert2Int(temp[1]);
			temp = br.readLine().split(" ");
			AR = convert2Int(temp[0]);
			AC = convert2Int(temp[1]);
			if(x!=AR || y!=AC)
				BFS(x,y);
			
			sb.append(answer).append('\n');
			
		}
		System.out.println(sb.toString());

	}
	
	static class Coord{
		int x;
		int y;
		int depth;
		
		public Coord(int x, int y, int depth) {
			this.x =x; this.y =y; this.depth = depth;
		}
	}

}
