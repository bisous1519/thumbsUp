
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N;
	static boolean ans;
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	static int[] order;
	static char[][] map;
	static List<int[]> t; //선생님 좌표
	static List<int[]> cand; //장애물 후보 좌표
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		t = new ArrayList<>();
		cand = new ArrayList<>();
		
		for(int i=0;i<N;++i) {
			String row = br.readLine();
			for(int j=0;j<N;++j) {
				map[i][j] = row.charAt(j*2);
				
				if(map[i][j] == 'T') {
					t.add(new int[] {i,j});
				} else if(map[i][j] == 'X') {
					cand.add(new int[] {i,j});
				}
			}
		}
		
		order = new int[3]; //3개 고를 후보
		Combination(0,0);
		
		if(ans == true) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
		
//		for(char[] row:map) {
//			System.out.println(row);
//		}
		
	}
	
	static void Combination(int nth, int start) {
		if(ans == true) return;
		
		if(nth == 3) {
			simulate();
			
			return;
		}
		
		for(int i=start;i<cand.size();++i) {
			order[nth] = i;
			Combination(nth+1,i+1);
		}
	}
	
	static void simulate() {
		
		for(int i=0;i<3;++i) {
			int[] pos = cand.get(order[i]); //후보자리 좌표
			map[pos[0]][pos[1]] = 'O'; // 장애물 설치		
		}
		
		boolean possible = true;
		
		//선생님들 위치에서 검사 시작
		outer : for(int[] pos : t) {
			int cury = pos[0];
			int curx = pos[1];
			
			for(int i=0;i<4;++i) {
				int ny = cury + dy[i];
				int nx = curx + dx[i];
				
				while(isIn(ny,nx)) {
					//학생 발견시
					if(map[ny][nx] == 'S') {
						possible = false;
						break outer;
					} else if(map[ny][nx] == 'O') break;
					
					ny += dy[i];
					nx += dx[i];
				}
			}
		}
		
		ans |= possible;
		
		for(int i=0;i<3;++i) {
			int[] pos = cand.get(order[i]); //후보자리 좌표
			map[pos[0]][pos[1]] = 'X'; // 원상복구		
		}
	}
	
	static boolean isIn(int r,int c) {
		return r >=0 && r < N && c >=0 && c <N;
	}
}
	