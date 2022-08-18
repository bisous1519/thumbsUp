package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Boj_2667_danji {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	
	static int N;
	static int[][] map;
	static int[] dy = {1, -1, 0 ,0}; 
	static int[] dx = {0, 0, 1, -1};
	static boolean[][] visited;
	static List<Integer> list = new ArrayList<>();
	static int total;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(input.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		for(int i =0; i<N; i++) {
			String str = input.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		bfs();
		
		Collections.sort(list);
		
		System.out.println(total);
		for(int i =0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		
			
	}
	
	static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		
		// 1찾기 
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(map[r][c] == 1 && visited[r][c] == false) {
					int cnt=0;
					queue.offer(new Point(r,c));
					// 첫번째 지점도 방문 체크!
					visited[r][c] = true;
					
					while(!queue.isEmpty()) {
						Point current = queue.poll();
						cnt++;
						
						for(int i=0; i<4; i++) {
							int nr = current.r + dy[i];
							int nc = current.c + dx[i];
							
							if(isIn(nr,nc) && map[nr][nc] == 1 && visited[nr][nc] == false) {
								queue.offer(new Point (nr, nc));
								visited[nr][nc] = true;
							}
//							System.out.println(cnt);
						}
						
					}
					list.add(cnt);
					total++;
				}
				
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return (r>=0 && c>=0 && r<N && c<N);
	}
	
	public static class Point {
		int r;
		int c;
		Point(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
}
