
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.StringTokenizer;
/*
    observation: 시뮬레이션 문제
                바이러스가 겹치는 부분에서 항상 작은 숫자가 우선권이 갖는다는 것 -> 큰 수부터 전염하면 나중에 작은수가 옴
                바이러스 전염은 naive하게 해도 되고 BFS를 써서 한칸씩 움직여도 됨
 */
public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N,K,S,X,Y;
	static int[][] map;
	static Set<Integer> s;
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	static int[][] tmpMap;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		tmpMap = new int[N][N];
		
		for(int i=0;i<N;++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken())-1;
		Y = Integer.parseInt(st.nextToken())-1;
		
		for(int i=1;i<=S;++i) {
			
			//tmpMap에서 진행할것.
			copyMap(map,tmpMap);
			//1~K번 바이러스 증식 큰숫자부터 하면 마지막에 덮어쓴 바이러스로 가능
			for(int j=K;j>=1;--j) {
				spread(j);
			}
			copyMap(tmpMap,map);
			if(map[X][Y]!=0) break;
		}
		
		System.out.println(map[X][Y]);
	}
	
	static void spread(int virus) {
		
		//특정 virus spread
		for(int i=0;i<N;++i) {
			for(int j=0;j<N;++j) {
				if(map[i][j] == virus) {
					
					for(int k=0;k<4;++k) {
						int ny = i+dy[k];
						int nx = j+dx[k];
						
						//범위 벗어나면 제끼고 이미 바이러스있으면 제끼고
						if(isIn(ny,nx) == false || map[ny][nx] > 0) continue;
						
						tmpMap[ny][nx] = virus; //복사되는 맵에 저장.
					}
				}
			}
		}
	}
	
	static boolean isIn(int y, int x) {
		return y >= 0 && y < N && x >=0 && x < N;
	}
	
	static void copyMap(int[][] src, int[][] des) {
		
		for(int i=0;i<N;++i) {
			des[i] = Arrays.copyOf(src[i], src[i].length);
		}
	}
}

