
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static char[][] map;
	static int[] dy = {1,-1,0,0};
	static int[] dx = {0,0,-1,1};
	static boolean[][] visited;
	static int ans;
	static List<int[]> cand;
	
 	public static void main(String[] args) throws Exception {
 		map = new char[12][6];
 		cand = new ArrayList<>();
 		for(int i=0;i<12;++i) {
 			map[i] = br.readLine().toCharArray();
 		}
 		
// 		for(char[] row: map) {
// 			System.out.println(Arrays.toString(row));
// 		}
 		
 		List<int[]> remove = new ArrayList<>(); //이번 페이즈에 삭제할 점
 		while(true) {
 			
 			visited = new boolean[12][6];
 			
 			for(int i=11;i>=0;--i) {
 	 			for(int j=0;j<6;++j) {
 	 				//뿌요를 만나면
 	 				if(map[i][j] != '.' && !visited[i][j]) {
 	 					BFS(i,j);
 	 					
 	 					//뿌요 4개이상발견
 	 					if(cand.size()>=4) {
 	 						remove.addAll(cand);
 	 					}
 	 					cand.clear();
 	 				}
 	 			}
 	 		}
 			
 			//삭제된 뿌요없으면 종료
 			if(remove.size() == 0) break;
 			ans++;
 			
 			// 삭제될뿌요 .으로 바꾸기
 			for(int[] p : remove) {
 				map[p[0]][p[1]] = '.';
 			}
 			
 			//떠있는 점 내리기
 			gravity();		
 			remove.clear();
 		}
 		System.out.println(ans);
 	}
 	
 	static void gravity() {
 		
 		for(int i=11;i>=0;--i) {
			for(int j=0;j<6;++j) {
				//떠있는 뿌요발견
				if(map[i][j] != '.') {
					int r = i+1;
					
					while(r < 12 && map[r][j] == '.') r++;
					
					if(r-1 != i) {
						map[r-1][j] = map[i][j]; // 이동
						map[i][j] = '.';
					}
				}
			}
		}
 	}
 	
 	static void BFS(int r,int c) {
 		Queue<int[]> q = new LinkedList<>();
 		q.offer(new int[] {r,c});
 		visited[r][c] = true;
 		
 		cand.add(new int[] {r,c});
 		
 		while(!q.isEmpty()) {
 			int qSize = q.size();
 			
 			while(qSize-- > 0) {
 				int[] cur = q.poll();
 				
 				for(int i=0;i<4;++i) {
 					int ny = cur[0] + dy[i];
 					int nx = cur[1] + dx[i];
 					
 					if(isIn(ny,nx) == false) continue;
 					if(map[ny][nx] != map[cur[0]][cur[1]]) continue;
 					if(visited[ny][nx]) continue;
 					
 					visited[ny][nx] = true;
 					q.offer(new int[] {ny,nx});
 					cand.add(new int[] {ny,nx});
 				}
 			}
 		}
 	}
 	
 	static boolean isIn(int r,int c) {
 		return r >= 0 && r<12 && c >=0 && c < 6;
 	}
}