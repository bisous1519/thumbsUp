import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Boj17141 {
	
	public static int N;
	public static int M;
	public static int answer;
	
	public static int wallCnt;
	public static int blankCnt;

	public static int[] dxs = {1,0,0,-1};
	public static int[] dys = {0,1,-1,0};
	public static List<Virus> virusCandidate;
	
	public static int[][] map;
	
	public static boolean isRange(int x, int y) {
		return 0<=x && x<N && 0<=y && y<N;
	}
	
	public static void combinations(int depth, int start, int[] arr) {
		
		if(depth == M) {
			Virus[] viruses = new Virus[M];
			for(int i=0; i<arr.length; i++) {
				viruses[i] = virusCandidate.get(arr[i]);
			}
			answer = Math.min(answer, BFS(new int[N][N], viruses));
			return;
		}
		
		
		for(int i=start; i<virusCandidate.size(); i++) {
			arr[depth] = i;
			combinations(depth+1, i+1, arr);
		}
		
	}
	
	
	public static int BFS(int[][] status, Virus[] viruses) {
		int depth = 0;
		int cnt = 0;
		Queue<Virus> q = new LinkedList<Virus>();
		
		for(int i=0; i<viruses.length; i++) {
			q.offer(viruses[i]);
			status[viruses[i].x][viruses[i].y] = -1;
		}
		
		
		while(!q.isEmpty()) {
			depth+=1;
			for(int i=0, size=q.size(); i<size; i++) {
				
				Virus cur = q.poll();
				
				for(int d=0; d<dxs.length; d++) {
					int nx = cur.x + dxs[d];
					int ny = cur.y + dys[d];
					
					if(isRange(nx,ny) && map[nx][ny] !=1 && status[nx][ny] == 0) {
						cnt +=1;
						status[nx][ny] = depth;
						q.offer(new Virus(nx,ny));
					}
				}
				
			}
		}
		return cnt==blankCnt? depth:2501;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] infos = br.readLine().split(" ");
		N = Integer.parseInt(infos[0]);
		M = Integer.parseInt(infos[1]);
		map = new int[N][N];
		virusCandidate = new ArrayList<Virus>();
		answer = 2501;
		wallCnt = 0;
		for(int r=0; r<N; r++) {
			infos = br.readLine().split(" ");
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(infos[c]);
				
				if(map[r][c] == 2) {
					virusCandidate.add(new Virus(r,c));
				}
				else if (map[r][c] == 1) {
					wallCnt+=1;
				}
			}
		}
		
		blankCnt = N*N - wallCnt - M;
		
		combinations(0 ,0, new int[M]);
		System.out.println(answer==2501? -1:answer-1);
		
	}
	
	static class Virus{
		
		int x;
		int y;
		
		public Virus(int x, int y) {
			this.x =x; this.y= y;
		}
	}

}
