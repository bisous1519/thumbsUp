package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Boj3190 {
	
	public static int N;
	public static final int SNAKE = 2;
	public static final int APPLE = 5;
	
	public static int[] dxs = {-1,0,1,0};
	public static int[] dys = {0,1,0,-1};
	
	public static int[][] map;
		
	public static int convert2Int(String s) {
		return Integer.parseInt(s);
	}
	
	public static boolean isRange(int x, int y) {
		return 0<=x && x<N && 0<=y && y<N;
	}
	
	public static int Dummy(Queue<String[]> commands) {
		int time = 0;
		Deque<int[]> snake = new ArrayDeque<>();
		snake.offer(new int[] {0,0});
		map[0][0] = SNAKE;
		int dir = 1;
		while(true) {
			
			if(!commands.isEmpty() && time == convert2Int(commands.peek()[0])) {
				if(commands.peek()[1].equals("D")) {
					dir = (dir+1)%4;
				}
				else {
					dir = (dir+3)%4;
				}
				commands.poll();
			}
			
			int nx = snake.peek()[0] + dxs[dir];
			int ny = snake.peek()[1] + dys[dir];
			
			if(!isRange(nx,ny) || (isRange(nx,ny) && map[nx][ny] ==SNAKE)) {
				time+=1;
				break;
			}
			
			if(map[nx][ny]!=APPLE) {
				int[] coord = snake.pollLast();
				map[coord[0]][coord[1]]=0;
				
			}
			
			map[nx][ny] = SNAKE;
			snake.offerFirst(new int[] {nx, ny});
			time+=1;
		}
		
		
		return time;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = convert2Int(br.readLine());
		map = new int[N][N];
		int K = convert2Int(br.readLine());
		//사과 위치 -1씩 빼주어야함
		for(int r=0; r<K; r++) {
			String[] infos = br.readLine().split(" ");
			int tx = convert2Int(infos[0]) -1;
			int ty = convert2Int(infos[1]) -1;
			map[tx][ty] = APPLE;
			
		}
		int L = convert2Int(br.readLine());
		Queue<String[]> commands = new LinkedList<>();
		for(int i=0; i<L; i++) {
			commands.offer(br.readLine().split(" "));
		}
		
		int answer = Dummy(commands);
		System.out.println(answer);

	}

}
