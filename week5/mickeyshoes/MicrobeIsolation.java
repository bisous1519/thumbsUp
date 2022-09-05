import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

public class MicrobeIsolation {
	
	public static int N;
	
	public static int[] dxs = {-1,1,0,0};
	public static int[] dys = {0,0,-1,1};
	
	public static LinkedList<Microbe> microbes;
	
	public static int convert2Int(String s) {
		return Integer.parseInt(s);
	}
	
	public static boolean isRange(int x, int y) {
		return 0!=x && x!=N-1 && 0!=y&& y!=N-1;
	}
	
	public static void moveCluster() {
		boolean[][] visited = new boolean[N][N];
		LinkedList<Microbe> nextMicrobes = new LinkedList<Microbe>();
		for(Microbe cur : microbes) {
			
			int nx = cur.x + dxs[cur.dir];
			int ny = cur.y + dys[cur.dir];
			
			if(!isRange(nx,ny)) {
				cur.convertDir();
				cur.cluster/=2;
			}
			if(visited[nx][ny]) {
				Microbe merge = null;
				int rmIdx = -1;
				for(int i=0; i<nextMicrobes.size(); i++) {
					Microbe temp = nextMicrobes.get(i);
					if (temp.x == nx && temp.y == ny) {
						merge = new Microbe(temp.x, temp.y, temp.cluster>=cur.cluster? temp.dir:cur.dir, temp.cluster+cur.cluster);
						rmIdx = i;
						break;
					}
				}
				nextMicrobes.remove(rmIdx);
				nextMicrobes.add(merge);
				continue;
			}

			if(cur.cluster!=0) {
				cur.x = nx; cur.y=ny;
				nextMicrobes.add(cur);
				visited[nx][ny] = true;
			}

		}
		
		microbes = nextMicrobes;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = convert2Int(br.readLine());
		for(int t=1; t<=T; t++) {
			String[] infos = br.readLine().split(" ");
			N = convert2Int(infos[0]);
			int M = convert2Int(infos[1]);
			int K = convert2Int(infos[2]);
			microbes = new LinkedList<>();
			for(int i=1; i<=K; i++) {
				infos = br.readLine().split(" ");
				int x = convert2Int(infos[0]);
				int y = convert2Int(infos[1]);
				int cluster = convert2Int(infos[2]);
				int dir = convert2Int(infos[3]);
				microbes.add(new Microbe(x, y, dir-1, cluster));
			}
//			System.out.println(t+" before :"+microbes.size());
			for(int i=0; i<M; i++) {
				Collections.sort(microbes);
				moveCluster();
			}
			
			int answer = 0;
			for(int i=0; i<microbes.size(); i++) {
				answer += microbes.get(i).cluster;
			}
			sb.append(String.format("#%d %d\n", t, answer));
		}
		System.out.println(sb.toString());
	}
	
	static class Microbe implements Comparable<Microbe>{
		int x;
		int y;
		int dir;
		int cluster;
		
		public Microbe(int x, int y, int dir, int cluster) {
			this.x=x; this.y=y; this.dir=dir; this.cluster=cluster;
		}
		
		public void convertDir() {
			if(dir>=2) {
				this.dir = this.dir==3? 2:3;
			}
			else {
				this.dir = this.dir==1? 0:1;
			}
		}

		@Override
		public int compareTo(Microbe o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cluster, o.cluster)*-1;
		}
		
	}

}
