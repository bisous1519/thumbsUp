
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int M,N,P,ans,ey,ex,health;
	static char[][] map;
	static int[] dy = {1,-1,0,0};
	static int[] dx = {0,0,1,-1};
	static ArrayList<Player> arr;
	static PriorityQueue<Attacker> pq;
	
 	public static void main(String[] args) throws Exception {
 		st = new StringTokenizer(br.readLine());
 		M = Integer.parseInt(st.nextToken());
 		N = Integer.parseInt(st.nextToken());
 		P = Integer.parseInt(st.nextToken());
 		
 		pq = new PriorityQueue<>();
 		arr =new ArrayList<>();
 		map = new char[M][N];
 		
 		for(int i=0;i<M;++i) {
 			map[i] = br.readLine().toCharArray();
 			
 			for(int j=0;j<N;++j) {
 				if(map[i][j] == 'B') {
 					ey = i; ex = j;
 				} else if(map[i][j] >= 'a' && map[i][j] <= 'z') {
 					arr.add(new Player(map[i][j],i,j));
 				}
 			}
 		}
 	
 		for(int i=0;i<P;++i) {
 			st = new StringTokenizer(br.readLine());
 			String w = st.nextToken();
 			int deal = Integer.parseInt(st.nextToken());
 			
 			for(int j=0;j<arr.size();++j) {
 				if(arr.get(j).who == w.charAt(0)) {
 					arr.get(j).attack = deal;
 				}
 			}
 		}
 		
 		st = new StringTokenizer(br.readLine());
 		health = Integer.parseInt(st.nextToken());
 		
// 		for(Player p:arr) {
// 			System.out.println(p.y + " " + p.x + " " + p.who +" " + p.attack);
// 		}
 		
 		for(int i=0;i<arr.size();++i) {
 			int res = BFS(arr.get(i).y, arr.get(i).x);
 			if(res!=-1) pq.add(new Attacker(res,arr.get(i).attack));
 		}
 		
// 		while(!pq.isEmpty()) {
// 			System.out.println(pq.peek().dist + " " + pq.peek().deal);
// 			pq.poll();
// 		}

 		calculate();
 		System.out.println(ans);
 	}
 	static void calculate() {
 		
 		List<Integer> dmgList = new ArrayList<>();
 		int prev = 0;
 		while(!pq.isEmpty()) {
 			Attacker cur = pq.peek();
 			int sum = 0;
 			int cnt = 0;
 			//같은 위치에 있는 애들 세줘야함
 			
 			while(!pq.isEmpty() && cur.dist == pq.peek().dist) {
 				sum += pq.peek().deal;
 				cnt++;
 				pq.poll();
 			}
 			
 			//특정 플레이어 이전에 있던 애들이 보스 공격
 			for(int i=0;i<dmgList.size();++i) {
 				health -= dmgList.get(i) * (cur.dist - prev);
 			}
 			
 			//보스 죽음
 			if(health <= 0) return;
 			ans += cnt;
 			dmgList.add(sum);
 			prev = cur.dist;
 		}
 	}
 	
 	static int BFS(int r,int c) {
 		
 		boolean[][] visited = new boolean[M][N];
 		Queue<int[]> q = new LinkedList<>();
 		visited[r][c] = true;
 		q.offer(new int[] {r,c,0});
 		
 		while(!q.isEmpty()) {
 			int[] cur = q.poll();
 			
 			for(int i=0;i<4;++i) {
 				int ny = cur[0] + dy[i];
 				int nx = cur[1] + dx[i];
 				
 				if(isIn(ny,nx) == false) continue;
 				if(visited[ny][nx] == true || map[ny][nx] == 'X') continue;
 				if(map[ny][nx] == 'B') return cur[2]+1;
 				visited[ny][nx] = true;
 				
 				q.offer(new int[] {ny,nx,cur[2]+1});
 			}
 		}
 		
 		return -1;
 	}
 	
 	static boolean isIn(int r,int c) {
 		return r>=0 && r< M && c >=0 && c < N;
 	}
 	
 	static class Attacker implements Comparable<Attacker>{
 		int dist;
 		int deal;
 		
 		public Attacker(int dist,int deal) {
 			this.dist = dist;
 			this.deal = deal;
 		}
 		
 		@Override
 		public int compareTo(Attacker a) {
 			return Integer.compare(this.dist, a.dist); //거리순으로 가까운애부터
 		}
 	}
 	
 	static class Player {
 		int y;
 		int x;
 		char who;
 		int attack;
 		
 		public Player(char who,int y,int x) {
 			this.who = who;
 			this.y = y;
 			this.x = x;
 		}
 		
 	}
}