
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
3 3 5
.O.
...
O..
 * */


public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int R,C,N;
	static int[] dy = {1,-1,0,0};
	static int[] dx = {0,0,1,-1};
	static ArrayList<Point> arr;
	static char[][] map;
	static HashSet<Point> deleted;
 	public static void main(String[] args) throws Exception {
 		
 		st = new StringTokenizer(br.readLine());
 		
 		R = Integer.parseInt(st.nextToken());
 		C = Integer.parseInt(st.nextToken());
 		N = Integer.parseInt(st.nextToken());
 		arr = new ArrayList<>();
 		
 		map = new char[R][C];
 		//입력
 		for(int i=0;i<R;++i) {
 			map[i] = br.readLine().toCharArray();
 		}
 		
 		//짝수시간은 무조건 꽉차있음
 		if(N % 2 == 0) {
 			for(int i=0;i<R;++i) {
 				for(int j=0;j<C;++j) {
 					sb.append("O");
 				}
 				sb.append("\n");
 			}
 			System.out.println(sb.toString());
 			return;
 		}
		
		if(N==1) {
			print();
			System.out.println(sb.toString());
			return;
		}
		
		//터질 점들을 모아둠
		deleted = new HashSet<>();
		
		//폭탄 있는 곳 터뜨리기
		simulate();
		
		//모든 점이 터진다면 시간이 홀 수 일때 결과는 같음
		if(deleted.size() == R*C || arr.size() == 0) {
			for(int i=0;i<R;++i) {
	 			for(int j=0;j<C;++j) {
	 				sb.append('.');
	 			}
	 			sb.append("\n");
	 		}
			System.out.println(sb.toString());
			return;
		}
		
		//3,7,11...
		if((N-1)%4 != 0) {
			print();
		}
		//5.9,13 ...
		else {
			deleted.clear();
			arr.clear();
			
			simulate();// 한번 더 실생히킴
			
			print();
		}
		
 		System.out.println(sb.toString());
 	}	
 	
 	static void simulate() {
 		
 		//폭탄 있는곳 검사
 		for(int r=0;r<R;++r) {
			for(int c=0;c<C;++c) {
				if(map[r][c] == 'O') {
					arr.add(new Point(r,c));
				}
			}
		}
 		
 		//터질 곳 표시
 		for(Point p:arr) {
			
			deleted.add(new Point(p.y,p.x));
			
			for(int i=0;i<4;++i) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				
				if(isIn(ny,nx) == false) continue;
				deleted.add(new Point(ny,nx));
			}
		}
 		
 		Point tmp = new Point(0,0);
 		
 		for(int i=0;i<R;++i) {
 			for(int j=0;j<C;++j) {
 				tmp.y = i;
 				tmp.x = j;
 				if(deleted.contains(tmp) == true) {
 					map[i][j] = '.';
 				} else map[i][j] = 'O';
 			}
 		}
 		
 	}
 	
 	static void print() {
 		for(int i=0;i<R;++i) {	
			for(int j=0;j<C;++j) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
 	}
 	static boolean isIn(int r,int c) {
 		return r >= 0 && r < R && c >=0 && c < C;
 	}
 	
 	static class Point {
 		int y;
 		int x;
 		
 		public Point(int y,int x) {
 			this.y = y;
 			this.x = x;
 		}
 		
 		@Override
 		public boolean equals(Object obj) {
 			if(obj != null && obj instanceof Point) {
 				Point tmp = (Point)obj;
 				return this.y==tmp.y && this.x == tmp.x;
 			}
 			return false;
 		}
 		
 		@Override
 		public int hashCode() {
 			int prime = 31;
 			int result = x * prime;
 			result = result * y * prime;
 			return result;
 		}
 	}
}