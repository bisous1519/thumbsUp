import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int r1,r2,c1,c2;
	static int dy[] = {0,-1,0,1}; //우 상 좌 하
	static int dx[] = {1,0,-1,0};
	static int[][] map;
	
 	public static void main(String[] args) throws Exception {
 		
 		st = new StringTokenizer(br.readLine());
 		
 		r1 = Integer.parseInt(st.nextToken());
 		c1 = Integer.parseInt(st.nextToken());
 		r2 = Integer.parseInt(st.nextToken());
 		c2 = Integer.parseInt(st.nextToken());

 		map = new int[r2-r1+1][c2-c1+1];
 		
 		int cury = 0, curx = 0;
 		int count = 1;
 		int iter=1,dir=0;
 		int candMax = 0;
 		int fillCount = 0;
 		int endCount = (r2-r1+1) * (c2-c1+1);
 		
 		//시작점이 들어온다면
 		if(isIn(0,0)) {
 			map[Math.abs(r1)][Math.abs(c1)] = 1;
 			fillCount = 1;
 		}
 		
 		outer : while(true) {
 			
 			//각 방향은 두번씩 반복
 			for(int i=0;i<2;++i) {
 				
 				//iter만큼 진행
 				for(int j=0;j<iter;++j) {
 					int ny = cury + dy[dir];
 	 				int nx = curx + dx[dir];
 	 				
 	 				count++;
 	 				//범위에 들어오는 수만 담기
 	 				if(isIn(ny,nx)) {
 	 					map[ny - r1][nx - c1] = count;
 	 					fillCount++;
 	 					candMax = Math.max(candMax,count);
 	 				}
 	 				
 	 				if(fillCount == endCount) {
 	 					break outer;
 	 				}
 	 				cury = ny;
 	 				curx = nx;
 				}
 				dir++;
	 			dir%=4;
 			}
 			iter++;
 		}
 		
 		int maxDigitLength = String.valueOf(candMax).length();
	
 		for(int i=0 ; i< r2-r1+1;++i) {
 			for(int j=0; j< c2-c1+1;++j) {
 				String cur = String.valueOf(map[i][j]);
 				
 				for(int k=0;k<maxDigitLength - cur.length();++k) sb.append(" ");
 				sb.append(map[i][j]+" ");
 			}
 			sb.append("\n");
 		}
 		
 		System.out.println(sb.toString());
 	}
 	
 	static boolean isIn(int r, int c) {
 		return r >= r1 && r <= r2 && c >= c1 && c <= c2;
 	}
}