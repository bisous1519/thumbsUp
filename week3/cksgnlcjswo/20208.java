
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N,M,H,ans;
	static int[][] map;

	static int sy,sx;
	static List<Point> arr;
	static int[] order;
	public static void main(String[] args) throws Exception {
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		arr = new ArrayList<>();
		
		for(int i=0;i<N;++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 1) {
					sy = i;
					sx = j;
				} else if(map[i][j] == 2) {
					arr.add(new Point(i,j));
				}
			}
		}

		order = new int[arr.size()];
		
		for(int i=0;i<arr.size();++i) {
			order[i] = i;
		}
		
		if(arr.size() == 0) {
			System.out.println(0);
			return;
		}
		
		do {
		
			int dist1 = calDist(sy,sx,arr.get(order[0]).y,arr.get(order[0]).x);
			
			if(dist1 > M) continue;
			int curH = M-dist1 + H;
			int i =0;
			int prev = -1; //집으로 올 수 있는 마지막 인덱스
			
			for(;i<arr.size()-1;++i) {
				//order[i] -> order[i+1]
				int nxtDist = calDist(arr.get(order[i]).y, arr.get(order[i]).x, 
						arr.get(order[i+1]).y, arr.get(order[i+1]).x);
				
				// order[i] -> home
				int back = calDist(arr.get(order[i]).y, arr.get(order[i]).x, sy, sx);
				
				//다음지점 못가는 경우
				if( nxtDist> curH ) {				
					break;
				}
				
				//집으로 못돌아감
				if(back > curH) {
					curH = curH - nxtDist + H;
					continue;
				}
				
				curH = curH - nxtDist + H;
				prev = i;
				
				//i이전으로는 올 수 있다는 것이고 여기까지오면 i+1로도 갈수 있다는것. i 개수 세준것임
			}
			
			int distLast = calDist(sy,sx,arr.get(order[i]).y,arr.get(order[i]).x);
			
			if(distLast <= curH) {
				curH -= distLast; // 시작지점으로의 거리
				
				if(curH >= 0) prev = i; 
			}	
		
			ans = Math.max(ans, prev+1);
			
		} while(next_permutation() != false);
		
		System.out.println(ans);
	}
	
	static int calDist(int r1,int c1, int r2, int c2) {
		return Math.abs(r1-r2) + Math.abs(c1-c2);
	}
	
	static boolean next_permutation() {
		int i = order.length -1;
		while(i > 0 && order[i-1] >=order[i]) i--;
		
		if(i==0) return false;
		
		int j= order.length - 1;
		while(order[i-1] >= order[j]) --j;
		
		swap(order,i-1,j);
		
		int k = order.length - 1;
		while(i < k) swap(order,i++,k--);
		return true;
	}
	
	static void swap(int[] order, int i,int j) {
		int tmp = order[i];
		order[i] = order[j];
		order[j] = tmp;
	}
	//r,c에서 현재 체력 h일 때 cur개의 민트초코 마심
	
	
	static class Point {
		int y;
		int x;
		
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}