import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N,M,ans;
	static int[][] map;
	static List<Point> list;
	static List<Bundle> bundle_list; //2번무리 돌둘 영역 저장
	static List<Point> b_list; //2번이하로 둬서 잡을 수 있는 모든 공백 후보
	static boolean[][] visited;
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	
	public static void main(String[] args) throws Exception {
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		bundle_list = new ArrayList<>();
		b_list = new ArrayList<>();
		visited = new boolean[N][M];
		list = new ArrayList<>();
		
		for(int i=0;i<N;++i) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0;j<M;++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<N;++i) {
			for(int j=0;j<M;++j) {
				//바둑돌인점에서 탐색 시작
				if(map[i][j] == 2 && visited[i][j] == false) {
					BFS(i,j);
				}
			}
		}
		
		//놓을 곳이 1개였다면
		if(b_list.size() == 1) {
			
			map[b_list.get(0).y][b_list.get(0).x] = 1;
			
			for(Bundle b : bundle_list) {
				boolean possible = true; //돌을 잡을 수 있는지 여부
				//각 무리에 대해 공백 좌표를 꺼냈을 때
				for(Point p : b.blank_list) {
					if(map[p.y][p.x] == 0) {
						possible = false;
						break;
					}
				}
				if(possible) ans += b.cnt;
			}
		} else {
			
			//공백 두 개에 놓았을 때 개수 세기
			for(int i=0;i<b_list.size();++i) {
				for(int j=i+1;j<b_list.size();++j) {
					map[b_list.get(i).y][b_list.get(i).x] = 1;
					map[b_list.get(j).y][b_list.get(j).x] = 1;
					
					int cand = 0;
					
					for(Bundle b : bundle_list) {
						boolean possible = true;
						
						for(Point p : b.blank_list) {
							if(map[p.y][p.x] == 0) {
								possible =false;
								break;
							}
						}
						
						if(possible == true) {
							cand += b.cnt;
						}
					}

					ans = Math.max(ans, cand);
					map[b_list.get(i).y][b_list.get(i).x] = 0;
					map[b_list.get(j).y][b_list.get(j).x] = 0;
				}
			}
		}
		
	
		System.out.println(ans);
	}
	
	static void BFS(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		HashSet<Point> blank = new HashSet<>(); //공백 좌표
		Bundle b = new Bundle(); //현재 탐색하고자 하는 2번 무리
		
		visited[r][c] = true;
		q.add(new Point(r,c));
		
		while(!q.isEmpty()) {
			int qSize = q.size();
			
			while(qSize-- > 0) {
				Point cur = q.peek();
				
				q.poll();
				b.cnt++; //poll의 개수가 무리의 개수
				for(int i=0;i<4;++i) {
					int ny = cur.y + dy[i];
					int nx = cur.x + dx[i];
					
					if(isIn(ny,nx) == false) continue;
					if(map[ny][nx] == 1) continue;
					if(visited[ny][nx]) continue; 
					
					if(map[ny][nx] == 0) {
						blank.add(new Point(ny,nx));
						continue;
					}
					
					visited[ny][nx] = true;
					
					Point nxt = new Point(ny,nx);
					q.offer(nxt);
				}
			}
		}
		
		//2번이하로 둬서 잡을 수 있는 수
		if(blank.size() <= 2) {
			for(Point p : blank) {
				b.blank_list.add(p); //현재 무리에서 빈칸 좌표 저장
				b_list.add(p); //두 번 이하로 잡을 수 있는 돌들의 좌표 저장
			}
			bundle_list.add(b);
		}
		
	}
	
	static boolean isIn(int r,int c) {
		return r >=0 && r < N && c>=0 && c < M;
	}
}

class Point {
	int y;
	int x;
	
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o!= null && o instanceof Point) {
			Point tmp = (Point)o;
			return this.y == tmp.y && this.x == tmp.x;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
}

//2번 돌들이 있는 무리에서 돌의 개수, 공백 좌표
class Bundle {
	int cnt;
	List<Point> blank_list;
	
	public Bundle() {
		blank_list = new ArrayList<>();
	}
}
