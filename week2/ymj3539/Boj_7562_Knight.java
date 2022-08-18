package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_7562_Knight {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	
	static int T, I; 
	static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[][] map;
	static int[] start;
	static int[] end;
	static int turn;
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(input.readLine());
		
		// tc
		for(int t =1; t<=T; t++) {
			I = Integer.parseInt(input.readLine());
			map = new int[I][I];
			start = new int[2];
			end = new int[2];
			
			tokens = new StringTokenizer(input.readLine());
			start[0] = Integer.parseInt(tokens.nextToken());
			start[1] = Integer.parseInt(tokens.nextToken());
			
			tokens = new StringTokenizer(input.readLine());
			end[0] = Integer.parseInt(tokens.nextToken());
			end[1] = Integer.parseInt(tokens.nextToken());
			
			map[end[0]][end[1]] = 1;
			bfs(start[0], start[1]);
			
			// 출력
			sb.append(turn+"\n");
		}
		System.out.println(sb);
	}
	
	static void bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[I][I];
		// 초기값 넣기
		queue.offer(new int[] {r,c});
		turn = 0;
		
		while(!queue.isEmpty()) {
			
			int size = queue.size();
			
			while(size-- >0) {
				// 대장 데려오기
				int[] current = queue.poll();
				visited[current[0]][current[1]] = true;
				if(map[current[0]][current[1]] == 1) {
					return;
				}
				
				// 자식노드 탐색
				for(int i=0; i<dx.length; i++) {
					int nr = current[0] + dy[i];
					int nc = current[1] + dx[i];
					
					if(isIn(nr,nc) && visited[nr][nc] == false) {
						queue.offer(new int[] {nr,nc});
						visited[nr][nc] = true;
					}
						//else if(isIn(nr,nc) && visited[nr][nc] == false && map[nr][nc] == 1) {
//						turn++;
//						return turn;
//					}
					
				}
				
			}
			// 한 단계 넘어갈때마다 이동횟수 추가
			turn++;
		}
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && c>=0 && r<I && c<I);
	}
	
}
