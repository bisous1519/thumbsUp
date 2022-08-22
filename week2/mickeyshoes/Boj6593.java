import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj6593 {

	public static int L;
	public static int R;
	public static int C;
	
	public static int targetL;
	public static int targetX;
	public static int targetY;
	
	public static int answer;
	
	public static int[][] coord = {
			{0,1,0},{0,-1,0},{0,0,1},{0,0,-1},
			{1,0,0},{-1,-0, 0}
	};
	public static char[][][] map;
	public static boolean[][][] visited;
	
	public static boolean isRange(int l, int x, int y) {
		return 0<=l && l<L && 0<=x && x<R && 0<=y && y<C;
	}
	
	public static boolean isTarget(int l, int x, int y) {
		return l==targetL && x==targetX && y==targetY;
	}
	
	public static void BFS(int l, int x, int y) {
		Queue<State> q = new LinkedList<>();
		visited[l][x][y] =true;
		q.offer(new State(l,x,y,0));
		
		while(!q.isEmpty()) {
			State s = q.poll();
			
			for(int i=0; i<coord.length; i++) {
				int nl = s.l + coord[i][0];
				int nx = s.x + coord[i][1];
				int ny = s.y + coord[i][2];

				if(isRange(nl, nx, ny) && isTarget(nl, nx, ny)) {
					answer = s.depth+1;
					return;
				}
				
				if(isRange(nl, nx, ny) && !visited[nl][nx][ny] && map[nl][nx][ny] == '.') {
					visited[nl][nx][ny] = true;
					q.offer(new State(nl, nx, ny, s.depth+1));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			String[] infos = br.readLine().split(" ");
			L = Integer.parseInt(infos[0]);
			R = Integer.parseInt(infos[1]);
			C = Integer.parseInt(infos[2]);
			if(L==R && R==C && C==0) break;
			
			map = new char[L][R][C];
			visited = new boolean[L][R][C];
			answer = 0;
			int layer=0; int x=0; int y=0;
			targetL =0; targetX=0; targetY=0;
			for(int l=0; l<L; l++) {
				for(int r=0; r<R; r++) {
					char[]cAry  = br.readLine().toCharArray();
					for(int c=0; c<C; c++) {
						map[l][r][c] = cAry[c];
						if(cAry[c] == 'S') {
							layer = l; x=r; y=c;
						}
						if(cAry[c] == 'E') {
							targetL = l; targetX=r; targetY=c;
						}
					}
				}
				br.readLine();
			}
			
			BFS(layer, x, y);
			
			if(answer !=0) {
				sb.append(String.format("Escaped in %d minute(s).\n", answer));
			}
			else {
				sb.append("Trapped!\n");
			}
			
		}
		
		System.out.println(sb.toString());

	}
	
	static class State{
		int l;
		int x;
		int y;
		int depth;
		
		public State(int l, int x, int y, int depth) {
			this.l=l; this.x=x; this.y=y; this.depth = depth;
		}
	}

}
