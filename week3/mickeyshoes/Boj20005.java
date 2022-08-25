package com.ssafy.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Boj20005 {
	
	
	public static int N;
	public static int M;
	public static Player boss;
	
	public static int[] dxs= {1,0,0,-1};
	public static int[] dys = {0,1,-1,0};
	
	public static char[][] map;
	
	public static int convertIdx(char c) {
		return c - 'a';
	}
	
	public static boolean isRange(int x, int y) {
		return 0<=x && x<N && 0<=y && y<M;
	}
	
	public static int getDistance(Player p) {
	
		boolean[][] visited = new boolean[N][M];
		Queue<Player> q = new LinkedList<>();
		visited[p.x][p.y] = true;
		q.offer(p);

		int depth = 0;
		while(!q.isEmpty()) {
			depth+=1;
			for(int t=0,size=q.size(); t<size; t++) {
				Player cur = q.poll();
				
				for(int i=0; i<dxs.length; i++) {
					int nx = cur.x + dxs[i];
					int ny = cur.y + dys[i];
					
					if(isRange(nx,ny) && !visited[nx][ny] && map[nx][ny] != 'X') {
						visited[nx][ny] = true;
						
						if(nx == boss.x && ny == boss.y)
							return depth+1;
						
						q.offer(new Player(nx,ny, cur.atk));
					}
				}
				
			}
			
		}
		return 0;
	}
	
	public static int killBoss(PriorityQueue<Player> pq) {
		int time = 0;
		int cnt = 0;
		ArrayList<Player> attacker = new ArrayList<>();
		
		while(true) {
			int prefixSum = 0;
			for(int i=0; i<attacker.size(); i++) {
				prefixSum += attacker.get(i).atk;
			}
			
			if(boss.atk<= prefixSum) {
				cnt = attacker.size();
				break;
			}
			boss.atk -= prefixSum;
//			System.out.println(time+": boss: "+boss.atk+" attack: "+prefixSum);
			
			time+=1;
			while(!pq.isEmpty() && pq.peek().time == time)
				attacker.add(pq.poll());
		}
		
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] infos = br.readLine().split(" ");
		N = Integer.parseInt(infos[0]);
		M = Integer.parseInt(infos[1]);
		int P = Integer.parseInt(infos[2]);
		map = new char[N][];
		boss = null;

		int[][] gamerIdx = new int[P][];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				if(map[i][j] !='.' && map[i][j] != 'X') {
					if(map[i][j] != 'B')
						gamerIdx[convertIdx(map[i][j])] = new int[] {i,j};
					else
						boss = new Player(i,j,0);
				}
			}
		}
		
		Queue<Player> q = new LinkedList<>();
		for(int i=0; i<P; i++) {
			infos = br.readLine().split(" ");
			q.offer(new Player(gamerIdx[i][0], gamerIdx[i][1], Integer.parseInt(infos[1])));
		}
		boss.atk = Integer.parseInt(br.readLine());
		
		
		PriorityQueue<Player> pq = new PriorityQueue<Player>();
		
		for(int i=0, size=q.size(); i<size; i++) {
			Player p = q.poll();
			p.setTime(getDistance(p));
//			System.out.println(i+"\'s time : "+p.time+" atk: "+p.atk);
			if(p.time !=0)	pq.offer(p);
		}
		
		int answer = killBoss(pq);
		System.out.println(answer);
	}
	
	static class Player implements Comparable<Player>{
		int x;
		int y;
		int atk;
		int time;
		
		public Player(int x, int y, int atk) {
			this.x= x; this.y =y; this.atk= atk;
		}
		
		public void setTime(int time) {
			this.time = time;
		}

		@Override
		public String toString() {
			return "Player [x=" + x + ", y=" + y + ", atk=" + atk + "]";
		}

		@Override
		public int compareTo(Player o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.time, o.time);
		}
		
		
		
	}

}
