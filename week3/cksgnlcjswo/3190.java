
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N,K,ans,L;
	static int[] dy = {0,1,0,-1}; //우 하 좌 상
	static int[] dx = {1,0,-1,0};
	static int[][] map;
	static List<Info> list;
	
 	public static void main(String[] args) throws Exception {
 		
 		N = Integer.parseInt(br.readLine());
 		K = Integer.parseInt(br.readLine());
 		map = new int[N+1][N+1];
 		list = new ArrayList<>();
 		
 		for(int i=0;i<K;++i) {
 			st = new StringTokenizer(br.readLine());
 			int r = Integer.parseInt(st.nextToken());
 			int c = Integer.parseInt(st.nextToken());
 			map[r][c] = 1; //사과 표시
 		}
 		L = Integer.parseInt(br.readLine());
 		
 		for(int i=0;i<L;++i) {
 			st = new StringTokenizer(br.readLine());
 			int t = Integer.parseInt(st.nextToken());
 			char d = st.nextToken().charAt(0);
 			
 			list.add(new Info(t,d));
 		}
 		
 		int idx = 0;//다음에 뱀이 움직일 시간을 가리키는 인덱스
 		
 		Snake snake = new Snake();
 		
 		for(int i=1;;++i) {
 			
 			snake.move(); //뱀 움직임
 			
 			if(snake.end) {
 				ans = i;
 				break;
 			}
 			
 			//명령에서 주어진 시간과 같을 때
 			if(idx < list.size() && list.get(idx).t == i) {
 				//왼쪽
 				if(list.get(idx).d == 'L') {
 					snake.dir--;
 					if(snake.dir < 0) snake.dir=3;
 				}
 				//오른쪽
 				else {
 					snake.dir++;
 					if(snake.dir > 3) snake.dir=0;
 				}
 				idx++;
 			}
 		}
 		System.out.println(ans);
 	}
 	
 	static boolean isIn(int r,int c) {
 		return r >= 1 && r<= N && c >= 1 && c<=N;
 	}
 	
 	static class Info {
 		int t;
 		char d;
 		
 		public Info(int t,char d) {
 			this.t = t;
 			this.d = d;
 		}
 	}
 	
 	static class Snake {
 		List<int[]> points;
 		int dir;
 		boolean end;
 		
 		public Snake() {
 			points = new ArrayList<>();
 			points.add(new int[] {1,1});
 			dir = 0;
 		}
 		
 		//뱀 움직임 구현
 		public void move() {
 			
 			int heady = points.get(0)[0] + dy[dir];
 			int headx = points.get(0)[1] + dx[dir];
 			
 			//머리가 벽에 부딪힘
 			if(isIn(heady,headx) == false) {
 				end = true;
 				return;
 			}
 			//머리가 자기 몸에 부딪힘
 			if(contain(heady,headx) == true) {
 				end = true;
 				return;
 			}
 			
 			int lasty = points.get(points.size()-1)[0];
 			int lastx = points.get(points.size()-1)[1];
 			
 			//이동
 			for(int i=points.size()-1;i>=1;--i) {
 				
 				points.get(i)[0] = points.get(i-1)[0];
 				points.get(i)[1] = points.get(i-1)[1];
	 		}
 			
 			//이동한 자리가 사과면
 			if(map[heady][headx] == 1) {
 				points.add(new int[] {lasty,lastx});
 				map[heady][headx] = 0;
 			} 
 			
 	 		points.get(0)[0] = heady;
 	 		points.get(0)[1] = headx;
 		}
 		
 		public boolean contain(int r,int c) {

 			for(int i=1;i<this.points.size();++i) {
 				
 				if(r == this.points.get(i)[0] && c == this.points.get(i)[1]) {
 					return true;
 				}
 			}
 			return false;
 		}
 		
 	}
}