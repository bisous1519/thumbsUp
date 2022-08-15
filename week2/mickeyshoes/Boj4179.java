import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj4179 {
	
	public static int R;
	public static int C;
	public static int[] dxs = {1,0,0,-1};
	public static int[] dys = {0,1,-1,0};

	public static char[][] map;
	public static boolean[][] visited;
	public static FiredStatus[][] firedStatusAry;
	
	static class FiredStatus{
		public int x;
		public int y;
		public int depth;
		
		public FiredStatus(int x, int y, int depth) {
			this.x =x; this.y= y; this.depth = depth;
		}
	}
	
	public static boolean isRange(int x, int y) {
		return 0<=x && x<R && 0<=y && y<C;
	}
		
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		R = Integer.parseInt(temp[0]);
		C = Integer.parseInt(temp[1]);
		map = new char[R][];
		firedStatusAry = new FiredStatus[R][C];
		Queue<FiredStatus> fireq = new LinkedList<>();
		Queue<FiredStatus> q = new LinkedList<>();
		visited= new boolean[R][C];
		
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<map[i].length; j++) {
				FiredStatus f = new FiredStatus(i,j,1000001);
				firedStatusAry[i][j] = f;
				if(map[i][j] == 'F') {
					f.depth=0;
					fireq.offer(f);
					visited[i][j] = true;
				}
				else if(map[i][j] == 'J') {
					q.offer(new FiredStatus(i,j,0));
				}
			}
		}
		
		//1.set on fire
		while(!fireq.isEmpty()) {
			FiredStatus fs = fireq.poll();
			
			for(int i=0; i<dxs.length; i++) {
				int nx = fs.x + dxs[i];
				int ny = fs.y + dys[i];
				
				if(isRange(nx,ny) && !visited[nx][ny] && map[nx][ny] != '#') {
					visited[nx][ny] = true;
					FiredStatus tempF = new FiredStatus(nx,ny,fs.depth+1);
					firedStatusAry[nx][ny] = tempF;
					fireq.offer(tempF);
				}
			}
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				visited[i][j] = false;
			}
		}
		
		
		//2. escape map
		boolean flag = true;
		while(!q.isEmpty() && flag) {
			FiredStatus fs = q.poll();
			for(int i=0; i<dxs.length; i++) {
				int nx = fs.x + dxs[i];
				int ny = fs.y + dys[i];
				
				if(!isRange(nx,ny) && (map[fs.x][fs.y]=='.' | map[fs.x][fs.y]== 'J') && fs.depth < firedStatusAry[fs.x][fs.y].depth) {
					System.out.println(fs.depth+1);
					flag = false;
					break;
				}
				
				if(isRange(nx,ny) && !visited[nx][ny] && map[nx][ny]=='.' && fs.depth < firedStatusAry[nx][ny].depth) {
					visited[nx][ny] = true;
					q.offer(new FiredStatus(nx,ny, fs.depth+1));
				}
			}
		}
		

		if(flag)
			System.out.println("IMPOSSIBLE");
		
	}

}
