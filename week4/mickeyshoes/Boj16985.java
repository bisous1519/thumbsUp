import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj16985 {
		
	public static int N = 5;
	public static int answer;
	
	public static int[] dest;
	public static int[][] coords = {{1,0,0}, {-1,0,0}, {0,1,0}, 
									{0,0,1}, {0,-1,0}, {0,0,-1}};
	public static int[][] availableU = {{0,0}, {4,0}, {0,4}, {4,4}};
	public static int[][] availableD = {{4,4}, {0,4}, {4,0}, {0,0}};
	public static int[][][] map;
	
	
	public static boolean isDestination(int d, int x, int y) {
		return d==dest[0] && x==dest[1] && y==dest[2];
	}
	
	public static boolean isRange(int d, int x, int y) {
		return 0<=d && d<N && 0<=x && x<N && 0<=y && y<N;
	}
	
	public static int[][][] copyMap(){
		int[][][] newmap = new int[N][N][N];
		
		for(int d=0; d<N; d++) {
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					newmap[d][r][c] = map[d][r][c];
				}
			}
		}
		
		return newmap;
	}
	
	public static int[][][] settingMap(int[][][] target, int[] rotate, int[] index){
		
		int[][][] tempMap = new int[N][][];
		
		for(int i=0; i<N; i++) {
			tempMap[i] = target[index[i]];
			for(int r=0; r<rotate[i]; r++) {
				tempMap[i] = rotate(tempMap[i]);
			}
		}
		
		return tempMap;
		
	}
	
	public static int[][] rotate(int[][] arr){
		Queue<Integer> q = new LinkedList<>();
		
		for(int c=0; c<N; c++) {
			for(int r=N-1; r>=0; r--) {
				q.offer(arr[r][c]);
			}
		}
		
		int[][] newarr = new int[N][N];
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				newarr[r][c] = q.poll();
			}
		}
		
		return newarr;
	}
	
	
	public static void powerSet(int depth, int[] rotateCnt, int[] index) {
		if(depth==5) {
			int[][][] newmap = settingMap(copyMap(), rotateCnt, index);
			
			for(int i=0; i<availableU.length; i++) {
				if(newmap[0][availableU[i][0]] [availableU[i][1]] ==1
						&& newmap[4][availableD[i][0]][availableD[i][1]] ==1) {
					dest = new int[] {4, availableD[i][0], availableD[i][1]};
					BFS(0,availableU[i][0],availableU[i][1], newmap);		
				}
			}			
			
			return;
		}
		
		for(int i=0; i<4; i++) {
			rotateCnt[depth] = i;
			powerSet(depth+1, rotateCnt, index);
		}
	}
	
	public static void permutations(int depth, int status, int[] arr) {
		if(depth==5) {
			//돌릴 방향 정한
			powerSet(0, new int[N], arr);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if((status & (1<<i))==0) {				
				arr[depth] = i;
				permutations(depth+1, status|(1<<i), arr);
			}
			
		}
		
	}
	
	public static void BFS(int d, int x, int y, int[][][]map) {
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {d,x,y});

		boolean[][][] visited = new boolean[N][N][N];
		visited[d][x][y] = true;
		int depth = 0;
		
		while(!q.isEmpty()) {
			depth+=1;
			for(int iter=0, size=q.size(); iter<size; iter++) {
				int[] cur = q.poll();
				
				for(int i=0; i<coords.length; i++) {
					int nd = cur[0] + coords[i][0];
					int nx = cur[1] + coords[i][1];
					int ny = cur[2] + coords[i][2];
					
					if(isRange(nd,nx,ny) && !visited[nd][nx][ny] && map[nd][nx][ny]==1) {
						if(isDestination(nd,nx,ny)) {
							answer = Math.min(answer, depth);
							return;
						}
						visited[nd][nx][ny] = true;
						q.offer(new int[] {nd, nx, ny});
					}
				}
				
			}
			
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[N][N][N];
		dest = new int[3];
		answer = Integer.MAX_VALUE;
		
		for(int d=0; d<N; d++) {
			for(int r=0; r<N; r++) {
				String[] infos = br.readLine().split(" ");
				for(int c=0; c<N; c++) {
					map[d][r][c] = Integer.parseInt(infos[c]);
				}
			}
		}
		
		permutations(0,0,new int[5]);
		
		System.out.println(answer==Integer.MAX_VALUE? -1:answer);
	}

}
